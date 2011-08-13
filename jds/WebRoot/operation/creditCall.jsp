<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能：  CALL CENTER 呼叫中心链接</p>
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

<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="../js/dtree/dtree.js"></script>
<link href="../js/dtree/dtree.css" rel="stylesheet" type="text/css">
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;呼叫中心</span></td>
            </tr>
          </table>
      </div>
      <div style="font-size:12px;color:red">说明：用户红色表示联系不上,绿色表示无法接通,蓝色表示联系亲友,红粗表示已全清</div>
      </TD>
  </TR>
  <TR>
      <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="middle" valign="top" bgColor=#F9F9F7>
        	 <div class="dtree" style="border:1px solid #666;valign:top;width:150px;height:400px;overflow:auto;overflow-x:hidden;float:left;text-align:left">
	         <script language="javascript">
	         	d = new dTree('d');
	         	d.add('0','-1','委托银行');
	         	<s:iterator value="@com.changpeng.operation.util.OperationUtil@listBank()" status="stat">	         	
				    d.add("${bankid}",0,"${bankname}");
				</s:iterator>
				<s:iterator value="tasklist" status="status">
					<s:if test="toprCreditcard.repaystatus==2">
						d.add("${credittaskid}${toprCreditcard.bankid}","${toprCreditcard.bankid}","<font color='red'><B>${toprCreditcard.username}</B></font>","creditUserView.action?creditcardid=${toprCreditcard.creditcardid}&credittaskid=${credittaskid}","","mainDiv");					
					</s:if>
					
					<s:elseif test="canlink==\"n\"">
						d.add("${credittaskid}${toprCreditcard.bankid}","${toprCreditcard.bankid}","<font color='red'>${toprCreditcard.username}</font>","creditUserView.action?creditcardid=${toprCreditcard.creditcardid}&credittaskid=${credittaskid}","","mainDiv");					
					</s:elseif>
					<s:elseif test="canlink==\"nn\"">
						d.add("${credittaskid}${toprCreditcard.bankid}","${toprCreditcard.bankid}","<font color='green'>${toprCreditcard.username}</font>","creditUserView.action?creditcardid=${toprCreditcard.creditcardid}&credittaskid=${credittaskid}","","mainDiv");					
					</s:elseif>
					<s:elseif test="canlink==\"yy\"">
						d.add("${credittaskid}${toprCreditcard.bankid}","${toprCreditcard.bankid}","<font color='blue'>${toprCreditcard.username}</font>","creditUserView.action?creditcardid=${toprCreditcard.creditcardid}&credittaskid=${credittaskid}","","mainDiv");					
					</s:elseif>
					
					<s:else>
						d.add("${credittaskid}${toprCreditcard.bankid}","${toprCreditcard.bankid}","${toprCreditcard.username}","creditUserView.action?creditcardid=${toprCreditcard.creditcardid}&credittaskid=${credittaskid}","","mainDiv");					
					</s:else>
				</s:iterator>
	         	document.write(d);
				d.openAll();
	         </script>
         </div>
         <div>
         	<iframe src="#" id="mainDiv" name="mainDiv" width="100%"  frameborder="0" height="550px" scrolling="auto"></iframe>
         </div>
         </TD>
         </TR>
         </TABLE>
	</div>
	</TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="credittaskList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
