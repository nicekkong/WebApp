<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Registration Page</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/plugins/iCheck/square/blue.css">
    <!-- Datepicker -->
    <link rel="stylesheet" href="/plugins/datepicker/datepicker3.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="/plugins/select2/select2.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/common_util.js"></script>

    <script>
        jQuery(document).ready(function () {

            jQuery.ajaxSetup({
                global: false,
                cache: true,
                dataType: 'json',
                timeout: 10000,
                type: 'POST'
            });

            <%-- ID 중복 체크 버튼 --%>
            jQuery('#id-dup-chk').click(function() {
                fncCheckIdDup();
            });
            jQuery(document).keydown(function (e){
                var focus_field = '';
                try {
                    focus_field = e.target.attributes['id'].value;
                } catch(e) {
                    ;
                }
                var keyCode = e.keyCode;
//                if(keyCode === 13 && focus_field === 'input-id') {
//                    fncCheckIdDup();
//                }
                // ID 중복 체크 메서드 호출
                keyCode === 13 && focus_field === 'input-id' && fncCheckIdDup();
            });

            jQuery(document).keyup(function (e){
                var focus_field = '';
                try {
                    focus_field = e.target.attributes['id'].value;
                } catch(e) {
                    ;
                }

                // 패스워드 일치여부 체크 메서드 호출
                if(focus_field === 'password' || focus_field === 're-password') {
                    funChkPassword();
                }
            });

            <!-- Register 버튼 이벤트 등록 -->
            jQuery('#registerBtn').click(function(){
               fncRegisger();
            });

        });


        /**
         * ID 중복 체크 처리 함수
         */
        function fncCheckIdDup() {
            var input_id;
            var msg;
            input_id = jQuery('#input-id').val();

            console.log("input_id : ", input_id);

            // ID 입력을 하지 않은 경우
            if(input_id === '') {
                msg = '사용하실 ID를 입력해주세요.';
                showModal(msg);
                return false;
            }

            //  ID 중복 체크
            jQuery.ajax({
                url: '/user/idDup/check',
                data: {
                    inputId : input_id
                },
//                beforeSend: function(xmlHttpRequest) {
//                    cfShowBlock(true);
//                },
                //error: function(xhr, textStatus, errorThrown) {
                error:function(request,status,error){
                    cfPrintErrorMsg("요청 중 서버에서 중요한 에러가 발생하였습니다.");
                    console.dir(request);
                    console.dir(status);
                    console.dir(error);
                },
                success: function(json, textStatus) {
                    fncIdSetting(json);

                },
//                complete: function(xhr, textStatus) {
//                    cfHideBlock();
//                }
            });
        }

        /**
         * Modal 창 호출
         * @param msg 모달 창 내부 메시지
         * @param title 모달창 Title(없으면 'Information')
         */
        function showModal(msg, title) {
            jQuery('#modal_str').html(msg);
            if(title) {
                jQuery('#myModalLabel').text(title);
            } else {
                jQuery('#myModalLabel').text('Information');
            }

            jQuery('#myModal').modal(
                    // options
            );

        }

        /**
         * ID 중복 체크 결과에 따른 관련 화면단 처리
         * @param json isDup=> 중복 체크 결과(true/false)
         */
        function fncIdSetting(json) {
            var msg = '';
            if(json.isDup) {
                msg = '[' + jQuery('#input-id').val() + '] 는 사용할 수 없는 ID 입니다.\n다시 입력해 주세요.';

                $('#myModal').on('hidden.bs.modal', function (e) {
                    jQuery('#input-id').focus();
                    e.preventDefault();
                });
                showModal(msg, 'ID Duplication Check!!');
                jQuery('#input-id').val('');
                jQuery('#userId').val('');
            } else {
                msg = '[' + jQuery('#input-id').val() + ']는 정말 멋진 ID입니다!!';
                showModal(msg, 'ID Duplication Check!!');
                jQuery('#userId').val(jQuery('#input-id').val());
                $('#myModal').on('hidden.bs.modal', function (e) {
                    jQuery('#password').focus();
                    e.preventDefault();
                });
            }
        }

        /**
         * 입력한 패스워드 일치 여부 체크
         */
        function funChkPassword() {

            var password    = jQuery('#password').val();
            var repassword  = jQuery('#re-password').val();

            //console.log(password + ' : ' + repassword );

            if(password !== repassword) {
                jQuery('#chk_password').css('color','red');
                jQuery('#chk_password').text('패스워드가 일치하지 않습니다.');

            } else {
                jQuery('#chk_password').text('');
                funChkPassword.chkPassword = true;
            }
            console.log(funChkPassword.chkPassword );
        }
        funChkPassword.chkPassword = false;     // 메모리이모제이션


        <!-- 등록 처리 메서드 -->
        function fncRegisger() {

            // 입력받은 사용자 정보를 셋팅한다.
            var userInfo = setUserInfo();

            console.log(userInfo);

            // 입력값을 검증한다.
            var errMsg = validateInput(userInfo);
            if(errMsg !== "") {
                var msg = '아래 입력 값들을 다시 확인해주세요<br/><br/>' + errMsg.toUpperCase();
                $('#myModal').on('hidden.bs.modal', function (e) {
                    e.preventDefault();
                });
                showModal(msg, 'Check Input Again~!!');
                return false;
            }

            // Ajax 요청
            jQuery.ajax({
                url: '/user/process/Register',
                data: {
                    userId  : userInfo.userId,
                    password: userInfo.password,
                    name    : userInfo.name,
                    job     : userInfo.job,
                    email   : userInfo.email,
                    company : userInfo.company,
                    birthday: userInfo.birthday
                },
                beforeSend: function(xmlHttpRequest) {
                    cfShowBlock(true);
                },
                //error: function(xhr, textStatus, errorThrown) {
                error:function(request,status,error){
                    cfPrintErrorMsg("요청 중 서버에서 중요한 에러가 발생하였습니다.");
                    console.dir(request);
                    console.dir(status);
                    console.dir(error);
                },
                success: function(json, textStatus) {
                    fncRegisterAlert(json);

                },
                complete: function(xhr, textStatus) {
                    cfHideBlock();
                }
            });
        }

        /**
         * 입력받은 사용자 정보를 셋팅한다.
         */
        function setUserInfo() {

            var userInfo = {
                'userId': jQuery('#userId').val(),
                'password' : jQuery('#password').val(),
                'name' : jQuery('#name').val(),
                'email' : jQuery('#email').val(),
                'company' : jQuery('#company').val(),
                'job' : jQuery('#job option:selected').val(),
                'birthday' : jQuery('#birthday').val().replace('-', '').replace('-', '')
            };
            console.log(userInfo);
            return userInfo;
        }

        /**
         * 입력받은 값을 검증한다.
         */
        function validateInput(userInfoObj) {
            var errMsg = "";
            jQuery.each(userInfoObj, function(k, v){
                if(v === "") {
                    errMsg += k + ",";
                }

            });

            if(!jQuery(":checkbox[id='agreeTerm']").is(":checked")) {
                errMsg += "<p>\n가입 동의에 체크를 해주세요.</p>";
            }
            errMsg = errMsg.replace(/,$/, '');  // 가장 맨뒤 항목의 , 를 없애준다.

            console.log("errMsg : ", errMsg);
            return errMsg;
        }

        function fncRegisterAlert(json) {

            var isSuccess = json.isRegister == true? true : false;
            var msg = "";
            var title = "회원 등록";

            if(isSuccess) {
                msg = "성공적으로 등록됐습니다!!<br/>가입을 진심으로 환영합니다~!! <br/>로그인 후, 이용바랍니다.";
                showModal(msg, title);
                setTimeout(function() {
                    location.href = "/user/login";
                }, 2500);
            } else {
                msg = "저장중 오류가 발생했습니다. <br/>시스템 관리자에게 문의 바랍니다.";
                showModal(msg, title);
            }
        }

        function showTerm() {

            var title = "회원가입 이용약관";
            var msg = "<p>우리는 좋은 서비스 입니다.</p>";
            msg += "회원 가입해주실 거죠?";
            msg += "<br/>동의하시면 체크박스에 체크해주세요.";

            showModal(msg, title);
        }



    </script>

</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="/index"><b>@nicekkong</b>.com</a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">회원 가입을 환영합니다.<br/>
            Welcome for register a new membership</p>

        <!-- ID -->
        <div class="row">
            <div class="col-xs-8">
                <div class="form-group has-feedback">
                    <input type="text" class="form-control" id='input-id' placeholder="ID">
                    <input type="hidden" class="form-control" id='userId'>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button type="submit" class="btn btn-block btn-default" id="id-dup-chk">ID 중복체크</button>
            </div>
            <!-- /.col -->
        </div>

        <div class="form-group has-feedback">
            <input type="password" class="form-control" id="password" placeholder="Password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" class="form-control" id="re-password" placeholder="Retype password">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
            <span id="chk_password"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="name" placeholder="이름">
            <span class="glyphicon glyphicon-font form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="email" class="form-control" id="email" placeholder="Email">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="text" class="form-control" id="company" placeholder="소속">
            <span class="glyphicon glyphicon-king form-control-feedback"></span>
        </div>

        <div class="row">
            <div class="col-xs-10">
                <div class="form-group has-feedback">
                    <select id="job" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true" data-placeholder="업무">
                        <option> </option>
                        <option value="development">Application 개발</option>
                        <option value="consultant">컨설팅</option>
                        <option value="infra">인프라</option>
                        <option value="system_op">시스템 운영</option>
                        <option value="uxui">UX/UI 디자인</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-2 center-block">
                <span class="glyphicon glyphicon-wrench form-control-feedback"></span>
            </div>
            <!-- /.col -->
        </div>

        <div class="form-group has-feedback">
            <div class="input-group date">
                <input type="text" class="form-control pull-right" id="birthday" placeholder="생일">
                <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label>
                        <input type="checkbox" id="agreeTerm"> I agree to the <a href="#" onclick="showTerm();">terms</a>
                    </label>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button type="submit" class="btn btn-primary btn-block btn-flat" id="registerBtn">Register</button>
            </div>
            <!-- /.col -->
        </div>
        <%--        <div class="social-auth-links text-center">
                    <p>- OR -</p>
                    <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up
                        using
                        Facebook</a>
                    <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign up
                        using
                        Google+</a>
                </div>--%>

        <a href="login" class="text-center">I already have a membership</a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body">
                <span id="modal_str"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal --//>



<!-- jQuery 2.2.3 -->
<%--<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>--%>
<!-- Bootstrap 3.3.6 -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/plugins/iCheck/icheck.min.js"></script>
<!-- bootstrap datepicker -->
<script src="/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/plugins/datepicker/locales/bootstrap-datepicker.kr.js"></script>

<!-- Select2 -->
<script src="/plugins/select2/select2.full.min.js"></script>

<script>

    document.addEventListener("click", function () {
    }, false);

    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });

    //Date picker
    $('#birthday').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd',
        language: 'kr'
    });

    //Initialize Select2 Elements
    $(".select2").select2();

</script>

</body>
</html>
