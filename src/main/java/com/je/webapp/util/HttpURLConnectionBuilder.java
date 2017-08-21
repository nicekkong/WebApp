/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 6. 25. 오후 4:15
 * Description  : 
 ******************************************************/
package com.je.webapp.util;

import com.je.webapp.util.enumType.Protocol;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionBuilder {

    private HttpURLConnection urlConnection;

    public HttpURLConnectionBuilder(URL url, Protocol protocol) {

        try {
            if (protocol == Protocol.HTTP) {
                setHttpConnection(url);
            } else if (protocol == Protocol.HTTPS) {
                setHttpsConnection(url);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private HttpURLConnection setHttpConnection(URL url) throws IOException {
        urlConnection = (HttpURLConnection)url.openConnection();
        return urlConnection;
    }

    private HttpsURLConnection setHttpsConnection(URL url) throws IOException {
        urlConnection = (HttpsURLConnection)url.openConnection();
        return (HttpsURLConnection) urlConnection;
    }

    public HttpURLConnection getConnection() {

        return urlConnection;
    }
}
