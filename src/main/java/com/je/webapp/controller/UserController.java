/*****************************************************************************
 * PROJECT NAME   : JEWebApp
 * SUBSYSTEM NAME :
 * FILE NAME      : .java
 * DESCRIPTION    :
 * <p/>
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
 * 1.0           Kong,Byungil     2016. 9. 30.     init
 *****************************************************************************/
package com.je.webapp.controller;

import com.je.webapp.Domain.User;
import com.je.webapp.form.ResultForm;
import com.je.webapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //private final static SystemResource systemResource = SystemResource.getInstance();


    @Value("${result.code.success}")
    private String successCode;

    @RequestMapping(value = "/getUserListAll", method = RequestMethod.POST)
    @ResponseBody
    public ResultForm getUserListAll(@RequestParam(required = true)String type) {

        logger.debug("getUserListAll() : type => " + type);

        ResultForm result = new ResultForm();
        List<User> userList ;

        try {
            userList = userService.getAllUser();
            result.setResult("userList", userList);
            result.setTotalCnt(userList.size());
            result.setResultCode(Integer.valueOf(successCode));

        } catch (Exception e) {
            //result.setResultCode(Integer.valueOf(systemResource.getString("result.code.fail")));
            result.setResultMsg("조회 중 오류가 발생했습니다.");
        }

        return result;
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        logger.info(" ===> /user/login called");

        return "/login/login";
    }


    @RequestMapping(value = "/loginProc", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginProc(@ModelAttribute("user")User user, Model model, HttpServletRequest request) {
        logger.info("=======> loginProc() called");

        String userId = user.getUserId();
        String password = user.getPassword();
        User userInfo;

        Map<String, Object> result = new HashMap<>();

        logger.info(" >>>>>ID : " + userId);
        logger.info(" >>>>>Password : " + password);

        try{
            userInfo = userService.getLoginUser(userId, password);
            if(userInfo != null) {
                request.getSession().setAttribute("userInfo", userInfo);
                result.put("loginYn", "Y");
                logger.info("로그인 성공~~");
            } else {

                result.put("loginYn", "N");
                logger.info("로그인 실패~~");
            }
        } catch(Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return result;
    }


    @RequestMapping(value = "/logoutProc", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> logoutProc(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

        try {
            request.getSession().invalidate();
            result.put("result", "succ");
            logger.info("Logout Success~!!");
            logger.info("Logout Session : " + request.getSession().getAttribute("userInfo"));
        } catch (Exception e) {
            logger.error("Logout Error~!!");
            result.put("result", "fail");
        }
        return  result;

    }

    @RequestMapping(value ="/register", method = RequestMethod.GET)
    public String goRegister() {
        logger.info(" ======> goRegister()");

        return "/login/register";
    }

    @RequestMapping(value="/idDup/check", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkIdDup(@RequestParam(value="inputId")String inputId) {

        Map<String, Object> result = new HashMap<>();
        boolean isDup = false;

        try {
            isDup = userService.isDupId(inputId);
            result.put("isDup", isDup);
            logger.debug("ID Dup Check ===> ID: " + inputId + " isDup ===> " + isDup);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return result;
    }

    /**
     * 신규 회원 등록
     * @param user
     * @return
     */
    @RequestMapping(value = "/process/Register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> doRegister(User user) {
        Map<String, Object> result = new HashMap<>();
        boolean isSuccess = false;

        logger.info("user Info : " + user.toString());


        try {
            userService.registerUser(user);
            isSuccess = true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            isSuccess = false;
        }
        result.put("isRegister", isSuccess);
        return result;
    }
}
