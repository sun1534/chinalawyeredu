<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">
  $(document).ready(function(){
    $("tbody tr td input").addClass("txt");
    $("tbody tr:even").addClass("fEven");
    $("tbody tr:odd").addClass("fOdd");
  });
</script>
</head>
<body>
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>MEM参数设置</a>＞<em><s:if test="settype.equals(\"pingip\")">设置PING测试IP</s:if><s:else>设置Tracert测试IP</s:else></em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="memParamSet" method="post" validate="true">
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="submit" class="btnSubmit" title="保 存" value="保 存"/>
				</div>
				<div class="operateTab">
				<!--	<div class="operateTabInfo">错误提示信息</div> -->
					
					<table class="operateTabBox">
						<tbody>
							
						
							<tr>
								<td class="fname" valign="top">
								<s:if test="settype.equals(\"pingip\")">PING测试IP:</s:if><s:else>Tracert测试IP:</s:else>（CMWAP）</td>
								<td>
       <s:textfield name="pvaluecmnet" size="25" maxlength="150"/>
							
								</td>
							</tr>
							<tr>
								<td class="fname" valign="top">
								<s:if test="settype.equals(\"pingip\")">PING测试IP:</s:if><s:else>Tracert测试IP:</s:else>（CMNET）</td>
								<td>
								 <s:hidden name="settype"/>
       <s:textfield name="pvaluecmwap" size="25" maxlength="150"/>
							
								</td>
							</tr>
							
							<tr>
								<td class="fname" valign="top">IP说明：</td>
								<td>
								<s:textarea name="comments" rows="5" cols="40" cssClass="txt"/>
								</td>
							</tr>
					
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</s:form>

</body>

</html>

