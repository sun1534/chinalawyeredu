<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登陆 - 天威广告业务管理站点 - 中国移动</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<link rel="stylesheet" type="text/css" href="../css/_style/reset.css" />
		<link rel="stylesheet" type="text/css" href="../css/_style/global.css" />
		<link rel="stylesheet" type="text/css" href="../css/_style/mod.css" />
        <link rel="stylesheet" type="text/css" href="../style/css/default.css" />
		<script src="http://58.83.134.61/ln//js/jquery.js" type="text/javascript"></script>
		<script src="http://58.83.134.61/ln//js/jquery.validate.js" type="text/javascript"></script>
		<script src="http://58.83.134.61/ln//js/common/login.js"></script>
		<script src="http://58.83.134.61/ln//js/jquery.blockUI.js" type="text/javascript"></script>
        <script type="text/javascript" src="../style/js/common/login.js"></script>
		<script type="text/javascript">
			function checkIden(node){
	if(node.selectedIndex == 2){
	var targetNode;
	targetNode = document.getElementById("name");
		targetNode.style.display = "block";
		 $("#phoneui").show();
	$("#teltxt").attr("value",'请输入你家长的手机号')
		}else{
		var targetNode;
		targetNode = document.getElementById("name");
		targetNode.style.display = "none";
		 $("#username").attr("value",'');//清空内容
		  $("#teltxt").attr("value",'请输入您的手机号')
		$("#usernameid").show();
	}
}

			$(document).ready(function(){
				checkIden(document.getElementById("select")),
				$("#form1").validate({
				
				   // errorLabelContainer: $("#form1 div.test"),
					rules: {               
						loginname: {
							required: true,
							 number:true,
							minlength: 11
						   
						},
						password: {
							required: true,
							minlength: 6
						},
						verifycode: {
							required: true,
							number:true
						}
					},
					messages: {               
						loginname: {
							required:  "请输入手机号码!",    
							number:    "手机号码须为数字!",  
							minlength: "输入11位手机号码!"  
									   
						},
						password: {
							required:  "请输入密码!",
							minlength: "密码最少为6位!"  
						},
						verifycode: {
							required: "请输入验证码!",
							number:   "验证码须为数字!"  
						   // email: "请输入正确的邮件地址!"
						}
					} 
					,
						errorPlacement: function(error, element) {						   
						    if ( element.is(":password") ){   
                            error.appendTo( element.parent() );   
           					}else if ( element.is("#verifycode") ){
           					error.appendTo(element.parent() );   
                			}else  {
               				 error.appendTo( element.parent() );   
               				}
				   }
				});
			})
		</script>
	</head>
	<body>
        <div id="doc">
            <div class="global-float-window"></div>
            <div class="global-float-dialog"></div>
            <div class="doc">
            	<div class="wrap">
                <div class="hd line">
			  <div class="in-hd">
				<h1>
					<a href="/" class="logo" title="天威广告业务管理站点"><img src="../style/images/logo.gif" width="199" height="59" alt="天威广告业务管理站点" /></a>
					<span class="change_city">
						<strong id="Provinces">辽宁</strong>
						<a class="current" title="切换省份"  href="javascript:void(0)" id="change_area">切换省份</a>
					</span>
				  </h1>
				  <h2><a>
				  <img src="../style/images/logo_cm.gif" width="140" height="55" alt="中国移动通信"  /></a></h2>
                  <div class="clear"></div>
				</div>
			</div>
            <div class="area" id="area" style="display:none">
					<iframe frameborder="0" allowTransparency="true"  class="cover" scrolling="no" marginwidth="0" marginheight="0" src=""></iframe>
					<div  class="inarea">
						<div class="area_h">
						<h3>以下是已开通天威广告业务管理站点的省市</h3><a href="http://jiaoyu.139.com/hc/" title="查看全部天威广告业务管理站点">查看全部</a>
						</div>
						<div class="list">
							<a href="http://jiaoyu.139.com/ln/index.html" tiltle="天威广告业务管理站点（辽宁省）">辽宁</a>
							<a href="http://jiaoyu.139.com/bj/index.html" tiltle="天威广告业务管理站点（北京市）">北京</a>
							<a href="http://jiaoyu.139.com/heb/index.html" tiltle="天威广告业务管理站点（河北省）">河北</a>
							<a href="http://jiaoyu.139.com/tj/index.html" tiltle="天威广告业务管理站点（天津市）">天津</a>
							<a href="http://jiaoyu.139.com/sx/index.html" tiltle="天威广告业务管理站点（山西省）">山西</a>
						</div>
						<div class="area_f"></div>
					</div>
				</div>
				<div class="page-nav sp-t15">
					<div class="wrapper">
						<span class="pn-top-level"><a href="/">天威广告业务管理站点首页</a></span>
						<span class="pn-sub-level"><a href="#">登录</a></span>
						<div class="clear"></div>
					</div>
				</div>
                <div class="page-body layout-a">
					<div class="col-1">
						<div class="page-block">
							<div class="box-orange" style="margin:8px 0 16px -70px">
								<h4>天威广告业务管理站点开学了！</h4>
								<div class="box-content">
									<p>教育化的互动社区欢迎您的注册入学。</p>
								</div>
							</div>
							
							<div style="margin:8px 0 16px -70px;text-align:center"><input type="button" value="马上注册" name="cname" tabindex="6" class="i-button w4" onclick="javascript:location.href='../regist/registOne!input.action?source'" /></div>
							<h4><a href="/common/iteacher.html">老师</a></h4>
							<p><a href="/common/iteacher.html" class="grey">与名师深度对话，与家长和学生交朋友，与同行分享你的教育资源</a></p>
							<h4><a href="/common/iparent.html">家长</a></h4>
							<p><a href="/common/iparent.html" class="grey">全面了解你的孩子，及时交流你的困惑，与大家分享你的教育资源</a></p>
							<h4><a href="/common/istudent.html">学生</a></h4>
							<p><a href="/common/istudent.html" class="grey">学校的围墙消失了，你能找到更多有共同爱好的同学，比一比谁学得更好</a></p>
						</div>
					</div>
					<div class="col-2">
						${message}
						<div class="form sp-b15">
							<form method="post" id="form1" name="form1" action="login.action">
								<fieldset>
									<legend>用户登录</legend>
									<p><label for="cname" class="title">我的身份：<em>*</em></label><span><select name="loginrole" id="select" tabindex="1" onchange="checkIden(this);"><option value="2" ${loginrole==2?"selected":""}>家 长 </option><option value="3" ${loginrole==3?"selected":""}>老 师 </option><option value="1" ${loginrole==1?"selected":""}>学 生</option></select></span></p>
									<p id="name" class="g"><label for="cname" class="title">姓名：<em>*</em></label><span><input name="username" value="${username}" type="text" class="normal w150" tabindex="2" id="username" /><i>您在天威广告业务管理站点的用户名</i></span></p>           
									<p id="usernameid" class="g"><label for="cname" class="title">手机号码：<em>*</em></label><span><input id="teltxt" name="loginname" value="${loginname}" tabindex="3" type="text" class="normal w150"  onclick="if(this.value=='请输入您的手机号'||this.value=='请输入你家长的手机号') this.value=''"/><i>输入手机号</i></span></p>
									<p><label for="cname" class="title">密码：<em>*</em></label><span><input name="password" type="password" class="normal w150" tabindex="4" /></span></p>
									<p class="g"><label for="cname" class="title">验证码：<em>*</em></label><span><input id="verifycode" name="verifycode" type="text" class="normal w150" tabindex="5" size="6" />&nbsp;<img id="imgObj" src="../verify/VerifyCode.jsp" height="20" width="60" align="absmiddle" />&nbsp;&nbsp;<a href="#" onclick="changeImg()">看不清楚,换一张</a></span></p>
									<!--<p><label for="cname" class="title">&nbsp;</label><span></span></p>-->
									<p class="no-line"><label for="cname" class="title">&nbsp;</label><span><input type="submit" value="登录"  class="i-button w2" tabindex="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="/common/findbackpassword.html" target="_blank">忘记密码</a></span></p>
								</fieldset>
							</form>
						</div>
						<p>&nbsp;</p>
						<div class="form sp-b15" style="display:none">
							<fieldset>
								<legend>已收到天威广告业务管理站点激活提醒</legend>
								<p class="no-line"><label for="cname" class="title">&nbsp;</label><span><input type="button" value="马上激活" name="cname" tabindex="6" class="i-button w5" onclick="javascript:location.href='/regist/activeone!input.action'" /></span></p>
							</fieldset>
						</div>
						<p>&nbsp;</p>
					</div>
					<div class="clear"></div>
				</div>
                <div class="page-footer">
					<p><span class="add"><a href="../about/privacy.html">隐私条款</a> | <a href="../about/contact.html">联系方式</a> | <a href="../about/jobs.html">招聘信息</a> | <a href="../about/feedback.html">意见反馈</a></span><span class="copyright">&copy;&nbsp;2009 139.com <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP证080595号</a></span></p>
				</div>
            	</div>
            </div>
            <div class="ext-javascript">
                <!--页尾javascript-->
            </div>
        </div>
	</body>
</html> 