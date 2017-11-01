<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
    <link rel="apple-touch-icon" href="touch.png">
    <title>AdminLTE 2 | Starter</title>
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
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="/dist/css/skins/skin-blue.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/common_util.js"></script>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-collapse">
<div class="wrapper">
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">


        <!-- Main content -->
        <section class="content" style="height:700px;">
            <!-- Your Page Content Here -->
            <p>${msg}</p>
            <p>${data}</p>


            <h1>이름을 입력하세요.</h1>

            <input id="name" class="form-control input-lg" type="text" placeholder="이름">
            <br/>
            <button id="saveBtn" type="submit" class="btn btn-primary" style="width:100%;">저
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp장</button>

        </section>
        <!-- /.content -->






    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->
</body>
<script type="text/javascript">
    document.addEventListener("touchstart", function() {},false);
</script>
<script>

    jQuery(document).ready(function(){

        jQuery.ajaxSetup({
            global: false,
            cache: true,
            dataType: 'json',
            timeout: 10000,
            type: 'POST'
        });

        jQuery('#saveBtn').click(function(){

            saveName();

        })

    });

    function saveName() {

        var name = jQuery('#name').val();

        if(!name) {
           alert("이름을 입력하세요");
           return false;
        }

//        alert(name);
        // Ajax 요청
        jQuery.ajax({
            url: '/game/login',
            data: {
                name : name
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
                location.href="/my_game";

            },
            complete: function(xhr, textStatus) {
                cfHideBlock();
            }
        });


    }

</script>




</html>
