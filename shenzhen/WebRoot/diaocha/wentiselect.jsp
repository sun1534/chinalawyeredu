<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>单选题列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<link href="../css/css.css" rel="stylesheet" type="text/css">
<script>
	var wentiid=0;
	function setWentiid(val){
		wentiid=val;
	}
	function setConfirm(){
		if(wentiid!=0){
			setLogic(wentiid);
		}
		self.close();
	}
	function setLogic(wentiid){
		var url="../diaochaajax/setLogic.pl?optionid=${optionid}&wentiid="+wentiid;
		var myAjax = new Ajax.Request(url,{method: 'get',  onComplete: {}});
	}

</script>
</head>
<body topmargin="0" leftmargin="0" style="font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12px;">
<div style="text-align:left;padding-top:3px;color:blue;" > 
   请选择逻辑跳转结果：
</div>
<div style="border:1px solid #666;padding-left:5px;vaglin:top;height:230px;overflow:auto;overflow-x:hidden;">	
	<s:if test="hastype">
		<s:iterator value="diaochatypes" status="stat">
			<div>${typename}</div>
			
			<s:iterator value="diaochawentis" status="status">
				<div><input type="radio" name="wentiid" value="${wentiid}" onclick="setWentiid(this.value)" <s:if test="wentiid==logicwenti">checked</s:if>>${title}</div>
			</s:iterator>
		</s:iterator>	
	</s:if>
	<s:else>
		<s:iterator value="wentilist" status="status">
			<div><input type="radio" name="wentiid" value="${wentiid}" onclick="setWentiid(this.value)" <s:if test="wentiid==logicwenti">checked</s:if>>${title}</div>
		</s:iterator>
	</s:else>

</div>
 <div style="text-align:center;padding-top:3px;" > 
   <input type="button" value=" 确 认 " class="button1" onclick="setConfirm()"/>
</div>
   
</body>
</html>