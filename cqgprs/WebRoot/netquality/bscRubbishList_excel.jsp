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
String filename="bsclist.xls";
response.reset();
response.setContentType("bin;charset=utf-8"); 
response.addHeader("Content-Disposition","attachment; filename="+filename);
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
%>
</head>
<body>


    
   <table id="data" width="100%"  border=1 align=center cellpadding=3 cellspacing=1 bgcolor="#F9F9F7">
     <tr>
    <td  colspan="6" align="center" bgcolor="#FFFF00"><b>垃圾BSC数据列表</b></td>
  </tr>
      <tr>

                          <th>BVCI</th>
                       <th>最后更新时间</th>
                      <th>垃圾NSEI</th>
                       <th>归属BSC</th>  
                       <th>正确NSEI</th>
                       <th>归属BSC</th>  
      </tr>
      <tbody>
           <s:iterator value="resultList" status="status1">
                    <s:if test="correctCnt>=rubbishCnt"><!-- 正确的多 -->
                    <s:iterator value="correctList" status="status2">
                    <s:if test="#status2.index==0">
                      <tr>
                      <td rowspan="${trcnt}">${bvci } </td>
                      <td rowspan="${trcnt}">${recordtimestr }</td>
                      <s:if test="#status2.index<rubbishCnt">
                        <td><s:property value="rubbishList[#status2.index].nesi"/></td> 
					    <td><s:property value="rubbishList[#status2.index].bscid"/></td> 
					  </s:if>
					  <s:elseif test="rubbishCnt==0">
					  	<td rowspan="${trcnt}" colspan="2">没有垃圾数据</td>
					  </s:elseif>
					  <s:else>
					    <td>&nbsp;</td> 
					    <td>&nbsp;</td> 
					  </s:else>
		    		  <td>${nesi }</td> 
		    		  <td>${bscid }</td> 
                      </tr>
                    </s:if>
                    <s:else>
                    <tr>
                    <s:if test="rubbishCnt!=0">
                     <s:if test="#status2.index<rubbishCnt">
                      <td ><s:property value="rubbishList[#status2.index].nesi"/></td> 
					  <td ><s:property value="rubbishList[#status2.index].bscid"/></td> 
					  </s:if>
					  <s:else>
					    <td>&nbsp;</td> 
					    <td>&nbsp;</td> 
					  </s:else>
					  </s:if>
		    		  <td >${nesi }</td> 
		    		  <td >${bscid }</td> 
                    </tr>
                    </s:else>
                    </s:iterator>
                    </s:if>
                    <s:else>
                        <s:iterator value="rubbishList" status="status3">
                    <s:if test="#status3.index==0">
                      <tr>
                      <td rowspan="${trcnt}">${bvci }</td>
                      <td rowspan="${trcnt}">${recordtimestr }</td>
                       <td >${nesi }</td> 
		    		  <td >${bscid }</td> 
                      <s:if test="#status3.index<correctCnt">
                      <td ><s:property value="correctList[#status3.index].nesi"/></td> 
					  <td ><s:property value="cor rectList[#status3.index].bscid"/></td> 
					  </s:if>
					    <s:elseif test="correctCnt==0">
					  	<td rowspan="${trcnt}" colspan="2">没有正确数据</td>
					  </s:elseif>
					    <s:else>
					    <td >&nbsp;</td> 
					  <td >&nbsp;</td> 
					  </s:else>
		    		 
                      </tr>
                    </s:if>
                    <s:else>
                    
                    <tr>
		    		  <td >${nesi }</td> 
		    		  <td >${bscid }</td> 
		    		       <s:if test="correctCnt!=0">
		    		    <s:if test="#status3.index<correctCnt">
                      <td ><s:property value="correctList[#status3.index].nesi"/></td> 
					  <td ><s:property value="correctList[#status3.index].bscid"/></td> 
					  </s:if>
					    <s:else>
					    <td >&nbsp;</td> 
					  <td >&nbsp;</td> 
					  
					  </s:else>
					  </s:if>
                    </tr>
                    </s:else>
                    </s:iterator>
                    </s:else>
                       
                     
                        </s:iterator>
      </tbody>
    </table>

</body>
</html>