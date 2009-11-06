<#escape x as (x)!"">

<div class="page-nav">
	<div class="wrapper">
		<span class="pn-top-level"><a href="../home/home.action">天威广告业务管理站点首页</a></span>
		<span class="pn-sub-level"><a href="../home/home.action">我的主页</a></span>
		<span class="pn-sub-level"><a href="#">好友消息</a></span>
		<div class="clear"></div>
	</div>
</div>
 <div class="msg">
        	<div class="msgtop">
            	<div class="pagecontent">&nbsp;</div>

            </div>
            <div class="msgbody">
           	  <div class="msgnav">
                	<ul>
                      <li class="nonce"><a href="shortmessagelist.action">收件箱</a></li>
                      <li><a href="sendMessageinput.action">发件箱</a></li>
                </ul>
              </div>
              <#list  smessagelist as smg >

              <div class="msgbox">
              	<dl class="msglist">
                	<dt>
                    	<div class="info">
                        	<dl>
                        	<dt>
                            <p><a href="#"><img src="${resourcepath}${smg.userinfo.userpersonal.images}" /></a></p>
                            <p><a href="#">${smg.userinfo.userbase.username}</a></p>
                            </dt>
                            <dd>
                            <p class="time">${smg.sendtime?string("MM月dd日 HH:mm:ss")}</p>
                            </dd>
                            </dl>
                        </div>
                    </dt>
                    <dd class="tit">
                    	<p>${smg.content}</p>
                    </dd>
                    <dd class="func">
                     	<p style="display:none"><a href="#">查看好友消息</a></p>
                        <p><a href="backMessageaction!input.action?parentid=${parentid}">回复好友消息</a></p>
                   </dd>
                   <dd class="del">
                   <p><a href="#" onclick="showdelbox(${smg.commid})">删除</a></p>
                   </dd>
                </dl>

              </div>

               </#list>
              <div class="page"><span>1 | <a href="#">2</a> | <a href="#">3</a> | <a href="#">4</a> | <a href="#">5</a> &nbsp;&nbsp;<a href="#" >  下一页 &raquo;</a></span></div>
          </div>
        </div>

 </#escape>