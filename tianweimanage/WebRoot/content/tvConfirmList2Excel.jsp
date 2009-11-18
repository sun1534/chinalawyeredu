<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ page import="java.util.*,com.opensymphony.xwork2.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-电视内容订购列表</title>
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
String filename="tvcontent.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename);
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>


    
   <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
      <thead>
      <tr>
                    <th class="listheadline">产品名</th>
                       <th class="listheadline">发布频道</th>
                   <th class="listheadline">订购人</th>
                      <th class="listheadline">当前状态</th>
             <th class="listheadline">费用</th>
  <th class="listheadline">订购时间</th>
   <th class="listheadline">付费时间</th>
    <th class="listheadline">审批通过时间</th>
     <th class="listheadline">发布起始时间</th>
 <th class="listheadline">发布结束时间</th>
      </tr>
      </thead>
      <tbody>
      <s:iterator value="page.items" status="stat">
      <tr>
     
        <th  class="listline2"><s:property value="@com.sxit.service.util.CommonDatas@ALLPRODUCTS[productid]"/></th>
        <td  class="listline2"><s:property value="@com.sxit.service.util.CommonDatas@ALLCHANNELS[channelid]"/></td>
        <td  class="listline2"><s:property value="@com.sxit.users.util.CommonDatas@ALLUSERS[userid]"/></td>
        <td  class="listline2"><s:property value="@com.sxit.content.util.CommonDatas@PUBLISHSTATUS[statusid]"/></td>
        <td  class="listline2">${fee }</td>
        <td  class="listline2">  <s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
           <td  class="listline2">  <s:date name="feetime" format="yyyy-MM-dd HH:mm"/></td>
              <td  class="listline2">  <s:date name="approvetime" format="yyyy-MM-dd HH:mm"/></td>
                 <td  class="listline2">  <s:date name="starttime" format="yyyy-MM-dd HH:mm"/></td>
                    <td  class="listline2">  <s:date name="endtime" format="yyyy-MM-dd HH:mm"/></td>
                        
   
      </tr>
   
    </s:iterator>
      </tbody>
    </table>

</body>
</html>