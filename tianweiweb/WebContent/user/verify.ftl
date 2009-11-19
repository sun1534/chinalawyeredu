<div class="popbox2">
  <div class="box-orange">
    	<h3>提示</h3>
		<div class="box-content">
			<form id="verifyform">
			</form>
			<p style="line-height:30px;font-style:bold;">请选择认证方式</p>
			<p>
				<input type="radio" name="approve_type" value="1" onclick="selectit(1)"/>身份证认证
				<input type="radio" name="approve_type" value="2" onclick="selectit(2)" />电话认证
				<input type="radio" name="approve_type" value="3"  onclick="selectit(3)"/>上门认证
			</p>
			
		</div>
		<div class="popboxbtn">
    		<button onclick="$.unblockUI();"  class="cancerBtn" >取消</button> 
    		<button onclick="sendverify()" class="delBtn igreen w80">确定</button>
		</div>
	</div>
</div>

<script language="javascript">
var selected=false;
var _type=1;
function selectit(val){
selected=true;
_type=val;
}
function sendverify(){
if(!selected){
alert("请先选择认证方式");
return;
}
$("#verifyform").ajaxSubmit();
		$.ajax({
		    type: "POST",
		    data:"approve_type:"+approve_type,
		    url:"../user/sendverifyapply.action",
		    success:function(data){
		    	$.blockUI({message:data});
	    }});


	//$("#verifyform").ajaxSubmit({
		// success:function(data){
		
		 	//if(data=="ok"){
		 		//location.href="/user/userbaseview.action";
		 	//	location=location;
		 	//}else{
		 	//	alert(data);
		 	//}
		 //	$.blockUI({message:data});
		// }
 	 //});
}
</script>
<!--弹出窗口end-->
