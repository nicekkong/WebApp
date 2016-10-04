package com.je.webapp.interceptor;

import com.je.webapp.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebAppInterceptor extends HandlerInterceptorAdapter {
    
    static final private Logger logger = LoggerFactory.getLogger(WebAppInterceptor.class);
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ( logger.isDebugEnabled() ) {
            logger.debug("request.getRequestURL() => [" + request.getRequestURL() + "]");
            //logger.debug("request.getRequestURI() => [" + request.getRequestURI() + "]");
            /*try {
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
            }*/
        }

        HttpSession session = request.getSession();
        User userInfo = (User)session.getAttribute("userInfo");

        if(userInfo != null) {
            logger.info("[Session]UserInfo : " + userInfo.toString());
            session.setAttribute("userInfo", userInfo); // TODO: 해당 로직이 필요한지는 추가 확인 필요
            return true;
        } else {
            response.sendRedirect("/user/login");   // 비로그인 사용자는 로그인 화면으로 넘겨 버린다.
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        logger.info("======>postHandler() called~!!");

        HttpSession session = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();

        Object userInfo = modelMap.get("userInfo");
        logger.info("===>userInfo : " + userInfo.toString());

        // 로그인 처리후, 진입을 하는 것이라면 세션에 userInfo를 셋팅한다
        if(userInfo != null) {
            session.setAttribute("userInfo", userInfo);
            response.sendRedirect("/index");
        }
    }
}
