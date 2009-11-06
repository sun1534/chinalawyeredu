<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
<@home.home myheader="message/leavelist_h.ftl" >
<div class="in-main" >
	<div class="title-h"><h3><a href="../message/messagecenter.action">站内消息</a></h3></div>
	<div class="title-info">
			<div class="m-tag">
				<ul>
					<li <#if type=1> class="current"</#if>><a href="shortmessagelist.action?type=1" ><span>收件箱</span></a></li>
					<!--<li <#if type=2> class="current"</#if>><a href="shortmessagelist.action?type=2" ><span>已发消息</span></a></li>
                    <li><a href="sendMessage!input.action?sendto=1" ><span>写站内信</span></a></li>
                    -->
				</ul>
			</div>
      </div>

<div class="message_content">
          <form name="form1" id="form1" action="../message/shortmessagelist.action">
          	<input type="hidden" id="type" name="type" value="${type}"/>
          	<input type="hidden" id="sendtype" name="sendtype" value="${type}"/>
          	<input type="hidden" name="pageNo" id="pageNo" />
          </form>
		<@s.iterator value="msglist" status="s">
		<dl id="mag_item_${id}">
           <dt>
		   <#if type=2>
		      <#assign dest=util.getDest(id) />
		      <#assign counts=util.getCount(id) />
		      </#if>
		          <#if type=1>
		            <#assign dest=userutil.getUserinfo(sendUserid) />
		            <#assign counts=util.getCount(id) />
		          </#if>
		   </dt>
               <dd>
                <div class="mag_info_box">
				<div class="mag_info">${content}</div>
				<!--<a id="mag_a_${id}" href="javascript:void(0)" onclick="setDelId(${id});$.Jxq.delTips('#mag_a_${id}','#mag_del',-60,-160,180)" class="del" title="删除消息">删除</a>-->
				<!--<#if type=1><a href="backMessageaction!input.action?msgid=${id}&desuserid=${sendUserid}" class="del" title="回复消息">回复</a></#if>-->
				</div>
                <div class="info"><span><#if type=2>发给</#if><#if type=1>来自</#if>：</span>系统
				<#if type=2>${counts}</#if>
		        <#if type=1><#if counts?length gt 2>群发</#if></#if></div>
                <div class="info"><span>${sendTime}</span></div>
				<p style="display:none">${parentid}</p>
               </dd>
      </dl>
	  </@s.iterator>
</div>

         <#if msglist?size==0>
         <div class="box-grey sp-t15">
			<h4>暂无站内信</h4>
			<div class="box-content">
				<p>您暂时还没有站内信！</p>
			</div>
		</div>
		</#if>
         <div class="formop">
			<div class="pager">${page.jxqPage}</div>
		</div>

<div style="display:none;" id="mag_del">
			<div class="tooltips-del_">
				<div class="intooltips-del_">
					<span>确认删除信息吗？</span>
						<input type="button" id="delmsgid" class="delBtn igreen" rel="" onclick="delmsg();" title="确定" value="确定"/>
						<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#mag_del')" title="取消" value="取消"/>
					</div>
				</div>
			</div>
</div>
</@home.home>
</#escape>