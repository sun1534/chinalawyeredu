<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-信息反馈</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
.STYLE1 {color: #FFFFFF}
-->
</style>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font"><img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：<%=com.changpeng.common.Constants.SYS_NAME%>&gt;&gt;信息反馈</td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="3" background="../imagesa/top-bg2.gif" class="baseFontBold">         </td>
        </tr>
      <tr>
        <td class="tab_content1" align="right" width="45%"><img src="../imagesa/sign.gif"></td>
        <td  class="tab_content1" align="left" valign="middle">
           <span class="message">
             ${message}
           </span>     
        </td>
      </tr>
      
      <tr>
        <td class="tab_content" colspan="2" align="center">
          <input name="button" type="button" class="button1" onClick="document.backForm.submit()" value=" 返 回 ">        </td>
        </tr>
    </table></td>
  </tr>
</table>
<form name="backForm" action="${nextPage}" method="post" target="_parent">
</form>
</body>
</html>