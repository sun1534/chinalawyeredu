<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-垃圾BSC数据列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 //var orderArray=["bscid","sgsnid","nsvccount","updatetime"];
// var field="${orderfield}";
//var ascdesc="${ascdesc}";
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
						<span>您所在是位置:</span><a>网络质量</a>＞<em>资源列表</em>＞<em>垃圾BSC列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="bscRubbishList" method="POST">	
			 <s:hidden name="orderfield" id="orderfieldid"/>
								      <s:hidden name="ascdesc" id="ascdescid"/>
                                 <s:hidden name="pageNo"/>
                                     <s:hidden name="resultType"/>
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
								
								<!--  <td>BSC/RNC编号：<s:textfield name="bscid" cssClass="txt" size="10"/>&nbsp;</td>
								  <td>SGSN编号：<s:select name="sgsnid" list="@com.sxit.netquality.service.BasicSetService@ALL_SGSNS" headerKey="" headerValue="全部" listKey="value.sgsnid" listValue="value.sgsnid"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							-->
							 <td><input type="button" class="btnSubmit" value="导  出" onclick="exportit()"/></td>
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
                        
                       <th>BVCI</th>
                       <th>最后更新时间</th>
                       <th>垃圾NSEI</th>
                       <th>归属BSC</th>  
                       <th>正确NSEI</th>
                       <th>归属BSC</th>  
                       </tr>
                      </thead>
                      <tbody id="checkForm">
                    
                    
                    <s:iterator value="resultList" status="status1">
                    <s:if test="correctCnt>=rubbishCnt"><!-- 正确的多 -->
                    <s:iterator value="correctList" status="status2">
                    <s:if test="#status2.index==0">
                      <tr>
                      <td rowspan="${trcnt}">${bvci } </td>
                      <td rowspan="${trcnt}">${recordtimestr }</td>
                      <s:if test="#status2.index<rubbishCnt">
                        <td><s:property value="rubbishList[#status2.index].nesi"/></td> 
					    <td><s:property value="rubbishList[#status2.index].bscid"/></td> 
					  </s:if>
					  <s:elseif test="rubbishCnt==0">
					  	<td rowspan="${trcnt}" colspan="2">没有垃圾数据</td>
					  </s:elseif>
					  <s:else>
					    <td>&nbsp;</td> 
					    <td>&nbsp;</td> 
					  </s:else>
		    		  <td>${nesi }</td> 
		    		  <td>${bscid }</td> 
                      </tr>
                    </s:if>
                    <s:else>
                    <tr>
                    <s:if test="rubbishCnt!=0">
                     <s:if test="#status2.index<rubbishCnt">
                      <td ><s:property value="rubbishList[#status2.index].nesi"/></td> 
					  <td ><s:property value="rubbishList[#status2.index].bscid"/></td> 
					  </s:if>
					  <s:else>
					    <td>&nbsp;</td> 
					    <td>&nbsp;</td> 
					  </s:else>
					  </s:if>
		    		  <td >${nesi }</td> 
		    		  <td >${bscid }</td> 
                    </tr>
                    </s:else>
                    </s:iterator>
                    </s:if>
                    <s:else>
                        <s:iterator value="rubbishList" status="status3">
                    <s:if test="#status3.index==0">
                      <tr>
                      <td rowspan="${trcnt}">${bvci }</td>
                      <td rowspan="${trcnt}">${recordtimestr }</td>
                       <td >${nesi }</td> 
		    		  <td >${bscid }</td> 
                      <s:if test="#status3.index<correctCnt">
                      <td ><s:property value="correctList[#status3.index].nesi"/></td> 
					  <td ><s:property value="cor rectList[#status3.index].bscid"/></td> 
					  </s:if>
					    <s:elseif test="correctCnt==0">
					  	<td rowspan="${trcnt}" colspan="2">没有正确数据</td>
					  </s:elseif>
					    <s:else>
					    <td >&nbsp;</td> 
					  <td >&nbsp;</td> 
					  </s:else>
		    		 
                      </tr>
                    </s:if>
                    <s:else>
                    
                    <tr>
		    		  <td >${nesi }</td> 
		    		  <td >${bscid }</td> 
		    		       <s:if test="correctCnt!=0">
		    		    <s:if test="#status3.index<correctCnt">
                      <td ><s:property value="correctList[#status3.index].nesi"/></td> 
					  <td ><s:property value="correctList[#status3.index].bscid"/></td> 
					  </s:if>
					    <s:else>
					    <td >&nbsp;</td> 
					  <td >&nbsp;</td> 
					  
					  </s:else>
					  </s:if>
                    </tr>
                    </s:else>
                    </s:iterator>
                    </s:else>
                       
                     
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

