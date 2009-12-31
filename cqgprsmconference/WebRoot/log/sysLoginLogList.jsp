<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>${sysName}-管理员登录日志</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <jscalendar:head/>
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit()
  return true;
}
function query(){
document.getElementById("totype").value="list";
document.form1.submit();
}
function export(){
document.getElementById("totype").value="excel";
document.form1.submit();
}
</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>系统管理</a>＞<em>管理员登录日志列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="sysLoginLogList" method="POST">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
					<!-- 查询模块-->
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
                                 <s:hidden name="pageNo"/>
								 <td>登录人员:<s:textfield name="username" size="10" cssClass="txt"/>&nbsp;
								 </td>
								 <td>登录时间从:<jscalendar:jscalendar name="loginTime" cssClass="txt"/>
                  	         	<s:hidden name="totype" id="totype"/>&nbsp;
								 </td>
								 <td>
								  <input type="button" value="查　询" class="btnSubmit" onclick="query()"/>
                  	              <input type="button" value="导  出" class="btnSubmit" onclick="export()"/>
								 </td>
							     <td>
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
				  <div class="tablist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                          <th>登录人员</th>
                          <th>登录时间</th>
                          <th>退出时间</th>
                          <th>登录IP</th>
                          <th>在线时长</th>  
                           <th>登录说明</th>  
                           <th>退出说明</th> 
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                       <td>${username}</td>
                       <td>${loginTime }</td>
                       <td>${logoutTime}</td>
                       <td>${loginip } </td>
                       <td>${inSysTime}</td>    
                        <td>${loginremarks}</td>       
                        <td>${remarks}</td>             
                        </tr>
                        </s:iterator>
                      </tbody>
                 <!--    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit "/>
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

