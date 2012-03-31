<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${webpara.sysname}
</title>
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript" type="text/javascript">
 
var issubmit=false;
function submitform (){
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
  if(!issubmit){//如果没有提交,直接关闭窗口的话
  
    $.getJSON("../commonajax/ajaxlogout.pl", {"now":new Date().getTime()}, function(resp){
      }
    ); 
  }
}
</script>
</head>

<body leftmargin="0" topmargin="0" onunload="exitform()">
<div class="header">

  <div class="logo left"><a href="index.html"><img src="../images/logo.gif" width="234" height="51" /></a></div>
  <div class="denglu right">${lawyer.lawyername} ，您好，欢迎您登录培训平台！&nbsp;&nbsp;
  	<a href="index.pl?old=1">旧版</a>&nbsp;&nbsp;
  	<a href="index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a></div>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="index.pl"  class="current">首页</a></li>
  <li><a href="lessoncenter.html">选课中心</a></li>
  <li><a href="course.html">我选购的课程</a></li>
  <li><a href="notice.html">培训通知</a></li>
  <li><a href="jifen.html">我的学分</a></li>
  <li><a href="favorites.html">收藏夹</a></li>
  <li><a href="shopping.html">购物车</a></li>
  <li><a href="order.html">我的订单</a></li>
  <li><a href="news.html">我的消息</a></li>
  <li><a href="application.html">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl">个人信息</a></li>
</ul></div>
</body>
</html>
