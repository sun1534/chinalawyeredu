<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户权限分配</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/dtree.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/dtreeselect.js"></script>
<script language="javascript" type="text/javascript">

function newselright(objid,objtext,checked){
  var _select=document.getElementById("assignedRightcodeId");
  if(checked){
        var _opt=new Option();
	  	var opt = new Option(objtext, objid);
		_select.options.add(opt)
   }else{
     for(var i=0;i<_select.options.length;i++){
       if(_select.options[i].value==objid){
          _select.remove(i);
           i=i-1;
       }
     }
   }
}
function selright(obj,objtext){
  var _select=document.getElementById("assignedRightcodeId");
  if(obj.checked){
        var _opt=new Option();
	  	var opt = new Option(objtext, obj.value);
		_select.options.add(opt)
   }else{
     for(var i=0;i<_select.options.length;i++){
       if(_select.options[i].value==obj.value){
          _select.remove(i);
           i=i-1;
       }
     }
   }
}
</script>
</head>
<body>
<div class="oper"> 你的位置：	
  ${navigator}
</div>
<div class="maincontent">

  <div class="tablebox">
    <p style="display:none">红色<span class="cR">*</span>表示的选项为<span class="cR">必填</span></p>
    
    <table border="0" cellspacing="0" cellpadding="0" width="90%" id="_tablebox">
      <tr>
        <td width="38%" class="tar" style="text-align:center;font:bold">请选择需分配的权限</td>
        <td align="center"  style="font:bold">已分配权限列表</td>
      </tr>
       <tr>
        <td width="52%" class="tar">
        <div style="margin-top:3px;text-align:left;overflow:auto;height:350px">
       <div id="dtree1" class="dtree">
	<script type="text/javascript">
		d = new dTree('d',"dtree1",'form1');
		d.config.useIcons =true;
		d.add(0,-1,'权限列表');
		<s:iterator value="allRights" status="stat">
		 var _checked="";
		 <s:if test="assignedRightcode.contains(rightcode)">
		   _checked=" checked=\"checked\"";
		 </s:if>
		d.add("${rightcode}","${parentcode}","${rightname}","","${rightname}","","","","",_checked);
		  </s:iterator>
	document.write(d);
		d.openAll();
	</script>
</div></div>
        </td>
        <td>  
       <s:form name="form1" action="sysUserRight" method="post" validate="true">
        <s:hidden name="userid"/>
        <select name="assignedRightcode" id="assignedRightcodeId" style="width:250px;height:360px" multiple="multiple">
          <s:iterator value="assignedRights" status="stat">
           <option value="${rightcode}">${rightname}</option>
          </s:iterator>
         </select> 
       </s:form>
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
    </table>
    <p class="pbtn">
  <button class="btn" type="button" id="save" onclick="javascript:formsub()">保存</button><button class="btn" type="button" onclick="javascript:history.go(-1)">返回</button>
  <script language="javascript" type="text/javascript">
  function formsub(){
    //提交之前将所有的选项都选中
  var _select=document.getElementById("assignedRightcodeId");
     for(var i=0;i<_select.options.length;i++){
         _select.options[i].selected=true;
     }
     document.form1.submit();
  }
  </script>
  </p>
  </div>
</div>
</body>
</html>
