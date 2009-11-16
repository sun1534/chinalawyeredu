<#escape x as (x)!"">
<#import "../common/home.ftl" as home>
<@home.home myheader="user/user_h.ftl">
<script type="text/javascript" src="${staticpath}/js/location.js"></script>
<div class="parentsinfo-box in-main">
	<div class="title-h">
		<h3>我的资料</h3>
	</div>
	<div class="title-parentsinfo">
		<div class="title-info">
			<div class="m-tag">
				<ul id="partents-mintag">
					<li class="current"><a href="#" title="基本资料"><span>基本资料</span></a></li>
					<li><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>
					<#if currentRole=2>
					<li><a title="更新LOGO" href="userlogo.action"><span>更新LOGO</span></a></li>
					</#if>
					<li><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
					<li><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="parentsminbox">
	<div class="baseadmininfo">
     <form id="form1" method="post" action="userbaseupdate.action">
		<div id="baseadmininfo" class="form" >
		<div class="even">
			<label class="fname" for="pname"><#if currentRole=1>姓名</#if><#if currentRole=2>法人代表</#if>：</label>
			<span class="fvalue"><input type="text" id="username" name="username" size="15" value="${username}" class="normal txt-login w200"/></span>
		</div>
		<div class="odd">
			<label class="fname" for="pname">身份认证：</label>
			<span class="fvalue"><#if status=0><b class="red">通过认证</b></#if><#if status=1><a href="javascript:applyverify()">申请认证</a></#if>
			<#if status=2>等待认证</#if>
			<#if status=3><a href="javascript:applyverify()">申请认证</a></#if></span>
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
				<input type="text" name="birth" size="15" value="${birth?string("yyyy-MM-dd")}" id="dateinput" class="normal txt-login w90"/>
			</span>
		</div>
		</#if>
		<div class="odd">
			<label class="fname" for="pname">地址：</label>
			<span class="fvalue"><input type="text" name="address" size="15" value="${address}" class="normal txt-login w300" /></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">邮编：</label>
			<span class="fvalue"><input type="text" name="postcode" size="15" value="${postcode}" class="normal txt-login w200"/></span>
		</div>
		<div class="odd">
			<label class="fname" for="pname">电话：</label>
			<span class="fvalue"><input id="phone" type="text" name="phone" size="15" value="${phone}" class="normal txt-login w200"/></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">身份证号码：</label>
			<span class="fvalue"><input id="cardno" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="cardno" size="15" value="${cardno}" class="normal txt-login w200"/></span>
		</div>
		<#if currentRole=2>
		<div class="even">
			<label class="fname" for="pname">营业执照号：</label>
			<span class="fvalue"><input id="entno" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="entno" size="15" value="${entno}" class="normal txt-login w200"/></span>
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
		<span class="fvalue">
		<input class="delBtn igreen "   id="saveBtn"  stype="button" title="保存修改" value="保存修改"/>
		</span>
		</div>
	</div>
	</div>
	</form>
	</div>
	</div>
<div class="clear"></div>
<script>
function applyverify(){
	if($("#username").val()==""){
		alert("请先输入用户名");
	}else if(!checkIDCard($("#cardno").val())){
		alert("请先输入正确的身份证号码");
	}else if($("#phone").val()==""){
		alert("请先输入联系电话");
	}else{
		$("#form1").ajaxSubmit();
		$.ajax({
		    type: "POST",
		    data:"",
		    url:"verifyapply.action",
		    success:function(data){
		    	$.blockUI({message:data});
	    }});
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
 return (isIDCard1.test(str)||isIDCard2.test(str));
}
</script>

</@home.home>
</#escape>
