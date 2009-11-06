<#escape x as (x)!"">
<div class="con_" style="display:;">
	<div class="form Mymsg">
	<textarea maxsize="200" id="leavemessage"  class="w480 h80"></textarea>
	<p class="formop"><label><input type="checkbox" checked="checked" name="" />悄悄话</label><input type="button" onclick="leavemessageindex()" value="留言" title="留言" class="delBtn igreen"/>（最多200字）</p>
	</div>
</div>
<#if usermessagelist?size == 0>
<div class="Tips">
	<div class="InTips Tips-gray">
	<h4>暂无留言</h4>
	<div class="TipsCon">
		<p>您还没有朋友给您留言,赶紧试着到他们留言本上去踩踩.这样会增加回访的机率哦!</p>
	</div>
	</div>
</div>
</#if>
<!-- 留言列表 -->
<div class="Mymsg-list" id="Mymsg-list">
	<div class="in-Mymsg-list">
		<ul>
		<@s.iterator value="usermessagelist" status="s">
		<#assign userinfo=userutil.getUserinfo(replyUserid) />
			<li id="ml1">
				<div class="Mymsg-entry">
					<p class="img"><a href="../home/home.action?viewUserid=${replyUserid}"><img width="60" height="60" border="0" src="${resourcepath}${userinfo.logo}" /></a><span class="name"><a href="../home/home.action?viewUserid=${replyUserid}">${userinfo.userName}</a></span></p>
					<div class="Mymsg-info">
						<div class="Mymsg-con"><#if content?length lt 120>
						${content}
						<#else> ${content[0..120]} <a href="../message/leavemessagelist.action?viewUserid=${viewUserid}">...</a>
						</#if></div>
						<p class="Mymsg-time">${createTime?string("yyyy-MM-dd HH:mm:ss")}<a id="m1" class="ico a-delmsg" href="javascript:void(0)" onclick="$.Jxq.delTips('#m1','#m1_',-60,20,220)">删除留言</a></p>
						<!-- 删除留言 -->
						<div style="display:none" class="webmenu" id="m1_">
							<div class="tooltips-del">
								<div class="intooltips-del">
									<span>确认删除该留言吗？</span>
									<input type="button" class="delBtn igreen" onclick="$.Jxq.removeTips('#ml1','#m1_')" title="确定" value="确定"/>
									<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#m1_')" title="取消" value="取消"/>
								</div>
							</div>
						</div>
					</div>
					<span class="repaly"><a class="ico a-repmsgbook" title="回复" href="javascript:void(0)">回复</a></span>
				</div>
			</li>
		</@s.iterator>
		</ul>
	</div>
</div>
<p class="formop"><a href="../message/leavemessagelist.action?viewUserid=${viewUserid}" title="更多">更多</a></p>









<div class="msgcom">
  <dl>
	<a href="javascript:showreplay(${id},${viewUserid})">回复</a>
	<#if currentUserid=viewUserid>
	<a href="javascript:void(0)" onclick="deleteleave(${id},${viewUserid},${currentUserid})">删除</a>
	</#if>
	<span class="jiantou" id="jiantou${id}"><a href="javascript:showreplay(${id},${viewUserid})">展开</a></span></dd>
  </dl>
</div>

<div  class="msgcom" id = comm${id} style="display:none">
</div>
<div class="msgline"></div>




<div class="popbox" id= "pb" style="display:none">
	<span id="tt" ></span>
	<!--<div class="popboxtop">
		<h4>删除</h4>
		<span><a href="javascript:void(0)" onclick="deleteleave('close')" >关闭</a></span>
	</div>
	<div id="pbtext" class="popboxbody">
		<p>确定删除该留言吗？</p>
	</div>
	<div class="popboxfoot">
		<button onclick="deleteleave('home')" class="popdelete">确定</button><button onclick="deleteleave('close')" class="popcancel">取消</button>
	</div>
	-->
	<div class="box-orange">
		<h4>删除</h4>
		<div class="box-content" id="pbtext">
			<p>确定删除该留言吗？</p>
		</div>
		<div class="popboxbtn">
			<button onclick="deleteleave('home')" class="popdelete">确定</button><button onclick="deleteleave('close')" class="popcancel">取消</button>
		</div>
	</div>
</div>


<div class="popbox" id="pb2" style="display:none">
	<!--
	<div id="pb2shareid" class="popboxtop">
		<h4>删除</h4>
		<span><a href="javascript:void(0)" onclick="deletereply1('close','','')" >关闭</a></span>
	</div>
	<div id="pbtext" class="popboxbody">
		<p>确定删除该回复吗？</p>
	</div>
	<div class="popboxfoot">
		<button onclick="deletereply1('delete','','')" class="popdelete">确定</button><button onclick="deletereply1('close','','')"  class="popcancel">取消</button>
	</div>
	-->
	<div class="box-orange" id="pb2shareid">
		<h4>删除</h4>
		<div class="box-content" id="pbtext">
			<p>确定删除该回复吗？</p>
		</div>
		<div class="popboxbtn">
			<button onclick="deletereply1('delete','','')" class="popdelete">确定</button><button onclick="deletereply1('close','','')"  class="popcancel">取消</button>
		</div>
	</div>
</div>

</#escape>