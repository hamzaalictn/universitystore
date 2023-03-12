package com.universitystore.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.file.Paths;

public class Driver {

    private static final String DOWNLOAD_DIR = Paths.get(System.getProperty("user.dir"), "downloads").toString();
    private static final String CERTIFICATE_PATH = Paths.get(System.getProperty("user.dir"), "certificate.pfx").toString();
    private static final String CERTIFICATE_PASSWORD = "password123";
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {
        throw new UnsupportedOperationException("Cannot instantiate utility class");
    }

    /**
     * Returns a new instance of a WebDriver based on the browser specified in the configuration properties file.
     * If an instance of the WebDriver already exists, that instance is returned.
     *
     * @return a new instance of a WebDriver
     * @throws RuntimeException if an invalid browser is specified in the configuration properties file
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browserName = com.universitystore.utilities.ConfigurationReader.getProperty("browser").toLowerCase();
            WebDriver driver;

            switch (browserName) {
                case "chrome":
                    driver = createChromeDriver();
                    break;
                case "firefox":
                    driver = createFirefoxDriver();
                    break;
                case "edge":
                    driver = createEdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser name specified in configuration properties file: " + browserName);
            }

            driverPool.set(driver);
        }

        return driverPool.get();
    }

    private static ChromeDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    public static ChromeDriver createChromeDriverWithSSL() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setCapability("browser.download.folderList", 2);
        options.setCapability("browser.download.dir", DOWNLOAD_DIR);
        options.setCapability("browser.helperApps.neverAsk.saveToDisk", "application/pdf");

        // Load certificate file and set up SSL
        options.setCapability("security.enterprise_roots.enabled", true);
        options.setCapability("profile.default_content_settings.popups", 0);
        options.setCapability("download.default_directory", DOWNLOAD_DIR);
        options.setCapability("download.prompt_for_download", false);
        options.setCapability("download.directory_upgrade", true);
        options.setCapability("download.extensions_to_open", "");

        // Load certificate file and set up SSL
        File certificateFile = new File(CERTIFICATE_PATH);
        options.setCapability("ssl.client_certificate", certificateFile.getAbsolutePath());
        options.setCapability("ssl.client_key_passphrase", CERTIFICATE_PASSWORD);

        return new ChromeDriver(options);
    }

    private static EdgeDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized"); // Maximize browser window on startup
        return new EdgeDriver(options);
    }

    private static FirefoxDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    /**
     * Closes the WebDriver instance and removes it from the thread pool.
     */
    public static void closeDriver() {
        WebDriver driver = driverPool.get();
        if (driver != null) {
            driver.quit();
            driverPool.remove();
        }
    }
}