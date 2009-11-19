<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="progress/progresslist_h.ftl" >
<style type="text/css">
<!--
.listheadline {
	FONT-WEIGHT: bold; FONT-SIZE: 11px; COLOR: #ffffff; FONT-FAMILY: Verdana, Sans-serif; BACKGROUND-COLOR: #469EBB
}
.listline {
	FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; BACKGROUND-COLOR: #EFEFEF; padding-top:0; padding-bottom:0;
}
.listtitle {
	FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #111111; FONT-FAMILY: Arial, Helvetica, sans-serif; background-image:url('../images/bgbar_1.gif')
}
-->
</style>
<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3>产品订购</h3></div>
		<!-- bloglist start -->
		<div class="bloglist">

			<!-- list start  -->
			<#if page.items?size == 0>
 			 <div class="list">
			   <div class="box-grey" style="margin:10px auto;">
					<h4>信息提示</h4>
					<div class="box-content">
						<p>暂无支付内容。</p>
					</div>
				</div>
               </div>
            <#else>
          	<div>
			<div class="tablist">
			<table class="tableBox">
				<thead>
				<tr>
					<th class="w110">产品名称</th>
					<th class="w80">价格</th>
					<th >简介</th>
					<th class="w70">详细介绍</th>
					<th class="w40">案例</th>
					<th class="w40">订购</th>
					</tr>
				</thead>
				<tbody>
					<#list page.items as t>
			     <tr>
			   	   <td>${t.name}</td>
			   	   <td>${t.pricestr} ${t.unit}</td>
			   	   <td>${t.description}</td>
			   	   <td><a href="#">详细介绍</a></td>
			   	   <td><a href="#">案例</a></td>
			   	   <td><a href="javascript:dinggou(${t.id})" class="a_book">订购</a></td>
			   </tr>
			</#list>
				</tbody>
				</table>
			</div>
			</div>
			</#if>
			<!-- list end  -->
		</div>
		<!-- bloglist end -->
	</div>
</div>
<script>
function dinggou(id){
	$.ajax({
	    type: "POST",
	    data:"productid="+id,
	    url:"../progress/orderproduct.action",
	    success:function(data){
	    	if(data.indexOf("userverify.action")>0){
	    		applyverify();
	    	}else{
	    		$.blockUI({message:data});
	    	}
	    }});
	//alert("订购");
}
</script>
 </@home.home>
 </#escape>
<script>
function applyverify(){

		$("#form1").ajaxSubmit();
		$.ajax({
		    type: "POST",
		    data:"",
		    url:"../user/verifyapply.action",
		    success:function(data){
		    	$.blockUI({message:data});
	    }});
    
}

</script>