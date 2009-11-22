<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-高流量用户排名</title>
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
function selectit(obj){
if(obj.value==2){
$("#pahint").text("总流量前X位：");
}else{
$("#pahint").text("总流量大于X(单位K)：");
}
}


</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>用户行为</a>＞<em>高流量用户排名<s:if test="standard==2">（排名前${condition }）</s:if><s:else>（流量大于${condition}(K)）</s:else></em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="hightStreamUser" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                
                                  <s:hidden name="resultType"/>
                                   <td><s:radio name="standard" list="#{'1':'流量大于设定值','2':'TOP设定值'}" onclick="selectit(this)"/>&nbsp;</td>
                                   <td><span id="pahint">流量前X位：</span><s:textfield name="condition" size="10" cssClass="txt"/>&nbsp;</td>
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
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>

                          <th><a onclick="orderByThis(document.form1,this)" id="mobile" title="点击排序">手机号码</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="apnni" title="点击排序">APN编码</a></th>
                           <th><a onclick="orderByThis(document.form1,this)" id="upvolume" title="点击排序">上行流量（K）</a></th>
                            <th><a onclick="orderByThis(document.form1,this)" id="downvolume" title="点击排序">下行流量（K）</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="allvolume" title="点击排序">总流量（K）</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="periodlen" title="点击排序">逗留时长（秒）</a></th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="top1000users" status="status">
                        <tr>
                         <td>${mobile}</td>
                          <td>${apnni}</td>
                          <td>${upvolume }</td>
                          <td>${downvolume}</td>
                          <td>${allvolume}</td>
                          <td>${periodlen}</td>
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

