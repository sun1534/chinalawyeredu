<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 创建nonlaw</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-18</p>
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
              <td width="97%"><span class="sort-title">非诉催收业务&gt;&gt;新增催收记录</span></td>
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
               <s:form name="form1" action="nonlawCreate" validate="true" method="post">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托银行</TD>
					<TD width="35%" class="listline">
					<s:select name="nonlaw.bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" listKey="bankid" listValue="bankname"/>
					</TD>	
					</TR>
					<TR>
						<TD width="15%" class="listheadline">支行名称</TD>
						<TD width="35%" class="listline">
						<s:textfield name="nonlaw.bankname"/>
						</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">客户姓名:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.username"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">借据号:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.duebill"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款账号:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.lendaccount"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">扣款账号:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.payaccount"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">合作项目:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.synergicname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证件号码:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.idcard"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款金额:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.lendfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款余额:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.balancefee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期本金:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.overfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期利息:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.accrualfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期罚息:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.castfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期期数:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.overstat"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">台帐逾期期数:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.overnum"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">每月还本:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.monthfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">每月罚息:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.breachfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款时间:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.lenddate"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款到期日:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.lendoverdate"/></TD>
					</TR>
	 			 	<!--  TR>
						<TD width="15%" class="listheadline">项目名称:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.projectname"/></TD>
					</TR-->
					<TR>
						<TD width="15%" class="listheadline">项目名称:</TD>
						<TD width="35%" class="listline"><s:select name="nonlaw.projectname" list="@com.changpeng.nonlaw.util.NonlawUtil@projectnameMap"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">购房地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.buyaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住址:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.homeaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住宅电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.homephoneold"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.mobileold"/></TD>
					</TR>
	 			 
	 			 	<TR>
						<TD width="15%" class="listheadline">单位名称:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.company"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">单位地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.companyaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">单位电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.companyphone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">提示电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="nonlaw.alterphone"/></TD>
					</TR>

	 			 	<TR>
						<TD width="15%" class="listheadline">委托时间:</TD>
						<TD width="35%" class="listline"><jscalendar:jscalendar	name="nonlaw.consigndate" format="%Y-%m-%d"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">客户描述:</TD>
						<TD width="35%" class="listline"><s:textfield name="nonlaw.comments"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">管贷信贷员:</TD>
						<TD width="35%" class="listline"><s:textfield name="nonlaw.lendadmin"/></TD>
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
