package com.universitystore.runners;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import okhttp3.*;
import org.openqa.selenium.Proxy;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.stream.Stream;

/**
 * This proxy intercepts https requests and adds certificate based client authentication.
 * It was only tested chromeDriver. For other drivers, minor adjustments in the filter may be necessary.
 */
public class SeleniumSslProxy extends Proxy {

    private final BrowserMobProxy browserMobProxy;

    public SeleniumSslProxy(File clientSslCertificate, String certificatePassword) {
        this.browserMobProxy = new BrowserMobProxyServer();

        browserMobProxy.addRequestFilter((request, contents, messageInfo) -> {
            String url = request.getUri().replace("http://", "https://");
            if (Stream.of("accounts.google.com", "gstatic.com").anyMatch(url::contains)
                    || !url.startsWith("https://")) {
                return null; // do not intercept driver-specific and non-https requests
            }
            SSLContext sslContext = createSslContext(clientSslCertificate, certificatePassword);
            Response intermediateResponse = doHttpsRequest(sslContext, url, request.getMethod().name(), contents.getContentType().toString(), contents.getBinaryContents());
            return convertOkhttpResponseToNettyResponse(intermediateResponse);
        });

        this.setProxyType(Proxy.ProxyType.MANUAL);
    }

    public void start() {
        this.browserMobProxy.start();
        InetSocketAddress connectableAddressAndPort = new InetSocketAddress(ClientUtil.getConnectableAddress(), browserMobProxy.getPort());
        String proxyStr = String.format("%s:%d", connectableAddressAndPort.getHostString(), connectableAddressAndPort.getPort());
        this.setHttpProxy(proxyStr);
        this.setSslProxy(proxyStr);
    }

    public void stop() {
        this.browserMobProxy.stop();
    }

    private Response doHttpsRequest(SSLContext sslContext, String url, String httpMethod, String mediaType, byte[] body) {
        RequestBody requestBody = null;
        if (httpMethod != null && !httpMethod.equalsIgnoreCase(HttpMethod.GET.name())) {
            requestBody = RequestBody.create(MediaType.get(mediaType), body);
        }

        Request request = new Request.Builder()
                .url(url)
                .method(httpMethod, requestBody)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) TrustAllCertsTrustManager.INSTANCE)
                .build();
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private SSLContext createSslContext(File clientSslCertificate, String certificatePassword) {
        try {
            KeyStore keyStore = KeyStore.Builder.newInstance("PKCS12", null, clientSslCertificate, new KeyStore.PasswordProtection(certificatePassword.toCharArray())).getKeyStore();

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, certificatePassword.toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

            TrustManager[] trustManagers = new TrustManager[] { TrustAllCertsTrustManager.INSTANCE };
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyManagers, trustManagers, new SecureRandom());

            return sslContext;
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FullHttpResponse convertOkhttpResponseToNettyResponse(Response okhttpResponse) {
        HttpResponseStatus httpResponseStatus = HttpResponseStatus.valueOf(okhttpResponse.code());
        HttpVersion httpVersion = HttpVersion.valueOf(okhttpResponse.protocol().toString());

        ByteBuf content = null;
        try (ResponseBody body = okhttpResponse.body()){
            content = Unpooled.wrappedBuffer(body.bytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FullHttpResponse nettyResponse = new DefaultFullHttpResponse(httpVersion, httpResponseStatus, content);
        okhttpResponse.headers().toMultimap().forEach((key, values) -> {
            nettyResponse.headers().remove(key);
            nettyResponse.headers().add(key, String.join(",", values));
        });

        return nettyResponse;
    }

    private static class TrustAllCertsTrustManager implements X509TrustManager {

        private static final TrustAllCertsTrustManager INSTANCE = new TrustAllCertsTrustManager();

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
