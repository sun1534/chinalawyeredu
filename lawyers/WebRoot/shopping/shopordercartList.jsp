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
function deleteid(cartid){
  if(confirm('您确定要将这个课程从购物车删除吗')){   
  	window.location.href="shopcartDelete.pl?cartid="+cartid+" ";
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
<div class="nav2">
	<ul>
  <li><a href="../index/index.pl"  >首页</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=2&lessontype=-1" >选课中心</a></li>
  <li><a href="../shopping/shoppaidList.pl">我选购的课程</a></li>
  <li><a href="../lessons/lessonsList.pl?lessonstyle=1" >培训通知</a></li>
  <li><a href="../jifen/jifenQuery.pl">我的学分</a></li>
  <li><a href="../shopping/shopfavoritesList.pl"  >收藏夹</a></li>
  <li><a href="../shopping/shopcartList.pl" >购物车</a></li>
  <li><a href="../shopping/shopOrderList.pl" class="current">我的订单</a></li>
  <li><a href="../lawyers/officeChangeApplyList.pl" >转所申请</a></li>
  <li><a href="../lawyers/lawyersEditSelf!input.pl" >个人信息</a></li>
  <li><a href="../articles/notifyList.pl?type=1" >系统消息</a></li>
	</ul>
</div>
<div class="gml">当前位置：<a href="../index/index.pl">首页</a>----<strong>我的订单</strong></div>
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
  	<div class="con_right4 left">
		<div class="dingdan5 right"><h2>确认订单</h2>&nbsp;&nbsp;&nbsp;&nbsp;订单编号：${orderno}</div>
    	<div class="star4">
    	<s:form action="../OrderPayment" name="form2" method="post">
    				
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
			<table>
			<tr>
				<td>
					<font color=red>*</font>商户号
				</td>

				<td>
                     808080011391913<input type="hidden" name="MerId" value="808080011391913" />
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单号
				</td>

				<td>
                     <input type="text" name="OrdId"  maxlength="16" value="${orderno}"/> &nbsp;(16位数字，订单号的第5～9位必须和商户号的第11～15位相同。不填由系统自动产生)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单金额
				</td>

				<td>
                     <input type="text" name="TransAmt"  maxlength="12" value="${TransAmt}"/> &nbsp;(12位数字，不填默认金额为1分)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易日期
				</td>

				<td>
                     <input type="text" name="TransDate"  maxlength="8" value="${TransDate}"/> &nbsp;(8位数字，不填系统默认为当前日期)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易类型
				</td>

				<td>
                     <input type="text" name="TransType" value="0001" maxlength="4" /> &nbsp;(4位数字，支付交易为0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易币种
				</td>

				<td>
                     <input type="text" name="CuryId" value="156" maxlength="3" /> &nbsp;(3位，默认为156 人民币)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>版本号
				</td>

				<td>
                     <input type="text" name="Version" value="20070129 " maxlength="8" /> &nbsp;(8位数字，支付接口版本号)
                </td>
			</tr>
			<tr>
				<td>
					支付网关号
				</td>

				<td>
                     <input type="text" name="GateId" maxlength="4" /> &nbsp;(4位数字，可以为空)
                </td>
			</tr>
<tr>
				<td>
					页面应答接收URL
				</td>

				<td>
                     <input type="text" name="PageRetUrl" value="http://localhost:8080/lawyerNew/shopping/shopOrderReturn.pl" maxlength="80" /> &nbsp;(不超过80字节，商户系统前台应答接受地址)
                </td>
			</tr>
						<tr>
				<td>
					<font color=red>*</font>后台应答接收URL
				</td>

				<td>
                     <input type="text" name="BgRetUrl" value="http://131.252.83.73:8080/chinapay_java/getBgReturn" maxlength="80" /> &nbsp;(不超过80字节，商户系统后台应答接受地址)
                </td>
			</tr>
			<tr>
				<td>
					商户私有域
				</td>

				<td>
                     <input type="text" name="Priv1"  maxlength="60" /> &nbsp;(英文或数字，不超过60字节，ChinaPay将原样返回给商户系统该字段填入的数据)
                </td>
			</tr>
		</table>
			<div class="jier">商品金额总计：￥<s:property value="@com.changpeng.jifen.util.NumberUtil@toMoney(#allprice)"/></div>
			<div class="js">				
				<INPUT type="submit" value="提交订单" name="addbutton"  />
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