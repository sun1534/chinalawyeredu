<#macro pagehtml pageModule>


<!--设置当前分方式的起始页-->
<#assign currentPage=pageModule.pageNo-2>
<#if currentPage lt 1>
<#assign currentPage=1>
</#if>
<!--设置当前分方式的结束页-->
<#assign endPage=currentPage+4>
<#if endPage gt pageModule.pageCount>
<#assign endPage=pageModule.pageCount>
</#if>
							<div class="page">
								<span class="page-total">共${pageModule.recordCount}条 当前${pageModule.pageNo}/${pageModule.pageCount}</span>
								<#if pageModule.pageCount gt 1>
								<a href='javascript:gotoPage(1);' class="page-first">首页</a>
								<#if pageModule.pageNo gt 1>
								<a href='javascript:gotoPage(${pageModule.pageNo-1});' class="page-pre">上一页</a>
								</#if>
								
								<#list currentPage.. endPage as i>
								<#if i==pageModule.pageNo>
								<span class='page-current'>${i}</span>
								<#else>
								<a title='跳到第${i}页' href='javascript:gotoPage(${i});'>${i}</a>
								</#if>
								</#list>							
								<#if pageModule.pageNo lt pageModule.pageCount>
								<a href='javascript:gotoPage(${pageModule.pageNo+1});' class="page-next">下一页</a>
								</#if>
								<a href='javascript:gotoPage(${pageModule.pageCount});' class="page-last">末页</a>
								<!--
								<span class="selPage">每页显示 
									<select name="pageSize1" onchange="gotoPage(this.value)">
									<#list [5,10,20,50,100] as idx>
									<option value="${idx}" <#if pageModule.pageSize==idx>selected</#if>>${idx}</option>
									</#list>
									</select>条
								</span>
								-->
								<span class="pageOp">
									<label for="pageNo">&nbsp;跳转至
									<select name="pageNo1" onchange="gotoPage(this.value)">
										<#list 1.. pageModule.pageCount as idx>
										<option value="${idx}" <#if pageModule.pageNo==idx>selected</#if>>${idx}</option>
										</#list>
									</select>页
									</label>
								</span>
								</#if>
							</div>
</#macro>