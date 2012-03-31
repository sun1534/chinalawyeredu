<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>中国律师培训网-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<body>
<form name="form1" method="post" action="../common/logout.pl">
<div class="header">

  <div class="logo left"><a href="index.html"><img src="../images/logo.gif" width="234" height="51" /></a></div>
  <div class="denglu right">${lawyer.lawyername} ，您好，欢迎您登录培训平台！&nbsp;&nbsp;  	
  	<a href="index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a></div>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="../index/index.pl"  class="current">首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">选课中心</a></li>
  <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1">培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>
  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl">个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1">系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="index.html">首页</a></div>
<div class="con">
	<div class="main_left left">
		<s:if test="tongzhi!=null">		
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED" >
			<tr>	      
	        	<td height="24"  class="baseFontBold"  align="left">重要通知：${tongzhi.title} 
	        	【<s:if test="tongzhi.thegroup==0">系统</s:if><s:else><s:property value="@com.changpeng.common.CommonDatas@groups[tongzhi.thegroup]"/></s:else>】
	        	</td>
			</tr>
			<tr>	         
	        	<td  class="tab_content">
	         		${tongzhi.content}
	        	</td>
	        </tr>
	    </table>
		<br/><br/>
		</s:if>
		<div class="main_left_title"><h2>最新培训</h2></div>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">   
			<tr>
				<TD width="40%" height="23" align="center" background="../imagesa/top-bg1.gif">培训标题</TD> 
				<TD width="10%" height="23" align="center" background="../imagesa/top-bg1.gif">主讲人</TD>
				<TD width="17%" align="center" background="../imagesa/top-bg1.gif">授课时间</TD>
				<TD width="9%" align="center" background="../imagesa/top-bg1.gif">学分</TD>
				<TD width="20%" align="center" background="../imagesa/top-bg1.gif">培训地点</TD>
			</tr>
			<s:iterator value="lessonList" status="statLesson">
			<tr>
				<TD class="tab_content" align="left">&nbsp;&nbsp;<a href="../lessons/lessonsView.pl?lessonid=${lessonid}">&nbsp;&nbsp;${title}</a></TD>
				<TD class="tab_content" align="center">${teachers}</TD>
				<TD class="tab_content" align="center"><s:date name="lessondate" format="yyyy-MM-dd HH:mm"/></TD>
				<TD class="tab_content" align="center">${xuefen}</TD>
				<TD class="tab_content" align="center"><s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/></TD>
			</tr>
			</s:iterator>  
		</table>      
		
	</div>
	<div class="main_right left">
		<div class="main_right_title">个人信息</div>   
		<table width="100%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td height="23" colspan="3"  align="left" background="../imagesa/top-bg1.gif">				
				<b>&nbsp;&nbsp;积分年度：<s:if test="lawyers.directunion!=37777">${jifentime.startstr} 至 ${jifentime.endstr}</s:if><s:else>2010-06-20至2011-04-20</s:else></b>				                </td>
			</tr>
			<tr>
				<td align="right"  width="75" height="20"  ><b>姓名：</b></td>
				<td align="left" > ${lawyer.lawyername}  </td>      
				<td width="120" rowspan="8" align="center" valign="middle"   >					
      		 		<s:if test="lawyer.photo==null||lawyer.photo.equals(\"\")"> 
      		 			<img src="../imagesa/none.jpg" width="106"/>       
      				</s:if>
      				<s:else>
      					<img src="${logopath}/${lawyer.photo}" width="106"/>    
      				</s:else>   
				</td>
			</tr>
			<tr>
				<td align="right"   height="23"><b>执业证： </b></td>                   
				<td align="left" >${lawyer.lawyerno}</td>  
			</tr>
			<tr>
				<td align="right"   height="23"><b>执业所：</b> </td>                   
				<td align="left" >
					  <s:property value="@com.changpeng.common.CommonDatas@groups[lawyer.theoffice]"/>
				</td>
			</tr>
			<tr>
				<td align="right"   height="23"><b>积分： </b> </td>                  
				<td align="left" >${nowxuefen}</td>
			</tr>
			<tr>
				<td align="right"   height="23"><b>达标分：</b> </td>                   
				<td align="left" >${needfen}分才能达标</td>
			</tr>
			<tr>
				<td align="right"   height="23"><b>执业时间： </b>   </td>                
				<td align="left" >${lawyer.zhiyedate}</td>
			</tr>
			<s:if test="lawyer.lawyertype>=0">
			<tr>
				<td align="right"   height="23"><b>是否达标：</b> </td>                   
				<td align="left" >
					<font color="red">
           			<s:if test="nowxuefen>=needfen">
           	 			已达标
            		</s:if>
             		<s:else>未达标
            		</s:else> 
            		</font>
				</td>
			</tr>
			</s:if>
			<tr>
				<td align="center"  height="10"> </td>                   
				<td align="left" ></td>
			</tr>     
		</table>
	</div>	  
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="#">长鹏软件</a><br/>
备案序号：粤ICP备05082150号
</div>
</form>
</body>
</html>