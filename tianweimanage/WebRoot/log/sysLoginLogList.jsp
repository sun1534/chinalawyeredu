<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}-登录日志</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<jscalendar:head/>

<script language="javascript">
<!--

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit()
  return true;
}
function query(){
document.getElementById("totype").value="list";
document.form1.submit();
}
function exportit(){
document.getElementById("totype").value="excel";
document.form1.submit();
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
              <td width="97%"><span class="sort-title">日志管理&gt;&gt;管理员登录日志列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
				<s:form name="form1" action="sysLoginLogList" method="POST">	
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  	<tr class=listline >
                  	  <td align="center" colspan="16">
              
                 	<s:hidden name="pageNo"/>
                 
                    登录人员:<s:textfield name="username" size="10"/>
                    登录时间从:<jscalendar:jscalendar name="loginTime"/>
                  	         	<s:hidden name="totype" id="totype"/>
              <input type="button" value="查询" class="botton" onclick="query()"/>
                  	  <input type="button" value="导出" class="botton" onclick="exportit()"/>
                  	  </td>
                  	  </tr>
                  	  <TR class="listheadline">               
                     <TD>登录人员</th>
                      <TD>登录时间</TD>
                          <TD>退出时间</TD>
                      <TD>登录IP</TD>
                  
                      <TD>在线时长</TD>  
                           <TD>登录说明</TD>  
                           <TD>退出说明</TD> 
                      </TR>
					<s:iterator value="page.items" status="status">
                      <TR class=listline>
                   
                   
                       <TD>${username}</TD>
                       <TD>${loginTime }</TD>
                       
                       <TD>${logoutTime}</TD>
                       <TD>${loginip } </TD>
                        
                       <TD>${inSysTime}</TD>    
                        <TD>${loginremarks}</TD>       
                        <TD>${remarks}</TD>                                                                        
                        </TR>                        				
					</s:iterator>
             <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colspan="12">

  <div align="right" style="background-color:#FEF7E9">
  ${page.pageView}
</div>
                      </TD>
                    </TR>
<!--
                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
              -->
                
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