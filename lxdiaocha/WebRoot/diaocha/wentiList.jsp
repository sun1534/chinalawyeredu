<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>调查问题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
		<!-- 页面不被缓存 -->
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache"> 
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
		<meta HTTP-EQUIV="expires" CONTENT="0"> 		
<style type="text/css">

.button
{
	border-top: #ffffff 1px solid;
	border-left: #ffffff 1px solid;
	border-bottom: #848284 1px solid;
	border-right: #848284 1px solid;
	background-color: #b2d3ff;
	height: 20px;
	color: #000000;
	background-repeat: repeat;
}
		.textfield {
			BORDER-RIGHT: 0px   solid;   
			BORDER-TOP:   0px   solid;   
			BORDER-LEFT:   0px   solid;   
			BORDER-BOTTOM:   1px   solid;
			background:#F4FBFF;
		}	
		body{background:#F4FBFF;font-size:14px;}
		td{border-collapse:collapse;padding-bottom:10px;font-size:12px;line-height:20px}
		th{padding-left:5px;padding-right:5px;padding-top:3px;font-size:14px;font-weight:normal;line-height:20px;border-top:2px solid #76BAC2;background:#B0E0E6;text-align:left;color:#005681;}
.STYLE1 {color: #FF0000}
</style>	

<script>
	function delwenti(){
		if(!confirm("确定要删除该调查问题吗（不可恢复）？"))
			return false;
		return true;
	}
	function setLogic(wentiid,optionid){
		var popwin=window.open("wentiList!select.pl?diaochaid=${diaocha.diaochaid}&wentiid="+wentiid+"&optionid="+optionid,"设置逻辑跳转","toolbar=no,location=no,width=400,height=305,menubar=no,scrollbars=yes,resizable=no,status=no");
		popwin.moveTo((screen.width+200)/2,(screen.height-400)/2);
	}
</script>
<% 
  //页面不缓存
  response.setHeader("Cache-Control","no-store"); 
  response.setHeader("Pragrma","no-cache"); 
  response.setDateHeader("Expires",0); 
%> 
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
		<br>
		<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD valign="top" bgColor=#F9F9F7>		
					<TABLE width="100%" border=0 align=center cellPadding=3 cellSpacing=1>
						<TBODY>
							<TR>
								<TD class="tab_content">
									<H2 style="line-height:40px" align="center">
										调查名称:${diaocha.title}
									</H2>
								</TD>
							</TR>
						
							<!-- 该调查存在分类 -->
							<s:if test="hastype">
								<s:iterator value="diaochatypes" status="stat">
								  <tr><th colspan="2" align="left">${typename}</th></tr>
									<s:iterator value="diaochawentis" status="status">
									<TR>
										<TD>
											${status.index+1}.${title}&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="wentiDelete.pl?wentiid=${wentiid}" onclick="return delwenti();">
											<img src="../imagesa/icon_delete.gif" alt="删除" border="0"/></a>
											&nbsp;
											<a href="wentiEdit!input.pl?wentiid=${wentiid}"><img src="../imagesa/icon_edit.gif" alt="编辑" border="0"/></a>
										</TD>
									</TR>
									<s:if test="wentileixing==1">
									<s:iterator value="diaochaoptions" status="status2">
									<TR>
										<TD>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="radio" name="wenti${status.index+1}">${options}.${title}
											<a href="#" onclick="javascript:setLogic(${wentiid},${optionid})"><img src="../imagesa/icon_edit.gif" alt="设置逻辑跳转" border="0"/></a>

											<s:if test="others"><input type="text" name="text${status.index+1}" size="40" maxlength="50" class="textfield" /></s:if>
										</TD>
									</TR>
									</s:iterator>
									</s:if>
									<s:elseif test="wentileixing==2">
									<s:iterator value="diaochaoptions" status="status2">
									<TR>
										<TD>
									
											&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="wenti${status.index+1}">${options}.${title}
											<s:if test="others"><input type="text" name="text${status.index+1}" size="80" maxlength="80" class="textfield" /></s:if>
										</TD>
									</TR>
									</s:iterator>
									</s:elseif>
									<s:elseif test="wentileixing==3">										
									<TR>
										<TD>
											&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="wenti${status.index+1}" size="80" maxlength="80" class="textfield" />
										</TD>
									</TR>
									</s:elseif>									
									<s:elseif test="wentileixing==4">										
									<TR>
										<TD>
											&nbsp;&nbsp;&nbsp;&nbsp;<textarea name="wenti${status.index+1}" cols="60" rows="5"></textarea>
										</TD>
									</TR>
									</s:elseif>	
									</s:iterator>
								</s:iterator>
							</s:if>
							
							<!-- 该调查没有分类 -->
							<s:else>
							<s:iterator value="wentilist" status="status">
							<TR>
								<TD>
									${status.index+1}.${title}(顺序:${listorder })&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="wentiDelete.pl?wentiid=${wentiid}" onclick="return delwenti();">
											<img src="../imagesa/icon_delete.gif" alt="删除" border="0"/></a>
											&nbsp;
									<a href="wentiEdit!input.pl?wentiid=${wentiid}">
											<img src="../imagesa/icon_edit.gif" alt="编辑" border="0"/></a>
								</TD>
							</TR>
							<s:if test="wentileixing==1">
							<s:iterator value="diaochaoptions" status="status2">
							<TR>
								<TD>
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="wenti${status.index+1}">${options}.${title}
									
									<a href="#" onclick="javascript:setLogic(${wentiid},${optionid})"><img src="../imagesa/icon_edit.gif" alt="设置逻辑跳转" border="0"/></a>
									
									<s:if test="others"><input type="text" name="text${status.index+1}" size="40" maxlength="50" class="textfield" /></s:if>
								</TD>
							</TR>
							</s:iterator>
							</s:if>
							<s:elseif test="wentileixing==2">
							<s:iterator value="diaochaoptions" status="status2">
							<TR>
								<TD>
							
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="wenti${status.index+1}">${options}.${title}
									<s:if test="others"><input type="text" name="text${status.index+1}" size="80" maxlength="80" class="textfield" /></s:if>
								</TD>
							</TR>
							</s:iterator>
							</s:elseif>
							<s:elseif test="wentileixing==3">										
							<TR>
								<TD>
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="wenti${status.index+1}" size="80" maxlength="80" class="textfield" />
								</TD>
							</TR>
							</s:elseif>									
							<s:elseif test="wentileixing==4">										
							<TR>
								<TD>
									&nbsp;&nbsp;&nbsp;&nbsp;<textarea name="wenti${status.index+1}" cols="60" rows="5"></textarea>
								</TD>
							</TR>
							</s:elseif>	
							</s:iterator>
							</s:else>
						</TBODY>
					</TABLE>
								
					
					<p align="center">
								<input type="button" class="button" value="新增问题" onclick="location.href='wentiCreate!input.pl?diaochaid=${diaocha.diaochaid}'">
								&nbsp;&nbsp;
								<input type="button" class="button" value=" 返  回 " onclick="location.href='diaochaList.pl'">	
					</p>
				</TD>
			</TR>
		</table>
	</body>
</html>