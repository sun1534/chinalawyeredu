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
 
 ${lawyers.lawyername} 律师的培训积分 	(积分计算：从【${jifentime.startstr}】到【${jifentime.endstr}】。达标需满【${dabiaofen}】分)
  
    </b></td>
  </tr>
      <tr>
       <th>课程名称</th>
        <th>培训方式</th>
        <th>培训日期</th>
        <th>要求时长（分）</th>
        <th>培训时长（分）</th>
        <th>获得积分</th>
         
                        
      </tr>
      <tbody>
      
        <s:iterator value="page.items" status="status">
                        <tr>
 
      <td class="listline2">
        ${title}
        </td>
       <td class="listline2">   <s:property value="@com.changpeng.jifen.util.CommonDatas@LEARNMODE[learnmode]"/>  </td>
      <td class="listline2">${pxdate} </td>
     	<td class="listline2">${pxreqminutes}  </td>
       <td class="listline2">${pxminutes}  </td>
        <td class="listline2">${pxxf}  </td>
    
                        </tr>
                        </s:iterator>
       
      </tbody>
    </table>

</body>
</html>