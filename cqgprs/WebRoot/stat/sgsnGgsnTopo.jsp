<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-SGSN/GGSN流量拓扑示意图</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">
function queryit(){
document.form1.submit();
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>统计分析</a>＞<em>业务全貌</em>＞<em>${start }之SGSN/GGSN流量拓扑示意图</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="sgsnGgsnTopo" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
								 <td>输入日期：<s:textfield name="start" id="start" size="10" cssClass="txt"/>&nbsp;</td>
								 <td>连续显示：<s:select name="count" list="#{'1':'1','2':'2','3':'3'}"/>天&nbsp;</td>
								 <td><input type="button" class="btnSubmit" title="显示流量示意图" value="显示流量示意图" onclick="queryit()"/></td>
                 <td><a href="http://download.adobe.com/pub/adobe/magic/svgviewer/win/3.x/3.03/zh/SVGView.exe">IE浏览器请到此下载一个SVGViewer</a></td>
								</tr>
							</tbody>
						</table>
				  </div> 
					<s:iterator value="datelist">
				  <div  class="tablist" style="text-align:center" id="imgreport">
               <embed src="sgsnGgsnTopoSVG.action?start=<s:property/>" width="950" height="350" type="image/svg+xml" /> 
                    </div>
				</s:iterator>
				</div>
			
			</div>
		</div>
</s:form>
</body>

</html>

