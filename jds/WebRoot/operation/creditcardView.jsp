<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看creditcard</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;查看催收记录</span></td>
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
						<TD width="35%" class="listline"><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[creditcard.bankid+\"\"]"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">客户姓名:</TD>
						<TD width="35%" class="listline">${creditcard.username}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">账号:</TD>
						<TD width="35%" class="listline">${creditcard.creditcard}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证件号码:</TD>
						<TD width="35%" class="listline">${creditcard.idcard}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托日期</TD>
						<TD width="35%" class="listline">${creditcard.consigndate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支人民币:</TD>
						<TD width="35%" class="listline">${creditcard.cnfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支美元:</TD>
						<TD width="35%" class="listline">${creditcard.usafee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支港元:</TD>
						<TD width="35%" class="listline">${creditcard.hkfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支欧元:</TD>
						<TD width="35%" class="listline">${creditcard.eurfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期状态:</TD>
						<TD width="35%" class="listline">${creditcard.overstat}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">授信额度:</TD>
						<TD width="35%" class="listline">${creditcard.maxfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托类型:</TD>
						<TD width="35%" class="listline"><s:property value="@com.changpeng.operation.util.OperationUtil@consigntypeMap[creditcard.consigntype]"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托类别:</TD>
						<TD width="35%" class="listline"><s:property value="@com.changpeng.operation.util.OperationUtil@consignflagMap[creditcard.consignflag]"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">申请编号:</TD>
						<TD width="35%" class="listline">${creditcard.applynum}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">卷宗号:</TD>
						<TD width="35%" class="listline">${creditcard.dossiernum}</TD>
					</TR>
				
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
						<TD width="35%" class="listline">${creditcard.mobileold}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住宅电话:</TD>
						<TD width="35%" class="listline">${creditcard.homephoneold}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">工作电话:</TD>
						<TD width="35%" class="listline">${creditcard.workphoneold}</TD>
					</TR>
					
	 			 	<TR>
						<TD width="15%" class="listheadline">公司名称:</TD>
						<TD width="35%" class="listline">${creditcard.company}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司地址:</TD>
						<TD width="35%" class="listline">${creditcard.compaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住宅地址:</TD>
						<TD width="35%" class="listline">${creditcard.homeaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">身份证地址:</TD>
						<TD width="35%" class="listline">${creditcard.idcardaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">账单邮编:</TD>
						<TD width="35%" class="listline">${creditcard.billpostcode}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">对账单地址:</TD>
						<TD width="35%" class="listline">${creditcard.billaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">通邮地址:</TD>
						<TD width="35%" class="listline">${creditcard.billpostaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">email:</TD>
						<TD width="35%" class="listline">${creditcard.email}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1:</TD>
						<TD width="35%" class="listline">${creditcard.contactpeople1}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1电话:</TD>
						<TD width="35%" class="listline">${creditcard.contactp1phone1}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1电话:</TD>
						<TD width="35%" class="listline">${creditcard.contactp1phone2}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1电话:</TD>
						<TD width="35%" class="listline">${creditcard.contactp1phone3}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2:</TD>
						<TD width="35%" class="listline">${creditcard.contactpeople2}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2电话:</TD>
						<TD width="35%" class="listline">${creditcard.contactp2phone1}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2电话:</TD>
						<TD width="35%" class="listline">${creditcard.contactp2phone2}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2电话:</TD>
						<TD width="35%" class="listline">${creditcard.contactp2phone3}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人:</TD>
						<TD width="35%" class="listline">${creditcard.cautioner}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人公司:</TD>
						<TD width="35%" class="listline">${creditcard.caucompany}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人公司地址:</TD>
						<TD width="35%" class="listline">${creditcard.caucompaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人住宅地址:</TD>
						<TD width="35%" class="listline">${creditcard.cauhomeaddr}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人工作电话:</TD>
						<TD width="35%" class="listline">${creditcard.cauworkphone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人住宅电话:</TD>
						<TD width="35%" class="listline">${creditcard.cauhomephone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人手机:</TD>
						<TD width="35%" class="listline">${creditcard.caumobile}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">状态:</TD>
						<TD width="35%" class="listline"><s:if test="creditcard.state==0">未分配</s:if><s:elseif test="creditcard.state==1">已分配</s:elseif></TD>
					</TR>
	 				<TR>
						<TD width="15%" class="listheadline">退单日期:</TD>
						<TD width="35%" class="listline">${creditcard.tddate}</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                      <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      <input name="back" type=button class="mask" onClick="history.go(-1)" value="返回">
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
   <s:form name="editForm" action="creditcardEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="creditcardDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="creditcardList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
