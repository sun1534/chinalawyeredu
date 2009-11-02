<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>课程列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
.dtop{
	font-size: 9pt;
	font-weight: bold;
	padding-left: 15px;
	padding-top: 3px;
}
.dcontent{
	font-size: 9pt;
	padding-left: 15px;
	padding-top: 10px;
}
.spantitle{
	font-size: 10pt;
	font-weight: bold;
	padding-left: 12px;
	padding-top: 3px;
}
.spanxuefen{
	font-size: 10pt;
	font-weight: bold;
	padding-left: 200px;
	padding-top: 3px;
	color:red;
}
.comment{
	FONT-SIZE: 10.5pt; 
	WIDTH: 416px; 
	BORDER-TOP-STYLE: none; 
	BORDER-RIGHT-STYLE: none; 
	BORDER-LEFT-STYLE: none; 
	HEIGHT: 0px; 
	BORDER-BOTTOM-STYLE: none"
	borderColor="#6699ff" 
	cellSpacing="2" 
	cellPadding="5" 
	bgColor="#99cccc"
	border="1"
}
-->
</style>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript">
function SetCwinHeight(fname)
{
 var cwin=document.getElementById(fname);
 if (document.getElementById)
 {
  if (cwin && !window.opera)
  {
   if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)
    cwin.height = cwin.contentDocument.body.offsetHeight; 
   else if(cwin.Document && cwin.Document.body.scrollHeight)
    cwin.height = cwin.Document.body.scrollHeight;
  }
 }
}
function refreshscore(score){
	if(score!=0){ 
		var now=new Date().getTime();
		 var url="../lessonajax/refreshScore.pl";
		 var common=$("commonl").innerHTML;
		 common=common.substring(0,common.indexOf('('))
     	 var commonl=$("commonl").style.width;
     	 commonl=commonl.substring(0,commonl.indexOf('px'))
     	 
     	 var preferably=$("preferablyl").innerHTML;
		 preferably=preferably.substring(0,preferably.indexOf('('))
     	 var preferablyl=$("preferablyl").style.width;
     	 preferablyl=preferablyl.substring(0,preferablyl.indexOf('px'))
     	 
     	 var good=$("goodl").innerHTML;
		 good=good.substring(0,good.indexOf('('))
     	 var goodl=$("goodl").style.width;
		goodl=goodl.substring(0,goodl.indexOf('px'))
		
		 var pars = 'now='+now+'&lessonid=${lesson.lessonid}&score='+score+'&common='+common+'&commonl='+commonl+'&preferably='+preferably+'&preferablyl='+preferablyl+'&good='+good+'&goodl='+goodl;

//var pars='lessonid=${lesson.lessonid}&score='+score+'&now='+now;
         var myAjax = new Ajax.Request(url,{method: 'post', parameters:pars, onComplete: showResponse});		
	}
}
function showResponse(originalRequest){
     var res=eval('(' + originalRequest.responseText + ')');
    
     if(res.code=="0"||res.code==0){
       $("commonl").innerHTML=res.common+"(票)";
       $("commonl").style.width=res.commonl;
       $("preferablyl").innerHTML=res.preferably+"(票)";
       $("preferablyl").style.width=res.preferablyl;
       $("goodl").innerHTML=res.good+"(票)";
       $("goodl").style.width=res.goodl;
    }else{
       alert(res.msg);
    }
}
</script>

	</head>
	<body>
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
		<table width="99%" height="50" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<tr>
				<td valign="top" width="60%">
					<span class="spantitle" id="title">${lesson.title}</span>
					<span class="spanxuefen" id="xuefen">${lesson.xuefen==null?"0":lesson.xuefen}分</span>
					<hr align="left" width="80%">
					<div class="dtop" id="lessontype">类别：
								<s:if test="lesson.lessontype==0">大型专题讲座</s:if>
								<s:elseif test="lesson.lessontype==1">学术研讨会</s:elseif>
								<s:elseif test="lesson.lessontype==2">实务培训学习</s:elseif>
								<s:elseif test="lesson.lessontype==3">其他</s:elseif>
					</div>
					<div class="dtop" id="teachers">讲师：${lesson.teachers}</div>
					<div class="dtop" >内容：</div>
					<div class="dcontent" id="lessoncontent">
						${lesson.lessoncontent}
					</div>
				</td>
				<td valign="top" width="40%">
					
					<iframe src="lessonsOnlineList.pl"  width="100%"  id="cwin" onload="Javascript:SetCwinHeight('cwin')" frameborder="0" scrolling="no">
					</iframe>
					<div>
					<TABLE class="comment">
						<TR>
							<TD width="20%" bgColor="#99ccff">
								<P align="right" ><FONT face="宋体" size="2"><STRONG>您的评价:</STRONG></FONT></P>
							</TD>
							<TD bgColor="#ffffcc" style="width: 309px">
								<s:select name="comment" list="#{0:'',1:'一般',2:'较好',3:'很好'}" onchange="refreshscore(this.value)"/>
							</TD>
						</TR>
						<TR>
							<TD bgColor="#99ccff" colSpan="2">
									<TABLE style="FONT-SIZE: 9.5pt; VERTICAL-ALIGN: baseline; WIDTH: 368px; HEIGHT: 58px"
										cellSpacing="2" cellPadding="2" width="368" border="0">
										<TR>
											<TD style="WIDTH: 70px" align="center"><STRONG>一般</STRONG></TD>
											<TD><span id="commonl" style="display:inline-block;color:Yellow;background-color:#FF8080;border-color:#FF8080;border-width:1px;border-style:Solid;height:6px;width:${commonl}px;">${common}(票)</span></TD>
										</TR>
										<TR>
											<TD style="WIDTH: 70px" align="center"><STRONG>较好</STRONG></TD>
											<TD><span id="preferablyl" style="display:inline-block;color:#FFFF80;background-color:#C00000;border-color:#C00000;border-width:1px;border-style:Solid;height:6px;width:${preferablyl}px;">${preferably}(票)</span></TD>
										</TR>
										<TR>
											<TD style="WIDTH: 70px" align="center"><STRONG>很好</STRONG></TD>
											<TD><span id="goodl" style="display:inline-block;color:Yellow;background-color:Maroon;border-color:Maroon;border-width:1px;border-style:Solid;height:6px;width:${goodl}px;">${good}(票)</span></TD>
										</TR>
									</TABLE>
								</TD>
							</TR>
						</TABLE>
					</div>
       		 </td>
        	</tr>
        </table>
		
	</body>
</html>