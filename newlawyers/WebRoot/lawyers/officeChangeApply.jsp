<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>律师申请转所</title>
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

<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  

  if(vallll!=0){
     $.getJSON("../lawyersajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     

        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
}
</script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
				申请转所
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="officeChangeApply" method="post">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         </b></font>
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             请现在所在的事务所:
          </td>
          <td class="tab_content1">
<s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.provinceunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.directunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.theoffice]"/>
           </td>
        </tr>
            <tr>
          <td align="right" class="tab_content1"> 请选择新的事务所: </td>
             <td class="tab_content1">
             <s:hidden name="isedit"/>
            <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.provinceunion]"/>->
         <s:property value="@com.changpeng.common.CommonDatas@groups[lawyers.directunion]"/>
         <s:select name="changeApply.newcity" list="citylist" listKey="groupid" listValue="groupname" onchange="getOffices(this.value)"/>
         ->
         <s:select name="changeApply.newoffice" list="officelist" listKey="groupid" listValue="groupname" id="office"/>
              </td>
        </tr>
              
       <tr>
          <td align="right" class="tab_content1">申请理由: </td>
             <td class="tab_content1">
             <s:textarea name="changeApply.applyReason" cols="40" rows="5"/>
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

          </td>
        </tr>
      </table>
    </s:form>
    </td>
  </tr>
</table>
</body>
</html>