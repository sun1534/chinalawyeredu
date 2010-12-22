<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-华为chr日志信息列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
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




</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>网络质量</a>＞<em>华为CHR日志</em>＞<em>日志详情查询</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="hwchrQuery" method="POST">	
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
                                 <td>选择日期：<jscalendar:jscalendar name="date" cssClass="txt"/>&nbsp;</td>
								 <td>手机号：<s:textfield name="msisdn" cssClass="txt" size="15"/>&nbsp;</td>
								 <td>IMEI：<s:textfield name="imei" cssClass="txt" size="15"/>&nbsp;</td>
								 <td>流程ID：
								 <s:select name="flowid" list="@com.sxit.netquality.service.ChrQueryService@ALL_FLOWID" headerKey="" headerValue="全部">
								 </s:select>
								 &nbsp;</td>
								 <td>CELLID：<s:textfield name="cellid" cssClass="txt" size="15"/>&nbsp;</td>
								<!--  <td>LAC：<s:textfield name="lac" cssClass="txt" size="15"/>&nbsp;</td>
								--> 
								
								
								 <!--  <td><input type="button" class="btnSubmit" value="导  出" onclick="exportit()"/></td>
							 -->
								</tr>
								<tr>
								<td height="5px" colspan="5"></td>
								</tr>
								<tr>
								<td colspan="4">您能查询最近7天之内的记录情况:${end }至${start }</td>
								<td><input type="button" class="btnSubmit"  value="查　询" onclick="queryit()"/></td>
								
								</tr>
							</tbody>
						</table>
				  </div> 
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                       
                       <th>时间</th>
                       <th>手机号</th>
                     <!--   <th>IMEI</th>-->
                       <th>流程</th>
                       <th>触发原因</th>
                       <th>时延</th>
                       <th>外部原因</th>
                       <th>内部原因</th>
                       <th>使用APN</th>
                       <th>小区名称</th>
                       <th>旧小区名称</th>
                       <th>SGSN</th>
                       <!-- <th>GGSN</th>-->
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                  
                         <td>${timeStr}</td>
                          <td>${msisdn}</td>
                         <!--  <td>${imei }</td>-->
                          <td><s:property value="@com.sxit.netquality.service.ChrQueryService@ALL_FLOWID[flowid]"/><br/>(${flowid})</td>
                          <td>${trigreason}</td>
                           <td>${lazytime}</td>
                          <td>${outreason }</td>
                          <td>${innerreason }</td>
                          <td>${apn }</td>
                          <td>${cellName }</td>
                          <td>${oldCellName }</td>
                          <td>${sgsnid }</td>
                        <!--   <td>${ggsn }</td>-->
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                 <!--    <tfoot>
							<tr>
							   <td colspan="7" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/>
							   </td>
							</tr>
						 </tfoot>
			  -->
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

