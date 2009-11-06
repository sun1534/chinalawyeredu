<#import "/common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="diary/diarylist_h.ftl" >
<#assign iclass="" />
<#if currentRole=1>
<#assign iclass="tab-green" />
</#if>
<#if currentRole=2>
<#assign iclass="tab-blue" />
</#if>
<#if currentRole=3>
<#assign iclass="tab-orange" />
</#if>



<!-- 2.0 html zrj 2009-6-4 -->
<div class="in-main">
	<div class="blogbox">
		<div class="title-h"><h3><a href="../photo/albumlist.action">相册</a></h3></div>
		<div class="title-info">
			<!-- tag -->
			
		</div>
		
		
		<div class="Tips"  id="delblogtips">
				<div class="InTips  Tips-green">
					<h4>系统提示</h4>
					<div class="TipsCon">
						<p>您访问的相片已删除！</p>
						<div class="TipsConbtn">
							
						</div>
					</div>
				</div>
			</div>
     
		
		
		
	</div>
</div>

</@home.home>
</#escape>