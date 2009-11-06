<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<!-- <script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script> -->
 <script type="text/javascript">

 function showpopbox1(){
	$('.overshadow').attr("style","display:block");
	$('#popbox1').attr("style","display:block");
}

function hidepopbox1(){
	$('#popbox1').attr("style","display:none");
	$('.overshadow').attr("style","display:none");
	$('#diarytype').attr("value","");
}

function adddiarytype(diarytype){
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

function deleteDiary(diaryid){
	//if(diaryid=="close"){
	//	$("#os").hide();
	//	 $("#pb").hide();
	//	 $("#pb").attr("value","");
	//	 $("#os").attr("value","");
	//
	//	 }else if(diaryid=="delete"){
	//		 window.location.href = "../diary/deleteDiary.action?diaryid="+ $("#pb").attr("value")+"&diarytypeid="+$("#os").attr("value");
	//	}else{
	//	$("#os").show();
	//	$("#os").attr("value",diarytypeid);
	//	$("#pb").attr("value",diaryid);
	//	$("#pb").show();
	//	}


	$.ajax({
        type: "POST",
        url:"deleteDiary.action",
        data:"diaryid="+diaryid,
        beforeSend:function(){
			hidepopbox1();
        },
        success:function(data){
        	//$('.albumbox').empty();
           // $('.albumbox').append(data);
         $.blockUI({ message:data});

     }});
}

 function deleteDiary11(diaryid,diarytypeid)
  {
			 $.ajax({
					type: "POST",
					url:"../diary/deleteDiary.action",
					data:"diaryid=" + diaryid+"&diarytypeid="+diarytypeid,
					success:function(data1){
					$.blockUI({ message:data1});
		      }});
}
       </script>


<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3><a href="../diary/diarylist.action"><#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if>发布</a></h3></div>
		<!-- tag start -->
		<div class="title-info">
			<div class="m-tag">
				<ul>
					<li class="current"><a href="../diary/diarylist.action"><span>我的所有<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></span></a></li>
				</ul>
				<a href="../diary/addDProiary!input.action" class="creatblog">新增<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></a>
			</div>
		</div>
		<!-- tag end -->
		<!-- bloglist start -->
		<div class="bloglist">

			<!-- list start  -->
			<#if diarylist?size == 0>
 			 <div class="list">
			   <div class="box-grey" style="margin:10px auto;">
					<h4>信息提示</h4>
					<div class="box-content">
						<p>暂无<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if>。</p>
					</div>
				</div>
               </div>
              </#if>
			 <@s.iterator value="diarylist" status="s">
			<div class="list">
				<h4>
					<a href="../diary/viewDiary.action?viewUserid=${viewUserid}&diaryid=${diaryid}&diarytypeid=${diarytypeid}" >${tile}</a>
					<span class="time">发表于：${createTime}</span>
					<span><a href="../diary/viewDiary.action?viewUserid=${viewUserid}&diaryid=${diaryid}&diarytypeid=${diarytypeid}" class="read">阅读（<i>${clickCount}</i>）</a><a href="../diary/viewDiary.action?viewUserid=${viewUserid}&diaryid=${diaryid}&diarytypeid=${diarytypeid}" class="readall">查看全文>></a></span>
				</h4>
			</div>
			</@s.iterator>
			<!-- list end  -->

			<!-- 分页 -->
			<div class="blogpager">
			  <div class="pager">
                <#if diaryPage.count!=0>
      	         <span class="page-total">共${diaryPage.count}页</span>${pageString}
                </#if>
				</div>
			</div>
			<form name="pageForm" action="../diary/diarylist.action">
  				<input type="hidden" name="pageNo" value="this.pageNo" />
			</form>
			<!-- 分页 end -->
		</div>
		<!-- bloglist end -->
	</div>
</div>

<!-- 2.0 html end zrj 2009-6-4 -->
<div class="popbox" id = "pb"  style="display:none">

    	<div class="box-orange">
		<h4>删除</h4>
		<div class="box-content" >
			<p>确定是否删除该记录？</p>
		</div>
		<div class="popboxbtn">
			<button onclick="deleteDiary('delete'),${diarytypeid}" class="popdelete">确定</button><button onclick="deleteDiary('close'),${diarytypeid}"  class="popcancel">取消</button>
		</div>
	</div>
</div>

<div class="Tips" style="display:none;" id="delblogtips">
	<div class="InTips  Tips-green">
		<h4>系统提示</h4>
		<div class="TipsCon">
			<p>您访问的博客已删除！</p>
			<div class="TipsConbtn">
				<input type="button" value="关闭" title="关闭" onclick="$.unblockUI();" class="cancerBtn igray"/>
			</div>
		</div>
	</div>
</div>
 </@home.home>
 </#escape>
