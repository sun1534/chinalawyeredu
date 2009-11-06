<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="photo/albumlist_h.ftl" >
  <div id="sider">
	<div class="page-nav">
		<div class="wrapper">
			<span class="pn-top-level"><a href="../home/home.action">我的主页</a></span>
			<span class="pn-sub-level"><a href="../photo/albumlist.action">相册</a></span>
			<span class="pn-sub-level"><a href="../photo/albumlist.action">我的相册</a></span>
			<div class="clear"></div>
		</div>
	</div>



      <div class="msg">
      <!--  <div class="msgtop">
          <h3>相册</h3></div> -->
        <div class="msgbody">
        <div class="mainrtop">
            <ul>
              <li class="nonce"><a href="albumlist.action" >我的相册</a></li>
              <li><a href="friendphotolist.action">好友的相册</a></li>
            </ul>
          </div>
        
          <div class="albumbox">
            <ul>
             
              <li>
                <div class="photo-album">
                  <table class="photo-album-table">
                    <tbody>
                      <tr>
                        <td class="cover-image">
                        <p class="image"><a href="photolist.action?albumid=${photos.albumid}&albumName=${photos.albumName}"><img src="${resourcepath}${photos.url}" width="128" height="128" alt="" /></a></p>
                        </td>
                        <td valign="top" width="400">
							
							<div class="form">
								<form method="post" action="">
									<fieldset>
										<p class="g">相册名称：<input type="text" id="albumName" name="albumName" value="${photos.albumName}" maxlength="10"/>  *  限10个汉字</p>
										<p class="no-line"><span><input type="button" value="确定" name="cname" class="i-button w2" onclick="updatephtot($('#albumName').attr('value'),${photos.albumid})"/><input type="button" value="取消" onclick="backlist()" name="cname" class="n-button w2" /></span></p>
									</fieldset>
								</form>
							</div>
                          </td>
                      </tr>
                    </tbody>
                  </table> 
                </div>
              </li>
             
            </ul>
          </div>
        
        </div>
      </div>
    </div>
    
<form name="pageForm" action="albumlist.action">
  	<input type="hidden" name="pageNo" value="" />
</form>
<!--遮罩start-->
<div class="overshadow" style="display:none;">
  <iframe width="100%" height="100%" marginheight="0" frameborder="0" marginwidth="0" scrolling="no"></iframe>
</div>
<!--遮罩end-->
    
  <div class="popbox" id="popbox1" style="height:auto; display:none">
  <div class="popboxtop">
    <h3>添加相册</h3>
    <span><a href="#" onclick="hidepopbox1()">关闭</a></span> </div>
  <div class="popboxbody" style="height:auto;">
  <strong>输入相册名：</strong><input name="" id="albumName" class="groupintxt" type="text" />
  </div>
  <div class="popboxfoot">
    <button type="button" onclick="addalbum($('#albumName').attr('value'))">确定</button>
    <button type="button" onclick="hidepopbox1()">取消</button>
  </div>
</div>

</@home.home>
</#escape>