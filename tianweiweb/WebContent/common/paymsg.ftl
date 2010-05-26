
<#escape x as (x)!"">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
<title>支付结果-系统提示</title>
<style type="text/css">
* {margin:0;padding:0;font: 12px/1.5 Tahoma, Helvetica, Arial, sans-serif;}
.box-green { border: 1px solid #ccc;padding:10px 10px 10px 45px;background:#F1F1F1 url(http://www.topway-ad.com/images/box_orange.gif) 10px 6px no-repeat; }
.box-green h4 { font-size: 12px; font-weight: bolder;color:#006600}
.box-green .box-content p {}
.box-green .box-content p {line-height:160%;font-weight:normal;color:#000000}

</style>
<script type="text/javascript">
<!--
var time =5; //倒计时的秒数
var URL ;
function LoadUrl(url)
{
URL =url;
for(var i=time;i>=0;i--)
{
window.setTimeout("doUpdate("+ i +")", (time-i) * 1000);
}
}
function doUpdate(num)
{
document.getElementById("url_id").innerHTML = "浏览器将在"+num+"秒后自动跳转到";
 if(num == 0)
 {
    window.location=URL;
 }
}
//-->
</script>
</head>
<body>
<div id="main" class="doc">
	<div class="wrap">
		<div class="bd">
			<div class="box-green">
				<h4>支付提示</h4>
				<div class="box-content">
					<p>${message} 支付成功! <span id="url_id"></span><a href="#">订购管理</a></p>
				</div>
			</div>
		</div>
	</div>
</div>
<script  type="text/javascript">
LoadUrl("http://www.topway-ad.com/progress/progresslist.action");
</script>
</body>
</html>
</#escape>

