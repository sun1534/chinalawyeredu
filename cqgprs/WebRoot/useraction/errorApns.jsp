<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-错误APN分布</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/orderby.js"></script>
 <script type="text/javascript">
 
 var field="${orderfield}";
var ascdesc="${ascdesc}";
  var orderArray=["reqapnni","usercount","errorcount","errcode"];
 

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
						<span>您所在是位置:</span><a>用户行为</a>＞<em>错误APN分布</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="errorApns" method="POST">	
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
								 <td>请求APN：<s:textfield name="reqapnni" cssClass="txt" size="12"/>&nbsp;</td>
								 <td>错误代码：<s:select name="errcode" list="#{'':'全部','27':'27','29':'29','33':'33','38':'38'}"/>&nbsp;</td>
								 
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
							<td><input type="button" value="导　出" title="导　出" class="btnSubmit " onclick="exportit()"/></td>
							
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
                       
                          <th><a onclick="orderByThis(document.form1,this)" id="reqapnni" title="点击排序">APN编码</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="usercount" title="点击排序">用户数</a></th>
                          <th><a onclick="orderByThis(document.form1,this)" id="errorcount" title="点击排序">PDP失败次数</a></th>
                        <th><a onclick="orderByThis(document.form1,this)" id="errcode" title="点击排序">错误代码</a></th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="apnerrorslist" status="status">
                        <tr>
                         <td>${apnni}</td>
                          <td>${usercount}</td>
                          <td>${errorcount }</td>
                            <td>${errcode }</td>
                          
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

