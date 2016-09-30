/*
 * Copyright (c) 2016.
 * nicekkong JE Foundation
 */

package com.je.webapp.service;

import com.je.webapp.Domain.User;
import com.je.webapp.WebAppApplication;
import com.je.webapp.util.SystemResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by 1001857 on 16. 6. 14..
 */
@RunWith(SpringJUnit4ClassRunner.class) // Spring Junit 모듈
@ActiveProfiles("local")    // profile 정보 셋팅(spring.profiles.active)
@SpringApplicationConfiguration(classes = {WebAppApplication.class})    // configuration 설정
@WebAppConfiguration
public class SnsEventServiceTest {

   private final static Logger logger = LoggerFactory.getLogger(SnsEventServiceTest.class);

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

       // System.setProperty("spring.profiles.active", "local");  // 개발환경
    }


    @Test
    public void testSelectAllUser() throws Exception {

        logger.info("ASDFASDFSADFSADFSADFASDFASDFAS");


        List<User> userList;

        userList = userService.getAllUser();

        logger.info("userList ===> " + userList);

    }

/*
    @Test
    public void testInsertAttend() throws Exception {
        SnsEventForm snsEventForm = new SnsEventForm();

        snsEventForm.setGameId("2048");
        snsEventForm.setMbrId("111111111");
        snsEventForm.setAttendDay("20160614");
        snsEventForm.setPoint(1);
        snsEventForm.setTrSts("SUCC_TEST");
        snsEventForm.setEventSeq(126);
        snsEventForm.setSnsCh("Cyworld");

        snsEventService.insertAttend(snsEventForm);
    }

    @Test
    public void testUpdateAttend() throws Exception {

        snsEventService.updateAttend("111111111", "20160614", "SUCC_CHANGE");
    }

    @Test
    public void testGivePoint() throws Exception {

        SnsEventForm snsEventForm = new SnsEventForm();

        snsEventForm.setGameId("2048");
        snsEventForm.setMbrId("111111111");
        snsEventForm.setAttendDay("20160616");
        snsEventForm.setPoint(100);
        snsEventForm.setTrSts("SUCC_TEST");
        snsEventForm.setEventSeq(126);
        snsEventForm.setSnsCh("Cyworld");

        snsEventService.givePoint(snsEventForm);
    }

    @Test
    public void testEnumeration() throws Exception {

        Vector<String> vector = new Vector<String>();

        vector.addElement("nicekkong1");
        vector.addElement("nicekkong2");
        vector.addElement("nicekkong3");
        vector.addElement("nicekkong4");

        Enumeration<String> e = vector.elements();

        while(e.hasMoreElements()){
            logger.info("=====>\t" + e.nextElement());
        }


    }
    */
}
