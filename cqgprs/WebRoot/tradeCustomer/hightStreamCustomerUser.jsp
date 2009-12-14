<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-高流量行业用户排名</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
  var orderArray=["mobile","apnni","upvolume","downvolume","allvolume","periodlen"];
 
 var field="${orderfield}";
var ascdesc="${ascdesc}";
 

function fanye(str){
 document.form1.firstpage.value="no";
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
 document.form1.firstpage.value="yes";
  document.form1.resultType.value="excel";
  document.form1.submit();
}
function queryit(){
 document.form1.firstpage.value="yes";
  document.form1.resultType.value="list";
  document.form1.submit();
}

$(document).ready(function(){
var flag="${flag}";
var standard="${standard}";
showhidehour(flag);
});
function selecthour(obj){
var flag=obj.value;
showhidehour(flag);
}

function showhidehour(_flag){
if(_flag==2){
$("#hourselect").show();
}else{
$("#hourselect").hide();
}
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>行业客户</a>＞<em>高流量行业用户排名（流量大于平均流量${standard}倍，即${condition }K）</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="hightStreamCustomerUser" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr title="您能查询最近7天之内的记录情况:${end }至${start }">
								 <s:hidden name="orderfield" id="orderfieldid"/>
								    	<s:hidden name="firstpage"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                   <s:hidden name="resultType"/>
                                   <s:hidden name="pageNo"/>
                                   <td>APN：<s:textfield name="apnni" size="10" cssClass="txt"/>
								   </td>
                                   <td>
                                   大于平均流量<s:select name="standard" list="#{'1.5':'1.5','2':'2','2.5':'2.5','3':'3','3.5':'3.5','4':'4','4.5':'4.5','5':'5'}"/>倍
                                   </td>
								   <td><s:radio name="flag" list="#{'1':'按天','2':'按时'}" onclick="selecthour(this)" title="按时查询可以选择当天的日期"/>
								   </td>
								 <td>选择日期：<jscalendar:jscalendar name="date" cssClass="txt"/>
								 </td>
								 
								 <td id="hourselect"><s:select name="hour" list="@com.sxit.stat.service.StatService@ALL_HOUR_LIST"/>
								 </td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							<!--  <td><input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/></td>
							-->
								</tr>
							</tbody>
						</table>
				  </div> 
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					    <input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>-->
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>

                          <th><a onclick="orderByThis(document.form1,this)" id="mobile" title="点击排序">手机号码</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="apnni" title="点击排序">APN编码</a></th>
                           <th><a onclick="orderByThis(document.form1,this)" id="upvolume" title="点击排序">上行流量（M）</a></th>
                            <th><a onclick="orderByThis(document.form1,this)" id="downvolume" title="点击排序">下行流量（M）</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="allvolume" title="点击排序">总流量（M）</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="periodlen" title="点击排序">逗留时长</a></th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="top1000users" status="status">
                        <tr>
                         <td>${mobile}</td>
                          <td>${apnni}</td>
                          <td>${upvolumeStr }</td>
                          <td>${downvolumeStr}</td>
                          <td>${allvolumeStr}</td>
                          <td>${periodlenStr}</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  
                    </table>
			  </div>

			 <div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

