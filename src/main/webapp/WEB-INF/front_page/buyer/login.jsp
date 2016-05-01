<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/res/css/login.css">
    <script type="text/javascript" src="/res/js/jquery-1.11.3.min.js"></script>
    <link href="/res/validetta/validetta.css" rel="stylesheet" type="text/css" media="screen" >
    <script type="text/javascript" src="/res/validetta/validetta.js"></script>
    <script src="/res/js/com.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("#jvform").validetta({

            });
        });
    </script>
</head>
<body>
     <div id="top">
        <a href="/product/display/list.shtml"><img src="/res/img/pic/logo.jpg" alt="Big-Boy"></a>
    </div>
    <div class="bg">
        <div class="login">
            <form id="jvform" action="/buyer/login.shtml" method="post">
             	<input type="hidden" name="returnUrl" value="${param.returnUrl}"/>
             	<div class="error" <c:if test="${empty msg }">style="display: none"</c:if>>${msg }</div><br>
                <label for="username">用户名:</label>
                <input type="text" name="username"  placeholder="请输入用户名" value="${username }" data-vd-message-required="用户名不能为空" data-validetta="required"><br/>
                <label for="password">密&nbsp;&nbsp;&nbsp;码:</label>
                <input type="password" name="password" placeholder="请输入密码" value="${password }" data-vd-message-required="密码不能为空" data-validetta="required"><br/>
                <label for="captcha">验证码:</label>
                <input type="text" name="captcha" style="width: 70px"  data-vd-message-required="验证码不能为空" data-validetta="required">
                <img src="/captcha.svl" onclick="this.src='/captcha.svl?d='+new Date()" class="code" alt="换一张" height="30px"/>
                <a href="javascript:void(0);" onclick="$('.code').attr('src','/captcha.svl?d='+new Date())" title="换一张">换一张</a>
                <button type="submit" onclick="check()">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陆</button>
            </form>
            <p>还没有账号?<a href="/buyer/regist.shtml">立即注册！</a></p>
        </div>
    </div>
</body>
</html>