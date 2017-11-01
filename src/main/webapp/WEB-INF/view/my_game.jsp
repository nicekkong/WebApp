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
            <p>${sessionScope.userInfo.name}님은...
                <input id="name" type="hidden" value="${sessionScope.userInfo.name}">
                <h1>[${sessionScope.userInfo.company}]입니다.</h1>
                <input id="group" type="hidden" value="${sessionScope.userInfo.company}">
            </p>
            <hr/>
            <input id="score1" class="form-control input-lg" type="number" placeholder="Game No.1" style="width:50%;margin: 0px"><br/>
            <input id="inputScore1" class="form-control input-lg" type="number" placeholder="Game No.1" style="width:50%;display:none;" readonly><br/>
            <button id="game1Btn" type="submit" class="btn btn-primary" style="width:50%;">Game 1 저장</button>
            <hr/><br/>
            <input id="score2" class="form-control input-lg" type="number" placeholder="Game No.2" style="width:50%;margin: 0px"><br/>
            <input id="inputScore2" class="form-control input-lg" type="number" placeholder="Game No.2" style="width:50%;display:none;" readonly><br/>
            <button id="game2Btn" type="submit" class="btn btn-primary" style="width:50%;">Game 2 저장</button>
            <hr/><br/>
            <input id="score3" class="form-control input-lg" type="number" placeholder="Game No.3" style="width:50%;margin: 0px"><br/>
            <input id="inputScore3" class="form-control input-lg" type="number" placeholder="Game No.3" style="width:50%;display:none;" readonly><br/>
            <button id="game3Btn" type="submit" class="btn btn-primary" style="width:50%;">Game 3 저장</button>
            <hr/>
            <input id="totalScore" value="0" type="hidden">
            <h2><div id="totPoint">Total Point 0</div></h2>

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

        getScore();



        jQuery('#game1Btn').click(function(){
            var score = jQuery('#score1').val();
            if(!score) {
                alert("점수를 입력하세요.");
                return false;
            }

            saveSore('1', score);

        })

        jQuery('#game2Btn').click(function(){
            var score = jQuery('#score2').val();
            if(!score) {
                alert("점수를 입력하세요.");
                return false;
            }

            saveSore('2', score);

        })

        jQuery('#game3Btn').click(function(){
            var score = jQuery('#score3').val();
            if(!score) {
                alert("점수를 입력하세요.");
                return false;
            }

            saveSore('3', score);
        })

    });


    function getScore() {

        var name = jQuery('#name').val();
        jQuery.ajax({
            url: '/getScore',
            data: {
                name : name
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

                var totScore = 0;

                for(var i = 0 ; i < json.length ; i++) {
console.log("total", json[i])
                    var displayIdx = i+1;
                    jQuery('#score' + displayIdx).hide();
                    jQuery('#game' + displayIdx + 'Btn').hide();
                    jQuery('#inputScore' + displayIdx).show();
                    jQuery('#inputScore' + displayIdx).val(json[i].score);

                    totScore += json[i].score;
                }

                jQuery('#totPoint').text("Total Score : " + totScore);
                jQuery('#totalScore').val(totScore);

            },
            complete: function(xhr, textStatus) {
                cfHideBlock();
            }
        });
    }


    function saveSore(game, score) {

        jQuery.ajax({
            url: '/saveScore',
            data: {
                name : jQuery('#name').val(),
                group : jQuery('#group').val(),
                game : game,
                score : score

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

                console.log(json);

                jQuery('#score' + game).hide();
                jQuery('#game' + game + 'Btn').hide();
                jQuery('#inputScore' + game).show();
                jQuery('#inputScore' + game).val(score);

                var totalScore = Number(jQuery('#totalScore').val()) + Number(score);

                jQuery('#totPoint').text("Total Score : " + totalScore);
                jQuery('#totalScore').val(totalScore);

            },
            complete: function(xhr, textStatus) {
                cfHideBlock();
            }
        });
    }

</script>

</html>
