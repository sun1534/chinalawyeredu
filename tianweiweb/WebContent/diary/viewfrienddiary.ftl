<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" > <#assign iclass="" />
  <#if currentRole=1>
  <#assign iclass="tab-green" />
  </#if>
  <#if currentRole=2>
  <#assign iclass="tab-blue" />
  </#if>
  <#if currentRole=3>
  <#assign iclass="tab-orange" />
  </#if>
<script type="text/javascript">


 function addComm(diaryid,diarytypeid,viewUserid){
	if($('#commcontent').attr("value")=="")
	{
		alert("请输入评论内容");
		$('#commcontent').focus();
		return;
	}
	var diaryid1 = diaryid;

		//alert($('#commcontent').attr("value"));
	$.ajax({

		type: "POST",
		url:"../diary/addDiaryReply.action",

        data:"content="+$('#commcontent').attr("value")+"&diaryid=" + diaryid1+"&diarytypeid="+diarytypeid+"&viewUserid="+viewUserid,
		beforeSend:function(){
		//提交过程到服务器端返回数据需要的页面效果　在这里写
		},
       success:function(data){
       		$('#Mymsg-list').empty();
	        $('#Mymsg-list').append(data);
	        $('#commcontent').attr("value","");
       }
	});
}

 function deleteDiary(diaryid,diarytypeid)
  {

			 $.ajax({
					type: "POST",
					url:"../diary/deleteDiary.action",
					data:"diaryid=" + diaryid+"&diarytypeid="+diarytypeid,
					beforeSend:function(){
					//提交过程到服务器端返回数据需要的页面效果　在这里写
					},
					success:function(data1){
					$.blockUI({ message:data1});
		      }});
}
</script>


<!-- 2.0html zrj 2009-6-4 -->
<#assign dest=userutil.getUserinfo(diary.userid) />
                       <div class="in-main">
	<!-- 访问他人主页 开始-->
	<#if viewUserid!=currentUserid>
	<div class="s-sub">
		<div class="in-s-sub">
			<ul>
				<li><a href="../home/home.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}" class="current"><span>主页</span></a></li>
				<#if p_blogphoto>
				<li><a title="博客" class="normal" href="../diary/proFriendDiaryList.action?viewUserid=${viewUserid}&friendID=${viewUserid}"><span>博客</span></a></li>
				<li><a title="相册" class="normal" href="../photo/viewalbumlist.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}"><span>相册</span></a></li>
				</#if>
				<li><a href="../friend/friendList.action?viewUserid=${viewUserid}"><span>好友</span></a></li>
				<li><a href="../classcenter/coreClassHome.action?viewUserid=${viewUserid}&viewUserrole=${viewUserrole}"><span>班级</span></a></li>
				<li><a title="投票" class="normal" href="../vote/myVoteView.action?viewUserid=${viewUserid}&userid=${viewUserid}"><span>投票</span></a></li>
				<li><a title="礼物" class="normal" href="../gift/receivePresent.action?viewUserid=${viewUserid}&userid=${viewUserid}"><span>礼物</span></a></li>
			</ul>
		</div>
	</div>
	</#if>
<!-- 访问他人主页 结束-->
							<div class="blogbox">
								<div class="title-h"><h3><a href="../diary/diarylist.action">博客</a></h3></div>
								<div class="title-info">
									<!-- tag -->
									<div class="m-tag">
										<ul>
											<!--<li><a href="../diary/diarylist.action"><span>我的博客</span></a></li>-->
											<li class="current"><a ><span>${username}的博客</span></a></li>
										</ul>
										<a href="../diary/addDProiary!input.action" class="creatblog" title="写新博客">写新博客</a>

									</div>
								</div>
								<!-- 博客日志 详细 -->
								<div class="blogdes">
									<div class="bloglist">
										<div class="list">
											<h4>${diary.tile}</h4>
											<div class="writeinfo"><a href="../home/home.action?viewUserid=${diary.userid}" class="author">${dest.userName}</a>来自   ${diary.classname}<a onclick="$.Jxq.ShowDialog('#sendmsg',400,-200,-90);showmsg(${diary.userid},'${dest.userName}','${dest.logo}')" href="javascript:void(0);" class="msg ico a-mail" title="消息"></a><span class="time">发表于：${diary.createTime} | 分类：${diary.typename}</span></div>
											<div class="intro">
												<p>
												${diary.content}
												</p>
											</div>
										</div>
										<!-- 评论 -->
										<div class="replaylist">
											<div class="replaytotle">
												<span>${diary.replyCount}条评论 </span><a href="#quickreply">马上评论</a>
											</div>
											<!-- 留言列表 -->
											<div class="Mymsg-list" id="Mymsg-list">
											<div class="in-Mymsg-list">
										    <ul>
											<@s.iterator value="friendPage.items" status="s">
						                      <#assign dest=userutil.getUserinfo(userid) />
												<li id="ml1${id}">
													<div class="Mymsg-entry">
														<p class="img"><a href="../home/home.action?viewUserid=${userid}"><img src="${resourcepath}${dest.logo}" width="61" height="61" alt="" /></a><span class="name"><a href="#">${dest.userName}</a></span></p>
														<div class="Mymsg-info">
															<div class="Mymsg-con">${content}</div>
															<#if diary.userid=currentUserid>
															<p class="Mymsg-time">${createTime}<a id="m1${id}" class="ico a-delmsg" href="javascript:void(0)" onclick="delmsg(${id},${diaryid});">删除评论</a></p>
															</#if>
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
														<!--<span class="repaly"><a class="ico a-repmsgbook" title="回复" href="#">回复</a></span>-->
													</div>
												</li>
												</@s.iterator>
											</ul>
												</div>
											</div>
											<!-- 分页 -->
											<div class="formop">
													<div class="pager">
										                <#if friendPage.count!=0>
											      	         <span class="page-total">共 ${friendPage.count} 页</span>
											                  ${pageString}
										                </#if>
							                         </div>
												</div>
												<form name="pageForm" action="../diary/viewDiary.action">
												     <input type="hidden" name="diaryid" value="${diary.diaryid}" />
										  			<input type="hidden" name="pageNo" value="this.pageNo" />
												</form>
											<!-- 分页 -->
										</div>
										<!-- 发布评论 -->
										 <form name="replyForm" action="">
										<div class="replaylistform" >

											<!-- 留言板 -->
											<a name="quickreply"></a>
											<h3 id="msgico">评论</h3>
												<div class="form Mymsg">
													<textarea id="commcontent" class="h80" style="width:97%" maxlen="100"></textarea>
												<p class="formop"><input type="button" value="发表" title="发表" class="delBtn igreen" onclick ="addComm(${diary.diaryid},${diary.diarytypeId},${viewUserid})" id="addcommbutton"/>（最多100字）</p>
												</div>

										</div>
										</form>
										<!-- 发布评论 end -->

									</div>
								</div>
							</div>
						</div>





<!-- 2.0 htm 弹出窗口start zrj 2009-6-4 -->
<div class="popbox" id="sendmsg" style="display:none">
<form name="replyForm" id="replyForm"  method="post" action="../message/sendMessage.action">
	<div class="pophead">
		<div class="in-pophead">
			<h4><b>给<span id="setusername"></span>发消息</b></h4>

			<span class="popCLose"><a title="关闭" class="closeA bgreen" href="javascript:void(0);$.Jxq.HideDialog('#sendmsg')">×</a></span>
		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
			<div class="addimg">
				<dl>
                     <input type="hidden" name="desuserid" id="desuserid"/>

					<dt id="userimg"><a href="#"><img id="msg_img" height="61" width="61" alt="" src=""/></a></dt>
					<dd class="form"><textarea class="w280 h60" id="content" name="content" maxlen="200">附加信息(选填，45字内)</textarea></dd>
				</dl>
			</div>
			<div class="clear"></div>
		</div>
	</div>

	<div class="popfoot">
		<div class="in-popfoot">
			<div  class="addfriendBtn clearfix">
				<button title="确定" type="button" class="delBtn igreen"  onclick="sendmsg();$.Jxq.HideDialog('#sendmsg');">确定</button>
				<button type="button" title="取消" class="cancerBtn igray" onclick="$.Jxq.HideDialog('#sendmsg')">取消</button><span></span>

			</div>
		</div>
	</div>
	</form>
</div>
<!-- 2.0 htm 弹出窗口end zrj 2009-6-4  -->



<!-- 2.0html end zrj 2009-6-4 -->

</@home.home>
</#escape>