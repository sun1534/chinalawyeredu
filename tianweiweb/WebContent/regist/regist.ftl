<#import "../common/homenoleft.ftl" as home>
<#escape x as (x)!"">
<@home.home>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/base/jquery.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.form.js" language="javascript" type="text/javascript"></script>
<script src="../js/base/jquery.blockUI.js" language="javascript" type="text/javascript"></script>
<script src="../js/home.js" language="javascript" type="text/javascript"></script>
<script src="../js/regist.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
<div class="navguild"><span><a href="../home/home.action">首页</a>->用户注册</span></div>
<div class="intro">
	<div class="introl"><img src="../images/law.jpg"  /></div>
    <div class="regmain">
	<div class="intror"><div class="introbanal">账户信息</div></div>
	<form id="form1" name="form1" method="post" action="regist.action">
		<table border="0" cellspacing="10" cellpadding="5">
		  <tr>
			<td width="68"><span style="color:red">*</span>用 户 名：</td>
			<td width="315">
			  <input class="regtxt" type="text" name="username" id="username" onfocus="$('#nametip').show()" onblur="checkusername()" />
			<span id="nametip"><font color="green">账号请输入6~20个字符</font></span></td>
			<td width="21"></td>
		  </tr>
		  <tr>
			<td><span style="color:red">*</span>密　　码：</td>
			<td><input  class="regtxt"  type="password" name="password" id="password" onfocus="$('#pwdtip').show()" onblur="checkpwd()" />
				<span id="pwdtip"><font color="green">密码请输入6~13个字符</font></span>
			</td>
			<td>&nbsp;</td>
		  </tr>
		  <tr>
			<td><span style="color:red">*</span>确认密码：</td>
			<td><input class="regtxt"  type="password" name="repassword" id="repassword" onblur="checkrepwd()" />
				<span id="repwdtip"><font color="green">密码请输入6~13个字符</font></span>
			</td>
			<td>&nbsp;</td>
		  </tr>
		</table>
	<div class="intror"><div class="introbanal">安全信息</div></div>
   	 <table border="0" cellspacing="10" cellpadding="5">
      <tr>
        <td width="68"><span style="color:red">*</span>密码问题：</td>
        <td width="315"><label>
          <select name="question" style="width:200px;">
			<option value="我最向往的城市？">我最向往的城市？</option>
			<option value="我的所读的大学？">我的所读的大学？</option>
			<option value="我的一个重要的纪念日？">我的一个重要的纪念日？</option>
		  </select>
        </label></td>
        <td width="21">&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red">*</span>问题答案：</td>
        <td><input type="text" name="answer" id="answer"   class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red">*</span>邮箱验证：</td>
        <td><input type="text" name="email" id="email"   class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
    </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><label>
    <input type="radio" name="role"  value="2" checked="checked"/>
    </label></td>
    <td><label><img src="../images/regsiter_03.gif" width="241" height="58" /></label></td>
    <td><input type="radio" name="role"  value="1" checked="checked"/></td>
    <td><img src="../images/regsiter_05.gif" width="251" height="58" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<div class="intror"><div class="introbanal">服务条款</div></div>
    <div>
    <textarea readonly="true"  style="width:600px;height:150px">
            1、服务条款的确认和接纳
    www.topway.cn服务的所有权和运作权归天威广告网络所有。所提供的服务必须按照其发布的公司章程，服务条款和操作规则严格执行。考虑到天威广告网络产品服务的重要性，用户同意：
    （1）提供及时、详尽及准确的个人资料。
    （2）不断更新注册资料，符合及时、详尽准确的要求。所有原始键入的资料将引用为注册资料。
    如果用户提供的资料不准确，不真实，不合法有效，天威广告网络保留结束用户使用天威广告网络各项服务的权利。用户在享用天威广告网络各项服务的同时，同意接受天威广告网络提供的各类信息服务。

    2、服务条款的修改
    天威广告网络会在必要时修改服务条款，天威广告网络服务条款一旦发生变动，公司将会在用户进入下一步使用前的页面提示修改内容。如果您同意改动，则再一次激活"我接受"按钮。如果您不接受，则及时取消您的用户使用服务资格。用户要继续使用天威广告网络各项服务需要两方面的确认
    （1）首先确认天威广告网络服务条款及其变动。
    （2）同意接受所有的服务条款限制。

    3、服务修订
    天威广告网络保留随时修改或中断服务而不需通知用户的权利。用户接受天威广告网络行使修改或中断服务的权利，天威广告网络不需对用户或第三方负责。

    4、用户隐私制度
    尊重用户个人隐私是天威广告网络的一项基本政策。所以，作为对以上第二点个人注册资料分析的补充，天威广告网络一定不会公开、编辑或透露用户的注册资料及保存在天威广告网络各项服务中的非公开内容，除非天威广告网络在诚信的基础上认为透露这些信息在以下几种情况是必要的：
    （1）遵守有关法律规定，包括在国家有关机关查询时，提供用户在天威广告网络的网页上发布的信息内容及其发布时间、互联网地址或者域名。
    （2）遵从天威广告网络产品服务程序。
    （3）保持维护天威广告网络的商标所有权。
    （4）在紧急情况下竭力维护用户个人和社会大众的隐私安全。
    （5）根据第9条的规定或者天威广告网络认为必要的其他情况下。
    用户在此授权天威广告网络可以向其电子邮箱发送商业信息。

    5、用户的帐号、密码和安全性
    您一旦注册成功成为用户，您将得到一个密码和帐号。如果您未保管好自己的帐号和密码而对您、天威广告网络或第三方造成的损害，您将负全部责任。另外，每个用户都要对其帐户中的所有活动和事件负全责。您可随时改变您的密码和图标，也可以结束旧的帐户重开一个新帐户。用户同意若发现任何非法使用用户帐号或安全漏洞的情况，立即通告天威广告网络。

    6、拒绝提供担保和免责声明
    用户明确同意使用天威广告网络服务的风险由用户个人承担。服务提供是建立在免费的基础上。天威广告网络明确表示不提供任何类型的担保，不论是明确的或隐含的，但是对商业性的隐含担保，特定目的和不违反规定的适当担保除外。天威广告网络不担保服务一定能满足用户的要求，也不担保服务不会受中断，对服务的及时性、安全性、真实性、出错发生都不作担保。天威广告网络拒绝提供任何担保，包括信息能否准确、及时、顺利地传送。用户理解并接受下载或通过天威广告网络产品服务取得的任何信息资料取决于用户自己，并由其承担系统受损、资料丢失以及其它任何风险。天威广告网络对在服务网上得到的任何商品购物服务、交易进程、招聘信息，都不作担保。用户不会从天威广告网络收到口头或书面的意见或信息，天威广告网络也不会在这里作明确担保。

    7、有限责任
    天威广告网络对直接、间接、偶然、特殊及继起的损害不负责任，这些损害来自：不正当使用产品服务，在网上购买商品或类似服务，在网上进行交易，非法使用服务或用户传送的信息有所变动。这些损害会导致天威广告网络形象受损，所以天威广告网络早已提出这种损害的可能性。

    8、天威广告网络虚拟社区信息的储存及限制
    天威广告网络不对用户所发布信息的删除或储存失败负责。天威广告网络保留判定用户的行为是否符合天威广告网络虚拟社区服务条款的要求和精神的权利，如果用户违背了服务条款的规定，则中断其虚拟社区服务的帐号。本社区内所有的文章版权归原文作者和天威广告网络共同所有，任何人需要转载社区内文章，必须征得原文作者或天威广告网络授权。

    9、用户管理
    用户单独承担发布内容的责任。用户对服务的使用是根据所有适用于服务的地方法律、国家法律和国际法律标准的。用户承诺：
    （1）在天威广告网络的网页上发布信息或者利用天威广告网络的服务时必须符合中国有关法规(部分法规请见附录)，不得在天威广告网络的网页上或者利用天威广告网络的服务制作、复制、发布、传播以下信息：
    (a)反对宪法所确定的基本原则的；
    (b) 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；
    (c) 损害国家荣誉和利益的；
    (d) 煽动民族仇恨、民族歧视，破坏民族团结的；
    (e) 破坏国家宗教政策，宣扬邪教和封建迷信的；
    (f) 散布谣言，扰乱社会秩序，破坏社会稳定的；
    (g) 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；
    (h) 侮辱或者诽谤他人，侵害他人合法权益的；
    (i) 含有法律、行政法规禁止的其他内容的。
    （2）在天威广告网络的网页上发布信息或者利用天威广告网络的服务时还必须符合其他有关国家和地区的法律规定以及国际法的有关规定。
    （3）不利用天威广告网络的服务从事以下活动：
    (a) 未经允许，进入计算机信息网络或者使用计算机信息网络资源的；
    (b) 未经允许，对计算机信息网络功能进行删除、修改或者增加的；
    (c) 未经允许，对进入计算机信息网络中存储、处理或者传输的数据和应用程序进行删除、修改或者增加的；
    (d) 故意制作、传播计算机病毒等破坏性程序的；
    (e) 其他危害计算机信息网络安全的行为。
    （4）不以任何方式干扰天威广告网络的服务。
    （5）遵守天威广告网络的所有其他规定和程序。
    用户需对自己在使用天威广告网络服务过程中的行为承担法律责任。用户理解，如果天威广告网络发现其网站传输的信息明显属于上段第(1)条所列内容之一，依据中国法律，天威广告网络有义务立即停止传输，保存有关记录，向国家有关机关报告，并且删除含有该内容的地址、目录或关闭服务器。
    用户使用天威广告网络电子公告服务，包括电子布告牌、电子白板、电子论坛、网络聊天室和留言板等以交互形式为上网用户提供信息发布条件的行为，也须遵守本条的规定以及天威广告网络将专门发布的电子公告服务规则，上段中描述的法律后果和法律责任同样适用于电子公告服务的用户。
    若用户的行为不符合以上提到的服务条款，天威广告网络将作出独立判断立即取消用户服务帐号。

    10、结束服务
    用户或天威广告网络可随时根据实际情况中断服务。天威广告网络不需对任何个人或第三方负责而随时中断服务。用户若反对任何服务条款的建议或对后来的条款修改有异议，或对天威广告网络服务不满，用户只有以下的追索权：
    （1）不再使用天威广告网络服务。
    （2）结束用户使用天威广告网络服务的资格。
    （3）通告天威广告网络停止该用户的服务。
    结束用户服务后，用户使用天威广告网络服务的权利马上中止。从那时起，天威广告网络不再对用户承担任何义务。

    11、通告
    所有发给用户的通告都可通过电子邮件或常规的信件传送。天威广告网络会通过邮件服务发报消息给用户，告诉他们服务条款的修改、服务变更、或其它重要事情。

    12、参与广告策划
    在天威广告网络许可下用户可在他们发表的信息中加入宣传资料或参与广告策划，在天威广告网络各项免费服务上展示他们的产品。任何这类促销方法，包括运输货物、付款、服务、商业条件、担保及与广告有关的描述都只是在相应的用户和广告销售商之间发生。天威广告网络不承担任何责任，天威广告网络没有义务为这类广告销售负任何一部分的责任。

    13、内容的所有权
    内容的定义包括：文字、软件、声音、相片、录象、图表；在广告中的全部内容；电子邮件的全部内容；天威广告网络虚拟社区服务为用户提供的商业信息。所有这些内容均受版权、商标、标签和其它财产所有权法律的保护。所以，用户只能在天威广告网络和广告商授权下才能使用这些内容，而不能擅自复制、再造这些内容、或创造与内容有关的派生产品。

    14、法律用户和天威广告网络一致同意有关本协议以及使用天威广告网络的服务产生的争议交由仲裁解决，但是天威广告网络有权选择采取诉讼方式，并有权选择受理该诉讼的有管辖权的法院。若有任何服务条款与法律相抵触，那这些条款将按尽可能接近的方法重新解析，而其它条款则保持对用户产生法律效力和影响。

    15、天威广告网络通行证所含服务的信息储存及安全
    天威广告网络对通行证上所有服务将尽力维护其安全性及方便性，但对服务中出现信息删除或储存失败不承担任何负责。另外我们保留判定用户的行为是否符合天威广告网络服务条款的要求的权利，如果用户违背了通行证服务条款的规定，将会中断其通行证服务的帐号。

    16、青少年用户特别提示
    青少年用户必须遵守全国青少年网络文明公约：
    要善于网上学习，不浏览不良信息；要诚实友好交流，不侮辱欺诈他人；要增强自护意识，不随意约会网友；要维护网络安全，不破坏网络秩序；要有益身心健康，不沉溺虚拟时空。

    计算机信息网络国际联网安全保护管理办法
            </textarea>
    </div>
	<p class="agree"><input type="checkbox" name="agreerule" id="agreerule" value="true" />同意</p>

<div class="intror"><div class="introbanal"><a  onclick="$('#moreinfo').toggle()">扩展信息▼</a></div></div>
    <table width="100%" border="0" cellspacing="10" cellpadding="5" id="moreinfo" style="display:none">
      <tr>
        <td width="68"><span style="color:red"></span>身份证：</td>
        <td width="315"><input type="text" name="cardno" maxlength="18"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red"></span>姓　名：</td>
        <td><input type="text" name="name"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>住　址：</td>
        <td><input type="text" name="address"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>邮　编：</td>
        <td><input type="text" name="postcode"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>电　话：</td>
        <td><input type="text" name="phone"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>　QQ：</td>
        <td><input type="text" name="qq"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>MSN：</td>
        <td><input type="text" name="msn"  class="regtxt"/></td>
        <td>&nbsp;</td>
      </tr>
    </table>
	<p class="regbtns"><input type="button" id="regbtn" value=" 提 交 " class="submitBtn" />&nbsp;&nbsp;<input type="reset" value=" 取 消 " class="cancerBtn"/></p>
    </div>

  </form>
  </div>
  <div class="clear"></div>
</@home.home>
</#escape>