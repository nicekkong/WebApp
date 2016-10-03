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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

}
