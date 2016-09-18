<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title></title>
	<meta charset="UTF-8">
	<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
    <script src="/js/jquery-1.10.2.min.js"></script>
    <link rel="apple-touch-icon" href="touch.png">
</head>
<body>
        <p>${msg}</p>    <!-- Data from System Resource -->
        <p>${data}</p>    <!-- Data from controller -->
</body>
</html>
<script type="text/javascript">
    var serverEnv = '<%= System.getProperty("spring.profiles.active") %>';

    $(document).ready(function(){
        console.log("call ready func~!! Server Env : " + serverEnv);
    });
</script>