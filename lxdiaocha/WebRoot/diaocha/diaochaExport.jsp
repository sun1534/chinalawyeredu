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
    <td  colspan="${columnsize }" align="center" bgcolor="#FFFF00"><b>
 
 ${diaocha.title }
  
    </b></td>
  </tr>
      <tr>
       <th>律师姓名</th>
        <th>执业证号</th>
        <th>调查日期</th>
      <s:iterator value="wentilist">
         <th>${title }</th>
      </s:iterator>
                        
      </tr>
      <tbody>
      
        <s:iterator value="exportlist" status="status">
      <tr>
      <td class="listline2">${lawyername}</td>
         <td class="listline2">${lawyerno}</td>
         <td class="listline2">${replytime}</td>
        <s:iterator value="wentilist">
         <td class="listline2">         
         <s:if test="answers.containsKey(wentiid)">
         <s:iterator value="answers[wentiid]" status="stat">
         <s:property value="#stat.index+1"/>、<s:property/><br/>
         </s:iterator>
         </s:if>         
         </td>
      </s:iterator>
     
                        </tr>
                        
       </s:iterator>
       
      </tbody>
    </table>

</body>
</html>