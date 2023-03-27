package com.universitystore.runners;

public class Adhoc {

    public static void main(String[] args) {
        KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
        clientKeyStore.load(new FileInputStream("client.p12"), "password".toCharArray());

        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(clientKeyStore, "password".toCharArray())
                .build();

        HttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        HttpGet httpGet = new HttpGet("https://www.example.com");
        HttpResponse response = httpClient.execute(httpGet);

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.example.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium WebDriver");
        element.submit();

        driver.quit();
    }
}
