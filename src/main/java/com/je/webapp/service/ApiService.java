package com.je.webapp.service;


import com.je.webapp.repository.jt.SampleJtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SampleJtRepository sampleJtRepository;

    public String searchUser(String id) throws Exception {

        // make some logic for service....
        String result;
        result = sampleJtRepository.searchUser(id);
        return  result;
    }
}
