<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<html>
<head>
<title>管理员个人资料修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script>

function getDelete(userid){
	var url="../systemajax/photoDelete.pl";		
	 var pars = 'userid='+userid;
     var myAjax = new Ajax.Request(url,{method: 'post', parameters:pars, onComplete: {}});	
     event.srcElement.parentNode.innerHTML="<font color='red'>已删除</font>";
}
</script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
<td height="23" background="../imagesa/top-bg3.gif"	class="baseFontBold">
			个人资料信息修改
					
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0" align="center" class="border-table">
  <tr class="border-tr">
    <td>
    <s:form name="form1" action="lawyerEditSelf" method="post" validate="true" enctype="multipart/form-data">
      <table width="80%" border="0" cellspacing="1" cellpadding="1" align="center" class="content-table">
		<!--<tr>
          <td colspan="2">&nbsp;
          	<font color="#FF0000"><b>
         注意：登录名设置将设置为执业资格证号，登录密码为身份证号码
         </b></font>
         </td>
        </tr>-->
		<tr>
          <td align="right" class="tab_content1">
             所属事务所机构:
          </td>
          <td class="tab_content1">
${groupname}
           </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             您的姓名:
          </td>
          <td class="tab_content">
            <s:textfield name="sysUser.username" size="15" maxlength="15" cssClass="text1" required="true"/>
              <span class="hint">不为空且长度不超过7个汉字</span> </td>
        </tr>
        <!--<tr>
            <td align="right" class="tab_content1">您的执业证号: </td>
          <td class="tab_content1">
            <s:textfield name="sysUser.lawerno" size="20" maxlength="15" cssClass="text1" required="true"/>
             <span class="hint">不为空且长度不超过20个字符</span> </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 身份证号: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.certno" size="20" maxlength="20" cssClass="text1" required="true"/>
            <span class="hint">不为空且长度不超过20个字符</span>
          </td>
        </tr>

		<tr>
            <td align="right" class="tab_content1"> 您的培训卡卡号: </td>
          <td class="tab_content1">
            <s:textfield name="sysUser.cardno" size="25" maxlength="25" cssClass="text1"/>
           <font color=red>您不能对此进行设置</font>
            </td>
        </tr>
         <tr> 
            <td align="right" class="tab_content1">您的培训卡发卡日期: </td>
          <td class="tab_content1">
          <jscalendar:jscalendar name="sysUser.carddate" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
             <font color=red>您不能对此进行设置</font>
            </td>
        </tr>
        
 <tr> 
            <td align="right" class="tab_content1">您在深圳执业的执业日期: </td>
          <td class="tab_content1">
           
            <jscalendar:jscalendar name="sysUser.zhiyedate" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr>-->
		<tr>
            <td align="right" class="tab_content"> 您的手机号码: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.mobile" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
         <tr>
            <td align="right" class="tab_content"> 您的办公电话: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.officephone" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
		 <tr> 
            <td align="right" class="tab_content1">您的照片: </td>
          <td class="tab_content1">
          <s:if test="sysUser.photo!=null">
             <div>
          <img src="../lawyerphotos/${sysUser.photo}" width="106" border="0"/>
        	 <a href="#" onclick="return getDelete('${sysUser.userid}')" >删除照片</a>       
          <br/>
          </div>
          </s:if>
           <s:file name="upload" cssClass="text1"/><br/>
           <font color="#FF0000">
           请提供标准2寸身份证相片，大小控制在100K以内
           </font>
            </td>
        </tr>
       <tr> 
            <td align="right" class="tab_content">性别: </td>
          <td class="tab_content">
           <s:select name="sysUser.gender" list="#{'G':'男','M':'女'}"/>
            </td>
        </tr>
        <tr> 
            <td align="right" class="tab_content1">备注信息: </td>
          <td class="tab_content1">
            <s:textarea name="sysUser.comments" rows="4" cols="50" cssClass="textarea1"/>
            </td>
        </tr>		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 保 存 " cssClass="button1"/>&nbsp;
           	&nbsp;
          	<input type="button" value=" 返 回 " onClick="javascript:history.back(-1)" class="button1">
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>