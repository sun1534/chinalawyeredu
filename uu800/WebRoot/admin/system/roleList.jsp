<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="MainHtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <title>${sysName}</title>
 <link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/main.css" />
 <link rel="stylesheet" type="text/css" href="../style/css/page.css" />
  <script type="text/javascript">
	function page(pageNo)
	{
		document.getElementById("pageNo").value = pageNo;
		document.forms[0].submit();
	}
 </script>
</head>
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; <strong>角色管理</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div id="listArea">
			<div id="listBtn">
					<span class="add"><span class="inbtns"><a href="roleCreate!input.action" class="btn">添加</a></span></span>
				</div>
				<div class="listTable">
					<div class="tableMod">
						<table>
							<thead>
								<tr class="tabTh">
									<th class="t_role">角色名称</th>
									<th class="t_time">创建时间</th>
									<th class="t_pra">角色权限</th>
									<th class="t_op">操作</th>
								</tr>
							</thead>
							<tbody class="tabBody">
						    <s:iterator value="rolelist" status="stats">
		                      <TR>
		                        <TD class="t_role">${rolename}</TD>
		                        <TD class="t_time"><s:date name="createtime" format="yyyy-MM-dd"/></TD>
		                        <TD class="t_pra">
		                        <a href="roleRightEdit!input.action?roleid=${roleid}" class="t_edit">修改</a>
		                        </TD>
		                 	 	<td class="t_op">
		                 	 	<a href="roleView.action?roleid=${roleid}&pagenumber=${pagenumber}" class="t_view">查看</a> | 
		                 	 	<a href="roleEdit!input.action?roleid=${roleid}" class="t_edit">编辑</a> | 
		                 	 	<a href="roleDelete.action?roleid=${roleid}&pagenumber=${pagenumber}" class="t_del">删除</a>
		                 	 	</td>
		                      </TR>
                    		</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			${PageHtml }
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
 
</html>