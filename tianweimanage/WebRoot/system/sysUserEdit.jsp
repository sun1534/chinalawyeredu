<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="all"/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript">
$(document).ready(function(){
 $("#_tablebox tr:even").addClass("odd");
 $("#location").hide();
});


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
	function showPassword(){
		$("#displayThisBtn").attr("disabled",false);
		$("#showPasswordBtn").attr("disabled",true);
		$("#password").attr("style","display:block;");
		$("#repassword").attr("style","display:block;");
		$("#save").attr("disabled",false);
	}
	function displayThis(){
		$("#showPasswordBtn").attr("disabled",false);
		$("#displayThisBtn").attr("disabled",true);
		$("#save").attr("disabled",false);
		$("#password").attr("style","display:none;");
		$("#repassword").attr("style","display:none;");
		$("#password").attr("value","");
		$("#repassword").attr("value","");
	}
	function repassword_on_blur(){
		var passwordtext = $("#password").val();
		var repasswordtext=$("#repassword").val();
		if(passwordtext==""&&repasswordtext==""){
			return;
		}
		else if(passwordtext!=repasswordtext){
			alert("密码输入不一致！");
			$("#save").attr("disabled",true);
			return;
		}
		else if(passwordtext.length<6||passwordtext.length>20){
			alert("密码长度在6-20之间！");
			$("#save").attr("disabled",true);
			return;
		}
		else
			$("#save").attr("disabled",false);
	}
	function password_on_blur(){
		var passwordtext = $("#password").val();
		var repasswordtext=$("#repassword").val();
		if(passwordtext!=repasswordtext){
			$("#repassword").focus();
		}
	}
</script>

</head>
<body>
<div class="oper"> 你的位置：${navigator}<!--	<a href='#'>后台用户管理</a>&gt;<a href='../system/sysUserList.action'>用户管理</a>&gt;<a href='#'>用户修改</a>--> </div>
<div class="maincontent">
  <s:form name="form1" action="sysUserEdit" method="post" validate="true">
  <div class="tablebox">
    <p>红色<span class="cR">*</span>表示的选项为<span class="cR">必填</span>
  <span class="cR">  <s:actionerror/>
    <s:actionmessage/>
    <s:fielderror/>
    </span>
    </p>
    
    <table border="0" cellspacing="0" cellpadding="0" width="90%" id="_tablebox">
      <tr>
        <td width="25%" class="tar">管理员姓名：</td>
        <td>
      <s:textfield name="sysUser.username" size="15" maxlength="15" required="true" id="username" onblur="paramCheck(this.value)"/>
      
        <span class="cR">不为空且长度不超过7个汉字</span>
        </td>
      </tr>
      <tr>
        <td width="20%" class="tar">所在部门：</td>
        <td>
     <s:select name="sysUser.sysGroup.groupid" list="parentList" listKey="groupid" listValue="groupname" />
        </td>
      </tr>
    
     
      <tr>
        <td class="tar">登录名：</td>
        <td><s:textfield name="sysUser.loginname" required="true" readonly="true"/>
        <span class="cR" id="checkloginname">不能修改</span>
        </td>
      </tr>
		<tr>
			<td class="tar"></td>
			<td>
				<button class="btn" type="button" id="showPasswordBtn" onclick="showPassword()">修改密码</button>&nbsp;&nbsp;
				<button class="btn" type="button" id="displayThisBtn" onclick="displayThis()" disabled="true">隐藏密码</button>
			</td>
		</tr>
      
      <span id="passworddiv">

      </span>
      <tr>
        <td class="tar">登录密码：</td>
        <td> <s:password id="password" cssStyle="display:none" name="password" size="28" maxlength="20" onblur="password_on_blur()"/><span class="cR" id="passwordclue" style="display:none">*密码长度必须在5-20之间</span></td>
      </tr>
      <tr>
        <td class="tar">重复输入登录密码：</td>
        <td><s:password id="repassword" cssStyle="display:none" name="passagain" size="28" maxlength="20" onblur="repassword_on_blur()"/><span class="cR" id="repasswordclue" style="display:none">*</span></td>
      </tr>
      <tr>
        <td class="tar">电话号码：</td>
        <td><s:textfield name="sysUser.officephone" size="25" maxlength="25"/></td>
      </tr>
     <tr>
        <td class="tar">手机号码：</td>
        <td><s:textfield name="sysUser.mobile" size="13" maxlength="13"/></td>
      </tr>
       <tr>
        <td class="tar">EMAIL：</td>
        <td> <s:textfield name="sysUser.email" size="40" maxlength="40"/></td>
      </tr>
       <tr>
        <td class="tar">状态：</td>
        <td>    <s:select name="sysUser.status" list="#{'0':'正常','1':'锁定'}"/></td>
      </tr>
    <tr>
        <td class="tar">性别：</td>
        <td>
        
           <s:select name="sysUser.gender" list="#{'G':'男','M':'女'}"/></td>
      </tr>
       <tr>
        <td class="tar">备注信息：</td>
        <td><s:textarea name="sysUser.comments" rows="4" cols="50"/></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
       
      </tr>
    </table>
    <p class="pbtn">
  <button class="btn" type="submit" id="save">保存</button><button class="btn" type="button" onclick="javascript:history.go(-1)">返回</button>
  </p>
  </div>
  </s:form>
</div>
</body>
</html>
