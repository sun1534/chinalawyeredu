<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${sysName }-事务所批量新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
function deletephoto(groupid){
if(confirm("您确实要删除这个LOGO吗?")){
var url="../systemajax/photoDelete.pl";
  $.getJSON(url, { "groupid":groupid,"now":new Date().getTime()}, function(json){
     if(json.success == "true"){
   		$("#imgdiv").empty();
      }else{
	   alert("照片删除失败");
      }
   });
}
else{
return;
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
				批量新增律师事务所信息
				</td>
			</tr>
		</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>		
    <td>
    <s:form name="form1" action="theOfficeCreateBatch" method="post" enctype="multipart/form-data">
     <table width="100%" border="0" cellpadding="0" cellspacing="1"	bgcolor="#EDEDED">
		<tr>
          <td colspan="2">
          	&nbsp;         
          </td>
        </tr>
     
      <tr>
          <td align="right" width="20%" class="tab_content1">
             所属律协:
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
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" />
            </s:if>
           <s:else>
             <s:property value="@com.changpeng.system.util.CommonDatas@groups[datavisible.cityid]"/>
             
              <s:hidden name="datavisible.cityid"/>
            </s:else>
            
          </td>
        </tr>
       
        <tr>
          <td align="right" class="tab_content">
       上传文件：
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
           <a href="../template/offices.xls">导入模板下载</a> （注：在批量新增时请先下载"导入模板"并制作表格）
          </td>
     
     </tr>
     
      
        <tr>
          <td height="5" colspan="2">
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
          	<input type="submit" value=" 保 存 " class="button">&nbsp;
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