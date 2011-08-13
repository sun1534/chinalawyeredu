<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 创建feeapply</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-24</p>
 * @版本： V1.0
 * @修改：
**/ 
%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<s:head /> 
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
</HEAD>
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
              <td width="97%"><span class="sort-title">诉讼管理&gt;&gt;诉讼案件代理情况记录表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
             
<TABLE width="80%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
				
					<TR>
					<TD colspan="3" class="listline"><div align="center" class="sort-title">诉讼案件代理情况记录表</div></TD>
                    <TD class="listline"><div align="center" class="sort-title">合同编号：${lawcase.contractno}</div></TD>
			</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">案件诉讼承办人:</TD>
					  <TD width="35%" class="listline">${feeapply.department}</TD>
					  <TD width="20%" class="listheadline">状态:</TD>
					  <TD width="35%" class="listline">


					</TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">立案时间:</TD>
					  <TD width="35%" class="listline">${feeapply.username}</TD>
					  <TD width="20%" class="listheadline">立案缴费时间:</TD>
					  <TD width="35%" class="listline">费用借款</TD>
					</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">移交调解时间:</TD>
	 			 	  <TD class="listline">
<s:date name="feeapply.applydate" format="yyyy-MM-dd"/>
					 </TD>
					  <TD class="listheadline">调解人:</TD>
					  <TD class="listline">
<s:date name="feeapply.returndate" format="yyyy-MM-dd"/>
					 </TD>
	 			 	</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">调解时间:</TD>
					  <TD width="35%" class="listline">${feeapply.tfeeProject.projectname}</TD>
					  <TD width="20%" class="listheadline">移交承办人时间:</TD>
					  <TD width="35%" class="listline">${feeapply.tfeeProject.tprjType.typename}</TD>
					</TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">判决书签署时间:</TD>
					  <TD colspan="3" class="listline"> ${projectoption}</TD>
				    </TR>
	 			 	<TR>
					  <TD width="20%" class="listheadline">移交执行时间:</TD>
					  <TD width="35%" class="listline">
 ${feeapply.totalfee}
					  </TD>
					  <TD class="listheadline">执行承办人:</TD>
					  <TD class="listline">${feeapply.totalfeevalue} </TD>
	 			 	</TR>
<s:if test="feeapply.statusid!=0"></s:if> 
            </TABLE>     
            <br/>
                <TABLE width="80%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
				
					<TR>
					  <TD colspan="2" class="listheadline">原告或申请人:</TD>
					  <TD width="29%" colspan="5" class="listline">${feeapply.department}</TD>
					  <TD width="17%" rowspan="2" class="listheadline">被告或被申请人:</TD>
					  <TD width="32%" rowspan="2" class="listline"></TD>
					</TR>
	 			 	<TR>
					  <TD colspan="2" class="listheadline">案件受理机关:</TD>
					  <TD width="29%" colspan="5" class="listline">${feeapply.username}</TD>
				    </TR>
	 			 	<TR>
	 			 	  <TD colspan="2" class="listheadline">法官及联系电话:</TD>
	 			 	  <TD colspan="5" class="listline">
<s:date name="feeapply.applydate" format="yyyy-MM-dd"/>					 </TD>
					  <TD class="listheadline">助理及联系电话：</TD>
					  <TD class="listline">
<s:date name="feeapply.returndate" format="yyyy-MM-dd"/>					 </TD>
	 			 	</TR>
	 			 	<TR>
					  <TD colspan="2" class="listheadline">诉讼标的:</TD>
					  <TD width="29%" colspan="5" class="listline">${feeapply.tfeeProject.projectname}</TD>
					  <TD width="17%" class="listheadline">判决支付金额:</TD>
					  <TD width="32%" class="listline">${feeapply.tfeeProject.tprjType.typename}</TD>
					</TR>
	 			 	<TR>
					  <TD colspan="2" class="listheadline">抵押物:</TD>
					  <TD colspan="7" class="listline"> ${projectoption}</TD>
				    </TR>
	 			 	<TR>
					  <TD width="9%" class="listheadline">垫付费用</TD>
					  <TD width="13%" class="listheadline">诉讼或仲裁费</TD>
					  <TD class="listline"> </TD>
				      <TD class="listline">公告费</TD>
				      <TD class="listline"></TD>
				      <TD class="listline">保全费</TD>
				      <TD class="listline"></TD>
				      <TD class="listline">打单费</TD>
				      <TD class="listline"></TD>
	 			 	</TR>
<TR>
					  <TD colspan="2" class="listheadline">诉讼进度记录</TD>
					  <TD colspan="7" class="listline"> </TD>
				    </TR>
            </TABLE>
                
                <br>
                
                <br>
                
            <p>&nbsp;</p></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
<s:form name="editForm" action="traFeeapplyDoEdit.action" method="POST">
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
</s:form>
<s:form name="submitForm" action="feeapplySubmit.action" method="POST">
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
</s:form>
<s:form name="messageForm" action="feeapplyDoMessage!input.action" method="POST">
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
</s:form>
<s:form name="backForm" action="feeapplyDoBack.action" method="POST">
<s:hidden name="flag" value="selnode"/>
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
<s:hidden name="statusid" value="-1"/>
</s:form><s:form name="rejectForm" action="feeapplyDoReject.action" method="POST">
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
</s:form>
<s:form name="endForm" action="feeapplyDoEnd.action" method="POST">
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
<s:hidden name="statusid" value="2"/>
</s:form>
<s:form name="viewflowForm" action="feeapplyViewFlow.action" method="POST">
<s:hidden name="feeapplyid" value="${feeapply.feeapplyid}"/>
</s:form>
</BODY>
</HTML>
