<#import "../common/home.ftl" as home>
<@home.home myheader="user/userpic_h.ftl">
<div class="in-main">
	 <div class="title-h"><h3>我的资料</h3></div>
        <div class="title-info">
			<div class="m-tag">
			<ul>
				<li ><a href="userbaseview.action" title="基本资料"><span>基本资料</span></a></li>
				<li class="current"><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>
				<#if currentRole=2>
				<li><a title="更新LOGO" href="userlogo.action"><span>更新LOGO</span></a></li>
				</#if>
				<li><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
				<li><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
			</ul>
			</div>
		</div>
  <div class="updateimg-info" id="abc">
	  <div class="baseadmininfo">
		  <div class="updateimg-in">
				<#assign user=userutil.getUserinfo(currentUserid) />
		  		<form id="form1" action="userimages.action" name="form1" method="post" action="" enctype="multipart/form-data">
					<div id="baseadmininfo" class="form-left">
						<h4 class="headimg">当前头像</h4>
						<!--<p class="img-tip">你可以到你的 <a href="picturelist.action" title="到相册选择头像" >相册</a> 里选择照片设置为当前头像</p>-->
						<div><img SRC="../${user.pic?default("")}"  align="middle" ></div>
					</div>
					<div id="baseadmininfo-r" class="form-right">
		          		<h4 class="headimg">上传头像</h4>
						<p>点击浏览，可以把您电脑上的照片更换成自己在天威广告业务管理站点的头像</p>
						<div class="fm">&nbsp;<input type="file" style="height:25px;" class="normal" id="fileUpload" name="upload"/> </div>
					    <p><span class="text">请确认上传的是你自己的照片。</p><p class="spe-warning">上传色情、反动等照片将导致你的账号被删除。
					    	<p class="spe-warning">${message}</span></p>
						<p class="no-line"><span><input id="saveBtn" type="button" value="上传"  class="delBtn igreen" /></span></p>
				 	</div>
		        </form>
			</div>
	    </div>
  </div>
  <div style="display:none text-align:center;" class="content-body" id="leavemessagediv">

  </div>

</div>

</@home.home>

