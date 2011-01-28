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
    <td  colspan="12" align="center" bgcolor="#FFFF00"><b>
 
 实习律师列表
  
    </b></td>
  </tr>
      <tr>
       <th>姓名</th>
        <th>身份证号</th>
        <th>资格证号</th>
        <th>资格证时间</th>
        <th>实习证号</th>
        <th>实习证时间</th>
        <th>所属事务所</th>
                        
      </tr>
      <tbody>
      
        <s:iterator value="page.items" status="status">
      <tr>
      <td class="listline2">
        ${lawyername}
        </td>
       <td class="listline2"> ${certno }&nbsp; </td>
      <td class="listline2">${zigeno}&nbsp;</td>
     	<td class="listline2">${zigedate}  </td>
       <td class="listline2">${shixino}&nbsp;  </td>
        <td class="listline2">${shixidate}  </td>
   <td class="listline2"><s:property value="@com.changpeng.system.util.CommonDatas@groups[theoffice]"/> </td>
                        </tr>
                        </s:iterator>
       
      </tbody>
    </table>

</body>
</html>