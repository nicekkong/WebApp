package com.je.webapp.util;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ApiClientUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    SystemResource systemResource = SystemResource.getInstance();

    private final static int TIME_OUT_MILLI_SEC = 5000;

    /**
     * Http 방식의 API 호출 연동을 위한 메서드
     * @param url  API URL
     * @param headerMap header 내 파라메터 값
     * @param paramMap body 내 파라메터 값
     * @param requestMethod Request Method (POST, GET)
     * @param protocolType 프로토콜 형식(HTTP, HTTPS)
     * @return
     * @throws Exception
     */
    public String callHttpApi(String url, HashMap<String, String> headerMap,
                          HashMap<String, String> paramMap, String requestMethod, String protocolType) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        JSONObject paramObj = new JSONObject(paramMap);     // 파라메터 셋팅
        String jsonParam = paramObj.toString();

        String responseJsonEncoded = null;
        URL apiUrl = new URL(url);      // API 호출 URL 셋팅

        DataOutputStream output = null;

        // now read the input stream until it is closed, line by line adding to the response
        InputStream inputstream = null;
        InputStreamReader inputstreamreader = null;
        BufferedReader bufferedreader = null;

        HttpURLConnection connection = null;

        try{
            connection = (HttpURLConnection)apiUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod);     // Request Method
            connection.setConnectTimeout(TIME_OUT_MILLI_SEC);
            connection.setReadTimeout(TIME_OUT_MILLI_SEC);

            //헤더가 있을 경우 세팅한다.
            if(headerMap != null && headerMap.size() > 0) {
                Collection<String> headerKeys = headerMap.keySet();
                Iterator<String> iterator = headerKeys.iterator();

                while(iterator.hasNext()) {
                    String headerKey = String.valueOf(iterator.next());
                    connection.setRequestProperty(headerKey, headerMap.get(headerKey));
                }
            }
            connection.setRequestProperty("Content-length", String.valueOf(jsonParam.length()));

            output = new DataOutputStream( connection.getOutputStream() );
            output.write(jsonParam.getBytes("UTF-8"));

            if (connection.getResponseCode() >= 400) {
                inputstream = connection.getErrorStream();
            } else {
                inputstream = connection.getInputStream();
            }
            inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
            bufferedreader = new BufferedReader(inputstreamreader);
            StringBuffer response = new StringBuffer();
            String line = null;
            while ((line = bufferedreader.readLine()) != null) {
                response.append(line);
            }

            if(logger.isDebugEnabled()) logger.debug("responseStatus : " + connection.getResponseCode());
            if(logger.isDebugEnabled()) logger.debug("RESULT : " + response.toString());

            responseJsonEncoded = response.toString();
            int responseStatus = connection.getResponseCode();
            resultMap.put("HTTP_STATUS", responseStatus);
            resultMap.put("RESULT", responseJsonEncoded);
            connection.disconnect();

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw e;

        } finally {
            if (output != null) output.close();
            if (inputstream != null) inputstream.close();
            if (connection != null) connection.disconnect();

            if (inputstreamreader != null) inputstreamreader.close();
            if (bufferedreader != null) bufferedreader.close();
        }

        return resultMap.get("RESULT").toString();
    }


    /**
     * Http 방식의 API 호출 연동을 위한 메서드
     * @param url  API URL
     * @param headerMap header 내 파라메터 값
     * @param paramMap body 내 파라메터 값
     * @param requestMethod Request Method (POST, GET)
     * @param protocolType 프로토콜 형식(HTTP, HTTPS)
     * @return
     * @throws Exception
     */
    public String callHttpsApi(String url, HashMap<String, String> headerMap,
                              HashMap<String, String> paramMap, String requestMethod, String protocolType) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();
        JSONObject paramObj = new JSONObject(paramMap);     // 파라메터 셋팅
        String jsonParam = paramObj.toString();

        String responseJsonEncoded = null;
        URL apiUrl = new URL(url);      // API 호출 URL 셋팅

        DataOutputStream output = null;

        // now read the input stream until it is closed, line by line adding to the response
        InputStream inputstream = null;
        InputStreamReader inputstreamreader = null;
        BufferedReader bufferedreader = null;

        HttpsURLConnection connection = null;

        try{
            connection = (HttpsURLConnection)apiUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod);     // Request Method
            connection.setConnectTimeout(TIME_OUT_MILLI_SEC);
            connection.setReadTimeout(TIME_OUT_MILLI_SEC);

            //헤더가 있을 경우 세팅한다.
            if(headerMap != null && headerMap.size() > 0) {
                Collection<String> headerKeys = headerMap.keySet();
                Iterator<String> iterator = headerKeys.iterator();

                while(iterator.hasNext()) {
                    String headerKey = String.valueOf(iterator.next());
                    connection.setRequestProperty(headerKey, headerMap.get(headerKey));
                }
            }
            connection.setRequestProperty("Content-length", String.valueOf(jsonParam.length()));

            output = new DataOutputStream( connection.getOutputStream() );
            output.write(jsonParam.getBytes("UTF-8"));

            if (connection.getResponseCode() >= 400) {
                inputstream = connection.getErrorStream();
            } else {
                inputstream = connection.getInputStream();
            }
            inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
            bufferedreader = new BufferedReader(inputstreamreader);
            StringBuffer response = new StringBuffer();
            String line = null;
            while ((line = bufferedreader.readLine()) != null) {
                response.append(line);
            }

            if(logger.isDebugEnabled()) logger.debug("responseStatus : " + connection.getResponseCode());
            if(logger.isDebugEnabled()) logger.debug("RESULT : " + response.toString());

            responseJsonEncoded = response.toString();
            int responseStatus = connection.getResponseCode();
            resultMap.put("HTTP_STATUS", responseStatus);
            resultMap.put("RESULT", responseJsonEncoded);
            connection.disconnect();

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw e;

        } finally {
            if (output != null) output.close();
            if (inputstream != null) inputstream.close();
            if (connection != null) connection.disconnect();

            if (inputstreamreader != null) inputstreamreader.close();
            if (bufferedreader != null) bufferedreader.close();
        }

        return resultMap.get("RESULT").toString();
    }

    /**
     * 프로토콜에 따른 HTTP(S) Connection 반환 메서드
     * @param url
     * @return
     * @throws Exception
     */
//    private static HttpURLConnection setConnection(String url) throws Exception {
//
//        if(url.indexOf("https://") > 0 ) {
//            return (HttpsURLConnection) new URL(url).openConnection();;
//        } else {
//            return (HttpURLConnection) new URL(url).openConnection();;
//        }
//    }

}
