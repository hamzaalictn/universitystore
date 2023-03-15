package com.universitystore.runners;

public class Adhoc2 {
    public static void main(String[] args) throws Exception {
        // Read the pfx file into a byte array
        FileInputStream fis = new FileInputStream("path/to/your/file.pfx");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int i; (i = fis.read(buf)) != -1;) {
            bos.write(buf, 0, i);
        }
        byte[] pfxData = bos.toByteArray();

        // Create the KeyStore object with the pfx file and password
        KeyStore pfxKeyStore = KeyStore.getInstance("PKCS12");
        pfxKeyStore.load(new FileInputStream("path/to/your/file.pfx"), "password".toCharArray());

        // Create the ChromeOptions object and add the pfx file as a certificate
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--auto-select-certificate-for-url=https://example.com", "--ssl-client-certificate-data=" + Base64.getEncoder().encodeToString(pfxData));
        options.setCapability("ssl.keyStore", pfxKeyStore);
        options.setCapability("ssl.keyStorePassword", "password");

        // Create the WebDriver object with the ChromeOptions object
        WebDriver driver = new ChromeDriver(options);

        // Use the driver to interact with a website that requires SSL client authentication
        driver.get("https://example.com");
    }
}
