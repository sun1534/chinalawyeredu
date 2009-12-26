<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-SGSN-APN流量分析</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript" src="../js/swfobject.js"></script>
 <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
 <script type="text/javascript">
  $(document).ready(function(){
 $("#tableOrder").tablesorter();
 });
 
 
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
						<span>您所在是位置:</span><a>统计分析</a>＞<em>业务全貌</em>＞<em>SGSN-GGSN-CMNET/CMWAP流量分析</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="sgsnStreamApn" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
                                  <s:hidden name="resultType"/>
								 <td>选择日期：<jscalendar:jscalendar name="start" id="start" cssClass="txt"/>&nbsp;</td>
								 <td id="sgsidopt">SGSN编号：<s:select name="sgsnid" id="sgsnid" list="@com.sxit.netquality.service.BasicSetService@ALL_SGSNS" listKey="key" listValue="key" headerKey="" headerValue="全部" onchange="document.form1.submit()"/>
								 <td><input type="button" class="btnSubmit" title="查　询" value="查　询" onclick="queryit()"/></td>
								 <td><input type="button" class="btnSubmit" title="图  形" value="图  形"  onclick="imageit()"/></td>
							 <td id="imageopton">
								   <s:select name="flashType" id="flashType" list="#{'line':'曲线图','bar':'柱状图'}" label="图形类型"></s:select>
								   <s:select name="flashby" id="flashby" list="#{'total':'总流量','user':'总用户数','average':'平均流量'}" label="维度"></s:select>
								   <s:select name="apnni" id="apnni" list="#{'cmwap':'CMWAP','cmwap':'CMNET','other':'其他'}" label="apnni"></s:select>
								   <input type="button" class="btnSubmit" value="确 认"  onclick="confirmit()" id="flashconfirm"/>
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
				  <div  class="tablist" style="text-align:center" id="imgreport">
                    <div id="barchart"></div>
                    </div>
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="tableOrder">
                      <thead>
                        <tr>
                       
                          <th>SGSN号</th>
                        <!--   <th>覆盖范围</th>-->
                          <th>GGSNID</th>
                          <th>APNNI</th>
                          <th>总流量（M）</th>
                          <th>总用户数</th>
                          <th>平均流量（K）</th>
                        
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="sgsnlist" status="stat">
                        <tr>
                        <!--   <s:if test="#stat.odd">
                          <td rowspan="2">${sgsnid}</td>
                          <td rowspan="2">${sgsnArea}</td>
                          </s:if>-->
                          <td>${sgsnid}</td>
                          <td>${ggsnid}</td>
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

