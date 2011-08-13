<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 编辑creditcard</p>
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

<jscalendar:head/>

</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=15 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;编辑催收记录</span></td>
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
                  <TBODY>
               <s:form name="form1" action="creditcardEdit" validate="true" method="post">
	 			 	<!--
	 			 	<TR>
						<TD width="15%" class="listheadline">主键:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.creditcardid"/></TD>
					</TR>
	 			 	-->
	 			 	<TR>
						<TD width="15%" class="listheadline">委托银行</TD>
					<TD width="35%" class="listline">
					<s:select name="creditcard.bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" listKey="bankid" listValue="bankname"/>
					
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">客户姓名:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.username"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">账号:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.creditcard"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">证件号码:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.idcard"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托日期:</TD>
					<TD width="35%" class="listline"><jscalendar:jscalendar	name="creditcard.consigndate" format="%Y-%m-%d"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支人民币:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.cnfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支美元:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.usafee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支港元:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.hkfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">透支欧元:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.eurfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">逾期状态:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.overstat"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">授信额度:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.maxfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托类型:</TD>
					<TD width="35%" class="listline"><s:select name="creditcard.consigntype" list="@com.changpeng.operation.util.OperationUtil@consigntypeMap"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托类别:</TD>
					<TD width="35%" class="listline"><s:select name="creditcard.consignflag" list="@com.changpeng.operation.util.OperationUtil@consignflagMap"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">申请编号:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.applynum"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">卷宗号:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.dossiernum"/></TD>
					</TR>
				
	 			 	<TR>
						<TD width="15%" class="listheadline">手机:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.mobileold"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住宅电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.homephoneold"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">工作电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.workphoneold"/></TD>
					</TR>
					
	 			 	<TR>
						<TD width="15%" class="listheadline">公司名称:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.company"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公司地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.compaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">住宅地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.homeaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">身份证地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.idcardaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">账单邮编:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.billpostcode"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">对账单地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.billaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">通邮地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.billpostaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">email:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.email"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactpeople1"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactp1phone1"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactp1phone2"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人1电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactp1phone3"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactpeople2"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactp2phone1"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactp2phone2"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系人2电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.contactp2phone3"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.cautioner"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人公司:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.caucompany"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人公司地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.caucompaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人住宅地址:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.cauhomeaddr"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人工作电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.cauworkphone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人住宅电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.cauhomephone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">担保人手机:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.caumobile"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">开户日期:</TD>
					<TD width="35%" class="listline"><jscalendar:jscalendar	name="creditcard.kaihudata" format="%Y-%m-%d"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">柜员号:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.guiyuanno"/></TD>
					</TR>
					
					<TR>
						<TD width="15%" class="listheadline">退单:</TD>
					<TD width="35%" class="listline"><s:select name="creditcard.tdflag" list="#{0:'正常',1:'伪冒办卡退单',2:'银行核销退单',3:'银行冲减退单',4:'正常退单'}"/></TD>
					</TR>
					<TR>
						<TD width="15%" class="listheadline">案件编号:</TD>
					<TD width="35%" class="listline"><s:textfield name="creditcard.bianhao"/></TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
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
