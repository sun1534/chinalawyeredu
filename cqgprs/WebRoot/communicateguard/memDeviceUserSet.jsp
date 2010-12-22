<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="dialogBox" id="dialogBoxAdd"> 
				<div class="DialogWrap"> 
					<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">MEM设备关联用户</div> 
						<form name="deviceUserSetForm" id="deviceUserSetForm">
						<table class="operateTabBox"> 
							<tbody> 
							
								<tr class="fEven"> 
									<td class="w120 fname">设备名称：</td> 
									<td class="fvalue">
									<s:hidden name="deviceId"/>
									${deviceName }
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w120 fname">关联用户列表：</td> 
									<td class="fvalue">
									<s:checkboxlist name="userIds" list="alluserList" value="useridList"/>
									
									
									</td> 
								</tr> 
								
							
					
								
							</tbody> 
						</table> 
								</form>
					</div> 
					<div class="dialogBtn">
					<input type="button"  value="保 存" title="保 存" class="btnSubmit" onclick="setUserDevice('${deviceId}')"/>　
					<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/></div> 
				</div> 
			</div> 