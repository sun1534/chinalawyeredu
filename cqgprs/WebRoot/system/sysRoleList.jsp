<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-角色列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="sysRoleCreate!input.action";
}
function deleteRole(roleid){
   if(confirm('您确定要删除这个角色吗?'))
     window.location.href="sysRoleDelete.action?roleid="+roleid;
   return false;
}
</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>系统管理</a>＞<em>角色列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="sysUserList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
			
					<!-- 操作模块-->
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					<!--<input type="button" class="btnCancel" title="返 回" value="删　除"/>-->
					</div>
				  <div class="tablist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                          <th>角色名</th>
                          <th>级别</th>
                          <th>创建人员</th>
                          <th>备注信息</th>
                          <th>分配权限</th>
                          <th>修改</th>
                          <th>删除</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                          <td>${rolename}</td>
                          <td>${gradeid }</td>
                          <td>${createuser}</td>
                          <td>${comments }</td>
                          <td><a href="sysRoleRightPre.action?roleid=${roleid}">分配权限</a></td> 
                          <td><a href="sysRoleEdit!input.action?roleid=${roleid}">修改</a></td>
                          <td><a href="#" onclick="deleteRole('${roleid}')">【删除】</a></td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                 <!--    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit "/>
							   </td>
							</tr>
						 </tfoot>
			     -->
                    </table>
			  </div>

				<div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

