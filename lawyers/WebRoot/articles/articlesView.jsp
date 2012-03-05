<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>中国律师培训网-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>

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
<script language="javascript">
function fanye(str){
  document.form2.pageNo.value=str;
  document.form2.submit();
}

</script>
</head>
<body>

<div class="header">
  <form name="form1" method="post" action="../common/logout.pl">	
  <div class="logo left"><a href="index.html"><img src="${resourcePath}${topbarpic}" width="234" height="51" /></a></div>
  <div class="denglu right">
  	${lawyer.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp; 
  	<a href="../index/index.pl?old=1">旧版</a>&nbsp;&nbsp;
  	<a href="../index/index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a>
  </div>
  </form>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="../index/index.pl"  >首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">选课中心</a></li>
    <!-- <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li> -->
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1">培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl">个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" class="current">系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>系统消息</strong></div>
<div class="con">
  <div class="con_left2 left">
     <div class="con_left2_title">系统消息</div>   
     <ul class="con_wz">
     <li><a href="../articles/notifyList.pl?type=1">重要通知</a></li>
        <li><a href="../articles/notifyList.pl?type=2">系统帮助</a></li>
      </ul>
  </div>
  <div class="con_right6 left">
    <div class="con_right6_title"><h2>
    	<s:if test="type==1">
    		重要通知
    	</s:if>
    	<s:else> 
    		系统帮助
    	</s:else></h2>
    </div>
    <div class="star2">
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
		<tr>
    		<td valign="top" bgcolor="#FFFFFF">
    			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
					<tr>
        				<td width="100%"  class="tab_content1" align="center">
        					<s:hidden name="articles.type"/>
        					<s:hidden name="type"/>    
        					${articles.title}
        				</td>
        			</tr>     
       				<tr>
						<td  class="tab_content1" align="left">
          					${articles.content}
       					</td>
       				</tr>
   					<tr>       
        				<td class="tab_content1" align="center">　          　
          				<input type="button" name="Submit3" value=" 返回 " onClick="javascript:history.go(-1)" />
          				</td>
        			</tr>
    			</table>
    </td>
  </tr>
</table>
    	
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>

</body>
</html>