/******************************************************
 * Project Name : WebApp
 * File Name    : .java
 * Author       : nicekkong@gmail.com
 * Create Date  : 2017. 5. 14. 오후 8:26
 * Description  : 
 ******************************************************/
package com.je.webapp.util;


import com.je.webapp.WebAppApplication;
import com.je.webapp.util.enumType.Protocol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class) // Spring Junit 모듈
@ActiveProfiles("local")    // profile 정보 셋팅(spring.profiles.active)
@SpringApplicationConfiguration(classes = {WebAppApplication.class})    // configuration 설정
@WebAppConfiguration
public class TestUtilClass {

    private static final Logger logger = LoggerFactory.getLogger(TestUtilClass.class);



    @Test
    public void TestApiUtil () {
        logger.debug("TestApiUtil Start~!!!");

        ApiUtil apiUtil = new ApiUtil();

        Map<String, String> headerMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();

        headerMap.put("appkey", "7cf3fe55-c382-314a-b2de-001c4aa854cd");

        paramMap.put("version", "1");
        paramMap.put("lat", "37.504916");
        paramMap.put("lon", "126.999363");

        Map<String, Object> result = new HashMap<>();

        try {
            result = apiUtil.callApi("http://apis.skplanetx.com/weather/dust",
                    Protocol.HTTP, ApiUtil.RequestMethod.GET, headerMap, paramMap);
        } catch (Exception e) {
            logger.error("ERROR~!!!", e.getLocalizedMessage().toString());
        }

        logger.debug("result : " + result.toString());
        logger.debug(result.get("weather").toString());
//        logger.debug("r1 : " , r1.toString());
//        logger.debug("asdf : ", r1.get("dust").toString());

        logger.debug(((HashMap<String, Object>)result.get("weather")).get("dust").toString());


        HashMap<String, Object> r2 = (HashMap<String, Object>)result.get("weather");

        logger.debug("dust : ", r2.get("dust"));


    }


}
