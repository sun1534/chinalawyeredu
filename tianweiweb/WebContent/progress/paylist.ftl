<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="progress/progresslist_h.ftl" >

<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3>网上支付</h3></div>
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
						 <th>订购时间</th>
						 <th class="w150">进度</th>
						 <th class="w60">详细信息</th>
						 <th class="w60">取消订购</th>
					 </tr>
				</thead>
				<tbody>
					<#list page.items as t>
			   <#assign title=userutil.getTitle(t.id) />
			   <tr>
			   	    <td>${title}</td>
			   	   <td>${t.feestr}</td>
			   	   <td>${t.createtime}</td>
			   	   <td>
			   	     <#if t.statusid=1>初订购</#if>
			   	     <#if t.statusid=2>待付费 <a href="../progress/tenpay.action?id=${t.id}" target="_blank" class="a_pay">现在付款</a></#if>
			   	     <#if t.statusid=3>待审核</#if>
			   	     <#if t.statusid=4>审核未通过</#if>
			   	     <#if t.statusid=5>审核通过</#if>
			   	     <#if t.statusid=99>发布中</#if>
			   	     <#if t.statusid=100>业务到期</#if>
			   	   </td>
			   	   <td><a href="/progress/publishview.action?publishid=${t.id}">查看</a></td>
			   	   <td>&nbsp;<#if t.statusid=1><a href="javascript:unorder(${t.id})">取消订购</a></#if>
			   	     <#if t.statusid=2><a href="javascript:unorder(${t.id})">取消订购</a></#if>
			   	   </td>
			   </tr>
			</#list>
				</tbody>
				</table>
			</div>
			</div>
			</#if>
			<!-- list end  -->

			<!-- 分页 -->
			<div class="blogpager">
			  <div class="pager">
                <#if page.count!=0>
      	         <span class="page-total">共${page.count}页</span>${pageString}
                </#if>
				</div>
			</div>
			<!-- 分页 end -->
		</div>
		<!-- bloglist end -->
	</div>
</div>


<form name="pageForm" action="progresslist.action">
  	<input type="hidden" name="pageNo" value="" />
</form>
<script>
function unorder(id){
	if(confirm("确定要取消订购？")){
		$.ajax({
		    type: "POST",
		    data:"id="+id,
		    url:"../progress/unorder.action",
		    success:function(data){
		    	location=location;
	    }});
	}
}
</script>
 </@home.home>
 </#escape>
