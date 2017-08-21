/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2016. 12. 2. AM 12:19
 * Description  : 
 ******************************************************/
package com.je.webapp.controller;

import com.je.webapp.Domain.User;
import com.je.webapp.form.ResultForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value ="/board")
public class BoardController {

    private static Logger logger = LoggerFactory.getLogger(BoardController.class);

    @RequestMapping(value="/freeBoard", method = RequestMethod.GET)
    public String moveToFreeBoard(HttpServletRequest request, Model model) {

        User userInfo = (User)request.getAttribute("userInfo");

        logger.debug("userInfo : " + userInfo.toString());

        return "freeBoard";
    }

    @RequestMapping(value="/getBoardList", method = RequestMethod.GET)
    @ResponseBody
    public ResultForm getBoardList() {

        return null;
    }


}
