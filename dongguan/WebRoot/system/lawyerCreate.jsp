<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-管理员新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script>
	function checkLoginname(loginname){	
	$("checkloginname").innerHTML = "不为空且长度不超过15个字符 ";
	if((loginname == null) || (loginname.length == 0))
		return;
	var now=new Date().getTime();
	var url="../systemajax/checkLoginname.pl?now="+now+"&loginname="+loginname;
	//alert(url);
	var myAjax = new Ajax.Request(url,{method: 'get',  onComplete: showSubResponse});
}
function checkCardno(cardno){
 	if((cardno == null) || (cardno.length == 0)){
		$("cardnoname").innerHTML = "";
   		
		return; 
	}
	var now=new Date().getTime();
	var url="../systemajax/checkCardno.pl?now="+now+"&cardno="+cardno;
	//alert(url);
	var myAjax = new Ajax.Request(url,{method: 'get',  onComplete: showSubResponse2});
}
function showSubResponse2(originalReq){

   var res=eval('(' + originalReq.responseText + ')');
 //  alert(res.isrepeat);
   if(res.isrepeat == true){
   		$("cardnoname").innerHTML = "<font color='red'>对不起，您输入的律师培训卡号【"+res.cardno+"】已经被他人使用。</font>";
   		${"save"}.disabled="true";
   }else{
    	$("cardnoname").innerHTML = "";
		//${"save"}.disabled="false";
   }
}
function showSubResponse(originalReq){

   var res=eval('(' + originalReq.responseText + ')');
 //  alert(res.isrepeat);
   if(res.isrepeat == true){
   		$("checkloginname").innerHTML = "<font color='red'>对不起，您输入的律师执业证号【"+res.loginname+"】已经被他人使用。</font>";
   		${"save"}.disabled="true";
   }else{
		//${"save"}.disabled="false";
   }
}
function openGroupTree(){
	var popwin=window.open("sysGroupTree.pl","部门列表","toolbar=no,location=no,width=300,height=305,menubar=no,scrollbars=yes,resizable=no,status=no");
	popwin.moveTo((screen.width+200)/2,(screen.height-400)/2);
}
</script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
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
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="lawyerCreate" method="post" validate="true" enctype="multipart/form-data">
      <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         注意：登录名设置将设置为执业资格证号，登录密码为身份证号码
         </b></font>
         
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             所属事务所:
          </td>
          <td class="tab_content1">
          <s:hidden name="groupid"/>
          <s:textfield name="groupname" size="40" readonly="true"/>
		  <s:if test="isadminer">
		  	<a href="#" onclick="javascript:openGroupTree()"><span style="color:red;font-size:8pt">请选择</span></a>
		  </s:if>

           </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             律师姓名:
          </td>
          <td class="tab_content">
            <s:textfield name="sysUser.username" size="15" maxlength="15" cssClass="text1" required="true"/>
              <span class="hint">不为空且长度不超过7个汉字</span> </td>
        </tr>
        <tr>
            <td align="right" class="tab_content1"> 律师执业证号: </td>
          <td class="tab_content1">
            <s:textfield name="sysUser.lawerno" size="20" maxlength="15" cssClass="text1" onblur="checkLoginname(this.value)" required="true"/>
              <span class="hint" id="checkloginname">不为空且长度不超过15个字符</span> </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 身份证号: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.certno" size="20" maxlength="20" cssClass="text1" required="true"/>
            <span class="hint">不为空且长度不超过20个字符</span>
          </td>
        </tr>
		
		<tr>
            <td align="right" class="input-title"> 培训卡卡号: </td>
          <td class="input-content">
            <s:textfield name="sysUser.cardno" size="25" maxlength="25" cssClass="text1" onblur="checkCardno(this.value)"/>
             
               <span class="hint" id="cardnoname"></span>
            </td>
        </tr>
       <!--  <tr> 
            <td align="right" class="input-title">培训卡发卡日期: </td>
          <td class="input-content">
          <jscalendar:jscalendar name="sysUser.carddate" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr>
         -->
 <tr> 
            <td align="right" class="tab_content1">东莞执业日期: </td>
          <td class="tab_content1">
           
            <jscalendar:jscalendar name="sysUser.zhiyedate" format="%Y-%m-%d" showstime="false" cssClass="text1"/>
            </td>
        </tr>
		<tr>
            <td align="right" class="tab_content"> 手机号码: </td>
          <td class="tab_content">
            <s:textfield name="sysUser.mobile" size="13" maxlength="13" cssClass="text1"/>
            </td>
        </tr>
         <tr> 
            <td align="right" class="tab_content1">律师照片: </td>
          <td class="tab_content">
           <s:file name="upload" cssClass="text1"/><br/>
           <font color="#FF0000">
           请提供标准2寸身份证相片，大小控制在100K以内
           </font>
            </td>
        </tr>
		 <tr> 
            <td align="right" class="tab_content">性别: </td>
          <td class="tab_content">
           <s:select name="sysUser.gender" list="#{'G':'男','M':'女'}"/>
            </td>
        </tr>
       
        <tr> 
            <td align="right" class="tab_content1">备注信息: </td>
          <td class="tab_content1">
            <s:textarea name="sysUser.comments" rows="4" cols="50" cssClass="textarea1"/>
            </td>
        </tr>
		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 保 存 " cssClass="button" id="save"/>&nbsp;
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