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
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">
function getCities(vallll){
  $("#city")[0].length=0;
  var _o=new Option('请选择',0);
  $("#city")[0].options.add(_o);  
  if(vallll!=0){
     $.getJSON("../commonajax/getSubGroup.pl", { "parentid": vallll,"now":new Date().getTime()}, function(json){
     for(var k in json.groups)  
     {     
        var _o=new Option(json.groups[k.toString()],k);
		$("#city")[0].options.add(_o);  
     }
}); 
  }
}
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function refreshscore(lessonid,score){
	if(score!=0){ 
		 var now=new Date().getTime();
$.getJSON(
	   "../lessonajax/refreshScore.pl",
	   { "now":now,"lessonid":lessonid,"score":score},
	    function(res){
	
         //   var res=eval( data);
     //   alert(res.lessonid);
            var lessonid=res.lessonid;
 	        var now=new Date().getTime();
            var _url="../lessons/lessonsVote.pl?now="+now+"&lessonid="+lessonid;
            if(res.code=="0"||res.code==0){
              window.showModalDialog(_url,"律协在线培训","dialogHeight:260px;dialogWidth:405px;center:yes");
            }else{
                alert(res.msg);
              window.showModalDialog(_url,"律协在线培训","dialogHeight:260px;dialogWidth:405px;center:yes");
           }
	    });
       
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
					<img src="../imagesa/b_02.gif" width="4" height="7">
					${navigator}
				</td>
			</tr>
		</table>
		<s:form name="form1" action="lessonsList" method="post">
		<s:hidden name="lessontype"/>
		<s:hidden name="lessonstyle"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
						align="center" class="query-table">
				<tr>
					<td align="left">
				
                	<s:hidden name="pageNo" value="1"/> 
                <span style="font-weight: bold">
                来源：
                  <s:select name="datavisible.provinceid" id="province" list="datavisible.provincelist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择" onchange="getCities(this.value)"/>
             <s:select name="datavisible.cityid" id="city" list="datavisible.citylist" listKey="groupid" listValue="groupname" headerKey="0" headerValue="请选择"/>
            		
                    老师:<s:textfield name="teachers" size="12"/>
            
   			 	    名称:<s:textfield name="title" size="15"/>
                 
  
				 课程年份:<s:select name="nianshenyear" list="jifentime.years" headerKey="0" headerValue="全部" onchange="document.form1.submit()"/>
			 <s:if test="nianshenyear!=0">
			 	（从【${jifentime.startstr}】到【${jifentime.endstr}】）
			</s:if>
				 	</span>
                    <s:submit value=" 查 询 " cssClass="button" />
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
						主讲人
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课程类别
					</TD>
					<TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课时学分
					</TD>
                    <TD height="23" align="center" background="../imagesa/top-bg1.gif">
						课程来源
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
				<s:iterator value="lessonlist" status="stat">
					<TR>
						<TD class="tab_content" align="left">
							<a href="lessonsView.pl?lessonid=${lessonid}">${title}</a>
						</TD>				
						<TD class="tab_content" align="center" title="${teacher}">
							<div style="overflow:hidden;text-overflow:ellipsis;">${teachers}</div>
                            
						</TD>
						<TD class="tab_content" align="center">
                        <s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lessontype]"/>
                      
						</TD>
						
						<TD class="tab_content" align="center">
							${xuefen}
						</TD>
                        <TD class="tab_content" align="center">
                  
                         <s:property value="@com.changpeng.common.CommonDatas@groups[groupid]"/>
                       
						  </TD>
                      
						<TD class="tab_content" align="center">
						  ${lessondate}
						</TD>
						<TD class="tab_content" align="center" 
							<s:if test="fenshuoff!=null">title="网上培训折扣:${fenshuoff}%"</s:if>	
							>
								${yihuoxuefen.pxxf} <br/>
							
						</TD>
						<TD class="tab_content" align="center">
							<s:if test="isfull">
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
			
			</table>

		</s:form>

	</body>
</html>