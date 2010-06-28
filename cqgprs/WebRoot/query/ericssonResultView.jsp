<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<s:if test="hasover">
<script language="javascript">
clearInterval(interval);
</script>
</s:if>

<s:iterator value="viewlist" status="stat">
<div class="lvlst">
<s:property/>
</div>
</s:iterator>