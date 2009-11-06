<div class="Mymsg-list" id="Mymsg-list">
	<div class="in-Mymsg-list">
		<ul>
		<@s.iterator value="friendPage.items" status="s">
          <#assign dest=userutil.getUserinfo(userid) />
			<li id="ml1${id}">
				<div class="Mymsg-entry">
					<p class="img"><a href="../home/home.action?viewUserid=${userid}"><img src="${resourcepath}${dest.pic}" width="61" height="61" alt="" /></a><span class="name"><a href="#">${dest.userName}</a></span></p>
					<div class="Mymsg-info">
						<div class="Mymsg-con">${content}</div>
					    <#if userid=currentUserid>
						<p class="Mymsg-time">${createTime}<a id="m1${id}" class="ico a-delmsg" href="javascript:void(0)" onclick="delmsg(${id},${diaryid});">删除留言</a></p>
						</#if>
					</div>
					<!--<span class="repaly"><a class="ico a-repmsgbook" title="回复" href="#">回复</a></span>-->
				</div>
			</li>
			</@s.iterator>
		</ul>
	</div>
	<!-- 删除留言 -->
	<div style="display:none" class="webmenu" id="delfrereplay">
		<div class="tooltips-del">
			<div class="intooltips-del">
				<span>确认删除该评论吗？</span>
				<input type="hidden" id="msgid" value=""/>
				<input type="hidden" id="diayid" value=""/>
				<input type="button"  class="delBtn igreen" onclick="deleteReply();" title="确定" value="确定"/>
				<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#delfrereplay')" title="取消" value="取消"/>
			</div>
		</div>
	</div>
	<!-- 删除留言 end -->
</div>