<#import "../common/home.ftl" as home>
<@home.home myheader="user/user_h.ftl">
<div class="psw-mod in-main">
	<div class="title-h"><h3>我的资料</h3></div>
	<div class="title-info">
		<div class="m-tag">
			<ul>
				<li ><a href="userbaseview.action" title="基本资料"><span>基本资料</span></a></li>
				<li><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>
				<#if currentRole=2>
				<li><a title="更新LOGO" href="userlogo.action"><span>更新LOGO</span></a></li>
				</#if>
				<li ><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
				<li class="current"><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
				<li><a title="认证管理" href="userverify.action"><span>认证管理</span></a></li>
			</ul>
		</div>
	</div>

	<div class="psw-info">
		<div class="baseadmininfo">
		    <form id="form1" name="form1" method="post" action="safepwd.action">
			<div id="baseadmininfo" class="form">
				<div class="psw-left">
					<div class="even">
						<label class="fname" for="cname">密码问题：</label>
						<span class="fvalue">
						  <select name="question" style="width:200px;">
							<option value="我最向往的城市？">我最向往的城市？</option>
							<option value="我的所读的大学？">我的所读的大学？</option>
							<option value="我的一个重要的纪念日？">我的一个重要的纪念日？</option>
						  </select>
						</span>
					</div>
					<div class="odd">
						<label class="fname" for="cname">问题答案：</label>
						<span class="fvalue"><input type="text" name="answer" id="answer" value="${answer}" class="regtxt"/></span>
					</div>
					<div class="even">
						<label class="fname" for="cname">邮箱验证：</label>
						<span class="fvalue"><input type="text" name="email" id="email" value=${email}   class="regtxt"/></span>
					</div>
					<div class="formbtn">
						<label class="fname" for="cname"></label>
						<span class="fvalue"><input class="delBtn igreen w80" onclick="updatesafe()"  type="button" title="保存" value="保存"/></span>
					</div>
				</div>
			 </form>
			</div>
			</div>
		</div>
		<div class="clear"/>
	</div>
</div>
</@home.home>
<script>
function updatesafe(){
	var options={success:showResponse};
	if($("#answer").val()==""){
		alert("请输入问题答案");
	}else if(!isEmail($("#email").val())){
		alert("请输入正确的邮箱");
	}else{
		$("#form1").ajaxSubmit(options);
	}
}

function showResponse(data){
	$.blockUI({ message: data });
}


function isEmail(str){
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return reg.test(str);
}
</script>