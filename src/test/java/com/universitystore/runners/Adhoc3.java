package com.universitystore.runners;

public class Adhoc3 {
    File clientSslCertificate = new File("/path/to/client/ssl/certificate.pfx");
    String certificatePassword = "password";
    SeleniumSslProxy sslProxy = new SeleniumSslProxy(clientSslCertificate, certificatePassword);

    // Step 2: Start the proxy server
        sslProxy.start();

        try {
        // Step 3: Configure the WebDriver to use the proxy server
        ChromeOptions options = new ChromeOptions();
        Proxy seleniumProxy = new Proxy();
        seleniumProxy.setProxyType(ProxyType.MANUAL);
        InetSocketAddress proxyAddress = new InetSocketAddress("localhost", sslProxy.getPort());
        String proxyString = String.format("%s:%d", proxyAddress.getHostString(), proxyAddress.getPort());
        seleniumProxy.setHttpProxy(proxyString);
        seleniumProxy.setSslProxy(proxyString);
        options.setCapability(CapabilityType.PROXY, seleniumProxy);

        // Step 4: Create a RemoteWebDriver instance
        RemoteWebDriver driver = new ChromeDriver(options);

        // Step 5: Use the WebDriver instance to navigate to a web page
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.example.com");

        // Step 6: Print the page title
        System.out.println("Page title: " + driver.getTitle());

        // Step 7: Quit the WebDriver
        driver.quit();
    } finally {
        // Step 8: Stop the proxy server
        sslProxy.stop();
    }
}
