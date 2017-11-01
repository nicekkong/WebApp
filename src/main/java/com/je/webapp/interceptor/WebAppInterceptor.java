package com.je.webapp.interceptor;

import com.je.webapp.Domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebAppInterceptor extends HandlerInterceptorAdapter {
    
    static final private Logger logger = LoggerFactory.getLogger(WebAppInterceptor.class);
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                                                                                        throws Exception {

        logger.debug("request.getRequestURL() => [" + request.getRequestURL() + "]");

        boolean isAjaxCall = false;

        if (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").toLowerCase().equals("xmlhttprequest")) {
            isAjaxCall = true;
        }

//        try {
//            // 로그인 상태를 확인한다.
//            if (!isLogin(request)) {
//                displayLoginPage(response, isAjaxCall); // Ajax 호출 여부에 따라 처리한다
//                return false;
//            }
//        } catch (Exception e) {
//            logger.error("preHadler Error~!!", e);
//            //response.sendRedirect("/user/login");
//            return false;
//        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // super.postHandle(request, response, handler, modelAndView);
    /*
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
        */
    }

    /**
     * ajax 요청이면 json응답, 일반 요청이면 로그인 페이지로 redirect 처리하는 메소드.
     *
     * @param
     * @param
     * @return void
     */
    private void displayLoginPage(HttpServletResponse response, boolean isAjaxCall) throws IOException {

        if (isAjaxCall) {
            String json = "{\"loginyn\":\"N\", \"authorityyn\":\"N\"}";
            logger.debug("displayLoginPage json=" + json);
        } else {
            response.sendRedirect("/user/login");
        }

    }


    private boolean isLogin(HttpServletRequest request) throws Exception {

        boolean isLogin = false;

        User userInfo = (User)request.getSession().getAttribute("userInfo");

        logger.info("[Session]UserInfo : " + (userInfo == null ? "NULL": userInfo.toString()));


        if(userInfo != null && userInfo.getUserId().length() > 0) {
            request.setAttribute("userInfo", userInfo);
            isLogin = true;
        }
        return isLogin;
    }
}
