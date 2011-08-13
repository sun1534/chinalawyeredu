<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%>
<%
/**
 * <p>功能： 导出催收记录(WORD)</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-13</p>
 * @版本： V1.0
 * @修改：
**/
%>
<%
ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
//String filename=(String)vs.findValue("filename");
String filename="cuishou.doc";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename+"");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<style type="text/css">
	
		body,td,th {
			font-size: 12px;
			line-height:16px;
			color: #000000;
		}
	
	</style>

</HEAD>
<BODY >
<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="20" align="center" style="font:bold" colspan="6">信用卡迟缴催收记录</td>
  </tr>

  <tr>
    <td width="5%" rowspan="5" align="left" bgcolor="#FFFFFF" class="td">持卡人资料</td>
    <td width="31%" align="left" bgcolor="#FFFFFF" class="td">姓名：${creditcard.username}</td>
    <td width="31%" align="left" bgcolor="#FFFFFF" class="td">
    	透支金额：
    	<s:if test="creditcard.curcnfee!=null">${creditcard.curcnfee}（人民币）</s:if>
    	<s:if test="creditcard.curusafee!=null">${creditcard.curusafee}（美元）</s:if>
    	<s:if test="creditcard.curhkfee!=null">${creditcard.curhkfee}（港元）</s:if>
    	<s:if test="creditcard.cureurfee!=null">${creditcard.cureurfee}（欧元）</s:if>
    </td>
    <td width="31%" align="left" colspan="2" bgcolor="#FFFFFF" class="td">信用额度：${creditcard.maxfee}</td>
  </tr>
  <tr>
    <td align="left" bgcolor="#FFFFFF" class="td">卡号：${creditcard.creditcard}</td>
    <td align="left" bgcolor="#FFFFFF" class="td">身份证号：${creditcard.idcard}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">透支天数：<s:property value="@com.changpeng.operation.util.OperationUtil@consignflagMap[creditcard.consignflag]"/></td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="#FFFFFF" class="td">住址：${creditcard.homeaddr}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">宅电：${creditcard.homephoneold}</td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="#FFFFFF" class="td">单位名称：${creditcard.company}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">单电：${creditcard.workphoneold}</td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="#FFFFFF" class="td">单位地址：${creditcard.compaddr}</td>
    <td align="left" bgcolor="#FFFFFF" colspan="2" class="td">手机:${creditcard.mobileold}</td>
  </tr>

 	<tr>
 	  <td width="10%" rowspan="2" align="left" bgcolor="#FFFFFF" class="td">其他卡号资料</td>
 	  <td width="30%" align="left" bgcolor="#FFFFFF" class="td">卡号：</td>
 	  <td width="30%" align="left" bgcolor="#FFFFFF" class="td">欠款金额：</td>
 	  <td width="30%" align="left" colspan="2" bgcolor="#FFFFFF" class="td">其他电话：</td>
  </tr>
 	<tr>
    <td colspan="4" align="left" bgcolor="#FFFFFF" class="td">EMAIL:${creditcard.email}</td>
  </tr>

     <tr>
       <td width="10%" align="left" bgcolor="#FFFFFF" class="td">担保人/联系人</td>
       <td width="30%" align="left" bgcolor="#FFFFFF" class="td">姓名：${creditcard.cautioner}</td>
       <td width="30%" align="left" bgcolor="#FFFFFF" class="td">身份证号：</td>
       <td width="30%" align="left" bgcolor="#FFFFFF" colspan="2" class="td">联系电话：${creditcard.cauworkphone}&nbsp;${creditcard.cauhomephone}&nbsp;${creditcard.caumobile}</td>
     </tr>
   <tr>
    <td width="10%" align="left" bgcolor="#FFFFFF" class="td">银行资料</td>
    <td width="21%" align="left" bgcolor="#FFFFFF" class="td">经办网点：</td>
    <td width="23%" align="left" bgcolor="#FFFFFF" class="td">经办人：</td>
    <td width="23%" align="left" bgcolor="#FFFFFF" class="td">电话：</td>
    <td width="23%" align="left" bgcolor="#FFFFFF" class="td">代码:${creditcard.guiyuanno}</td>
  </tr>

     <tr>
       <td width="10%" rowspan="<s:property value="logList.size+1"/>" align="left" bgcolor="#FFFFFF" class="td">催收记录</td>
       <td align="center" bgcolor="#FFFFFF" class="td">日期</td>
       <td align="center" bgcolor="#FFFFFF" class="td" colspan="4">催收情况</td>
     </tr>
     <s:iterator value="logList" status="stat">
      <tr>
        <td width="18%" align="center" bgcolor="#FFFFFF" class="td">${logtime}</td>
       	<td width="72%" align="left" bgcolor="#FFFFFF" colspan="4" class="td">&nbsp;${comments}</td>
  	  </tr>
     </s:iterator>

   <tr>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">是否结清</td>
    <td width="20%" align="left" bgcolor="#FFFFFF" class="td">是□    否□</td>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">损失原因</td>
    <td width="50%" align="left" bgcolor="#FFFFFF" colspan="3" class="td">冒用、伪造证件□   恶意透支□   无法联系□   经济困难□</td>
  </tr>
  <tr>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">催收建议</td>
    <td width="85%" align="left" bgcolor="#FFFFFF" class="td" colspan="5">继续开卡□              停用卡□               额度降半□</td>
  </tr>
  <tr>
    <td width="15%" align="left" bgcolor="#FFFFFF" class="td">户籍：</td>
    <td width="85%" align="left" bgcolor="#FFFFFF" class="td" colspan="5">上门情况：</td>
  </tr>
</table>
催收员1签名：&nbsp;&nbsp;&nbsp;&nbsp;催收员2签名：
</BODY>
</HTML>
