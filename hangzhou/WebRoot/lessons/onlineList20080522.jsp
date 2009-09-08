<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>在线课程</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/css.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
body {
	background-color: #DAE7F6;
}
.tab {
    font-size: 9pt;
	color: #666666;
	background-color: #F2F8FF;
    background-repeat: no-repeat;
    background-position: right bottom;
	height: 15px;
}
-->
</style>

<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function onlineView(lessonid){
    //    var url="../lessonajax/onlineView.pl?lessonid="+lessonid;
     //   var myAjax = new Ajax.Request(url,{method: 'get',  onComplete: showResponse});
parent.location.href="../lessons/lessonsOnlineView.pl?lessonid="+lessonid;
}
function showResponse(originalRequest){
     var res=eval('(' + originalRequest.responseText + ')');
 
     parent.document.getElementById("title").innerHTML=res.lesson.title;
     
     var xuefen=res.lesson.xuefen;
     if(xuefen==null) xuefen="0";
	parent.document.getElementById("xuefen").innerHTML=xuefen+"分";
     parent.document.getElementById("teachers").innerHTML="讲师："+res.lesson.teachers;
     parent.document.getElementById("lessoncontent").innerHTML=res.lesson.lessoncontent;
     
     parent.document.getElementById("commonl").innerHTML=res.common+"(票)";
     parent.document.getElementById("commonl").style.width=res.commonl;
     parent.document.getElementById("preferablyl").innerHTML=res.preferably+"(票)";
     parent.document.getElementById("preferablyl").style.width=res.preferablyl;
     parent.document.getElementById("goodl").innerHTML=res.good+"(票)";
     parent.document.getElementById("goodl").style.width=res.goodl;
    
     var lessontype=res.lesson.lessontype;
     if(lessontype==0)
     	parent.document.getElementById("lessontype").innerHTML="类别：大型专题讲座";
     else if(lessontype==1)
     	parent.document.getElementById("lessontype").innerHTML="类别：学术研讨会";
     else if(lessontype==2)
     	parent.document.getElementById("lessontype").innerHTML="类别：实务培训学习";
     else
     	parent.document.getElementById("lessontype").innerHTML="类别：其他";
  
}

</script>

</head>
	<body>		
	<s:form name="form1" action="lessonsLocalList" method="post">
			<s:hidden name="pageNo" value="1"/> 
			

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#EDEDED">
				<tr>				
					<TD height="18" width="50%" align="center" background="../imagesa/top-bg1.gif">
						在线课程
					</TD>
					<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
						&nbsp;
					</TD>
					<TD align="center" width="15%" background="../imagesa/top-bg1.gif">
						方式
					</TD>
					<TD align="center" width="10%" background="../imagesa/top-bg1.gif">
						积分
					</TD>
					<TD align="center" width="15%" background="../imagesa/top-bg1.gif">
						&nbsp;
					</TD>						
				</tr>
				<s:iterator value="onlineList" status="stat">
				<TR>			
					<TD class="tab" align="center">
						<a href="#" onclick="onlineView(${lessonid})">${title}</a>
					</TD>
					
					<TD class="tab" align="center">
						<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","深圳律协在线培训","toolbar=no,location=no,width=500,height=450,menubar=no,scrollbars=no,resizable=no,status=no")>观看</a>
					</TD>
					<TD class="tab" align="center">
						<s:if test="userlessonxf!=null">
  						${userlessonxf.learnmode}	
					</s:if>
					<s:else>&nbsp;
					</s:else>
					</TD>
					<TD class="tab" align="center">
						<s:if test="userlessonxf!=null"> 
						${userlessonxf.pxxf}	
						</s:if>
					<s:else>&nbsp;
					</s:else>
					</TD>
					<TD class="tab" align="center">
						<s:if test="userlessonxf!=null&&userlessonxf.pxxf.equals(xuefen)">
 						<font color=red>已满分</font>
						</s:if>			
					</TD>
					</TR>
				 </s:iterator>	
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="page-table">
				<tr>
					<td align="right">${page.pageView}</td>
				</tr>
			</table>
	</s:form>
       	
	</body>
</html>