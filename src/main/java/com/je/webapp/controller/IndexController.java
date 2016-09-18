package com.je.webapp.controller;

import com.je.webapp.form.ResultForm;
import com.je.webapp.service.IndexService;
import com.je.webapp.util.SystemResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(value = "/index")
public class IndexController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String serverEnv = System.getProperty("spring.profiles.active");

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/index")
    public String loadIndexPage(HttpServletRequest request, Model model) {
        SystemResource systemResource = SystemResource.getInstance();
        String msg = systemResource.getString("result.msg.success");

        model.addAttribute("msg", msg);
        model.addAttribute("data", "Nicekkong's World!!");

        logger.info(" >>>> Server Env : " + serverEnv);
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
}