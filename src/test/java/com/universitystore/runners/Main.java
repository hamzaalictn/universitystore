package com.universitystore.runners;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.content_settings.exceptions.certificate_errors.<your-website-domain>",ImmutableMap.of("certificate_file", "<path-to-pfx-file>", "password", "<pfx-password>"));
        options.setCapability("chrome.prefs", prefs);
    }


}
