package com.je.webapp.service;

import com.je.webapp.repository.jt.SampleJtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SampleJtRepository sampleJtRepository;

    public void addUser(String name) throws Exception {

        try {
        	sampleJtRepository.addUser(name);
        }
        catch(Exception e) {
            logger.error("Error Insert member");
            throw e;
        }
    }
}