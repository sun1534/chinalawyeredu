<#import "../common/homenoleft.ftl" as home>
<#escape x as (x)!"">
<@home.home>
<div class="vvideo">
	<div class="invvideo">
		<div class="vvideoh">
		<div class="ssTips2">
		<b>温馨提示:</b>如果鼠标无法操作，请用上下左右键控制焦点 ，用删除键进行返回,回车键进行选择！
	</div></div>
		<div class="vvideom" >
		<iframe width="640" height="526" src="../szsc/index.htm" frameborder="0" scrolling="no" id="scrolling"></iframe>
		</div>
		<div class="vvideof"></div>
	</div>
	<div class="vvideolist">
		<h3>今日更新</h3>
		<ul>
			<#list shichuangs as shichuang>
				<li><img src="../images/video_doc.gif" width="7" height="7" alt="" align="absmiddle" /><a  href="#" onclick="window.open('${shichuang.url}','newwindow', 'height=526,width=640, top=100, left=50, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" >${shichuang.title}</a></li>
			</#list>
		</ul>
		<div class="vvideolistf"></div>
	</div>
	<div class="clear"></div>
	<div class="ssTips">
		<b>温馨提示:</b>如果鼠标无法操作，请用上下左右键控制焦点 ，用删除键进行返回,回车键进行选择！
	</div>
</div>
</@home.home>
</#escape>