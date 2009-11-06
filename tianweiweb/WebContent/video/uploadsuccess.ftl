<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/uploadsuccess_h.ftl" >
 <div id="sider">
	<div class="page-nav">
		<div class="wrapper">
			<span class="pn-top-level"><a href="../home/home.action">我的主页</a></span>
			<span class="pn-sub-level"><a href="../photo/albumlist.action">相册</a></span>
			<span class="pn-sub-level"><a href="../photo/albumlist.action">上传照片</a></span>
			<div class="clear"></div>
		</div>
	</div>
      <div class="msg">
        
        <div class="msgbody">
	          <div class="msgnav">
	            <ul>
	              <li class="nonce">上传照片</li>
	            </ul>
	          </div>

			<div class="formq"  style="width:700px;margin:10px">
			<p><span>上传成功！</span></p>
			<p class="no-line"><span><input class="i-button w4" type="button"  onclick="backform.submit()"value="返回相册 " /><input class="i-button w4" type="button"  onclick="ctnform.submit()" value="继续上传 " /></span></p>

			</div>
         </div>
      </div>
</div>

<form name="backform" action="albumlist.action" >
<input type="hidden" name="albumid" value="${albumid}" />
<input type="hidden" name="albumname" value="${albumname}" />
</form>
<form name="ctnform" action="uploadphoto!input.action" >
<input type="hidden" name="albumid" value="${albumid}" />
</form>
 </@home.home>
</#escape>