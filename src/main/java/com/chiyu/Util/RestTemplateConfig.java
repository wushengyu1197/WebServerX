package com.chiyu.Util;

//        import com.rfid.instantDeploy.common.SSLVerify;
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.http.client.SimpleClientHttpRequestFactory;
//        import org.springframework.web.client.RestTemplate;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class RestTemplateConfig {
//    @Bean
//    public RestTemplate restTemplate(){
//        SimpleClientHttpRequestFactory factory = new SSLVerify();
//        factory.setReadTimeout(10000);//单位为ms
//        factory.setConnectTimeout(5000);//单位为ms
//        return new RestTemplate(factory);
//    }
@Bean
public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
}

        @Bean
        public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
                HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                factory.setConnectTimeout(3000);
                factory.setReadTimeout(5000);
                return factory;
        }

        public static HttpComponentsClientHttpRequestFactory generateHttpRequestFactory()
                throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
                TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
                SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                        new NoopHostnameVerifier());
                HttpClientBuilder httpClientBuilder = HttpClients.custom();
                httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
                CloseableHttpClient httpClient = httpClientBuilder.build();
                HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                factory.setHttpClient(httpClient);
                return factory;
        }


}