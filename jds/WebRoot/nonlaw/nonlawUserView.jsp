<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../js/thickbox.css" type="text/css" media="screen" />
<style type="text/css">
		body {
			FONT-SIZE: 12px;
		}
		#layer1 
		{
			position: absolute;
			left:380px;
			top:150px;
			width:250px;
			background-color:#f0f5FF;
			border: 1px solid #000;
			z-index: 50;
		}
		#layer1_handle 
		{
			background-color:#5588bb;
			padding:2px;
			text-align:center;
			font-weight:bold;
			color: #FFFFFF;
			vertical-align:middle;
		}
		#layer1_content 
		{
			padding:5px;
		}
		#close
		{
			float:right;
			text-decoration:none;
			color:#FFFFFF;
		}
		h1, h2
		{
			font-size:20px;
		}
.jqDrag {
  width: 100%;
  cursor: move;
}
.jqDnR {	
    z-index: 3;
    position: relative;   
    width: 400px;
    font-size: 0.77em;
    color: #618d5e;
    margin: 5px 10px 10px 10px;
    background-color: #EEE;
    border: 1px solid #CCC;
}
	</style>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="../js/thickbox-compressed.js"></script> 
<script type="text/javascript" src="../js/jqDnR.js"></script> 
<script type="text/javascript" src="../js/callCenter.js"></script> 
<script language="javascript">

$(document).ready(function(){
	var call=false;
	$("#layer1").hide();
	//$("#showmsg").hide();	
	$("#jqdrag").hide();	
	$('#savelog').click(function(){
		$("#jqdrag").show();
		$("#logFrame").get(0).src="nonlawlogCreate2!input.action?nonlawtaskid=${nonlawtaskid}";
	});
	$('#closelog').click(function(){
		$("#jqdrag").hide();
	});		
	$('#call').click(function()
	{	
		$("#showmsg").html("");
		$("#layer1").show();
		//alert($('input[@name=phoneradio][@checked]').val());
	});
	
	$('#close').click(function()
	{	if(call)
			Release('${curuser.userno}','${curuser.bqqno}');	
		$("#layer1").hide();
		call=false;
	});
	
	$('#closeButton').click(function()
	{	
		if(call)
			Release('${curuser.userno}','${curuser.bqqno}');		
		$("#layer1").hide();
		call=false;
	});
	
	$('#callPhone').click(function()
	{	  		
		  
		if(!isTEL($("#phone").val())){
			alert("请输入正确的呼叫号码");
		}else{
			//呼叫前发起挂断(暂停)操作
			Wrap('${curuser.userno}','${curuser.bqqno}');		
			call=true;
			//Dail($("#phone").val(),'${curuser.userno}','${curuser.bqqno}');
			//等待600毫秒后再发起呼叫操作
			window.setTimeout("Dail($('#phone').val(),'${curuser.userno}','${curuser.bqqno}')",600);
		}
	});
	$('#jqdrag').jqDrag();
	//$('#jqdrag').css('opacity',0.6).jqDrag();
});
function setPhone(val){
	$("#phone").val(val);
}
function phoneDelete(phoneid){
	$.ajax({
           type:"GET",
           url:'../addressAjax/addressDelete.action',
           dataType:"json",
           data:"addressid="+phoneid,
           success:function(data){
		    if(data.result){
		      	alert("号码删除成功。");
		      	location.reload();
		      }
           }
    });  
}
function logDelete(logid){
	$.ajax({
           type:"GET",
           url:'../nonlawAjax/nonlawlogAjaxDelete.action',
           dataType:"json",
           data:"logid="+logid,
           success:function(data){
		    if(data.result){
		      	alert("日志删除成功。");
		      	location.reload();
		      }
           }
    });  
}
function canlink(val){
	
	if(confirm("是否修改?")){
		$.ajax({
           type:"GET",
           url:'../nonlawAjax/taskcanLink.action',
           dataType:"json",
           data:"nonlawtaskid=${nonlawtask.nonlawtaskid}&canlink="+val+"&"+new Date().getTime(),
           success:function(data){
		    if(data.result){
		      	alert("修改成功。");
		      	//location.reload();
		      }
           }
   		 });  
	}
}
</script>

</HEAD>
<BODY >

<fieldset style="text-align:center;width:620px;padding-top:0px;margin-top:0px;FONT-SIZE: 12px;">&nbsp; 
	<legend>
		<span style="font:bold">基本信息</span>
	</legend>
	<table align="center"  border=0 align=center style="FONT-SIZE:12px;">
		<tr>
			<td width="15%" align="right">客户姓名:</td><td width="30%" align="left">${nonlaw.username}</td>
			<td width="15%" align="right">借据号:</td><td width="30%" align="left">${nonlaw.duebill}</td>
		</tr>
		<tr>
			<td width="15%" align="right">贷款账号:</td><td width="30%" align="left">${nonlaw.lendaccount}</td>
			<td width="15%" align="right">扣款账号:</td><td width="30%" align="left">${nonlaw.payaccount}</td>
		</tr>
		<tr>
			<td width="15%" align="right">合作项目:</td><td width="30%" align="left">${nonlaw.synergicname}</td>
			<td width="15%" align="right">证件号码:</td><td width="30%" align="left">${nonlaw.idcard}</td>
		</tr>
		<tr>
			<td width="15%" align="right">贷款金额:</td><td width="30%" align="left">${nonlaw.lendfee}</td>
			<td width="15%" align="right">贷款余额:</td><td width="30%" align="left">${nonlaw.curbalancefee}</td>
		</tr>
		<tr>
			<td width="15%" align="right">逾期本金:</td><td width="30%" align="left">${nonlaw.curoverfee}</td>
			<td width="15%" align="right">逾期利息:</td><td width="30%" align="left">${nonlaw.curaccrualfee}</td>
		</tr>
		<tr>
			<td width="15%" align="right">逾期期数:</td><td width="30%" align="left">${nonlaw.curoverstat}</td>
			<td width="15%" align="right">台帐逾期期数:</td><td width="30%" align="left">${nonlaw.overnum}</td>
		</tr>
		<tr>
			<td width="15%" align="right">每月还本:</td><td width="30%" align="left">${nonlaw.monthfee}</td>
			<td width="15%" align="right">每月还息:</td><td width="30%" align="left">${nonlaw.breachfee}</td>
		</tr>	
		<tr>
			<td width="15%" align="right">贷款时间:</td><td width="30%" align="left">${nonlaw.lenddate}</td>
			<td width="15%" align="right">贷款到期日:</td><td width="30%" align="left">${nonlaw.lendoverdate}</td>
		</tr>	
		<tr>
			<td width="15%" align="right">购房地址:</td><td width="30%" align="left">${nonlaw.buyaddr}</td>
			<td width="15%" align="right">住址:</td><td width="30%" align="left">${nonlaw.homeaddr}</td>
		</tr>		
		<tr>
			<td width="15%" align="right">单位名称:</td><td width="30%" align="left">${nonlaw.company}</td>
			<td width="15%" align="right">单位地址:</td><td width="30%" align="left">${nonlaw.companyaddr}</td>
		</tr>
		<tr>
			<td width="15%" align="right">支行名称:</td><td width="30%" align="left"  colspan="3">${nonlaw.bankname}</td>
			
		</tr>
		<tr>
			
			<td width="15%" align="right"><font color="red">约定还款:</font></td>
			<td colspan="3">
			<a href="setPaydate!input.action?nonlawtaskid=${nonlawtask.nonlawtaskid}&keepThis=true&TB_iframe=true&height=250&width=400" title="约定还款日期设置" class="thickbox" >		
			<font color="red">
			<s:if test="nonlawtask.paydate==null">设置</s:if>
			<s:else>${nonlawtask.paydate}</s:else>
			</font>
			</a>
			</td>
		</tr>
	</table>
</fieldset>
<fieldset style="text-align:center;width:620px;">
	<legend>
		<span style="font:bold">联系电话</span>
	</legend>
	<table align="center" width="95%"  border=0 align=center style="FONT-SIZE:12px;">
		<TR class="listheadline">
			<td>&nbsp;</td>
			<td>号码</td>
			<td>姓名</td>
			<td>说明</td>
			<td>编辑</td>
			<td>删除</td>
		</tr>
		<s:iterator value="addressList" status="stat">
			<TR class=listline>
				<td><input type="radio" name="phoneradio" value="${phone}" onclick="setPhone(this.value)"></td>
				<td><div id="phone${stat.index}">${phone}</div></td>
				<td>${username}</td>
				<td>${comments}</td>
				<td>
					<a href="../address/addressEdit!input.action?addressid=${addressid}&keepThis=true&TB_iframe=true&height=250&width=400" title="号码编辑" class="thickbox" >编辑</a></td>
				<td><a href="#" onclick="phoneDelete(${addressid})">删除</a></td>
			</tr>
		</s:iterator>	
		<TR class="listline"><td colspan="6">是否能联系上:<s:select name="nonlawtask.canlink" list="#{'y':'是','n':'否','yy':'联系亲友','nn':'无法接通'}" onchange="canlink(this.value)"/></td></tr>
	</table>
	 <div align="center" style="padding-top:10px"> 
	 	<a href="../address/addressCreate!input.action?oprflag=2&oprid=${nonlaw.nonlawid}&keepThis=true&TB_iframe=true&height=250&width=400" title="号码新增" class="thickbox" >
			<input type=button class="mask" value="新增" id="addPhone">&nbsp;&nbsp;
		</a>
		<input type=button class="mask" value="呼叫" id="call"> &nbsp;&nbsp;
		<!-- 
		<input type=button class="mask" value="挂断"> &nbsp;&nbsp;
		
		<a href="nonlawlogCreate2!input.action?nonlawtaskid=${nonlawtaskid}&keepThis=true&TB_iframe=true&height=250&width=500" title="记录日志" class="thickbox" >
			<input type=button class="mask" value="记录日志">
		</a> 
		 -->
		 <input type=button class="mask" value="记录日志" id="savelog">
		&nbsp;&nbsp;
		<a href="repaylogList.action?oprflag=1&nonlawid=${nonlaw.nonlawid}&keepThis=true&TB_iframe=true&height=250&width=500" title="还款记录" class="thickbox" >
			<input type=button class="mask" value="还款记录">
		</a> 
		&nbsp;&nbsp;
		<a href="../address/addressList.action?keepThis=true&TB_iframe=true&height=200&width=500" title="号码查询" class="thickbox" >
			<input type=button class="mask" value="号码查询">
		</a>    
		&nbsp;&nbsp;
		<a href="../sms/smsSend!input.action?keepThis=true&TB_iframe=true&height=200&width=500" title="发送短信" class="thickbox" >
			<input type=button class="mask" value="发送短信">
		</a> 
	</div>      
</fieldset>
<fieldset style="text-align:center;width:620px;">
	<legend>
		<span style="font:bold">催收日志</span>
	</legend>
	<table align="center" width="95%"  border=0 align=center style="FONT-SIZE:12px;">
		<TR class="listheadline">
			<td width="25%">催收日期</td>
			<td width="75%">说明</td>
		</tr>
		<s:iterator value="logList" status="stat2">
			<TR class=listline>
				<td >${logtime}</td>
				<td >
				${comments}&nbsp;
				<a href="#" title="删除" style="color:red" onclick="logDelete(${logid})">[x]</a>
				</td>			
			</tr>
		</s:iterator>	
	</table> 
</fieldset>

 <div align="center" style="padding-top:10px"> </div>          
 <div id="layer1">
	<div id="layer1_handle">			
		<a href="#" id="close">[ x ]</a>
		呼叫中心
	</div>
	<div id="layer1_content">
		<form id="layer1_form" method="post" action="save_settings.php">
		
			请选择呼叫号码:<br>
			<input type='text' name='phone' id='phone'>
			<div id="showmsg" style="color:red"></div>
			<div align="center" style="padding-top:8px"><input type="button" value="确定" id="callPhone"/>&nbsp;&nbsp;<input type="button" id="closeButton" value="挂断" /></div>
		</form>
	</div>
</div>     
<div id="jqdrag" class="jqDnR">
  <div style="height:20px;width:400px;text-align:right"><a href="#" id="closelog" style="float:right;FONT-SIZE:12px;color:blue">关闭</a></div>
  <iframe src="#" id="logFrame" height="200px" width="398px"/>
</div>
</BODY>
</HTML>
