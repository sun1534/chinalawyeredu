<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>律师申请转所处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
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
function getOffices(vallll){
  $("#office")[0].length=0;
  var _o=new Option('请选择',0);
  $("#office")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../systemajax/getSubGroup.pl", { "parentid": vallll,"time":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#office")[0].options.add(_o);  
     }
}); 
  }
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
				${lawyers.lawyername }律师的转所申请处理
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="officeChangeHandle" method="post">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         </b></font>
         </td>
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             ${lawyers.lawyername }现在所在事务所:
          </td>
          <td class="tab_content1">
      <s:property value="@com.changpeng.system.util.CommonDatas@groups[changeApply.oldprovince]"/>->
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[changeApply.oldcity]"/>->
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[changeApply.oldoffice]"/>
           </td>
        </tr>
            <tr>
          <td align="right" class="tab_content1"> 申请转所的事务所: </td>
             <td class="tab_content1">
             <s:hidden name="lawyerid"/>
      <s:property value="@com.changpeng.system.util.CommonDatas@groups[changeApply.newprovince]"/>->
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[changeApply.newcity]"/>->
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[changeApply.newoffice]"/>
       
              </td>
        </tr>
                   <tr>
          <td align="right" class="tab_content1">申请转所理由: </td>
             <td class="tab_content1">
           ${changeApply.applyReason }
              </td>
        </tr>
           <tr>
          <td align="right" class="tab_content1">处理结果: </td>
             <td class="tab_content1">
            <s:radio name="changeApply.status" list="#{'1':'通过','2':'不通过'}"></s:radio>
              </td>
        </tr>
              <tr>
          <td align="right" class="tab_content1">处理备注: </td>
             <td class="tab_content1">
             <s:textarea name="changeApply.confirmContent" cols="50" rows="5"/>
             	&nbsp;&nbsp;&nbsp;<font color='red'>如果审批不通过,请在此说明理由</font>
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