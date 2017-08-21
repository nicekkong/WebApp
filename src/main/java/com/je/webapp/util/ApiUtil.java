/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 5. 14. 오후 1:28
 * Description  : 
 ******************************************************/
package com.je.webapp.util;

import com.je.webapp.util.enumType.Protocol;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ApiUtil {

    private final static Logger logger = LoggerFactory.getLogger(ApiUtil.class);

    public enum RequestMethod {
        GET, POST, PUT, DELETE
    }

    private final static int TIME_OUT_SEC = 5 * 1000;

    private HttpURLConnection urlConnection = null;

    public Map<String, Object> callApi(String url, Protocol protocol, RequestMethod requestMethod, Map<String, String> headerMap, Map<String, Object> paramsMap)
        throws Exception{

        Map resultMap = new HashMap<>();

        InputStream inputstream = null;
        InputStreamReader inputstreamreader = null;
        BufferedReader bufferedreader = null;

        DataOutputStream output = null;

        if((RequestMethod.GET == requestMethod) && (paramsMap.size() != 0)) {

            Collection<String> paramKeys = paramsMap.keySet();  // List, set 등과 같은 클래스의 상위 인터페이스
            Iterator<String> iterator = paramKeys.iterator();   // Collection 객체를 구성하는 각각의 객체 <E>
            boolean isFirstParam = true;
            String key ;
            while(iterator.hasNext()) {
                key = iterator.next();
                if(isFirstParam) {
                    url += "?" + key + "=" + paramsMap.get(key);
                    isFirstParam = false;
                } else {
                    url += "&" + key + "=" + paramsMap.get(key);
                }
            }
        }

        try {
            URL apiUrl = new URL(url);

            setUrlConnection(protocol, apiUrl);

            urlConnection.setDoInput(true);
            if(requestMethod == RequestMethod.POST) {
                urlConnection.setDoOutput(true);    // Send Request Body
            }
            urlConnection.setRequestMethod(requestMethod.name());
            urlConnection.setConnectTimeout(TIME_OUT_SEC);
            urlConnection.setReadTimeout(TIME_OUT_SEC);

            // 헤더 셋팅을 한다
            if(headerMap != null && headerMap.size() > 0) {
                Set<String> headerKeys = headerMap.keySet();
                Iterator<String> iterator = headerKeys.iterator();

                String headerKey;
                while(iterator.hasNext()) {
                    headerKey = iterator.next();
                    urlConnection.setRequestProperty(headerKey, (String)headerMap.get(headerKey));
                }
            }

            // 파라메터 셋팅

//          urlConnection.setRequestProperty("Accept-Charset", "UTF-8");

            if(RequestMethod.POST == requestMethod) {
                JSONObject paramObj = new JSONObject(paramsMap);
                String jsonParams = paramObj.toString();
                urlConnection.setRequestProperty("Content-length", String.valueOf(jsonParams.length()));
                output = new DataOutputStream(urlConnection.getOutputStream());
                output.write(jsonParams.getBytes("UTF-8"));
            }


            int responseStatus = urlConnection.getResponseCode();
            if(responseStatus >= 400 ) {
                inputstream = urlConnection.getErrorStream();
            } else {
                inputstream = urlConnection.getInputStream();
            }
            inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
            bufferedreader = new BufferedReader(inputstreamreader);
            StringBuffer response = new StringBuffer();
            String line ;
            while((line = bufferedreader.readLine()) != null) {
                response.append(line);
            }


            String responseJsonEncoded = response.toString();

            logger.debug("response Status : " + responseStatus);
            logger.debug("result : " + responseJsonEncoded);

            ObjectMapper objectMapper = new ObjectMapper();
            resultMap = objectMapper.readValue(responseJsonEncoded, new TypeReference<HashMap<String, Object>>(){});


        } catch (MalformedURLException e) {
            logger.error(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        } finally {
            if (output != null) output.close();
            if (inputstream != null) inputstream.close();
            if (urlConnection != null) urlConnection.disconnect();

            if (inputstreamreader != null) inputstreamreader.close();
            if (bufferedreader != null) bufferedreader.close();
        }
        return resultMap;

    }



    private void setUrlConnection(Protocol protocol, URL url) throws IOException {

        if(protocol == Protocol.HTTP) {

            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            urlConnection = httpConn;
        } else {
            HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
            urlConnection = httpsConn;
        }
    }

}
