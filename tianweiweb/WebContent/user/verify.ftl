<div class="popbox2">
  <div class="box-orange">
    	<h3>提示</h3>
		<div class="box-content">
			<form id="verifyform" action="sendverifyapply.action">
			<p style="line-height:30px;font-style:bold;">请选择认证方式</p>
			<p>
				<input type="radio" name="approve_type" value="1" />身份证认证
				<input type="radio" name="approve_type" value="2" />电话认证
				<input type="radio" name="approve_type" value="3" />上门认证
			</p>
			</form>
		</div>
		<div class="popboxbtn">
    		<button onclick="$.unblockUI();"  class="cancerBtn" >取消</button> <button onclick="sendverify()" class="delBtn igreen w80">确定</button>
		</div>
	</div>
</div>

<script>
function sendverify(){
	$("#verifyform").ajaxSubmit({
		 success:function(data){
		 	if(data=="ok"){
		 		//location.href="/user/userbaseview.action";
		 		location=location;
		 	}else{
		 		alert(data);
		 	}
		 }
 	 });
}
</script>
<!--弹出窗口end-->
