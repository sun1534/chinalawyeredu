<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="lefthtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName }-子菜单</title>
<link rel="stylesheet" type="text/css" href="../css/reset.css" />
<link rel="stylesheet" type="text/css" href="../css/left.css" />
<script src="../js/jquery.js" type="text/javascript"></script>
<script language="javascript">
$(document).ready(function(){$(".Menu").hide()});
function showMenu(rightcode){
//显示某一个div,隐藏其他的div
 $("#"+rightcode).show();  //自己展开,其他所有的隐藏,这个div的第一个ul展开
 $("#"+rightcode).siblings().hide();
 $("ul").hide(); //所有的ul都隐藏后再当前id下的第一个ul展开
 $("#"+rightcode+" ul:first").show();
 //alert($("#"+rightcode).siblings());
}
</script>
</head>
<body >
<div id="leftmenu">
		<s:iterator value="#session.loginUser.topMenus" status="stat">
		
			<div id="${rightcode}" class="Menu">
			<s:if test="hasChild">
			<s:iterator value="children" status="stat"> 
			   <s:if test="hasChild">
			   	<h3 id="${rightcode}">${rightname }</h3>
			   	<ul>
			   	<s:iterator value="children" status="stat">
			   	<s:if test="ismenu">
			   	 <li id="${rightcode}"><a href="${linkurl}" target="${opentarget}">${rightname}</a></li>
			   	</s:if>
			   	</s:iterator>
			   	</ul>
			   </s:if>
			   <s:else>
			   <h3><a  href="${linkurl }" target="${opentarget} ">${rightname }</a></h3>
			   </s:else>
			</s:iterator>
			</s:if>
			</div>
		
		</s:iterator>
	
</div>
<script src="../js/left.js" type="text/javascript"></script>
</body>
</html>
