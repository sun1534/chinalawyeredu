<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>课程听课记录列表</title>
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
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}


</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置： 课程听课记录列表
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
	<s:form name="form1" action="lessonListenList" method="post">
  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr>
          <td height="24"  >
             <s:hidden name="pageNo" value="1"/>
             <s:hidden name="lessonid"/>
             <!-- 
        	 <s:textfield name="loginname" size="12" label="登录名"/>
             <s:textfield name="username" size="8" label="姓名"/> 
              
             <s:submit value=" 查 询 " cssClass="button"/>
              -->
          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr  class="list_tdfunc">
          <td height="24" align="right" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
      	 	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">姓名</TD>
      	 	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">律协</TD>
      	 	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">事务所</TD>
       	  <TD align="center" background="../imagesa/top-bg1.gif">初次听课时间</TD>
    
      </tr>
      
	<s:iterator value="page.items" status="stat">
      <TR class="list_td01">
        <TD align="center">${lawyers.lawyername}</TD>
        <TD align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyers.directunion]"/></TD>
        <TD align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyers.theoffice]"/></TD>
        <TD align="center">${firsttime}</TD>
      
      </TR>
      </s:iterator>
      <tr class="list_td01">
        <td  colspan="11" align="center">&nbsp;       </td>
      </tr>
      
    </table>
      </td>
  </tr>
    </s:form>
</table>
</body>
</html>