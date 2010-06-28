<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<script language="javascript">
setTimeout("quit()",2000);
function quit(){
	if(interval)
		clearInterval(interval);
}
</script>
<s:iterator value="maslist" status="stat">
<s:if test="hasexception">
${mobile }在${sgsnid }的查询有异常:<br/>
${webexception }
</s:if>
<s:else>
<s:iterator value="webstringList" status="stat">
<p>
<s:property/>
</p>
</s:iterator>
</s:else>
<hr/>
</s:iterator>