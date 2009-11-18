function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(obj){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=obj.checked;
               }
          }else{
               document.form1.check.checked=obj.checked;
          }
     }
}
function isEmpty(s) {
	return ((s == null) || (s.length == 0))
}
function getStringLength(str) {
	var num = 0;
	if (str != "") {
		var i;
		var s;
		for (i = 0; i < str.length; i++) {
			s = str.charCodeAt(i);
			if (s - 128 < 0)
				num = num + 1;
			else
				num = num + 2;
		}
	}
	return num;
}
function isInBag(s, bag) {
	var i;
	for (i = 0; i < s.length; i++) {
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1)
			return false;
	}
	return true;
}
function isNotInBag(s, bag) {
	var i;
	for (i = 0; i < s.length; i++) {
		var c = s.charAt(i);
		if (bag.indexOf(c) > -1)
			return false;
	}
	return true;
}
function isGoodChar(str) {
	if (isNotInBag(str, " -_.><,[]{}?/+=|\\'\":;~!@#$%^&()`"))
		return true;
	else
		return false;
}
function isNumber(str) {
	if (isInBag(str, "0123456789"))
		return true;
	else
		return false;
}
function isLetter(str) {
	if (isInBag(str, "~!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"))
		return true;
	else
		return false;
}
function isLetterNumber(str) {
	if (isInBag(str,
			"~!@#$%^&*()0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"))
		return true;
	else
		return false;
}
function isEmail(email) {
	var reEmail = /^\w([A-Za-z0-9_-]|\.|\+)*@(\w)+(\.)(com|com\.cn|net|cn|net\.cn|org|biz|info|gov|gov\.cn|edu|end\.cn)$/i;
	if (!email.match(reEmail) || email.length == 0)
		return false;
	else
		return true;
}
function LTrim(str) {
	var whitespace = new String(" \t\n\r");
	
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		var j = 0, i = s.length;
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
			j++;
		}
		s = s.substring(j, i);
	}
	return s;
}
function RTrim(str) {
	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		var i = s.length - 1;
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) {
			i--;
		}
		s = s.substring(0, i + 1);
	}
	return s;
}
function Trim(str) {
	return RTrim(LTrim(str));
}
function xmlEncode(str) {
	str = Trim(str);
	str = str.replace("&", "&amp;");
	str = str.replace("<", "&lt;");
	str = str.replace(">", "&gt;");
	str = str.replace("'", "&apos;");
	str = str.replace("\"", "&quot;");
	return str;
}

function isChinaMobile(mobile) {
	var reChinaMobile = /^((13[4-9])|(159|158))[0-9]{8}/;
	if (mobile.length == 0 || !mobile.match(reChinaMobile))
		return false;
	else
		return true;
}

function isChinaUnicom(mobile) {
	var reChinaMobile = /^((13[0-3])|(153))[0-9]{8}/;
	if (mobile.length == 0 || !mobile.match(reChinaUnicom))
		return false;
	else
		return true;
}

//测试值是否存在,在添加数据时使用，弃用，改checkUserByMobileAndRoleId(mobile,roleid)
function checkCreatename(columnName,columnValue){	
	var _url="../commonajax/checkCreateName.action?createType=com.sxit.models.webuser.Usercontact&propertyName="+encodeURI(columnName)+"&columnValue="+encodeURI(columnValue);
	$.ajax({
          type:"post",
          url:_url,
          async:true,
          success:function(xml){
		  var res=eval('(' + xml + ')');
   if(res.count !=0){
   		$("#checkname").html("对不起，您输入的【"+decodeURI(columnValue)+"】已经被他人使用，请选择其他名字后再试.");
   		$("#save").attr("disabled", true);
   }else{
		$("#save").attr("disabled", false);
		$("#checkname").html("");
   }}
		  });
}

//测试值是否数据大于1，在修改数据时使用，弃用，改checkUserByMobileAndRoleId(mobile,roleid)
function checkCreatename(columnName,columnValue){
	if(columnValue==nameBefore)
		return;
	var _url="../commonajax/checkCreateName.action?createType=com.sxit.models.webuser.Usercontact&propertyName="+encodeURI(columnName)+"&columnValue="+encodeURI(columnValue);
	$.ajax({
          type:"post",
          url:_url,
          async:true,
          success:function(xml){
		  var res=eval('(' + xml + ')');
   if(res.count !=0){
   		$("#checkname").html("对不起，您输入的【"+decodeURI(columnValue)+"】已经被他人使用，请选择其他名字后再试.");
   		$("#save").attr("disabled", true);
   }else{
		$("#save").attr("disabled", false);
		$("#checkname").html("");
   }}
		  });
}


var nameBefore;
$(document).ready(function(){
	 nameBefore = $("#beforeCheck").val();
	});

//测试指定表中指定列的指定值是否已存在，弃用，改checkUserByMobileAndRoleId(mobile,roleid)
function checkCreatename(tableName,columnName,columnValue){
	if(columnValue==nameBefore){
		return;
	}
	var _url="../commonajax/checkCreateName.action?createType="+encodeURI(tableName)+"&propertyName="+encodeURI(columnName)+"&columnValue="+encodeURI(columnValue);
	$.ajax({
          type:"post",
          url:_url,
          async:true,
          success:function(xml){
		  var res=eval('(' + xml + ')');
   if(res.count !=0){
   		$("#checkname").html("对不起，您输入的【"+decodeURI(columnValue)+"】已经被他人使用，请重新输入再试.");
   		$("#save").attr("disabled", true);
   }else{
		$("#save").attr("disabled", false);
		$("#checkname").html("");
   }}
		  });
}


//根据手机号和角色ID检查指定角色的用户是否已存在
function checkUserByMobileAndRoleId(mobile,roleid){
	if(mobile==nameBefore){
		$("#checkname").html("*");
		$("#save").attr("disabled", false);
		return;
	}
	var _url="../commonajax/checkUserByMobileAndRoleId.action?mobile="+mobile+"&roleid="+roleid;
	$.ajax({
          type:"post",
          url:_url,
          async:true,
          success:function(xml){
		  var res=eval('(' + xml + ')');
   if(res.count !=0){
   		$("#checkname").html("对不起，您输入的【"+mobile+"】已经被他人使用，请重新输入再试.");
   		$("#save").attr("disabled", true);
   }else{
		$("#save").attr("disabled", false);
		$("#checkname").html("*");
   }}
		  });
}

