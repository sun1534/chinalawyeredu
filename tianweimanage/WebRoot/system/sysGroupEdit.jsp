<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}-部门修改</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="all"/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript">
$(document).ready(function(){
 $("#_tablebox tr:even").addClass("odd");
});
</script>

</head>
<body>
<div class="oper"> 你的位置：部门管理-部门信息修改 </div>
<div class="maincontent">
 <s:form name="form1" action="sysGroupEdit" method="post" validate="true">
  <div class="tablebox">
    <p>红色<span class="cR">*</span>表示的选项为<span class="cR">必填</span></p>
    
    <table border="0" cellspacing="0" cellpadding="0" width="90%" id="_tablebox">
      <tr>
        <td width="20%" class="tar">上级部门：</td>
        <td>
     <s:select name="sysGroup.parentid" list="parentList" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
        </td>
      </tr>
      <tr>
        <td width="20%" class="tar">部门名称：</td>
        <td>
       <s:textfield name="sysGroup.groupname" size="30" maxlength="150" cssClass="text1" required="true"/>
         <span class="cR">不能为空</span>
        </td>
      </tr>
         <tr>
        <td width="20%" class="tar">部门简称：</td>
        <td>
       <s:textfield name="sysGroup.groupenname" size="25" maxlength="150" cssClass="text1" required="true"/>
       
        </td>
      </tr>
        <tr>
        <td width="20%" class="tar">联系人：</td>
        <td>
       <s:textfield name="sysGroup.contacter" size="15" maxlength="150" cssClass="text1" required="true"/>
       
        </td>
      </tr>
        <tr>
        <td width="20%" class="tar">联系电话：</td>
        <td>
       <s:textfield name="sysGroup.phone" size="15" maxlength="150" cssClass="text1" required="true"/>
       
        </td>
      </tr>
        <tr>
        <td width="20%" class="tar">传真：</td>
        <td>
       <s:textfield name="sysGroup.fax" size="15" maxlength="150" cssClass="text1" required="true"/>
       
        </td>
      </tr>
       <tr>
        <td width="20%" class="tar">备注说明：</td>
      
    
        <td><s:textarea name="sysGroup.comments" rows="4" cols="50"/></td>
      </tr>
      
      <tr>
        <td colspan="2">&nbsp;</td>
       
      </tr>
    </table>
    <p class="pbtn">
  <button class="btn" type="submit" id="save">保存</button>
  <button class="btn" type="button" onclick="javascript:history.go(-1)">返回</button>
  </p>
  </div>
  </s:form>
</div>
</body>
</html>
