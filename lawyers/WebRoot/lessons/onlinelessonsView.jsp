<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<html>
<head>
<title>中国律师培训网-首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>

<script language="javascript" type="text/javascript">
 
var issubmit=false;
function submitform (){	
	 issubmit=true;
	 document.form1.submit();
}
function exitform(){
  if(!issubmit){//如果没有提交,直接关闭窗口的话
  
    $.getJSON("../commonajax/ajaxlogout.pl", {"now":new Date().getTime()}, function(resp){
      }
    ); 
  }
}

function fanye(str){
  document.form2.pageNo.value=str;
  document.form2.submit();
}


</script>

<script language="javascript" id=clientEventHandlersJS>
<!--
var number=6;

function LMYC() {
var lbmc;
//var treePic;
    for (i=1;i<=number;i++) {
        lbmc = eval('LM' + i);
        //treePic = eval('treePic'+i);
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}
 
function ShowFLT(i) {

    lbmc = eval('LM' + i);
    //treePic = eval('treePic' + i)
    if (lbmc.style.display == 'none') {
        LMYC();
        //treePic.src = 'images/nofile.gif';
        lbmc.style.display = '';
    }
    else {
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
}

function LoadshowFLT(){
	var aa=document.getElementById("lessontype").value;

	if(aa==1 || aa==101 || aa==102 || aa==103 || aa==104 || aa==105 || aa==106 || aa==107 || aa==108 || aa==109 || aa==110 || aa==111 || aa==112 || aa==113 ){
		lbmc = eval('LM' + 1);		
	}else if(aa==2 || aa==201 || aa==202){
		lbmc = eval('LM' + 2);
	}else if(aa==3 || aa==301 || aa==302){
		lbmc = eval('LM' + 3);
	}else if(aa==5){
		lbmc = eval('LM' + 4);
	}else if(aa==6 || aa==7){
		lbmc = eval('LM' + 5);
	}else if(aa==4 || aa==8){
		lbmc = eval('LM' + 6);
	}
	
	
	if (lbmc.style.display == 'none') {
        LMYC();
        //treePic.src = 'images/nofile.gif';
        lbmc.style.display = '';
    }
    else {
        //treePic.src = 'images/file.gif';
        lbmc.style.display = 'none';
    }
	
}
//-->
      </script>
</head>

<body onload="LoadshowFLT();">
<div class="header">
  <form name="form1" method="post" action="../common/logout.pl">	
  <div class="logo left"><img src="${resourcePath}${webpara.topbarpic}" width="234" height="51" /></div>
  <div class="denglu right">
  	${lawyer.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp; 
  	<a href="../index/index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="../common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a>
  	
  </div>
  </form>
</div>
<div class="blank15px"></div>
<div class="nav2"><ul>
  <li><a href="../index/index.pl"  >首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1" class="current">选课中心</a></li>
       <!-- <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li> -->
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1"  >培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl" >我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl" >转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="blank15px"></div>
<div class="con">

  <div class="con_rightpl">
    <div class="con_rightpl_title"><h2 style="width: 960px;">
    	课件评论</h2>
    </div>
    <div class="con_rightpl_star2">
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
		<tr>
    		<td valign="top" bgcolor="#FFFFFF">
    			<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
					<tr>						
        				<td width="100%"  class="tab_content1" align="left" style="padding-left: 10px;">
        					<s:hidden name="articles.type"/>
        					<s:hidden name="type"/>    
        					<div class="dtop">名称：${lesson.title}</div>
					<div class="dtop">讲师：${lesson.teachers}</div>				
					<div class="dtop">类别：<s:property value="@com.changpeng.lessons.util.CommonDatas@LessonType[lesson.lessontype]"/>
   				    </div>
					<div class="dtop">学分：${lesson.xuefen}</div>
					<div class="dtop">时间：<s:date name="lesson.lessondate" format="yyyy-MM-dd HH:mm"/>
					<!-- ${lesson.lessondate} -->
					</div>
					
					<s:if test="lesson.lessonstyle==1||lesson.lessonstyle==3">
					<div class="dtop">地点：${lesson.lessonaddress}</div>	
					</s:if>	
			     	<s:if test="lesson.lessonstyle==2||lesson.lessonstyle==3">
				    <div class="dtop">观看：<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lesson.lessonid}","在线培训","")><font color="red">点击观看</font></a></div>
					</s:if>		
					<s:if test="hasattach">
					<div class="dtop">附件（右键点击下载）：<s:iterator value="filelist" status="stat"><a href="download.pl?filename=${filelist[stat.index]}">${filelist[stat.index]}</a>&nbsp;&nbsp;&nbsp;</s:iterator></div>
					</s:if>
					<div class="dtop" id="lessoncontent">
					${lesson.lessoncontent}
					</div>
					<s:if test="lesson.lessonstyle==2||lesson.lessonstyle==3">
				    <div class="dtop">
				    	<s:if test="lesson.onlinefile!=null && lesson.onlinefile!=''"><a  href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lesson.lessonid}&filetype=1","律协在线培训","") ><font color="red" >观看视频</font></a>&nbsp;&nbsp;&nbsp;&nbsp;</s:if>
                		<s:if test="lesson.soundfile!=null && lesson.soundfile!=''"><a  href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lesson.lessonid}&filetype=2","律协在线培训","") ><font color="red">收听音频</font></a> </s:if>   
                	</div>
					</s:if>
						
        				</td>
        			</tr>   
        			<tr>&nbsp;</tr>  
       				<tr>
						<td  class="tab_content1" align="left" style="padding-left: 10px;">
						<div class="dtop"></div>
							
							<s:iterator value="replylist" status="stat">	
								<div class="commenttop">评论人：${replyuser}&nbsp;&nbsp;&nbsp;评论日期:<s:date name="replytime" format="yyyy-MM-dd HH:mm"/></div>								
								<div class="comment">${replycontent}</div>
								<hr width="100%" align="center"/>
							</s:iterator>
							<s:form name="form2" method="post" action="replyCreate" >
								<input type="hidden" name="lessonid" value="${lesson.lessonid}" />
								<div style="padding-left:15px">
								<FCK:editor id="replycontent" height="200" width="80%" skinPath="../editor/skins/default/" basePath="../" toolbarSet="Basic">
								</FCK:editor>	
								</div>
								<div style="padding-left:300px"><input type=submit value="发表评论" class="button"/>
								
								</div>
							</s:form>
							
       					</td>
       				</tr>
   					
    			</table>
    		</td>
  		</tr>
  		
	</table>    	
  	</div>
</div>





</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>
</body>
</html>