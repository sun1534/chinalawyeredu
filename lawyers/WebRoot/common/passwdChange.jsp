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
<jscalendar:head/>
<script language="javascript">

function deletephoto(lawyerid){
if(confirm("您确实要删除这个照片吗?")){
var url="../lawyersajax/photoDelete.pl?lawyerid=${lawyers.lawyerid}";
  $.getJSON(url, { "lawyerid":lawyerid,"now":new Date().getTime()}, function(json){
     if(json.success == "true"){
   		$("#imgdiv").empty();
      }else{
	   alert("照片删除失败");
      }
   });
}
else{
return;
}
}
</script>
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
<body>

<div class="header">
  <form name="form1" method="post" action="../common/logout.pl">	
  <div class="logo left"><a href="index.html"><img src="../images/logo.gif" width="234" height="51" /></a></div>
  <div class="denglu right">
  	${lawyer.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp; 
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
  <li><a href="../lawyers/lawyersEditSelf!input.pl" class="current">个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1">系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<a href="../lawyers/lawyersEditSelf!input.pl"><strong>个人信息</strong></a></div>
<div class="con">
  <div class="con_left2 left">
     <div class="con_left2_title">个人信息</div>   
     <ul class="con_wz">
     <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人资料维护</a></li>
        <li><a href="../common/passwdChange!input.pl">密码修改</a></li>
      </ul>
  </div>
  <div class="con_right6 left">
    <div class="con_right6_title"><h2>密码修改</h2></div>
    <div class="star2">
    <s:form name="form2" action="passwdChange" method="post" validate="true">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"></td>
        </tr>
      <tr>
        <td width="37%" class="tab_content1" align="right">旧密码：</td>
        <td width="63%" colspan="2" class="tab_content1"> 　
          <s:password name="oldpass" size="25" maxlength="25"/>
        </td>
        </tr>
      <tr>
        <td class="tab_content" align="right">新密码：</td>
         <td width="63%" colspan="2" class="tab_content1"> 　
          <s:password name="newpass" size="25" maxlength="25"/>
         </td>
        </tr>
      <tr>
        <td class="tab_content1"  align="right">重新输入：</td>
        <td colspan="2" class="tab_content1">　
           <s:password name="passagain" size="25" maxlength="25"/>
        </td>
        </tr>
      <tr>
        <td class="tab_content">                    </td>
        <td colspan="2" class="tab_content">　
          
          <input type="submit" name="Submit" value=" 确 认 ">
          　
          <input type="reset" name="Submit2" value=" 重 置 ">
        </td>
      </tr>
    </table>
    </s:form>
    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>

</body>
</html>