 <#macro dynamic  f>
<#escape x as (x)!"">
 <#if f.appaction.moduledir=="zlxg">
<li> <a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 修改了 "${f.notename}",快去看看吧  <span>${jt.getSub(f.createtime)}</span></li>
<#elseif f.appaction.moduledir=="kyds">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>参加了英语口语大赛<span>${jt.getSub(f.createtime)}</span></li>
<#elseif f.appaction.moduledir=="zjhy">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 与<a href="../home/home.action?viewUserid=${f.desuser.userid}">${f.desuser.userbase.username}</a>${f.desuser.userorrole.userrole.rolename}成为好友  <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="rjfb">
<li>大家快去瞧瞧吧！！<a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 发表了一篇标题为："<a href=${f.noteurl}>${f.notename}</a>"的博客 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="rjpr">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 对我的"<a href=${f.noteurl}>${f.notename}</a>"进行评论 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="xpsc">

<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 上 传了相片:${f.summary?replace('#..', '&viewUserid='+f.srcuser.userid+'#..')},大家快去看看！！ <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="xppr">
<li><a href="../home/home.action?viewUserid=${f.desuser.userid}">${f.desuser.userbase.username}</a>${f.desuser.userorrole.userrole.rolename} 对我的"<a href=${f.noteurl}>${f.notename}</a>"进行评论 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="ly">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 给<a href="../home/home.action?viewUserid=${f.desuser.userid}">${f.desuser.userbase.username}</a>${f.desuser.userorrole.userrole.rolename}留言了,内容为:${f.notename}<span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="sl">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 给<a href="../home/home.action?viewUserid=${f.desuser.userid}">${f.desuser.userbase.username}</a>${f.desuser.userorrole.userrole.rolename}送了一件漂亮的礼物,快去看看吧 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="hdjf">
<li><a href="../home/home.action?viewUserid=${f.desuser.userid}">${f.desuser.userbase.username}</a>${f.desuser.userorrole.userrole.rolename} 做了<a href=${f.noteurl}>${f.notename}</a>，获得100积分 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="cytp">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 参与了" <a href=${f.noteurl}>${f.notename}</a>"的投票 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="awtw">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 发起了"<a href=${f.noteurl}>${f.notename}</a>"的问题 <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="awjd">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 参与了"<a href=${f.noteurl}>${f.notename}</a>"的解答:${f.summary} <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="fqtp">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename}发起了投票: "<a href=${f.noteurl}>${f.notename}</a>",大家快去投一票吧！！ <span>${jt.getSub(f.createtime)}</span></li>
<#elseif f.appaction.moduledir=="fx">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 对<a href=${f.noteurl}>${f.notename}</a>进行了分享 <span>${jt.getSub(f.createtime)}</span> </li>

<#elseif f.appaction.moduledir=="fxpr">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 对<a href=${f.noteurl}>${f.notename}</a>的分享进行了评论，评论的内容为"<${f.notename}>" <span>${jt.getSub(f.createtime)}</span></li>

<#elseif f.appaction.moduledir=="fbht">
<li><a href="../home/home.action?viewUserid=${f.srcuser.userid}">${f.srcuser.userbase.username}</a>${f.srcuser.userorrole.userrole.rolename} 发表了"<a href=${f.noteurl}>${f.notename}</a>"的话题，大家去评论评论吧 <span>${jt.getSub(f.createtime)}</span> </li>

</#if>

</#escape> 
 </#macro>             