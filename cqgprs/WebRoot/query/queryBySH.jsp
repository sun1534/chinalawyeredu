<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-实时查询</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
<style>
.consoleresult{
	float:left;
	background-color:black;
	color:white;
	width:49%;
	height:auto!important;  
	height:420px;  
	min-height:420px;  
}

.queryresult{
	margin-left:50%;
	height:auto!important;  
	height:420px;  
	min-height:420px;  
}

</style>

 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">
 function mobileCheck(str){
	if(!str) return false;
		str=$.trim(str);
	if(str==""){
		return false;
	}
	if(str.length!=11){
  		return false;
	}
  //  var myreg = /^(((13[0-9]{1})|15[0-9]{1}|18[0-9]{1}|)+\d{8})$/;
    var myreg=/^1(3[4-9]|5[012789]|8[78])[0-9]{8}$/;
    if(!myreg.test(str)){
        return false;
    }
    return true;
}

function fanye(str){
  document.form1.firstpage.value="no";

  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  if(checkvalue()){
 	document.form1.firstpage.value="yes";
  	document.form1.resultType.value="excel";
  	document.form1.submit();
  }
}
//得到选择的sgsnid的值
var sgsnids="";
function getCheckedSgsnids(){
	sgsnids="";
	$.each($(":checkbox"),function(){
		if($(this).attr("checked")){
		  sgsnids+=$(this).attr("value")+";";
		}
	});
}
function setheight(){
      $('.eqHeight').css("height",$(document).height());
}
var random="";
var mobile="";
var interval;
var waitinterval;
var waitsecond=0;
function queryit(){
	getCheckedSgsnids();
	if(sgsnids=="")
	 {
	 alert("请选择SGSN");
	 return false;
	 }
	if(checkvalue()){
		//异步的方式调用执行脚本的action
		//1秒钟调用轮询数据产生的action
		 random=new Date().getTime();
		 mobile=$("#mobile").attr("value");
		$("#resultlist").html(""); //清空显示的结果
		 waitinterval=setInterval("waiting()",1000);
		 $("#querybutton").attr("disabled",true);
		 $("#querylist").html("");
		$.ajax({
        	type: "POST",
       	 	url:"../query/queryBySH.action",
       	 	data:"random="+random+"&mobile="+mobile+"&sgsnid="+sgsnids,
        	beforeSend:function(){
        	},
       		success:function(data){
       			setheight();
				clearInterval(waitinterval); 
       			$("#resultlist").html(data);
       			$("#queryhint").text("查询完毕,总共耗时:"+waitsecond+"秒");
       			$("#querybutton").attr("disabled",false); //可以重新进行查询
       			waitsecond=0;
			}
		});
		interval=setInterval("getResults()",1000); //每一秒钟去服务器获取结果数据
	}
}
function waiting(){
	 $("#queryhint").text("正在查询中,请稍等..."+(waitsecond++));
}
//每隔1秒钟获取执行结果
function getResults(){	
	 //setheight();
	$("#querylist").load("../query/shQueryResultView.action?now="+new Date().getTime()+"&random="+random+"&mobile="+mobile);
}
function checkvalue(){
	var apnni=$("#mobile").attr("value");
	
	if(!mobileCheck(apnni)){
		alert("请输入正确的要查询的手机号码,不以86开头");
		return false;
	}
	
	//if(apnni==""||apnni.length==0){
	//	alert("请输入要查询的手机号码,不以86开头");
	//	return false;
	//}
	return true;
}
var traceorstop=1;
function trace(){
	
	getCheckedSgsnids();
	if(sgsnids=="")
	 {
	 alert("请选择SGSN");
	 return false;
	 }
	
	 if(sgsnids.length>9||sgsnids=="SGSNCQ07;"||sgsnids=="SGSNCQ08;"||sgsnids=="SGSNCQ09;"||sgsnids=="SGSNCQ01;")
	 {
	 alert("只能选择1个SGSN并且只能为SGSN2-6");
	 return false;
	 }

	if(checkvalue()){
		 random=new Date().getTime();
		 mobile=$("#mobile").attr("value");
		$("#resultlist").html(""); //清空显示的结果
		 waitinterval=setInterval("waiting()",1000);
		 $("#tracebutton").attr("disabled",true);

		 $("#querylist").html("");
		$.ajax({
        	type: "POST",
       	 	url:"../query/ericssonTrace.action",
       	 	data:"stoporstart="+traceorstop+"&random="+random+"&mobile="+mobile+"&sgsnid="+ sgsnids.substring(0,8),
        	beforeSend:function(){
        	},
       		success:function(data){
       			setheight();
       			clearInterval(waitinterval); 
       			$("#resultlist").html(data);
       			$("#queryhint").text("追踪/停止追踪命令执行完毕,总共耗时:"+waitsecond+"秒");
       			$("#tracebutton").attr("disabled",false); //可以重新进行查询
       					 if(traceorstop==1){
			 $("#tracebutton").attr("value","停止追踪");
		 	 traceorstop=2;	
		 }
		 else{
		 	$("#tracebutton").attr("value","追踪");
		 	traceorstop=1;	
		 }
       			
       			waitsecond=0;
			}
		});
		interval=setInterval("getResults()",1000); //每一秒钟去服务器获取结果数据
	}		
}
function gettracelogs(){
	//也同时
	getCheckedSgsnids();
	if(sgsnids=="")
	 {
	 alert("请选择SGSN");
	 return false;
	 }
	
	 if(sgsnids.length>9||sgsnids=="SGSNCQ07;"||sgsnids=="SGSNCQ08;"||sgsnids=="SGSNCQ09;"||sgsnids=="SGSNCQ01;")
	 {
	 alert("只能选择1个SGSN并且只能为SGSN2-6");
	 return false;
	 }
	if(checkvalue()){
		 random=new Date().getTime();
		 mobile=$("#mobile").attr("value");
		$("#resultlist").html(""); //清空显示的结果
		 waitinterval=setInterval("waiting()",1000);
		 $("#gettracelogsbutton").attr("disabled",true);
		 $("#querylist").html("");
		$.ajax({
        	type: "POST",
       	 	url:"../query/ericssonTrace.action",
       	 	data:"stoporstart=3&random="+random+"&mobile="+mobile+"&sgsnid="+ sgsnids.substring(0,8),
        	beforeSend:function(){
        	},
       		success:function(data){
       			setheight();
				clearInterval(waitinterval); 
       			$("#resultlist").html(data);
       			$("#queryhint").text("追踪/停止追踪命令执行完毕,总共耗时:"+waitsecond+"秒");
       			$("#gettracelogsbutton").attr("disabled",false); //可以重新进行查询
       			waitsecond=0;
			}
		});
		interval=setInterval("getResults()",1000); //每一秒钟去服务器获取结果数据
	}
}
function cantrace(value){
	if(value=="SGSNCQ07"||value=="SGSNCQ08"||value=="SGSNCQ09"){
		$("#tracebuttontd").hide();
		$("#gettracelogsbuttontd").hide();
		
	}else{
		$("#tracebuttontd").show();
		$("#gettracelogsbuttontd").show();
		
	}
}
//停止所有号码的追踪
function stopalltraces(){
	//alert("离开本页面，将停止追踪所有已启用追踪的号码");
	$.getJSON("../query/ericssonTrace.action?stoporstart=4", {"now":new Date().getTime()}, function(resp){
      }
    ); 
}
function go2query(){
	if(checkvalue()){
		mobile=$("#mobile").attr("value");
		$("#resultlist").html(""); //清空显示的结果
		$("#querylist").html("");
		 waitinterval=setInterval("waiting()",1000);
		$.ajax({
        	type: "POST",
       	 	url:"../query/querySHHistories.action",
       	 	data:"current=current&now="+new Date().getTime()+"&mobile="+mobile,
        	beforeSend:function(){
        	},
       		success:function(data){
       			setheight();
       			clearInterval(waitinterval); 
       			$("#resultlist").html(data);
       			$("#queryhint").text("号码"+mobile+"今天的历史记录查询完毕,耗时:"+waitsecond+"秒");
       			clearInterval(waitinterval); 
       			waitsecond=0;
			}
		});
	}	
}
</script>
</head>

<body onunload="stopalltraces()">
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>记录查询</a>＞<em>用户数据实时查询</em>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
								  <td width="25%" >输入手机编号：<s:textfield name="mobile" cssClass="txt" id="mobile" size="12"/>&nbsp;</td>
                                  <td width="40%" >
                                  <input type="button" class="btnSubmit" id="querybutton" value="状态查询" onclick="queryit()"/>
                                  &nbsp;&nbsp;
                                  <input type="button" class="btnSubmit" id="querybutton" value="状态查询历史记录" onclick="go2query()"/>
                                  &nbsp;&nbsp;
                                  <input type="button" class="btnSubmit" id="tracebutton" value="追踪" onclick="trace()"/>
                                  &nbsp;&nbsp;
                                  <input type="button" class="btnSubmit" id="gettracelogsbutton" value="获取追踪记录" onclick="gettracelogs()"/>
                                  </td>
							      <td width="35%" id="queryhint" style="color:red"></td>
								</tr>
								<tr>
								<td colspan="6" height="10px"></td>
								</tr>
								<tr>
                                 <td colspan="6">SGSN编号：
                                 
                                 <s:iterator value="@com.sxit.netquality.service.BasicSetService@ALL_SGSNS">
                                 <input type="checkbox" name="sgsnid" value="${value.sgsnid }" id="s-${value.sgsnid }"/>&nbsp;<label for="s-${value.sgsnid }">${value.sgsnid }</label>
                                 </s:iterator>
                                 
								</tr>
							</tbody>
						</table>
				  </div> 
				
				
				 <div class="tablist consoleresult eqHeight" style="overflow:auto" id="div1">
			   <!-- 存储返回的内容使用的 -->
					<div id="querylist" style="overflow:auto">
					</div>
				</div>

				<div class="tablist queryresult eqHeight" id="div2">
			   <!-- 存储返回的内容使用的 -->
					 <div id="resultlist" style="overflow:auto"></div>
				
				</div>
		<!-- 
				<div class="tablist" id="div3">
					
				</div> -->
			</div>
		</div>
	</div>
</body>

</html>