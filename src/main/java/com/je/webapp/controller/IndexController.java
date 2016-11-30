package com.je.webapp.controller;

import com.je.webapp.form.ResultForm;
import com.je.webapp.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(value = "/index")
public class IndexController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String serverEnv = System.getProperty("spring.profiles.active");
    //private final static SystemResource systemResource = SystemResource.getInstance();

    @Autowired
    private IndexService indexService;

    @Value("${result.msg.success}")
    String config_success;


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

    @RequestMapping(value="/freeBoard", method = RequestMethod.GET)
    public String moveToFreeBoard(HttpServletRequest request, Model model) {
        String msg = config_success;

        model.addAttribute("msg", msg);
        model.addAttribute("data", "Nicekkong's World!!");

        //logger.info(" >>>> Server Env : " + serverEnv);
        return "freeBoard";


    }
}