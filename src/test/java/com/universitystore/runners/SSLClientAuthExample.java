package com.universitystore.runners;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class SSLClientAuthExample {

    public static void main(String[] args) {

        String pfxFilePath = "/path/to/cert.pfx";
        String pfxPassword = "myPFXPassword";
        String websiteDomain = "www.example.com";

        // Set SSL client certificate configuration using UserPreferences
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("certificate_manager.policy", 2);
        prefs.put("profile.content_settings.exceptions.certificate_errors." + websiteDomain,
                ImmutableMap.of("certificate_file", pfxFilePath, "password", pfxPassword));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Or alternatively, set SSL client certificate file and password directly in ChromeOptions
        /*
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ssl-client-certificate=" + pfxFilePath);
        options.addArguments("--ssl-client-key-passphrase=" + pfxPassword);
        */

        // Create WebDriver instance with the desired capabilities
        WebDriver driver = new ChromeDriver(options);
    }
}
