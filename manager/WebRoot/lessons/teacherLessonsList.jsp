<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>授课老师课程列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
	<script type="text/javascript" src="../js/common.js"></script>
		<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
		<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="lessonsCreate!input.pl";
}
//新增视频或者语音课程
function getAddTeacherLessons(){
	window.location.href="lessonsCreate!input.pl?onlyonline=2";
}
function deleteid(lessonid){
  if(confirm('您确定要删除这个课程吗')){ 
  	 window.location.href="lessonsDelete.pl?type=teacher&lessonid="+lessonid+"&pageNo=${pageNo}";
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
					授课老师课程列表
				</td>
			</tr>
		</table>
		<table width="99%" height="316" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<s:form name="form1" action="teacherLessonsList" method="post">
			<tr>
				<td valign="top">
			
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
						<tr>
							<td align="left">
							<s:hidden name="pageNo" value="1"/> 
            
						 	文件类型:<s:select name="onlineType" list="#{'-1':'全部','0':'视频','1':'音频'}"/>
                            课程类型:
                            	<s:select name="lessontype" list="@com.changpeng.lessons.util.CommonDatas@LessonType" headerKey="0" headerValue="全部"/>
						 
                            名称:<s:textfield name="title" size="12"/>
                            <s:if test="!listall">
                            <s:hidden name="teacherid"/>
                            </s:if>
                            <s:else>
                        授课老师:<s:select name="teacherid" list="teacherList" listKey="userid" listValue="username" headerKey="0" headerValue="全部"/>
                            
                            </s:else>
			
							<s:submit value=" 查 询 " cssClass="button" /></td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="page-table">
						<tr>
							<td align="right">${page.pageView}</td>
						</tr>
					</table>

					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
						 <!--<TD align="center" background="../imagesa/top-bg1.gif">
					        <s:checkbox name="checkAll" onclick="getCheckAll(this)"/>
					      </TD>-->
							<TD height="23" align="center" width="250px" background="../imagesa/top-bg1.gif">
								课程名称
							</TD>
						
							<TD align="center" width="15%" background="../imagesa/top-bg1.gif">
								主讲人
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								学分
							</TD>
								<TD align="center" background="../imagesa/top-bg1.gif">
								文件类型
							</TD><TD align="center" background="../imagesa/top-bg1.gif">
								画质
							</TD><TD align="center" background="../imagesa/top-bg1.gif">
								音质
							</TD><TD align="center" background="../imagesa/top-bg1.gif">
								状态
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								价格
							</TD>
                          <TD align="center" background="../imagesa/top-bg1.gif">
								共享
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">授课时间</TD>
<TD align="center" background="../imagesa/top-bg1.gif">课程来源</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								修改
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								删除
							</TD>
						</tr>

						<s:iterator value="page.items" status="stat">
						<TR>
				
       
							<TD class="tab_content" align="left" title="${title}">
								<a href="lessonsView.pl?lessonid=${lessonid}">${titleTrim}</a>
							</TD>
						
							<TD class="tab_content" align="center">
					   	<div style="overflow:hidden;text-overflow:ellipsis;">${teachers}</div>
							</TD>
							<TD class="tab_content" align="center">${xuefen}</TD>
								<TD class="tab_content" align="center">
								<s:if test="onlineType==1">		
								<font color='red'>音频</font>
								</s:if>
								<s:else>视频
								</s:else>
								</TD>
							<TD class="tab_content" align="center">
								${videoQualityStr }
							</TD><TD class="tab_content" align="center">
								${audioQualityStr}
							</TD><TD class="tab_content" align="center">
								<s:if test="deleteflag">
								禁用
								</s:if>
								<s:else>正常
								</s:else>
							</TD>
								<TD class="tab_content" align="center">
								${price}
							</TD>
	                <TD class="tab_content" align="center">
	               
	                <a href="lessonsShared!input.pl?lessonid=${lessonid}&pageNo=${pageNo }">共享</a>
	              
	                </TD>
							<TD class="tab_content" align="center">   <s:date name="lessondate" format="yyyy-MM-dd HH:mm"/></TD>
                     <TD class="tab_content" align="center">

                              <s:property value="@com.changpeng.system.util.CommonDatas@groups[groupid]"/>
                              
							</TD>
							<TD class="tab_content" align="center">
							
                                    <a href="lessonsEdit!input.pl?onlyonline=1&lessonid=${lessonid}&pageNo=${pageNo }">修改</a>
							
						
							</TD>
							<TD class="tab_content" align="center">
							
									<a href="#" onClick="deleteid('${lessonid}')">【删除】</a>
							
							
								
							</TD>
						</TR>

 					</s:iterator>

						<tr style="background-color=#F1F1ED">
							<td colspan="7" align="center">&nbsp;
							</td>
						</tr>

					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr>
				<td height="24" align="center" background="../imagesa/login_bg1.gif">
				
							
									
                                    &nbsp;<INPUT type="button" onClick="return getAddTeacherLessons()" value="新增视频课程"
									name="addbutton" class="button">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:form>




		</table>
	</body>
</html>