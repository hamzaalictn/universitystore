package com.universitystore.runners;

public class AdHoc {
    public static void main(String[] args) throws Exception {
        // Read the pfx file into a byte array
        FileInputStream fis = new FileInputStream("path/to/your/file.pfx");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int i; (i = fis.read(buf)) != -1;) {
            bos.write(buf, 0, i);
        }
        byte[] pfxData = bos.toByteArray();

        // Create SSL context for the pfx file
        KeyStore pfxKeyStore = KeyStore.getInstance("PKCS12");
        pfxKeyStore.load(new FileInputStream("path/to/your/file.pfx"), "password".toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(pfxKeyStore, "password".toCharArray());
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(keyManagers, null, null);

        // Create trust manager that trusts all certificates
        TrustManager[] trustManagers = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {}
        }};

        // Create the DesiredCapabilities object with the required capabilities
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("goog:chromeOptions", new ChromeOptions().setExperimentalOption("useAutomationExtension", false).addArguments("--ignore-certificate-errors"));
        capabilities.setCapability("goog:chromeOptions", new ChromeOptions().setExperimentalOption("w3c", false));

        // Create the ChromeOptions object and add the pfx file data as an argument
        ChromeOptions options = new ChromeOptions();
        options.setCapability("acceptSslCerts", true);
        options.addArguments("--ssl-client-certificate-data=" + Base64.getEncoder().encodeToString(pfxData));
        options.setCapability(ChromeOptions.CAPABILITY, options);

        // Create the WebDriver object with the desired capabilities and options
        WebDriver driver = new ChromeDriver(capabilities);

        // Use the driver to interact with a website that requires SSL client authentication
        driver.get("https://example.com");
    }
}
