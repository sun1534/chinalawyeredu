<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-链路信息列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 var orderArray=["nsvc","nsvcgbindex","bscid","capacity","opst"];
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
						<span>您所在是位置:</span><a>网络质量</a>＞<em>资源列表</em>＞<em>链路信息列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="nsvcList" method="POST">	
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
								 <td>归属BSC/RNC编号：<s:textfield name="bscid" cssClass="txt" size="15"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit"  value="查　询" onclick="queryit()"/></td>
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
                       
                       <th><a onclick="orderByThis(document.form1,this)" id="nsvc" title="点击排序">NSVC</a></th>
                       <th><a onclick="orderByThis(document.form1,this)" id="nsvcgbindex" title="点击排序">查询索引号</a></th>
                       <th><a onclick="orderByThis(document.form1,this)" id="bscid" title="点击排序">所属BSC/RNC</a></th>
                       <th>归属SGSN</th>
                       <th><a onclick="orderByThis(document.form1,this)" id="opst" title="点击排序">当前状态</a></th>
                       <th><a onclick="orderByThis(document.form1,this)" id="capacity" title="点击排序">容量大小（K）</a></th>
                       <th>最后更新时间</th>
                        
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="nsvclist" status="status">
                        <tr>
                  
                         <td>${nsvc}</td>
                          <td>${nsvcindex}</td>
                          <td>${bscid }</td>
                          <td><s:property value="@com.sxit.netquality.service.BasicSetService@BSC_SGSN[bscid]"/></td>
                          <td>${opst}</td>
                           <td>${capacity}</td>
                          <td>${lastupdate }</td>
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

