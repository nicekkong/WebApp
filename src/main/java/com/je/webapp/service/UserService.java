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

import java.util.ArrayList;
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
}
