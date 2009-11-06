<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<script type="text/javascript">

 function showpopbox1(){
	$('.overshadow').attr("style","display:block");
	$('#popbox1').attr("style","display:block");
}
function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}
function hidepopbox1(){
	$('#popbox1').attr("style","display:none");
	$('.overshadow').attr("style","display:none");
	$('#diarytype').attr("value","");
}
function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}
function adddiarytype(diarytype){
	//alert(diarytype);

	$.ajax({
        type: "POST",
        url:"adddiarytype.action",
        data:"diarytype="+diarytype,
        beforeSend:function(){
			hidepopbox1();
        },
        success:function(data){
        	//$('.albumbox').empty();
           // $('.albumbox').append(data);
         $.blockUI({ message:data});

     }});
}

function deleteDiary(diaryid,diarytypeid){
	if(diaryid=="close"){
		$("#os").hide();
		 $("#pb").hide();
		 $("#pb").attr("value","");
		 $("#os").attr("value","");

		 }else if(diaryid=="delete"){
			 window.location.href = "../diary/deleteDiary.action?diaryid="+ $("#pb").attr("value")+"&diarytypeid="+$("#os").attr("value");
		}else{
		$("#os").show();
		$("#os").attr("value",diarytypeid);
		$("#pb").attr("value",diaryid);
		$("#pb").show();
		}
}

 function deleteDiary11(diaryid,diarytypeid)
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



<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3><a href="../diary/diarylist.action">博客</a></h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<li><a href="../diary/diarylist.action"><span>我的博客</span></a></li>
					<li class="current"><a href="../diary/diarylist.action?type=friend"><span>好友和同学们的博客</span></a></li>
				</ul>
				<a href="../diary/addDProiary!input.action" class="creatblog" title="写新博客">写新博客</a>
			</div>
		</div>
		<!-- bloglist start -->
		<div class="bloglist" id="blog-id">
			<!-- list start  -->
			<#if diarylist?size == 0>
 			 <div class="list">
			   <div class="box-grey" style="margin:10px auto;">
					<h4>信息提示</h4>
					<div class="box-content">
						<p>您还没有好友或同学写博客</p>
					</div>
				</div>
               </div>
              </#if>
			 <@s.iterator value="diarylist" status="s">
			 <#assign dest=userutil.getUserinfo(userid) />
			<div class="list">
				<h4><a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytypeId}&type=friend" >${tile}</a></h4>
				<div class="writeinfo"><a href="../home/home.action?viewUserid=${userid}" class="author">${dest.userName}</a>来自   ${classname} <a onclick="$.Jxq.ShowDialog('#sendmsg',400,-200,-90);showmsg(${userid},'${dest.userName}','${dest.logo}')" href="javascript:void(0);" class="msg ico a-mail" title="消息"></a><span class="time">发表于：${createTime} | 分类：${typename}</span></div>
				<div class="intro">
					<p>
						${content}
					</p>
				</div>
				<div class="blog-op"><a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytype.diarytypeid}&type=friend" class="read">阅读（<i>${clickCount}</i>）</a><a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytype.diarytypeid}&type=friend" class="comments">评论（<i>${replyCount}</i>）</a> <a href="../diary/viewDiary.action?viewUserid=${userid}&diaryid=${diaryid}&diarytypeid=${diarytype.diarytypeid}&type=friend" class="readall">查看全文>></a></div>
			</div>
			 </@s.iterator>
			<!-- list end  -->

			<!-- 分页 -->

			<!-- 分页 end -->
		</div>
		<!-- bloglist end -->
	</div>
</div>
<!-- 2.0 html end zrj 2009-6-4 -->

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
				<button title="确定" type="button" class="delBtn igreen"  onclick="sendmsg();$.Jxq.HideDialog('#sendmsg')">确定</button>
				<button title="取消" type="button" class="cancerBtn igray" onclick="$.Jxq.HideDialog('#sendmsg')">取消</button><span></span>

			</div>
		</div>
	</div>
	</form>
</div>
<!-- 2.0 htm 弹出窗口end zrj 2009-6-4  -->

 </@home.home>
 </#escape>
