<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-PDP失败用户排名</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["reqapnni","imsi","errcount"];
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
showhidehour(flag);
});
function selectit(obj){
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
						<span>您所在是位置:</span><a>用户行为</a>＞<em>PDP失败用户排名（错误前1000）</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="userPdpErrorTopList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
								<s:hidden name="pageNo"/>
				                	<s:hidden name="firstpage"/>
					              <s:hidden name="orderfield" id="orderfieldid"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                  <s:hidden name="resultType"/>
                                  <td><s:radio name="flag" list="#{'1':'按天','2':'按时'}" onclick="selectit(this)" title="按时查可以选当天的日期"/>&nbsp;</td>
								  <td id="dateselect">选择日期：<jscalendar:jscalendar name="date" cssClass="txt"/>&nbsp;</td>
								  <td id="hourselect"><s:select name="hour" list="@com.sxit.stat.service.StatService@ALL_HOUR_LIST"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							<td><input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							</td>
							
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
                  
                          <th><a onclick="orderByThis(document.form1,this)" id="imsi" title="点击排序">IMSI</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="reqapnni" title="点击排序">请求APN</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="errcount" title="点击排序">PDP失败次数</a></th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="resultList" status="status">
                        <tr>
                         <td>${imsi}</td>
                          <td>${apn}</td>
                          <td>${errorcount }</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                 
			  
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

