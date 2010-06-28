<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<script language="javascript">
setTimeout("quit()",2000);
function quit(){
	if(interval)
		clearInterval(interval);
}
</script>
<s:if test="traceState.hasexception">
处理有异常:<br/>
${traceState.webexception }
</s:if>
<s:else>
<s:if test="stoporstart==1">追踪执行成功
</s:if>
<s:elseif test="stoporstart==2">停止追踪执行成功
</s:elseif>
<s:elseif test="stoporstart==4">停止所有号码的追踪执行成功
</s:elseif>
<s:else>命令字有异常
</s:else>
</s:else>
