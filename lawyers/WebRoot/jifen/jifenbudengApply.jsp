<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<html>
<head>
<title>律师申请补登积分</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jscalendar:head/>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
-->
</style>
<script language="javascript"> 

function islocalclick(obj){
  if(obj.checked){
    $("#locallisttr").show();
    $("#titleidtr").hide();
       $("#budengdateid").attr("readonly",true);
       $("#applytitleid").attr("readonly",true);
       $("#xuefenid").attr("readonly",true);
  }
  else{
      $("#locallisttr").hide();
      $("#titleidtr").show();
       $("#budengdateid").attr("readonly",false);
       $("#applytitleid").attr("readonly",false);
       $("#xuefenid").attr("readonly",false);
  }
}
function selectlocal(lessonid){
  if(lessonid==0){
    alert("请选择您需要补登的对应现场课程");
  }else{
     if(lessonid!=0){
       $.getJSON("../lessonajax/getLessonsById.pl", { "lessonid": lessonid,"now":new Date().getTime()}, function(json){
          var exist=json.exist;
        //  alert(exist+",,,"+json.lessons);
          if(exist==1){
             $("#budengdateid").attr("value",json.lessons.lessondatestr);
              $("#applytitleid").attr("value",json.lessons.title);
               $("#xuefenid").attr("value",json.lessons.xuefen);
          }
       }); 
    }
  
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
				申请补登积分
				</td>
			</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0"
			align="center" class="border-table">
	<tr>			
    <td>
    <s:form name="form1" action="jifenbudengApply" method="post">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
		<tr>
          <td colspan="2">&nbsp;
          	 	<font color="#FF0000"><b>
         </b></font>
         </td>
        </tr>
		<tr>
        <td width="37%" class="tab_content" align="right">是否计为现场培训：</td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <s:checkbox name="budengApply.islocal" onclick="islocalclick(this)"/>
       	&nbsp;&nbsp;&nbsp;<font color='red'>如果计为现场培训,则补登的积分,在积分统计处,培训方式显示为"现场培训"</font>
        </td>
        </tr>
        <tr id="locallisttr" style="display:none">
        <td width="37%" class="tab_content" align="right">选择现场课程：</td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
          <s:select name="budengApply.lessonid" list="locallessonlist" listKey="lessonid" listValue="title" headerKey="0" headerValue="请选择" onchange="selectlocal(this.value)"/>
        </td>
        </tr>
		<tr id="titleidtr">
        <td width="37%" class="tab_content" align="right">内容标题：</td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <s:textfield name="budengApply.title" size="40" id="applytitleid"/>
        </td>
        </tr>
           <tr>
        <td width="37%" class="tab_content" align="right">补登学分：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
       <s:textfield name="budengApply.xuefen" size="10" id="xuefenid"/> 
       	&nbsp;&nbsp;&nbsp;<font color='red'>如果为现场课程，则积分为对应现场课程的积分</font>
        </td>
        </tr>
        <tr>
        <td width="37%" class="tab_content" align="right">计分日期：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
        <jscalendar:jscalendar name="budengApply.budengdate" format="%Y-%m-%d" showstime="false" id="budengdateid"/>
         	&nbsp;&nbsp;&nbsp;<font color='red'>为参加对应培训的时间</font>	
        </td>
        </tr>
        
         <tr>
        <td width="37%" class="tab_content" align="right">补登积分年度：
        </td>
        <td width="63%" colspan="2" class="tab_content1" align="left">
       
       <s:select name="budengApply.theyear" list="jifentime.years"/> 
       	&nbsp;&nbsp;&nbsp;<font color='red'>补登的积分,将会计算到所选择的补登年度</font>
        </td>
        </tr>
		
		
              
       <tr>
          <td align="right" class="tab_content">申请补登理由: </td>
             <td class="tab_content1">
             <s:textarea name="budengApply.applyreason" cols="40" rows="5"/>
             &nbsp;&nbsp;&nbsp;<font color='red'>请输入您要求补登此次积分的理由，以便较快的通过审批</font>
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