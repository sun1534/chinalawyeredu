<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * <p>功能： 查看creditcard列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/xtheme-gray.css" />
<link rel="stylesheet" href="../js/thickbox.css" type="text/css" media="screen" />
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="userselect.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="../js/thickbox-compressed.js"></script> 
<script type="text/javascript" src="../js/common.js"></script> 
<jscalendar:head/>
<script language=javascript>
<!--
var sum_cnfee=0;
var	sum_usafee=0;
var sum_hkfee=0;
var sum_eurfee=0;
function changePagesize(maxperpage){ 
	var url = location.href; 
	if(url.indexOf("?")>0)
		url=url.substring(0,url.indexOf("?"));
	location.replace(url+"?maxperpage="+maxperpage);
}
function noChecked() {
     var i;
     if(document.form1.check!=null){
       if(document.form1.check.length!=null){
            for(i=0;i<document.form1.check.length;i++){
                 if(document.form1.check[i].checked==true){
                      return false;
                 }
            }
       }else{
            if(document.form1.check.checked==true) return false;
       }
     }
     return true;
}
function getCheckAll(){
     var i;
     var b=0;
     if(document.form1.check!=null){
          if(document.form1.check.length!=null){
               for(i=0;i<document.form1.check.length;i++){
                    document.form1.check[i].checked=document.form1.selectAll.checked;
               }
          }else{
               document.form1.check.checked=document.form1.selectAll.checked;
          }
     }
     if(document.form1.selectAll.checked){
		calCol();
	 }else{
		sum_cnfee=sum_usafee=sum_hkfee=sum_eurfee=0;
	 }
	 sum_desc.innerHTML="当前所选任务人民币【"+sum_cnfee+"】,美元【"+sum_usafee+"】,港元【"+sum_hkfee+"】,欧元【"+sum_eurfee+"】";
}

function page(str){
  //document.pageForm.pagenumber.value=str;
  //document.pageForm.submit();
  document.form1.pagenumber.value=str;
  document.form1.action="credittaskList.action";
  document.form1.submit();
  return true;
}
function query(){
 document.form1.action="credittaskList.action";
 document.form1.submit();
 return true;
}
function assignTask(){
	if(noChecked()){
          alert("请选择需要分配的催收记录！");
          return false;
     }
     if (confirm("您确定要对该用户分配所选催收任务?")) {
          document.form1.action="credittaskAssign.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}

function   toNum(str){   
	try{
		n   = parseInt(str);  
	}catch(e){
		n=0;
	}
    if(isNaN(n))   return   0;   
    else   return   n   ;   
}   
 function   calCol(){   //计算指定列之和   
      //var   s=0;     
      sum_cnfee=sum_usafee=sum_hkfee=sum_eurfee=0;
      for(i=1;i<tasks.rows.length;i++){
      	var o=tasks.rows(i).cells(5).childNodes;
      	sum_cnfee=accAdd(sum_cnfee,o[0].value);
		sum_usafee=accAdd(sum_usafee,o[2].value);
		sum_hkfee=accAdd(sum_hkfee,o[4].value);
		sum_eurfee=accAdd(sum_eurfee,o[6].value);
      	//sum_cnfee+=toNum(o[0].value);
		//sum_usafee+=toNum(o[2].value);
		//sum_hkfee+=toNum(o[4].value);
		//sum_eurfee+=toNum(o[6].value);
      }   
           // s   +=   toNum(tasks.rows(i).cells(n).innerText);   
     // return s;
  }   
  function change(obj){	
  	var o=obj.parentNode.parentNode.cells(5).childNodes
	if(obj.checked){
	 	sum_cnfee=accAdd(sum_cnfee,o[0].value);
		sum_usafee=accAdd(sum_usafee,o[2].value);
		sum_hkfee=accAdd(sum_hkfee,o[4].value);
		sum_eurfee=accAdd(sum_eurfee,o[6].value);
		//sum_cnfee+=toNum(o[0].value);
		//sum_usafee+=toNum(o[2].value);
		//sum_hkfee+=toNum(o[4].value);
		//sum_eurfee+=toNum(o[6].value);
	 }else{
	 	sum_cnfee=accSub(sum_cnfee,o[0].value);
		sum_usafee=accSub(sum_usafee,o[2].value);
		sum_hkfee=accSub(sum_hkfee,o[4].value);
		sum_eurfee=accSub(sum_eurfee,o[6].value);
		//sum_cnfee-=toNum(o[0].value);
		//sum_usafee-=toNum(o[2].value);
		//sum_hkfee-=toNum(o[4].value);
		//sum_eurfee-=toNum(o[6].value);
		if(sum_cnfee<0) sum_cnfee=0;
		if(sum_usafee<0) sum_usafee=0;
		if(sum_hkfee<0) sum_hkfee=0;
		if(sum_eurfee<0) sum_eurfee=0;
	 }
	 sum_desc.innerHTML="当前所选任务人民币【"+sum_cnfee+"】,美元【"+sum_usafee+"】,港元【"+sum_hkfee+"】,欧元【"+sum_eurfee+"】";
}

function showFee(usafee,hkfee,eurfee){
	var fee="";
	if(usafee!="")
		fee="透支美元:"+usafee;
	if(hkfee!="")
		fee+="  透支港元:"+hkfee;
	if(eurfee!="")
		fee+="  透支欧元:"+eurfee;
	if(fee!="")
		alert(fee);
}

function _export(){
 document.form1.action="credittaskList!export.action";
 document.form1.submit();
 return true;
}

-->
</script>
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">信用卡业务&gt;&gt;催收任务分配</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
         
         
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="credittaskList.action" method="POST">
<s:hidden name="pagenumber"/>
<s:hidden name="maxperpage"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD>
			           委托银行：<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					  委托类型：<s:select name="consigntype" list="@com.changpeng.operation.util.OperationUtil@consigntypeMap" headerKey="" headerValue="全部"/>
					  委托类别：<s:select name="consignflag" list="@com.changpeng.operation.util.OperationUtil@consignflagMap" headerKey="" headerValue="全部"/>
					 状态：<s:select name="state" list="#{-1:'全部',0:'未分配',1:'已分配'}"/>
					<br>
					   案件编号:<s:textfield name="bianhao" size="10"/>	
					 委托日期：<jscalendar:jscalendar	name="consigndate" format="%Y-%m-%d"/>
					承办人：<s:textfield name="chengbanren" size="10"/>	
					<!--  
					  客户账号：<s:textfield name="creditcard" size="10"/>	
					  -->
					  <input type="button" value="查询" class="botton" onclick="query()"/>					
			           </TD>
			          </TR>
			         
			          <TR class=listline >
			           <TD align="center">
			           <table>
			           	<tr class=listline>
			           		<td>请选择催收员：</td>
			           		<td><s:select name="userid" listKey="userid" listValue="username" list="userlist" id="userselsect"/></td>
			           		<td>
			           		<input type="button" value="任务分配" class="botton" onclick="assignTask()"/>
			           		<input type="button" value="批量分配" class="botton" onclick="location.href='assignBatch!input.action'"/>
			           		<span id="sum_desc" style="color:red"></span></td>
			           	</tr>					
			           </table>
			           </TD>
			          </TR>
			          <TR>
			           <TD align="center">
			           <table id="tasks"  width="100%">
			           	<TR class="listheadline">
                        <TD>选择</TD>
                         <TD >案件编号</TD>
                        <TD >委托银行</TD>
                        <TD >客户姓名</TD>
                        <TD >家庭住址</TD>
                        
                        <TD >委托日期</TD>
                        <TD >透支人民币</TD>
					    <TD >透支港币</TD>
                        <TD >透支美元</TD>
                        <TD >透支欧元</TD>
                      	<TD >逾期状态</TD>
                      	<TD >委托类型</TD>
                      	<TD >委托类别</TD>
                        <TD >承办人</TD>
                        <TD >详细信息</TD>
                        <TD >还款记录</TD>
                        <TD >催收日志</TD>
                      </TR>
<s:iterator value="creditcardlist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${creditcardid}' name="check" onclick="change(this)">
                        </TD>
                         <TD >${bianhao}</TD>
                        <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[bankid+\"\"]"/></TD>
                      <TD >${username}</TD>
                      <TD >${homeaddr}</TD>
                      <TD >${consigndate}</TD>
                      <TD >
                      	<input type="hidden" value="${curcnfee}">
                      	<input type="hidden" value="${curusafee}">
                      	<input type="hidden" value="${curhkfee}">
                      	<input type="hidden" value="${cureurfee}">
                      	
                     	<a href="#" title="点击查看其他币种欠费情况" onclick="showFee('${curusafee}','${curhkfee}','${cureurfee}')"><s:if test="curcnfee==null||curcnfee==\"\"">0</s:if>${curcnfee}<s:else></s:else></a>
                     	
                      </TD>
                  	                  	<TD ><s:if test="curhkfee==null||curhkfee==\"\"">0</s:if>${curhkfee}<s:else></s:else></TD>
                     	<TD ><s:if test="curusafee==null||curusafee==\"\"">0</s:if>${curusafee}<s:else></s:else></TD>
                     	<TD ><s:if test="cureurfee==null||cureurfee==\"\"">0</s:if>${cureurfee}<s:else></s:else></TD>
                      <TD >${overstat}</TD>
                 
                      <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@consigntypeMap[consigntype]"/></TD>
                      <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@consignflagMap[consignflag]"/></TD>
                      <TD >
                      	<s:if test="state==0">未分配</s:if>
                       	<s:elseif test="state==1">
                       	<a href="reassignTask!input.action?creditcardid=${creditcardid}&keepThis=true&TB_iframe=true&height=250&width=400" title="任务重新分配" class="thickbox" >			
                       	<font color="red"><!-- 已分配 --><s:property value="@com.changpeng.operation.util.OperationUtil@taskuser(creditcardid)"/></font>
                       	</a>
                       	</s:elseif>
                      </TD>
                        <TD><a href="creditcardView.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                        <TD><a href="repaylogList.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                 	   <TD><a href="creditlogList.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                 	
                  </TR>
</s:iterator>
			           </table>
			           </TD>
			           </TR>
                      

				<TR bgcolor="#ECECFF" class="pt9-18">
                      <TD>
                      	<div align="left" style="float:left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选
                      </div>
                      <div align="right">
                      	 	每页显示<s:select name='maxperpage' list="#{10:'10',50:'50',100:'100',200:'200',500:'500'}" onchange="changePagesize(this.value)"/>条
                      </div>
                      </TD>
                    </TR>
                    ${pagestring}
                      <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 >
						<div align="center">
						 <input class="botton" type=button onclick="_export()" value="批量导出">&nbsp;
						 <input class="botton" type=button onclick="location.href='creditReassignBatch!input.action'" value="重新分配">
						</div>
                       </TD>
                    </TR>
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="credittaskList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
