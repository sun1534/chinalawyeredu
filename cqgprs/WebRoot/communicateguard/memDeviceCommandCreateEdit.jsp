<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="dialogBox" id="dialogBoxAdd"> 
				<div class="DialogWrap"> 
					<a href="#" class="closeDialogBox" onclick="closeDialogBox('#dialogBoxAdd')" title="关闭">关闭</a> 
					<div class="dialogMain"> 
						<div class="operateTabInfo">新增MEM设备命令</div> 
						<form id="commandCreateEditForm" name="commandCreateEditForm">
						<table class="operateTabBox"> 
							<tbody> 
								<tr class="fEven">  
									<td class="w120 fname">请选择设备：</td> 
									<td class="fvalue">
									<s:iterator value="deviceList">
									<s:if test="selectdevice.contains(key)">
									<input type="checkbox" name="selectdevice" value="${key}" checked/>${value}
									</s:if>
									<s:else><input type="checkbox" name="selectdevice" value="${key}"/>${value}
									</s:else>
									</s:iterator>
									
								
									</td> 
								</tr> 
								<tr class="fEven"> 
									<td class="w120 fname">命令名称：</td> 
									<td class="fvalue">
									<s:textfield name="command.commananame" cssClass="txt" size="20"/>
									</td> 
								</tr> 
								
								<tr class="fEven"> 
									<td class="w120 fname">命令脚本：</td> 
									<td class="fvalue">
										<s:hidden name="command.commandtype"/>
									<input type="hidden" name="isnew" value="${isnew }"/>
									<input type="hidden" name="commandId" value="${commandId}"/>
									<s:textarea name="command.commandscript" cssClass="txt" rows="3" cols="40"></s:textarea>
									</td> 
								</tr> 
								
								<s:if test="commandtype!=3">
								<tr class="fEven"> 
									<td class="w120 fname">命令执行解析类：</td> 
									<td class="fvalue">
									
								
								
									
									<s:textfield name="command.plugin" cssClass="txt" cssClass="txt" size="60"></s:textfield>
									</td> 
								</tr>
								</s:if>
								
							</tbody> 
						</table> 
						</form>
					</div> 
					<div class="dialogBtn"><input type="button"  value="保 存" title="保 存" class="btnSubmit" onclick="createEditCommand('${isnew }','${commandId }')"/>　<input type="button"  value="取 消" title="取 消" class="btnBack" onclick="closeDialogBox('#dialogBoxAdd')"/></div> 
				</div> 
			</div> 