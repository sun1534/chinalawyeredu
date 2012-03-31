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
function deleteid(favoritesid){
  if(confirm('您确定要将这个课程从收藏夹删除吗')){   
  //alert(favoritesid);
  window.location.href="shopfavoritesDelete.pl?favoritesid="+favoritesid+" ";
  }
}

function fanye(str){
  document.form2.pageNo.value=str;
  document.form2.submit();
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
<div class="nav2">
	<ul>
  <li><a href="../index/index.pl"  >首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1" >选课中心</a></li>
     <!-- <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li> -->
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1"  >培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl" >我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl" class="current">收藏夹</a></li>
  <!--<li><a href="../shopping/shopcartList.pl">购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl">我的订单</a></li>-->
  <li><a href="../lawyers/officeChangeApplyList.pl" >转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
	</ul>
</div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>收藏夹</strong></div>
<div class="con">
	<div class="con_left3 left">
		<div class="con_left3_title">收藏夹</div>   
			<div class="gwc"><a href="../shopping/shopfavoritesList.pl?viewtype=0">
				<s:if test="viewtype==0"><font color="red">全部</font></s:if>
				<s:else>全部</s:else>
				</a>
			</div>
    		<!-- <div class="gwc"><a href="../shopping/shopfavoritesList.pl?viewtype=2">
    		<s:if test="viewtype==2"><font color="red">已购买</font></s:if>
				<s:else>已购买</s:else>
    		</a></div>
     		<div class="gwc"><a href="../shopping/shopfavoritesList.pl?viewtype=1">
     		<s:if test="viewtype==1"><font color="red">未购买</font></s:if>
				<s:else>未购买</s:else>
     		</a></div> -->
  	</div>
  	<div class="con_right4 left">
		<div class="dingdan5 right"><h2>收藏夹</h2></div>
    	<div class="star4">
    	<s:form action="shopfavoritesList" name="form2" method="post">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  				<tr>
    				<td>
    				<span id="right_lblist">
    					<TABLE class='tab_list' width=100% cellSpacing=1 cellPadding=1 align=center bgColor=#b5b5b5 border=0>
    						<TR bgColor=#649fbc nowrap></tr>
    						<tr bgcolor=#f3f3f3>
    							<td align=center height=30 nowrap="nowrap">
    								课程名称
    							</td>
    							<td align=center>主讲人</td>    							
    							<td align=center width="50px;" >学分</td>    							
    							<!--  <td align=center width="65px;">价格(￥)</td>-->
    							<td align=center width="120px;">收藏时间</td>
    							<td align=center width="145px;">操作</td>
    						</tr>
    						<s:set name="allprice" value="0"/>
    						<s:iterator value="page.items" status="stat">
    						<tr bgcolor=#f3f3f3>
    							<td align=left height=30 nowrap="nowrap" title="${title }">&nbsp;&nbsp;${titleTrim}</td>
    							<td align=center>${teachers}</td>
    							
    							<td align=center >${xuefen}</td>    							
    							<!-- <td align=center>${price}</td>  -->
    							<td align=center><s:date name="createdate" format="yyyy-MM-dd HH:mm"/></td>
    							<td align=center width="145px;">
    								<a href="#" onClick="deleteid('${favoritesid}')">【取消收藏】</a>&nbsp;
    								<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","") >【观看】</a>
    								<!--  
    								<s:if test="price!=0">
    									<s:if test="state==0">
    										<a href="../shopping/shopcartCreate.pl?lessonid=${lessonid }">【购买】</a>
    									</s:if>
    									<s:elseif test="state==1">
    										<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","")>【试听】</a>
    									</s:elseif>
    									<s:elseif test="state==2">
    										<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","") >【观看】</a>
    									</s:elseif>    									
    								</s:if>
    								<s:else>
    									<a href=# onclick=javascript:window.open("../jifen/videoLookPre.pl?lessonid=${lessonid}","律协在线培训","") >【观看】</a>
    								</s:else>
    								-->
    							</td> 
    							<s:set name="allprice" value="#allprice+price"/>  							
    						</tr>
    						</s:iterator>
    					</TABLE>   
    					</span> 			
   					</td>
  				</tr>
			</table>
			
			    <div >
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td align="right">
						${page.pageView}
					</td>
				</tr>
			</table>
			<s:hidden name="viewtype" />
			<s:hidden name="pageNo" value="1"/>
    </div>
		</s:form>    
		</div>
    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>

</body>
</html>