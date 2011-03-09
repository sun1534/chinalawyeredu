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
 <script language=javascript>
<!--
function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}

function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
}
-->
</script>
</head>
 
<body >
	<div class="guildNav" id="guildNav">
		<div class="crumbs">
			<div class="inCrumbs">
				<div class="crumbsTit">&nbsp;</div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
	 <s:form name="form1" action="userRoleEdit.action" method="POST">                  
      <s:hidden name="userid" value="%{user.userid}"/>
		<div class="main">
			<div id="listArea">
				<div class="addModBtn">
					<table class="addTable">
						<tbody>
							<tr>
								<td class="w100"></td>
								<td><span class="saveBtn"><span class="inbtns"><input name="edit" class="btn" type=submit value="保 存"/> </span></span>
									<span class="backBtn"><span class="inbtns"><a href="javascript:history.back(-1);" class="btn">返 回</a></span></span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="DataAddArea">
					<div class="addMod">
						<table class="addTable">
							<tbody>
								<tr>
									<th class="fTit w100">用户ID：</th>
									<td>${user.userid}</td>
								</tr>
								<tr>
									<th class="fTit w100">用户名称：</th>
									<td>${user.username}</td>
								</tr>
								<tr>
									<th class="fTit" valign="top">角色列表：</th>
									<td colspan="3">
										<div class="listTable">
											<div class="tableMod">
												<table>
													<thead>
														<tr class="tabTh">
															<th class="t_chkNo"><input type="checkbox" name="selectAll" onClick="getCheckAll()" /></th>
															<th class="t_menu_prq">角色名称</th>
															<th class="t_prq_id">权限Id</th>
														</tr>
													</thead>
													<tbody class="tabBody">
														  <s:iterator value="rolelist" status="status">
								                            <TR>
								                              <TD class="t_chkNo"><INPUT type="checkbox" value='${roleid}' name="check" ${checked?"checked":""}/></TD>
								                              <TD class="t_menu_prq"> ${rolename} </TD>
								                              <TD class="t_prq_id">${roleid}</TD>
								                            </TR>
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
 

