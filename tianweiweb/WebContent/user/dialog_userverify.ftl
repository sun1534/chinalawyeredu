<#escape x as (x)!"">
<script type="text/javascript" src="../js/base/jquery.MultiFile.js"></script>
<script type="text/javascript" src="../js/sctx.js"></script>
<style type="text/css">@import url(../css/userset.css);</style>
<script type="text/javascript" src="${staticpath}/js/location.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<div class="popbox2" id="userverifyBox" style="">
     <form id="form1" method="post" action="../user/userbaseupdate.action">
     	<input type="hidden" name="fromorder" value="true" />
     	<input type="hidden" name="approve_type" id="approve_type" value="" />
		<div id="baseadmininfo" class="form" >

		<div class="odd">
			<label class="fname" for="pname">认证方式：</label>
			<span class="fvalue"><input type="radio"  value="1" onclick="selectit(1)"/>身份证认证
				<input type="radio"  value="2" onclick="selectit(2)" />电话认证
				<input type="radio"  value="3"  onclick="selectit(3)"/>上门认证</span>
		</div>

		<div class="even">
			<label class="fname" for="pname"><#if currentRole=1>姓名</#if><#if currentRole=2>法人代表</#if>：</label>
			<span class="fvalue"><input type="text" id="username" name="username" size="15" value="${username}" class="normal txt-login w200"/><font color="red">*</font></span>
		</div>

		<#if currentRole=1>
		<div class="odd">
			<label class="fname" for="pname">性别：</label>
			<span class="fvalue">
			<#if sex==1>
			    <label for="pname"> <input name="sex" type="radio" class="inputxt" value="1" checked/>男</label>
			    <label for="pname"> <input name="sex" type="radio" class="inputxt" value="2"/>女</label>
			<#else>
				<label for="pname">	<input name="sex" type="radio" class="inputxt" value="1"/>男</label>
				<label for="pname">	<input name="sex" type="radio"   class="inputxt" value="2" checked/>女</label>
			</#if>
			</span>
		</div>


		<div class="even">
			<label class="fname" for="pname">生日：</label>
			<span class="fvalue">
				<!--<input type="text" name="birth" size="15" value="${birth?string("yyyy-MM-dd")}" id="dateinput" class="normal txt-login w90"/>-->
				<input type="text" name="birth" size="15" value="${birth?string("yyyy-MM-dd")}" id="dateinput1" onfocus="new WdatePicker(this)"  class="normal txt-login w90"/>
		<font color="red">*</font>
			</span>
		</div>
		</#if>
		<#if currentRole=2>
		<div class="even">
			<label class="fname" for="pname">公司名称：</label>
			<span class="fvalue"><input id="sign" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="sign" size="20" value="${sign}" class="normal txt-login w200"/>

				<font color="red">*</font>
			</span>
		</div>
		</#if>
		<div class="odd">
			<label class="fname" for="pname">地址：</label>
			<span class="fvalue"><input type="text" name="address" size="15" value="${address}" class="normal txt-login w300" /><font color="red">*</font></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">邮编：</label>
			<span class="fvalue"><input type="text" name="postcode" size="15" value="${postcode}" class="normal txt-login w200"/></span>
		</div>
		<div class="odd">
			<label class="fname" for="pname">电话：</label>
			<span class="fvalue"><input id="phone" type="text" name="phone" size="15" value="${phone}" class="normal txt-login w200"/>	<font color="red">*</font></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">身份证号码：</label>
			<span class="fvalue"><input id="cardno" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="cardno" size="15" value="${cardno}" class="normal txt-login w200"/><font color="red">*</font></span>
		</div>
		<#if currentRole=2>
		<div class="even">
			<label class="fname" for="pname">营业执照号：</label>
			<span class="fvalue"><input id="entno" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="entno" size="15" value="${entno}" class="normal txt-login w200"/><font color="red">*</font></span>
		</div>
		</#if>
		<div class="odd">
			<label class="fname" for="pname">QQ：</label>
			<span class="fvalue"><input type="text" name="qq" size="15" value="${qq}" class="normal txt-login w200"/></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">MSN：</label>
			<span class="fvalue"><input type="text" name="msn" size="15" value="${msn}" class="normal txt-login w200"/></span>
		</div>


		<div class="formbtn">
		<label class="fname" for="cname"></label>
		<#if status=3>
				<span class="fvalue">
		<input class="delBtn igreen "   id="saveBtn11" onclick="javascript:applyverify1();$('#userverifyBox').remove();$('.blockUI').remove();" type="button" title="申请认证" value="申请认证"/>
		</span>
		</#if>
			<#if status=1>
				<span class="fvalue">
		<input class="delBtn igreen "   id="saveBtn11" onclick="javascript:applyverify1()$('#userverifyBox').remove();$('.blockUI').remove();" type="button" title="申请认证" value="申请认证"/>
		</span>
		</#if>
		</div>
	</div>
	</div>
	</form>
<script>
var selected=false;
function selectit(value){
	selected=true;
	$("#approve_type").val(value);
	//alert($("#approve_type").val());
}
function applyverify1(){
	if(!selected){
		alert("请先选择认证方式");
		return;
	}
	<#if currentRole=1>
	if($("#username").val()==""){
		alert("请先输入用户名");
	}
	else if(!checkIDCard($("#cardno").val())){
		alert("请先输入正确的身份证号码和生日");
	}
	</#if>
	   <#if currentRole=2>
	   if($("#username").val()==""){
		alert("请先输入企业法人代表名称,不能为空");
	    }
	    else if($("#sign").val()==""){
		alert("请先输入企业名称,不能为空");
	    }
	    else if($("#entno").val()==""){
		alert("请输入企业营业执照号,不能为空");
	    }
	    	  else if($("#cardno").val()==""){
		alert("请输入企业法人身份证号码,不能为空");
	    }

	</#if>
	else if($("#address").val()==""){
		alert("请输入您或您公司的地址,不能为空");
	}
	else if($("#phone").val()==""){
		alert("请输入您的联系电话,不能为空");
	}else{
		$("#form1").ajaxSubmit({
			success:function(data){
			 	$.blockUI({message:data});
			 	/*
			 	$.ajax({
			    type: "POST",
			    data:"",
			    url:"../user/verifyapply.action",
			    success:function(d1){
			    	$.blockUI({message:d1});
		        }})
		        */
		 	 }
		});
    }
}

function checkPhone (str){
	isPhone=/\d{7,15}/;
	return (isPhone.test(str));
}
function checkIDCard (str)
{ //身份证正则表达式(15位)
 isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
 //身份证正则表达式(18位)
 isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
 //验证身份证，返回结果

 <#if currentRole=1>
 var birthday=$("#dateinput1").val();
 if(str.length==15){
 	if(birthday!="19"+str.substring(6,8)+"-"+str.substring(8,10)+"-"+str.substring(10,12))
 		return false;
 }else if(str.length==18){
 	if(birthday!=str.substring(6,10)+"-"+str.substring(10,12)+"-"+str.substring(12,14))
 		return false;
 }else{
 	return false;
 }
 </#if>
 return (isIDCard1.test(str)||isIDCard2.test(str));
}
//function submita(){
//	if($("#username").val()==""){
//		alert("请先输入用户名");
//	}else if(!checkIDCard($("#cardno").val())){
//		alert("请先输入正确的身份证号码和生日");
//	}else if($("#phone").val()==""){
//		alert("请先输入联系电话");
//	}else{
//		var options={success:showResponse};
//		$("#form1").ajaxSubmit(options);
//	}
//}
</script>
</div>
</#escape>
