<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/sendmessage_h.ftl" >
<div class="in-main">
	<div class="title-h"><h3><a href="../message/messagecenter.action">消息</a></h3></div>
	<div class="msgcollect">
			<ul>
            <li>站内信：<span><a href="../message/shortmessagelist.action?type=1" id="topshortmsg1">0条</a></span></li>
            <li class="xitong">系统消息：<span><a href="../message/systemmessagelist.action" id="topsysmsg1">0条</a></span></li>
            <li class="liuyan">留言板：<span><a href="../message/leavemessagelist.action?status=1" id="topreply1">0条</a></span></li>
            <#if currentRole=3 >
            <li class="jiaxiaohudong"><span><a href="../message/sendsmsMessageinput.action">家校互动</a></span></li>
            </#if>
          </ul>
       </div>
	<div class="message_top">
           <a href="sendMessage!input.action?sendto=3" class="s1">与老师交流</a>
           <a href="sendMessage!input.action?sendto=2" class="s2">与家长交流</a>
           <a href="sendMessage!input.action?sendto=1" class="s3">与学生交流</a>
           <a href="sendMessage!input.action?sendto=4" class="s4">与好友交流</a>
    </div>
	<div class="title-info">
			<div class="m-tag">
				<ul>
					<li><a href="shortmessagelist.action?type=1" ><span>收件箱</span></a></li>
					<li><a href="shortmessagelist.action?type=2" ><span>已发消息</span></a></li>
                    <li class="current"><a href="sendMessage!input.action?sendto=1" ><span>写站内信</span></a></li>
				</ul>
			</div>
      </div>

    	<div class="mag_sendbox">
            <div class="mag_wrap">
			<div class="mag_sendbox_in" >
              <form id="msgform" action="../message/backMessageaction.action" method="post">
			<#assign dest=userutil.getUserinfo(desuserid) />
			<input type="hidden" name="desuserid" value="${desuserid}" />
			<input type="hidden" name="msgid" value="${msgid}" />
			<p class="sendto"><strong>发 送 给：</strong>
			    <span>
			       ${dest.userName}
			    </span>
			</p>
			<div class="form Mymsg"><textarea name="content" maxlen="500"  class="inputbox"></textarea></div>
			<p style="display:none"><input name="parentid" type="text" value="${parentid}"/></p>
			<p class="formop"><input class="delBtn igreen" onclick="submit()" type="button" value="发送 " /></p>
              </form>
          </div>
		  </div>
        </div>
    </div>
    </@home.home>
</#escape>