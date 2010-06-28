<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-用户状态实时查询历史记录</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <link rel="stylesheet" type="text/css" href="../css/dialog.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/dialog.js"></script> 
  <script type="text/javascript" src="../js/jquery.blockUI.js"></script> 
 <script type="text/javascript">
 

function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
if(checkvalue()){
  document.form1.resultType.value="excel";
  document.form1.submit();
  }
}
function queryit(){
if(checkvalue()){
  document.form1.resultType.value="list";
  document.form1.submit();
  }
}
function checkvalue(){
var apnni=$("#mobile").attr("value");
if(apnni==""||apnni.length==0){
alert("请输入要查询的手机号码");
return false;
}
return true;
}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>记录查询</a>＞<em>用户状态实时查询历史记录</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="querySHHistories" method="POST">	
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
								  <td>输入手机号码：<s:textfield name="mobile" cssClass="txt" id="mobile" size="12"/>&nbsp;</td>
                                 <td>查询时间跨度,从<jscalendar:jscalendar name="start" cssClass="txt"/>&nbsp;
                                 	至：<jscalendar:jscalendar name="end" cssClass="txt"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							 <td><input type="button" class="btnSubmit" value="返回上页" onclick="javascript:location.href='queryBySHPre.action'"/></td>
								</tr>
							</tbody>
						</table>
				  </div> 
				
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                       
                          <th>查询时间</th>
                          <th>查询人员</th>
                           <th>手机号码</th>
                           <th>IMSI号码</th>
                          <th>SGSN编号</th>
                          <th>执行结果</th>
                        <th>详情</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                         <s:iterator value="page.items" status="status">
                        <tr>
                         <td>${querydate}</td>
                          <td>${execuser} </td>
                          <td>${msisdn} </td>
                             <td>${imsi} </td>
                          <td>${sgsnid }</td>
                          <td>${result}</td>
                          <td><a href="#" onclick="showDialogBox('#${id }',500);" title="显示命令的执行结果">详情</a></td>
                        
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                      <!-- 
                    <tfoot>
							<tr>
							   <td colspan="8" class="fright">
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
  <s:iterator value="page.items" status="status">
  <div class="Overdialog" id="${id }" style="display:none;overflow:auto">
                          ${detailsClob2Str }
                          </div>
</s:iterator>
</body>

</html>

