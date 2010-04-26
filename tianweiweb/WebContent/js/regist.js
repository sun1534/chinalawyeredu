$(document).ready(function(){
	var options={success:showResponse};
	$("#regbtn").click(function(){
//		if($.trim($("#question").val())==""){
//			alert("请输入安全问题！");
//			return;
//		}
		if($.trim($("#answer").val())==""){
			alert("请输入安全问题答案！");
			return;
		}
		if(!isEmail($("#email").val())){
			alert("请输入正确的 邮箱！");
			return;
		}
		$("#regbtn").attr("disabled", true);
		$("#form1").ajaxSubmit(options);
	});
});
function showResponse(data){
	$("#regbtn").attr("disabled", false);
	$.blockUI({ message: data });
}


function checkIDCard (str)
{ //身份证正则表达式(15位)
 isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
 //身份证正则表达式(18位)
 isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
 //验证身份证，返回结果
 return (isIDCard1.test(str)||isIDCard2.test(str));
} 

function isEmail(str){
   var reg = /^([a-zA-Z0-9_-].{0,1})+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return reg.test(str);
}

function isUsername(str){
    var reg = /^([a-zA-Z0-9_-])+$/;
    return reg.test(str);
}

function checkusername(){
	if(!isUsername($("#username").val())){
		$('#nametip').html("<font color='red'>用户名必须是字母或和数字的组合</font>");
		return false;
	}
	$.ajax({
	    type: "POST",
	    data:"type=1&username="+$("#username").val(),
	    url:"../regist/checkdata.action",
	    success:function(data){
			$('#nametip').html(data);
	    }
	});
}
function checkpwd(){
	if($("#password").val().length<6){
		$('#pwdtip').html("<font color='red'>输入有误，密码请输入6~13个字符。</font>");
	}else{
		$('#pwdtip').html("<font color='#CBCBCB'>ok</font>");
	}
}
function checkrepwd(){
	if($("#repassword").val()==""){
	}else if($("#password").val()!=$("#repassword").val()){
		$('#repwdtip').html("<font color='red'>2次密码输入不同。</font>");
	}else{
		$('#repwdtip').html("<font color='#CBCBCB'>ok</font>");
	}
}