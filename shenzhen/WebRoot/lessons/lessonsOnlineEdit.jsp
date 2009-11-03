<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<html>
	<head>
		<title>编辑在线课程</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="JavaScript"> 
var len=0;
var i=1;
function addFile(){
	len++;
	i++;
	if(len==5){
		alert("对不起，您最多能添加5个附件");
		return false;
	}
	 var obj = document.getElementById("more");
	 obj.insertAdjacentHTML("beforeBegin","<br><input type=\"file\" name=\"file\" size=\"60\"/>");
	
	if(i==2){
		obj.insertAdjacentHTML("afterEnd","<input type=\"button\" value=\"删  除\" onclick=\"delFile()\"/>");
	}	 
 }
 function delFile(){
	var obj=event.srcElement
	var div = document.getElementById('files');
	var files = document.getElementsByName('file');
	var brs = document.getElementsByTagName('br');
	//alert(brs.length);
	
	div.removeChild(files[files.length-1]);
	div.removeChild(brs[brs.length-1]);
	len--;
	i--;
	if(i==1)
		div.removeChild(obj);
		
 }
 
 
function deleteAttach(lessonid,filename){
	 var url="../lessonajax/attachDelete.pl";		
	 var pars = 'lessonid='+lessonid+'&filename='+filename;
     var myAjax = new Ajax.Request(url,{method: 'post', parameters:pars, onComplete: showResponse});	
     event.srcElement.parentNode.innerHTML="<font color='red'>已删除</font>";
}
function showResponse(originalRequest){
    var res=eval('(' + originalRequest.responseText + ')');
   	len=res.attachs;
   	if(len==0) len=1;
 	//alert("附件删除成功");   
 	//alert(len);
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
		<s:form action="lessonsOnlineEdit" method="post" name="form1" validate="true"  enctype="multipart/form-data">
		
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
								<div align="center"></div>
								<div align="center"></div>
								<div align="center"></div>
								<div align="center"></div>
							</td>
						</tr>
						<tr>
							<td width="15%" class="tab_content1" align="right">
									内容标题：
							</td>
							<td width="85%" colspan="2" class="tab_content1">
								<s:textfield name="lesson.title" size="50" />
							</td>
						</tr>
						<tr>
							<td class="tab_content" align="right">
									课程状态：
							</td>
							<td colspan="2" class="tab_content">
									<s:select name="lesson.lessonstate" list="#{0:'内容编辑',1:'培训课程',2:'往期课程',3:'课程结束'}"/>
							</td>
						</tr>
						<tr>
							<td class="tab_content1" align="right">
									培训师：
							</td>
							<td colspan="2" class="tab_content1">
								<s:textfield  name="lesson.teachers" size="20"/>
							</td>
						</tr>
						<tr>
							<td class="tab_content" align="right">
									课程类别：
							</td>
							<td colspan="2" class="tab_content">
									<s:select name="lesson.lessontype" list="#{0:'大型专题讲座',1:'学术研讨会',2:'实务培训学习',3:'其他'}"/>
							</td>
						</tr>


						<tr>
							<td class="tab_content1" align="right">
									学分：
							</td>
							<td colspan="2" class="tab_content1">
								<s:textfield  name="lesson.xuefen" size="5"/>
							</td>
						</tr>
					
                    	<tr>
							<td class="tab_content" align="right">
									授课时间：
							</td>
							<td colspan="2" class="tab_content">
								<jscalendar:jscalendar name="datestart" format="%Y-%m-%d"  showstime="false"/>
								<s:textfield name="hmstart" size="4"/> <span style="color:red">形如HH：mm的形式</span>
        					</td>
						</tr>

					<!--	<tr>
							<td class="tab_content1" align="right">
									结束时间：
							</td>
							<td colspan="2" class="tab_content1">
								<jscalendar:jscalendar name="dateend" format="%Y-%m-%d" showstime="false"/>
								<s:textfield name="hmend" size="4"/> <span style="color:red">形如HH：mm的形式</span>
							</td>
						</tr>
						<tr>
							<td class="tab_content" align="right">
									授课地点：
							</td>
							<td colspan="2" class="tab_content">
									<s:textfield  name="lesson.lessonaddress" size="40"/>
							</td>
						</tr>

						<tr>
							<td class="tab_content1" align="right">
									课程内容：
							</td>
							<td colspan="2" class="tab_content1">
								<FCK:editor id="lesson.lessoncontent" height="300" width="95%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
								 >
									${lesson.lessoncontent}
								</FCK:editor>
							</td>
						</tr>
						<tr>
							<td class="tab_content" align="right">
									考勤时长：
							</td>
							<td colspan="2" class="tab_content">
									<s:textfield  name="lesson.kaoqinshichang"  size="4"/><span style="color:red">单位为分钟</span>
							</td>
						</tr>

						<tr>
							<td class="tab_content1" align="right">
									是否提醒：
							</td>
							<td colspan="2" class="tab_content1">
								<s:checkbox name="lesson.isalert"/>
							</td>
						</tr>
						<tr>
							<td class="tab_content" align="right">
									限制人数：
							</td>
							<td colspan="2" class="tab_content">
									<s:textfield  name="lesson.maxpersons"  size="4"/>
							</td>
						</tr>

                    
                    -->
					<!--	<tr>
							<td class="tab_content1" align="right">
									未到扣分：
							</td>
							<td colspan="2" class="tab_content1">
								<s:textfield  name="lesson.notexistfen" size="4"/>
							</td>
						</tr>-->
						<tr>
							<td class="tab_content" align="right">
									在线文件：
							</td>
							<td colspan="2" class="tab_content">
									<s:textfield  name="lesson.onlinefile" size="60"/>
							</td>
						</tr>

						<tr>
							<td class="tab_content1" align="right">
									分数折扣：
							</td>
							<td colspan="2" class="tab_content1">
								<s:textfield  name="lesson.fenshuoff" size="4"/>%
							</td>
						</tr>
					<!--	<tr>
							<td class="tab_content" align="right">
									是否必修：
							</td>
							<td colspan="2" class="tab_content">
						
									<s:checkbox name="lesson.isbixiu" />
							</td>
						</tr>-->
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