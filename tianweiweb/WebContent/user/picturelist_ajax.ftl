<#escape x as (x)!"">
<div>
<ul class="main_photolist">
	<#if picturelist.size()==0 >
    <div class="demo">
    <p>您的这个相册还没有照片</p>
    </div>
    <#else>
    <#list picturelist as pl >
	<li><IMG name=Myimg SRC="${resourcepath}${pl.logoname}" BORDER="0" align="middle" width="80" height="80">
	<input type="radio" name="photoid" id="photoid" value="${pl.photoid}" /></li>
    </#list>
    </#if>
   </ul>
	<div class="clear"></div>
	<div class="page"> <#if photoPage.count!=0> <span>共 ${photoPage.count} 页</span> <span>${pageString}</span> </#if> </div>
    <form name="pageForm" action="picturelist.action">
    <input type="hidden" name="pageNo" value="" />
    </form>
    <#if photoPage.count!=0>
    <p><span><input  id="" type="button" value="确定"  class="i-button w2" /></span></p>
    </#if>
    </div>
    </#escape>