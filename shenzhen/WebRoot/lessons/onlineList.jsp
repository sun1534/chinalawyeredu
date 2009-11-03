<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
function refreshscore(lessonid,score){
	if(score!=0){ 
		 var now=new Date().getTime();
		 var url="../lessonajax/refreshScore.pl";		
		 var pars = 'now='+now+'&lessonid='+lessonid+'&score='+score;
		// alert(pars);
         var myAjax = new Ajax.Request(url,{method: 'post', parameters:pars, onComplete: showResponse});	
       
	}
}
function showResponse(originalRequest){
     var res=eval('(' + originalRequest.responseText + ')');
     var lessonid=res.lessonid;
 	var now=new Date().getTime();
var _url="../lessons/lessonsVote.pl?now='+now+'&lessonid="+lessonid;
     if(res.code=="0"||res.code==0){
        window.showModalDialog(_url,"律协在线培训","dialogHeight:260px;dialogWidth:405px;center:yes");
    }else{
       alert(res.msg);
       window.showModalDialog(_url,"律协在线培训","dialogHeight:260px;dialogWidth:405px;center:yes");
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
		<s:form name="form1" action="lessonsOnlineList" method="post">
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
				<tr>
					<td align="left">
					<s:hidden name="pageNo" value="1"/> 
				 	<span style="font-weight: bold">
				 	课程年份:<s:select name="year" list="years" headerKey="-1" headerValue="请选择" onchange="document.form1.submit()"/>
				 	（从【${from}】到【${end}】）
				 	</span>
				 	</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="page-table">
				<tr>
					<td align="right">
						${page.pageView}
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				bgcolor="#EDEDED">
				<tr>
					<TD height="23" width="27%" align="center" background="../imagesa/top-bg1.gif">
						课程名称
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						授课老师
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课程类别
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课时学分
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						授课时间
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						已获学分
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						状态
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						观看
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						投票
					</TD>
				</tr>
				<s:iterator value="onlineList" status="stat">
					<TR>
						<TD class="tab_content" align="left">
							<a href="lessonsOnlineView.pl?lessonid=${lessonid}">${title}</a>
						</TD>				
						<TD class="tab_content" align="center">
							${teachers}
						</TD>
						<TD class="tab_content" align="center">
							<s:if test="lessontype==0">
									大型专题讲座
							</s:if>
							<s:elseif test="lessontype==1">
									学术研讨会
								</s:elseif>
							<s:elseif test="lessontype==2">
									实务培训学习
								</s:elseif>
							<s:elseif test="lessontype==3">
									其他
								</s:elseif>
						</TD>
						
						<TD class="tab_content" align="center">
							${xuefen}
						</TD>

						<TD class="tab_content" align="center">
							${lessondate}

						</TD>
						<TD class="tab_content" align="center" 
							<s:if test="fenshuoff!=null">
                  title="网上培训折扣:${fenshuoff}%"
								</s:if>	
							>
							<s:if test="userlessonxf!=null"> 
								${userlessonxf.pxxf} <br/>
								
							</s:if>
							<s:else>&nbsp;
							</s:else>
						</TD>
						<TD class="tab_content" align="center">
							<s:if test="userlessonxf!=null&&userlessonxf.ismanfen">
		 						<font color=red>已满分</font>
							</s:if>
						</TD>
						<TD class="tab_content" align="center">
							<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","深圳律协在线培训","toolbar=no,location=no,width=500,height=450,menubar=no,scrollbars=no,resizable=no,status=no")>观看</a>
						</TD>
						<TD class="tab_content" align="center">
							<a href="#" onClick="javascript:refreshscore(${lessonid},1)">一般</a>
							<a href="#" onClick="javascript:refreshscore(${lessonid},2)">较好</a>
							<a href="#" onClick="javascript:refreshscore(${lessonid},3)">很好</a>
						</TD>

					</TR>
				</s:iterator>
				</td>
				</tr>
			</table>

		</s:form>

	</body>
</html>