<!--弹出窗口start-->
<div  class="popbox2">
<div class="box-orange">
	<h4>系统提示</h4>
	<div class="box-content">
		<p>${message}</p>
	</div>
	<div class="popboxbtn">
    	
    	<button onclick="window.location.href='${redirectURL}'" class="submitBtn">确定</button>
    	
    	<button onclick="$.unblockUI();" class="cancerBtn">关闭</button>
    	
	</div>
</div>


</div>
<!--弹出窗口end-->

