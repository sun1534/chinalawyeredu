<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-异常行业用户</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
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
						<span>您所在是位置:</span><a>行业客户</a>＞<em>异常行业用户</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="exceptionUsers" method="POST">	
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
								 <td>APN编号：<s:textfield name="apnni" cssClass="txt" size="10"/>&nbsp;</td>
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
					
					<div class="tablist"> 
						<table class="tableBox"> 
							<thead> 
								<tr> 
									<th  rowspan="2">APN</th> 
									<th  rowspan="2">客户名称</th> 
									<th  rowspan="2">联系方式</th> 
									<th  rowspan="2">异常用户数</th> 
									<th  colspan="2" align="center">详情</th> 
								</tr> 
								<tr> 
									<th>IMSI</th> 
									<th>PDP失败次数</th> 
								</tr> 
							</thead> 
							<tbody> 
								<s:iterator value="page.items">
							<s:iterator value="imsilist" status="stat">
							<s:if test="#stat.index==0">
								<tr> 
									<td rowspan="${ usercount}">${apnni }</td> 
									<td rowspan="${ usercount}">${apn.usercorp }</td> 
									<td rowspan="${ usercount}">${apn.userphone }</td> 
									<td rowspan="${ usercount}">${usercount }</td> 
									<td >${imsi }</td> 
									<td >${errcount }</td> 
								</tr> 
								</s:if>
								<s:else>
								<tr>
								<td >${imsi }</td> 
									<td >${errcount }</td> 
									</tr>
								</s:else>
								</s:iterator>
								</s:iterator>
							</tbody>
						</table> 
					</div> 
					<!-- 
				<s:iterator value="page.items">
				<div class="tablist"> 
						<table class="tableBox"> 
							<thead> 
								<tr> 
									<th>APN</th> 
									<th>客户名称</th> 
									<th>联系方式</th> 
									<th>异常用户数</th> 
								</tr> 
							</thead> 
							<tbody> 
								<tr> 
									<td>${apnni }</td> 
									<td>${apn.usercorp }</td> 
									<td>${apn.userphone }</td> 
									<td>${usercount }</td> 
								</tr> 
							</tbody> 
						</table> 
					</div> 
				
				  <div class="tablist" >
			        <table class="tableBox" >
                      <thead>
                        <tr>
                       
                          <th>用户IMSI</th>
                          <th>PDP失败次数</th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="imsilist" status="status">
                        <tr>
                         <td>${imsi}</td>
                          <td>${errcount}</td>
                        
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    </table>
			  </div>
			  
			  </s:iterator>-->

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

