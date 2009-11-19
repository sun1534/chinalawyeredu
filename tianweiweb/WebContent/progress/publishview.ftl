<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="progress/progresslist_h.ftl" >

<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3>详细信息</h3></div>
		<div class="bloglist">
			<!-- list start  -->
			<div class="prolist2 clearfix">
			<dl>
				<dt><img  width="330" height="270" src="${product.pic}"   /></dt>
				<dd>
					<p><b>产品介绍</b></p>
					<p>${product.name}</p>
					<p>简介：${product.description}</p>
					<p>价格：${product.pricestr} ${product.unit}</p>
					<p>当前状态：
					 <#if publish.statusid=1>初订购</#if>
			   	     <#if publish.statusid=2>待付费（<a href="../progress/tenpay.action?id=${product.id}" target="_blank" class="a_pay">现在付款</a>）</#if>
			   	     <#if publish.statusid=3>待审核</#if>
			   	     <#if publish.statusid=4>审核未通过</#if>
			   	     <#if publish.statusid=5>审核通过</#if>
			   	     <#if publish.statusid=99>发布中</#if>
			   	     <#if publish.statusid=100>业务到期</#if>
			   	     </p>
				</dd>
			</dl>
			</div>
			<#if currentRole=1>
				<div class="companylist">

					<div class="title-h"><h3>内容</h3></div>
					<div class="piclist_ clearfix">
						<#list photos as photo>
						<ul>
							<li><img src="../${photo.smallPic}" onclick="javascript:addphoto(${publish.id},${photo.contentid},'${photo.remark}')"  /></li>
							<li>${photo.remark}</li>
						</ul>

						</#list>
						<#if remainphotos gt 0>
							<#list 1..remainphotos as ii>
								<ul>
									<li><img src="../upload/logo/00/00/15_96_2.jpg" onclick="javascript:addphoto1(${publish.id})" /></li>
									<li>祝福</li>
								</ul>
							</#list>
						</#if>
					</div>
					<#if contentsize gt 0>
						<textarea maxLength="${contentsize}" id="txtcontent" class="editArea">${content}</textarea>
						<input type="button" id="editbtn" onclick="editit()" value="编辑"  class="delBtn igreen "/>
						<input type="button" id="savebtn" onclick="saveit()" value="保存"   class="delBtn igreen " style="display:none" />
						<input type="button" id="cancelbtn" onclick="cancelit()" value="取消"  class="cancerBtn" style="display:none" />
					</#if>
				</div>

				<!--<div class="companylist">
					<div>文章</div>
					<#list diarys as diary><div>${diary.tile}</div></#list>
					<#if publish.statusid=1><div><a href="javascript:adddiary(${publish.id})">添加</a></div></#if>
					<#if publish.statusid=2><div><a href="javascript:adddiary(${publish.id})">添加</a></div></#if>
					<#if publish.statusid=4><div><a href="javascript:adddiary(${publish.id})">添加</a></div></#if>
				</div>
				<#if product.maxaudio gt 0>
				<div class="companylist">
					<div>音频视频</div>
					<#list audios as audio><div>${audio.fileName}</div></#list>
					<#if publish.statusid=1><div><a href="javascript:addaudio(${publish.id})">添加</a></div></#if>
					<#if publish.statusid=2><div><a href="javascript:addaudio(${publish.id})">添加</a></div></#if>
					<#if publish.statusid=4><div><a href="javascript:addaudio(${publish.id})">添加</a></div></#if>
				</div>
				</#if>
				-->
			</#if>

			<#if currentRole=2>
				<#if publish.statusid!=2>
					<div class="list  tablist" >
						<table class="tableBox">
							<thead>
								<tr>
									<th  class="w200">文件包名称</th>
									<th>审批状态</th>
									<th>&nbsp;</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody>
								<#list audios as file>
								<tr>
									<td>${file.fileName}</td>
									<td><#if file.publishstatus=0>待审核</#if><#if file.publishstatus=1>审核通过</#if><#if file.publishstatus=2>审核不通过</#if></td>
									<td><a href="${file.url}">下载</a></td>
									<td><#if file.publishstatus=0><a href="javascript:delcontent(${file.id})">删除</a></#if>
									<#if file.publishstatus=2><a href="javascript:delcontent(${file.id})">删除</a></#if></td>
								</tr>
								</#list>

								<#if publish.statusid=5><tr><td colspan=2><a href="javascript:getUploadFile(${publish.id})" class="a_book">添加</a></td></tr></#if>
								<#if publish.statusid=99><tr><td colspan=2><a href="javascript:getUploadFile(${publish.id})" class="a_book">添加</a></td></tr></#if>
							</tbody>
						</table>
					</div>
				</#if>
			</#if>
		</div>
	</div>
</div>
<script>
function addphoto1(publishid){
	$.ajax({
	    type: "POST",
	    data:"publishid="+publishid,
	    url:"../progress/addphotolist.action",
	    success:function(data){
	    	$.blockUI({message:data});
    }});
}
function addphoto(publishid,contentid,remark){
	$.ajax({
	    type: "POST",
	    data:"publishid="+publishid+"&contentid="+contentid+"&remark="+remark,
	    url:"../progress/addphotolist.action",
	    success:function(data){
	    	$.blockUI({message:data});
    }});
}
function adddiary(publishid){
	$.ajax({
	    type: "POST",
	    data:"publishid="+publishid,
	    url:"../progress/adddiarylist.action",
	    success:function(data){
	    	$.blockUI({message:data});
    }});
}
function addaudio(publishid){
	$.ajax({
	    type: "POST",
	    data:"publishid="+publishid,
	    url:"../progress/addaudiolist.action",
	    success:function(data){
	    	$.blockUI({message:data});
    }});
}
function delcontent(id){
	if(confirm("确定要删除此文件吗？")){
		$.ajax({
		    type: "POST",
		    data:"publishid="+${publish.id}+"&id="+id,
		    url:"../progress/contentdel.action",
		    success:function(data){
		    	//$.blockUI({message:data});
		    	location=location;
	    }});
    }
}
function editit(){
	$("#editbtn").hide();
	$("#savebtn").show();
	$("#cancelbtn").show();
	$("#txtcontent").removeClass("editArea");
	$("#txtcontent").addClass("saveEidt");
}
function saveit(){
	//alert($("#txtcontent").val());
	$.ajax({
	    type: "POST",
	    data:"publishid="+${publish.id}+"&content="+$("#txtcontent").val(),
	    url:"../progress/editcontent.action",
	    success:function(data){
	    	//$.blockUI({message:data});
	    	location=location;
    }});
	$("#editbtn").show();
	$("#savebtn").hide();
	$("#cancelbtn").hide();
	$("#txtcontent").removeClass("saveEidt");
	$("#txtcontent").addClass("editArea");

}
function cancelit(){
	$("#editbtn").show();
	$("#savebtn").hide();
	$("#cancelbtn").hide();
	$("#txtcontent").removeClass("saveEidt");
	$("#txtcontent").addClass("editArea");
}
</script>
</@home.home>
</#escape>
