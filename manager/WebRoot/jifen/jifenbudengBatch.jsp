<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>${sysName }-积分批量补登</title>
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

</script>
</head>
<body leftmargin="0" marginwidth="0" marginheight="0" topmargin="0">
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="23" background="../imagesa/top-bg3.gif"
					class="baseFontBold">
					<img src="../imagesa/b_02.gif" width="4" height="7">
					批量补登积分
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="jifenbudengBatch" method="post" validate="true" enctype="multipart/form-data">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		
		 <tr>
          <td align="right" width="20%" class="tab_content1">
            对应市(省直属)律协选择：
          </td>
          <td class="tab_content1">
        <s:if test="datavisible.provinceview">
          <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" label="省级律协" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
        </s:if>
        <s:else>
              <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.provinceid]"/>
              <s:hidden name="datavisible.provinceid"/>
        </s:else>
              <!-- 具体到某个律协就行了 -->
        <s:if test="datavisible.cityview">
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname"/>
        </s:if>
        <s:else>
            <s:hidden name="datavisible.cityid"/>
            <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
        </s:else>
           </td>
        </tr>
        <tr>
          <td align="right" class="tab_content">
             上传文件:
          </td>
          <td class="tab_content">
           <s:file name="upload" size="35" cssClass="text1"/>
          </td>
        </tr>
		  <tr>
      <td align="right" class="tab_content">
     &nbsp;
          </td>
          <td class="tab_content">
           <a href="../template/jifenbudeng.xls">导入模板下载</a> （注：在批量新增时请先下载"导入模板"并制作表格）
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