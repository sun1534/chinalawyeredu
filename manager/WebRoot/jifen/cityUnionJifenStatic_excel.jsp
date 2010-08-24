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
 
  积分统计-${group.groupname }
  <s:if test="group.groupid!=8078">
  -从【${jifentime.startstr }】到【${jifentime.endstr }】
  </s:if>
    </b></td>
  </tr>
      <tr>
       <th>姓名</th>
        <th ><s:if test="!gongzheng">事务所</s:if><s:else>公证处</s:else></th>
        <th>现场积分</th>
        <th>网上积分</th>
        <th>文本课件</th>
        <th>补登积分</th>
       <th>未到扣分</th>
        <th>总积分</th>
         <th>是否达标</th>
         
                        
      </tr>
      <tbody>
      
        <s:iterator value="page.items" status="status">
                        <tr>
  <td class="listline2">${name}</td>
      <td class="listline2">
        ${groupname}
        </td>
       <td class="listline2"> ${xianchang}  </td>
      <td class="listline2">${video}  </td>
     	<td class="listline2">${doc}  </td>
       <td class="listline2">${budeng}  </td>
        <td class="listline2">${koufen}  </td>
       <td class="listline2">${zongjifen}  </td>
      <td class="listline2">${dabiaostr }</td>
                        </tr>
                        </s:iterator>
       
      </tbody>
    </table>

</body>
</html>