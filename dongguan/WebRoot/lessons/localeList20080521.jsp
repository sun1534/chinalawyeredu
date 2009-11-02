<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>现场课程</title>
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
function localView(lessonid){
      //  var url="../lessonajax/localView.pl?lessonid="+lessonid;
       // var myAjax = new Ajax.Request(url,{method: 'get',  onComplete: showResponse});
parent.location.href="../lessons/lessonsLocalView.pl?lessonid="+lessonid;
}
function showResponse(originalRequest){
     var res=eval('(' + originalRequest.responseText + ')');
 
     parent.document.getElementById("title").innerHTML=res.lesson.title;
     
     var maxpersons=res.lesson.maxpersons;
     if(maxpersons==null) maxpersons="";
     var baomingshu=res.lesson.baomingshu;
     if(baomingshu==null) baomingshu="";
     parent.document.getElementById("time").innerHTML="时间："+res.lesson.lessondate+"--"+res.lesson.lessonend;
     parent.document.getElementById("address").innerHTML="地点："+res.lesson.lessonaddress;
     parent.document.getElementById("teachers").innerHTML="讲师："+res.lesson.teachers;
     parent.document.getElementById("maxpersons").innerHTML="限制："+maxpersons;
     parent.document.getElementById("baimingshu").innerHTML="报名人数："+baomingshu;
     parent.document.getElementById("lessoncontent").innerHTML=res.lesson.lessoncontent;
     
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
function baoming(lessonid){
		var now=new Date().getTime();
		 var url="../lessonajax/lessonBaoming.pl";
	
		
		 var pars = 'now='+now+'&lessonid='+lessonid;
		
         var myAjax = new Ajax.Request(url,{method: 'post', parameters:pars, onComplete: showResponse});		

}
function showResponse(originalRequest){
     var res=eval('(' + originalRequest.responseText + ')');
     if(res.code=="0"||res.code==0){
       alert("网上报名成功!");
    }else{
       alert(res.msg);
    }
}
</script>

</head>
	<body>		
	<s:form name="form1" action="lessonsLocalList" method="post">
			<s:hidden name="pageNo" value="1"/> 
			

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#EDEDED">
				<tr>				
					<TD height="18" align="center" background="../imagesa/top-bg1.gif">
						培训课程
					</TD>
					<TD align="center" background="../imagesa/top-bg1.gif">
						报名
					</TD>					
				</tr>
				<s:iterator value="lessonList" status="stat">
				<TR>			
					<TD class="tab" align="center">
						<a href="#" onclick="localView(${lessonid})">${title}</a>
					</TD>
					
					<TD class="tab" align="center">
						<span style="color:red">
					      <s:if test="baominguser==2">
        报名截止
        </s:if>
        <s:elseif test="baominguser==1">
        已报名
        </s:elseif>
        <s:else>
        <a href="#" onClick="baoming(${lessonid})">报名</a>
        </s:else>
		</span>
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