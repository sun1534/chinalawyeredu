<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${webpara.sysname}</title>
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
  <div class="logo left"><img src="${resourcePath}${webpara.topbarpic}" width="234" height="51" /></div>
  <div class="denglu right">
  	${lawyers.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp; 
  	<a href="../index/index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyers.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
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
  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl"  class="current">个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>个人信息</strong></div>
<div class="con">
  <div class="con_left2 left">
     <div class="con_left2_title">个人信息</div>   
     <ul class="con_wz">
     	<li><a href="../lawyers/lawyersEditSelf!input.pl" >个人资料维护</a></li>
        <li><a href="../common/passwdChange!input.pl">密码修改</a></li>
      </ul>
  </div>
  <div class="con_right6 left">
    <div class="con_right2_title"><h2>个人资料维护</h2></div>
    <div class="star2">
      <s:form name="form2" action="lawyersEditSelf" method="post" validate="true" enctype="multipart/form-data">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>       
         </b></font>
         
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             您的所属事务所:
          </td>
          <td class="tab_content1">
<s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.provinceunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.directunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.theoffice]"/>
           </td>
        </tr>
            <tr>
          <td align="right" class="tab_content1"> 您的执业证号: </td>
             <td class="tab_content1">
              ${lawyers.lawyerno}
              </td>
        </tr>
                <tr id="loginnameid" style="display:none">
          <td align="right" class="tab_content1"> 您的登录账号: </td>
             <td class="tab_content1">
               ${lawyers.loginname}
               </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             您的姓名:
          </td>
          <td class="tab_content">
            <s:textfield name="lawyers.lawyername" size="15" maxlength="15" cssClass="text1" required="true"/>
              &nbsp;<span class="hint"><font color="#FF0000">不为空且长度不超过7个汉字</font></span> </td>
        </tr>

        
		<tr>
            <td align="right" class="tab_content"> 您的身份证号: </td>
          <td class="tab_content">
            <s:textfield name="lawyers.certno" size="20" maxlength="18" cssClass="text1" required="true"/>
            &nbsp;<span class="hint"><font color="#FF0000">不为空且长度不超过18个字符</font></span>
          </td>
        </tr>
		
	
       <tr> 
            <td align="right" class="tab_content1">您在律协执业日期: </td>
          <td class="tab_content1">
           
            <jscalendar:jscalendar name="lawyers.zhiyedatestr" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 您的手机号码: </td>
          <td class="tab_content">
            <s:textfield name="lawyers.mobile1" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
         <tr> 
          <td align="right" class="tab_content1">您的照片: </td>
          <td class="tab_content">
        
          <s:if test="lawyers.photo!=null&&!lawyers.photo.equals(\"\")">
          <div id="imgdiv">
          <img src="${logopath }/${lawyers.photo}" width="150"/>
          <a href="#" onclick="deletephoto('${lawyerid}')"/>删除照片</a>
          </div>
          </s:if>
          
           <s:file name="upload" cssClass="text1"/>
           <br><font color="#FF0000">
          请提供标准2寸身份证相片,照片格式为:宽为120像素,高为160像素
           </font>
            </td>
        </tr>
		 <tr> 
            <td align="right" class="tab_content">您的性别: </td>
          <td class="tab_content">
           <s:select name="lawyers.gender" list="#{'G':'男','M':'女'}"/>
            </td>
        </tr>
       
        <tr> 
            <td align="right" class="tab_content1">备注信息: </td>
          <td class="tab_content1">
            <s:textarea name="lawyers.remarks" rows="4" cols="50" cssClass="textarea1"/>
            </td>
        </tr>
		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:if test="lawyers.provinceunion!=22">
          	<s:submit value=" 保 存 " cssClass="button" id="save"/>&nbsp;&nbsp;
          	</s:if>
          	<s:else>
          		<font color="red">对不起,广西律协的律师资料来自于律管平台,不能修改</font>
          	</s:else>

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