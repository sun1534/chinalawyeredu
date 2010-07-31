<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<html>
<head>
<title>授课律师新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/common.js"></script>
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
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}
function checkLoginname(loginname){	

	if((loginname == null) || (loginname.length == 0)){
	    $("#checkloginname").html("不为空且长度不超过15个字符");
		return;
	}
	var now=new Date().getTime();
	var url="../systemajax/checkLoginname.pl";
   $.getJSON(url, { "loginname": loginname,"now":now}, function(json){

     if(json.isrepeat == true){
   		$("#checkloginname").html("<font color='red'>对不起，您输入的帐号【"+json.loginname+"】已经被他人使用，请选择其他名字后再试。</font>");
   		$("#save").attr("disabled",true);
   }else{
	    $("#save").attr("disabled",false);
   }
});
}



</script>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>

</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
				<s:if test="isnew">
				新增授课律师
				</s:if>
				<s:else>
				授课律师信息修改
				</s:else>
				</td>
				
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>
    <td>
    <s:form name="form1" action="teacherCreateEdit" method="post" validate="true">
         <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;   
          <s:actionmessage/>
          <s:actionerror/>
          <s:fielderror/>     
          <s:hidden name="isnew"/>
          <s:hidden name="teacher.userid"/>
           <s:if test="!isnew">
            <s:hidden name="teacher.loginname"/>
             <s:hidden name="teacher.password"/>
             <s:hidden name="teacher.lessoncount"/>
            </s:if>
         </td>
        </tr>
		
        <tr>
          <td align="right" class="tab_content">
             老师姓名:
          </td>
          <td class="tab_content">
            <s:textfield name="teacher.username" size="15" maxlength="25" cssClass="text1" required="true"/>
              <span class="hint">不为空且长度不超过7个汉字</span> </td>
        </tr>
        
        <tr>
							<td class="tab_content" align="right">
									老师类型：
							</td>
							<td colspan="2" class="tab_content">
									<s:select name="teacher.teacherType" list="@com.changpeng.lessons.util.CommonDatas@TeacherType"/>
							</td>
						</tr>
        <s:if test="isnew">
        <tr>
            <td align="right" class="tab_content1"> 登录名称: </td>
          <td class="tab_content1">
            <s:textfield name="teacher.loginname" size="15" maxlength="25" cssClass="text1" onblur="checkLoginname(this.value)" required="true"/>
              <span class="hint" id="checkloginname">不为空且长度不超过15个字符</span> 
              </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 登录密码: </td>
          <td class="tab_content">
            <s:password name="teacher.password" size="20" maxlength="25" cssClass="text1" required="true"/>
            <span class="hint">不为空且长度不超过20个字符</span>
          </td>
        </tr>
		<tr>
            <td align="right" class="tab_content1"> 重复输入登录密码: </td>
          <td class="tab_content1">
            <s:password name="passagain" size="20" maxlength="25" cssClass="text1"/>
            
          </td>
        </tr>
        		</s:if>
		<tr>
            <td align="right" class="tab_content"> 电话号码: </td>
          <td class="tab_content">
            <s:textfield name="teacher.officephone" size="25" maxlength="25" cssClass="text1"/>
           
            </td>
        </tr>
		<tr>
            <td align="right" class="tab_content1"> 手机号码: </td>
          <td class="tab_content1">
            <s:textfield name="teacher.mobile" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
		  <tr> 
            <td align="right" class="tab_content">EMAIL: </td>
          <td class="tab_content">
            <s:textfield name="teacher.email" size="40" maxlength="40" cssClass="text1"/>
            </td>
        </tr>
          <tr> 
            <td align="right" class="tab_content">个人主页地址: </td>
          <td class="tab_content">
            <s:textfield name="teacher.website" size="40" maxlength="40" cssClass="text1"/>
            </td>
        </tr>
<tr> 
            <td align="right" class="tab_content1">性别: </td>
          <td class="tab_content1">
           <s:select name="teacher.gender" list="#{'1':'男','2':'女'}"/>
            </td>
        </tr>
       
        <tr> 
            <td align="right" class="tab_content">生日: </td>
          <td class="tab_content">
           
            <jscalendar:jscalendar name="teacher.birthday" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr> 
        
         <tr>
        <td width="15%" class="tab_content" align="right">老师介绍：
        </td>
        <td width="85%" colspan="2" class="tab_content1" align="left">
        
         		           <FCK:editor id="teacher.comments" height="300" width="95%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
							>
									${teacher.comments}
						</FCK:editor>
        	</td>
        </tr>
        
        <tr> 
            <td align="right" class="tab_content1">备注信息: </td>
          <td class="tab_content1">
            <s:textarea name="teacher.remarks" rows="3" cols="55" cssClass="textarea1"/>
            </td>
        </tr>
		
        <tr>
          <td height="2" colspan="2">&nbsp;
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 保 存 " cssClass="button" id="save" />&nbsp;
           	&nbsp;
          	<input type="button" value=" 返 回 " onClick="javascript:history.back(-1)" class="button">
          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>