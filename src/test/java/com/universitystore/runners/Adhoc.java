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

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.example.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium WebDriver");
        element.submit();

        driver.quit();
    }
}
