<#import "../common/home.ftl" as home>
<@home.home myheader="user/user_h.ftl">
<div class="psw-mod in-main">
	<div class="title-h"><h3>我的资料</h3></div>
	<div class="title-info">
		<div class="m-tag">
			<ul>
				<li ><a href="userbaseview.action" title="基本资料"><span>基本资料</span></a></li>
						<#if currentRole=1>
					<li><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>	
						</#if>
					<#if currentRole=2>
					<li><a title="企业形象" href="userimages!input.action"><span>企业形象</span></a></li>	
					<li><a title="更新LOGO" href="userlogo.action"><span>更新LOGO</span></a></li>
					</#if>
				<li class="current"><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
				<li><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
				<li><a title="认证管理" href="userverify.action"><span>认证管理</span></a></li>
			</ul>
		</div>
	</div>

	<div class="psw-info">
		<div class="baseadmininfo">
		    <form id="form1" name="form1" method="post" action="userpasswordupdate.action">
			<div id="baseadmininfo" class="form">
				<div class="psw-left">
					<div class="even">
						<label class="fname" for="cname">旧密码：</label>
						<span class="fvalue"><input type="password" name="oldPwd" value="" required="true" class="normal txt-login w110"/></span>
					</div>
					<div class="odd">
						<label class="fname" for="cname">新密码：</label>
						<span class="fvalue"><input id="password" type="password" name="newPwd" required="true" class="normal txt-login w110" onblur="checkpwd()" /></span>
						<span id="pwdtip" class="fvalue"><font color="green">密码请输入6~13个字符</font></span>
					</div>
					<div class="even">
						<label class="fname" for="cname">重复新密码：</label>
						<span class="fvalue"><input id="repassword" type="password" name="confirmPwd" required="true" class="normal txt-login w110" onblur="checkrepwd()" /></span>
						<span id="repwdtip" class="fvalue"><font color="green">密码请输入6~13个字符</font></span>
					</div>
					<div class="formbtn">
						<label class="fname" for="cname"></label>
						<span class="fvalue"><input class="delBtn igreen w80" id="saveBtn"  type="button" title="保存密码" value="保存密码"/></span>
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
function checkpwd(){
	if($("#password").val().length<6){
		$('#pwdtip').html("<font color='red'>输入有误，密码请输入6~13个字符。</font>");
	}else{
		$('#pwdtip').html("<font color='#CBCBCB'>ok</font>");
	}
}
function checkrepwd(){
	if($("#repassword").val()==""){
	}else if($("#password").val()!=$("#repassword").val()){
		$('#repwdtip').html("<font color='red'>2次密码输入不同。</font>");
	}else{
		$('#repwdtip').html("<font color='#CBCBCB'>ok</font>");
	}
}
</script>