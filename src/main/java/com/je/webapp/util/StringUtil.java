package com.je.webapp.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {

    protected static Log logger = LogFactory.getLog(StringUtil.class);

    /**
     * 문자열을 넘겨받아 null이거나 공백 문자일 경우 true를 반환하는 메소드.
     *
     * @param String
     *            src : 체크할 문자열
     * @return boolean : null이거나 공백 문자인지 여부
     */
    public static boolean isEmptyOrWhitespace(String src) {

        if (src == null || src.trim().length() == 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 문자열을 넘겨받아 null일 경우 빈 문자열을 반환하는 메소드.
     *
     * @param String
     *            src : null 체크할 문자열
     * @return String : 변환한 문자열
     */
    public static String changeNullToEmpty(String src) {

        if (src == null) {
            return "";
        } else {
            return src;
        }
    }

    /**
     * 문자열을 넘겨받아 null일 경우 빈 문자열/ null이 아닌경우 trim 후 반환하는 메소드.
     *
     * @param String
     *            src : null 체크할 문자열
     * @return String : 변환한 문자열
     */
    public static String ifNullTrim(String src) {
        if (src == null) {
            return "";
        } else {
            return src.trim();
        }
    }

    public static String phoneNumberFormat(String src) {

        if (src != null) {
            return src.replaceFirst("(0\\d{1,2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
        } else {
            return "-";
        }

    }

    /**
     * 입력문자열의 특정 문자열을 다른 문자열로 바꾸어 반환한다.
     *
     * @param String
     *            src : 수정할 문자열
     * @param String
     *            oldStr : 찾을 문자열
     * @param String
     *            newStr : 바꿀 문자열
     * @return String : 변환한 문자열
     */
    public static String replaceAll(String src, String oldStr, String newStr) {

        if (src == null || src.length() == 0) {
            return "";
        }

        if (oldStr == null || oldStr.length() == 0) {
            return src;
        }

        int idx = src.indexOf(oldStr);
        StringBuilder result = new StringBuilder();

        if (idx == -1) {
            return src;
        }

        result.append(src.substring(0, idx) + newStr);

        if (idx + oldStr.length() < src.length()) {
            result.append(replaceAll(src.substring(idx + oldStr.length(), src.length()), oldStr, newStr));
        }

        return result.toString();

    }

    /**
     * 문자열과 구분자를 넘겨받아 구분자로 분리한 문자배열을 반환하는 메소드.
     *
     * @param String
     *            src : 배열로 분리할 문자열
     * @param String
     *            delim : 구분자
     * @return String[] result : 구분자로 분리한 배열
     */
    public static String[] split(String src, String delim) {

        if (src == null || src.length() == 0) {
            return null;
        }

        String[] result = null;
        String sourceStr = src;
        int idx = 0;

        ArrayList<String> list = new ArrayList<String>();

        while (true) {
            idx = sourceStr.indexOf(delim);

            if (idx == -1) {
                list.add(sourceStr);
                break;
            } else {
                list.add(sourceStr.substring(0, idx));
                sourceStr = sourceStr.substring(idx + delim.length());
            }
        }

        result = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;

    }

    /**
     * 일정 Byte 이상의 문자열 이후를 잘라내고 주어진 문자열을 추가한다. 예)제목이 길 경우 뒷부분을 잘라내고 "..."을 붙인다.
     *
     * @param1 String str : 수정을 원하는 문자열
     * @param2 int size : Byte 길이
     * @param3 String append : 추가하고자 하는 문자열
     * @return String retStr : 변환한 문자열
     */
    public static String cutString(String str, int size, String append) {

        String retStr = "";
        int strSize = 0;
        char[] charArray = str.toCharArray();
        byte[] byteArray = str.getBytes();

        // Byte 길이를 먼저 검사하여 입력된 길이보다 긴 경우만 처리
        if (byteArray.length > size) {
            for (int i = 0; i < str.length(); i++) {
                // 2 Byte가 하나의 char인 경우
                if (charArray[i] > '\u00FF') {
                    strSize += 2;
                } else {
                    strSize++;
                }

                if (strSize > size) {
                    break;
                } else {
                    retStr += charArray[i];
                }
            }
            // append를 뒷 부분에 추가
            retStr += append;

            return retStr;
        }

        return str;

    }

    /**
     * 개행문자를 <br>
     * 로, " "을 "&nbsp;"로 치환한다.
     *
     * @param String
     *            src : 변환할 문자열
     * @return String result : 변환한 문자열
     */
    public static String changeEnter(String src) {

        if (src == null) {
            return "";
        }

        String result = replaceAll(src, "\n", "<br>");
        result = replaceAll(result, "  ", "&nbsp;&nbsp;");

        return result;

    }

    /**
     * XSS 공격을 차단한다.
     *
     * @param String
     *            src : 변환할 문자열
     * @return String : 변환한 문자열
     */
    public static String chkXss(String src) {

        if (src == null || src.equals("")) {
            return "";
        }

        StringBuffer buffer = new StringBuffer();

        String[] otoken = split(src, "<");
        String[] ttoken = split(src, "<");

        int cnt = 0;

        for (int i = 0; i < otoken.length; i++) {
            cnt++;
            String oStr = otoken[i];
            String tStr = ttoken[i].toLowerCase();

            boolean isXss = false;

            if (tStr.length() > 5 && tStr.substring(0, 6).equals("script"))
                isXss = true;
            else if (tStr.length() > 5 && tStr.substring(0, 6).equals("object"))
                isXss = true;
            else if (tStr.length() > 0 && tStr.substring(0, 1).equals("a"))
                isXss = true;
            else if (tStr.length() > 2 && tStr.substring(0, 3).equals("xmp"))
                isXss = true;
            else if (tStr.length() > 4 && tStr.substring(0, 5).equals("param"))
                isXss = true;

            if (cnt == 1 && src.charAt(0) != '<')
                ;
            else if (!isXss && cnt == 1)
                buffer.append("<");
            else if (isXss)
                buffer.append("&lt;");
            else if (cnt > 1)
                buffer.append("<");

            buffer.append(oStr);
        }

        return buffer.toString();

    }

    /**
     * 특수문자가 있을 경우 javascript에서 json 파일의 특수문자를 인식하도록 치환한다. (\' : \\\'), (\" : \\\"), (\\ : \\\\), (\n : \\n), (\r\n :
     * \\n)
     *
     * @param String
     *            pm_sSrc : 변환할 문자열
     * @return String : 변환한 문자열
     */
    public static String changeJson(String pm_sSrc) {
        if (pm_sSrc == null)
            return "";

        StringBuffer lm_sBuffer = new StringBuffer();
        char[] charArray = pm_sSrc.toCharArray();
        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] == '\'') {
                lm_sBuffer.append("\\\'");
            } else if (charArray[i] == '\"') {
                lm_sBuffer.append("\\\"");
            } else if (charArray[i] == '\\') {
                lm_sBuffer.append("\\\\");
            } else if (charArray[i] == '\n') {
                lm_sBuffer.append("\\n");
            } else if (charArray[i] == '\r') {
                lm_sBuffer.append("");
            } else if (new Character(charArray[i]).hashCode() == 0) {
                lm_sBuffer.append("");
            } else {
                lm_sBuffer.append(charArray[i]);
            }

        }

        return lm_sBuffer.toString();
    }

    /**
     * 문자열을 넘겨받아 끝 두글자를 **로 처리하여 반환한다. 만약 '@'가 있으면 이메일로 생각하고 '@' 앞부분을 두글자를 ** 처리한다.
     *
     * @param String
     *            src : 변환할 문자열
     * @return String : 변환한 문자열
     */
    public static String changeLastTwoChar(String src) {

        if (src == null || src.length() == 0) {
            return "";
        }

        String result = null;

        if (src.length() == 1) {
            result = "*";
        } else if (src.length() == 2) {
            result = "**";
        } else if (src.indexOf('@') > 0) {

            if (src.indexOf('@') == 1) {
                result = "*";
            } else if (src.indexOf('@') == 2) {
                result = "**";
            } else {
                result = src.substring(0, src.indexOf('@') - 2) + "**";
            }

            result += src.substring(src.indexOf('@'), src.length());
        } else {
            result = src.substring(0, src.length() - 2) + "**";
        }

        return result;

    }

    /**
     * 전화번호 마스킹을 처리하기 위해 문자열을 전달 받아 첫번째, 두번째 "-" 중간에 있는 문자를 *으로 마스킹 처리한다.
     *
     * @param src
     *            : 변환할 문자열
     * @return : 변환된 문자열
     */
    public static String changeMiddleDash(String src) {

        if (src == null || src.length() == 0) {
            return "";
        }

        String result = null;
        result = src;

        boolean hasDash = false;
        hasDash = src.contains("-"); // "-"를 가지고 있는 문자열인지 확인한다.

        if (hasDash) {
            if (src.length() == 11) { // ex) 02-555-7777
                result = src.substring(0, 3) + "***" + src.substring(6);
            } else if (src.length() == 12) { // 010-555-7777
                result = src.substring(0, 4) + "***" + src.substring(7);
            } else { // src.length() == 11, 010-5555-7777
                result = src.substring(0, 4) + "****" + src.substring(8);
            }
        } else {
            if (src.length() == 9) { // ex) 025557777
                result = src.substring(0, 2) + "-" + "***" + "-" + src.substring(5);
            } else if (src.length() == 10) { // 0105557777
                result = src.substring(0, 3) + "-" + "***" + "-" + src.substring(6);
            } else { // src.length() == 11, 01055557777
                result = src.substring(0, 3) + "-" + "****" + "-" + src.substring(7);
            }
        }
        return result;
    }

	/*
	 * public static void main(String[] arg){
	 *
	 * String src = "010-5534-0476";
	 *
	 * System.out.println("src : " + src); changeMiddleDash(src); }
	 */

    /**
     * 문자열을 넘겨받아 첫글자와 마지막 글자를 제외하고 *로 변환하여 반환한다.
     *
     * @param String
     *            src : 변환할 문자열
     * @return String : 변환한 문자열
     */
    public static String changeMiddleChar(String src) {

        if (src == null || src.length() == 0) {
            return "";
        }

        String result = null;

        if (src.length() == 1) {
            result = "*";
        } else if (src.length() == 2) {
            result = src.charAt(0) + "*";
        } else {

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < src.length(); i++) {
                if (i == 0 || i == src.length() - 1) {
                    stringBuilder.append(src.charAt(i));
                } else {
                    stringBuilder.append("*");
                }
            }

            result = stringBuilder.toString();

        }

        return result;

    }

    /**
     * 전달받은 인자 str의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * str의 길이가 len과 같을때까지 num의 우측에 공백문자(' ')를 채워 되돌린다.
     *
     * @param str
     *            - StringBuffer 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @return
     */
    public static String rPad(String str, int len) {
        return rPad(str, len, " ");
    }

    /**
     * /** 전달받은 인자 num의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * num의 길이가 len과 같을때까지 num의 우측에 0을 채워 되돌린다.
     *
     * @param num
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @return
     */
    public static String rPad(int num, int len) {
        return rPad(String.valueOf(num), len, "0");
    }

    /**
     * 전달받은 인자 num의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * num의 길이가 len과 같을때까지 num의 우측에 pad를 채운 문자열을 되돌린다.
     *
     * @param num
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @param pad
     *            - char 채워질 문자
     * @return
     */
    public static String rPad(int num, int len, char pad) {
        return rPad(String.valueOf(num), len, String.valueOf(pad));
    }

    /**
     * 전달받은 인자 num의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * num의 길이가 len과 같을때까지 num의 우측에 pad를 채운 문자열을 되돌린다.
     * <p>
     * 단, pad문자열로 패딩완료 후 전체 길이가 len보다 긴 경우에는,<br/>
     * 되돌릴 문자열을 len길이만큼 잘라내어 되돌린다.
     * </p>
     *
     * @param num
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @param pad
     *            - String 채워질 문자열
     * @return
     */
    public static String rPad(int num, int len, String pad) {
        return rPad(String.valueOf(num), len, pad);
    }

    /**
     * 전달받은 인자 str의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * str의 길이가 len과 같을때까지 str의 우측에 pad를 채운 문자열을 되돌린다.
     * <p>
     * 단, pad문자열로 패딩완료 후 전체 길이가 len보다 긴 경우에는,<br/>
     * 되돌릴 문자열을 len길이만큼 잘라내어 되돌린다.
     * </p>
     *
     * @param str
     *            - String 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @param pad
     *            - String 채워질 문자열
     * @return
     */
    public static String rPad(String str, int len, String pad) {
        // 원본문자열이 주어진 길이 보다 짧은 경우만 true.
        boolean padable = true;
        str = defaultStr(str);

        // 패딩문자가 없는 경우 공백처리
        if (pad == null || "".equals(pad)) {
            pad = new String(" ");
        }
        // 패딩문자가 len보다 길거나 같은 경우
        else if (pad.length() > len) {
            padable = false;
        }
        // 원본문자열이 주어진 길이보다 길거나 같은 경우
        else if (str.getBytes().length >= len) {
            padable = false;
        }

        // 우측패딩
        while (padable && str.getBytes().length < len) {
            str += pad;
        }

        // 문자열로 패딩한경우 주어진 길이보다 되돌릴 문자가 길어지면,
        // 주어진 길이만큼 우측문자열을 삭제.
        if (padable && str.getBytes().length > len) {
            str = new StringBuffer(str).delete(0, len - 1).toString();
        }

        return str;
    }

    /**
     * 전달받은 인자 str의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * str의 길이가 len과 같을때까지 num의 좌측에 공백문자(' ')를 채워 되돌린다.
     *
     * @param str
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @return
     */
    public static String lPad(String str, int len) {
        return lPad(str, len, " ");
    }

    /**
     * 전달받은 인자 num의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * num의 길이가 len과 같을때까지 num의 좌측에 0을 채워 되돌린다.
     *
     * @param num
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @return
     */
    public static String lPad(int num, int len) {
        return lPad(String.valueOf(num), len, "0");
    }

    /**
     * 전달받은 인자 num의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * num의 길이가 len과 같을때까지 num의 좌측에 pad를 채운 문자열을 되돌린다.
     *
     * @param num
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @param pad
     *            - char 채워질 문자
     * @return
     */
    public static String lPad(int num, int len, char pad) {
        return lPad(String.valueOf(num), len, String.valueOf(pad));
    }

    /**
     * 전달받은 인자 num의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * num의 길이가 len과 같을때까지 num의 좌측에 pad를 채운 문자열을 되돌린다.
     * <p>
     * 단, pad문자열로 패딩완료 후 전체 길이가 len보다 긴 경우에는,<br/>
     * 되돌릴 문자열을 len길이만큼 잘라내어 되돌린다.
     * </p>
     *
     * @param num
     *            - int 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @param pad
     *            - String 채워질 문자열
     * @return
     */
    public static String lPad(int num, int len, String pad) {
        return lPad(String.valueOf(num), len, pad);
    }

    /**
     * 전달받은 인자 str의 길이가 두번째 인자 len보다 짧은 경우,<br/>
     * str의 길이가 len과 같을때까지 str의 좌측에 pad를 채운 문자열을 되돌린다.
     * <p>
     * 단, pad문자열로 패딩완료 후 전체 길이가 len보다 긴 경우에는,<br/>
     * 되돌릴 문자열을 len길이만큼 잘라내어 되돌린다.
     * </p>
     *
     * @param str
     *            - String 원본 데이터
     * @param len
     *            - int 데이터의 길이
     * @param pad
     *            - String 채워질 문자열
     * @return
     */
    public static String lPad(String str, int len, String pad) {
        // 원본문자열이 주어진 길이 보다 짧은 경우만 true.
        boolean padable = true;
        str = defaultStr(str);

        // 패딩문자가 없는 경우 공백처리
        if (pad == null || "".equals(pad)) {
            pad = new String(" ");
        }
        // 패딩문자가 len보다 길거나 같은 경우
        else if (pad.length() > len) {
            padable = false;
        }
        // 원본문자열이 주어진 길이보다 길거나 같은 경우
        else if (str.length() >= len) {
            padable = false;
        }

        // 좌측패딩
        while (padable && str.length() < len) {
            str = pad + str;
        }

        // 문자열로 패딩한경우 주어진 길이보다 되돌릴 문자가 길어지면,
        // 주어진 길이만큼 좌측문자열을 삭제.
        if (padable && str.length() > len) {
            str = new StringBuilder(str).delete(Math.abs(str.getBytes().length - len) - 1, str.getBytes().length - 1)
                .toString();
        }

        return str;
    }

    /**
     * 주어진 문자열의 첫글자를 대문자로 변환한다.
     *
     * @param str
     * @return
     */
    public static String toCamelCase(String str) {
        if (defaultStr(str).length() < 1) {
            return str;
        }

        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 문자열을 넘겨받아 null일 경우 입력받은 값으로 변환
     *
     * @param param
     * @param defaultStr
     * @return
     */
    public static String defaultStr(String param, String defaultVal) {
        if ((null == param) || "".equals(param)) {
            return defaultVal;
        }
        return param.trim();
    }

    /**
     * 문자열을 넘겨받아 null일 "" 반환
     *
     * @param param
     * @return
     */
    public static String defaultStr(String param) {
        return defaultStr(param, "");
    }

    /**
     * URL encoding 처리를 한다.
     * @param string
     * @return
     */
    public static String encodeURL(String string) {
        if (string == null)
            return null;
        try {
            return java.net.URLEncoder.encode(string, "UTF-8").replace("+", "%20");
        } catch(UnsupportedEncodingException e){
            logger.error("URLEncoding error for " + string, e);
            return null;
        }

    }


}
