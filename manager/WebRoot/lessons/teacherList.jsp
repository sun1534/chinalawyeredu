<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>授课律师列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="teacherCreateEditPre.pl";
}

function deleteit(userid){
	  if(confirm("您确定要删除这个授课律师的信息吗？")){
	    window.location.href="teacherDelete.pl?userId="+userid+"&pageNo=${pageNo}";
	  }
}

function passwdReset(userid){
   var _url="../lessonajax/passwordReset.pl";
   $.getJSON(_url, { "userid": userid,"now":new Date().getTime()}, function(json){
     alert(json.changeok);
   });
}

</script>
</head>
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" class="baseFontBold">
    	<img src="../imagesa/b_02.gif" width="4" height="7"> 
    	当前位置： 授课律师列表
    	</td>
  </tr>
</table>
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" >
	<s:form name="form1" action="teacherList" method="post">
  <tr>
    <td valign="top">	
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr>
          <td height="24"  >
             <s:hidden name="pageNo" value="1"/>
        	 <s:textfield name="loginname" size="12" label="登录名"/>
             <s:textfield name="username" size="8" label="姓名"/> 
              
             <s:submit value=" 查 询 " cssClass="button"/>
          </td>
        </tr>
      </table>
   
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list_box">
        <tr  class="list_tdfunc">
          <td height="24" align="right" >
           ${page.pageView}
          </td>
        </tr>
      </table>
  
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
       	<TD height="23"  align="center" background="../imagesa/top-bg1.gif">姓名</TD>
       	  <TD align="center" background="../imagesa/top-bg1.gif">类别</TD>
       	  <TD align="center" background="../imagesa/top-bg1.gif">现有课件个数</TD>
       	   <TD align="center" background="../imagesa/top-bg1.gif">个人介绍</TD>
       	   <TD align="center" background="../imagesa/top-bg1.gif">个人主页</TD>
       	    <TD align="center" background="../imagesa/top-bg1.gif">电话号码</TD>
       	       <TD align="center" background="../imagesa/top-bg1.gif">邮箱地址</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">登录帐号</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">密码重置</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">修改</TD>
        <TD align="center" background="../imagesa/top-bg1.gif">删除</TD> 
      </tr>
      
	<s:iterator value="page.items" status="stat">
      <TR class="list_td01">
        <TD align="center">${username}</TD>
        <TD align="center">${teacherTypeStr}</TD>
        <TD align="center"><a href="teacherLessonsList.pl?teacherid=${userid }">${lessoncount}</a></TD>
        <TD align="center"><a href="teacherView.pl?userId=${userid }">查看</a></TD>
        <TD align="center">${website}</TD>
        <TD align="center">${mobile }</TD>
        <TD align="center">${email}</TD>
        <TD align="center">${loginname}</TD>
        <TD align="center"><a href="#" onClick="passwdReset('${userid}')">密码重置</a></TD>
        <TD align="center"><a href="teacherCreateEditPre.pl?userId=${userid}">修改</a></TD>
        <TD align="center"><a href="teacherDelete.pl?userId=${userid}">【删除】</a></TD>
      </TR>
      </s:iterator>
      <tr class="list_td01">
        <td  colspan="11" align="center">&nbsp;       </td>
      </tr>
       <tr class="list_tdfunc">
          <td height="24" colspan="11"  align="center"  >
         <INPUT type="button" onClick="return getAdd()"  value=" 新增授课老师 " name="addbutton" class="button">
&nbsp;          </td>
        </tr>
    </table>
      </td>
  </tr>
    </s:form>
</table>
</body>
</html>