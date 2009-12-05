<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-高流量小区</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["cellid","bscid","allvolume"];
 var field="${orderfield}";
var ascdesc="${ascdesc}";
 

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.firstpage.value="no";
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
function selectit(obj){
showhint(obj.value);
}
$(document).ready(function(){
var flag="${flag}";
var standard="${standard}";
showhint(standard);
showhidehour(flag);
});
function selecthour(obj){
var flag=obj.value;
showhidehour(flag);
}
function showhint(_standard){
if(_standard==2){
$("#pahint").text("总流量前X位：");
}else{
$("#pahint").text("总流量大于X(单位K)：");
}
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
						<span>您所在是位置:</span><a>网络质量</a>＞<em>高流量小区<s:if test="standard==2">（当前流量排名前${condition }）</s:if><s:else>（流量大于${condition}(K)）</s:else></em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="hightStreamCell" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
									 <s:hidden name="orderfield" id="orderfieldid"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                 <s:hidden name="pageNo"/>
                                     <s:hidden name="firstpage"/>
                                  <s:hidden name="resultType"/>
								
								   <td><s:radio name="standard" list="#{'1':'流量大于设定值','2':'TOP设定值'}" onclick="selectit(this)"/>&nbsp;</td>
                                   <td><span id="pahint">流量前X位：</span><s:textfield name="condition" size="6" cssClass="txt"/>&nbsp;</td>
								   <td><s:radio name="flag" list="#{'1':'按天','2':'按时'}" onclick="selecthour(this)" title="按时查询可以选择当天的日期"/>&nbsp;</td>
								 <td>选择日期：<jscalendar:jscalendar name="date" cssClass="txt"/>&nbsp;</td>
								 <td id="hourselect"><s:select name="hour" list="@com.sxit.stat.service.StatService@ALL_HOUR_LIST"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							     <!--  <td><input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							      </td>
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
                       
                          <th><a onclick="orderByThis(document.form1,this)" id="cellid" title="点击排序">小区编码</a></th>
                           <th>小区名称</th>
                          <th><a onclick="orderByThis(document.form1,this)" id="bscid" title="点击排序">归属BSC/RNC</a></th>
                          <th>归属SGSN</th>
                          <th>历史总流量（M）</th>
                          <th><a onclick="orderByThis(document.form1,this)" id="allvolume" title="点击排序">当前流量（M）</a></th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="topcelllist" status="status">
                        <tr>
                         <td>${cellkey}</td>
                         <td><s:property value="@com.sxit.netquality.service.BasicSetService@ALL_CELLS[cellkey].cellname"/></td>
                          <td>${bscrncid}</td>
                          <td>${sgsnid }</td>
                          <td>${totalStreamStr}</td>
                          <td>${currentStreamStr}</td>
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

