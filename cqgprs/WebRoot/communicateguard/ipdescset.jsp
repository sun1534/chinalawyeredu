<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-参数新增</title>
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
					<span>您所在是位置:</span><a>MEM参数设置</a>＞<em>Tracert测试IP说明设置</em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="ipdescset" method="post" validate="true">
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="submit" class="btnSubmit" title="保 存" value="保 存"/>
					<input type="button" class="btnBack" title="返 回" value="返 回" onclick="history.go(-1)"/>
				</div>
				<div class="operateTab">
				<!--	<div class="operateTabInfo">错误提示信息</div> -->
					
					<table class="operateTabBox">
						<tbody>
							<tr>
								<td class="w150 fname">IP地址：<br /></td>
								<td>
  	<s:if test="!type.equals(\"modify\")">
       <s:textfield name="paramname" size="25" maxlength="150"/>
         <span class="cR">不能为空</span>
       </s:if>
       <s:else>
       ${paramname }
       <s:hidden name="paramname"/>
       </s:else>
                                   
								 </td>
							</tr>
						
							<tr>
								<td class="fname" valign="top">IP地址说明：<br /></td>
								<td>
								<input type="hidden" name="type" value="${type }"/>
       <s:textfield name="pvalue" size="75" maxlength="150"/>
							 <span class="cR">不能为空</span>
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

