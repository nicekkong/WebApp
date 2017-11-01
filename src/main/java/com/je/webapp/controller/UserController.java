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
import com.je.webapp.util.ApiUtil;
import com.je.webapp.util.RestTemplateApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
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
    public String login(Model model) {

        logger.debug(" ===> /user/login called");

        ApiUtil apiUtil = new ApiUtil();

        Map<String, String> headerMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();

//        Map<String, String> headerMap = new HashMap<>();
//        Map<String, String> paramMap = new HashMap<>();

        headerMap.put("appkey", "7cf3fe55-c382-314a-b2de-001c4aa854cd");
        headerMap.put("accept", "application/json");

        paramMap.put("version", "1");
        paramMap.put("lat", "37.504916");
        paramMap.put("lon", "126.999363");

        Map<String, Object> result = new HashMap<>();
//        Map<String, Object> result ="";
//        String result ="";

        RestTemplateApiUtil restTemplateApiUtil = new RestTemplateApiUtil();
        restTemplateApiUtil.RestTemplateApiUtilBuilder();

//        String apiUrl = "https://reward.t-ad.co.kr/reward/api/totalRewardPoint";
        String apiUrl = "http://apis.skplanetx.com/weather/dust";



        try {

//            result = apiUtil.callApi("http://apis.skplanetx.com/weather/dust",Protocol.HTTP, ApiUtil.RequestMethod.GET, headerMap, paramMap);

            result = restTemplateApiUtil.callApiByType(HttpMethod.GET, apiUrl, headerMap, paramMap, HashMap.class);


//            result = restTemplateApiUtil.callApi(HttpMethod.POST, apiUrl, headerMap, paramMap);


//            result = restTemplateApiUtil.callApiByType(HttpMethod.POST, apiUrl, headerMap, paramMap, HashMap.class);


//            HttpClientUtil httpClientUtil = new HttpClientUtil();
//            result = httpClientUtil.doPost("https://reward.t-ad.co.kr/reward/api/totalRewardPoint", headerMap, paramMap);


        } catch (Exception e) {
            logger.error("ERROR~!!!", e.getLocalizedMessage().toString());
        }


        model.addAttribute("result", result);

        model.addAttribute("data", "NICEKKONG");



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
