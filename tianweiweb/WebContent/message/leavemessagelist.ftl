<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/leavelist_h.ftl" >
  <div class="in-main">
    <div class="title-h"><h3><a href="../message/leavemessagelist.action?viewUserid=${viewUserid}">${name}的留言板</a></h3></div>
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
	   <div class="back_button">
			<input type="button" class="delBtn igreen back" onclick="javascript:history.back(-1)" title="返回" value="返回"/>
		</div>
    <div class="mag_sendbox">
        <div class="mag_wrap">
		  <div class="mag_sendbox_in">
      <form id="form1" name="form1" method="post" action="leavemessage.action">
        <p class="sendto">发送内容：</p>
		<div class="form Mymsg"><textarea maxlen="500" id="levid" name="content" class="w450" rows="7"></textarea></div>
		<input type=hidden id=viewUserid  name="viewUserid" value="${viewUserid}">
		<p class="formop"><input class="delBtn igreen" type="button" onclick = "addmessage()" value="发表留言 " /></p>
      </form>
	  	</div>
	  </div>
    </div>
    <#if page.items?size == 0>
	<div class="Tips">
		<div class="InTips Tips-gray">
		<h4>暂无留言</h4>
		<div class="TipsCon">
			<p>您还没有朋友给您留言,赶紧试着到他们留言本上去踩踩.这样会增加回访的机率哦!</p>
		</div>
		</div>
	</div>
    </#if>
    <#list page.items as f>
    <div class="message_content" id="mag_item_${f.id}">
      <#assign userinfo=userutil.getUserinfo(f.replyUserid) />
      <dl>
        <dt><a href="../home/home.action?viewUserid=${f.replyUserid}"><img src="${resourcepath}${userinfo.logo}" /></a></dt>
		<dd>
			<div class="mag_info_box">
				<div class="mag_info">${f.content}</div>
				<div class="mag_dels"><span id="jiantou${f.id}"><a class="ico a-repmsgbook" href="javascript:showreplay(${f.id},${viewUserid})">回复</a></span>
		</div>
			</div>
			<div class="info">来自：<a href="../home/home.action?viewUserid=${f.replyUserid}">${userinfo.userName}</a> </span><span class="time">${f.createTime?string("yyyy-MM-dd HH:mm:ss")}<#if currentUserid=viewUserid> <a id="mag_a_${f.id}" href="javascript:void(0)" class="ico a-delmsg" onclick="setDelId(${f.id});$.Jxq.delTips('#mag_a_${f.id}','#mag_del',-60,-160,180)">删除</a> </#if></span></div>
		</dd>
      </dl>
	</div>
    <div  class="message_content" id = comm${f.id} style="display:none"></div>
    </#list>
	<div class="formop">
    <div class="pager"> <span>共 ${page.count} 页</span> <span>${pageString}</span> </div>
	</div>

  </div>

  <!--弹出窗口start-->
<div style="display:none;" id="mag_del">
			<div class="tooltips-del_">
				<div class="intooltips-del_">
					<span>确认删除信息吗？</span>
						<input type="button" id="delmsgid" class="delBtn igreen" rel="" onclick="dellvmsg();" title="确定" value="确定"/>
						<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#mag_del')" title="取消" value="取消"/>
					</div>
				</div>
			</div>
  <!--弹出窗口end-->
  <form name="pageForm" action="leavemessagelist.action">
    <input type=hidden id=viewUserid  name="viewUserid" value="${viewUserid}">
    <input type=hidden id=userid  name="userid" value="${currentUserid}">
    <input type="hidden" name="pageNo" value=""/>
  </form>
  <script>
var msg1;
var msg2;
var msg3;
$.ajax({
	type:"post",
	url:"../home/messageCountShow.action",
	success:function(data){
	   msgcnt=data.split(",");
	   msg1=msgcnt[0];
	   msg2=msgcnt[1];
	   msg3=msgcnt[2];

	   $("#topsysmsg1").html(packmsg(msg1)+"条");
	   $("#topshortmsg1").html(packmsg(msg2)+"条");
	   $("#topreply1").html(packmsg(msg3)+"条");
	}
});
</script>
</@home.home>
</#escape>