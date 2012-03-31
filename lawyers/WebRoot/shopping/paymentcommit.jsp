<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="paybean" scope="request" class="com.changpeng.shopping.util.PaymentBean" />
<jsp:useBean id="pay_url" scope="request" class="java.lang.String" />
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

</script>
</head>
<body>

<div class="header">
  <form name="form1" method="post" action="common/logout.pl">	
  <div class="logo left"><img src="${resourcePath}${webpara.topbarpic}" width="234" height="51" /></div>
  <div class="denglu right">
  	${lawyer.lawyername}律师 您好，欢迎您登录培训平台！&nbsp;&nbsp; 
  	<a href="../index/index.pl">首页</a>&nbsp;&nbsp;
  	<s:if test="lawyer.provinceunion!=22"><a href="common/passwdChange!input.pl">修改密码</a></s:if>&nbsp;&nbsp;
  	<a href="#" onclick="submitform()">退出</a>
  </div>
  </form>
</div>
<div class="blank15px"></div>
<div class="nav2">
	<ul>
	  <li><a href="../index/index.pl"  >首页</a></li>
	  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1">选课中心</a></li>
	  <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li>
	  <li><a href="../lessons/lessonsList.pl?lessonstyle=1">培训通知</a></li>
	  <li><a href="../jifen/jifenQuery.pl" >我的学分</a></li>
	  <li><a href="../shopping/shopfavoritesList.pl">收藏夹</a></li>
	  <li><a href="../shopping/shopcartList.pl" >购物车</a></li>
	  <li><a href="../shopping/shopOrderList.pl" class="current">我的订单</a></li>
	  <li><a href="../lawyers/officeChangeApplyList.pl">转所申请</a></li>
	  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
	  <li><a href="../articles/notifyList.pl?type=1">系统消息</a></li>
	</ul>
</div>
<div class="gml">当前位置：<a href="index/index.pl">首页</a>----<strong>我的订单</strong></div>
<div class="con">
	<div class="con_left3 left">
			<div class="con_left3_title">我的订单</div>  
     		
     		<div class="dan">
				<ul>
					<li><a href="../shopping/shopOrderList.pl?viewtype=0">全部</a></li>
		       		<li><a href="../shopping/shopOrderList.pl?viewtype=2">已结算</a></li>
		       		<li><a href="../shopping/shopOrderList.pl?viewtype=1">未结算</a></li>
		     	</ul>
     		</div>
  	</div>
  	<div class="con_right3 left">
    <div class="con_right2_title"><h2>支付信息</h2></div>
    <div class="star3">
	   <form name="payment" action="<%= pay_url %>"  method="post"  target="_blank">
<%
	String  MerId = paybean.getMerId();
	String  OrdId = paybean.getOrdId();
	String  TransDate = paybean.getTransDate();
	String  TransType = paybean.getTransType();
	String  TransAmt = paybean.getTransAmt();
	String  CuryId = paybean.getCuryId();
	String  GateId = paybean.getGateId();
	String  Version	= paybean.getVersion();
	String  ChkValue = paybean.getChkValue();
	String  BgRetUrl = paybean.getBgRetUrl();
	String  PageRetUrl = paybean.getPageRetUrl();
	String  Priv1 = paybean.getPriv1();
	
	//20100304版需要参数
	String  ClientIp = paybean.getClientIP();
	//20080515版需要参数
	String  CountryId = paybean.getCountryId();
	String  TimeZone = paybean.getTimeZone();
	String  TransTime = paybean.getTransTime();
	String  DSTFlag = paybean.getDSTFlag();
	String  ExtFlag = paybean.getExtFlag();
	String  Priv2 = paybean.getPriv2();
	
	
%>	

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
    							<td align=center >学分</td>
    							<td align=center>加入时间</td>
    							<td align=center width="65px;">价格(￥)</td>
    							
    						</tr>
    						<s:set name="allprice" value="0"/>
    						<s:iterator value="page.items" status="stat">
    						<tr bgcolor=#f3f3f3>
    							
    							<td align=left height=30 nowrap="nowrap">&nbsp;&nbsp;${title}</td>
    							<td align=center>${teachers}</td>
    							<td align=center >${xuefen}</td>
    							<td align=center><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
    							<td align=right>${price}&nbsp;&nbsp;&nbsp;&nbsp;</td> 
    							
    							<s:set name="allprice" value="#allprice+price"/>  							
    						</tr>
    						</s:iterator>
    					</TABLE>   
    					</span> 			
   					</td>
  				</tr>
			</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">		
			
			<tr>
				<td width="100px;" height="30px;" align="right" >
					商户号:
				</td>

				<td width="200px;">
                     <%= MerId %>
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					订单号:
				</td>

				<td>
                     <%= OrdId %> 
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					交易日期:
				</td>

				<td>
                     <%= TransDate %>
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					交易类型:
				</td>

				<td>
                     在线支付
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					交易币种:
				</td>

				<td>
                     人民币
                </td>
			</tr>
			<tr>
				<td width="100px;" height="30px;" align="right" >
					支付金额:
				</td>

				<td>
                     <%= TransAmt %> &nbsp;<font color=gray>(12位数字，不填默认金额为1分)</font>
                </td>
			</tr>
			<tr>
				

				<td colspan="2" align="right">
                    <input type='button' name='v_action' value='确认支付' onClick='document.payment.submit()' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
			</tr>
			
			

		</table>	

<%
	
%>
	<hr/>
<input type=hidden name="MerId" value="<%=MerId%>" />
<input type=hidden name="OrdId" value="<%=OrdId%>"/>
<input type=hidden name="TransAmt" value="<%=TransAmt%>" />
<input type=hidden name="CuryId" value="156" />
<input type=hidden name="TransDate" value="<%=TransDate%>" />
<input type=hidden name="TransType" value="<%=TransType%>" />
<input type=hidden name="Version" value="<%=Version%>" />
<input type=hidden name="BgRetUrl" value="<%=BgRetUrl%>" />
<input type=hidden name="PageRetUrl" value="<%=PageRetUrl%>" />
<input type=hidden name="Priv1" value="<%=Priv1%>" />
<input type=hidden name="ChkValue" value="<%=ChkValue%>" />
<input type=hidden name="GateId" value="<%=GateId%>" />
<input type=hidden name="ClientIp" value="<%=ClientIp%>" />

<input type=hidden name="CountryId" value="<%=CountryId%>" />
<input type=hidden name="TransTime" value="<%=TransTime%>" />
<input type=hidden name="TimeZone" value="<%=TimeZone%>" />
<input type=hidden name="DSTFlag" value="<%=DSTFlag%>" />
<input type=hidden name="ExtFlag" value="<%=ExtFlag%>"/>
<input type=hidden name="Priv2" value="<%=Priv2%>"/>

		
</form>






    
    </div>
    
  </div>
</div>
<div class="blank15px"></div>
<div class="copy3">CopyRight(C)  中国律师培训网  版权所有    设计制作：<a href="http://www.cpsoft.cn/" target="_blank">长鹏软件</a></br>
备案序号：粤ICP备05082150号
</div>
</body>
</html>





<title>ChinaPay 银联电子支付</title>
</head>
<br />
<p class="menu">
	
</p>



<body>

</body>
</html>