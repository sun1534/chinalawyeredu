<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="dialogBox" id="dialogBoxAdd"> 
				<div class="DialogWrap"> 
					<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">新增MEM设备</div> 
						<form name="deviceCreateEditForm" id="deviceCreateEditForm">
						<table class="operateTabBox"> 
							<tbody> 
							
								<tr class="fEven"> 
									<td class="w120 fname">设备名称：</td> 
									<td class="fvalue">
									<s:textfield name="device.devicename" id="device.devicename" cssClass="txt" size="20"/>
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w120 fname">IP：</td> 
									<td class="fvalue">
									<s:textfield name="device.ip" id="device.ip" cssClass="txt" size="20"/>
									</td> 
								</tr> 
								
								<tr class="fEven"> 
									<td class="w120 fname">端口：</td> 
									<td class="fvalue">
									<s:textfield name="device.port" id="device.port" cssClass="txt" size="20"/>
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w120 fname">连接协议：</td> 
									<td class="fvalue">
										<input type="hidden" name="isnew" value="${isnew}"/>
									<input type="hidden" name="deviceId" value="${deviceId}"/>
									<s:select name="device.ishuawei" list="#{'0':'TELNET','2':'SSH','1':'华为TELNET'}"/>
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w120 fname">是否需要跳转：</td> 
									<td class="fvalue">
									<s:select name="device.istransit" list="#{'0':'不需要','1':'需要'}" onchange="transitview(this.value)"/>
									<script type="text/javascript">
									function transitview(val){
									 if(val==1)
									 document.getElementById("transitid").style.display="";
									 else
									 document.getElementById("transitid").style.display="none";
									}
									</script>
									</td> 
								</tr>
							
								<tr id="transitid" 	<s:if test="device.istransit==0">style="display:none"</s:if>> 
								<td colspan="6">
								
									<table class="operateTabBox"> 
									
										<tr class="fEven"> 
									<td class="w120 fname">跳转IP：</td> 
									<td class="fvalue">
									<s:textfield name="transit.ip" cssClass="txt" size="20"/>
									</td> 
								</tr>
									<tr class="fEven"> 
									<td class="w120 fname">跳转端口：</td> 
									<td class="fvalue">
									<s:textfield name="transit.port" cssClass="txt" size="20"/>
									</td> 
								</tr>
								<tr class="fEven"> 
									<td class="w120 fname">跳转登录用户：</td> 
									<td class="fvalue">
									<s:textfield name="transit.loginname" cssClass="txt" size="20"/>
									</td> 
								</tr>
								<tr class="fEven"> 
									<td class="w120 fname">跳转登录密码：</td> 
									<td class="fvalue">
									<s:textfield name="transit.pwd" cssClass="txt" size="20"/>
									</td> 
								</tr>
									</table>
								</td>
								
									</tr>
								
								
								<!-- 
								<tr class="fEven"> 
									<td class="w120 fname">登录名：</td> 
									<td class="fvalue">
									<s:textfield name="device.loginName" id="device.loginName" cssClass="txt" size="20"/>
									</td> 
								</tr> 
								
								
								<tr class="fEven"> 
									<td class="w120 fname">登录密码：</td> 
									<td class="fvalue">
								
									<s:textfield name="device.loginPwd" id="device.loginPwd" cssClass="txt" size="20"/>
									</td> 
								</tr>
								 --> 
								<tr class="fEven"> 
									<td class="w120 fname">备注信息：</td> 
									<td class="fvalue">
									<s:textarea name="device.description" id="device.description" cssClass="txt" rows="2" cols="35"></s:textarea>
									</td> 
								</tr> 
					
								
							</tbody> 
						</table> 
								</form>
					</div> 
					<div class="dialogBtn">
					<input type="button"  value="保 存" title="保 存" class="btnSubmit" onclick="createEditDevice('${isnew}',${deviceId})"/>　
					<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/></div> 
				</div> 
			</div> 