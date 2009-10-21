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
//String filename="export.xls";
//response.reset();
//response.setContentType("bin;charset=utf-8"); 
//response.addHeader("Content-Disposition","attachment; filename="+filename);
//out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>


    
   <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
      <tr>
        <td colspan="5" align="center" bgcolor="#FFFF00"><b>${start}CELL流量分析</b></td>
      </tr>
      <tr>
                          <th>类型</th>
                         <th>BSC/RNC编码</th>
                          <th>归属SGSN</th>
                           <th>总流量（M）></th>
                          <th>总用户数</th>
                          <th>平均流量（K）</th>
      </tr>
      <tbody>
      <s:iterator value="page.items" status="stat">
      <tr>
     
     <td class="listline2">${nettype }</td>
                          <td class="listline2">${bscrncid}</td>
                          <td class="listline2">${sgsnid}</td>
                          <td class="listline2">${totalStreamStr }</td>
                          <td class="listline2">${totalUser}</td>
                          <td class="listline2">${averageStreamStr}</td>
   
      </tr>
   
    </s:iterator>
      </tbody>
    </table>

</body>
</html>