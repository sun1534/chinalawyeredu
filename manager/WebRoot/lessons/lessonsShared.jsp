<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<html>
	<head>
		<title>编辑课程</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="JavaScript"> 
 function shareit(){ 
    var _select=document.getElementById("lessonsShared_sharedgroupids");
    if(_select.value=="all"){
	   for(var i=0;i<_select.options.length;i++){
	      if(_select.options[i].value!="all")
          _select.options[i].selected=true;
		  else
		  _select.options[i].selected=false;
       }   
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
					<img src="../imagesa/b_02.gif" width="4" height="7">
					课程共享设置
				</td>
			</tr>
		</table>
		<s:form action="lessonsShared" method="post" name="form1" validate="true">
		
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0">
			<tr>
				<td valign="top" bgcolor="#FFFFFF">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
							<td height="24" colspan="5" background="../imagesa/top-bg2.gif"
								class="baseFontBold">
				
						<font color=red>
			<s:fielderror/> 
			</font>
							</td>
						</tr>
						<tr>
							<td width="15%" class="tab_content1" align="right">
								已共享给：
								<s:hidden name="lessonid"/>
							</td>
							<td width="85%" colspan="2" class="tab_content1">
							<s:iterator value="sharedgroupids" status="stat">
						
							    <s:property value="@com.changpeng.system.util.CommonDatas@groups[sharedgroupids[#stat.index]]"/>
                         
							<br/>
							</s:iterator>
							
							</td>
						</tr>
						
                      
					  <tr id="sharedto" style="display:${display}">
							<td class="tab_content1" align="right">
									共享修改为：
							</td>
							<td colspan="2" class="tab_content1">

                             <s:select name="sharedgroupids" list="shouldsharedgroupids" listKey="groupid" listValue="groupname" cssStyle="width:200px;height:350px" multiple="true" headerKey="all" headerValue="共享给全部" onchange="shareit()"/><span>(按住Ctrl键选择多个)</span>
							</td>
						</tr>
						
                        
					
						<tr>
							<td class="tab_content">
							</td>
							<td colspan="2" class="tab_content">
					
									<input type="submit" class="button" value=" 确认 ">
									&nbsp;&nbsp;
									<input type="reset" class="button" value=" 重置 ">
									&nbsp;&nbsp;
									<input type="button" class="button" value=" 返回 " onClick="history.go(-1)">
							</td>
						</tr>
					</table>
			</s:form>
	</body>
</html>