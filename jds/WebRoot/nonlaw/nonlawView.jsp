<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看nonlaw</p>
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
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">非诉催收业务&gt;&gt;查看催收记录</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY >
	 			 	<TR>
						<TD width="15%" class="listheadline">委托银行:</TD>
						<TD width="35%" class="listline"><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[nonlaw.bankid+\"\"]"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">客户姓名:</TD>
						<TD width="35%" class="listline">${nonlaw.username}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">借据号:</TD>
						<TD width="35%" class="listline">${nonlaw.duebill}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款账号:</TD>
						<TD width="35%" class="listline">${nonlaw.lendaccount}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">扣款账号:</TD>
						<TD width="35%" class="listline">${nonlaw.payaccount}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">合作项目:</TD>
						<TD width="35%" class="listline">${nonlaw.synergicname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证件号码:</TD>
						<TD width="35%" class="listline">${nonlaw.idcard}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款金额:</TD>
						<TD width="35%" class="listline">${nonlaw.lendfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款余额:</TD>
						<TD width="35%" class="listline">${nonlaw.balancefee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期本金:</TD>
						<TD width="35%" class="listline">${nonlaw.overfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期利息:</TD>
						<TD width="35%" class="listline">${nonlaw.accrualfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期罚息:</TD>
						<TD width="35%" class="listline">${nonlaw.castfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期期数:</TD>
						<TD width="35%" class="listline">${nonlaw.overstat}</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">台帐逾期期数:</TD>
						<TD width="35%" class="listline">${nonlaw.overnum}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">每月还本:</TD>
						<TD width="35%" class="listline">${nonlaw.monthfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">每月罚息:</TD>
						<TD width="35%" class="listline">${nonlaw.breachfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款时间:</TD>
						<TD width="35%" class="listline">${nonlaw.lenddate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">贷款到期日:</TD>
						<TD width="35%" class="listline">${nonlaw.lendoverdate}</TD>
					</TR>
	 			 	<!--  TR>
						<TD width="15%" class="listheadline">项目名称:</TD>
						<TD width="35%" class="listline">${nonlaw.projectname}</TD>
					</TR-->
					<TR>
						<TD width="15%" class="listheadline">项目名称:</TD>
						<TD width="35%" class="listline"><s:property value="@com.changpeng.nonlaw.util.NonlawUtil@projectnameMap[nonlaw.projectname]"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">购房地址:</TD>
						<TD width="35%" class="listline">${nonlaw.buyaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住址:</TD>
						<TD width="35%" class="listline">${nonlaw.homeaddr}</TD>
					</TR>
					
	 			 	<TR>
						<TD width="15%" class="listheadline">住宅电话:</TD>
						<TD width="35%" class="listline">${nonlaw.homephoneold}</TD>
					</TR>					
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
						<TD width="35%" class="listline">${nonlaw.mobileold}</TD>
					</TR>
				
	 			 	<TR>
						<TD width="15%" class="listheadline">单位名称:</TD>
						<TD width="35%" class="listline">${nonlaw.company}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">单位地址:</TD>
						<TD width="35%" class="listline">${nonlaw.companyaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">单位电话:</TD>
						<TD width="35%" class="listline">${nonlaw.companyphone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">提示电话:</TD>
						<TD width="35%" class="listline">${nonlaw.alterphone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">状态:</TD>
						<TD width="35%" class="listline"><s:if test="creditcard.state==0">未分配</s:if><s:elseif test="creditcard.state==1">已分配</s:elseif></TD>
					</TR>
	 		
	 			 	<TR>
						<TD width="15%" class="listheadline">记录时间:</TD>
						<TD width="35%" class="listline"><s:date name="nonlaw.createtime" format="yyyy-MM-dd HH:mm:ss"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托时间:</TD>
						<TD width="35%" class="listline">${nonlaw.consigndate}</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">客户描述:</TD>
						<TD width="35%" class="listline">${nonlaw.comments }</TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">管贷信贷员:</TD>
						<TD width="35%" class="listline">${nonlaw.lendadmin }</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                      <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">
                      </TD>
                    </TR>
                  </TBODY>
                </TABLE>
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
   <s:form name="editForm" action="nonlawEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="nonlawDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="nonlawList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
