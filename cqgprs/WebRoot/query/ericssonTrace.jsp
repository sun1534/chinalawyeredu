<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<script language="javascript">
	setTimeout("quit()",2000);
function quit(){
	if(interval)
	clearInterval(interval);
}
</script>
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                      <!--  时间	事件	详情	错误码	小区	接入类型 -->
                          <th>时间</th>
                             <!-- <th>IMSI</th>-->
                          <th>事件</th>
                          <th>详情</th>
                          <th>错误码</th>
                          <th>小区编号</th>
                          <th>接入类型</th>                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="traceState.traceLogs" status="status">
                        <tr>
                         <td>${theTime}</td>
                           <!-- <td>${imsi}</td>-->
                          <td>${eventName }</td>
                          <td>${eventDetails}</td>
                          <td>${causeValue}</td>
                          <td>${Cellname}</td>
                          <td>${accessType}</td>
                        </tr>
                        </s:iterator>                      
                      </tbody> 
                    </table>			