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
    <td  colspan="8" align="center" bgcolor="#FFFF00"><b>
 
    ${date }告警小区列表
    </s:if>
  
    </b></td>
  </tr>
      <tr>
                        <th>小区编号</th>
                          <th>小区名称</th>
                          <th>当前用户数</th>          
                          <th>上小时用户数</th>
                          <th>昨天同时用户数</th>
                          <th>当前流量（M）</th>
                          <th>上小时流量（M）</th>
                          <th>昨天同时流量（M）</th>
                              <th>用户增长率</th>
                          <th>流量增长率</th>
                          <td class="listline2">${useraddrate}</td>
                                 <td class="listline2">${flowaddrate}</td>
                        
      </tr>
      <tbody>
      
        <s:iterator value="page.items" status="status">
                        <tr>
                            <td class="listline2">${cellkey}</td>
                           <td class="listline2"><${cell.cellname}</td>
              <td class="listline2">${usercount }</td>
                          <td class="listline2">${pretimeusercount}</td>
                          <td class="listline2">${predayusercount}</td>
                           <td class="listline2">${allvolumeStr}</td>
                            <td class="listline2">${pretimeallvolumeStr}</td>
                             <td class="listline2">${predayallvolumeStr}</td>

                        </tr>
                        </s:iterator>
       
      </tbody>
    </table>

</body>
</html>