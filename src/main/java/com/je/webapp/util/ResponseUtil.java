package com.je.webapp.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResponseUtil {
	
	protected static Log logger = LogFactory.getLog(ResponseUtil.class);
	
	/**
	 * HttpServletResponse 객체와 출력할 문자열, contentType을 전달받아 client로 결과 response를 보내는 메소드.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String output                : 사용자에 출력할 문자열
	 * @param String contentType           : Content Type
	 * @return void 
	 */
	public static void print(HttpServletResponse response, String output, String contentType) {
		
		PrintWriter printWriter = null;
		
		try {
			response.setContentType(contentType + ";charset=UTF-8");
			
			printWriter = response.getWriter();
			printWriter.print(output);
			
		} catch (Exception e) {
			logger.error(e);
		} finally {
			printWriter.close();
		}
		
	}
	
	/**
	 * HttpServletResponse 객체와 출력할 메시지, 에러코드를 전달받아 client로 결과 Error를 보내는 메소드.
	 * 
	 * @param HttpServletResponse response : HttpServletResponse 객체
	 * @param String output                : 사용자에 출력할 메시지
	 * @param String statusCode            : 에러코드
	 * @return void 
	 */
	public static void printError(HttpServletResponse response, String msg, int errorCode) {
				
		try {
			response.sendError(errorCode, msg);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
}