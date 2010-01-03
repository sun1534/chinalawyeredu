<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-其他GGSN流量统计列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript" src="../js/swfobject.js"></script>
 <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["sgsnid","ggsnid","allvolume","usercount"];
 var field="${orderfield}";
var ascdesc="${ascdesc}";
 
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  document.form1.resultType.value="excel";
  document.form1.submit();
}
function queryit(){
  document.form1.resultType.value="list";
  document.form1.submit();
}
function imageit(){
 if(ishide){
    $("#imageopton").show();
    $("#sgsidopt").hide();
    ishide=false;
  }else{
    $("#sgsidopt").show();
    $("#imageopton").hide();
    $("#imgreport").hide();
    ishide=true;
  }
}
var ishide=true;
$(document).ready(function(){
  $("#imageopton").hide();
  $("#imgreport").hide();
  ishide=true;
});
function confirmit(){
      $("#imgreport").show();
   var flashType=$("#flashType").val();
   var flashby=$("#flashby").val();
    var apnni=$("#apnni").val();
   var start=$("#start").val();
   var url="sgsnStream23g.action?start="+start+"%26resultType=flash%26flashby="+flashby+"%26flashType="+flashType+"%26nettype="+apnni;
   swfobject.embedSWF("../open-flash-chart.swf", "barchart", "700", "300", "9.0.0","",{"data-file":url,"loading":"正在载入数据..."} );
   //alert(url);

}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>统计分析</a>＞<em>业务全貌</em>＞<em>其他GGSN流量统计列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="otherGgsnStat" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
                                   <s:hidden name="orderfield" id="orderfieldid"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                      <s:hidden name="resultType"/>
								 <td>选择日期：<jscalendar:jscalendar name="start" id="start" cssClass="txt"/>&nbsp;</td>
								 <td id="sgsidopt">SGSN编号：<s:select name="sgsnid" id="sgsnid" list="@com.sxit.netquality.service.BasicSetService@ALL_SGSNS" listKey="key" listValue="key" headerKey="" headerValue="全部" onchange="document.form1.submit()"/>
								 <td id="sgsidopt">GGSN编号：<s:textfield name="ggsnid" id="ggsnid" size="15" cssClass="txt"/>
								 <td><input type="button" class="btnSubmit" title="查　询" value="查　询" onclick="queryit()"/></td>
						
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
			        <table class="tableBox" id="tableOrder">
                      <thead>
                        <tr>
                       
                          <th><a onclick="orderByThis(document.form1,this)" id="sgsnid" title="点击排序">SGSN号</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="ggsnid" title="点击排序">GGSNID</a></th>
                          <th>网络类型</th>
                          <th>APNNI</th>
                          <th><a onclick="orderByThis(document.form1,this)" id="allvolume" title="点击排序">总流量（M）</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="usercount" title="点击排序">总用户数</a></th>
                          <th>平均流量（K）</th>
                        
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="stat">
                        <tr>
                
                          <td >${sgsnid}</td>
                          <td>${ggsnid}</td>
                          <td>${nettype}</td>
                          <td>${apnni}</td>
                          <td>${totalStreamStr }</td>
                          <td>${totalUser}</td>
                          <td>${averageStreamStr}</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="7" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  
                    </table>
			  </div>
                
			<!-- 	<div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>	
					--> 
				</div>
			
			</div>
		</div>
</s:form>
</body>

</html>

