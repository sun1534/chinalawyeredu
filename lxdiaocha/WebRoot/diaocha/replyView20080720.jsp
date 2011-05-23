<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>调查问题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">		
	<style type="text/css">
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
						<TD>
							<H2 style="line-height:40px" align="center">
								调查名称:${diaocha.title}
							</H2>
						</TD>
					</TR>
					<TR>
						<TD>
							<P>
								${diaocha.shuoming}
							</p>
						</TD>
					</TR>
				
					<!-- 该调查存在分类 -->
					<s:if test="hastype">
					<s:iterator value="diaochatypes" status="stat">
				    <tr><th colspan="2" align="left">${typename}</th></tr>
					<s:iterator value="diaochawentis" status="status">
					<TR>
						<TD>							
							${status.index+1}.${title}&nbsp;&nbsp;
							<s:if test="wentileixing==1||wentileixing==2">
							（<s:property value="@com.changpeng.diaocha.util.ReplyView@getReplycontent(replys)" />）
							
							<input type="hidden" name="bixu" value="${isbixu}">
							</s:if>
							
						</TD>
					</TR>
					<s:if test="wentileixing==1">
					<s:iterator value="diaochaoptions" status="status2">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio"  value="${options}" >${options}.${title}
							<s:if test="others">（<s:property value="@com.changpeng.diaocha.util.ReplyView@getOthers(replys)" />）</s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:if>
					<s:elseif test="wentileixing==2">
					<s:iterator value="diaochaoptions" status="options">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" value="${options}" >${options}.${title}
							<s:if test="others">（<s:property value="@com.changpeng.diaocha.util.ReplyView@getReplycontent(replys)" />）</s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:elseif>
					<s:elseif test="wentileixing==3">										
					<TR>
						<TD  class="tab_content">
							&nbsp;&nbsp;&nbsp;&nbsp;
					
							<input type="text" name="replys" size="80" maxlength="80" class="textfield" /><s:if test="isbixu"><span style="color:red">*</span></s:if><input type="hidden" name="bixu" value="${isbixu}">
						</TD>
					</TR>
					</s:elseif>									
					<s:elseif test="wentileixing==4">										
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							
							<textarea name="replys" cols="60" rows="5"></textarea><input type="hidden" name="bixu" value="${isbixu}"><s:if test="isbixu"><span style="color:red">*</span></s:if>
						</TD>
					</TR>
					</s:elseif>	
					</s:iterator>
					</s:iterator>
					</s:if>
					
					<!-- 该调查不存在分类 -->
					<s:else>
					<s:iterator value="wentilist" status="status">
					<TR>
						<TD>

							${status.index+1}.${title}&nbsp;&nbsp;
							<s:if test="wentileixing==1||wentileixing==2">
							（<input type="text" name="replys" id="wenti${status.index+1}" size="5" class="textfield"/>）
							<input type="hidden" name="bixu" value="${isbixu}">
							</s:if>
							<span id="desc" style="color:red"></span>
						</TD>
					</TR>
					<s:if test="wentileixing==1">
					<s:iterator value="diaochaoptions" status="status2">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" onclick="setRadio(this,'wenti${status.index+1}')" value="${options}" name="wenti${status.index+1}">${options}.${title}
							<s:if test="others"><input type="text" size="80" maxlength="80" class="textfield" name="other" /></s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:if>
					<s:elseif test="wentileixing==2">
					<s:iterator value="diaochaoptions" status="options">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="setCheckbox(this,'wenti${status.index+1}')" value="${options}" name="wenti${status.index+1}">${options}.${title}
							<s:if test="others"><input type="text" size="80" maxlength="80" class="textfield" name="other" /></s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:elseif>
					<s:elseif test="wentileixing==3">										
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
			
							<input type="text" name="replys" size="80" maxlength="80" class="textfield" /><s:if test="isbixu"><span style="color:red">*</span></s:if><input type="hidden" name="bixu" value="${isbixu}">
						</TD>
					</TR>
					</s:elseif>									
					<s:elseif test="wentileixing==4">										
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
				
							<textarea name="replys" cols="60" rows="5"></textarea><input type="hidden" name="bixu" value="${isbixu}"><s:if test="isbixu"><span style="color:red">*</span></s:if>
						</TD>
					</TR>
					</s:elseif>	
					</s:iterator>
					</s:else>
					
				</TBODY>
			</TABLE>
			
				
					<p align="center">
								<input type="button" class="button" value=" 返  回 " onclick="history.go(-1)">	
					</p>
				</TD>
			</TR>
		</table>
	
	</body>
</html>