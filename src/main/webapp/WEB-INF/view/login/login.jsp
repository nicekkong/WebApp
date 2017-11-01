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
    <title>AdminLTE 2 | Log in</title>
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
        jQuery(document).ready(function(){

            jQuery.ajaxSetup({
                global: false,
                cache: true,
                dataType: 'json',
                timeout: 30000,
                type: 'POST'
            });

            jQuery('#btn_login').click(function() {
                fncLoginProc();
                //return false;
            })

            /*엔터키 이벤트 */
            jQuery(document).keydown(function (key) {
                if(key.keyCode === 13) {
                    fncLoginProc()
                }
            });

        });

        function fncLoginProc() {

            var user_id = jQuery('#user_id').val();
            var password = jQuery('#password').val();

            jQuery.ajax({
                url: '/user/loginProc',
                data: {
                    userId : user_id,
                    password : password
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

                    //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                },
                success: function(json, textStatus) {
                    if(json.loginYn === "N") {
                        cfPrintErrorMsg("아이디/패스워드를 다시 확인해주세요.");
                        return ;
                    }
                    document.location.href = "/index";
                },
                complete: function(xhr, textStatus) {
                    cfHideBlock();
                }
            });
        }

    </script>

</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="/index"><b>@nicekkong</b>.com</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
        <div class="form-group has-feedback">
                <input type="text" id="user_id" class="form-control" placeholder="User ID">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" id="password" class="form-control" placeholder="Password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label>
                        <input type="checkbox"> Remember Me
                    </label>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button type="submit" id="btn_login" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div>
            <!-- /.col -->
        </div>

        <%--<div class="social-auth-links text-center">
            <p>- OR -</p>
            <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in
                using
                Facebook</a>
            <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in
                using
                Google+</a>
        </div>--%>
        <!-- /.social-auth-links -->
        <a href="#">I forgot my password</a><br>
        <a href="/user/register" class="text-center">Register a new membership</a>

    </div>
    <!-- /.login-box-body -->
   data : ${data}
    <%--result : ${result.weather.dust[0].pm10.grade}--%>
    <%--result : ${result.weather.dust[0].pm10.value}--%>
    <hr/>
    xxx : ${result}
    <hr/>
    ${result.weather.dust[0].station.name} =>
    ${result.weather.dust[0].pm10.grade}(${result.weather.dust[0].pm10.value})

    <hr/>
    NICEKKONG's World

</div>
<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<%--<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>--%>
<!-- Bootstrap 3.3.6 -->
<script src="/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>
