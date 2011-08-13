<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%>
<%
/**
 * <p>功能： 导出催收记录(EXCEL)</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-14</p>
 * @版本： V1.0
 * @修改：
**/
%>
<%
ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
//String filename=(String)vs.findValue("filename");
String filename="cuishou.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename+"");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
<html xmlns:x="urn:schemas-microsoft-com:office:excel">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<style type="text/css">	
	body,td,th {
		font-size: 12px;
		line-height:16px;
		color: #000000;
	}
	.xl25{
		mso-style-parent:style0;
		color:black;
		font-size:9.0pt;
		font-family:Arial, sans-serif;
		mso-font-charset:0;
		border-top:none;
		border-right:.5pt solid black;
		border-bottom:.5pt solid black;
		border-left:none;
		background:white;
		mso-pattern:auto none;
		white-space:normal;
	}
	tr{border:1px solid #000000; }
	td{ border:1px solid #000000;}
</style>
</HEAD>

<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
 
                      <TR>
                        <TD>客户姓名</TD>
                        <TD >卡号</TD>
                        <TD>身份证</TD>
                        <TD>逾期金额</TD>
                         <TD>联系人</TD>
                         <TD>担保人</TD>
                        <TD >催收时间</TD>
                        <TD >催收记录</TD>
                        <TD >案件编号</TD>
                      </TR>
<s:iterator value="creditcardlist" status="status">
            <TR>
            <TD >${username}</TD>                      
            <TD x:str>${creditcard}</TD>
            <TD x:str>${idcard}</TD>
            <TD x:str>${curcnfee}</TD>
            <TD>${contactp1phone1}</TD>
            <TD>${cautioner}</TD>
            <s:iterator value="toprCreditlogs" status="stat">
            <TD >
            <s:property value="@com.changpeng.operation.util.OperationUtil@showDate(logtime)"/></TD>
                      
            </TD>
        	<TD >${comments}</TD>
        	   <TD>${bianhao}</TD>
         	</s:iterator>		
        </TR>
</s:iterator>

</TABLE>
</BODY>
</HTML>
