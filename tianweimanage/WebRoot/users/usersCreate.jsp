<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/locationList.action", {"optype":1, "locationid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.childrens)  
     {     
        var _o=new Option(json.childrens[k.toString()],k);
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
     $.getJSON("../systemajax/locationList.action", { "optype":0,"locationid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.childrens)  
     {     
        var _o=new Option(json.childrens[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}
function checkLoginname(loginname){	
	$("#checkloginname").html("不为空且长度不超过15个字符");
	if((loginname == null) || (loginname.length == 0))
		return;
	var now=new Date().getTime();
	var _url="../usersajax/checkLoginname.action?now="+now+"&loginname="+loginname;
	$.ajax({
          type:"post",
          url:_url,
          async:true,
          success:function(xml){
		  var res=eval('(' + xml + ')');
   if(res.isrepeat == true){
   		$("#checkloginname").html("<font color='red'>对不起，您输入的帐号【"+res.loginname+"】已经被他人使用</font>");
   		$("#save").attr("disabled", true);
   }else{
		$("#save").attr("disabled", false);
   }}
		  });
}
</script>

<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;新增用户</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="85%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
               <s:form name="form1" action="usersCreate" validate="true" method="post">
               <s:hidden name="user.userRole"/>
        			 	<TR>
					  <TD width="15%" class="listheadline">名称:</TD>
					  <TD width="35%" class="listline">
					  <s:textfield size="25" name="user.userName"/></TD>
					</TR>
                 
	 			 	<TR>
						<TD class="listheadline">登录名:</TD>
						<TD class="listline">
                   <s:textfield name="user.loginName" size="12" onblur="checkLoginname(this.value)" />
                      <span id="checkloginname">不为空且长度不超过15个字符</span>
                     
                        </TD>
					</TR>
                   
                    <TR>
                      <TD class="listheadline">密码:</TD>
                      <TD  class="listline"><s:textfield  name="user.pwd" size="20"/></TD>
                    </TR>
                      <TR>
                      <TD  class="listheadline">所属地区:</TD>
                      <TD  class="listline">
                      <s:select onchange="getCities(this.value)" name="user.provinceId" id="province" list="@com.sxit.common.util.LocationTree@ProvinceList" listKey="id" listValue="areaName" headerKey="0" headerValue="请选择"/>省(市)
                       <s:select onchange="getOffices(this.value)" name="user.cityId" id="city" list="#{'0':'请选择'}"/>市(区)
                      <s:select name="user.districtId" id="office" list="#{'0':'请选择'}"/>市(县)
                      
                      </TD>
                    </TR>
                              <TR>
                      <TD class="listheadline">联系手机:</TD>
                      <TD  class="listline">
                      <s:textfield  name="user.mobile" size="20"/></TD>
                    </TR>
                    <s:if test="user.userRole==1">
                      <TR>
                      <TD class="listheadline">身份证号码:</TD>
                      <TD  class="listline">
                      <s:textfield  name="user.cardno" size="25"/></TD>
                    </TR>
                    </s:if>
                    <s:if test="user.userRole==2">
                      <TR>
                      <TD  class="listheadline">工商编号:</TD>
                      <TD  class="listline">
                      <s:textfield  name="user.entno"  size="25"/></TD>
                    </TR>
                    </s:if>
                    
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
                <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
