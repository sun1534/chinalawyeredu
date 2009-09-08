<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
	<head>
		<title>新建课件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script>
	function isupload(val){
		if(val==true){
			document.getElementById("upload").style.display="";
			document.getElementById("edit").style.display="none";
		}else{
			document.getElementById("upload").style.display="none";
			document.getElementById("edit").style.display="";
		}
	}
	function alertError(field, error){
  		alert(error);
  		field.focus();
	}
	function isFloat(val){ 
	  var parten = /^\d+(\.\d+)?$/; 
	  if(parten.exec(val)){ 
	    return true;
	  }else{ 
	    return false;
	  } 
	}
    function validateForm_coursewareCreate() {
        form = document.getElementById("coursewareCreate");
		var errors = false;
        if (form.elements['ware.warename']) {
            field = form.elements['ware.warename'];
            var error = "请输入课件名称!";
            if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
                alertError(field, error);
            errors = true;
            return !errors;
            }
        }
        // field name: ware.warename
        // validator name: stringlength
        if (form.elements['ware.warename']) {
            field = form.elements['ware.warename'];
            var error = " 课件名称长度必须小于50个汉字或100个字母!";
            if (field.value != null) {
                var value = field.value;
                    //trim field value
                    while (value.substring(0,1) == ' ')
                        value = value.substring(1, value.length);
                    while (value.substring(value.length-1, value.length) == ' ')
                        value = value.substring(0, value.length-1);
                if ((-1 > -1 && value.length < -1) ||
                    (100 > -1 && value.length > 100)) {
                    alertError(field, error);
		            errors = true;
		            return !errors;
                }
            } 
        }
        // field name: ware.xuefen
        // validator name: double
        if (form.elements['ware.xuefen']) {
            field = form.elements['ware.xuefen'];
            var error = "学分必须为数字!";
            if (!isFloat(field.value)) {
	             alertError(field, error);
			     errors = true;
			     return !errors;
            }
        }
        // field name: ware.waretime
        // validator name: double
         if (form.elements['ware.waretime']) {
            field = form.elements['ware.waretime'];
            var error = "时间必须为数字!";
            if (!isFloat(field.value)) {
	             alertError(field, error);
			     errors = true;
			     return !errors;
            }
        }
       
       if (form.elements['ware.isupload']) {
            field = form.elements['ware.isupload'];
            
            if(field.checked){
            	var upload = form.elements['uploadfile'];
            	filename=upload.value.toLowerCase();
            
            	index=filename.lastIndexOf(".");
            	endfix="";
            	if(index>0)
					endfix=filename.substring(index+1);
				var error = "上传文件必须为html.htm或shtml型网页文件!";	
				
				if(endfix!="html"&&endfix!="htm"&&endfix!="shtml"){
					alertError(field, error);
				    errors = true;
				    return !errors;
				}
            }/**else{
            	var content = form.elements['warecontent'];
            	var val=content.value;
	            var error = "请输入课件内容!";
	            if (val == null || (val == "" || val.replace(/^\s+|\s+$/g,"").length == 0)) {
	                alert(error);
		            errors = true;
		            return !errors;
	            }
            }*/
        }
       return !errors;
      // return false;
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
		<s:form action="coursewareCreate" method="post" name="form1" onsubmit="return validateForm_coursewareCreate();" enctype="multipart/form-data">		
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
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   课件名称：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textfield name="ware.warename" size="40" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   课件类别：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:select name="ware.coursetype.typeid" list="typelist" listValue="typename" listKey="typeid" value="typeid"/>
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   学分：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textfield name="ware.xuefen" size="5" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   时间：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:textfield name="ware.waretime" size="5" /><span style="color:red">*单位为分钟</span>
							</td>
						</tr>
						<tr>
							<td width="20%" class="tab_content1" align="right">
								   是否上传：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:checkbox name="ware.isupload" onclick="isupload(this.checked)"/>
							</td>
						</tr>
						<tr id="upload" style="display:none">
							<td width="20%" class="tab_content1" align="right">
								   请选择文件：
							</td>
							<td width="80%" colspan="2" class="tab_content1">
								<s:file name="uploadfile" size="60"/>
									<br/><font color=red>注意:上传的课件必须是.html,.shtml以及.htm格式的网页文件。且网页大小不能超过2M。</font>
							</td>
						</tr>
						<tr id="edit">
							<td width="20%" class="tab_content1" align="right">
									编辑课件：
							</td>
							<td width="80%" class="tab_content1">
								<FCK:editor id="warecontent" height="400" width="99%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
									>
							</FCK:editor>
							</td>
						</tr>
						
						
						<tr>
							<td class="tab_content">&nbsp;
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