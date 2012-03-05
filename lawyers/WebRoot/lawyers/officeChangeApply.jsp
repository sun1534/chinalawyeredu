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

function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  

  if(vallll!=0){
     $.getJSON("../lawyersajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     

        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
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
    <div class="con_right2_title"><h2>发起申请转所</h2></div>
    <div class="star">
    <s:form name="form2" action="officeChangeApply" method="post">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         </b></font>
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             请现在所在的事务所:&nbsp;
          </td>
          <td class="tab_content1">
			&nbsp;<s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.provinceunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.directunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.theoffice]"/>
           </td>
        </tr>
            <tr>
          <td align="right" class="tab_content1"> 请选择新的事务所:&nbsp;</td>
             <td class="tab_content1">
             <s:hidden name="isedit"/>
            &nbsp;<s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.provinceunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.directunion]"/>
         <s:select name="changeApply.newcity" list="citylist" listKey="groupid" listValue="groupname" onchange="getOffices(this.value)"/>
         ->
         <s:select name="changeApply.newoffice" list="officelist" listKey="groupid" listValue="groupname" id="office"/>
              </td>
        </tr>
              
       <tr>
          <td align="right" class="tab_content1">申请理由:&nbsp;</td>
             <td class="tab_content1">
             &nbsp;<s:textarea name="changeApply.applyReason" cols="40" rows="5"/>
              </td>
        </tr>
		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 保 存 " cssClass="button" id="save"/>&nbsp;
           	&nbsp;

          </td>
        </tr>
      </table>
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