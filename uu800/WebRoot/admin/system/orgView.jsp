<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
				<div class="crumbsTit">&nbsp;</div>
			</div>
		</div>
		<iframe class="overFast" src="about:blank"></iframe>
	</div>
	<div class="doc">
		<div class="main">
			<div id="listArea">
				<div id="listBtn">
					<span class="add"><span class="inbtns"> <input type=button  onClick="document.createRootForm.submit()" class="btn" value="添加根组织"/></span></span>
					<span class="add"><span class="inbtns"> <input type=button  onClick="document.createForm.submit()" class="btn" value="添加下属组织"></span></span>
					<span class="add"><span class="inbtns">
    				<input type=button  onClick="document.editForm.submit()" class="btn" value="修改组织"/>
    				</span>
    				</span>
					<span class="del"><span class="inbtns">
    				<input type=button  onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" class="btn" value="删除组织"/>
					</span></span>
				</div>
				<div id="DataAddArea">
					<div class="addMod">
						<table class="addTable">
							<tbody>
								<tr>
									<th class="fTit w100">名称：</th>
									<td colspan="3">${org.orgname}</td>
								</tr>
								<tr>
									<th class="fTit w100">简称：</th>
									<td>${org.orgshortname }</td>
									<th class="fTit w100">英文名称：</th>
									<td>${org.orgenname }</td>
								</tr>
								<tr>
									<th class="fTit w100">联系人：</th>
									<td>${org.contactperson }</td>
									<th class="fTit w100">联系电话：</th>
									<td>${org.contactphone }</td>
								</tr>
								<tr>
									<th class="fTit w100">手机号：</th>
									<td>${org.mobile }</td>
									<th class="fTit w100">传真：</th>
									<td>${org.faxnum }</td>
								</tr>
								<tr>
									<th class="fTit w100">说明：</th>
									<td colspan="3">${org.comments }</td>
								</tr>
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
 <s:form name="createRootForm" action="orgCreate!input.action" method="POST">
       <s:hidden name="orgid" value="0"/>
   </s:form>
   
   <s:form name="createForm" action="orgCreate!input.action" method="POST">
       <s:hidden name="orgid" value="%{org.orgid}"/>
   </s:form>
   
   <s:form name="editForm" action="orgEdit!input.action" method="POST">
       <s:hidden name="orgid" value="%{org.orgid}"/>     
   </s:form>
   <s:form name="deleteForm" action="orgDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   		<s:hidden name="orgid" value="%{org.orgid}"/>
   </s:form>
