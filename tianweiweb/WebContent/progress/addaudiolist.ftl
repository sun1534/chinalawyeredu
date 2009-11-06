<!--弹出窗口start-->
<div style="overflow:hidden;"  class="popbox2">
<div class="box-orange">
	<h4>选择文件</h4>
	<form id="addpicform" action="addselectaudio.action" method="POST">
	<input type="hidden" name="publishid" value="${publishid}" />
	<#list page.items as audio>
	<input type="checkbox" name="fileid" value="${audio.id}" />${audio.fileName}<br />
	</#list>
	</form>
	<div class="popboxbtn">
		${pageString}
    	<button onclick="addselectdiary()" class="popdelete">确定</button>
    	<button onclick="$.unblockUI();" class="popdelete">取消</button>
	</div>
</div>
</div>
<script>
function addselectdiary(){
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
</script>
<!--弹出窗口end-->