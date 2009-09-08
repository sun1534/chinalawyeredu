<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=com.changpeng.common.Constants.SYS_NAME%>
</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript" type="text/javascript"> 
var issubmit=false;
function submitform (){
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
  if(!issubmit){//如果没有提交,直接关闭窗口的话
     var url="../commonajax/ajaxlogout.pl";
     var pars = 'now=' + new Date().getTime();
     var myAjax = new Ajax.Request(
                    url,
                    {method: 'post', parameters: pars, 
                     onComplete: function(transport){
                        // var response = transport.responseText || "no response text";
                        // alert("Success! \n\n" + response);
                                                    }
                    }
                    );

  }
}
</script>
</head>

<body leftmargin="0" topmargin="0" onunload="exitform()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td  align="right" background="../imagesa/top-bg1.jpg">
       	<form name="form1" method="post" action="../common/logout.pl" target="_top">
       
       <table width="28%" border="0" cellspacing="0" cellpadding="0" background="../imagesa/bgright.jpg">
        <tr> 
            <td height="26"  align="right">
                 <a href="../index/workspace.pl" target="main">返回首页</a> ｜
                 <a href="../common/passwdChange!input.pl" target="main">修改密码</a> ｜
                  <s:if test="sysUser.roleid!=null&&sysUser.roleid==1">
                 <a href="../shouce/lawyers.html" target="_blank"> 操作手册</a> ｜
                 </s:if>
                 <s:if test="isofficeadminer">
                 <a href="../shouce/offices.html" target="_blank"> 操作手册</a> ｜
                 </s:if>
                 <a href="#" onclick="submitform()">【退出】</a>         
          </td> 
         
        </tr>
        <tr>
          <td height="24" align="right">
               ${sysUser.username} 您好,欢迎登录培训系统！  
          </td>
        </tr>
</table> </form>
    </td>
  </tr>
</table>
<table width="100%" height="2" border="0" cellpadding="0" cellspacing="0" bgcolor="eeeeee">
  <tr>
    <td> </td>
  </tr>
</table>
</body>
</html>
