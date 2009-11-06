<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
	<@home.home myheader="message/receivemessage_h.ftl" >

<div id="sider" >
	<div class="page-nav">
		<div class="wrapper">
			<span class="pn-top-level"><a href="../home/home.action">天威广告业务管理站点首页</a></span>
			<span class="pn-sub-level"><a href="../home/home.action">我的主页</a></span>
			<span class="pn-sub-level"><a href="#">手机短消息</a></span>
			<div class="clear"></div>
		</div>
	</div>
	<div class="msg">
            <div class="msgbody">
           	  <div class="msgnav">
                	<ul>
                      <li><a href="shortmessagelist.action">收件箱</a></li>
                      <li><a href="sendMessageinput.action">发件箱</a></li>
                      <#if currentRole==3 >
                      <li><a href="sendsmsMessageinput.action">手机短信发送</a></li>
                      <li class="nonce"><a href="#">手机短信查看</a></li>


                      </#if>
                </ul>
              </div>
              <@s.iterator value="shortmessagelist" status="s">

              <div class="msgbox">
              	<dl class="msglist">
                	<dt>
                    	<div class="info">
                        	<dl>
                        	<dt>
                            <p><a href="#"><img src="${resourcepath}${userinfo.userpersonal.images}" /></a></p>
                            <p><a href="#">${userinfo.userbase.username}</a></p>
                            </dt>
                            <dd>
                            <p class="time">${sendtime?string("MM月dd日 HH:mm:ss")}</p>
                            </dd>
                            </dl>
                        </div>
                    </dt>
                    <dd class="tit">
                    	<p>${content}</p>
                    </dd>
                    <dd class="func">
                     	<p style="display:none"><a href="#">查看短消息</a></p>
                        <p><a href="backMessageaction!input.action?parentid=${parentid}">回复短消息</a></p>
                   </dd>
                   <dd class="del">
                   <p><a href="#" onclick="showdelbox(${commid})">删除</a></p>
                   </dd>
                </dl>

              </div>

               </@s.iterator>

          </div>
        </div>
    </div>
<div id="delbox" style="display:none" >
<!--遮罩start-->
<div class="overshadow">
  <iframe width="100%" height="100%" marginheight="0" frameborder="0" marginwidth="0"

scrolling="no"></iframe>
</div>
<!--遮罩end-->
<!--弹出窗口start-->
<div class="popbox">
  <div class="popboxtop">
    	<h3>删除</h3>
      <span><a href="#" onclick="hidedelbox()">关闭</a></span>    </div>

    <div class="popboxbody">
    	<p>确定删除该消息吗？</p>
    </div>
    <div class="popboxfoot">
    	<button id="delbut" name="" type="button" onclick="delmsg()">删除</button><button

type="button" onclick="hidedelbox()">取消</button>
    </div>
</div>
<!--弹出窗口end-->
</div>
    </@home.home>
    </#escape>