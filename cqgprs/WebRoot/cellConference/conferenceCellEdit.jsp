<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName }-监控小区参数修改</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
</head>

<body >
	<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>会议小区监控</a>＞<em>小区会议参数修改</em>
				</div>
			</div>
	</div>
	<s:form name="form1" action="conferenceCellEdit" method="post">
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
								<td class="w220 fname">小区名称：</td>
								<td class="fvalue">
								<s:hidden name="cellkey"/>
						     	<s:property value="@com.sxit.netquality.service.BasicSetService@ALL_CELLS[cellkey].cellname"/>（${cellkey }）
								</td>
							</tr>
							<tr class="fEven">
								<td class="fname">监控时间点：</td>
								<td>
									<s:select name="timeview" id="timeview" list="@com.sxit.cellConference.service.CellConferenceService@TIMELIST"></s:select>
								</td>
							</tr>
							<tr class="fOdd" >
								<td  class="fname">对比前一天同时段流量增长率：</td>
								<td class="fvalue">
								<s:textfield name="flowview" size="10" cssClass="txt"/>%
								</td>
							</tr>
							<tr class="fOdd" >
								<td  class="fname">对比前一天同时段用户增长率：</td>
								<td class="fvalue">
								<s:textfield name="userview" size="10" cssClass="txt"/>%
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

