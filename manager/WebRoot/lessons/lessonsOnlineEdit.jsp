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
 function shareit(){ 
    var _select=document.getElementById("lessonsOnlineEdit_sharedlist");
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
					${navigator}
				</td>
			</tr>
		</table>
		<s:form action="lessonsEdit" method="post" name="form1" validate="true"  enctype="multipart/form-data">
		
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0">
			<s:fielderror/>
				<s:hidden name="onlyonline"/>
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
									名家讲坛：
							</td>
							<td colspan="2" class="tab_content">
						<s:select name="lesson.teachertype" list="@com.changpeng.lessons.util.CommonDatas@TeacherType"/>
					   <font color="red">*请选择</font>
							</td>
						</tr>
						<tr>
							<td class="tab_content1" align="right">
									主讲人：
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
								
											<s:select name="lesson.lessontype" list="@com.changpeng.lessons.util.CommonDatas@LessonType"/>
                                     <font color="red">*请选择</font>
							</td>
						</tr>

						<tr>
							<td class="tab_content1" align="right">
									课程类别描述：
							</td>
							<td colspan="2" class="tab_content1">
								<s:textfield  name="lesson.lessontypedesc" size="50"/>
                                 <font color="red">*不能为空</font>
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
								<jscalendar:jscalendar name="datestart" format="%Y-%m-%d" showstime="false"/>
								<s:textfield name="hmstart" size="4"/> <span style="color:red">*不能为空.形如HH：mm的形式</span>
        					</td>
						</tr>
						<tr>
							<td class="tab_content1" align="right">
									课程内容介绍：
							</td>
							<td colspan="2" class="tab_content1">
								<FCK:editor id="lesson.lessoncontent" height="400" width="99%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
									>			${lesson.lessoncontent}
								</FCK:editor>
							</td>
						</tr>
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
									文件类型：
							</td>
							<td colspan="2" class="tab_content1">
							 <s:radio name="lesson.onlineType" list="#{'1':'音频文件','0':'视频文件'}"/>
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
				
						<tr>
							<td class="tab_content">
							</td>
							<td colspan="2" class="tab_content">
					<s:hidden name="pageNo"/>
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