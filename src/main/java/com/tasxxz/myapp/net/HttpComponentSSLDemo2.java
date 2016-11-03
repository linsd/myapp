package com.tasxxz.myapp.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by pc on 2016/5/5.
 */
public class HttpComponentSSLDemo2 {


    private String DEFAULT_CHARSET = "UTF-8";

    //表示请求器是否已经做了初始化工作
    private boolean hasInit = false;

    //连接超时时间，默认30秒
    private int socketTimeout = 30000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpComponentSSLDemo2() {
        super();
    }

    private void init() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();

        httpClient = HttpClientBuilder.create().setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setDefaultRequestConfig(requestConfig).build();

        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        hasInit = true;
    }

    public String request(String url, Map<String, String> param) {

        String result = null;
        HttpPost httpPost = null;
        HttpEntity responseEntity = null;

        try {
            if (!hasInit) {
                init();
            }
            httpPost = new HttpPost(url);
            //设置请求器的配置
            httpPost.setConfig(requestConfig);
            HttpEntity entity = this.wrapParams(httpPost,param);

            httpPost.setEntity(entity);
            //提交请求
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            responseEntity = response.getEntity();
            result = EntityUtils.toString(responseEntity, getCharset());
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println(result);
            } else {
                System.out.println(response.getStatusLine());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                httpPost.abort();
            }
            if (null != responseEntity) {
                try {
                    EntityUtils.consume(responseEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig() {
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    /**
     *
     * @param requestConfig 设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        requestConfig = requestConfig;
    }

    /**
     * 参数封装
     * @return
     */
    public HttpEntity wrapParams(HttpPost httpPost,Map<String,String> params) {
        //封装提交参数
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            String paramValue = "";
            if(entry.getValue() != null){
                paramValue = String.valueOf(entry.getValue());
            }
            pairs.add(new BasicNameValuePair(entry.getKey(), paramValue));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8);
        return entity;
    }

    /**
     * 默认的编码
     * @return
     */
    protected String getCharset(){
        return DEFAULT_CHARSET;
    }
    
    public static void main(String[] args) {
    	String url = "http://jingyan.baidu.com/article/e52e3615a2b18f40c60c51d1.html";
    	Map<String, String> param = new HashMap<String, String>();
		String rel = new HttpComponentSSLDemo2().request(url, param);
		System.out.println(rel);

	}
}