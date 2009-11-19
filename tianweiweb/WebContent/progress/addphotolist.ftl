<#escape x as (x)!"">
<!--弹出窗口start-->
<div style="overflow:hidden;"  class="popbox2">
<div class="box-orange">
	<h4>选择图片</h4>
	<form id="addpicform" action="addselectphoto.action" method="POST">
	<input type="hidden" name="contentid" value="${contentid}" />
	<input type="hidden" name="publishid" value="${publishid}" />
	<div style="line-height:25px;">${pageString}</div>
	<ul class="selectpic clearfix">
		<#list page.items as photo>
		<li><img src="../${photo.smallPic}" /><span><input type="radio" name="photoid" value="${photo.photoid}" /></span></li>
	</#list>
	</ul>

	<table style="margin-top:10px">
		<tbody>
			<tr>
				<td valign="top"><b>祝福语：</b></td>
				<td><textarea style="width:300px;height:20px;" name="remark">${remark}</textarea></td>
			</tr>
		</tbody>
	</table>
	</form>
	<div style="text-align:center;padding:10px "><input onclick="addselectphoto()" class="submitBtn" type="submit" value="确定"/>
    <input onclick="$.unblockUI();"  type="button" class="cancerBtn" value="取消"/></div>

</div>
</div>
<script>
function addselectphoto(){
	$("#addpicform").ajaxSubmit({
		 success:function(data){
		 	if(data=="ok"){
		 		//location.href="/home/home.action";
		 		document.location.reload();
		 	}else{
		 		alert(data);
		 	}
	 	 }
	 });
}
function getPhotoPage(pageno){
	$.ajax({
	    type: "POST",
	    data:"publishid="+${publishid}+"&contentid="+${contentid}+"&pageNo="+pageno+"&remark=${remark}",
	    url:"../progress/addphotolist.action",
	    success:function(data){
	    	$.unblockUI();
	    	$.blockUI({message:data});
    }});
}
</script>
<!--弹出窗口end-->
</#escape>