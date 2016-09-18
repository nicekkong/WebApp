package com.je.webapp.validator.api;

public class ApiCommonCode {

    static public final int API_SUCCESS_CODE = 0;
    static public final String API_SUCCESS_MSG  = "SUCC";

    static public final int API_ERR_CODE = 1;
    static public final String API_ERR_MSG  = "API 호출중 오류 발생했습니다.";

    static public final int FINISH_EVENT_SEQ_CODE = 2;
    static public final String FINISH_EVENT_SEQ_MSG = "이벤트가 종료되어 현재 점수는 랭킹에 반영되지 않습니다.";

    static public final int NOT_VALID_GAME_ID_CODE = 3;
    static public final String NOT_VALID_GAME_ID_MSG = "유효하지 않은 gameId 입니다.";

    static public final int NOT_VALID_EVENT_SEQ_CODE = 4;
    static public final String NOT_VALID_EVENT_SEQ_MSG = "유효하지 않은 eventSeq 입니다.";

    static public final int SOI_CONNECTION_ERR_CODE = 5;
    static public final String SOI_CONNECTION_ERR_MSG = "SOI 연동중 오류가 발생됐습니다.";

    static public final int NOT_AUTH_SESSION_ID_CODE = 6;
    static public final String NOT_AUTH_SESSION_ID_MSG = "유효하지 않은 sessionId 입니다.";

    static public final int NO_AVAILABLE_PLAY_CNT_CODE = 7;
    static public final String NO_AVAILABLE_PLAY_CNT_MSG = "사용할 수 있는 게임기회가 없습니다.";

    static public final int NOT_AVAILABLE_PLAY_SEQ_CODE = 8;
    static public final String NOT_AVAILABLE_PLAY_SEQ_MSG = "유효하지 않은 play_seq 입니다.";

    static public final int INVALID_PARAM_FORMAT_CODE = 9;
    //static public final String INVALID_PARAM_FORMAT_MSG = "파라메터 포멧이 잘못됐습니다."; // 파라메터 포멧 검증은 Validator 에서 별도 체크하므로 해당 상수가 실제 메시지로는 사용되지는 않음

    static public final int ALREADY_ADD_CHANCE_MBRID_CODE = 10;
    static public final String ALREADY_ADD_CHANCE_MBRID_MSG = "요청된 serviceCd로 게임기회가 기지급된 mbrId 입니다.";

    static public final int INTERFACE_API_CALL_ERR_CODE = 11;
    static public final String INTERFACE_API_CALL_ERR_MSG = "API Interface 서버 호출 중 에러가 발생됐습니다.";

}
