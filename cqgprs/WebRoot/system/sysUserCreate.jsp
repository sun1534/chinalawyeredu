<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-新增管理员</title>
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
	$("#checkloginname").html("*不为空且长度不超过15个字符");
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
           }
          }
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
function checkInput(){
	var passwordText = document.getElementById("password");
	var passagainText = document.getElementById("passagain");
	if(passagainText.value.length<5||passagainText.value.length>20){
		alert("重复密码的长度必须在5-20之间！");
		passagainText.focus();
		return false;
	}
	if(passwordText.value!=passagainText.value){
		alert("密码输入不一致！");
		passagainText.focus();
		return false;
	}
	return true;
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
<body>
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
					<input type="submit" class="btnSubmit" title="保 存" value="保 存" id="save"/>
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
								<s:textfield name="sysUser.loginname" size="15" maxlength="15" onblur="checkLoginname(this.value)"/>
                                <span class="cR" id="checkloginname">*不为空且长度不超过15个字符</span>
								</td>
							</tr>
							
							<tr>
								<td class="fname" valign="top">登录密码：</td>
								<td><s:password name="sysUser.password" size="28" maxlength="20" id="password"/><span class="cR">*密码长度必须在5-20之间</span></td>
							</tr>
							
								<tr>
								<td class="fname" valign="top">重复输入登录密码：</td>
								<td><s:password name="passagain" id="passagain" size="28" maxlength="20"/><span class="cR">*</span></td>
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
							  <s:if test="assignrole">
								<tr>
								<td class="fname" valign="top">请选择角色：</td>
								<td><s:checkboxlist name="roleid" list="roles" onclick="checkRole(this,this.value)"/>
								</td>
							</tr>
							  </s:if>
							  <s:else>
                               <s:hidden name="roleid" value="0"/>
                              </s:else>
						</tbody>

					</table>
				</div>
			</div>
		</div>
	</div>
</s:form>

</body>

</html>

