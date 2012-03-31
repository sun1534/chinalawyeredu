<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>${webpara.sysname}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/css_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript" src="../js/jquery-1.2.6.pack.js"></script>
<script language="javascript">

function deletephoto(lawyerid){
if(confirm("您确实要删除这个照片吗?")){
var url="../lawyersajax/photoDelete.pl?lawyerid=${lawyers.lawyerid}";
  $.getJSON(url, { "lawyerid":lawyerid,"now":new Date().getTime()}, function(json){
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
</script>
</head>
<body>

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
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">选课中心</a></li>
      <!-- <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li> -->
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1">培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl" class="current">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
</ul></div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>我的学分</strong></div>
<div class="con">
    <div class="con_left3 left">
     <div class="con_left3_title">我的学分</div>   
     <ul class="con_wz">
     	<li><a href="../jifen/jifenQuery.pl" >学分统计</a></li>
     	<li><a href="../jifen/jifenbudengQuery.pl">学分补登查询</a></li>
        <li><a href="../jifen/jifenbudengApply!input.pl">学分补登申请</a></li>
      </ul>
  </div>
  <div class="con_right5 left">
	<div class="con_right5_title"><h2>学分补登查询</h2></div>
    <div class="star4">
    <s:form action="jifenQuery" name="form2" method="post">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td>
    			<span id="right_lblist">
    			<TABLE class="tab_list" width=100% cellSpacing=1 cellPadding=1 align=center bgColor=#b5b5b5 border=0>
    				
    				<tr bgcolor=#f3f3f3>
    					<td align=center height=30 nowrap="nowrap">补登课程标题</td>
    					<td align=center>是否现场课程</td>
    					<td align=center>补登来源</td>
    					<td align=center >积分时间</td>
    					<td align=center >补登学分</td>
    					<td align=center>补登积分年限</td>
    					<td align=center>处理时间</td>
    					<td align=center>处理人员</td>
    				</tr>	
    				<s:set name="zongjifen" value="0"/>
    				<s:iterator value="page.items" status="stat">
    				<tr bgcolor=#f3f3f3>
    					<td align=left height=30 nowrap="nowrap">&nbsp;&nbsp;${title}</td>
    					<td align=center><span class="tab_content">${islocalstr}</span></td>
    					<td align=center><span class="tab_content">
    						<s:if test="budengfrom>0">
					     	 	自己申请
					      	</s:if>
					      	<s:else>
					      		律协补登
					      	</s:else>
					      	</span>
					    </td>  
    					<td align=center><span class="tab_content"><s:date name="budengdate" format="yyyy-MM-dd"/></span></td>
    					<td align=center><span class="tab_content">${xuefen}</span></td>
    					<td align=center><span class="tab_content">${theyear}</span></td>
    					<td align=center><span class="tab_content">${createtime}</span></td>
    					<td align=center><span class="tab_content">${createuser}</span></td>    					
    				</tr>
    				</s:iterator>	
    			</TABLE> 
    			</span>   
   			</td>
  		</tr>
		</table>
		
			${page.pageView}	
    </s:form>
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>

</body>
</html>