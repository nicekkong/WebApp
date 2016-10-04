/***************************************************************
 * 화면을 회색 레이어로 블록 처리하는 메소드
 *
 * @param boolean isLoading : 로딩 이미지를 띄울지 여부(true/false)
 * @return void
****************************************************************/
function cfShowBlock(isLoading) {

	if(isLoading) {

		var loadingDiv = '<img id="loadingImg" src="/images/loading/bigWaiting.gif" width="32" height="32" style="display:none" />';
		$(loadingDiv).appendTo('body');

		$.blockUI({message: $('#loadingImg'),
            css: {
                top:  ($(window).height() - 32) /2 + 'px',
                left: ($(window).width() - 32) /2 + 'px',
                width: '32px'
            }}
        );

	} else {

		$.blockUI({message: null});

	}

}

/***************************************************************
 * 화면을 회색 레이어로 블록 처리하는 메소드
 *
 * @param void
 * @return void
****************************************************************/
function cfHideBlock() {

	$.unblockUI();

}

/***************************************************************
 * 에러 메시지를 전달받아 화면에 출력한다.
 * 중요) 하단에는 alert로 출력하나 실제 사용 시 디자인에 따라 변경
 *      해야 한다.
 *
 * @param String msg : 에러 메시지
 * @return void
****************************************************************/
function cfPrintErrorMsg(msg) {

	alert(msg);

}

/***************************************************************
 * 일반 메시지를 전달받아 화면에 출력한다.
 * 중요) 하단에는 alert로 출력하나 실제 사용 시 디자인에 따라 변경
 *      해야 한다.
 *
 * @param String msg : 일반 메시지
 * @return void
****************************************************************/
function cfPrintCommonMsg(msg) {

	alert(msg);

}

/***************************************************************
 * 날짜 문자열을 전달받아 구분자로 구분하여 반환한다.
 *
 * @param String dateStr     : YYYYMMDDHHMMSS
 * @param boolean isViewTime : 시간 표시여부
 * @param int divNo          : 날짜 표시 포맷 (0, 1, 2중 하나)
 * @return String            : 구분자로 구분된 날짜 (YYYY-MM-DD HH:MM)
****************************************************************/
function cfGetDivDate(dateStr, isViewTime, divNo) {

	var div = new Array();
	div[0] = new Array(".", ".", "", ":", "");
	div[1] = new Array("-", "-", "", ":", "");
	div[2] = new Array("/", "/", "", ":", "");
	div[3] = new Array("년", "월", "일", "시", "분");

	if(dateStr==null) {
		return "";
	}

	if(divNo!=0 && divNo!=1 && divNo!=2 && divNo!=3) {
		divNo = 0;
	}

	var result = "";

	var year = dateStr.substring(0,4);
	var month = dateStr.substring(4,6);
	var day = "";

	if(dateStr.length > 6) {
		day = dateStr.substring(6,8);
	}

	if(dateStr.length > 6) {
		result = year + div[divNo][0] + month + div[divNo][1] + day + div[divNo][2];
	} else {
		result = year + div[divNo][0] + month;
	}

	//시간을 출력할 경우
	if(isViewTime && dateStr.length >= 12) {
		var hour = dateStr.substring(8,10);
		var minute = dateStr.substring(10,12);

		result += " " + hour + div[divNo][3] + minute + div[divNo][4];
	}

	return result;
}

/***************************************************************
 * 문자열을 전달받아 trim 처리하는 메소드
 *
 * @param String str : 문자열
 * @return String    : trim 처리한 문자열. null은 ""를 반환한다.
****************************************************************/
function cfTrim(str) {

	if(str == null) {
		return "";
	}

	return str.replace(/(^\s*)|(\s*$)/gi, "");

}

/***************************************************************
 * 문자열을 전달받아 null이면 "" 처리하는 메소드
 *
 * @param String str : 문자열
 * @return String    : trim 처리한 문자열. null은 ""를 반환한다.
****************************************************************/
function cfChangeNullToEmpty(str) {

	if(str == null) {
		return "";
	}

	return str;

}

/************************************************************************
 * 필드의 글자수 제한 체크를 하는 메소드
 *
 * @param String textName : textarea ID
 * @param String cntName  : 현재 글자수 span 태그 id
 * @param String maxCount : 최대 글자수
 * @return void
*************************************************************************/
function cfCheckTextArea(textName, cntName, maxCount) {
	var now = maxCount - $(textName).val().length;
	if(now < 0) {
		$(textName).val($(textName).val().substr(0, maxCount));
	}

	$(cntName).text($(textName).val().length);
}

/************************************************************************
 * 문자열의 byte 수를 반환하는 메소드
 *
 * @param String src : 계산할 문자열
 * @return int       : byte 수
*************************************************************************/
function cfGetStrByteCnt(src) {
	var byteCnt = 0;

	for(var i=0; i<src.length; i++) {
		if(src.charCodeAt(i) < 256) {
			byteCnt++;
		} else {
			byteCnt = byteCnt + 2;
		}
	}

	return byteCnt;
}

/************************************************************************
 * 필드에 숫자가 아닌 문자를 제거하는 메소드
 *
 * @param void
 * @return void
*************************************************************************/
function cfDeleteChar(fieldName) {
	var str = $(fieldName).val();

	if(!str.match(/^([0-9]+)$/)) {
		$(fieldName).val(str.substr(0, str.length-1));
    }
}

/************************************************************************
 * 숫자를 넘겨받아 3자리단위 콤마로 분리하여 반환하는 메소드
 *
 * @param num
 * @return String
*************************************************************************/
function cfFormatComma(num) {
    if( typeof num != "string"){
        num = String(num);
    }
    var decimal = "";
    var decimalYn = num.indexOf(".") > -1 ? true : false;

    if ( decimalYn ){
        decimal = num.split(".")[1];
        num = num.split(".")[0];
    }

    var length = num.length;
    var commaNum = "";

    for ( var i = 1; i <= length; i++) {
        commaNum = num.charAt(length - i) + commaNum;
        if (i%3 == 0 && length-i != 0) {
            commaNum = "," + commaNum;
        }
    }

    if ( decimalYn ){
        //소수점 두자리까지 이용 가능
        if(decimal.length >1) {
            decimal = decimal.substring(0, 2);
        }
        commaNum = commaNum +"."+ decimal;
    }

    return commaNum;
}