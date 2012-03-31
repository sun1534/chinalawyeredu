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
<jscalendar:head/>
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
  <div class="logo left"><a href="index.html"><img src="${resourcePath}${topbarpic}" width="234" height="51" /></a></div>
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
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<a href="../jifen/jifenQuery.pl"><strong>我的学分</strong></a></div>
<div class="con">
    <div class="con_left3 left">
     <div class="con_left3_title">我的学分</div>   
     <ul class="con_wz">
     	<li><a href="../jifen/jifenQuery.pl" >学分统计</a></li>
     	<li><a href="../jifen/jifenbudengApplyList.pl">学分补登查询</a></li>
        <li><a href="../jifen/jifenbudengApply!input.pl">学分补登申请</a></li>
      </ul>
  </div>
  <div class="con_right5 left">
	<div class="con_right2_title"><h2>学分统计</h2></div>
    <div class="star4">
    <s:form action="jifenQuery" name="form2" method="post">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    	<tr>
    		<td>
    			<span id="right_lblist">
    			<TABLE class="tab_list" width=100% cellSpacing=1 cellPadding=1 align=center bgColor=#b5b5b5 border=0>
    				<tr  bgcolor=#f3f3f3 >
    					<td colspan="8" height="40"> 
    						 &nbsp;&nbsp;积分年限:<s:select name="nianshenyear" list="jifentime.years" onchange="document.form2.submit()"/>
           					(积分计算：从【${jifentime.startstr}】到【${jifentime.endstr}】。达标需满【${dabiaofen }】分)
           				</td>
    				</tr>
    				<tr bgcolor=#f3f3f3>
    					<td align=center height=30 nowrap="nowrap">课程名称</td>
    					<td align=center>培训方式</td>
    					<td align=center>培训日期</td>
    					<td align=center >课程时长(分)</td>
    					<td align=center >培训时长(分)</td>
    					<td align=center>课程积分</td>
    					<td align=center>获得积分</td>
    					<td align=center>积分年度</td>
    				</tr>	
    				<s:set name="alljifen" value="0"/>
    				<s:iterator value="page.items" status="stat">
    				<tr bgcolor=#f3f3f3>
    					<td align=left height=30 nowrap="nowrap">
    						<s:if test="lessonid>0">
					        	<a href="../lessons/lessonsView.pl?lessonid=${lessonid }">&nbsp;&nbsp;${title}</a>
					        </s:if>
					        <s:else>
					        	&nbsp;&nbsp;${title}
					        </s:else>
    					</td>
    					<td align=center><span class="tab_content"><s:property value="@com.changpeng.jifen.util.CommonDatas@LEARNMODE[learnmode]"/></span></td>
    					<td align=center><span class="tab_content">${pxdate}</span></td>
    					<td align=center><span class="tab_content">${pxreqminutes}</span></td>
    					<td align=center><span class="tab_content">${pxminutes}</span></td>
    					<td align=center><span class="tab_content">${zongjifen}</span></td>
    					<td align=center><span class="tab_content">${pxxf}</span></td>    					
    					<td align=center><span class="tab_content">${theyear}</span></td>
    					<s:set name="alljifen" value="#alljifen+pxxf"/>
    				</tr>
    				</s:iterator>	
    			</TABLE> 
    			</span>   
   			</td>
  		</tr>
		</table>
		<div class="jier2">积分统计：
     		现场培训:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(localecnt)"/>&nbsp;
      		在线视频:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(video)"/>&nbsp;
       		文本课件:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(wenbenkejian)"/>&nbsp;
       		补登积分:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(budeng)"/>&nbsp;
  			总积分:<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(#alljifen)"/></div>
    	</div>
    </s:form>
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="#">长鹏软件</a><br/>
备案序号：粤ICP备05082150号
</div>

</body>
</html>