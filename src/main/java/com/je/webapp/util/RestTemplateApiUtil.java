/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 6. 25. 오후 12:24
 * Description  : 
 ******************************************************/
package com.je.webapp.util;

import com.je.webapp.util.exception.APICallException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class RestTemplateApiUtil {

    private final static Logger logger = LoggerFactory.getLogger(RestTemplateApiUtil.class);


    private RestTemplate restTemplate;

    private HttpComponentsClientHttpRequestFactory factory;
    private HttpHeaders headers;
    private MultiValueMap<String, Object> requestBody;


    final private int DEFAULT_CONNECTION_TIMEOUT = 3 * 1000;
    final private int DEFAULT_READ_TIMEOUT = 3 * 1000;

    /**
     * Constructor
     */
    public RestTemplateApiUtil() {
        if (factory == null) {
            setTimeout(this.DEFAULT_CONNECTION_TIMEOUT, this.DEFAULT_READ_TIMEOUT);
        }

        this.headers = new HttpHeaders();
        this.restTemplate = new RestTemplate(this.factory);
    }

    /**
     * Constructor Builder
     *
     * @param connectionTimeout
     * @param readTimeout
     */
    public void RestTemplateApiUtilBuilder(int connectionTimeout, int readTimeout) {
        setTimeout(connectionTimeout, readTimeout);
        new RestTemplateApiUtil();
    }


    /**
     * Constructor Builder
     * Timeout 만을 Default 값으로 셋팅한다.
     */
    public void RestTemplateApiUtilBuilder() {
        new RestTemplateApiUtil();
    }

    /**
     * API 호출 처리
     *
     * @param requestMethod POST or GET
     * @param apiUrl        호출 URL
     * @param headerMap     Header 값
     * @param paramerterMap 파라메터
     * @return
     */
    public Map<String, Object> callApi(HttpMethod requestMethod, String apiUrl,
                                       Map<String, String> headerMap, Map<String, Object> paramerterMap) throws IOException, APICallException {

        logger.info("============== start Call API : " + apiUrl);
        logger.info("Parameter : " + paramerterMap.toString());

        Map<String, Object> result = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        setHeaders(headerMap);

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, requestMethod, request, String.class, paramerterMap);
        try {
            if (responseEntity.getStatusCode() == HttpStatus.OK) {

                result = objectMapper.readValue(responseEntity.getBody(), new TypeReference<Map<String, Object>>() {
                });
                logger.info("Result : " + result.toString());

            } else {
                logger.error("API HTTP_STATUS Not 200~!! But," + responseEntity.getStatusCode());
                throw new APICallException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("============== End Call API : " + apiUrl);
        return result;
    }


    /**
     * RestTemplate의 Timeout을 설정하는 factory에 timeout값을 입력한다
     *
     * @param connectionTimeout
     * @param readTimeout
     */
    private void setTimeout(int connectionTimeout, int readTimeout) {
        this.factory = new HttpComponentsClientHttpRequestFactory();
        this.factory.setConnectTimeout(connectionTimeout);
        this.factory.setReadTimeout(readTimeout);

    }

    /**
     * RestTemplate의 Header를 셋팅한다
     * 별도 값이 없으면, Content-type="application/json;charset=UTF-8" 로 설정한다
     *
     * @param headerMap
     */
    private void setHeaders(Map<String, String> headerMap) {

        if (headerMap != null) {
            Set<String> headerKeys = headerMap.keySet();
            Iterator<String> iterator = headerKeys.iterator();

            String headerkey;

            if (!(headerKeys.contains("content-type") || headerKeys.contains("Content-type"))) {
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            }

            while (iterator.hasNext()) {
                headerkey = iterator.next();
                headers.set(headerkey, headerMap.get(headerkey));
            }
        } else {
            this.headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }
    }

    /**
     * REQUEST Body를 셋팅한다.
     *
     * @param bodyMap
     */
    private void setBody(Map<String, Object> bodyMap) {

        if (bodyMap != null) {
            Collection<String> bodyKey = bodyMap.keySet();
            Iterator<String> keyIterator = bodyKey.iterator();

            while (keyIterator.hasNext()) {
                Object value = bodyMap.get(keyIterator.next());
                requestBody.add(keyIterator.next(), value);
            }
        }
    }


    public <T> T callApiByType(HttpMethod requestMethod, String apiUrl,
                               Map<String, String> headerMap, Map<String, Object> paramerterMap, Class<T> responseType) throws IOException, APICallException {

        logger.info("============== start Call API : " + apiUrl);
        logger.info("Parameter : " + paramerterMap.toString());

        T result = null;
        ObjectMapper objectMapper = new ObjectMapper();


        setHeaders(headerMap);

        HttpEntity request = new HttpEntity(headers);

        if (requestMethod == HttpMethod.GET && paramerterMap.size() != 0) {
            String paramStr = setGETMethodParam(paramerterMap);
            apiUrl += paramStr.toString();
        }


        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, requestMethod, request, String.class, paramerterMap);
            String resultStr = responseEntity.getBody();
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
            logger.info("Result : " + resultStr.toString());
                result = objectMapper.readValue(resultStr, new TypeReference<T>() {
                });
                logger.info("Result : " + result.toString());
            } else {
                logger.error("API HTTP_STATUS Not 200~!! But," + responseEntity.getStatusCode());
                throw new APICallException();
            }
        } catch (IOException e) {


            logger.error("============== End Call API : " + apiUrl);
        } catch (Exception e) {
            logger.error("============== End Call API : " + apiUrl);
            logger.error(e.getLocalizedMessage());
            logger.error(e.getLocalizedMessage().toString());
        }
            return result;
    }

    private String setGETMethodParam(Map<String, Object> paramerterMap) {
        StringBuffer paramStr = new StringBuffer();

        Set<String> paramKey = paramerterMap.keySet();
        Iterator<String> paramIterator = paramKey.iterator();

        while(paramIterator.hasNext()) {
            String key = paramIterator.next();
            if(paramStr.length() == 0 ){
                paramStr.append("?" + key + "=" + paramerterMap.get(key));
            } else {
                paramStr.append("&" + key + "=" + paramerterMap.get(key));
            }
        }
        return paramStr.toString();
    }

}
