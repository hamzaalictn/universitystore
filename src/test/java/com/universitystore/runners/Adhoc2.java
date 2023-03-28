package com.universitystore.runners;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class Adhoc2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        int numberOfThreads = 3; // Change this to the desired number of parallel threads
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Callable<String> task = () -> {
                ChromeOptions chromeOptions = new ChromeOptions();

                // Add any desired Chrome options
                // chromeOptions.addArguments("--ignore-certificate-errors");

                WebDriver driver = new ChromeDriver(chromeOptions);
                driver.get("https://your-url.com");

                try {
                    Robot robot = new Robot();
                    // Wait for the certificate selection popup to appear
                    Thread.sleep(2000);

                    // Press Enter to accept the certificate
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Task completed";
            };

            futures.add(executorService.submit(task));
        }

        executorService.shutdown();
    }
}
