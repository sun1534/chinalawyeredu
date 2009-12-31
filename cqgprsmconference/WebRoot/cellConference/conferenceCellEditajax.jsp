<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
			<!-- 新增  --> 
			<div class="dialogBox" id="dialogBoxAdd"> 

		   <script type="text/javascript" src="../js/jquery.form.js"></script> 
		<form id="form11" method="post" action="../cellconferenceajax/conferenceCellSet.action">
				<div class="DialogWrap"> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">监控会议小区参数修改</div> 
						<table class="operateTabBox"> 
							<tbody> 
								<tr class="fEven"> 
									<td class="w200 fname">小区编码：</td> 
									<td class="fvalue">
								<s:property value="@com.sxit.netquality.service.BasicSetService@ALL_CELLS[cellkey].cellname"/>（${cellkey }）
									</td> 
								</tr> 
							
									<tr class="fEven"> 
									<td class="w200 fname">上午监控时间点：</td> 
									<td class="fvalue">
									<s:select name="timeview1" id="timeview1" list="@com.sxit.cellConference.service.CellConferenceService@TIMELIST"></s:select>
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w200 fname">下午监控时间点：</td> 
									<td class="fvalue">
									<s:select name="timeview2" id="timeview2" list="@com.sxit.cellConference.service.CellConferenceService@TIMELIST"></s:select>
									</td> 
								</tr> 
									<tr class="fEven"> 
									<td class="w200 fname">对比前一天同时段流量增长率：</td> 
									<td class="fvalue">
									<input type="text" id="flowview" size="10" value="${flowview }" class="txt"/>%
									</td> 
								</tr> 
									<tr class="fEven"> 
									<td class="w200 fname">对比前一天同时段用户增长率：</td> 
									<td class="fvalue">
									<input type="text" id="userview" size="10" value="${usercview }" class="txt"/>%
									</td> 
								</tr> 
							</tbody> 
						</table> 
					</div> 
					<div class="dialogBtn"><input type="button"  value="保 存" title="保 存" class="btnSubmit" onclick="editcell()"/>　
					<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="$.unblockUI();"/></div> 
	</form>
	<script type="text/javascript" >
	function editcell(){
	    var options={success:showResponse};
		$("#form11").ajaxSubmit(options);
	}
function showResponse(data){
  alert(data);
  $.unblockUI();
//	$.blockUI({ message: data });
}
	
	</script>
				</div> 

