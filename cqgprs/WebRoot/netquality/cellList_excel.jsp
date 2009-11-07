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
    <td  colspan="6" align="center" bgcolor="#FFFF00"><b>所有小区信息列表</b></td>
  </tr>
      <tr>
     <!-- 
                          <th class="listheadline">SGSN号</th>
                          -->
                          <th class="listheadline">小区编号</th>
                       <th class="listheadline">小区名称</th>
                       <th class="listheadline">所属BSC/RNC</th>
                       <th class="listheadline">归属SGSN</th>
                       <th class="listheadline">备注信息</th>
                       <th class="listheadline">最后更新时间</th>
      </tr>
      <tbody>
      <s:iterator value="page.items" status="stat">
      <tr>
      
                          <td class="listline2">${cellid}</td>
                          <td class="listline2">${cellname}</td>
                          <td class="listline2">${bscrncid }</td>
                          <td class="listline2"><s:property value="@com.sxit.netquality.service.BasicSetService@BSC_SGSN[bscrncid]"/></td>
                          <td class="listline2">${remarks}</td>
                          <td class="listline2"><s:date name="lastupdate" format="yyyy-MM-dd HH:mm:ss"/></td>

                       
   
      </tr>
   
    </s:iterator>
      </tbody>
    </table>

</body>
</html>