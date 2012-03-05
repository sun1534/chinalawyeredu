<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<html>
<head>
<title>中国律师培训网-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jscalendar:head/>
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/datejs.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript"> 

function islocalclick(obj){
  if(obj.checked){
    $("#locallisttr").show();
    $("#titleidtr").hide();
       $("#budengdateid").attr("readonly",true);
       $("#applytitleid").attr("readonly",true);
       $("#xuefenid").attr("readonly",true);
  }
  else{
      $("#locallisttr").hide();
      $("#titleidtr").show();
       $("#budengdateid").attr("readonly",false);
       $("#applytitleid").attr("readonly",false);
       $("#xuefenid").attr("readonly",false);
  }
}
function selectlocal(lessonid){
  if(lessonid==0){
    alert("请选择您需要补登的对应现场课程");
  }else{
     if(lessonid!=0){
       $.getJSON("../lessonajax/getLessonsById.pl", { "lessonid": lessonid,"now":new Date().getTime()}, function(json){
          var exist=json.exist;
        //  alert(exist+",,,"+json.lessons);
          if(exist==1){
             $("#budengdateid").attr("value",json.lessons.lessondatestr);
              $("#applytitleid").attr("value",json.lessons.title);
               $("#xuefenid").attr("value",json.lessons.xuefen);
          }
       }); 
    }
  
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
  <li><a href="../jifen/jifenQuery.pl" class="current">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>我的学分</strong></div>
<div class="con">
    <div class="con_left3 left">
     <div class="con_left3_title">我的学分</div>   
     <ul class="con_wz">
     	<li><a href="../jifen/jifenQuery.pl" >学分统计</a></li>
     	<li><a href="../jifen/jifenbudengApplyList.pl">学分补登查询</a></li>
        <li><a href="../jifen/jifenbudengApply!input.pl">学分补登申请</a></li>
      </ul>
  </div>
  <div class="con_right5 left">
	<div class="con_right5_title"><h2>学分补登申请</h2></div>
    <div class="star4">
    <s:form name="form2" action="jifenbudengApply" method="post" validate="true">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b></b></font>
          </td>
        </tr>
		<tr>
        <td width="20%" class="tab_content" align="right">是否计为现场培训：</td>
        <td  class="tab_content1" align="left">
        	&nbsp;<s:checkbox name="budengApply.islocal" onclick="islocalclick(this)"/>
       		&nbsp;&nbsp;&nbsp;<font color='red'>如果计为现场培训,则补登的积分,在积分统计处,培训方式显示为"现场培训"</font>
        </td>
        </tr>
        <tr id="locallisttr" style="display:none">
        	<td  class="tab_content" align="right">选择现场课程：</td>
        	<td   class="tab_content1" align="left">
          		&nbsp;<s:select name="budengApply.lessonid" list="locallessonlist" listKey="lessonid" listValue="title" headerKey="0" headerValue="请选择" onchange="selectlocal(this.value)"/>
        	</td>
        </tr>
		<tr id="titleidtr">
        	<td  class="tab_content" align="right">内容标题：</td>
        	<td  class="tab_content1" align="left">
        		&nbsp;<s:textfield name="budengApply.title" size="40" id="applytitleid"/>
        	</td>
        </tr>
		<tr>
        	<td class="tab_content" align="right">补登学分：</td>
        	<td class="tab_content1" align="left">
       			&nbsp;<s:textfield name="budengApply.xuefen" size="10" id="xuefenid"/> 
       			&nbsp;&nbsp;&nbsp;<font color='red'>如果为现场课程，则积分为对应现场课程的积分</font>
        	</td>
        </tr>
        <tr>
        	<td  class="tab_content" align="right">计分日期：</td>
        	<td  class="tab_content1" align="left">
        		&nbsp;<s:textfield name="budengApply.budengdate" size="10" id="xuefenid" onfocus="SelectDate(this)" id="budengdateid"/> 
         		&nbsp;&nbsp;&nbsp;<font color='red'>为参加对应培训的时间</font>	
        	</td>
        </tr>
        
         <tr>
        	<td  class="tab_content" align="right">补登积分年度：</td>
        	<td  class="tab_content1" align="left">       
       			&nbsp;<s:select name="budengApply.theyear" list="jifentime.years"/> 
       			&nbsp;&nbsp;&nbsp;<font color='red'>补登的积分,将会计算到所选择的补登年度</font>
        	</td>
        </tr>
       	<tr>
			<td align="right" class="tab_content">申请补登理由: </td>
			<td class="tab_content1">
             	&nbsp;<s:textarea name="budengApply.applyreason" cols="40" rows="5"/>
             	&nbsp;&nbsp;&nbsp;<font color='red'>请输入您要求补登此次积分的理由，以便较快的通过审批</font>
			</td>
        </tr>		
        <tr>
          <td height="2" colspan="2"></td>
        </tr>

        <tr>
        	<td></td>	
          	<td  align="left">
          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:submit value=" 保 存 " cssClass="button" id="save"/>&nbsp;          
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