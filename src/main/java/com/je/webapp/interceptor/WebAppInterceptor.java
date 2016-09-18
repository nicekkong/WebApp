package com.je.webapp.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class WebAppInterceptor extends HandlerInterceptorAdapter {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ( logger.isDebugEnabled() ) {
            logger.debug("request.getRequestURL() => [" + request.getRequestURL() + "]");
            logger.debug("request.getRequestURI() => [" + request.getRequestURI() + "]");
            try {
                Enumeration<String> headerNames = request.getHeaderNames();

                while (headerNames.hasMoreElements()) {
                    String key = headerNames.nextElement();
                    String val = request.getHeader(key);
                    logger.debug("Reqeust Header Key = >[" + String.format("%-30s", key) + "] | value = [" + String.format("%-100s", val) + "]");
                }

                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements()) {
                    String key = parameterNames.nextElement();
                    String val = request.getParameter(key);
                    logger.debug("Reqeust Parameter Key = >[" + String.format("%-27s", key) + "] | value = [" + String.format("%-100s", val) + "]");
                }
            } catch (Throwable t) {
                logger.warn("Can't Read Request Header List !!!" + t.getMessage(), t);
            }
        }
        return true;
        
    }
    
}
