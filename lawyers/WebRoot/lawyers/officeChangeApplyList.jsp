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
	function getAdd(){
		window.location.href="officeChangeApply!input.pl";
	}


</script>
</head>
<body>

<div class="header">
  <form name="form1" method="post" action="../common/logout.pl">	
  <div class="logo left"><a href="index.html"><img src="${resourcePath}${topbarpic}" width="234" height="51" /></a></div>
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
  <li><a href="../jifen/jifenQuery.pl" >我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl" class="current">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>转所申请</strong></div>
<div class="con">
  <div class="con_left2 left">
     <div class="con_left2_title">转所申请</div>   
     <ul class="con_wz">
     	 <li><a href="../lawyers/officeChangeApplyList.pl">状态跟踪</a></li>
        <li><a href="../lawyers/officeChangeApply!input.pl" >发起申请</a></li>
       
      </ul>
  </div>
  <div class="con_right2 left">
    <div class="con_right2_title"><h2>转所申请</h2></div>
    <div class="star">
      <s:form action="officeChangeApplyList" name="form2" method="post">   	
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      
		<tr>
			<TD width="6%" height="23" align="center" background="../imagesa/top-bg1.gif">申请人</TD> 
            <TD width="27%" height="23" align="center" background="../imagesa/top-bg1.gif">原所在事务所</TD> 
     		<TD width="27%" align="center" background="../imagesa/top-bg1.gif">申请转所事务所</TD>
         	<TD width="10%" align="center" background="../imagesa/top-bg1.gif">申请时间</TD>
         	<TD width="10%" align="center" background="../imagesa/top-bg1.gif">当前状态</TD>
          	<TD width="10%" align="center" background="../imagesa/top-bg1.gif">处理时间</TD>
			<TD width="10%" align="center" background="../imagesa/top-bg1.gif">处理人</TD>
		</tr>
		<s:iterator value="page.items" status="stat">
      	<TR>
      		<TD class="tab_content" align="center">${applyname }</TD>
       		<TD class="tab_content" align="center"><s:property value="@com.changpeng.common.CommonDatas@groups[oldcity]"/>&gt;<s:property value="@com.changpeng.common.CommonDatas@groups[oldoffice]"/></TD>
         	<TD class="tab_content" align="center"><s:property value="@com.changpeng.common.CommonDatas@groups[newcity]"/>&gt;<s:property value="@com.changpeng.common.CommonDatas@groups[newoffice]"/></TD>
       		<TD class="tab_content" align="center"><s:date name="applyTime" format="yyyy-MM-dd HH:mm"/></TD>
       		<TD class="tab_content" align="center"><font color="red">${statusStr}</font></TD>
       		<TD class="tab_content" align="center">
       			<s:if test="status==0">
       			 <a href="officeChangeApplyCancel.pl?id=${id }">【取消】</a>
       			</s:if>
       			<s:else>
       				<s:date name="confirmTime" format="yyyy-MM-dd HH:mm"/>
       			</s:else>
       		</TD>
       		<TD class="tab_content" align="center">${confirmusername}</TD>
      	</TR>
    	</s:iterator>      
      	<tr style="background-color=#F1F1ED">
        	<td  colspan="7" align="center">&nbsp;</td>
      	</tr>
	  </table>
	  <table width="100%" border="0" cellpadding="0" cellspacing="1">
        <tr >
          <td height="24" align="center" background="../imagesa/login_bg1.gif" >
       		<input type="button" name="addforum" value="申请转所" onclick="getAdd()"/>
          </td>
        </tr>
      </table>   
	  
    		 <span>${page.pageView}
             <s:hidden name="pageNo"/>
             </span>
      
      </s:form>
    </div>    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>

</body>
</html>