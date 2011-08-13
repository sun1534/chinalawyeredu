<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--

function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function query(){
	document.form1.action="customer3List.action";
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
              <td width="97%"><span class="sort-title">客户管理&gt;&gt;当事人客户列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="customer2Deletes.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class="listheadline">
                        <TD colspan="15">
                        客户姓名：<s:textfield name="username" size="10"/>
                   	 身份证：<s:textfield name="idcard" size="10"/>
                        公司名称：<s:textfield name="company" size="10"/>
                   
                       <input type="button" value="查询" onclick="query()"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                      <TD >姓名</TD>  
                      <TD >身份证</TD>
                      <TD >手机</TD>
                      <TD >住宅电话</TD>
                      <TD >公司电话</TD>
                      <TD >公司名称</TD>
            		  <TD >住宅地址</TD>
            		  <!--   <TD >联系人</TD>-->
            		    <TD >详细</TD>
            		 
            		    <TD >业务</TD>
                      </TR>
<s:iterator value="customerlist" status="status">
                      <TR class=listline>
                       <TD >${username}</TD>
                      <TD >${idcard}</TD>
                      <TD >${mobile1}</TD>
                      <TD >${homephone}</TD>
                       <TD >${compphone}</TD>
                      <TD >${company}</TD>
                     <TD >${homeaddr}</TD>
            		<!--       
            		<TD ><a href="../address/addressList.action?customerid=${customerid }">联系人</a></TD>
                    -->
                      <TD ><a href="customer3View.action?customerid=${customerid }">详细</a></TD>
                       <TD ><a href="customer3ServiceList.action?customerid=${customerid }">业务</a></TD>
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
<s:form name="pageForm" action="customer3List.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>

