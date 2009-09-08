<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<html>
	<head>
		<title>新建课程</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<jscalendar:head/>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
</script>
<script language="JavaScript"> 
var len=1;
function addFile(){
	 
	 if(len==5){
		 alert("对不起，您最多能添加5个附件");
		 return false;
	 } 
	  len++;
	 var obj = document.getElementById("more");
	 obj.insertAdjacentHTML("beforeBegin","<br><input type=\"file\" name=\"file\" size=\"50\"/>");
	 if(len==2){
	  	obj.insertAdjacentHTML("afterEnd","&nbsp;<input type=\"button\" value=\"删  除\" onclick=\"delFile()\"/>");
	 }	 
 }
 function delFile(){
	var obj=  event.srcElement||event.target;
	var div = document.getElementById('upfiles');
	var files = document.getElementsByName('file');
	var brs = document.getElementsByTagName('br');
	//alert(brs.length);
	
	div.removeChild(files[files.length-1]);
	div.removeChild(brs[brs.length-1]);
	len--;
	if(len==1)
		div.removeChild(obj);
		
 }
   function shareit(){ 
    var _select=document.getElementById("lessonsCreate_sharedgroupids");
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
		<s:form action="lessonsCreate" method="post" name="form1" validate="true" enctype="multipart/form-data">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#C2D6F0">
			
			<tr>
				<td valign="top" bgcolor="#FFFFFF">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
							<td height="24" colspan="5" background="../imagesa/top-bg2.gif"
								class="baseFontBold">
							<font color=red>
			<s:fielderror/> 	<s:hidden name="onlyonline"/>
			</font>
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
									 内容标题：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textfield name="lesson.title" size="50" />
                               <font color="red">*不能为空</font>
							</td>
						</tr>
				
						     <s:if test="shouldview">
						<tr>
							<td class="tab_content" align="right">
									课程来源：
							</td>
							<td colspan="2" class="tab_content">
                       
						  <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
            		
					
							</td>
						</tr>
							</s:if>
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
                                 <font color="red">*不能为空</font>
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
                                 <font color="red">*不能为空且必须为数字</font>
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
								<FCK:editor id="lesson.lessoncontent" height="300" width="99%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
									>
								</FCK:editor>
							</td>
						</tr>
				
						<tr>
							<td class="tab_content" align="right">
									在线文件：
                                 
							</td>
							<td colspan="2" class="tab_content">
									<s:textfield id="fileid" name="lesson.onlinefile" size="60"/><font color=red">(*不能为空)</font>
							</td>
						</tr>
                       
                 
						
					<tr>
							<td class="tab_content1" align="right">
									分数折扣：
							</td>
							<td colspan="2" class="tab_content1">
								<s:textfield  name="lesson.fenshuoff" size="4"/>%
                                 <font color="red">*</font>
							</td>
						</tr>
					
                          <tr id="sharedto" >
							<td class="tab_content" align="right">
									共享给：
							</td>
							<td colspan="2" class="tab_content">
                           <s:select name="sharedgroupids" list="shouldsharedgroupids" listKey="groupid" listValue="groupname" cssStyle="width:150px;height:100px" multiple="true" headerKey="all" headerValue="共享给全部" onchange="shareit()"/><span>(按住Ctrl键选择多个)</span>
                         
							</td>
						</tr>
							<tr>
							<td class="tab_content" align="right">
									附件：
							</td>
							<td colspan="2" class="tab_content" id="upfiles">
								<s:file name="file" size="50"/>&nbsp;<input id="more" type="button" value="更多..." onClick="addFile()">                      
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