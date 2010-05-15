<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<html>
<head>
<title>${sysName }-补登积分申请处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<jscalendar:head/>
<script language="javascript">


    
</script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
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
<body>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="23" background="../imagesa/top-bg3.gif" class="font">
    <img src="../imagesa/b_02.gif" width="4" height="7"> 当前位置：积分补登 &gt; 补登积分申请处理 </td>
  </tr>
</table>
<s:form action="jifenbudengApplyHandle" method="post" name="form1">
<table width="99%" height="316" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5" background="../imagesa/top-bg2.gif" class="baseFontBold"><div align="left">　</div>
        </td>
        </tr>
      <tr>
        <td width="37%" class="tab_content" align="right">内容标题：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.title}</td>
        </tr>
   <tr>
        <td width="37%" class="tab_content" align="right">计分日期：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.budengdate}
         	
        </td>
        </tr>
          <tr>
        <td width="37%" class="tab_content" align="right">是否计为现场培训：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.islocalstr}
        </td>
        </tr>
         <tr>
        <td width="37%" class="tab_content" align="right">补登积分年度：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.theyear}
        </td>
        </tr>
                    <tr>
        <td width="37%" class="tab_content" align="right">学分：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.xuefen}	</td>
        </tr>
      <tr>
      <tr>
        <td width="37%" class="tab_content" align="right">律师执业证号：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.lawyerno}</td>
        </tr>
      <tr>
        <td width="37%" class="tab_content" align="right">律师姓名：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">${ budengApply.lawyername}</td>
        </tr>
      <tr>
       	 <tr>
        <td width="37%" class="tab_content" align="right">律师所在事务所：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[budengApply.provinceid]"/>->
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[budengApply.cityid]"/>->
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[budengApply.officeid]"/>
        </td>
        </tr>
            <tr>
          <td align="right" class="tab_content">处理结果: </td>
             <td class="tab_content1">
            <s:radio name="budengApply.status" list="#{'1':'通过','2':'不通过'}"></s:radio>
              </td>
        </tr>
              <tr>
          <td align="right" class="tab_content">处理结果说明: </td>
             <td class="tab_content1">
             <s:textarea name="budengApply.confirmcontent" cols="50" rows="5"/>
             &nbsp;&nbsp;&nbsp;<font color='red'>如果申请不通过,请在此说明理由</font>
              </td>
        </tr>
      <tr>
        <td class="tab_content1"></td>
        <td colspan="2" class="tab_content1">　
         
          <input type="submit" name="Submit" value=" 保存 "/>
          　 
          <input type="reset" name="Submit2" value=" 重置 "/>
          　
          <input type="button" name="Submit3" value=" 返回 " onClick="javascript:history.go(-1)">
          </td>
        </tr>
       
    </table>
    </s:form>
    </td>
  </tr>

</table>
</body>
</html>