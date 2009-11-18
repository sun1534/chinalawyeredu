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
			   	 <li id="${rightcode}"><a href="${linkurl}" target="mainFrame">${rightname}</a></li>
			   	</s:if>
			   	</s:iterator>
			   	</ul>
			   </s:if>
			   <s:else>
			   <h3><a  href="${linkurl }" target="mainFrame">${rightname }</a></h3>
			   </s:else>
			</s:iterator>
			</s:if>
			</div>
		
		</s:iterator>
		<!-- 
	<div id="menuWarp"  class="Menu">
	 	<h3 id="now_h3">业务全貌</h3>
	 	<ul id="open">
			<li><a href="total_total_1.html" target="mainFrame">总流量分析</a></li>
			<li><a href="total_23g_2.html" target="mainFrame">2/3G流量分析</a></li>
			<li><a href="total_sgsn_3.html" target="mainFrame">SGSN流量分析</a></li>
			<li><a href="total_sgsn23g_4.html" target="mainFrame">SGSN-2/3G流量分析</a></li>
		</ul>
		<h3>业务细节</h3>
		<ul>
			<li><a href="total_bscrnc_5.html" target="mainFrame">BSC/RNC流量分析</a></li>
			<li><a href="total_cell_6.html" target="mainFrame">CELL流量分析</a></li>
			<li><a href="total_celltime_7.html" target="mainFrame">CELL分时流量分析</a></li>
		</ul>
		<h3>行业业务统计</h3>
		<ul>
			<li><a href="total_apn_8.html" target="mainFrame">APN业务统计</a></li>
			<li><a href="total_apntime_9.html" target="mainFrame">行业APN分时段统计</a></li>
			<li><a href="total_apnimport_10.html" target="mainFrame">行业APN分重点小区分析</a></li>
		</ul>
	</div>-->
</div>
<script src="../js/left.js" type="text/javascript"></script>
</body>
</html>
