<#import "../common/home.ftl" as home>
<@home.home myheader="user/picture_h.ftl">
<div class="in-main">
	<div class="title-h"><h3>头像修改</h3></div>
	<div class="title-info">
	<!-- tag -->
		<div class="m-tag">
			<ul id="partents-mintag">
				<li><a href="userbaseview.action" title="基本资料"><span>基本资料</span></a></li>
				<li class="current"><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>
				<li><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
				<li><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
				<li><a title="认证管理" href="userverify.action"><span>认证管理</span></a></li>
			</ul>
		</div>
	</div>

<div class="mainrboxc">
	<!-- 提示信息 start -->
	<div class="Tips">
	<div class="InTips Tips-gray">
		<h4>说明</h4>
		<div class="TipsCon">
			<p>请先选择相册，然后选择照片，点击“确认”就可以成功更改头像。</p>
		</div>
	</div>
	</div>
	<!-- 提示信息 end -->
	  <div class="change_img">
  		<form id="form1" action="userpicture.action" name="form1" method="post">
			<fieldset>
				<p ><label><select name="albumid" id="albumid"  onchange="getpicture(this.value)"  class="w120">
				<option>--相册目录--</option>
				<@s.iterator value="albumlist" status="st">
                  <option value="${albumid}">${albumName}</option>
                </@s.iterator>
				</select></label></p>
   <div id="tablebody">
 <ul class="main_photolist">
	<#if picturelist.size()==0 >
    <div class="demo">
    <p>您的这个相册还没有照片</p>
    </div>
    <#else>
    <#list picturelist as pl >
	<li><IMG name=Myimg SRC="${resourcepath}${pl.smallPic}" BORDER="0" align="middle" width="80" height="80">
	<input type="radio" name="photoid" id="photoid" value="${pl.photoid}" /></li>
    </#list>
    </#if>
   </ul>
	<div class="clear"></div>
	<div class="page"> <#if photoPage.count!=0> <span>共 ${photoPage.count} 页</span> <span>${pageString}</span> </#if> </div>
    <form name="pageForm" action="picturelist.action">
    <input type="hidden" name="pageNo" value="" />
    </form>
    <#if photoPage.count!=0>
    <p><span><input id="" type="button" value="确定"  class="delBtn igreen" onclick="picturecut()"/></span></p>
    </#if>
     </div>             
		 	</fieldset>
        </form>
     </div>
 </div>
<!-- 选修改相片 end -->
</div>
</@home.home> 