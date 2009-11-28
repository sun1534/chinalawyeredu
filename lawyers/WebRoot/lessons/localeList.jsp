<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>现场课程</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}

.tab {
	font-size: 9pt;
	color: #666666;
	background-color: #F2F8FF;
	background-repeat: no-repeat;
	background-position: right bottom;
	height: 15px;
}
-->
</style>

<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../commonajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
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
					${navigator}
				</td>
			</tr>
		</table>
		<s:form name="form1" action="lessonsList" method="post">
			<s:hidden name="lessonstyle"/>
			<s:hidden name="pageNo" /> 
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
			<tr>
				<td align="left">
		
				<span style="font-weight: bold">
			 
			 <s:if test="viewother">
			                 来源：
                  <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
             </s:if>
               老师:<s:textfield name="teachers" size="12"/>
            
   			 	    名称:<s:textfield name="title" size="15"/>
			 	课程年份:<s:select name="nianshenyear" headerKey="0" headerValue="全部" list="jifentime.years" onchange="document.form1.submit()"/>
			 <s:if test="nianshenyear!=0">
			 	（从【${jifentime.startstr}】到【${jifentime.endstr}】）
			 	</s:if>
			<s:submit value="查询"/>
			 	</span>
			 	
			 	</td>
			</tr>
     	 </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="page-table">
				<tr>
					<td align="right">
						${page.pageView}
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#EDEDED">
				<tr>
					<TD height="23" width="27%" align="center" background="../imagesa/top-bg1.gif">
						课程名称
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						主讲人
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课程类别
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课时学分
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						授课时间
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						授课地点
					</TD>
				 <s:if test="viewother">
				 <TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课程来源
					</TD>
				 </s:if>
				</tr>
				<s:iterator value="lessonlist" status="stat">
					<TR>
						<TD class="tab_content" align="left">
							<a href="lessonsView.pl?lessonid=${lessonid}">${title}</a>
						</TD>				
						<TD class="tab_content" align="center">
							${teachers}
						</TD>
						<TD class="tab_content" align="center">
						 <s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lessontype]"/>
                      
						</TD>
						
						<TD class="tab_content" align="center">
							${xuefen}
						</TD>

						<TD class="tab_content" align="center">
							
							<s:date name="lessondate" format="yyyy-MM-dd HH:mm"/>

						</TD>
						<TD class="tab_content" align="center">
							${lessonaddress}
						</TD>
					 <s:if test="viewother">
   <TD class="tab_content" align="center">
                         <s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/>
                       </TD>
						   </s:if>
					</TR>
				</s:iterator>
			
			</table>

		</s:form>

	</body>
</html>