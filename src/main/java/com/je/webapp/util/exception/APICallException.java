/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 6. 28. 오후 11:43
 * Description  : 
 ******************************************************/
package com.je.webapp.util.exception;

public class APICallException extends Exception {

    public APICallException() {
        super();
    }

    public APICallException(String errorMsg) {
        super(errorMsg);
    }
}
