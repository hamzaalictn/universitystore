package com.universitystore.runners;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class Main {
    public static void main(String[] args) {
        File clientSslCertificate = new File("path/to/client/certificate.p12");
        String certificatePassword = "certificate_password";

        SeleniumSslProxy sslProxy = new SeleniumSslProxy(clientSslCertificate, certificatePassword);
        sslProxy.start();

        String proxyStr = String.format("%s:%d", sslProxy.getProxyIp(), sslProxy.getPort());
        Proxy seleniumProxy = new Proxy().setHttpProxy(proxyStr).setSslProxy(proxyStr);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setProxy(seleniumProxy);
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Use the driver as needed

        driver.quit();
        sslProxy.stop();
    }
}
