<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<script type="text/javascript">

    function addDiary(data){

    document.form1.action="editProDiary.action?diaryid="+data;
    document.form1.submit();
	}

    function showdiary()
    {
     $('.overshadow').attr("style","display:block");
	 $('#AddblogCat').attr("style","display:block");
    }

    function addgroup(groupname){
//	alert("--"+groupname+"--");
	if(groupname==""){
		alert("类型不能为空");
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
</script>
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
					<li><a href="../diary/diarylist.action"><span>我的所有<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></span></a></li>
				</ul>
				<a href="../diary/addDProiary!input.action" class="creatblog">发布<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></a>
			</div>
		</div>
		<!-- 写blog writeblog start-->
		<div class="writeblog">
			<h4>编辑<#if currentRole=1>文章</#if><#if currentRole=2>新闻</#if></h4>
			<!-- blog_line start -->
			<form id="form1" name="form1" method="post" action="../diary/editProDiary.action">
			<div class="blog_line">
				<div class="in-writeblog form" id="baseform">
						<p class="note">带<em>*</em>的为必填项</p>
						<div class="even">
							<label class="fname" for="cname"><em>*</em>标题：</label>
							<span class="fvalue">
								<input type="text" class="normal w350" maxlength="80" id="tile" name="proDiary.tile" value="${proDiary.tile}"/>
							</span>
						</div>
						<div class="odd">
							<label class="fname" for="cname"><em>*</em>分类：</label>
							<span class="fvalue">
								<@s.select name="proDiary.diarytypeId" list="diaryTypeList"  value="${proDiary.diarytypeId}"/>
							</span>
						</div>
						<div class="even">
							<label class="fname" for="cname"><em>*</em>内容：</label>
							<span class="fvalue">
								<textarea name="proDiary.content" id="proDiarycontent" style="width:500px;">${proDiary.content}</textarea></span>
								<input type=hidden id="summary"  name="summary" value="${diary.summary}"/></p>
							</span>
						</div>
						<div class="formbtn">
							<label class="fname" for="cname"> </label>
							<span class="fvalue">
								<input type="button" class="delBtn igreen" title="修改" value="修改" onclick="javascript:addDiary(${proDiary.diaryid});"/>
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
 </@home.home>
 </#escape>