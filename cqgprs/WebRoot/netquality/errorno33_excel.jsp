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
    <td  colspan="3" align="center" bgcolor="#FFFF00"><b>${exceltitle}</b></td>
  </tr>
      <tr>        
			<td class="listheadline">错误总数</td> 
			<td  colspan="2" class="listline2">${codestat.errorcount }</td> 
			
		</tr>
		<tr>			
			<th class="listheadline">错误用户数</td> 
			<td  colspan="2" class="listline2">${codestat.usercount }</td> 
		</tr> 
 <tr>
    <td  colspan="3" align="center" bgcolor="#FFFF00"><b>错误详情</b></td>
  </tr>
       <tr> 
						<td class="listheadline" >用户IMSI</td>
                          <td class="listheadline" >请求APN</td>
                          <td class="listheadline" >PDP失败次数</td>
</tr>
                        <s:iterator value="codestat.detailist" status="status">
                         <tr>
                         <td  class="listline2">${imsi}</td>
                          <td  class="listline2">${apn}</td>
                          <td  class="listline2">${pdperrorcnt }</td>
                      
                        </tr>
                        </s:iterator>
    </table>

</body>
</html>