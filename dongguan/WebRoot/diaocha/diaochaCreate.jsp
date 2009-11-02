<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
	<head>
		<title>新建调查</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script>
	function showtype(val){
		var t=event.srcElement;
		var tr = t.parentNode.parentNode;
		var table = t.parentNode.parentNode.parentNode.parentNode;
		for (i=0; i < table.rows.length; i++) {
            if(table.rows(i)==tr) {
                //alert(i);
                break;
            }
        }
		if(val==true){
			var newtr=table.insertRow(i+1);
			var newtd1=newtr.insertCell();
		    newtd1.width="15%";
		    //newtd1.class="tab_content";
			//newtd1.background-color="#F2F8FF";
			newtd1.height="30px";
		    newtd1.align="right";
			newtd1.innerText="类别名称:";
			var newtd2=newtr.insertCell();
			newtd2.colspan="2";
			//newtd2.class="tab_content";
			//newtd2.background-color="#F2F8FF";
			newtd2.height="30px";
			newtd2.innerHTML="<input type=\"text\" name=\"typename\" size=\"60\" maxlength=\"200\">&nbsp;<input type=\"button\" value=\"添加\" onclick=\"addtype()\">";
		}else{
			for(j=table.rows.length-2;j > i; j--){
				table.deleteRow(j);
			}
		}
	}
	function addtype(){
		var t=event.srcElement;
		var tr = t.parentNode.parentNode;
		var table = t.parentNode.parentNode.parentNode.parentNode;
		for (i=0; i < table.rows.length; i++) {
            if(table.rows(i)==tr) {
                //alert(i);
                break;
            }
        }
        var newtr=table.insertRow(i+1);
			var newtd1=newtr.insertCell();
		    newtd1.width="15%";
		    //newtd1.class="tab_content";
		   // newtd1.background-color="#F2F8FF";
			newtd1.height="30px";
		    newtd1.align="right";
			newtd1.innerHTML="&nbsp;";
			//newtd1.style="font-size: 9pt;color: #666666;background-color: #F2F8FF;background-repeat: no-repeat; background-position: right bottom;height: 30px;";
			var newtd2=newtr.insertCell();
			newtd2.colspan="2";
			//newtd2.class="tab_content";
			//newtd2.background-color="#F2F8FF";
			newtd2.height="30px";
			newtd2.innerHTML="<input type=\"text\" name=\"typename\" size=\"60\" maxlength=\"200\">&nbsp;<input type=\"button\" value=\"添加\" onclick=\"addtype()\">&nbsp;<input type=\"button\" value=\"删除\" onclick=\"deltype()\">";
	}
	function deltype(){
		var t=event.srcElement;
		var tr = t.parentNode.parentNode;
		var table = t.parentNode.parentNode.parentNode.parentNode;
		for (i=0; i < table.rows.length; i++) {
            if(table.rows(i)==tr) {
                //alert(i);
                break;
            }
        }
        table.deleteRow(i);
	}
</script>
<style type="text/css">
TD{
    font-size: 9pt;
	color: #666666;
	background-color: #F2F8FF;
    background-repeat: no-repeat;
    background-position: right bottom;
	height: 30px;
}
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
		<s:form action="diaochaCreate" method="post" name="form1" validate="true">
		
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1">
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

							</td>
						</tr>
						<tr>
							<td width="15%" class="tab_content1" align="right">
								   调查标题：
							</td>
							<td width="85%" colspan="2" class="tab_content1">
								<s:textfield name="diaocha.title" size="80" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="tab_content1" align="right">
								   调查说明：
							</td>
							<td width="85%" colspan="2" class="tab_content1">
								<FCK:editor id="diaocha.shuoming" height="300" width="95%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
								 >
								</FCK:editor>
							</td>
						</tr>
						<tr>
							<td class="tab_content" align="right">
								状态：
							</td>
							<td colspan="2" class="tab_content">
								<s:select name="diaocha.state" list="#{1:'草稿'}"/>
        					</td>
						</tr>
						<tr>
							<td class="tab_content1" align="right">
								是否添加分类：
							</td>
							<td colspan="2" class="tab_content1">
								<input type="checkbox" onclick="showtype(this.checked)"/>
        					</td>
						</tr>
						
						<tr>
							<td class="tab_content">&nbsp;
							</td>
							<td colspan="2" class="tab_content">
					
									<input type="submit" class="button" value="确认">
									&nbsp;&nbsp;
									<input type="reset" class="button" value="重置">
									&nbsp;&nbsp;
									<input type="button" class="button" value="返回" onClick="history.go(-1)">
							</td>
						</tr>
					</table>
			</s:form>
	</body>
</html>