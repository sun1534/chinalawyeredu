<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-登录日志列表</title>
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
String filename="userloginlog.xls";
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
      <tr>
     
        <th class="listheadline">登录用户</th>
        <th class="listheadline">类型</th>
        <th class="listheadline">登录IP</th>
        <th class="listheadline">登录时间</th>
 <th class="listheadline">退出时间</th>
      </tr>
      </thead>
      <tbody>
      <s:iterator value="page.items" status="stat">
      <tr>
     
        <th  class="listline2">${userName}</th>
        <td  class="listline2">
        <s:if test="userRole==1">家庭
                        </s:if>
                        <s:else>企业
                        </s:else>
        </td>
        <td  class="listline2">${loginTime}</td>
        <td  class="listline2">${quitTime}</td>
   
      </tr>
   
    </s:iterator>
      </tbody>
    </table>

</body>
</html>