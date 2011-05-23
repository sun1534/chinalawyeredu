<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>调查列表</title>
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
		<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
		<script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
	window.location.href="diaochaCreate!input.pl";
}

function joinDiaocha(diaochaid){
	if(diaochaid!=0){ 
		 var now=new Date().getTime();
		 var url="../diaochaajax/hasReply!input.pl?diaochaid="+diaochaid;			
         var myAjax = new Ajax.Request(url,{method: 'post',onComplete: showResponse});       
	}
}
function showResponse(originalRequest){
    var res=eval('(' + originalRequest.responseText + ')');
    var diaochaid=res.diaochaid;
    if(res.hasreply){
    	alert("您已经参与过该调查，不能重复参与。");
    	//window.open("../diaocha/diaochaView.pl?diaochaid="+diaochaid);
    }else{
    	window.open("../diaocha/diaochaView.pl?diaochaid="+diaochaid);
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
		<table width="99%" height="316" border="0" align="center"
			cellpadding="0" cellspacing="1">

			<s:form name="form1" action="diaochaList" method="post">
			<s:hidden name="pageNo" />
			<tr>
				<td valign="top">
			
					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="page-table">
						<tr>
							<td align="right">${page.pageView}</td>
						</tr>
					</table>

					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#EDEDED">
						<tr>
						
							<TD height="23" align="center"
								background="../imagesa/top-bg1.gif">
								调查名称
							</TD>
									
							<TD align="center" background="../imagesa/top-bg1.gif">
								回复次数
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								调查结果
							</TD>
							<TD align="center" background="../imagesa/top-bg1.gif">
								参与调查
							</TD>
					
						</tr>

						<s:iterator value="page.items" status="stat">
						<TR>
							
							<TD class="tab_content" align="left">&nbsp; &nbsp;
								${title}
							</TD>
							
							<TD class="tab_content" align="center">
					
								${replycount}
		
							</TD>
							<TD class="tab_content" align="center">
								<a href="diaochaStat.pl?diaochaid=${diaochaid}" target="_blank">查看</a>							
							</TD>
							<TD class="tab_content" align="center">
								<a href="../diaocha/diaochaView.pl?diaochaid=${diaochaid}" target="_blank">参与调查</a>
							<!-- 	<a href="#" onClick="javascript:joinDiaocha(${diaochaid})">参与调查</a> -->
							</TD>
						
						</TR>

 					</s:iterator>

						<tr background-color"#F1F1ED>
							<td colspan="7" align="center">
								&nbsp;


							</td>
						</tr>

					</table>
					
				</td>
			</tr>
			</s:form>




		</table>
	</body>
</html>