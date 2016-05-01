<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function login(){
			window.location.href = "/buyer/login.shtml?returnUrl="+window.location.href;
		};
		function regist(){
			window.location.href = "/buyer/regist.shtml?returnUrl="+window.location.href;
		};
	</script>
  </head>
  
  <body>
   <div id="shortcut-2014">
    <div class="w">
    	<p class="l">
		<span class="l">
			收藏本网站！北京<a href="#" title="更换">[更换]</a>
		</span>
		</p>
        <ul class="fl" clstag="h|keycount|2015|01a">
            <li class="dorpdown" id="ttbar-mycity"></li>
        </ul>
        <ul class="fr">
            <li class="fore1" id="ttbar-login" clstag="h|keycount|2015|01b">
            	<c:if test="${!isLogin}">
            		<a href="javascript:login();" class="link-login">你好，请登录</a>&nbsp;&nbsp;<a href="javascript:regist();" class="link-regist style-red">免费注册</a>
            	</c:if>
            	<c:if test="${isLogin}">
                	<a href="/buyer/index.shtml" class="link-login">你好，${username }</a>&nbsp;&nbsp;<a href="/shop/logout.shtml" class="link-regist style-red">退出</a>
                </c:if>
            </li>
            <li class="spacer"></li>
            <li class="fore2" clstag="h|keycount|2015|01c">
                <div class="dt">
                    <a target="_blank" href="http://order.jd.com/center/list.action">我的订单</a>
                </div>
            </li>
            <li class="spacer"></li>
            <li class="fore3 dorpdown" id="ttbar-myjd" clstag="h|keycount|2015|01d">
                <div class="dt cw-icon">
                    <i class="ci-right"><s>◇</s></i>
                    <a target="_blank" href="http://home.jd.com/">个人中心</a>
                </div>
                <div class="dd dorpdown-layer"></div>
            </li>
            <li class="spacer"></li>
            <li class="spacer"></li>
            <li class="fore6 dorpdown" id="ttbar-apps" clstag="h|keycount|2015|01g">
                <div class="dt cw-icon">
                    <i class="ci-left"></i>
                    <i class="ci-right"><s>◇</s></i>
                    <a target="_blank" href="http://app.jd.com/">手机版</a>
                </div>
            </li>
            <li class="spacer"></li>
            <li class="fore7 dorpdown" id="ttbar-atte" clstag="h|keycount|2015|01h">
                <div class="dt cw-icon">
                    <i class="ci-right"><s>◇</s></i>关注我们
                </div>
                <div class="dd dorpdown-layer"></div>
            </li>
            <li class="spacer"></li>
            <li class="fore8 dorpdown" id="ttbar-serv" clstag="h|keycount|2015|01i">
                <div class="dt cw-icon">
                    <i class="ci-right"><s>◇</s></i>客户服务
                </div>
                <div class="dd dorpdown-layer"></div>
            </li>
            <li class="spacer"></li>
            <li class="fore9 dorpdown" id="ttbar-navs" clstag="h|keycount|2015|01j">
                <div class="dt cw-icon">
                    <i class="ci-right"><s>◇</s></i>网站导航
                </div>
                <div class="dd dorpdown-layer"></div>
            </li>
        </ul>
        <span class="clr"></span>
    </div>
</div>

  </body>
</html>
