<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>在线课程列表</title>
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
function getAddOnline(){
	window.location.href="lessonsCreate!input.pl?onlyonline=1";
}
function deleteid(lessonid){
  if(confirm('您确定要删除这个课程吗')){
 
  	 window.location.href="lessonsDelete.pl?lessonid="+lessonid;
  }
}
function getCities(vallll){

  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
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
					${navigator}
				</td>
			</tr>
		</table>
		<table width="99%" height="316" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<s:form name="form1" action="lessonsOnlineList" method="post">
			<s:hidden name="lessonstyle"/>
			<tr>
				<td valign="top">
			
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
						<tr>
							<td align="left">
							<s:hidden name="pageNo" value="1"/> 
                            来源：
                  <%//  <s:select name="groupid" list="fromlist" listKey="groupid" listValue="groupname" headerKey="-1" headerValue="全部"/>
				 %>
				
							
			<s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" label="省律协" headerKey="-1" headerValue="请选择" onchange="getCities(this.value)"/>
          
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" label="市律协" headerKey="0" headerValue="请选择"/>
            
						 	
                            类型:
                            	<s:select name="lessontype" list="@com.changpeng.lessons.util.CommonDatas@LessonType" headerKey="0" headerValue="全部"/>
						
						 		<s:hidden name="lessonstyle"/>
						 
                            名称:<s:textfield name="title" size="12"/>
                            主讲人:<s:textfield name="teachers" size="8"/>
                    课程年份:<s:select name="nianshenyear" list="jifentime.years"  headerKey="0" headerValue="全部"  onchange="document.form1.submit()"/>
			  <s:if test="nianshenyear!=0">
			 	（从【${jifentime.startstr}】到【${jifentime.endstr}】）
			 	</s:if>
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
							<TD height="23" align="center" width="30%" background="../imagesa/top-bg1.gif">
								课程名称
							</TD>
						
							<TD align="center" width="15%" background="../imagesa/top-bg1.gif">
								主讲人
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								学分
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
				
       
							<TD class="tab_content" align="left">&nbsp;
								<a href="lessonsView.pl?lessonid=${lessonid}">${title}</a>
							</TD>
						
							<TD class="tab_content" align="center">
					   	<div style="overflow:hidden;text-overflow:ellipsis;">${teachers}</div>
							</TD>
							<TD class="tab_content" align="center">${xuefen}</TD>
							
	                <TD class="tab_content" align="center">
	                	<s:if test="mygroup==groupid">
	                <a href="lessonsShared!input.pl?lessonid=${lessonid}">共享</a>
	                </s:if>
	                <s:else>&nbsp;
	                </s:else>
	                </TD>
							<TD class="tab_content" align="center">   <s:date name="lessondate" format="yyyy-MM-dd HH:mm"/></TD>
                     <TD class="tab_content" align="center">

                              <s:property value="@com.changpeng.system.util.CommonDatas@groups[groupid]"/>
                              
							</TD>
							<TD class="tab_content" align="center">
							<s:if test="mygroup==groupid">
                                    <a href="lessonsEdit!input.pl?onlyonline=1&lessonid=${lessonid}">修改</a>
								</s:if>
							<s:else>
									&nbsp;
							</s:else>
							</TD>
							<TD class="tab_content" align="center">
								<s:if test="mygroup==groupid">
									<a href="#" onClick="deleteid('${lessonid}')">【删除】</a>
								</s:if>
								<s:else>
                                  &nbsp;
								</s:else>
								
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
				
							
									
                                    &nbsp;<INPUT type="button" onClick="return getAddOnline()" value="新增在线课程"
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