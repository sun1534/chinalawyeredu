<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-MEM设备操作日志列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <link rel="stylesheet" type="text/css" href="../css/dialog.css" />
 
 <jscalendar:head/>
    <script type="text/javascript" src="../js/dialog.js"></script> 
  <script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/jquery.form.js"></script>
 <script type="text/javascript">
 


function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function exportit(){
  document.form1.submit();

}
function queryit(){

  document.form1.submit();

}

</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
						<span>您所在是位置:</span><a>通信保障</a>＞<em>MEM设备操作日志列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="memLogList" method="POST">	
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
                                  <td>用户列表：<s:select name="userId" list="userList" headerKey="0" headerValue="全部"/>&nbsp;</td>
                                <td>设备列表：<s:select name="deviceId" list="deviceList" headerKey="0" headerValue="全部"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit" value="查　询" onclick="queryit()"/></td>
								 <td><input type="button" class="btnSubmit" value="新　增" onclick="createEditCommandPre('1',0);"/> </td>
								</tr>
							</tbody>
						</table>
				  </div> 
				
				
				  <div class="tablist" id="querylist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                       
                          <th>操作用户</th>
                          <th>对应设备</th>
                          <th>命令</th>
                          <th>命令结果</th>
                          <th>操作时间</th>
                          </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                          <td><a href="memLogList.action?userId=${userid}"><s:property value="userList[userid]"/></a></td>
                          <td><a href="memLogList.action?deviceId=${deviceid}"><s:property value="deviceList[deviceid]"/></a> </td>
                          <td><a href="memLogList.action?commandId=${commandid}">
                          <s:if test="commandid==0">
                          登录
                          </s:if>
                          <s:elseif test="commandid==-1">
                          自定义命令
                          </s:elseif>
                          <s:else>
                          <s:property value="commandList[commandid]"/>
                          </s:else>
                          </a></td>
                          <td>${result}</td>
                           <td>${createtime}</td>
            
                        </tr>
                        </s:iterator>
                      
                      </tbody>
             <!--        <tfoot>
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

