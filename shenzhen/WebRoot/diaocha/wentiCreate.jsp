<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>新建调查问题</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script>
	function setLeixing(leixingid){
		if(leixingid==1||leixingid==2){
			document.getElementById('optionscontent').style.display="";
			document.getElementById('options').style.display=""
		}else{
			document.getElementById('optionscontent').style.display="none";
			document.getElementById('options').style.display="none";
		}
	}
</script>
</head>
	<body>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
				<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="images/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
		<s:form action="wentiCreate" method="post" name="form1" validate="true">
		<input type="hidden" name="diaochaid" value="${diaochaid}">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#C2D6F0">
			<s:fielderror/>
			<tr>
				<td valign="top" bgcolor="#FFFFFF">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
							<td height="24" colspan="5" background="../imagesa/top-bg2.gif"
								class="baseFontBold">
								<div align="left">
								</div>
							</td>
						</tr>
						<s:if test="hastype">
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   请选择问题类别：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:select name="typeid" listKey="typeid"  listValue="typename" list="diaochatypes" />
							</td>
						</tr>
						</s:if>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   问题：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textarea name="wenti.title" rows="4" cols="70" value="请在此处键入您的问题..." />
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   答案类型：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:radio name="wenti.wentileixing" list="#{1:'单选',2:'复选',3:'单行文本',4:'多行文本'}" value="1" onclick="setLeixing(this.value)"/>
							</td>
						</tr>
						<tr id="isbixu">
							<td width="20%" class="tab_content1" align="right">
								   是否必答：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:checkbox name="wenti.isbixu" />
							</td>
						</tr>
						<tr id="optionscontent">
							<td width="20%" class="tab_content1" align="right">
								   回车分行键入每个选项：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textarea name="optionscontent" rows="5" cols="70" value="输入选项 #1
输入选项 #2
输入选项 #3
输入选项 #4"/>
							</td>
						</tr>
						
						 
						
						<tr id="options">
							<td width="20%" class="tab_content1" align="right">
								   是否输入其他内容：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:checkbox name="others" />
							</td>
						</tr>
							<tr id="listorder">
							<td width="20%" class="tab_content1" align="right">
								   显示顺序(输入数字)：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
							 <s:textfield name="wenti.listorder" size="5"/>
							</td>
						</tr>
						<!--
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   是否逻辑选项：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:checkbox name="wenti.islogic" />
							</td>
						</tr>
						 -->
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   问题说明：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textarea name="wenti.remarks" rows="5" cols="50" />
							</td>
						</tr>
						<tr>
							<td class="tab_content">
							</td>
							<td colspan="2" class="tab_content">
					
									<input type="submit" class="button" value="确认">
									&nbsp;&nbsp;
									<input type="reset" class="button" value="重置">
									&nbsp;&nbsp;
									<input type="button" class="button" value="返回" onClick="history.go(-1)">
							</td>
						</tr>
					</table>
			</s:form>
	</body>
</html>