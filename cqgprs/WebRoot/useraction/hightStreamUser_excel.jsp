<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<style type="text/css">
<!--
.listheadline {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #919191}
th
{FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #919191}

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
String filename="export.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename);
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>


    
   <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
     <tr>
    <td  colspan="6" align="center" bgcolor="#FFFF00"><b>
    <s:if test="standard==2">
    <s:if test="flag.equals(\"2\")">
    ${start} ${hour }:00之高流量用户排名（前${condition}）
    </s:if>
    <s:else>
    ${start}之高流量用户排名（前${condition}）
    </s:else>
    </s:if>
    <s:else>
       <s:if test="flag.equals(\"2\")">
    ${start} ${hour }:00之高流量用户排名（前${condition}）
    </s:if>
    <s:else>
      ${start}之流量大于${condition }（K）用户排名
      </s:else>
    </s:else>
    </b></td>
  </tr>
      <tr>
                <th>手机号码</th>
                          <th>APN编码</th>
                           <th>上行流量（M）</th>
                            <th>下行流量（M）</th>
                          <th>总流量（M）</th>
                          <th>逗留时长</th>
      </tr>
      <tbody>
     <s:iterator value="top1000users" status="status">
      <tr>
        <td class="listline2">${mobile}</td>
                          <td class="listline2">${apnni}</td>
                          <td class="listline2">${upvolumeStr }</td>
                          <td class="listline2">${downvolumeStr}</td>
                          <td class="listline2">${allvolumeStr}</td>
                          <td class="listline2">${periodlenStr}</td>
      </tr>
    </s:iterator>
      </tbody>
    </table>

</body>
</html>