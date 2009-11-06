<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	function addDiary(){
    if($.trim($("#tile").attr("value"))=='')
		{
			alert("标题不能为空");
			return false;
		}
	 var content2 = FCKeditorAPI.GetInstance("proDiary.content").GetXHTML(true);
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

<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3><a href="../diary/diarylist.action">博客</a></h3></div>
		<div class="title-info">
			<!-- tag -->
			<div class="m-tag">
				<ul>
					<li><a href="../diary/diarylist.action"><span>我的博客</span></a></li>
					<li><a href="../diary/diarylist.action?type=friend"><span>好友和同学们的博客</span></a></li>
				</ul>
				<a href="../diary/addDProiary!input.action" class="creatblog" title="写新博客">写新博客</a>
			</div>
		</div>
		<!-- 写blog writeblog start-->
		<div class="writeblog">
			<h4>写新博客</h4>
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
								<a class="ico a-add" onclick="$.Jxq.ShowDialog('#addblogcat',400,-120,-100)" href="javascript:void(0)" title="增加新分类" >增加新分类</a>
							    &nbsp;&nbsp;隐私设置:   <@s.select name="proDiary.privateFlag" list=r"#{0:'全站公开',1:'好友可见'  ,'2':'设为私密'}" />
							</span>
						</div>
						<div class="even">
							<label class="fname" for="cname"><em>*</em>内容：</label>
							<span class="fvalue">
								<textarea name="proDiary.content" id="proDiary.content" style="width:500px;"></textarea></span><input type=hidden id="summary"  name="summary" value="${diary.summary}"/></p>
								<script type="text/javascript">
                                    var oFCKeditor = new FCKeditor( 'proDiary.content' ) ;
                                    oFCKeditor.BasePath = '../FCKeditor/' ;
                                    oFCKeditor.ToolbarSet = 'Basic' ;
                                    oFCKeditor.Width = '650' ;
                                    oFCKeditor.Height = '400' ;
                                    oFCKeditor.Value = '' ;
                                    oFCKeditor.ReplaceTextarea();
                                    //oFCKeditor.Create() ;
                                </script>
							</span>
						</div>
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