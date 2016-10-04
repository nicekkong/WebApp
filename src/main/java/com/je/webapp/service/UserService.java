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
package com.je.webapp.service;

import com.je.webapp.Domain.User;
import com.je.webapp.repository.jt.UserJtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserJtRepository userJtRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUser() throws Exception{

        List<User> userList;
        userList = userJtRepository.seletAllUser();
        return userList;
    }

    /**
     * ID, Password 로그인 결과를 조회한다
     * 해당 데이터로 1건만 존재해야 정상 데이터로 처리한다.
     * @param userId 사용자 ID
     * @param password 사용자 Password
     * @return
     * @throws Exception
     */
    public User getLoginUser(String userId, String password) throws Exception {

        List<User> userInfo;
        userInfo = userJtRepository.loginUser(userId, password);

        // 조회한 결과에 대하여 1건만 조회가 되면 정상 사용자
        if(userInfo != null && userInfo.size() == 1) {
            return userInfo.get(0);
        } else {
            return null;
        }
    }
}
