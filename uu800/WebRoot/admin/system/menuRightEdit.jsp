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
				<div class="crumbsTit"><em>您所在的位置：</em>系统管理 &gt; 菜单管理 &gt; <strong>菜单权限编辑</strong></div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	 <s:form name="form1" action="menuRightEdit" method="POST">                  
        <s:hidden name="menuid" value="%{menu.menuid}"/>
		<div class="main">
			<div id="listArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td>
								<span class="saveBtn"><span class="inbtns"><button class="btn" type="submit">保 存</button></span></span>
								<span class="backBtn"><span class="inbtns"><a href="javascript:history.back(-1);" class="btn">返 回</a></span></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="DataAddArea">
					<div class="addMod">
						<table class="addTable">
							<tbody>
								<tr>
									<th class="fTit w100">菜单编码：</th>
									<td>${menu.menuid}</td>
								</tr>
								<tr>
									<th class="fTit w100">菜单名称：</th>
									<td>/${menu.parentMenu.menuname}/${menu.menuname}</td>
								</tr>
								<tr>
									<th class="fTit" valign="top">菜单权限：</th>
									<td colspan="3">
										<div class="listTable">
											<div class="tableMod">
												<table>
													<thead>
														<tr class="tabTh">
															<th class="t_menu_prq">权限名称</th>
															<th class="t_prq_code">权限编码</th>
															<th class="t_prq_id">权限Id</th>
														</tr>
													</thead>
													<tbody class="tabBody">
														  <s:iterator value="rightlist" status="status">
							                              <s:if test="grade==1">
							                                <TR class=listline>
							                                  <TD >├ <em class="str">${rightname}</em></TD>
							                                  <TD align="left" >${rightcode}</TD>
							                                  <TD align="left" >${rightid}</TD>
							                                </TR>
							                              </s:if>
							                              <s:if test="grade==2">
							                                <TR class=listline>
							                                  <TD >│　├<em class="str"> ${rightname}</em></TD>
							                                  <TD align="left" >${rightcode}</TD>
							                                  <TD align="left" >${rightid}</TD>
							                                </TR>
							                              </s:if>
							                              <s:if test="grade==3">
							                                <TR class=listline>
							                                  <TD >│　│　├
							                                    <INPUT type="checkbox" value='${rightid}' name="check" ${checked?"checked":""}>
							                                    ${rightname}</TD>
							                                  <TD align="left" >${rightcode}</TD>
							                                  <TD align="left" >${rightid}</TD>
							                                </TR>
							                              </s:if>
							                            </s:iterator>
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</s:form>
	</div>
	<script type="text/javascript" src="../style/js/jquery.js"></script>
	<script type="text/javascript" src="../style/js/tablegrid.js"></script>
</body>
 
</html>
 


