<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/res/css/formValidation.css"/>
    <link rel="stylesheet" href="/res/css/font-awesome.css"/>
    <link rel="stylesheet" href="/res/css/foundation.min.css"/>
    <link rel="stylesheet" href="/res/css/normalize.min.css"/>
    <link href="validetta/validetta.css" rel="stylesheet" type="text/css" media="screen" >


</head>
<body>
<div class="row">
    <div class="small-8 small-centered columns">
        <h2>个人注册</h2>
        <hr/>

        <form id="jvform" action="/buyer/regist.shtml" method="post">
        <input type="hidden" name="returnUrl" value="${param.returnUrl}"/>
            <div class="row">
                <div class="small-3 columns">
                    <label class="right">用 户 名</label>
                </div>
                <div class="small-9 columns">
                    <div class="row">
                        <div class="small-6 columns">
                            <input type="text" name="username" placeholder="您的用户名" />
                        </div>
                    </div>
                </div>
            </div>

             <div class="row">
                <div class="small-3 columns">
                    <label class="right">真实姓名</label>
                </div>
                <div class="small-6 small-pull-3 columns">
                    <input type="text" name="realName" placeholder="您的真实姓名" />
                </div>
            </div>

            <div class="row">
                <div class="small-3 columns">
                    <label class="right">密&nbsp;&nbsp;&nbsp;码</label>
                </div>
                <div class="small-6 small-pull-3 columns">
                    <input type="password" name="password" placeholder="建议长度大于6位"/>
                </div>
            </div>

            <div class="row">
                <div class="small-3 columns">
                    <label class="right">确认密码</label>
                </div>
                <div class="small-6 small-pull-3 columns">
                    <input type="password" placeholder="请再次输入密码" />
                </div>
            </div>

            <div class="row">
                <div class="small-3 columns">
                    <label class="right">E-mail</label>
                </div>
                <div class="small-6 small-pull-3 columns">
                    <input type="text" name="email" placeholder="邮箱" />
                </div>
            </div>
            <div class="row">
                <div class="small-3 columns">
                    <label class="right">性别</label>
                </div>
                <div class="small-9 columns">
                    <input type="radio" name="sex" value="male" /><label>先生</label>
                    <input type="radio" name="sex" value="female" /><label>女士</label>
                </div>
            </div>

            <div class="row">
                <div class="small-9 small-push-3 columns">
                    <input type="checkbox" value="agree" checked/> <label>我已阅读并同意《用户注册协议》</label>
                </div>
            </div>

            <div class="row">
                <div class="small-9 small-push-3 columns">
                    <button type="submit" class="button small">注册</button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>