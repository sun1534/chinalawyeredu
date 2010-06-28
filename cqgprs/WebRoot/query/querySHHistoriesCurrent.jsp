<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<a href="querySHHistories.action?mobile=${mobile}" target="_blank">更多</a><hr/>
<s:iterator value="page.items" status="status">
${result}<hr/>
</s:iterator>
 