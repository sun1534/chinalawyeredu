<#import "../common/home.ftl" as home>
<#escape x as (x)!"">
	<@home.home myheader="message/systemmessage_h.ftl" >
   <div class="in-main">
	<div class="title-h"><h3><a href="../message/messagecenter.action">消息</a> > 系统消息</h3></div>
	<div class="msgcollect">
			<ul>
            <li>站内信：<span><a href="../message/shortmessagelist.action?type=1" id="topshortmsg1">0条</a></span></li>
            <li class="xitong">系统消息：<span><a href="../message/systemmessagelist.action" id="topsysmsg1">0条</a></span></li>
            <li class="liuyan">留言板：<span><a href="../message/leavemessagelist.action?status=1" id="topreply1">0条</a></span></li>
            <#if currentRole=3 >
            <li class="jiaxiaohudong"><span><a href="../message/sendsmsMessageinput.action">家校互动</a></span></li>
            </#if>
          </ul>
       </div>
	   <div class="back_button">
			<input type="button" class="delBtn igreen back" onclick="javascript:history.back(-1)" title="返回" value="返回"/>
			</div>
   	    <div class="mag_sendbox">

            <div class="mag_wrap">
              <div class="Tips">
                 <#if page.items?size == 0>
				  <div class="InTips Tips-gray">    <h4>提示</h4>    <div class="TipsCon">        <p>暂无系统消息</p>    </div></div>
                  </#if>
                <@s.iterator value="page.items" status="s">
                <div id="mag_item_${id}" class="message_content message_sys">
                 <#if true>
                 <dl>
                <dt><a href="#"><img src="../css/images/icon/xtxx.gif" /></a></dt>
					<dd>
					<div class="mag_info_box">
						<div class="mag_info">${content}</div>
						<a id="mag_a_${id}" href="javascript:void(0)" class="del" onclick="setDelId(${id});$.Jxq.delTips('#mag_a_${id}','#mag_del',-60,-160,180)" >删除</a>
					</div>
					<div class="info">${sendtime}</div>
					</dd>
                </dl>

                  <#else>
              	<dl>
                <dt><a href="#"><img src="../css/images/icon/xtxx.gif" /></a></dt>
					<dd>
					<div class="mag_info_box">
						<div class="mag_info">${content}</div>
						<a id="mag_a_${id}" href="javascript:void(0)" class="del" onclick="setDelId(${id});$.Jxq.delTips('#mag_a_${id}','#mag_del',80,70,180)" >删除</a>
					</div>
					<div class="info">${sendtime}</div>
					</dd>
                </dl>
                 </#if>
              </div>
               </@s.iterator>
               </dd>
                </dl>
              </div>
				  <div class="back_button">
					<input type="button" value="清空信息" name="cname" title="清空所有信息"   onclick="javascript:delmessageall()" class="delBtn igreen" />
				  </div>
				<div class="formop">
                <div class="pager">
                  <span>共 ${page.count} 页</span>
       		      <span>${pageString}</span>
       		      <form name="pageForm" action="systemmessagelist.action">
  		 		  <input type="hidden" name="pageNo" value="" />
  		 	     </form>
                </div>
			</div>
          </div>
        </div>
     </div>
<!--
<div class="popbox" id="refusebox" style=" display:none">

  <div class="box-orange" >
		<h4>拒绝请求</h4>
		<div class="box-content" id="pbtext3">
			 <p>拒绝<strong>"<span id="friendname"></span>"</strong>成为好友的请求</p>
			 <p><strong>附言：</strong>
			<textArea name="" id="reason"  rows="3" cols="45"></textarea></p>
		</div>
		<div class="popboxbtn">
			<button onclick="refuse($('#reason').attr('value'))" class="popdelete">确定</button><button onclick="hiderefusebox()" class="popcancel">取消</button>
		</div>
	</div>
</div>
--->
<div class="popbox" id="refusebox" style="display:none">
		<div class="pophead">
			<div class="in-pophead">
				<h4><b>拒绝请求</b></h4>
				<span class="popCLose"><a title="关闭" class="closeA bgreen" href="javascript:void(0);$.Jxq.HideDialog('#addFriendBox')">×</a></span>
			</div>
		</div>
		<div class="popmain">
			<div class="in-popmain clearfix">
				<div class="addfriendhead">拒绝<strong>"<span id="friendname"></span>"</strong>成为好友的请求</div>
				<div class="addimg">
					<dl>

						<dt>附言：</dt>
						<dd class="form"><textArea name="" id="reason"  rows="3" cols="45"></textarea></dd>
					</dl>
				</div>
				<div class="clear"></div>
			</div>
		</div>

		<div class="popfoot">
			<div class="in-popfoot">
				<div  class="addfriendBtn clearfix">
					<button onclick="refuse($('#reason').attr('value'));" class="delBtn igreen" >确定</button>
					<button onclick="$.Jxq.HideDialog('#refusebox')" class="cancerBtn igray">取消</button>

				</div>
			</div>
		</div>
	</div>



		<div style="display:none;" id="mag_del">
			<div class="tooltips-del_">
				<div class="intooltips-del_">
					<span>确认删除信息吗？</span>
						<input type="button" id="delmsgid" class="delBtn igreen" rel="" onclick="delsysmsg();" title="确定" value="确定"/>
						<input type="button" class="cancerBtn igray" onclick="$.Jxq.hideTips('#mag_del')" title="取消" value="取消"/>
				</div>
			</div>
		</div>
		
		<!-- 弹出层 模块 添加分组 开始 -->
		<div class="popbox" id="editgroups" style="display:none"></div>
		
		
<!-- 弹出层 模块 添加分组 开始 -->
<div class="popbox" id="creatgroups" style="display:none">
	<div class="pophead">
		<div class="in-pophead">

			<h4><b>请输入分组名</b></h4>
			<span class="popCLose"><a title="关闭" class="closeA bgreen" href="javascript:void(0);$.Jxq.HideDialog('#creatgroups')">×</a></span>
		</div>
	</div>
	<div class="popmain">
		<div class="in-popmain clearfix">
			<div class="increatgroups">
				分组名称：<input type="text" class="normal w150" name="groupname" id="groupname" maxlength="10"/>
			</div>

		</div>
	</div>
	<div class="popfoot">
		<div class="in-popfoot">
			<div  class="addfriendBtn clearfix">
				<button title="确定" class="delBtn igreen" onclick="systemaddgroup($('#groupname').attr('value'));">确定</button>
				<button title="取消" class="cancerBtn igray" onclick="$.Jxq.HideDialog('#creatgroups')">取消</button><span></span>

			</div>
		</div>
	</div>
</div>
<!-- 弹出层 模块 结束 -->
		<script>
		
var msg1;
var msg2;
var msg3;
$.ajax({
	type:"post",
	url:"../home/messageCountShow.action",
	success:function(data){
	   msgcnt=data.split(",");
	   msg1=msgcnt[0];
	   msg2=msgcnt[1];
	   msg3=msgcnt[2];

	   $("#topsysmsg1").html(packmsg(msg1)+"条");
	   $("#topshortmsg1").html(packmsg(msg2)+"条");
	   $("#topreply1").html(packmsg(msg3)+"条");
	}
});
</script>
	</@home.home>
</#escape>