<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<script type="text/javascript">
	function addDiary(){
    if($.trim($("#tile").attr("value"))=='')
		{
			alert("标题不能为空");
			return false;
		}
	 var content2 =  $("#proDiarycontent").val();
	  if(content2=='')
	  {
	    alert("内容不能为空");
	    return false;
	   }
    document.form1.submit();
	}
    function showdiary()
    {
     $('.overshadow').attr("style","display:block");
	 $('#AddblogCat').attr("style","display:block");
    }

    function addgroup(groupname){
	if(groupname==""){
		alert("类型不能为空!");
		return false;
	}
	$.ajax({
        type: "POST",
        url:"addProDiaryType.action",
        data:"diaryType="+groupname,
        beforeSend:function(){

        },
        success:function(date){
             $('#AddblogCat').attr("style","display:none");
	         $('.overshadow').attr("style","display:none");
	         $('#diarytype').attr("value","");
        	 $.blockUI({ message:date});

     }});
}
function hidepopbox1(){
	$('#addblogcat').attr("style","display:none");
	$('popbox').attr("style","display:none");
	$('#diarytype').attr("value","");
}
function checktotv(){
	if($('#channelcheckbox').attr("checked")){
		$('#channeldiv').show();
		$('#cardnodiv').show();
	}else{
		$('#channeldiv').hide();
		$('#cardnodiv').hide();
	}
}
$(document).ready(function(){
checktotv();
});
</script>
<script type="text/javascript" src="../xheditor/jquery/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../xheditor/xheditor.js"></script>
<script type="text/javascript">
$(pageInit);
function pageInit()
{
	$('#proDiarycontent').xheditor(true,{skin:'jxq_greed',tools:'GStart,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,Removeformat,GEnd,Separator,GStart,Align,List,Outdent,Indent,Link,Unlink,Img,Flash,Media,Emot,Table,GEnd,Separator,GStart,Source,Preview,Fullscreen,About,GEnd',uploadUrl:"../diary/uploadpic.action",uploadExt:"jpg,jpeg,gif,png"});
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
				<a href="../diary/addDProiary!input.action" class="creatblog" title="写新博客">发布<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></a>
			</div>
		</div>
		<!-- 写blog writeblog start-->
		<div class="writeblog">
			<h4>发布<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></h4>
			<!-- blog_line start -->
			<form id="form1" name="form1" method="post" action="addDProiary.action">
			<div class="blog_line">
				<div class="in-writeblog form" id="baseform">
						<p class="note">带<em>*</em>的为必填项</p>
						<div class="even">
							<label class="fname" for="cname"><em>*</em>标题：</label>
							<span class="fvalue">
								<input type="text" class="normal w350" maxlength="80"  id="tile" name="proDiary.tile"/>
							</span>
						</div>
						<div class="odd">
							<label class="fname" for="cname"><em>*</em>分类：</label>
							<span class="fvalue">
								<@s.select name="proDiary.diarytypeId" list="diaryTypeList" />
								  
								<a class="ico a-add" onclick="ok()" href="javascript:void(0)">增加新分类</a>
								<script>
								function ok(){
									$.blockUI({message:$("#addblogcat")});
								}
								</script>
								<!--<a class="ico a-add" onclick="$.Jxq.ShowDialog('#addblogcat',400,-120,-100)" href="javascript:void(0)" title="增加新分类" >增加新分类</a>-->
								<!--<a href="manageDiaryType.action" title="管理分类" >管理分类</a>-->
							</span>
						</div>
						<div class="even">
							<label class="fname" for="cname"><em>*</em>内容：</label>
							<span class="fvalue">
							<textarea name="proDiary.content" id="proDiarycontent" rows="35" style="width:450px;"></textarea>
							</span><input type=hidden id="summary"  name="summary" value="${diary.summary}"/></p>
						</div>
						<div class="odd">
							<label class="fname" for="cname">申请发布：</label>
							<span class="fvalue">
								<input id="channelcheckbox" type="checkbox" name="totv" value="true" onclick="checktotv()" />
							</span>
						</div>
						<div class="even" id="channeldiv">
							<label class="fname" for="cname">栏目：</label>
							<#list channels as c>
							<span class="fvalue">
								<input  type="radio" name="channel" value="${c.id}" />${c.name}
							</span>
							</#list>
						</div>
						<#if needverify>
						<div class="even" id="cardnodiv">
							<label class="fname" for="cname">身份证：</label>
							<span class="fvalue">
								<input  type="text" name="cardno" value="${cardno}" />
							</span>
						</div>
						<#if currentRole=2>
						<div class="even" id="entdiv">
							<label class="fname" for="cname">工商执证号：</label>
							<span class="fvalue">
								<input  type="text" name="entno" value="${entno}" />
							</span>
						</div>
						</#if>
						</#if>
						<div class="formbtn">
							<label class="fname" for="cname"> </label>
							<span class="fvalue">
								<input type="button" class="delBtn igreen" title="发表" value="发表" onclick="javascript:addDiary();"/>
							</span>
						</div>

				</div>
			</div>
			</form>
			<!-- blog_line end -->
		</div>
		<!-- writeblog end -->
	</div>
</div>
<!-- 弹出层 模块 添加分组 开始 -->
<div class="popbox" id="addblogcat" style="display:none">
	<div class="pophead">
		<div class="in-pophead">
			<h4><b>请输入分类名</b></h4>
			<span class="popCLose"><a title="关闭" class="closeA bgreen" href="javascript:void(0);$.Jxq.HideDialog('#creatgroups')">×</a></span>

		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
			<div class="increatgroups">
				分类名称：<input type="text" maxlength="20" class="normal w150" name="diarytype" id="diarytype"/>
			</div>

		</div>

	</div>
	<div class="popfoot">
		<div class="in-popfoot">
			<div  class="addfriendBtn clearfix">
				<button title="确定" class="delBtn igreen" onclick="addgroup($('#diarytype').attr('value'))">确定</button>
				<button title="取消" type="button" class="cancerBtn igray" onclick="$.Jxq.HideDialog('#creatgroups')">取消</button><span></span>
			</div>
		</div>

	</div>
</div>
<!-- 弹出层 模块 结束 -->
<!-- 2.0 html end zrj 2009-6-4 -->

 </@home.home>
 </#escape>