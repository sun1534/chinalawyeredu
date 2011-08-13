<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%

%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<s:head /> 
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
</HEAD>
<script language="JavaScript" type="text/JavaScript">
function qicksub(domessage)
{
var form = document.forms['form1'];
form.domessage.value=domessage;
}
</script>

<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">诉讼案件&gt;&gt;请选择处理人</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD  bgColor=#F9F9F7>&nbsp;              </TD>
          </TR>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="caseDoSubmit" method="post">
<s:hidden name="caseid"/>
  <s:hidden name="nodeid"/>
    <s:hidden name="actionid"/>
      <s:hidden name="remarks"/>
        <s:hidden name="tonext"/>
  
              <br>
              <TABLE width="400"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                    <TR class="listheadline">
                      <TD colspan="2">请选择当前处理人</TD>
                    </TR>
                    <TR class=listline>
                 
                       <TD colspan="2">
                       <s:iterator value="roleusers" status="stat">
                       <input type="radio" name="userid" value="${comp_id.tsysUser.userid}">
                       ${comp_id.tsysUser.username}<br/>
                       </s:iterator>    
                       
                       
                   
                        
                   </TD>
                  </TR>
                   
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align=center>
                        <div align="center">
                          <input type=submit class="botton" value="确定">&nbsp;
                          <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
                        </div></TD>
                    </TR>
              </TABLE>
</s:form>
            <p>&nbsp;</p></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
