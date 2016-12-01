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
    <meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
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

    <script type="text/javascript" src="/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/common_util.js"></script>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">

        jQuery(document).ready(function(){

            jQuery.ajaxSetup({
                global: false,
                cache: true,
                dataType: 'json',
                timeout: 30000,
                type: 'POST'
            });


            fncSearchList();

        });

        function fncSearchList() {

            jQuery.ajax({
                url: '/board/getBoardLise',
                beforeSend: function(xmlHttpRequest) {
                    cfShowBlock(true);
                },
                error: function(xhr, textStatus, errorThrown) {
                    cfPrintErrorMsg("요청 중 서버에서 에러가 발생하였습니다.");
                },
                success: function(json, textStatus) {
                    if(json.resultCode === 0) {
                        fncSetList(json);
                    } else {
                        cfPrintErrorMsg("요청 중 서버에서 에러가 발생하였습니다.");
                    }
                },
                complete: function(xhr, textStatus) {
                    cfHideBlock();
                }
            });
        }




        function fncSearchUser() {

            jQuery.ajax({
                url: '/user/getUserListAll',
                data: {
                    type : 'allUser'
                },
                beforeSend: function(xmlHttpRequest) {
                    cfShowBlock(true);
                },
                error: function(xhr, textStatus, errorThrown) {
                    cfPrintErrorMsg("요청 중 서버에서 에러가 발생하였습니다.");
                },
                success: function(json, textStatus) {
                    if(json.resultCode === 0) {
                        fncSetList(json);
                    } else {
                        cfPrintErrorMsg("요청 중 서버에서 에러가 발생하였습니다.");
                    }
                },
                complete: function(xhr, textStatus) {
                    cfHideBlock();
                }
            });
        }

        function fncSetList(json) {

            var userList = json.result.userList;
            var tbody = jQuery('#listbody');
            var tbody_content = '';
            tbody.html('');
            if(json.totalCnt != 0) {

                for(var i = 0 ; i < json.totalCnt ; i++) {
                    tbody_content += "<tr>";
                    tbody_content += "  <td>" + i +"</td>";
                    tbody_content += "  <td>" + userList[i].userId +"</td>";
                    tbody_content += "  <td>" + userList[i].name +"</td>";
                    tbody_content += "  <td>" + userList[i].email +"</td>";
                    tbody_content += "  <td>" + userList[i].company +"</td>";
                    tbody_content += "  <td>" + userList[i].job +"</td>";
                    tbody_content += "  <td>" + userList[i].birthday +"</td>";
                    tbody_content += "  <td>" + userList[i].creDate +"</td>";
                    tbody_content += "</tr>";
                }
            } else {
                tbody_content = "<tr><td colspan='8' class='text-gray text-center'>검색 결과가 없습니다.</td></tr>";
            }
            tbody.html(tbody_content);




        }
    </script>
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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/common/header.jsp"%>
    <%@include file="/WEB-INF/common/side_menu.jsp"%>
    <%--<%@include file="/WEB-INF/common/top_menu.jsp"%>--%>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Free board
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 자유게시판</a></li>
                <%--<li class="active">Here</li>--%>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- Your Page Content Here -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">List</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width:5%">No.</th>
                                <th  class="text-center"style="width:50%">제목</th>
                                <th class="text-center" style="width:15%">조회수</th>
                                <th class="text-center" style="width:15%">작성자</th>
                                <th class="text-center" style="width:15%">작성일</th>
                            </tr>
                        </thead>
                        <tbody id="listbody">
                            <tr>
                                <td colspan="5" class="text-gray text-center">검색 결과가 없습니다.</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
<%--                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <li><a href="#">«</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">»</a></li>
                    </ul>
                </div>--%>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <%@include file="/WEB-INF/common/footer.jsp"%>
    <%@include file="/WEB-INF/common/side_bar.jsp"%>
</div>
<!-- ./wrapper -->
</body>
</html>
