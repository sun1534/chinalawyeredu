<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * <p>功能： 查看我的催收任务</p>
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
<link rel="stylesheet" href="../js/thickbox.css" type="text/css" media="screen" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="../js/thickbox-compressed.js"></script> 
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="../js/common.js"></script> 
<script language=javascript>
<!--
var sum_cnfee=0;
var	sum_usafee=0;
var sum_hkfee=0;
var sum_eurfee=0;
var sum_refee=0;
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
		sum_cnfee=sum_usafee=sum_hkfee=sum_eurfee=sum_refee=0;
	 }
	 sum_desc.innerHTML="当前所选任务人民币【"+sum_cnfee+"】,美元【"+sum_usafee+"】,港元【"+sum_hkfee+"】,欧元【"+sum_eurfee+"】,已还金额为【"+sum_refee+"】";
}

function page(str){
  //document.pageForm.pagenumber.value=str;
  //document.pageForm.submit();
  document.form1.action="mycredittaskList.action";
  document.form1.pagenumber.value=str;
  document.form1.submit();
  return true;
}
function query(){
 document.form1.action="mycredittaskList.action";
 document.form1.submit();
 return true;
}

function creditCall(){
	document.form1.action="creditCall.action";
 	document.form1.submit();
 	return true;
}
function exportLogs(){
	document.form1.action="exportLogs.action";
 	document.form1.submit();
 	return true;
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
function changePagesize(maxperpage){ 
	var url = location.href; 
	if(url.indexOf("?")>0)
		url=url.substring(0,url.indexOf("?"));
	location.replace(url+"?maxperpage="+maxperpage);
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
      	var o=tasks.rows(i).cells(0).childNodes;
      	sum_cnfee=accAdd(sum_cnfee,o[0].value);
		sum_usafee=accAdd(sum_usafee,o[2].value);
		sum_hkfee=accAdd(sum_hkfee,o[4].value);
		sum_eurfee=accAdd(sum_eurfee,o[6].value);
		sum_refee=accAdd(sum_refee,o[8].value);
      	//sum_cnfee+=toNum(o[0].value);
		//sum_usafee+=toNum(o[2].value);
		//sum_hkfee+=toNum(o[4].value);
		//sum_eurfee+=toNum(o[6].value);
		//sum_refee+=toNum(o[8].value);
      }   
           // s   +=   toNum(tasks.rows(i).cells(n).innerText);   
     // return s;
  }   
    function change(obj){	
  	var o=obj.parentNode.parentNode.cells(0).childNodes
	if(obj.checked){
	   sum_cnfee=accAdd(sum_cnfee,o[0].value);
		sum_usafee=accAdd(sum_usafee,o[2].value);
		sum_hkfee=accAdd(sum_hkfee,o[4].value);
		sum_eurfee=accAdd(sum_eurfee,o[6].value);
		sum_refee=accAdd(sum_refee,o[8].value);
		//sum_cnfee+=toNum(o[0].value);
		//sum_usafee+=toNum(o[2].value);
		//sum_hkfee+=toNum(o[4].value);
		//sum_eurfee+=toNum(o[6].value);
		//sum_refee+=toNum(o[8].value);
	 }else{
	 	sum_cnfee=accSub(sum_cnfee,o[0].value);
		sum_usafee=accSub(sum_usafee,o[2].value);
		sum_hkfee=accSub(sum_hkfee,o[4].value);
		sum_eurfee=accSub(sum_eurfee,o[6].value);
		sum_refee=accSub(sum_refee,o[8].value);
		//sum_cnfee-=toNum(o[0].value);
		//sum_usafee-=toNum(o[2].value);
		//sum_hkfee-=toNum(o[4].value);
		//sum_eurfee-=toNum(o[6].value);
		//sum_refee-=toNum(o[8].value);
		if(sum_cnfee<0) sum_cnfee=0;
		if(sum_usafee<0) sum_usafee=0;
		if(sum_hkfee<0) sum_hkfee=0;
		if(sum_eurfee<0) sum_eurfee=0;
		if(sum_refee<0) sum_refee=0;
	 }
	 sum_desc.innerHTML="当前所选任务人民币【"+sum_cnfee+"】,美元【"+sum_usafee+"】,港元【"+sum_hkfee+"】,欧元【"+sum_eurfee+"】,已还金额为【"+sum_refee+"】";
}

-->
</script>
<jscalendar:head/>
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
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;我的催收任务</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
         
         
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="mycredittaskList.action" method="POST">
<s:hidden name="pagenumber"/>
<s:hidden name="maxperpage"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=13 >
			           委托银行:<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					  委托类型:<s:select name="consigntype" list="@com.changpeng.operation.util.OperationUtil@consigntypeMap" headerKey="" headerValue="全部"/>
					  委托类别:<s:select name="consignflag" list="@com.changpeng.operation.util.OperationUtil@consignflagMap" headerKey="" headerValue="全部"/>
					  委托日期:<jscalendar:jscalendar	name="consigndate" format="%Y-%m-%d"/>
					  卡号:<s:textfield name="creditcard" size="8"/>
					  姓名:<s:textfield name="username" size="4"/>	
					  <br>
					     案件编号:<s:textfield name="bianhao" size="10"/>		
					  身份证号:<s:textfield name="idcard" size="12"/>	
					  
					  当天还款:<jscalendar:jscalendar	name="curdate" format="%Y-%m-%d"/>
					  约定还款:<jscalendar:jscalendar	name="paydate" format="%Y-%m-%d"/>
					  联系:<s:select name="canlink" list="#{'':'全部','y':'是','n':'否','yy':'联系亲友','nn':'无法接通'}"/>
					  预警:<s:select name="lawflag" list="#{-1:'全部',3:'预警'}"/>	
					  <br>
					  全清:<s:select name="repaystatus" list="#{0:'否',1:'全部',2:'是'}"/>	
					  退单:<s:select name="tdflag" list="#{0:'否',1:'全部',2:'是'}"/>	
					  <input type="button" value="查询" class="botton" onclick="query()"/>					
			           </TD>
			          </TR>
			          <tr>
			          <td colSpan="13" class="listline"><span id="sum_desc" style="color:red"></span></td>
			          </tr>
			 		<TR>
			           <TD align="center" colSpan=13>
			           <table id="tasks"  width="100%">
                      <TR class="listheadline">
                      	<TD>选择</TD>
                      	<TD >案件编号</TD>
                        <TD>委托银行</TD>
                        <TD>客户姓名</TD>
						<td>卡号</td>
                        <TD>身份证</TD>						
                        <TD>透支人民币</TD>
                         <TD >透支港币</TD>
                        <TD >透支美元</TD>
                        <TD >透支欧元</TD>
                        					
                      	<TD>还款金额</TD>
                        <TD>委托日期</TD>						
                        <TD>约定还款</TD>
                        <TD >催收日志</TD>
                      </TR>
<s:iterator value="tasklist" status="status">
                      <TR class=listline <s:if test="toprCreditcard.lawflag==3">style="color:red"</s:if>>
                        <TD >
                        <input type="hidden" value="${toprCreditcard.curcnfee}">
                      	<input type="hidden" value="${toprCreditcard.curusafee}">
                      	<input type="hidden" value="${toprCreditcard.curhkfee}">
                      	<input type="hidden" value="${toprCreditcard.cureurfee}">
                      	<input type="hidden" value="${toprCreditcard.refee}">
                        <INPUT type="checkbox"  name="check" onclick="change(this)">
						
                        </TD>
                          <TD >${bianhao}</TD>
                        <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[toprCreditcard.bankid+\"\"]"/></TD>
                      <TD >${toprCreditcard.username}</TD>
					   <TD >${toprCreditcard.creditcard}</TD>
                       <TD >${toprCreditcard.idcard}</TD>
					  
             		 <TD >
             		 	
                     	
                     	<a href="#" title="点击查看其他币种欠费情况" onclick="showFee('${toprCreditcard.curusafee}','${toprCreditcard.curhkfee}','${toprCreditcard.cureurfee}')"><s:if test="toprCreditcard.curcnfee==null||toprCreditcard.curcnfee==\"\"">0</s:if>${toprCreditcard.curcnfee}<s:else></s:else></a>
                     	
                      </TD>
                  	 	<TD ><s:if test="toprCreditcard.curhkfee==null||toprCreditcard.curhkfee==\"\"">0</s:if>${toprCreditcard.curhkfee}<s:else></s:else></TD>
                     	<TD ><s:if test="toprCreditcard.curusafee==null||toprCreditcard.curusafee==\"\"">0</s:if>${toprCreditcard.curusafee}<s:else></s:else></TD>
                     	<TD ><s:if test="toprCreditcard.cureurfee==null||toprCreditcard.cureurfee==\"\"">0</s:if>${toprCreditcard.cureurfee}<s:else></s:else></TD>
                      <TD>
                       <s:if test="toprCreditcard.repaystatus==3">备注清零</s:if>
                       <s:elseif test="toprCreditcard.repaystatus==2">全清</s:elseif>
                       ${toprCreditcard.refee}
                      </TD> 
                      
                 	 <TD >${toprCreditcard.consigndate}</TD>
                     
					 <td>
                      	<a href="setPaydate!input.action?credittaskid=${credittaskid}&keepThis=true&TB_iframe=true&height=250&width=400" title="约定还款日期设置" class="thickbox" >
                      		<s:if test="paydate==null">设置</s:if>                    
                      		<s:else>${paydate}</s:else>
                      	</a>
                      </td>
                      
                 	   <TD><a href="creditlogList.action?creditcardid=${toprCreditcard.creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
</table>
</TD>
</TR>
<TR bgcolor="#F9F9F7" class="pt9-18">
    <TD colSpan=12 >
			<div align="left" style="float:left">
                <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选
             </div>
             <div align="right">
             	 	每页显示<s:select name='maxperpage' list="#{10:'10',50:'50',100:'100',200:'200',500:'500'}" onchange="changePagesize(this.value)"/>条
             </div>
	</TD>
</TR>
${pagestring}
		<s:if test="tasklist!=null"> 
                  <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 >
						<div align="center">
							<input class="botton" type=button onclick="creditCall()" value="电话催收">
							&nbsp;
							<input class="botton" type=button onclick="exportLogs()" value="导出催收记录">
						</div>
                       </TD>
                    </TR>
          </s:if>
          
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
<s:form name="pageForm" action="mycredittaskList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
