<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>BigBoy网上商城-后台首页</title>
</head>
<frameset rows="72,*" frameborder="0" border="0" framespacing="0">
	<!-- 为什么不是/control/top.do呢？这里是相对路径,自带一个/control/，但是绝对不能是/top.do,这种就是localhost：8080/top.do了 -->
	<frame src="top.do" name="topFrame" noresize="noresize" id="leftFrame" />
	<frame src="main.do" name="mainFrame" id="mainFrame" />
</frameset>
<noframes><body></body></noframes>
</html>
