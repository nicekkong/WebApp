package com.je.webapp.validator;

import javax.servlet.http.HttpServletRequest;

import com.je.webapp.util.RequestUtil;
import com.je.webapp.validator.api.ApiClientAccessIP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IpValidator {

	private static final Logger logger = LoggerFactory.getLogger(IpValidator.class);

	private static final List<String> bikong_IP = new ArrayList<>(Arrays.asList("124.66.184.4", "127.0.0.1"));

	/**
	 * API 호출 가능한 서버인지 IP를 체크하는 메소드
	 *
	 * @param HttpServletRequest request  : 검증할 객체
	 * @return String errormsg : 에러메시지, null이면 에러 없는 경우임.
	 */
	public boolean validate(HttpServletRequest request) {

		boolean isAllowed = false;
		String serverType = System.getProperty("spring.profiles.active");

		// 로컬 환경과 개발 환경일때는 체크하지 않는다.
		if(serverType.equals("local") || serverType.equals("dev")) {
			isAllowed = true;
			return isAllowed;
		}

		String requestUri = RequestUtil.getRequestUri(request);
		String requestIp = RequestUtil.getRequestIp(request);

		//IP를 추출할 수 있을 경우만 허용여부를 확인한다.
		if(requestIp.length() > 0) {
			logger.debug("API call by [" + requestIp + "]");
			if(requestUri.indexOf("/api/add/chance") != -1 || requestUri.indexOf("/api/add/anyChance") != -1 || requestUri.indexOf("/api/add/chance/byService") != -1) { //게임기회 부여 API가 호출된 경우
				isAllowed = checkAllowIp("/api/add/", requestIp, ApiClientAccessIP.CLIENT_SERVER);
			}
		}
		return isAllowed;

	}


	/**
	 * API 별로 허용하는 Client IP인지 확인하는 체크하는 메서드
	 * @param clientIp
	 * @param allowedIpList
	 * @return
	 */
	private static boolean checkAllowIp(String api, String clientIp, List<String> allowedIpList) {

		boolean isAllowed = false;

        allowedIpList.addAll(bikong_IP);
        //logger.debug(">>>>>>>>>>" + allowedIpList.toString());
		for(String allowedIp : allowedIpList){

			if(clientIp.equals(allowedIp)){
				isAllowed = true;
				break;
			}
		}
		if(!isAllowed) {
        	logger.info(">>> Unauthorize API client call~!! [" + api + "] call by ["+ clientIp +"]");
		}

		return isAllowed;
	}

}