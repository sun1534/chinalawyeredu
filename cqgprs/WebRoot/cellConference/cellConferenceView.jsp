at<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-会议小区监控</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["cellid","usercount","predayusercount","pretimeusercount","allvolueme","predayallvolume","pretimeallvolume"];
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


</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>会议小区</a>＞<em>${date }会议小区列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="cellConferenceView" method="POST">	
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
                                  <s:hidden name="resultType"/>
								 <td>选择日期：<jscalendar:jscalendar name="date" cssClass="txt"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
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
			        <!-- <table class="tableBox" id="newTablesD">-->
			          <table class="tableBox">
                      <thead>
                        <tr>
                       
                          <th><a onclick="orderByThis(document.form1,this)" id="cellid" title="点击排序">小区编号</a></th>
                          <th>小区名称</th>
                          <th><a onclick="orderByThis(document.form1,this)" id="usercount" title="点击排序">当前用户数</a></th>          
                          <th><a onclick="orderByThis(document.form1,this)" id="predayusercount" title="点击排序">上小时用户数</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="pretimeusercount" title="点击排序">昨天同时用户数</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="allvolueme" title="点击排序">当前流量</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="pretimeallvolume" title="点击排序">上小时流量</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="predayallvolume" title="点击排序">昨天同时流量</a></th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="stat">
                        <tr>
                          <td>${cellkey}</td>
                           <td><a href="compareCellByHour.action?date=${date }&cellkey=${cellkey }&stattime=${stattime }">${cell.cellname}</a></td>
                          <td>${usercount }</td>
                          <td>${pretimeusercount}</td>
                          <td>${predayusercount}</td>
                           <td>${allvolumeStr}</td>
                            <td>${pretimeallvolumeStr}</td>
                             <td>${predayallvolumeStr}</td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                    <tfoot>
							<tr>
							   <td colspan="8" class="fright">
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

