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
</head>
 
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; <strong>菜单管理</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div id="listArea">
				<div class="listTable">
					<div class="tableMod">
						<table>
							<thead>
								<tr class="tabTh">
									<th class="t_menu">菜单名称</th>
									<th class="t_No">序号</th>
									<th class="t_url">菜单Url</th>
									<th class="t_url_target">目标框架</th>
									<th class="t_pra">菜单权限</th>
									<th class="t_op">操作</th>
								</tr>
							</thead>
							<tbody class="tabBody">
							<s:iterator value="menulist" status="status">
								<TR>
			                      <TD class="t_menu" align="left">┣ <em class="str">${menuname}</em></TD>
			                      <TD class="t_No">${orderid}</TD>
			                      <TD class="t_url">${linkurl}</TD>
			                      <TD class="t_url_target">${opentarget}</TD>
			                      <TD class="t_pra">&nbsp;</TD>
			                      <TD class="t_op">
			                    
			                          <a href="menuEdit!input.action?menuid=${menuid}" class="t_edit">编辑</a> | 
			                   
			                       	  <a href="menuView.action?menuid=${menuid}" class="t_view">查看</a>
			                      </TD>
			                      </TR>
			                  <s:iterator value="childMenus" status="status2">          
			                     <TR >
			                      <TD class="t_menu" align="left">　├─ <strong>${menuname}</strong></TD>
			                      <TD class="t_No">${orderid}</TD>
			                      <TD class="t_url">${linkurl}</TD>
			                      <TD class="t_url_target">${opentarget}</TD>
			                      <TD class="t_pra">
			                     
                      					<a href="menuRightEdit!input.action?menuid=${menuid}" class="t_edit">修改</a>
                    			
			                      </TD>
			                      <TD class="t_op">
			                    
			                          <a href="menuEdit!input.action?menuid=${menuid}" class="t_edit">编辑</a> | 
			                  
			                       	  <a href="menuView.action?menuid=${menuid}" class="t_view">查看</a>
			                      </TD>
			                      </TR>
			                  </s:iterator>
						 </s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
 
</html>
 

