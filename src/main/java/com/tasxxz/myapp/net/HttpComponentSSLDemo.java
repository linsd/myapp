package com.tasxxz.myapp.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class HttpComponentSSLDemo {

    public CloseableHttpClient createHttpsClient() {

        X509TrustManager x509mgr = new X509TrustManager() {

            @Override

            public void checkClientTrusted(X509Certificate[] xcs, String string) {

            }

            @Override

            public void checkServerTrusted(X509Certificate[] xcs, String string) {

            }

            @Override

            public X509Certificate[] getAcceptedIssuers() {

                return null;

            }

        };

        SSLContext sslContext = null;

        try {

            sslContext = SSLContext.getInstance("TLS");

        } catch (NoSuchAlgorithmException e1) {

            e1.printStackTrace();

        }

        try {

            sslContext.init(null, new TrustManager[]{x509mgr}, null);

        } catch (KeyManagementException e) {

            e.printStackTrace();

        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(

                sslContext,

                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();

    }

    /**
     * @param args
     */

    public static void main(String[] args) throws Exception {

//	CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpComponentSSLDemo t = new HttpComponentSSLDemo();

        CloseableHttpClient httpclient = t.createHttpsClient();

//	HttpGet httpGet = new HttpGet("http://www.google.com.hk/search?hl=zh&ie=utf-8&num=30&output=rss&q=java+%7C+hibernate+%7C+spring&tbm=blg");

        HttpGet httpGet = new HttpGet("https://www.google.com.hk/search?hl=zh&ie=utf-8&num=30&output=rss&q=java+%7C+hibernate+%7C+spring&tbm=blg");

//	HttpGet httpGet = new HttpGet("http://itindex.net/");

        try {

            HttpResponse httpResp = httpclient.execute(httpGet);

            int statusCode = httpResp.getStatusLine().getStatusCode();

            if (statusCode == 200) {

                System.out.println(EntityUtils.toString(httpResp.getEntity()));

            } else {
                System.out.println("http status:" + statusCode);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            httpGet.releaseConnection();

        }

    }

}