<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>调查问题</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">		
<style type="text/css">

.button
{
	border-top: #ffffff 1px solid;
	border-left: #ffffff 1px solid;
	border-bottom: #848284 1px solid;
	border-right: #848284 1px solid;
	background-color: #b2d3ff;
	height: 20px;
	color: #000000;
	background-repeat: repeat;
}
		.textfield {
			BORDER-RIGHT: 0px   solid;   
			BORDER-TOP:   0px   solid;   
			BORDER-LEFT:   0px   solid;   
			BORDER-BOTTOM:   1px   solid;
			background:#F4FBFF;
		}	
		body{background:#F4FBFF;font-size:14px;}
		td{border-collapse:collapse;padding-bottom:10px;font-size:12px;line-height:20px}
		th{padding-left:5px;padding-right:5px;padding-top:3px;font-size:14px;font-weight:normal;line-height:20px;border-top:2px solid #76BAC2;background:#B0E0E6;text-align:left;color:#005681;}
.STYLE1 {color: #FF0000}
</style>
<script>
	//获取页面中指定元素位置
	function getElementPos(elementId) {
	 var ua = navigator.userAgent.toLowerCase();
	 var isOpera = (ua.indexOf('opera') != -1);
	 var isIE = (ua.indexOf('msie') != -1 && !isOpera); // not opera spoof
	 var el = document.getElementById(elementId);
	 if(el==null||el.parentNode === null || el.style.display == 'none') {
	  return false;
	 }      
	 var parent = null;
	 var pos = [];     
	 var box;     
	 if(el.getBoundingClientRect)    //IE
	 {         
	  box = el.getBoundingClientRect();
	  var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
	  var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
	  return {x:box.left + scrollLeft, y:box.top + scrollTop};
	 }else if(document.getBoxObjectFor)    // gecko    
	 {
	  box = document.getBoxObjectFor(el); 
	  var borderLeft = (el.style.borderLeftWidth)?parseInt(el.style.borderLeftWidth):0; 
	  var borderTop = (el.style.borderTopWidth)?parseInt(el.style.borderTopWidth):0; 
	  pos = [box.x - borderLeft, box.y - borderTop];
	 } else    // safari & opera    
	 {
	  pos = [el.offsetLeft, el.offsetTop];  
	  parent = el.offsetParent;     
	  if (parent != el) { 
	   while (parent) {  
	    pos[0] += parent.offsetLeft; 
	    pos[1] += parent.offsetTop; 
	    parent = parent.offsetParent;
	   }  
	  }   
	  if (ua.indexOf('opera') != -1 || ( ua.indexOf('safari') != -1 && el.style.position == 'absolute' )) { 
	   pos[0] -= document.body.offsetLeft;
	   pos[1] -= document.body.offsetTop;         
	  }    
	 }              
	 if (el.parentNode) { 
	    parent = el.parentNode;
	   } else {
	    parent = null;
	   }
	 while (parent && parent.tagName != 'BODY' && parent.tagName != 'HTML') { // account for any scrolled ancestors
	  pos[0] -= parent.scrollLeft;
	  pos[1] -= parent.scrollTop;
	  if (parent.parentNode) {
	   parent = parent.parentNode;
	  } else {
	   parent = null;
	  }
	 }
	 return {x:pos[0], y:pos[1]};
	}
	function setRadio(radios,id,logicwenti){
	 v=document.getElementById(id);
	 v.value=radios.value;
	 
	 //逻辑跳转
	 if(logicwenti!=''&&logicwenti!=0){
	 	 var pos=getElementPos("logic"+logicwenti);
	 	 window.scrollTo(pos.x,pos.y);
	 }
	}
	function setCheckbox(checkboxs,id){
	 v=document.getElementById(id);
	 if(checkboxs.checked==true)
	 	v.value+=checkboxs.value+" ";
	 else{
	 	var val=v.value;
	 	val=val.replace(checkboxs.value+" ","");
	 	v.value=val;
	 }	 
	}
	function checkbixu(){
		var obj=document.getElementsByName("bixu");
		var desc=document.getElementsByName("desc");
		var result=true;
		var i=0;
		for(;i<obj.length;i++){
			reply=obj[i].parentNode.childNodes[1];
			if(obj[i].value=="true"&&(reply.value==null||reply.value=="")){
				desc[i].innerHTML="该题为必答题，请选择或填充该问题答案";
				result=false;
			}else{
				desc[i].innerHTML="";
			}				
		}
		if(!result){
			alert("部分未答题未回复");
		}
		return result;	
	}
</script>
	</head>
	<body>
		<TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
			<TR>
				<TD valign="top" bgColor=#F9F9F7>	
	
		<s:form name="form1" action="diaochaReply" onsubmit="return checkbixu()">
		<input type="hidden" name="diaochaid" value="${diaocha.diaochaid}"/>
		
			<TABLE width="100%" border=0 align=center cellPadding=3 cellSpacing=1>
				<TBODY>
					<TR>
						<TD>
							<H2 style="line-height:40px" align="center">
								调查名称:${diaocha.title}
							</H2>
						</TD>
					</TR>
					<TR>
						<TD>
							<P>
								${diaocha.shuoming}
							</p>
						</TD>
					</TR>
					<TR>
						<TD>
							<div align="left" style="color:red">
								&nbsp;&nbsp;&nbsp;&nbsp;注意：输入框后有红色标记的为必答题目
							</div>
						</TD>
					</TR>
					<!-- 该调查存在分类 -->
					<s:if test="hastype">
					<s:iterator value="diaochatypes" status="stat">
				    <tr><th colspan="2" align="left">${typename}</th></tr>
					<s:iterator value="diaochawentis" status="status">
					<TR id="logic${wentiid}">
						<TD>
							<s:hidden name="wentiid"/>
							<input type="hidden" name="other" id="other${wentiid}"/>
							${status.index+1}.${title}&nbsp;&nbsp;
							<s:if test="wentileixing==1||wentileixing==2">
							（<input type="text" name="replys" id="wenti${(stat.index+1)*(status.index+1)}" size="5" class="textfield" readonly/><s:if test="isbixu"><span style="color:red">*</span></s:if>）
							<input type="hidden" name="bixu" value="${isbixu}">
							</s:if>
							<span id="desc" style="color:red"></span>
						</TD>
					</TR>
					<s:if test="wentileixing==1">
					<s:iterator value="diaochaoptions" status="status2">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="radio${(stat.index+1)*(status.index+1)}" onclick="setRadio(this,'wenti${(stat.index+1)*(status.index+1)}','${logicwenti}')" value="${options}" >${options}.${title}
							<s:if test="others"><input type="text" size="80" maxlength="80" class="textfield" onpropertychange="document.getElementById('other${wentiid}').value=this.value" /></s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:if>
					<s:elseif test="wentileixing==2">
					<s:iterator value="diaochaoptions" status="options">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" onclick="setCheckbox(this,'wenti${(stat.index+1)*(status.index+1)}')" value="${options}" >${options}.${title}
							<s:if test="others"><input type="text"  name="other" size="80" maxlength="80" class="textfield" onpropertychange="document.getElementById('other${wentiid}').value=this.value" /></s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:elseif>
					<s:elseif test="wentileixing==3">										
					<TR>
						<TD  class="tab_content">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="replys" size="80" maxlength="80" class="textfield" /><s:if test="isbixu"><span style="color:red">*</span></s:if><input type="hidden" name="bixu" value="${isbixu}">
						</TD>
					</TR>
					</s:elseif>									
					<s:elseif test="wentileixing==4">										
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea name="replys" cols="60" rows="5"></textarea><input type="hidden" name="bixu" value="${isbixu}"><s:if test="isbixu"><span style="color:red">*</span></s:if>
						</TD>
					</TR>
					</s:elseif>	
					</s:iterator>
					</s:iterator>
					</s:if>
					
					<!-- 该调查不存在分类 -->
					<s:else>
					<s:iterator value="wentilist" status="status">
					<TR id="logic${wentiid}">
						<TD>
							<s:hidden name="wentiid"/>
							<input type="hidden" name="other" id="other${wentiid}"/>
							${status.index+1}.${title}&nbsp;&nbsp;
							<s:if test="wentileixing==1||wentileixing==2">
							（<input type="text" name="replys" id="wenti${status.index+1}" size="5" class="textfield"/><s:if test="isbixu"><span style="color:red">*</span></s:if>）
							<input type="hidden" name="bixu" value="${isbixu}">
							</s:if>
							<span id="desc" style="color:red"></span>
						</TD>
					</TR>
					<s:if test="wentileixing==1">
					<s:iterator value="diaochaoptions" status="status2">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" onclick="setRadio(this,'wenti${status.index+1}','${logicwenti}')" value="${options}" name="wenti${status.index+1}">${options}.${title}
							<s:if test="others"><input type="text" size="80" maxlength="80" class="textfield" onpropertychange="document.getElementById('other${wentiid}').value=this.value"/></s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:if>
					<s:elseif test="wentileixing==2">
					<s:iterator value="diaochaoptions" status="options">
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="setCheckbox(this,'wenti${status.index+1}')" value="${options}" name="wenti${status.index+1}">${options}.${title}
							<s:if test="others"><input type="text" size="80" maxlength="80" class="textfield" name="other" onpropertychange="document.getElementById('other${wentiid}').value=this.value"/></s:if>
						</TD>
					</TR>
					</s:iterator>
					</s:elseif>
					<s:elseif test="wentileixing==3">										
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="replys" size="80" maxlength="80" class="textfield" /><s:if test="isbixu"><span style="color:red">*</span></s:if><input type="hidden" name="bixu" value="${isbixu}">
						</TD>
					</TR>
					</s:elseif>									
					<s:elseif test="wentileixing==4">										
					<TR>
						<TD>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<textarea name="replys" cols="60" rows="5"></textarea><input type="hidden" name="bixu" value="${isbixu}"><s:if test="isbixu"><span style="color:red">*</span></s:if>
						</TD>
					</TR>
					</s:elseif>	
					</s:iterator>
					</s:else>
					
				</TBODY>
			</TABLE>
			
			<p align="center">
						<input type="submit" class="button" value=" 提  交 ">
						&nbsp;&nbsp;
						<input type="button" class="button" value=" 查  看 " onclick="location.href='diaochaStat.pl?diaochaid=${diaocha.diaochaid}'">	
			</p>
				
		</s:form>
			</TD>
			</TR>
		</table>
	</body>
</html>