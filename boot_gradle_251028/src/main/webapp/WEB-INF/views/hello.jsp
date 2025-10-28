<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 밑에 코드는 static 까지 감 -->
<c:set var = "contextPath" value ="${pageContex.request.contextPath}"></c:set> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type="text/javascript" src="${contextPath}/js/scriptTest.js"></script>
</head>
<body>
  	hello2.jsp<br>
	<h2>${message}</h2>
<!--	밑에 코드는 img를 받아옴-->
	<img src ="${contextPath}/image/duke.png" width ="200" height="200"> 
    <input type="button" name="test" value="테스트" onclick="test();">
</body>
</html>