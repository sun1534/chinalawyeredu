﻿<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title><%=com.changpeng.common.Constants.SYS_NAME%>-律师换所</title>
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
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
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
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="lawyersChangeOffice" method="post" validate="true" enctype="multipart/form-data">
      <table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
     &nbsp;
         </b></font>
         
         </td>
        </tr>
           <tr>
          <td align="right" class="tab_content">
             律师姓名:
          </td>
          <td class="tab_content">
           ${lawyers.lawyername }
           <s:hidden name="lawyers.lawyername"/>
            
        </tr>
		 <tr>
          <td align="right" width="20%" class="tab_content1">
             原所在事务所:
          </td>
          <td class="tab_content1">
          <s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyers.provinceunion]"/>-<s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyers.directunion]"/>-<s:property value="@com.changpeng.system.util.CommonDatas@groups[lawyers.theoffice]"/>
          
            
           </td>
        </tr>
      <tr>
          <td align="right" width="20%" class="tab_content1">
             新转事务所:
          </td>
          <td class="tab_content1">
          
                <s:if test="datavisible.provinceview">
             <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             </s:if>
            <s:else>
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>-
             
              <s:hidden name="datavisible.provinceid"/>
            </s:else>
                  <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getOffices(this.value)"/>
            </s:if>
           <s:else>
             <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>-
             
              <s:hidden name="datavisible.cityid"/>
            </s:else>
            ${ datavisible.officeid}
                 <s:if test="datavisible.officeview">
             <s:select name="datavisible.officeid" id="office" list="datavisible.officelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
 </s:if>
            <s:else>
              <s:hidden name="datavisible.officeid"/>
                <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.officeid]"/>
              
            </s:else>
           </td>
        </tr>
    
		
        <tr>
          <td height="2" colspan="2">
          </td>
        </tr>

        <tr>
          <td colspan="2" align="center">
          	<s:submit value=" 确认转所 " cssClass="button" id="save"/>&nbsp;
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