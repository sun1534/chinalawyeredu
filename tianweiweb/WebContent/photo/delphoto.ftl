<#escape x as (x)!"">

<div style="display:none" class="webmenu" id="p${i}_">
	<div class="tooltips-del">
		<div class="intooltips-del">
			<span>确认删除该相片？</span>
			<input type="button" class="delBtn igreen" onclick="delphoto(${photoid},${albumid},${i})" title="确定" value="确定"/>
			<input type="button" class="cancerBtn igray" onclick="removeDelPhoto(${i})" title="取消" value="取消"/>
		</div>
	</div>
</div>

</#escape>