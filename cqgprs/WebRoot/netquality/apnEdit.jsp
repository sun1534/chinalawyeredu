<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-APN参数修改</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>网络质量</a>＞<em>APN参数信息修改</em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="apnEdit" method="post">
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="submit" class="btnSubmit" title="保 存" value="保 存"/>
					 <input type="button" class="btnCancel" title="返 回" value="返 回" onclick="history.go(-1)"/>
					
			    </div>
				<div class="operateTab">
					<!--<div class="operateTabInfo">密码错误！</div>-->
					<table id="editPwd" class="operateTabBox">
						<tbody>
							<tr class="fOdd">
								<td class="w220 fname">APN编码：</td>
								<td class="fvalue">
								<s:hidden name="pageNo"/>
								<s:hidden name="apnni"/>
							<s:hidden name="from"/>
								
						     	${apnni }
								</td>
							</tr>
							<tr class="fEven">
								<td class="fname">APN使用单位：</td>
								<td>
							<s:textfield name="apn.usercorp" size="25" cssClass="txt"/>
								</td>
							</tr>
							<tr class="fOdd" >
								<td  class="fname">APN联系方式：</td>
								<td class="fvalue">
								<s:textfield name="apn.userphone" size="25" cssClass="txt"/>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</s:form>
	<!--  -->

</body>

</html>

