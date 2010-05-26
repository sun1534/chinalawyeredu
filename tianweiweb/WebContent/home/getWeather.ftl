<#escape x as (x)!"">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title></title>
<style type="text/css">
body, p, li, input, h1, h2, h3, td, select, textarea, div
{
	font-family: Tahoma;
	font-size: 12px;
	background-color:transparent;
}
body
{
    overflow:hidden;
	padding: 0;
	margin: 0;
}
a, a:hover
{
	text-decoration: none;
	color: #404082;
}
.forecastDay
{
    	color: #FF0000;
}
.forecastText
{
    	color: #FF0000;
}
.forecast
{
    	color: #FF0000;
}
.forecastIcon
{
    margin:-3px -12px -3px -15px;
    vertical-align: middle;
    border: none;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
function fixPng(a){var b=window.navigator.userAgent.toLowerCase();if(/msie 6/.test(b)){b="display:inline-block;"+a.style.cssText;b='<span id="'+a.id+'" class="'+a.className+'" title="'+a.title+'" style="width:'+a.clientWidth+"px; height:"+a.clientHeight+"px;"+b+";filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+a.src+"', sizingMethod='scale');\"></span>";a.outerHTML=b}};
</script>

</head>

<body >
${weatherContent}

</body>
</html>
</#escape>