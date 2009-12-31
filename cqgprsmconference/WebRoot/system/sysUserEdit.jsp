<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-管理员信息修改</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script language="javascript">
  $(document).ready(function(){
    $("tbody tr td input").addClass("txt");
    $("tbody tr:even").addClass("fEven");
    $("tbody tr:odd").addClass("fOdd");
  });
function checkLoginname(loginname){	
	$("#checkloginname").html("不为空且长度不超过15个字符");
	if((loginname == null) || (loginname.length == 0))
		return;
	var now=new Date().getTime();
	var _url="../systemajax/checkLoginname.action?now="+now+"&loginname="+loginname;
	$.ajax({
          type:"post",
          url:_url,
          async:true,
          success:function(xml){
		  var res=eval('(' + xml + ')');
   if(res.isrepeat == true){
   		$("#checkloginname").html("对不起，您输入的帐号【"+res.loginname+"】已经被他人使用，请选择其他名字后再试");
   		$("#save").attr("disabled", true);
   }else{
		$("#save").attr("disabled", false);
   }}
		  });
}
String.prototype.getBytes = function() {　　
	　　var cArr = this.match(/[^x00-xff]/ig);　　
	　　return cArr == null ? 0 : cArr.length;　　
	}
	function paramCheck(cur){
	　　if(cur.getBytes() > 7){
	　　		document.getElementById("username").focus();
			document.getElementById("username").select();
	　　}
	}

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
					<span>您所在是位置:</span><a>系统管理</a>＞<em>新增管理员</em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="sysUserCreate" method="post" validate="true">
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
								<td class="w150 fname">管理员姓名：</td>
								<td>
								  <s:textfield name="sysUser.username" size="15" maxlength="15" id="username" onblur="paramCheck(this.value)"/>
								</td>
							</tr>
						
							<tr>
								<td class="fname" valign="top">所在部门：</td>
								<td>
								<s:select name="sysUser.sysGroup.groupid" list="parentList" listKey="groupid" listValue="groupname" />
								</td>
							</tr>
							
							<tr>
								<td class="fname" valign="top">登录名：</td>
								<td>
								<s:textfield name="sysUser.loginname" size="15" maxlength="15"/>
                                <span class="cR" id="checkloginname">不能修改</span>
								</td>
							</tr>
							
							
							
								<tr>
								<td class="fname" valign="top">电话号码：</td>
								<td><s:textfield name="sysUser.officephone" size="25" maxlength="25"/></td>
								</td>
							</tr>
								<tr>
								<td class="fname" valign="top">手机号码：</td>
								<td><s:textfield name="sysUser.mobile" size="15" maxlength="15"/></td>
					
							</tr>
								<tr>
								<td class="fname" valign="top">EMAIL：</td>
								<td><s:textfield name="sysUser.email" size="40" maxlength="40"/></td>
					
							</tr>
								<tr>
								<td class="fname" valign="top">状态：</td>
								<td> <s:select name="sysUser.status" list="#{'0':'正常','1':'锁定'}"/></td>
							</tr>
								<tr>
								<td class="fname" valign="top">性别：</td>
								<td><s:select name="sysUser.gender" list="#{'G':'男','M':'女'}"/></td>
							</tr>
								<tr>
								<td class="fname" valign="top">备注信息：</td>
								<td><s:textarea name="sysUser.comments" rows="4" cols="50" cssClass="txt"/>
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

