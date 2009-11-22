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
    <td  colspan="7" align="center" bgcolor="#FFFF00"><b>
    <s:if test="flag==1">
    ${date1 }之实时PDP失败总情况
    </s:if>
    <s:else>
     ${date1 }之前3天PDP失败总情况
    </s:else>
   </b></td>
  </tr>
      <tr>
                       <th ></th> 
                          <th colspan="2">当天累加值（${date1 }）</th> 
                          <th colspan="2">本时间段增加值（${date2}）</th> 
                          <th colspan="2">上次时间段增加值（${ date3}）</th> 
      </tr>
      <tbody>
  
      <tr>
     
                 <tr > 
                          <td align="center" >错误类型</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                        </tr> 
                        
                         <tr> 
                          <td align="center" >#33用户设置错误 </td> 
                          <td>${errorallstat1.usercount33 }</td> 
                          <td>${errorallstat1.errorcount33 }</td> 
                          <td>${errorallstat2.usercount33 }</td> 
                          <td>${errorallstat2.errorcount33 }</td> 
                          <td>${errorallstat3.usercount33 }</td> 
                          <td>${errorallstat3.errorcount33 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#27漫游错误 </td> 
                           <td>${errorallstat1.usercount27 }</td> 
                          <td>${errorallstat1.errorcount27 }</td> 
                          <td>${errorallstat2.usercount27 }</td> 
                          <td>${errorallstat2.errorcount27 }</td> 
                          <td>${errorallstat3.usercount27 }</td> 
                          <td>${errorallstat3.errorcount27 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#29RADUIAS错误 </td> 
                         <td>${errorallstat1.usercount29 }</td> 
                          <td>${errorallstat1.errorcount29 }</td> 
                          <td>${errorallstat2.usercount29 }</td> 
                          <td>${errorallstat2.errorcount29 }</td> 
                          <td>${errorallstat3.usercount29 }</td> 
                          <td>${errorallstat3.errorcount29 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#38网络错误 </td> 
                           <td>${errorallstat1.usercount38 }</td> 
                          <td>${errorallstat1.errorcount38 }</td> 
                          <td>${errorallstat2.usercount38 }</td> 
                          <td>${errorallstat2.errorcount38 }</td> 
                          <td>${errorallstat3.usercount38 }</td> 
                          <td>${errorallstat3.errorcount38 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >其他错误 </td> 
                          <td>${errorallstat1.usercountothers }</td> 
                          <td>${errorallstat1.errorcountothers }</td> 
                          <td>${errorallstat2.usercountothers }</td> 
                          <td>${errorallstat2.errorcountothers }</td> 
                          <td>${errorallstat3.usercountothers }</td> 
                          <td>${errorallstat3.errorcountothers }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >总量 </td> 
                          <td>${errorallstat1.usercountall }</td> 
                          <td>${errorallstat1.errorcountall }</td> 
                          <td>${errorallstat2.usercountall }</td> 
                          <td>${errorallstat2.errorcountall }</td> 
                          <td>${errorallstat3.usercountall }</td> 
                          <td>${errorallstat3.errorcountall }</td> 
                        </tr> 
   
      </tr>
   
   
      </tbody>
    </table>

</body>
</html>