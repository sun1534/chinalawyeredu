<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-密码修改</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>首页</a>＞<em>修改密码</em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="passwdChange" method="post" validate="true">
	<div class="main">
		<div class="inmain">
			<div class="wrap">
				<!-- 操作模块 -->
				<div class="operate">
					<input type="submit" class="btnSubmit" title="保 存" value="保 存"/>
					<!-- <input type="button" class="btnCancel" title="返 回" value="返 回" onclick="history.go(-1)"/>
					 -->	
			    </div>
				<div class="operateTab">
					<!--<div class="operateTabInfo">密码错误！</div>-->
					<table id="editPwd" class="operateTabBox w350">
						<tbody>
							<tr class="fOdd">
								<td class="w80 fname">原始密码：</td>
								<td class="fvalue">
								<s:password name="oldpass" size="25" maxlength="25" cssClass="txt w200"/>
								</td>
							</tr>
							<tr class="fEven">
								<td class="fname">新 密 码：</td>
								<td>
								<s:password name="newpass" size="25" maxlength="25" cssClass="txt w200"/>
								</td>
							</tr>
							<tr class="fOdd" >
								<td  class="fname">确认密码：</td>
								<td class="fvalue">
								<s:password name="passagain" size="25" maxlength="25" cssClass="txt w200"/>
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

