<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 创建customer</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">

<jscalendar:head/>

<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
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
              <td width="97%"><span class="sort-title">客户管理&gt;&gt;新增个人客户管理</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="customer2Create" validate="true" method="post">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 			
	 			 	<TR>
						<TD width="15%" class="listheadline">姓名:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.username"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">单位:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.company"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.address" size="30"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">邮编:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.postcode" size="10"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">籍贯:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.nativeplace"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">生日:</TD>
					<TD width="35%" class="listline"><jscalendar:jscalendar name="customer.birthday" format="%Y-%m-%d"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.mobile"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.phone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">传真:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.fax"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">邮箱:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.email"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">QQ:</TD>
						<TD width="35%" class="listline"><s:textfield name="customer.qq"/></TD>
					</TR>
					
	 			 	<TR>
						<TD width="15%" class="listheadline">至交:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.linkman"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">至交手机:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.linkmobile"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">至交电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="customer.linkphone"/></TD>
					</TR>
	 			 
					
	 			 	<TR>
						<TD width="15%" class="listheadline">客户性质</TD>
					<TD width="35%" class="listline">
						<s:select name="customer.customerflag" list="#{1:'VIP',2:'一般'}"/>
						<s:hidden name="customer.customertype" value="2"/></TD>
					</TR>
	 			 
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
