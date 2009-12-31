<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
<meta name="author" content="KevinXiao Email:kevin_218@163.com" />
<title>${sysName }-用户角色分配</title>
<link rel="stylesheet" type="text/css" href="../css/reset.css" />
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script language="javascript">
var __roleid=0;
function checkRole(obj,roleid){
__roleid=roleid;
if(obj.checked){
    $(":checkbox").each(function(i){
	      if($(this).val()!=roleid){
		    $(this).attr("checked",false);
		  }
       });
}
}
</script>
</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>系统管理</a>＞<em>管理员角色分配</em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="sysUserRole" method="post" validate="true">
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="submit" class="btnSubmit" title="保 存" value="保 存"/>
					<input type="button" class="btnBack" title="返 回" value="返 回" onclick="history.go(-1)"/>
				</div>
				<div class="operateTab">
				<!--<div class="operateTabInfo">错误提示信息</div> -->
					<table class="operateTabBox">
						<tbody>
							<tr>
								<td class="w250 fname">
								请选择角色：<br/>(已勾选的表示现在选中的角色)：
								</td>
								<td>
								  <s:hidden name="userid"/>
								  <s:checkboxlist name="roleid" list="roles" onclick="checkRole(this,this.value)"/>
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

