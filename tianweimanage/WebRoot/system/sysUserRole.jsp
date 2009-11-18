<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="all"/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
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

function getChildren(locationid){
  $("#locationid").attr({ value: $("#province")[0].value }); 

  if(__roleid==2){//只在是地市公司的时候才用到这个
  $.getJSON("../commonajax/getChildLocations.action", { "locationid": locationid}, function(json){
     $("#city")[0].length=0;
     for(var k in json.areas)  
     {     
	   var _o=new Option(json.areas[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
 }
}

function getCity(val){
  $("#locationid").attr({ value: $("#city")[0].value }); 
}
</script>

</head>
<body>
<div class="oper"> 你的位置：	
  ${navigator}
</div>
<div class="maincontent">
 <s:form name="form1" action="sysUserRole" method="post" validate="true">
  <div class="tablebox">
    <p style="display:none">红色<span class="cR">*</span>表示的选项为<span class="cR">必填</span></p>
    
    <table border="0" cellspacing="0" cellpadding="0" width="90%" id="_tablebox">
      <tr>
        <td width="38%" class="tar">请选择角色：<br/>(已勾选的表示现在选中的角色)：</td>
        <td>
          <s:hidden name="userid"/>
         <%
		// <s:checkboxlist name="sysRoles" list="roles" onclick="checkRole(this,this.value)"/>
		 %>
       
          <s:checkboxlist name="roleid" list="roles" onclick="checkRole(this,this.value)"/>

        </td>
      </tr>
     <!-- <tr>
        <td colspan="2"><span class="cR">注意:省份管理员和地市管理员角色只能2选1</span></td>
      </tr>-->
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
