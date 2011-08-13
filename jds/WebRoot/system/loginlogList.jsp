<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script language=javascript>
<!--

function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}

-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">系统管理&gt;&gt;登录日志</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="pageForm" action="loginlogList.action" method="POST">
<s:hidden name="pagenumber"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=12 >
			        
					  登录用户：<s:textfield name="loginname" size="10"/>
					  登录日期：<jscalendar:jscalendar	name="logintime" format="%Y-%m-%d"/>
					  <input type="submit" value="查询" class="botton"/>					
			           </TD>
			          </TR>
                      <TR class="listheadline">
                        <TD>登录人员</TD>
				        <TD>登录时间</TD>
				        <TD>退出时间</TD>
				        
				        <TD>登录IP</TD>
				        <TD>登录信息</TD>
				        <TD>退出信息</TD>
                      </TR>
<s:iterator value="loglist" status="status">
                      <TR class=listline>
                      	 <TD>${loginname} </TD>
				        <TD><s:date name="logintime" format="yyyy-MM-dd HH:mm:ss"/></TD>
				        <TD><s:date name="logouttime" format="yyyy-MM-dd HH:mm:ss"/></TD>
				       
				        <TD>${loginip}</TD>
				        <TD>${loginremarks}</TD>
				        <TD>${logoutremarks}</TD>
                    </TR>
</s:iterator>
${pagestring}

                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>

