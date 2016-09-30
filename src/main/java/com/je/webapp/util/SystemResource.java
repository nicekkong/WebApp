package com.je.webapp.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SystemResource {
	
	protected static Log logger = LogFactory.getLog(SystemResource.class);
	
    private final static String propertiesFile = System.getProperty("spring.profiles.active") + ".config";
    //private final static String propertiesFile = "local" + ".config";

    private static ResourceBundle rscBundle = ResourceBundle.getBundle(propertiesFile); //ResourceBundle 객체
    private static SystemResource systemResource = null;                                //SystemResource 인스턴스

    /**
     * SystemResource의 instance를 리턴한다.
     * @return SystemResource instance
     */
    public static SystemResource getInstance() {
    	
    	//SystemResource 객체가 null일 경우만 생성하는 싱글톤 패턴.
    	if(systemResource == null) {
    		systemResource = new SystemResource();
    	}
    	
        return systemResource;
        
    }

    /**
     * 리소스번들에서 해당 키에 해당하는 스트링을 리턴한다.
     * @param key 리소스번들 키
     * @return 리소스번들 value
     */
    public String getString(String key) {
    	
        String valueString = null;

        try {
        	valueString = rscBundle.getString(key);
        } catch (MissingResourceException e) {
        	logger.error("SystemResource:getString(\"" + key + "\") : MissingResourceException \n", e);
        }

        return valueString;
        
    }

}