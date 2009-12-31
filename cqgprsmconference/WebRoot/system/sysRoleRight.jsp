<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-角色权限修改</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/dtree.css" />
 <script type="text/javascript" src="../js/dtreeselect.js"></script>
<script type="text/javascript">
function newselright(objid,objtext,checked){
//alert(objid+",,"+objtext+",,"+checked);
  var _select=document.getElementById("assignedRightcodeId");
//  alert(_select);
  if(checked){
 // alert("===");
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

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>系统管理</a>＞<em>角色权限修改</em>
				</div>
			</div>
	</div>
	
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="button" class="btnSubmit" title="保 存" value="保 存" onclick="javascript:formsub()"/>
					<input type="button" class="btnBack" title="返 回" value="返 回" onclick="history.go(-1)"/>
				</div>
				<div class="operateTab">
				<!--	<div class="operateTabInfo">错误提示信息</div> -->
					
					<table class="operateTabBox">
						<tbody>
							<tr>
								<td class="w350 fname" style="text-align:center;font:bold">请选择需分配的权限</td>
								<td style="text-align:center;font:bold">
								 已分配权限列表
								</td>
							</tr>
							<tr>
								<td class="fname" valign="top">
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
</div>
</div>		
								</td>
								<td>
  <s:form name="form1" action="sysRoleRight" method="post">
        <s:hidden name="roleid"/>
        <select name="assignedRightcode" id="assignedRightcodeId" style="width:250px;height:360px" multiple="multiple">
          <s:iterator value="assignedRights" status="stat">
           <option value="${rightcode}">${rightname}</option>
          </s:iterator>
         </select> 
       </s:form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
  <script type="text/javascript">
  function formsub(){
    //提交之前将所有的选项都选中
  var _select=document.getElementById("assignedRightcodeId");
     for(var i=0;i<_select.options.length;i++){
         _select.options[i].selected=true;
     }
     document.form1.submit();
  }
  </script>
</body>

</html>

