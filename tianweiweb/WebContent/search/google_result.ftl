<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/sendmessage_h.ftl" >
<div id="sider">
   <div class="msg">
      <div class="pagecontent"><a href="../home/home.action">我的主页</a>&raquo;  <strong>搜索结果</strong>
      	<span><input id="qinput"  value="${q}" type="text" class="inputxt" /><a href="#" onclick="$('#q').attr('value',$('#qinput').attr('value'));$('#cse-search-box').submit();">资源搜索&nbsp;&nbsp;&nbsp;&nbsp;</a></span>
         <form style="display:none;" action="../search/result.action" id="cse-search-box" method="post">
		    <input type="hidden" name="cx" value="000404249635326097413:vwniagudlxo" />
		    <input type="hidden" name="cof" value="FORID:11" />
		    <input type="hidden" name="ie" value="UTF-8" />
		    <input type="hidden" name="google" value="true" />
		    <input type="text" name="q" id="q" class="inputxt" />
	     </form>
      </div>
            
          <div id="cse-search-results"></div>
			<script type="text/javascript">
			  var googleSearchIframeName = "cse-search-results";
			  var googleSearchFormName = "cse-search-box";
			  var googleSearchFrameWidth = 600;
			  var googleSearchDomain = "www.google.com";
			  var googleSearchPath = "/cse";
			</script>
		<script type="text/javascript" src="http://www.google.com/afsonline/show_afs_search.js"></script>
        </div>
    </div>
</@home.home>
</#escape>