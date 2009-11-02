<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-部门修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>

</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="images/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>		
    <td>
    <s:form name="form1" action="sysGroupEdit" method="post" validate="true">
      <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">
          	&nbsp;
          	<s:hidden name="sysGroup.parentid"/>
          	<s:hidden value="groupid"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content1">
             上级部门名称:
          </td>
          <td class="tab_content1">
             ${parentname}
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             部门名称:
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.groupname" size="30" maxlength="50" cssClass="text1"/>
            <span class="hint">必须为有效字符并长度不超过25个汉字</span>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content1">
             事务所执业证号:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.groupenname" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             联系人:
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.contacter" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right" class="tab_content1">
             联系电话:
          </td>
          <td class="tab_content1">
            <s:textfield name="sysGroup.phone" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
                <tr>
          <td align="right" class="tab_content">
             传真:
          </td>
          <td class="tab_content">
            <s:textfield name="sysGroup.fax" size="20" maxlength="20" cssClass="text1"/>
          </td>
        </tr>
        <tr>
          <td align="right"   class="tab_content1">
           所在地址:
          </td>
          <td class="tab_content1">
             <s:textarea name="sysGroup.comments" cssClass="textarea1" cols="40" rows="4"/>
             <span class="hint">必须为有效字符并长度不超过50个汉字</span>
          </td>
        </tr>
        <tr>
          <td height="5" colspan="2">
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<input type="submit" value=" 保 存 " class="button">&nbsp;
           	<input type="reset" value=" 重 置 " class="button">&nbsp;
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