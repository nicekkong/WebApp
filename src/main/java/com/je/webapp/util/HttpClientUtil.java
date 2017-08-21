package com.je.webapp.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class HttpClientUtil {

	public Map<String, Object> doGet(String url, HashMap<String,String> headerMap, HashMap<String,String> paramMap) throws Exception {
		
		StringBuilder result = new StringBuilder();
		Map<String, Object> resultMap;
//		InputStream inputStream = null;
//		BufferedReader bufferedReader = null;
//		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
//		CloseableHttpResponse response  = null;

		StringBuilder urlSb = new StringBuilder();
		urlSb.append(url + "?");

		//파라미터가 있을 경우 세팅한다.
		if(paramMap != null && paramMap.size() > 0) {
			Collection<String> paramKeys = paramMap.keySet();
			Iterator<String> iterator = paramKeys.iterator();
			int idx = 0;

			while(iterator.hasNext()) {
				String paramKey = String.valueOf(iterator.next());

				if(idx == 0) {
					urlSb.append(paramKey + "=" + paramMap.get(paramKey));
				} else {
					urlSb.append("&" + paramKey + "=" + paramMap.get(paramKey));
				}

				idx++;
			}
		}

		HttpGet httpGet = new HttpGet(urlSb.toString());
		//헤더가 있을 경우 세팅한다.
		if(headerMap != null && headerMap.size() > 0) {
			Collection<String> headerKeys = headerMap.keySet();
			Iterator<String> iterator = headerKeys.iterator();

			while(iterator.hasNext()) {
				String headerKey = String.valueOf(iterator.next());
				httpGet.setHeader(headerKey, headerMap.get(headerKey));
			}
		}
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(10 * 1000)
				.setConnectTimeout(10 * 1000)
				.build();

		httpGet.setConfig(config);
		
		try (CloseableHttpClient 	httpclient 		= HttpClientBuilder.create().build();
			 CloseableHttpResponse 	response 		= httpclient.execute(httpGet);
			 InputStream 			inputStream 	= response.getEntity().getContent();
			 BufferedReader 		bufferedReader 	= new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
			) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "\n");
			}

			ObjectMapper objectMapper = new ObjectMapper();
			resultMap = objectMapper.readValue(result.toString(), new TypeReference<Map<String, Object>>(){});
			
		} catch(Exception e) {
			throw e;
		}
//		finally {
//			if(inputStream != null) try { inputStream.close(); } catch(Exception e) {}
//			if(bufferedReader != null) try { bufferedReader.close(); } catch(Exception e) {}
//			HttpClientUtils.closeQuietly(response);
//			HttpClientUtils.closeQuietly(httpclient);
//		}
		
		return resultMap;
		
	}
	
	/**
     * HttpClient 모듈을 이용하여 Post 방식으로 Http 통신을 요청 및 응답 받는다.
     *
     * @param url : 요청 URL
     * @param headerMap : 요청 Header Map
     * @param paramMap : 요청 파라메터 Map
     * @return result : 응답결과 json 문자열
     */
    public Map<String, Object> doPost(String url, Map<String,String> headerMap, Map<String,String> paramMap) throws Exception {

    	StringBuilder result = new StringBuilder();
		Map<String, Object> resultMap;
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		CloseableHttpResponse response  = null;
        HttpPost httpPost = null;

        try {
        	
        	RequestConfig config = RequestConfig.custom()
	                .setSocketTimeout(10 * 1000)
	                .setConnectTimeout(10 * 1000)
	                .build();
        	
            httpPost = new HttpPost(url);
            httpPost.setConfig(config);

            //Header Setting
            if (headerMap != null && headerMap.size() > 0) {
            	
                Collection<String> headerKeys = headerMap.keySet();
                Iterator<String> iterator = headerKeys.iterator();

                while (iterator.hasNext()) {
                    String headerKey = String.valueOf(iterator.next());
                    httpPost.setHeader(headerKey, headerMap.get(headerKey));
                }
                
            }

            // 파라미터가 있을 경우 세팅한다.
            if (paramMap != null && paramMap.size() > 0) {
            	
                Collection<String> paramKeys = paramMap.keySet();
                Iterator<String> iterator = paramKeys.iterator();
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();

                while (iterator.hasNext()) {
                    String paramKey = String.valueOf(iterator.next());
                    paramList.add(new BasicNameValuePair(paramKey, paramMap.get(paramKey)));
                }

                httpPost.setEntity(new UrlEncodedFormEntity(paramList));
                
            }

            response = httpclient.execute(httpPost);

            inputStream = response.getEntity().getContent();
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
			
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line + "\n");
			}


			ObjectMapper objectMapper = new ObjectMapper();
			resultMap = objectMapper.readValue(result.toString(), new TypeReference<Map<String, Object>>(){});




        } catch(Exception e) {
			throw e;
		} finally {
			if(inputStream != null) try { inputStream.close(); } catch(Exception e) {}
			if(bufferedReader != null) try { bufferedReader.close(); } catch(Exception e) {}
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(httpclient);
		}
		
		return resultMap;
		
    }

}
