package com.je.webapp.controller;

import com.je.webapp.Domain.MemberInfo;
import com.je.webapp.Domain.User;
import com.je.webapp.form.ResultForm;
import com.je.webapp.repository.jt.GameJtRepository;
import com.je.webapp.service.GameService;
import com.je.webapp.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value = "/index")
public class IndexController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private final static String serverEnv = System.getProperty("spring.profiles.active");
    //private final static SystemResource systemResource = SystemResource.getInstance();

    @Autowired
    private IndexService indexService;

    @Autowired
    private GameService gameService;

    @Autowired
    private GameJtRepository gameJtRepository;

    @Value("${result.msg.success}")
    String config_success;

    @RequestMapping(value="/game")
    public String startGame(HttpServletRequest request, Model model) {


        return "game";
    }


    @RequestMapping(value = "/game/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginGame(@RequestParam(value="name")String inputName, HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

        User userInfo = new User();

        try {
            Map<String, Object> memberInfo =  gameService.loginMember(inputName);


            userInfo.setName(inputName);
            userInfo.setCompany((String)memberInfo.get("group"));

            request.getSession().setAttribute("userInfo", userInfo);

            result.put("memberInfo", memberInfo);
            result.put("result", "success");
        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());
        }

        return result;
    }

    @RequestMapping(value = "/my_game")
    public String mgGame(HttpServletRequest request, Model model) {

        return "my_game";

    }


    @RequestMapping(value = "/getScore")
    @ResponseBody
    public List<MemberInfo> getScore(String name) {

        List<MemberInfo> scoreInfo = new ArrayList<>();

//        User userInfo = (User) request.getSession().getAttribute("userInfo");
//        String name = userInfo.getName();

        try {

            scoreInfo = gameService.getMemberInfo(name);

        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());

        }

        return scoreInfo;

    }


    @RequestMapping(value = "/saveScore")
    @ResponseBody
    public Map<String, String> saveScore(String name, String group, String game, int score) {

        Map<String, String> result = new HashMap<>();
        List<MemberInfo> scoreInfo = new ArrayList<>();

//        User userInfo = (User) request.getSession().getAttribute("userInfo");
//        String name = userInfo.getName();

        try {
            gameService.insertScore(name, group, game, score);
        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());

        }

        result.put("result", "success");

        return result;



    }


    @RequestMapping(value = "/admin")
    @ResponseBody
    public Map<String, Object> admin() {

        Map<String, Object> result = new HashMap<>();

        List<Map<String, String>> q1 = new ArrayList<>();
        List<Map<String, String>> q2 = new ArrayList<>();
        List<Map<String, String>> q3 = new ArrayList<>();
        List<Map<String, String>> q4 = new ArrayList<>();
        List<Map<String, String>> q5 = new ArrayList<>();

        try {
            q1 = gameJtRepository.q1();
            q2 = gameJtRepository.q2();
            q3 = gameJtRepository.q3();
            q4 = gameJtRepository.q4();
            q5 = gameJtRepository.q5();
        } catch (Exception e) {

            logger.error(e.getLocalizedMessage());

        }

        result.put("myteam", q1);
        result.put("teamCnt", q2);
        result.put("teamScore", q3);
        result.put("myTotScore", q4);
        result.put("mySumScore", q5);


        result.put("result", "success");

        return result;


    }





    @RequestMapping(value = "/index")
    public String loadIndexPage(HttpServletRequest request, Model model) {

        //String msg = systemResource.getString("result.msg.success");
        String msg = config_success;



        model.addAttribute("msg", msg);
        model.addAttribute("data", "Nicekkong's World!!");



        //logger.info(" >>>> Server Env : " + serverEnv);
        return "index";
    }

    @RequestMapping(value = "/ajax/addUser")
    @ResponseBody
    public ResultForm doAjax(@RequestBody String name) {

        ResultForm result = new ResultForm();
        boolean isSuccess = false;
        String resultMsg;
        int resultCode;

        try {
            indexService.addUser(name);
            isSuccess = true;
            resultMsg = "SUCCESS";
            resultCode = 0;
        } catch (Exception e){
            logger.error("INSERT ERROR");
            resultMsg = "ERROR";
            resultCode = -1;
        }

        result.setResult("isSuccess", isSuccess);
        result.setResultMsg(resultMsg);
        result.setResultCode(resultCode);

        return result;
    }

    /**
     * UI 샘플 시작 예제 화면
     * @param model
     * @return
     */
    @RequestMapping(value ="starter")
    public String starter(Model model) {

        //String msg = systemResource.getString("result.msg.success");

        model.addAttribute("msg", config_success);
        model.addAttribute("data", "Nicekkong's World!!");

        return "starter";
    }


}