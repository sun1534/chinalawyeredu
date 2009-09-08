<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>参数新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../css/css.css" rel="stylesheet" type="text/css">

</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font">
    <img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：系统管理 &gt; 参数管理 &gt;参数新增 </td>
  </tr>
</table>
<s:form action="sysParameterCreate" method="post" name="form1">
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"><div align="left">　</div>
        </td>
        </tr>
      <tr>
        <td width="37%" class="tab_content" align="right">参数名：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="sysParameter.paramname"/></td>
        </tr>
      <tr>
       <tr>
        <td width="37%" class="tab_content" align="right">参数值：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textfield name="sysParameter.paramvalue"/>
        	</td>
        </tr>
      <tr>
       <tr>
        <td width="37%" class="tab_content" align="right">参数说明：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left"><s:textarea name="sysParameter.comments" rows="4" cols="50"/></td>
        </tr>
      <tr>
        <td class="tab_content1"></td>
        <td colspan="2" class="tab_content1">　
         
          <input type="submit" name="Submit" value=" 保存 "/>
          　 
          <input type="reset" name="Submit2" value=" 重置 "/>
          　
          <input type="button" name="Submit3" value=" 返回 " onClick="javascript:history.go(-1)">
          </td>
        </tr>
    </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>