<#escape x as (x)!"">
<script type="text/javascript">
	checkareatext();
</script>
<@s.iterator value="usermessagelist"  status="s">
<#assign userinfo=userutil.getUserinfo(replyUserid) />
<dl id="mag_item_${id}">
<dt class="none"></dt>
<dd>
<div class="info">
<a href="../home/home.action?viewUserid=${replyUserid}">${userinfo.userName}</a> 回复道：</span><span class="time">${createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
</div>
<div class="mag_info_box">
<div class="mag_info">${content}</div>
<div class="mag_dels">
<#if currentUserid=viewUserid>
   <a id="mag_a_${id}" href="javascript:void(0)" class="del" onclick="setDelId(${id});$.Jxq.delTips('#mag_a_${id}','#mag_del',-60,20,220)">删除</a>
  </#if>
</div>
</div>
</dd>

</dl>
</@s.iterator>


<div class="form mag_reply">
<form method="post" action="">
    <fieldset>
    <legend>我要回复</legend>
	<input type="hidden" id="viewUserid"  name="viewUserid" value="${viewUserid}">
	<input type="hidden" id="userid" name="userid" value="${currentUserid}">
      <p>
      <textarea maxlen="200" rows="5" id="t${messageid}"></textarea>
      </p>
    <p>
      <input class="delBtn igreen" type="button"onclick = "addComm(${messageid})" value="回复 " />
    </p>
    </fieldset>
</form>
</div>
</#escape>

