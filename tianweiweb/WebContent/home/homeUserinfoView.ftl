 <#escape x as (x)!"">
<dt><img src="${resourcepath}${bigImage?replace('/_80_/', '/_128_/')}" />
<#if viewUserid=currentUserid>
<p><a href="../user/userbaseview.action">个人资料</a>　<a href="../user/usercontactview.action">联系方式</a><!--<a href="../user/usersetview.action">隐私设置</a>--></p>
<p><a href="../user/userpassword.action">密码修改</a>　<a href="../user/userimages!input.action">更新头像</a></p>
<#else>
<p>
<#if !isfriend>
<a href="javascript:showeditfriend(${viewUserid},'${username}')">加为好友</a></#if>
&nbsp;&nbsp;
<!--<a href="javascript:backmessage(${currentUserid},${viewUserid})">发送信息</a></p>-->
<a href="../message/backMessageaction!input.action?msgid=0&desuserid=${viewUserid}">发送信息</a></p>
</#if>
</dt>
  <dd>

  	<p><font color="#336699">${meterialmessage?default("")}</font></p>

  	<p><strong>${username} <#if viewUserrole=1>同学<#elseif viewUserrole=2>家长<#else>老师</#if></strong>：${sign}
  	<!--
  	<#if viewUserid=currentUserid>
  	  <a href="../user/userpersonalview.action"><img src="${staticpath}/css/images/icon/sz1.gif" alt="修改签名" title="修改签名" border="0" /></a>
  	</#if>
  	-->
  	</p>
	<#switch viewUserrole>
	<#case 1>
	<p></p>
    <p><strong>我的天威广告业务管理站点</strong></p>
    <p><span>我的家长</span>：<a href="../home/home.action?viewUserid=${parent.id}&viewUserrole=2">${parent.userName}</a> 家长</p>
    <p><span>我的班主任</span>：<a href="../home/home.action?viewUserid=${teacher.id}&viewUserrole=3">${teacher.userName}</a> 老师</p>
  
	<p><span>我所在班级</span>：${coreclass.className}</p>
	<#break>
	<#case 2>
   
    <p><strong>我的天威广告业务管理站点</strong></p>
	<table class="newsChildinfo">
    <#assign ii=0 />
    <#list childrenlist as child>

	  <tr>
		<td width="70" ><b>我的孩子：</b></td>
		<td width="210"><a href="../home/home.action?viewUserid=${child.id}&viewUserrole=1">${child.userName}</a> 同学</td>
		<td rowspan="2" valign="top"><a href="../home/home.action?viewUserid=${child.id}&viewUserrole=1"><img src="${resourcepath}${child.pic?replace('/60/', '/48/')}" /></a></td>
	  </tr>
	  <tr>
		<td valign="top"><b>孩子班级：</b></td>
		 <#assign dest=userutil.getClasses(child.classId) />
		<td valign="top">${dest.className}</td>
	  </tr>
	    <#assign ii=ii+1 />
    </#list>
	</table>
	<#break>
	<#case 3>
  
    <p><strong>我的天威广告业务管理站点</strong></p>

    <p><span>我任教科目</span>：${(teacher.subjectName)?default("暂无学科")}</p>

    <p><span>我任教的班级</span>： <div class="new2bj">
    <#list classes as cs>
   ${cs.className} <br/>
   </#list></div>
</p>
	
	</#switch>

	<#if !isfriend>

<div class="popbox" id="addbox" style="height:auto; display:none">
	<div class="box-orange">
		<h4>发送好友请求</h4>
		<div class="box-content" id="pbtext">
			 <p> 发送请求加<strong>"<span id="friendname"></span>"</strong>为好友</p>
  <strong>附言：</strong>
  <p><textArea name="" id="remark"  rows="3" cols="45"></textarea></p>
		</div>
		<div class="popboxbtn">
			<button onclick="sendapply($('#remark').attr('value'))" class="popdelete">确定</button>
			<button  onclick="hideaddbox()" class="popcancel">取消</button>
		</div>
	</div>

</div>
	</#if>
  </dd>
  </#escape>