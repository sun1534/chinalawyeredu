<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/sendmessage_h.ftl" >
<div class="in-main">
	<div class="title-h"><h3><a href="../message/messagecenter.action">消息</a> > 家校互动</h3></div>
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
	<div class="mag_sendbox">
	<input type="button" onclick="javascript:history.back(-1)" value="返回上页" name="cname" class="delBtn igreen" />
        <div class="mag_wrap">
			<iframe height="300" style="margin:20px auto;" frameborder="0" width="700" scrolling="no" marginwidth="0" marginheight="0" src="http://www.ydxxt.com/web/139/139send.aspx?phone=${mobile}"></iframe>
      </div>
    </div>

</div>
</@home.home>
</#escape>