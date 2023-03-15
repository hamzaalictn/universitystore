package com.universitystore.runners;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import com.google.common.collect.ImmutableMap;

public class SSLClientAuthExample {

    public static void main(String[] args) {

        String pfxFilePath = "/path/to/cert.pfx";
        String pfxPassword = "myPFXPassword";
        String websiteDomain = "www.example.com";

        // Set SSL client certificate configuration using Chrome preferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.content_settings.exceptions.certificate_errors." + websiteDomain,
                ImmutableMap.of("certificate_file", pfxFilePath, "password", pfxPassword));
        ChromeOptions options = new ChromeOptions();
        options.setCapability("chrome.prefs", prefs);

        // Or alternatively, set SSL client certificate file and password directly in ChromeOptions
        /*
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ssl-client-certificate=" + pfxFilePath);
        options.addArguments("--ssl-client-key-passphrase=" + pfxPassword);
        */

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        // Create WebDriver instance with the desired capabilities
        // WebDriver driver = new ChromeDriver(capabilities);
    }
}