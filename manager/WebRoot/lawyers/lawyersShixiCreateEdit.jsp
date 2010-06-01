﻿<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>实习律师信息新增修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<jscalendar:head/>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}


function deletephoto(lawyerid){
if(confirm("您确实要删除这个照片吗?")){
var url="../lawyersajax/photoShixiDelete.pl";
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
function checkLoginname(loginname,lawyerid){	
	if(loginname == null || $.trim(loginname).length == 0){
	    $("#checkloginname").html("<font color='red'>不为空且长度不超过20个字符</font>");
	    $("#save").attr("disabled",true);
		return;
	}
	var now=new Date().getTime();
	var url="../systemajax/checkLawyersLoginname.pl";
   $.getJSON(url, { "lawyerid":lawyerid,"loginname": loginname,"now":now}, function(json){
     $("#lawyerloginname").attr("value",json.loginname);
     if(json.isrepeat == true){
   		$("#checkloginname").html("<font color='red'>对不起，您输入的帐号【"+json.loginname+"】已经被他人使用，请选择其他名字后再试。</font>");
   		$("#save").attr("disabled",true);
   }else{
        $("#checkloginname").html("");
	    $("#save").attr("disabled",false);
   }
});
}
</script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
					实习律师信息新增修改
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="lawyersShixiCreateEdit" method="post" validate="true" enctype="multipart/form-data">
      <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         注意：登录名设置将设置为实习律师资格证号，登录密码为身份证号码
         </b></font>
         
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             所属事务所:
          </td>
          <td class="tab_content1">
          <s:if test="isnew">
             <s:if test="datavisible.provinceview">
             <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             </s:if>
            <s:else>
              <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
           
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getOffices(this.value)"/>
            
              </s:if>
           <s:else>
              <s:hidden name="datavisible.cityid"/>
            </s:else>
                 <s:if test="datavisible.officeview">
             <s:select name="datavisible.officeid" id="office" list="datavisible.officelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
  </s:if>
            <s:else>
              <s:hidden name="datavisible.officeid"/>
                <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.officeid]"/>
              
            </s:else>
</s:if>
<s:else>
  <s:hidden name="datavisible.provinceid"/>
  <s:hidden name="datavisible.officeid"/>
    <s:hidden name="datavisible.cityid"/>
   <s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyersShixi.provinceunion]"/>&gt;
   <s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyersShixi.directunion]"/>&gt;
   <s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyersShixi.theoffice]"/>
</s:else>
<s:hidden name="isnew"/>
           </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             实习律师姓名:
          </td>
          <td class="tab_content">
            <s:textfield name="lawyersShixi.lawyername" size="15" maxlength="15" cssClass="text1" required="true"/>
              <span class="hint">不为空且长度不超过7个汉字</span> </td>
        </tr>
            <tr>
          <td align="right" class="tab_content1">实习律师资格证号: </td>
             <td class="tab_content1">
             <s:if test="isnew">
              <s:textfield name="lawyersShixi.zigeno" size="20" maxlength="20" cssClass="text1" onblur="checkLoginname(this.value,0)"/>
             </s:if>
             <s:else>
             <s:textfield name="lawyersShixi.zigeno" size="20" maxlength="20" cssClass="text1" onblur="checkLoginname(this.value,'%{lawyersShixi.lawyerid}')"/>
             </s:else>
             <span class="hint" id="checkloginname">不为空且长度不超过20个字符</span>
        </tr>
           <tr> 
            <td align="right" class="tab_content1">获取资格证证日期: </td>
          <td class="tab_content1">
           
            <jscalendar:jscalendar name="lawyersShixi.zigedate" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr>
        <tr>
          <td align="right" class="tab_content1">实习律师实习证号: </td>
             <td class="tab_content1">
              <s:textfield name="lawyersShixi.shixino" size="20" maxlength="20" cssClass="text1"/>
             <span class="hint">不为空且长度不超过20个字符</span>
        </tr>
           <tr> 
            <td align="right" class="tab_content1">获取实习证日期: </td>
          <td class="tab_content1">
           
            <jscalendar:jscalendar name="lawyersShixi.shixidate" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr>
    
		<tr>
            <td align="right" class="tab_content"> 身份证号: </td>
          <td class="tab_content">
            <s:textfield name="lawyersShixi.certno" size="20" maxlength="20" cssClass="text1" required="true"/>
            <span class="hint">不为空且长度不超过20个字符</span>
          </td>
        </tr>
        <tr>
            <td align="right" class="tab_content">民族: </td>
          <td class="tab_content">
            <s:textfield name="lawyersShixi.nation" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 手机号码: </td>
          <td class="tab_content">
            <s:textfield name="lawyersShixi.mobile1" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
         <tr> 
          <td align="right" class="tab_content1">实习律师照片: </td>
          <td class="tab_content">
          <s:if test="!isnew&&lawyersShixi.photo!=null&&!lawyersShixi.photo.equals(\"\")">
          <div id="imgdiv">
          <img src="${logopath}${lawyersShixi.photo}" width="150"/>
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
            <td align="right" class="tab_content">性别: </td>
          <td class="tab_content">
           <s:select name="lawyersShixi.gender" list="#{'G':'男','M':'女'}"/>
            </td>
        </tr>
      
        <tr> 
            <td align="right" class="tab_content1">个人经历: </td>
          <td class="tab_content1">
            <s:textarea name="lawyersShixi.remarks" rows="6" cols="70" cssClass="textarea1"/>
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
          	<input type="button" value=" 返 回 " onClick="javascript:history.back(-1)" class="button">
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>