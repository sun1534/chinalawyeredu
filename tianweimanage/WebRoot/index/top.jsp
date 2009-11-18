<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<link rel="stylesheet" type="text/css" href="../common/css/style.css" media="all"/>
<script type="text/javascript" src="../js/jquery.js"></script>

<script language="javascript" type="text/javascript">
var issubmit=false;
var f5pressed=false;
function submitform (){
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
	 // alert("f5pressed:::"+f5pressed+",,,"+window.location.href);
  if(!issubmit&&!f5pressed){//如果没有提交,直接关闭窗口的话  
    var url="../commonajax/ajaxlogout.action";
    $.post(url,{now:new Date().getTime()});
  }
  f5pressed=false;
}
function pressedF5(){
	//  alert("f5pressed");
	 
	  f5pressed=true;
}
</script>
</head>

<body>
<div id="header">
<form name="form1" method="post" action="../common/logout.action" target="_top">
    <p class="userinfo">
	<img src="../common/css/images/home.gif" width="22" height="20" border="0" alt="" align="absmiddle" /><strong><a href="../index/workspace.action" target="mainFrame">返回首页</a></strong>
	<img src="../common/css/images/hele.gif" width="22" height="20" border="0" alt="" align="absmiddle" /><strong>帮助</strong>
	</p>

    <p class="userinfo"><img src="../common/css/images/ico_06.gif" width="22" height="20" border="0" alt="" align="absmiddle" />
  
    ${sysUser.username}
    </strong>
	<a href="../common/passwdChange!input.action" target="mainFrame"><img src="../common/css/images/ico_07.gif" width="22" height="20" border="0" alt="" align="absmiddle" />修改密码</a> 
	<a href="#" onClick="submitform()"><img src="../common/css/images/ico_08.gif" width="22" height="20" border="0" alt="" align="absmiddle" />退出</a>
    </p>
    
   </form>
  </div>
</body>
</html>