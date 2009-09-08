<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-系统管理-系统管理员列表</title>
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
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}

</script>
</head>
<body topmargin="0" leftmargin="0">
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
<s:form name="form1" action="sysLoginLogList" method="post">
<TR>
  <TD valign="top">
	 <table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="center" class="query-table">
	 <tr>
        <td align="left">
<!-- 根据登录时间段，登录人姓名进行查询(登录人姓名查询的方式显示,自动填充的,最好是) -->
              <s:hidden name="pageNo" value="1"/>
              <span style="font-weight: bold">
        	  &nbsp;
             登录日期:<jscalendar:jscalendar name="loginTime" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
             <s:submit value=" 查 询 " cssClass="button"/>
             </span>
        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr>
								<td height="24" align="right"
									background="../imagesa/login_bg1.gif">
									${page.pageView}
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bgcolor="#EDEDED">
     	<tr>
        <TD height="23" align="center" background="../imagesa/top-bg1.gif">登录人员</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">登录时间</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">退出时间</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">驻留时长(S)</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">登录IP</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">登录信息</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">退出信息</TD>
      
      </TR>
      <s:iterator value="page.items" status="stat">
      <TR onDblClick="javascript:window.location.href='sysOperLogList.pl?loginid=${loginid}'">
     
        <TD align="center" class="tab_content">${username} </TD>
        <TD align="center" class="tab_content" >${loginTime}</TD>
        <TD align="center" class="tab_content" >${logoutTime}</TD>
        <TD align="center" class="tab_content" >${inSysTime}</TD>
        <TD align="center" class="tab_content" >${loginip}</TD>
        <TD align="center" class="tab_content" >${loginremarks}</TD>
        <TD align="center" class="tab_content" >${remarks}</TD>
      </TR>
      </s:iterator>
      <tr background-color"#F1F1ED>
								<td colspan="7" align="center">
									&nbsp;
		</tr>
    </table>
    <TABLE cellSpacing="1" cellPadding="1" width="100%" align="center" border="0" class="content-table">  
      <TR>
         <TD align="center">&nbsp;
             
         </TD>
      </TR>
    </TABLE>
  </TD>
</TR>
</s:form>
</TABLE>   
</body>
</html>