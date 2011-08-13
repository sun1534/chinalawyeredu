 //true为mail
 function ismail(mail)
{
        if(!new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(mail))
		{
			//alert(mess);
			return false;
		}
	return true;
}
//判断字符数是否达到最在限制
function checkStrLen(value,maxLen){
	var str,Num = 0;
	for (var i=0;i<value.length;i++){
		str = value.substring(i,i+1);
		if (str<="~") //判断是否双字节
			Num+=1;
		else
			Num+=2;
	}
	if (Num>=maxLen+1)
	{
	return false;
	}
	else
	{
		return true;
	}
}


//判断是否为空或者全部为空格
function isEmpty(s){
  if((s == null) || (s.length == 0))
	  return true;
  else{
	  for(i=0;i<s.length;i++){
		var c=s.charAt(i);
		if(c!=" ") return false;

	  }
  }
}
//函数名：isNUM
//功能介绍：检查是否为数字
//参数说明：要检查的数字
function isNUM(NUM)
{
	var i,j,strTemp;
	strTemp="0123456789";
	if ( NUM.length== 0)
		return false;
	for (i=0;i<NUM.length;i++)
	{
		j=strTemp.indexOf(NUM.charAt(i));
		if (j==-1)
		{
		//说明有字符不是数字
			return false;
		}
	}
	//说明是数字
	return true;
}

//函数名：isMoblile
//功能介绍：检查是否为电话号码
//参数说明：要检查的字符串
function isMobile (mobile){
	 if(!new RegExp(/^1(3|5|8)\d{9}$/).test(mobile))
		{
			return false;
		}
	return true;
}

//函数名：isTEL
//功能介绍：检查是否为电话号码
//参数说明：要检查的字符串
function isTEL(TEL)
{
	var i,j,strTemp;
	strTemp="0123456789-()# ";
	for (i=0;i<TEL.length;i++)
	{
		j=strTemp.indexOf(TEL.charAt(i));
		if (j==-1)
		{
		//说明有字符不合法
			return false;
		}
	}
	//说明合法
	return true;
}

//获取字符串长度,中文为两个字节
function strlen(str){
	var len;
	var i;
	len = 0;
	for (i=0;i<str.length;i++){
		if (str.charCodeAt(i)>255) len+=2; else len++;
	}
	return len;
}
//取消字符串中的回车换行符
//加入/g是取消字符串中所有replace标记，否则只会替换第一个
function replaceEnter(text){
	var str=text;
	return(str.replace(/\r/g,"").replace(/\n/g,""));
}

//弹出遮罩层 canClose决定能否关闭 位true的话带关闭按钮
function alertDialog(msg,canClose){
	var alertmsg="<div id=\"layer1\" style=\"position: absolute;align:center;width:300px;background-color:#f0f5FF;border: 1px solid #000;z-index: 50;\">";
	alertmsg+="<div id=\"layer1_content\" style=\"background-color:#5588bb;padding:2px;text-align:center;font-weight:bold;color: #FFFFFF;vertical-align:middle;\">";
	if(canClose)
		alertmsg+="<a href=\"javascript:$.layerclose('alertmsg')\" id=\"close\" style=\"float:right;text-decoration:none;color:#FFFFFF;\">[ x ]</a>"
	alertmsg+="提示信息<br/>";
	alertmsg+="</div>";
	alertmsg+="<div id=\"layer1_content\" style=\"padding:10px;background-color:#ADBACE;font:bold\">";
	alertmsg+=msg;		
	alertmsg+="</div>";
	if(canClose)
		alertmsg+="<div style=\"text-align:center;background-color:#ADBACE;padding-bottom:10px\"><input type=\"button\" onclick=\"$.layerclose('alertmsg')\" id=\"closeButton\" name=\"submit\" value=\"确定\" /></div>";		
	alertmsg+="</div>";
	show("alertmsg",alertmsg);	
}

//例如enableDiv("comp_info"); 可将id为comp_info下所有tagname为input的元素设置为disabled
function enableDiv(obj,disabled){
	$("#"+obj+" input").each(function(){this.disabled=disabled;});
}

//图片自适应大小
function resizeImage(img,width){
	var image=new Image(); 
	image.src=img.src; 
	image.onload = function() {
		var temp = image.width;
		img.width = temp = (temp>width)?width:temp;
		img.style.display = "inline";	
		image.onload = null;
		image=null;			
	}	
	
}

//取消双引号
function replaceYH(text){
	var str=text;
	return(str.replace(/&ldquo/g,"").replace(/&rdquo/g,""));
}
//替换空格
function replaceSpace(text){
	var str=text;
	return(str.replace(/&nbsp;/g," "));
}
//将两个或两个以上空格替换成一个空格
function replaceSpace2(text){
	var str=text;
	return(str.replace(/ {2,}/g," "));
}

 function createxmlhttp(){
     var xmlhttp1=null;
     try{
        xmlhttp1=new ActiveXObject("Msxml2.XMLHTTP");
      }catch(e){
         try{
              xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
         }catch(oc){
              xmlhttp1=null;
         }
      }
      if ( !xmlhttp1 && typeof XMLHttpRequest != "undefined" ){
                xmlhttp1=new XMLHttpRequest();
       }
     return xmlhttp1;
}


//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2){
    var _arg1=arg1.toString().replace(/(^\s*)|(\s*$)/g, "").replace(",", ""); 
	
	var _arg2=arg2.toString().replace(/(^\s*)|(\s*$)/g, "").replace(",", ""); 
	
    var r1,r2,m;
    try{r1=_arg1.split(".")[1].length}catch(e){r1=0}
    try{r2=_arg2.split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
	
    return (_arg1*m+_arg2*m)/m;
}
//减法
function accSub(arg1,arg2){
    var _arg1=arg1.toString().replace(/(^\s*)|(\s*$)/g, "").replace(",", ""); 
	
	var _arg2=arg2.toString().replace(/(^\s*)|(\s*$)/g, "").replace(",", ""); 
	
    var r1,r2,m;
    try{r1=_arg1.split(".")[1].length}catch(e){r1=0}
    try{r2=_arg2.split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2));
	
    return (_arg1*m-_arg2*m)/m;
}