<#escape x as (x)!"">
<#import "../common/home.ftl" as home>
<@home.home myheader="user/user_h.ftl">
<script type="text/javascript" src="${staticpath}/js/location.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function HS_DateAdd(interval,number,date){
	number = parseInt(number);
	if (typeof(date)=="string"){var date = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2])}
	if (typeof(date)=="object"){var date = date}
	switch(interval){
	case "y":return new Date(date.getFullYear()+number,date.getMonth(),date.getDate()); break;
	case "m":return new Date(date.getFullYear(),date.getMonth()+number,checkDate(date.getFullYear(),date.getMonth()+number,date.getDate())); break;
	case "d":return new Date(date.getFullYear(),date.getMonth(),date.getDate()+number); break;
	case "w":return new Date(date.getFullYear(),date.getMonth(),7*number+date.getDate()); break;
	}
}
function checkDate(year,month,date){
	var enddate = ["31","28","31","30","31","30","31","31","30","31","30","31"];
	var returnDate = "";
	if (year%4==0){enddate[1]="29"}
	if (date>enddate[month]){returnDate = enddate[month]}else{returnDate = date}
	return returnDate;
}

function WeekDay(date){
	var theDate;
	if (typeof(date)=="string"){theDate = new Date(date.split("-")[0],date.split("-")[1],date.split("-")[2]);}
	if (typeof(date)=="object"){theDate = date}
	return theDate.getDay();
}
function HS_calender(){
	var lis = "";
	var style = "";
	/* http://www.codefans.net */
	style +="<style type='text/css'>";
	style +=".calender { width:170px; height:auto; font-size:12px; margin-right:14px; background:url(calenderbg.gif) no-repeat right center #fff; border:1px solid #397EAE; padding:1px}";
	style +=".calender ul {list-style-type:none; margin:0; padding:0;}";
	style +=".calender .day { background-color:#EDF5FF; height:20px;}";
	style +=".calender .day li,.calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
	style +=".calender li a { text-decoration:none; font-family:Tahoma; font-size:11px; color:#333}";
	style +=".calender li a:hover { color:#f30; text-decoration:underline}";
	style +=".calender li a.hasArticle {font-weight:bold; color:#f60 !important}";
	style +=".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
	style +=".selectThisYear a, .selectThisMonth a{text-decoration:none; margin:0 2px; color:#000; font-weight:bold}";
	style +=".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
	style +=".calender .LastMonth { float:left;}";
	style +=".calender .NextMonth { float:right;}";
	style +=".calenderBody {clear:both;z-index:99}";
	style +=".calenderTitle {text-align:center;height:20px; line-height:20px; clear:both}";
	style +=".today { background-color:#ffffaa;border:1px solid #f60; padding:2px}";
	style +=".today a { color:#f30; }";
	style +=".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:left}";
	style +=".calenderBottom a {text-decoration:none; margin:2px !important; font-weight:bold; color:#000}";
	style +=".calenderBottom a.closeCalender{float:right}";
	style +=".closeCalenderBox {float:right; border:1px solid #000; background:#fff; font-size:9px; width:11px; height:11px; line-height:11px; text-align:center;overflow:hidden; font-weight:normal !important}";
	style +="</style>";

	var now;
	if (typeof(arguments[0])=="string"){
		selectDate = arguments[0].split("-");
		var year = selectDate[0];
		var month = parseInt(selectDate[1])-1+"";
		var date = selectDate[2];
		now = new Date(year,month,date);
	}else if (typeof(arguments[0])=="object"){
		now = arguments[0];
	}
	var lastMonthEndDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+now.getMonth()+"-01").getDate();
	var lastMonthDate = WeekDay(now.getFullYear()+"-"+now.getMonth()+"-01");
	var thisMonthLastDate = HS_DateAdd("d","-1",now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-01");
	var thisMonthEndDate = thisMonthLastDate.getDate();
	var thisMonthEndDay = thisMonthLastDate.getDay();
	var todayObj = new Date();
	today = todayObj.getFullYear()+"-"+todayObj.getMonth()+"-"+todayObj.getDate();
	
	for (i=0; i<lastMonthDate; i++){  // Last Month's Date
		lis = "<li class='lastMonthDate'>"+lastMonthEndDate+"</li>" + lis;
		lastMonthEndDate--;
	}
	for (i=1; i<=thisMonthEndDate; i++){ // Current Month's Date

		if(today == now.getFullYear()+"-"+now.getMonth()+"-"+i){
			var todayString = now.getFullYear()+"-"+(parseInt(now.getMonth())+1).toString()+"-"+i;
			lis += "<li><a href=javascript:void(0) class='today' onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
		}else{
			lis += "<li><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+now.getFullYear()+"-"+(parseInt(now.getMonth())+1)+"-"+i+"'>"+i+"</a></li>";
		}
		
	}
	var j=1;
	for (i=thisMonthEndDay; i<6; i++){  // Next Month's Date
		lis += "<li class='nextMonthDate'>"+j+"</li>";
		j++;
	}
	lis += style;

	var CalenderTitle = "<a href='javascript:void(0)' class='NextMonth' onclick=HS_calender(HS_DateAdd('m',1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Next Month'>&raquo;</a>";
	CalenderTitle += "<a href='javascript:void(0)' class='LastMonth' onclick=HS_calender(HS_DateAdd('m',-1,'"+now.getFullYear()+"-"+now.getMonth()+"-"+now.getDate()+"'),this) title='Previous Month'>&laquo;</a>";
	CalenderTitle += "<span class='selectThisYear'><a href='javascript:void(0)' onclick='CalenderselectYear(this)' title='Click here to select other year' >"+now.getFullYear()+"</a></span>年<span class='selectThisMonth'><a href='javascript:void(0)' onclick='CalenderselectMonth(this)' title='Click here to select other month'>"+(parseInt(now.getMonth())+1).toString()+"</a></span>月"; 

	if (arguments.length>1){
		arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
		arguments[1].parentNode.innerHTML = CalenderTitle;

	}else{
		var CalenderBox = style+"<div class='calender'><div class='calenderTitle'>"+CalenderTitle+"</div><div class='calenderBody'><ul class='day'><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class='date' id='thisMonthDate'>"+lis+"</ul></div><div class='calenderBottom'><a href='javascript:void(0)' class='closeCalender' onclick='closeCalender(this)'>×</a><span><span><a href=javascript:void(0) onclick='_selectThisDay(this)' title='"+todayString+"'>Today</a></span></span></div></div>";
		return CalenderBox;
	}
}
function _selectThisDay(d){
	var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
		boxObj.targetObj.value = d.title;
		boxObj.parentNode.removeChild(boxObj);
}
function closeCalender(d){
	var boxObj = d.parentNode.parentNode.parentNode;
		boxObj.parentNode.removeChild(boxObj);
}

function CalenderselectYear(obj){
		var opt = "";
		var thisYear = obj.innerHTML;
		for (i=1970; i<=2020; i++){
			if (i==thisYear){
				opt += "<option value="+i+" selected>"+i+"</option>";
			}else{
				opt += "<option value="+i+">"+i+"</option>";
			}
		}
		opt = "<select onblur='selectThisYear(this)' onchange='selectThisYear(this)' style='font-size:11px'>"+opt+"</select>";
		obj.parentNode.innerHTML = opt;
}

function selectThisYear(obj){
	HS_calender(obj.value+"-"+obj.parentNode.parentNode.getElementsByTagName("span")[1].getElementsByTagName("a")[0].innerHTML+"-1",obj.parentNode);
}

function CalenderselectMonth(obj){
		var opt = "";
		var thisMonth = obj.innerHTML;
		for (i=1; i<=12; i++){
			if (i==thisMonth){
				opt += "<option value="+i+" selected>"+i+"</option>";
			}else{
				opt += "<option value="+i+">"+i+"</option>";
			}
		}
		opt = "<select onblur='selectThisMonth(this)' onchange='selectThisMonth(this)' style='font-size:11px'>"+opt+"</select>";
		obj.parentNode.innerHTML = opt;
}
function selectThisMonth(obj){
	HS_calender(obj.parentNode.parentNode.getElementsByTagName("span")[0].getElementsByTagName("a")[0].innerHTML+"-"+obj.value+"-1",obj.parentNode);
}
function HS_setDate(inputObj){
	var calenderObj = document.createElement("span");
	calenderObj.innerHTML = HS_calender(new Date());
	calenderObj.style.position = "absolute";
	calenderObj.targetObj = inputObj;
	inputObj.parentNode.insertBefore(calenderObj,inputObj.nextSibling);
}
  </script>
<div class="parentsinfo-box in-main">
	<div class="title-h">
		<h3>我的资料</h3>
	</div>
	<div class="title-parentsinfo">
		<div class="title-info">
			<div class="m-tag">
				<ul id="partents-mintag">
					<li ><a href="userbaseview.action" title="基本资料"><span>基本资料</span></a></li>
						<#if currentRole=1>
					<li><a title="更新头像" href="userimages!input.action"><span>更新头像</span></a></li>	
						</#if>
					<#if currentRole=2>
					<li><a title="企业形象" href="userimages!input.action"><span>企业形象</span></a></li>	
					<li><a title="更新LOGO" href="userlogo.action"><span>更新LOGO</span></a></li>
					</#if>
					<li><a title="密码修改" href="userpassword.action"><span>密码修改</span></a></li>
					<li><a title="安全资料" href="safepwd!input.action"><span>安全资料</span></a></li>
					<li class="current"><a title="认证管理" href="userverify.action"><span>认证管理</span></a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="parentsminbox">
	<div class="baseadmininfo">
     <form id="form1" method="post" action="userbaseupdate.action">
		<div id="baseadmininfo" class="form" >
		<div class="odd">
			<label class="fname" for="pname">当前状态：</label>
			<span class="fvalue">
			<#if status=0><b class="red">通过认证</b></#if>
			<#if status=1><b class="red">待认证</b></#if>
			<#if status=2><b class="red">已提交认证申请,待审核</b></#if>
			<#if status=3><b class="red">认证未通过</b></#if></span>
		</div>
		<div class="even">
			<label class="fname" for="pname"><#if currentRole=1>姓名</#if><#if currentRole=2>法人代表</#if>：</label>
			<span class="fvalue"><input type="text" id="username" name="username" size="15" value="${username}" class="normal txt-login w200"/><font color="red">*</font></span>	
		</div>
		
		<#if currentRole=1>
		<div class="odd">
			<label class="fname" for="pname">性别：</label>
			<span class="fvalue">
			<#if sex==1>
			    <label for="pname"> <input name="sex" type="radio" class="inputxt" value="1" checked/>男</label>
			    <label for="pname"> <input name="sex" type="radio" class="inputxt" value="2"/>女</label>
			<#else>
				<label for="pname">	<input name="sex" type="radio" class="inputxt" value="1"/>男</label>
				<label for="pname">	<input name="sex" type="radio"   class="inputxt" value="2" checked/>女</label>
			</#if>
			</span>
		</div>


		<div class="even">
			<label class="fname" for="pname">生日：</label>
			<span class="fvalue">
				<!--<input type="text" name="birth" size="15" value="${birth?string("yyyy-MM-dd")}" id="dateinput" class="normal txt-login w90"/>-->
				<input type="text" name="birth" size="15" value="${birth?string("yyyy-MM-dd")}" id="dateinput1"  onfocus="HS_setDate(this)"  class="normal txt-login w90"/>
		<font color="red">*</font>
			</span>	
		</div>
		</#if>
		<#if currentRole=2>
		<div class="even">
			<label class="fname" for="pname">公司名称：</label>
			<span class="fvalue"><input id="sign" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="sign" size="20" value="${sign}" class="normal txt-login w200"/>
			
				<font color="red">*</font>
			</span>
		</div>
		</#if>
		<div class="odd">
			<label class="fname" for="pname">地址：</label>
			<span class="fvalue"><input type="text" name="address" size="15" value="${address}" class="normal txt-login w300" /><font color="red">*</font></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">邮编：</label>
			<span class="fvalue"><input type="text" name="postcode" size="15" value="${postcode}" class="normal txt-login w200"/></span>
		</div>
		<div class="odd">
			<label class="fname" for="pname">电话：</label>
			<span class="fvalue"><input id="phone" type="text" name="phone" size="15" value="${phone}" class="normal txt-login w200"/>	<font color="red">*</font></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">身份证号码：</label>
			<span class="fvalue"><input id="cardno" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="cardno" size="15" value="${cardno}" class="normal txt-login w200"/><font color="red">*</font></span>	
		</div>
		<#if currentRole=2>
		<div class="even">
			<label class="fname" for="pname">营业执照号：</label>
			<span class="fvalue"><input id="entno" <#if status=0>readonly="true"</#if><#if status=2>readonly="true"</#if> type="text" name="entno" size="15" value="${entno}" class="normal txt-login w200"/><font color="red">*</font></span>	
		</div>
		</#if>
		<div class="odd">
			<label class="fname" for="pname">QQ：</label>
			<span class="fvalue"><input type="text" name="qq" size="15" value="${qq}" class="normal txt-login w200"/></span>
		</div>
		<div class="even">
			<label class="fname" for="pname">MSN：</label>
			<span class="fvalue"><input type="text" name="msn" size="15" value="${msn}" class="normal txt-login w200"/></span>
		</div>


		<div class="formbtn">
		<label class="fname" for="cname"></label>
		<#if status=3>
				<span class="fvalue">
		<input class="delBtn igreen "   id="saveBtn11" onclick="javascript:applyverify()" stype="button" title="申请认证" value="申请认证"/>
		</span>
		</#if>
			<#if status=1>
				<span class="fvalue">
		<input class="delBtn igreen "   id="saveBtn11" onclick="javascript:applyverify()" stype="button" title="申请认证" value="申请认证"/>
		</span>
		</#if>
		</div>
	</div>
	</div>
	</form>
	</div>
	</div>
<div class="clear"></div>
<script>
function applyverify(){
	<#if currentRole=1>
	if($("#username").val()==""){
		alert("请先输入用户名");
	}
	else if(!checkIDCard($("#cardno").val())){
		alert("请先输入正确的身份证号码和生日");
	}
	</#if>
	   <#if currentRole=2>
	   if($("#username").val()==""){
		alert("请先输入企业法人代表名称,不能为空");
	    }	   
	    else if($("#sign").val()==""){
		alert("请先输入企业名称,不能为空");
	    }
	    else if($("#entno").val()==""){
		alert("请输入企业营业执照号,不能为空");
	    }
	    	  else if($("#cardno").val()==""){
		alert("请输入企业法人身份证号码,不能为空");
	    }
	    	
	</#if>
	else if($("#address").val()==""){
		alert("请输入您或您公司的地址,不能为空");
	}
	else if($("#phone").val()==""){
		alert("请输入您的联系电话,不能为空");
	}else{
		$("#form1").ajaxSubmit({
		 success:function(data){
		 	$.ajax({
		    type: "POST",
		    data:"",
		    url:"verifyapply.action",
		    success:function(d1){
		    	$.blockUI({message:d1});
	        }})
	 	 }
	 });
	 /*
		$.ajax({
		    type: "POST",
		    data:"",
		    url:"verifyapply.action",
		    success:function(data){
		    	$.blockUI({message:data});
	    }});
	 */
    }
}

function checkPhone (str){
	isPhone=/\d{7,15}/;
	return (isPhone.test(str));
}
function checkIDCard (str)
{ //身份证正则表达式(15位)
 isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
 //身份证正则表达式(18位)
 isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
 //验证身份证，返回结果
 
 <#if currentRole=1>
 var birthday=$("#dateinput1").val();
 if(str.length==15){
 	if(birthday!="19"+str.substring(6,8)+"-"+str.substring(8,10)+"-"+str.substring(10,12))
 		return false;
 }else if(str.length==18){
 	if(birthday!=str.substring(6,10)+"-"+str.substring(10,12)+"-"+str.substring(12,14))
 		return false;
 }else{
 	return false;
 }
 </#if>
 return (isIDCard1.test(str)||isIDCard2.test(str));
}
//function submita(){
//	if($("#username").val()==""){
//		alert("请先输入用户名");
//	}else if(!checkIDCard($("#cardno").val())){
//		alert("请先输入正确的身份证号码和生日");
//	}else if($("#phone").val()==""){
//		alert("请先输入联系电话");
//	}else{
//		var options={success:showResponse};
//		$("#form1").ajaxSubmit(options);
//	}
//}
</script>

</@home.home>
</#escape>
