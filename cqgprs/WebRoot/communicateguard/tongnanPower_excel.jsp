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
String filename="zaipingshu.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename);
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>


    
   <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
     <tr>
    <td  colspan="6" align="center" bgcolor="#FFFF00"><b>${month}潼南电度对比</b></td>
  </tr>
      <tr>
     
                         <th>基站名称</th>
                     
                          <th>中达日均</th>
                           <th>电力公司日均</th>
                            <th>载频数</th>
                          <th>日均话务量</th>
                          <th>中达与电力数据比例</th>
      </tr>
      <tbody>
      <s:iterator value="powerlist" status="stat">
      <tr>
     
                         <td class="listline2">${cellName}</td>
                          <td class="listline2">${zhongdanrijun}</td>
                          <td class="listline2">${powerrijun }</td>
                          <td class="listline2">${zaipin}</td>
                          <td class="listline2">${huawuliangrijun}</td>
                          <td class="listline2">${theratePercent}</td>
                         
   
      </tr>
   
    </s:iterator>
      </tbody>
    </table>

</body>
</html>