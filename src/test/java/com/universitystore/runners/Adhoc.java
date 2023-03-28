package com.universitystore.runners;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
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

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.example.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // Extract the session cookie from the response
        Header[] headers = response.getHeaders("Set-Cookie");
        String sessionCookie = headers[0].getValue();

        // Set up Selenium WebDriver and navigate to the website
        WebDriver driver = new ChromeDriver();
        driver.manage().addCookie(new Cookie("session", sessionCookie, ".example.com", "/", null, false, true));
        driver.get("https://www.example.com");



        String pfxPath = "path/to/your/certificate.pfx";
        String pfxPassword = "your-certificate-password";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--client-certificate=" + pfxPath);
        chromeOptions.addArguments("--ssl-client-certificate-password=" + pfxPassword);



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

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.example.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium WebDriver");
        element.submit();

        driver.quit();
    }
}
