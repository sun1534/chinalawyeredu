<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="/search/result_h.ftl" >
<div id="sider">
   <div class="msg">
   <div class="pagecontent"><a href="../home/home.action">我的主页&raquo;<strong>搜索结果</strong></a>
 	<span><input id="qinput" type="text" class="inputxt" value="${q}" /><a href="#" onclick="$('#q').attr('value',$('#qinput').attr('value'));$('#cse-search-box').submit();">资源搜索&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
     <form style="display:none;" action="../search/result.action" id="cse-search-box" method="post">
	    <input type="hidden" name="cx" value="000404249635326097413:vwniagudlxo" />
	    <input type="hidden" name="cof" value="FORID:11" />
	    <input type="hidden" name="ie" value="UTF-8" />
	    <input type="text" name="q" id="q" class="inputxt" />
	    <a href="#" onclick="$('#cse-search-box').submit()">资源搜索</a>
     </form>
   </div>
   <div class="contentmain">
    <div class="contentmain-body">
    
	  <dl class="txtbox">
	      <dt> p.papername}</dt>
	      <dd> description}</dd>
      </dl>
     
    </div>
  </div> 
</div>
</@home.home>
</#escape>