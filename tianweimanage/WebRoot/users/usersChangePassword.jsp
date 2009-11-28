<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-密码修改</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="all"/>
</head>
<body>

<div class="oper"> 您的位置：	修改用户密码 </div>
<div class="maincontent">
  <s:form name="form1" action="usersChangePassword" method="post" validate="true">
  <div class="tablebox">
 
    
    <table border="0" cellspacing="0" cellpadding="0" width="90%">
    
        <tr>
        <td class="tar">新密码：</td>
        <td>

<s:hidden name="userid"/>
<s:password name="newpass" size="25" maxlength="25"/></td>
      </tr>
      <tr class="odd">
        <td class="tar">重新输入新密码：</td>
        <td><s:password name="newpassagain" size="25" maxlength="25"/>
     
        </td>
      </tr>
      
      <tr>
        <td class="tar" colspan="2">&nbsp;</td>
       
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