<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>信息修改</title>
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
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="lawyersEditSelf" method="post" validate="true" enctype="multipart/form-data">
      <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
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
              <span class="hint">不为空且长度不超过7个汉字</span> </td>
        </tr>

        
		<tr>
            <td align="right" class="tab_content"> 您的身份证号: </td>
          <td class="tab_content">
            <s:textfield name="lawyers.certno" size="20" maxlength="20" cssClass="text1" required="true"/>
            <span class="hint">不为空且长度不超过20个字符</span>
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
    </td>
  </tr>
</table>
</body>
</html>