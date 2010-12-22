<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <title>${sysName}-PDP失败情况总表</title>
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
$(document).ready(function(){
var flag=${flag};
if(flag==2){
$("#flag2").show();
}else{
$("#flag2").hide();
}
});
function selectit(obj){
if(obj.value==1){
$("#flag2").hide();
}else{
$("#flag2").show();
}
}
</script>
</head>
 
<body > 
		<div class="navigation" id="quickTools"> 
			<div class="innavigation"> 
				<div  class="navlist"> 
						<span>您所在是位置:</span><a>用户行为</a>＞<em>PDP失败情况</em>＞<em>PDP失败情况总表</em>
				</div> 
			</div> 
		</div> 
	<s:form name="form1" action="userPdpErrorAll" method="POST">	
		
		<div class="main"> 
			<div class="inmain"> 
				<div class="wrap"> 
					<!-- 查询模块 --> 
					<div class="searchTab">
						<table>
							<tbody>
								<tr>
 <s:hidden name="resultType"/>
								 <td><s:radio name="flag" list="#{'1':'实时','2':'选定时间前3天'}" onclick="selectit(this)"/>&nbsp;</td>
								 <td id="flag2">日期：<jscalendar:jscalendar name="date" cssClass="txt"/>&nbsp;</td>
								 <td><input type="button" class="btnSubmit " title="查　询" value="查　询" onclick="queryit()"/></td>
                                 <td><input type="button" class="btnSubmit " title="导　出" value="导　出" onclick="exportit()"/></td>
						
								</tr>
							</tbody>
						</table>
				  </div>
					<!-- 操作模块
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="location.href='add.html'"/>
						<input type="button" class="btnCancel" title="返 回" value="删　除"/>
					</div>--> 
				  <div class="tablist"> 

					<table class="tableBox" id="a"> 
 
                      <thead> 
                        <tr> 
                          <th ></th> 
                          <th colspan="2">当天累加值（${date1 }）</th> 
                          <th colspan="2">本时间段增加值（${date2}）</th> 
                          <th colspan="2">上次时间段增加值（${ date3}）</th> 
                        </tr> 
                      </thead> 
                      <tbody id="checkForm"> 
                        <tr > 
                          <td align="center" >错误类型</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                          <td align="center" >用户数</td> 
                          <td align="center" >PDP失败次数</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#33用户设置错误 </td> 
                          <td>${errorallstat1.usercount33 }</td> 
                          <td>${errorallstat1.errorcount33 }</td> 
                          <td>${errorallstat2.usercount33 }</td> 
                          <td>${errorallstat2.errorcount33 }</td> 
                          <td>${errorallstat3.usercount33 }</td> 
                          <td>${errorallstat3.errorcount33 }</td> 
                        </tr> 
                       <!--   <tr> 
                          <td align="center" >华为#4328错误 </td> 
                          <td>${errorallstat1.usercount4328 }</td> 
                          <td>${errorallstat1.errorcount4328 }</td> 
                          <td>${errorallstat2.usercount4328 }</td> 
                          <td>${errorallstat2.errorcount4328 }</td> 
                          <td>${errorallstat3.usercount4328 }</td> 
                          <td>${errorallstat3.errorcount4328 }</td> 
                        </tr>      
                        <tr> 
                          <td align="center" >华为#4329错误 </td> 
                          <td>${errorallstat1.usercount4329 }</td> 
                          <td>${errorallstat1.errorcount4329 }</td> 
                          <td>${errorallstat2.usercount4329 }</td> 
                          <td>${errorallstat2.errorcount4329 }</td> 
                          <td>${errorallstat3.usercount4329 }</td> 
                          <td>${errorallstat3.errorcount4329 }</td> 
                        </tr>  -->   
                        <tr> 
                          <td align="center" >#27漫游错误 </td> 
                           <td>${errorallstat1.usercount27 }</td> 
                          <td>${errorallstat1.errorcount27 }</td> 
                          <td>${errorallstat2.usercount27 }</td> 
                          <td>${errorallstat2.errorcount27 }</td> 
                          <td>${errorallstat3.usercount27 }</td> 
                          <td>${errorallstat3.errorcount27 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#29RADUIAS错误 </td> 
                         <td>${errorallstat1.usercount29 }</td> 
                          <td>${errorallstat1.errorcount29 }</td> 
                          <td>${errorallstat2.usercount29 }</td> 
                          <td>${errorallstat2.errorcount29 }</td> 
                          <td>${errorallstat3.usercount29 }</td> 
                          <td>${errorallstat3.errorcount29 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >#38网络错误 </td> 
                           <td>${errorallstat1.usercount38 }</td> 
                          <td>${errorallstat1.errorcount38 }</td> 
                          <td>${errorallstat2.usercount38 }</td> 
                          <td>${errorallstat2.errorcount38 }</td> 
                          <td>${errorallstat3.usercount38 }</td> 
                          <td>${errorallstat3.errorcount38 }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >其他错误 </td> 
                          <td>${errorallstat1.usercountothers }</td> 
                          <td>${errorallstat1.errorcountothers }</td> 
                          <td>${errorallstat2.usercountothers }</td> 
                          <td>${errorallstat2.errorcountothers }</td> 
                          <td>${errorallstat3.usercountothers }</td> 
                          <td>${errorallstat3.errorcountothers }</td> 
                        </tr> 
                        <tr> 
                          <td align="center" >总量 </td> 
                          <td>${errorallstat1.usercountall }</td> 
                          <td>${errorallstat1.errorcountall }</td> 
                          <td>${errorallstat2.usercountall }</td> 
                          <td>${errorallstat2.errorcountall }</td> 
                          <td>${errorallstat3.usercountall }</td> 
                          <td>${errorallstat3.errorcountall }</td> 
                        </tr> 
                      </tbody> 
                     <tfoot> 
								<tr> 
									<td colspan="7" class="fright"><input type="button" value="导　出" title="导　出" class="btnSubmit" onclick="exportit()"/></td> 
								</tr> 
							</tfoot> 
                    </table> 
			  </div> 
            
				</div> 
			</div> 
		</div>
		</s:form> 
	<!--  --> 
 
</body> 
 
</html> 
 
