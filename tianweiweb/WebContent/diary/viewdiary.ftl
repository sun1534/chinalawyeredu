<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<#assign iclass="" />
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
  function setDelId(msgid){
	$("#delmsgid").attr("rel",msgid);
}
 function deleteReplys(){
	 $.ajax({
			type: "POST",
			url:"../diary/deleteDiaryReply.action",
			data:"id="+$("#delmsgid").attr("rel")+"&diaryid="+$("#diaryid").attr("value"),
			success:function(data){
      }});
	  $.Jxq.removeTips('#mag_item_'+$("#delmsgid").attr("rel"),'#mag_del');
}


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


function deleteDiary(diaryid)
  {
	if(window.confirm('确定删除吗?')){
	$.ajax({
        type: "POST",
        url:"deleteDiary.action",
        data:"diaryid="+diaryid,
        beforeSend:function(){

        },
        success:function(data){
        	//$('.albumbox').empty();
           // $('.albumbox').append(data);
         $.blockUI({ message:data});
     }});
	}
}
       </script>

<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	
	<div class="blogbox">
		<div class="title-h"><h3><a href="../diary/diarylist.action"><#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></a></h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<li class="current"><a href="../diary/diarylist.action"><span>我的所有<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></span></a></li>
				</ul>
				<a href="../diary/addDProiary!input.action" class="creatblog">发布<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></a>
			</div>
		</div>
		<!-- 博客日志 详细 -->
		<div class="blogdes">
			<div class="bloglist">
				<div class="list">
					<h4>${diary.tile}</h4>
					<#if diary.userid=currentUserid>
					<div class="writeinfo"><span class="time">发表于：${diary.createTime} | 分类：${diary.typename} 阅读（<i>${diary.clickCount}</i>）评论（<i>${diary.replyCount}</i>）<a href="../diary/editProDiary!updateProDiary.action?diaryid=${diaryid}">编辑</a>&nbsp;&nbsp;<a href="javascript:deleteDiary(${diaryid})">删除</a></span></div>
					<#else>
					<div class="writeinfo"><span class="time">发表于：${diary.createTime} | 分类：${diary.typename} 阅读（<i>${diary.clickCount}</i>）评论（<i>${diary.replyCount}</i>）</span></div>
					</#if>
					<div class="intro">
						<p>
						 ${diary.content}

					</div>
				</div>
				<!-- 评论
				<div class="replaylist">
					<div class="replaytotle">
						<span>${diary.replyCount}条评论 </span><a href="#quickreply">马上评论</a>
					</div>
					<div class="Mymsg-list" id="Mymsg-list">
						<div class="in-Mymsg-list">
							<ul>
							<@s.iterator value="friendPage.items" status="s">
		                      <#assign dest=userutil.getUserinfo(userid) />
								<li id="mag_item_${id}">
									<div class="Mymsg-entry">
										<p class="img"><a href="../home/home.action?viewUserid=${userid}"><img src="${resourcepath}${dest.pic}" width="61" height="61" alt="" /></a><span class="name"><a href="#">${dest.userName}</a></span></p>
										<div id="blog_mag" class="Mymsg-info">
											<div class="Mymsg-con">${content}</div>
											<p class="Mymsg-time">${createTime}<a id="mag_a_${id}" class="ico a-delmsg" href="javascript:void(0)" onclick="setDelId(${id});$.Jxq.delTips('#mag_a_${id}','#mag_del',-60,10,180)">删除评论</a></p>
										</div>
									</div>
								</li>
								</@s.iterator>

							</ul>
						</div>
					</div>
					<div class="formop">
						<div class="pager">
			                <#if friendPage.count!=0>
				      	         <span class="page-total">共 ${friendPage.count} 页</span>
				                  ${pageString}
			                </#if>
                         </div>
					</div>
					<form name="pageForm" action="../diary/viewDiary.action">
			  			<input type="hidden" id="diaryid" name="diaryid" value="${diary.diaryid}" />
			  			<input type="hidden" name="pageNo" value="this.pageNo" />
					</form>
				</div>
				 <form name="replyForm" action="">
				<div class="replaylistform" >

					<a name="quickreply"></a>
					<h3 id="msgico">评论</h3>
						<div class="form Mymsg">
							<textarea id="commcontent" class="h80" style="width:97%" maxlen="100"></textarea>
						<p class="formop"><input type="button" value="发表" title="发表" class="delBtn igreen" onclick ="addComm(${diary.diaryid},${diary.diarytypeId},${viewUserid})" id="addcommbutton"/>（最多100字）</p>
						</div>

				</div>
				</form>
			 发布评论 end -->
			</div>
		</div>
	</div>
</div>

<!-- 删除留言 -->
<div style="display:none;" id="mag_del">
	<div class="tooltips-del">
		<div class="intooltips-del">
			<span>确认删除该评论吗？</span>
			<input type="button" id="delmsgid" rel="" class="delBtn igreen" onclick="deleteReplys();" title="确定" value="确定"/>
			<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#mag_del')" title="取消" value="取消"/>
		</div>
	</div>
</div>
<!-- 删除留言 end -->
</@home.home>
</#escape>