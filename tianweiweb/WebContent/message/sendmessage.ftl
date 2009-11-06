<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/sendmessage_h.ftl" >
<input type="hidden" id="sendto" value="${sendto}" />
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
		  <div class="mag_sendbox_in">
			<form name="form1" id="form1" method="post" action="sendMessage.action" >
			<input type="hidden" name="sendto" value="${sendto}" />
			<p class="sendto"><strong>发送给：</strong><label><input type="radio" name="ss" onclick="selectDest(3)" <#if sendto=3>checked="checked"</#if> />&nbsp;老师 </label>
					   <label><input type="radio" name="ss" onclick="selectDest(2)" id="" <#if sendto=2>checked="checked"</#if>  />&nbsp;家长</label>
					   <label><input type="radio" name="ss" onclick="selectDest(1)" id="" <#if sendto=1>checked="checked"</#if>  />&nbsp;同学</label>
					   <label><#if hasfriend!=0><input type="radio" name="ss" onclick="selectDest(4)" id="" <#if sendto=4>checked="checked"</#if> />好友</label></#if>
			
			<p id="classp" class="sendselect" <#if sendto=4>style="display:none"</#if>>选择班级：<select id="classlist" onchange="changeclass()">
			  <#list classeslist as cla >
			  <option value="${cla.id}">${cla.className}</option>
			  </#list>
			 </select></p>
			
			 <div id="afriend" class="tokenbox"></div>
			 <!--<input  style="margin-top:10px;" type="hidden" id="tokeninput" class="tokeninput"/>-->
		     <p class="sendto">发送内容：</p>
		     <div class="form Mymsg"><textarea maxlen="500" id="content" name="content" cols="" rows="" class="inputbox"></textarea>
		     </div>
			 <p class="formop"><input class="delBtn igreen" type="button"onclick="sendmsg()" value="发送消息 " /></p>
			</form>
		  </div>
      </div>
    </div>
</div>
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