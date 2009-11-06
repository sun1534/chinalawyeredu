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

<div id="main">
  <div id="nr-right">
  <form id="form1" name="form1" method="post" action="regist.action">
  <div class="right-top">
  <div class="text">
  <span class="title">用户注册</span>
  </div>
  <div class="content1">
    <div>
<b class="b1"></b><b class="b2 d1" style="background:#d1e2f2"></b><b class="b3 d1" style="background:#d1e2f2"></b><b class="b4 d1" style="background:#d1e2f2"></b>
<div class="b d1" >
<span class="reg-title">账户信息</span>
  
    <table width="500" border="0" cellspacing="10" cellpadding="5">
      <tr>
        <td width="64"><span style="color:red">*</span>帐　号：</td>
        <td width="315"><label>
          <input type="text" name="username" />
        </label></td>
        <td width="21">&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red">*</span>密　码：</td>
        <td><input type="password" name="password" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red">*</span>确认密码：</td>
        <td><input type="password" name="repassword" /></td>
        <td>&nbsp;</td>
      </tr>
    </table>
      
  </div>
<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
</div>
<div style="height:10px;font-size:1px;"></div>
<div>
<b class="b1"></b><b class="b2 d1" style="background:#d1e2f2"></b><b class="b3 d1" style="background:#d1e2f2"></b><b class="b4 d1" style="background:#d1e2f2"></b>
<div class="b d1" >
<span class="reg-title">安全信息</span>
  
    <table width="500" border="0" cellspacing="10" cellpadding="5">
      <tr>
        <td width="64"><span style="color:red">*</span>密码问题：</td>
        <td width="315"><label>
          <input type="text" name="question" id="question" />
        </label></td>
        <td width="21">&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red">*</span>问题答案：</td>
        <td><input type="text" name="answer" id="answer" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><span style="color:red">*</span>邮箱验证：</td>
        <td><input type="text" name="email" id="email" /></td>
        <td>&nbsp;</td>
      </tr>
    </table>
      
  </div>
<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
</div>
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
<div style="height:10px;font-size:1px;"></div>
<div>
<b class="b1"></b><b class="b2 d1" style="background:#d1e2f2"></b><b class="b3 d1" style="background:#d1e2f2"></b><b class="b4 d1" style="background:#d1e2f2"></b>
<div class="b d1" >
<span class="reg-title">服务条款</span>
  
    <table width="500" height="150" border="0" >
      <tr>
        <td ></td>
        </tr>
      <tr><td><input type="checkbox" name="agreerule" id="agreerule" value="true" />同意</td></tr>
    </table>
      
  </div>
<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
</div>
<div style="height:10px;font-size:1px;"></div>
<div>
<b class="b1"></b><b class="b2 d1" style="background:#d1e2f2"></b><b class="b3 d1" style="background:#d1e2f2"></b><b class="b4 d1" style="background:#d1e2f2"></b>
<div class="b d1" >
<span class="reg-title">扩展信息</span>
<table width="100%" border="0" cellspacing="10" cellpadding="5">
  <tr>
    <td><span style="color:red">*</span>身份证：</td>
    <td><input type="text" name="cardno" id="cardno" /></td>
  </tr>
  <tr>
    <td><span style="color:red">*</span>姓名：</td>
    <td><input type="text" name="name" id="name" /></td>
  </tr>
  <tr>
    <td>住　址：</td>
    <td><input type="text" name="address" /></td>
  </tr>
  <tr>
    <td>邮　编：</td>
    <td><input type="text" name="postcode" /></td>
  </tr>
  <tr>
    <td>电　话：</td>
    <td><input type="text" name="phone" /></td>
  </tr>
  <tr>
    <td>QQ：</td>
    <td><input type="text" name="qq" /></td>
  </tr>
  <tr>
    <td>MSN：</td>
    <td><input type="text" name="msn" /></td>
  </tr>
</table>
</div>
<b class="b4b d1"></b><b class="b3b d1"></b><b class="b2b d1"></b><b class="b1b"></b>
</div>
<div style="height:80px;font-size:16px;text-align:center;">
  <label><br />
  <input type="button" id="regbtn" value=" 提 交 " />　 
  </label>
   <input type="reset" value=" 取 消 " />
</div>
  </div>
  </div>
  </form>
  </div>
  <div class="clear"></div>
</@home.home>
</#escape>