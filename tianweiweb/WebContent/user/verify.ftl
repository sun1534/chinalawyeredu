<!--弹出窗口start-->
<!--弹出窗口start-->
<div class="popbox2" style="background:#87CEFA">
  <div class="box-orange">
    	<h3>提示</h3>
      <span><a href="#" onclick="$.unblockUI();">关闭</a></span>    </div>
    <div class="box-content">
    	<form id="verifyform" action="sendverifyapply.action">
    	<p>请选择认证方式</p>
    	<p>
	    	<input type="radio" name="approve_type" value="1" />身份证认证
	    	<input type="radio" name="approve_type" value="2" />电话认证
	    	<input type="radio" name="approve_type" value="3" />上门认证
    	</p>
    	</form>
    </div>
    <div class="popboxfoot">
    	<button onclick="$.unblockUI();">取消</button> <button onclick="sendverify()">确定</button>
    </div>
</div>
<script>
function sendverify(){
	$("#verifyform").ajaxSubmit({
		 success:function(data){
		 	if(data=="ok"){
		 		location.href="/user/userbaseview.action";
		 	}else{
		 		alert(data);
		 	}
		 }
 	 });
}
</script>
<!--弹出窗口end-->
