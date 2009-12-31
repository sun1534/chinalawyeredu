<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-用户列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">
 $(document).ready(function() {
			$("#checkAll").click(selectAll);
			$("[name=check]").click(selectOne);
		});
		var l=0;
	function selectAll() {
			 var checkbox = $("#checkForm :checkbox");
			 if(!$(this).attr('checked')){
				checkbox.attr('checked','');
				checkbox.parent().parent().children().addClass("current");
				checkbox.parent().parent().children().removeClass("nomal");
			 }else{
				checkbox.attr('checked','checked');
				checkbox.parent().parent().children().addClass("nomal");
				checkbox.parent().parent().children().removeClass("current");
			}
			selectOne();
		}

		/*单选取值*/
		function selectOne(){
		//	var str="";
			$("[name=check]").each(function(){
				if($(this).attr('checked')){
					$(this).parent().parent().children().addClass("current");
					$(this).parent().parent().children().removeClass("nomal");
				//	str+=$(this).val()+"|";
					l=$("[name=check]:checked").size();
				}else{
					$(this).parent().parent().children().addClass("nomal");
					$(this).parent().parent().children().removeClass("current");
					$("#checkAll").attr('checked','');
				}
			})
      }
 
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="sysUserCreate!input.action";
}
function deleteUser(userid){
  if(confirm('您确定要删除这个用户吗?'))
    window.location.href="sysUserDelete.action?check="+userid;
  return false;
}
</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>系统管理</a>＞<em>管理员列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="sysUserList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
								 <td>登录名：<s:textfield name="loginname" size="10" cssClass="txt"/>&nbsp;</td>
								 <td><input type="submit" class="btnSubmit" title="查　询" value="查　询"/></td>
							     <td></td>
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块-->
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>
				  <div class="tablist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                          <th>
                          <input type="checkbox" class="checkbox" name="checkAll"  id="checkAll"/>
                          </th>
                          <th>姓名</th>
                          <th>账号</th>
                          <th>所在部门</th>
                          <th>当前状态</th>
                          <s:if test="hassetrole">
        <th>分配角色</th>
        </s:if>
          <s:if test="hassetrights">
        <th>分配权限</th>
        </s:if>
                          <th>修改</th>
                          <th>删除</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                          <td><input type="checkbox" class="checkbox" name="check" value="${userid }"/></td>
                          <td>${username}</td>
                          <td>${loginname }</td>
                          <td>${sysGroup.groupname}</td>
                          <td>  
<s:if test="status==0">
        激活
        </s:if>
        <s:else>
    <b>冻结</b>
        </s:else>
        </td>
              <s:if test="hassetrole">
        <td><a href="sysUserRolePre.action?userid=${userid}">角色分配</a></td>
        </s:if>
         <s:if test="hassetrights">
           <td><a href="sysUserRightPre.action?userid=${userid}">权限分配</a></td>
           </s:if>
            <td><a href="sysUserEdit!input.action?userid=${userid}">修改</a></td>
            <td><a href="#" onclick="deleteUser('${userid}')">【删除】</a></td>
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

