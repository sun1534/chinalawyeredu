<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-概况</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
  <link rel="stylesheet" type="text/css" href="../css/main.css" />
  <script type="text/javascript" src="../js/jquery.js"></script>
   <script type="text/javascript" src="../js/swfobject.js"></script>
   <script type="text/javascript" >
swfobject.embedSWF("../open-flash-chart.swf", "totalchart", "300", "200", "9.0.0","",
  {"data-file":"../stat/workspaceIndex.action?flashby=total","loading":"正在载入数据..."} );
swfobject.embedSWF("../open-flash-chart.swf", "avergchart", "300", "200","9.0.0","",
  {"data-file":"../stat/workspaceIndex.action?flashby=average","loading":"正在载入数据..."} );
swfobject.embedSWF("../open-flash-chart.swf", "userchart", "300", "200","9.0.0","",
  {"data-file":"../stat/workspaceIndex.action?flashby=user","loading":"正在载入数据..."} );
  
  $(document).ready(function(){
  $("#userpdperrorall").load("../useraction/userPdpErrorAll.action?resultType=toindex");
  });
 </script>
</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>系统概况</a></em>
				</div>
			</div>
		</div>
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!--  -->
						<div class="nowInfo">
							<h2>昨日系统异常情况：</h2>
							<ul>
								<li><a href="../netquality/bscList.action">新增<b>${newaddbsc }</b>个BSC，共<b>${totalbsc }</b>个BSC</a>，<a href="#">有<b>${exceptionbsc }</b>个BSC有异常</a></li>
								<li><a href="../netquality/cellList.action">新增<b>${newaddcell }</b>个小区，共<b>${totalcell }</b>个小区</a>，<a href="../netquality/zeroStreamCell.action">有<b>${exceptioncell }</b>个小区有异常</a></li>
								<li><a href="../netquality/apnList.action">新增<b>${newaddapn }</b>个APN，共<b>${totalapn }</b>个行业APN</a>，<a href="../netquality/zeroStreamAPN.action">有<b>${exceptionapn }</b>个行业APN异常</a></li>
							 	<li><a href="../netquality/nsvcList.action">新增<b>${newlink }</b>个链路，共<b>${totallink}</b>个链路</a><!-- ，有<b>82</b>个链路流量告警，有<b>15</b>个链路需要关注</a> --></li>
							
							</ul>
				  </div>
				  
				  	<div class="tablist" id="userpdperrorall">
				  	<img src="../images/loading.gif"/>
					</div>
				  
			  <div  class="tablist" style="text-align=center">
    <div  id="totalchart"></div>
    <div  id="avergchart"></div>
    <div  id="userchart"></div>
         </div>




					<div class="tablist">
                    <table class="tableBox">
                        	<thead>
								<tr>
									<th>近3天总流量表</th>
                                 </tr>
                            </thead>
                        </table>
						<table class="tableBox">
							<thead onclick="$('#checkForm').toggle()">
								<tr>
									<th>日期</th>
									<th>总流量（M）</th>
									<th>总用户数</th>
									<th>平均每用户流量（K）</th>
								</tr>
							</thead>
							<tbody id="checkForm">
						     <s:iterator value="totallist" status="status">
						      <tr>
						  <td>${date}</td>
                          <td>${totalStreamStr }</td>
                          <td>${totalUser}</td>
                          <td>${averageStreamStr}</td>
                          </tr>
                             </s:iterator>
							</tbody>
							<!--<tfoot>
								<tr>
									<td colspan="5" class="fright"><input type="button" value="导　出" title="导　出" class="btnSubmit "/></td>
								</tr>
							</tfoot>-->
						</table>
					</div>

                    <div class="tablist">
                    	<table class="tableBox">
                        	<thead>
								<tr>
									<th>近3天分2G/3G总流量表</th>
                                 </tr>
                            </thead>
                        </table>
						<table class="tableBox">
							<thead  onclick="$('#checkForm2').toggle()">
								<tr>
									<th>日期</th>
									<th>用户类型</th>
									<th>总流量（M）</th>
									<th>总用户数</th>
									<th>平均每用户流量（K）</th>
								</tr>
							</thead>
							<tbody id="checkForm2">
						 <s:iterator value="total23glist" status="stat">
						 <tr>
						  <s:if test="#stat.odd">
						  <td rowspan="2">${date}</td>
						  </s:if>
						  <td>${nettype}</td>
                          <td>${totalStreamStr }</td>
                          <td>${totalUser}</td>
                          <td>${averageStreamStr}</td>
                          </tr>
                             </s:iterator>
							</tbody>
						<!-- 	<tfoot>
								<tr>
									<td colspan="5" class="fright"><input type="button" value="导　出" title="导　出" class="btnSubmit "/></td>
								</tr>
							</tfoot>-->
						</table>
					</div>

				</div>
			</div>
		</div>
</body>

</html>

