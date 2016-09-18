package com.je.webapp.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestUtil {
	
	protected static Log logger = LogFactory.getLog(RequestUtil.class);
	
	/**
	 * HttpServletRequest 객체로부터 원하는 파라미터를 가져오는 메소드.
	 * 파라미터가 null일 경우 빈 문자열을 리턴한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @param String paramName           : 파라미터명
	 * @param String defaultStr          : 파라미터가 null이거나 공백일 경우 디폴트값
	 * @return String paramValue         : 파라미터값
	 */
	public static String getParameter(HttpServletRequest request, String paramName, String defaultStr) {
		
		String paramValue = changeNullToEmpty(request.getParameter(paramName)).trim();
		
		if(paramValue.length() == 0) {
			paramValue = defaultStr;
		}
		
		return paramValue;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 원하는 int형태의 파라미터를 가져오는 메소드.
	 * 파라미터가 null이거나 숫자가 아닐 경우 0을 리턴한다.
	 * 
	 * @param request       : HttpServletRequest 객체
	 * @param paramName     : 파라미터명
	 * @return int intValue : int로 변형한 파라미터값
	 */
	public static int getIntParameter(HttpServletRequest request, String paramName) {
		
		String paramValue = changeNullToEmpty((String)request.getParameter(paramName)).trim();
		int intValue = 0;
		
		if(paramValue.length() > 0) {
			try {
				intValue = Integer.parseInt(paramValue);
			} catch(NumberFormatException nfe) {}
		}
		
		return intValue;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 원하는 long 형태의 파라미터를 가져오는 메소드.
	 * 파라미터가 null이거나 숫자가 아닐 경우 0L을 리턴한다.
	 * 
	 * @param request         : HttpServletRequest 객체
	 * @param paramName       : 파라미터명
	 * @return long longValue : long으로 변형한 파라미터값
	 */
	public static long getLongParameter(HttpServletRequest request, String paramName) {
		
		String paramValue = changeNullToEmpty(request.getParameter(paramName)).trim();
		long longValue = 0L;
		
		if(paramValue.length() > 0) {
			try {
				longValue = Long.parseLong(paramValue);
			} catch(NumberFormatException nfe) {}
		}
		
		return longValue;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 Request URI를 가져오는 메소드.
	 * Request URI가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Request URI
	 */
	public static String getRequestUri(HttpServletRequest request) {
		
		return changeNullToEmpty(request.getRequestURI()).trim();
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 Request IP를 가져오는 메소드.
	 * Request IP가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Request URI
	 */
	public static String getRequestIp(HttpServletRequest request) {
		
		return changeNullToEmpty(request.getRemoteAddr());
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 Referer를 가져오는 메소드.
	 * null일 경우 "-" 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Referer
	 */
	public static String getReferer(HttpServletRequest request) {
		
		String referer = changeNullToEmpty(request.getHeader("referer"));
		
		if(referer.equals("")) {
			referer = "-";
		}
		
		return referer;
		
	}
	
	/**
	 * HttpServletRequest 객체로부터 User-Agent를 가져오는 메소드.
	 * null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Referer
	 */
	public static String getUserAgent(HttpServletRequest request) {
		
		String useragent = changeNullToEmpty(request.getHeader("User-Agent"));
		
		if(useragent.equals("")) {
			useragent = "-";
		}
		
		return useragent;
		
	}
 
	/**
	 * HttpServletRequest 객체로부터 요청 host정보를 가져오는 메소드.
	 * host정보가 null일 경우 빈 문자열을 반환한다.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return String                    : Referer
	 */
	public static String getHost(HttpServletRequest request) {
		
		return changeNullToEmpty(request.getHeader("Host"));
		
	}
	
	/**
	 * 문자열을 넘겨받아 null일 경우 빈 문자열을 반환하는 메소드.
	 * @param String src : null 체크할 문자열
	 * @return String    : null 체크한 문자열
	 */
    private static String changeNullToEmpty(String src) {
    	
    	if(src == null) {
    		return "";
    	} else {
    		return src;
    	}
    	
    }
    
	/**
	 * Http Header 정보 설정 메소드
	 * 
	 * @return void
	 */
	public static void setHeader(HashMap<String, String> headerMap, HashMap<String, String> paramMap, HttpServletRequest request) {
		
		Cookie [] cookies = request.getCookies();
		
		for(int i=0; i<cookies.length; i++) {
			headerMap.put(cookies[i].getName(), cookies[i].getValue());
		}
		
	}
	
	/**
     * request parameter를 디버그로 출력하는 용도로 작성된 메소드
     * @param {@link HttpServletRequest} - request
     * @return {@link String}g - 모든 파라미터 정보
     */
    public static String printParamters(HttpServletRequest request) {
    	
    	Enumeration<String> e = request.getParameterNames();
    	
    	String key = null;
    	String[] val = null;
    	
    	StringBuffer sb = new StringBuffer("\n");
    	
    	int[] maxLen = getMaxLengthParameterKey(request);
    	
    	while(e.hasMoreElements()) {
    		key = e.nextElement();
    		val = request.getParameterValues(key);
    		
    		for ( int i = 0; i < val.length; i++ ) {
    			sb.append("Request Parameter ==> Key : [" + StringUtil.lPad(key, maxLen[0]) + "] | value : [" + StringUtil.rPad(val[i], maxLen[1]) + "]\n");
    		}
    	}
    	
    	return sb.toString();
    	
    }
    
	/**
	 * request parameter중 가장 길이가 긴 Key, Value의 길이를 int 배열(key, value순)로 반환한다.
	 * @param {@link HttpServletRequest} - request
	 * @return {@link Integer} Array - int[0] : key , int[1] : value
	 */
	public static int[] getMaxLengthParameterKey(HttpServletRequest request) {
		
		Enumeration<String> e = request.getParameterNames();
		
		String key = null;
    	String[] val = null;
    	
    	int maxKey = 0;
    	int maxVal = 0;
    	
    	int[] result = new int[2];
    	
    	while(e.hasMoreElements()) {
    		key = e.nextElement();
    		val = request.getParameterValues(key);
    		
    		maxKey = maxKey < key.length() ? key.length() : maxKey;
    		
    		for ( int i = 0; i < val.length; i++ ) {
    			maxVal = maxVal < val[i].length() ? val[i].length() : maxVal;
    		}
    	}
    	
    	result[0] = maxKey;
    	result[1] = maxVal;
    	
    	return result;
    	
	}
	
}