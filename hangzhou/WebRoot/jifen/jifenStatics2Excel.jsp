<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-积分统计列表</title>
<style type="text/css">
<!--
.listheadline {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #919191}
.listline {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #EFEFEF
}
.listline1 {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #CCCCFF}
.listline2 {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #FFCC99}
.listline3 {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #9999CC}
-->
</style>
<%
//ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
//String filename=(String)vs.findValue("filename");
String filename="loginlog.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename);
//response.addHeader("Content-Disposition","attachment; filename=hello.xsl");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>
  <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
      <thead>
      <tr >
     
        <th class="listheadline">姓名</th>
        <th class="listheadline">事务所</th>
        <th class="listheadline">现场积分</th>
        <th class="listheadline">网上积分</th>
        <th class="listheadline">文本课件</th>
        <th class="listheadline">补登积分</th>
        <th class="listheadline">未到扣分</th>
        <th class="listheadline">总积分</th>
        <th class="listheadline">是否达标</th>
   
      </tr>
      </thead>
      <tbody>
      <s:iterator value="page.items" status="stat">
      <tr>
    
        <th class="listline2">${id.username}</th>
        <td class="listline2">${id.groupname}</td>
          <td class="listline2"><s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.xianchang)"/></td>
        <td class="listline2">	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.video)"/></td>
          <td class="listline2">	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.doc)"/></td>
            <td class="listline2">	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.budeng)"/></td>
             <th class="listline2"> 	<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.koufen)"/></th>
      <th class="listline2"> <s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(id.zongjifen)"/></th>
            <th class="listline2">
        	<s:if test="id.zongjifen!=0&&id.zongjifen<dabiaofen">
                		 <font color="blue">未达标</font>
               	</s:if>
               	<s:elseif test="id.zongjifen==0"> 
               		未培训
               	</s:elseif>
                <s:else><font color="red">已达标</font>
                </s:else>
      </th>
      
      </tr>
    </s:iterator>
      </tbody>
    </table>

</body>
</html>