<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * <p>功能： 查看nonlaw列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-18</p>
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
<script type="text/javascript" src="../js/common.js"></script> 
<jscalendar:head/>
<script language="javascript">
<!--
var sum_lendfee=0;
var	sum_curbalancefee=0;
var sum_overfee=0;
var sum_accrualfee=0;
var sum_refee=0;
function getSearch(){
     document.form1.action = "searchUser.action";
     document.form1.submit();
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
		sum_lendfee=sum_curbalancefee=sum_overfee=sum_accrualfee=sum_refee=0;
	 }
	 sum_desc.innerHTML="当前所选任务贷款金额【"+sum_lendfee+"】,贷款余额【"+sum_curbalancefee+"】,逾期本金【"+sum_overfee+"】,逾期利息【"+sum_accrualfee+"】,已还金额为【"+sum_refee+"】";
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
      	//sum_lendfee+=toNum(o[0].value);
		//sum_curbalancefee+=toNum(o[2].value);
		//sum_overfee+=toNum(o[4].value);
		//sum_accrualfee+=toNum(o[6].value);
		//sum_refee+=toNum(o[8].value);
		
		sum_lendfee=accAdd(sum_lendfee,o[0].value);
		sum_curbalancefee=accAdd(sum_curbalancefee,o[2].value);
		sum_overfee=accAdd(sum_overfee,o[4].value);
		sum_accrualfee=accAdd(sum_accrualfee,o[6].value);
		sum_refee=accAdd(sum_refee,o[8].value);
      }   
           // s   +=   toNum(tasks.rows(i).cells(n).innerText);   
     // return s;
  }   
    function change(obj){	
  	var o=obj.parentNode.parentNode.cells(0).childNodes
	if(obj.checked){
		//sum_lendfee+=toNum(o[0].value);
		//sum_curbalancefee+=toNum(o[2].value);
		//sum_overfee+=toNum(o[4].value);
		//sum_accrualfee+=toNum(o[6].value);
		//sum_refee+=toNum(o[8].value);
		
		sum_lendfee=accAdd(sum_lendfee,o[0].value);
		sum_curbalancefee=accAdd(sum_curbalancefee,o[2].value);
		sum_overfee=accAdd(sum_overfee,o[4].value);
		sum_accrualfee=accAdd(sum_accrualfee,o[6].value);
		sum_refee=accAdd(sum_refee,o[8].value);
	 }else{
		//sum_lendfee-=toNum(o[0].value);
		//sum_curbalancefee-=toNum(o[2].value);
		//sum_overfee-=toNum(o[4].value);
		//sum_accrualfee-=toNum(o[6].value);
		//sum_refee-=toNum(o[8].value);
		
		sum_lendfee=accSub(sum_lendfee,o[0].value);
		sum_curbalancefee=accSub(sum_curbalancefee,o[2].value);
		sum_overfee=accSub(sum_overfee,o[4].value);
		sum_accrualfee=accSub(sum_accrualfee,o[6].value);
		sum_refee=accSub(sum_refee,o[8].value);
		
		if(sum_lendfee<0) sum_lendfee=0;
		if(sum_curbalancefee<0) sum_usafee=0;
		if(sum_overfee<0) sum_overfee=0;
		if(sum_accrualfee<0) sum_accrualfee=0;
		if(sum_refee<0) sum_refee=0;
	 }
	 sum_desc.innerHTML="当前所选任务贷款金额【"+sum_lendfee+"】,贷款余额【"+sum_curbalancefee+"】,逾期本金【"+sum_overfee+"】,逾期利息【"+sum_accrualfee+"】,已还金额为【"+sum_refee+"】";
}
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.form1.action="nonlawDeletes.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
 // document.pageForm.pagenumber.value=str;
 // document.pageForm.submit()
  document.form1.pagenumber.value=str;
  document.form1.action="mynonlawtaskList.action";
 document.form1.submit();
  return true;
}
function query(){
 document.form1.action="mynonlawtaskList.action";
 document.form1.submit();
 return true;
}
function nonlawCall(){
document.form1.action="nonlawCall.action";
 document.form1.submit();
 return true;
}
function changePagesize(maxperpage){ 
	var url = location.href; 
	if(url.indexOf("?")>0)
		url=url.substring(0,url.indexOf("?"));
	location.replace(url+"?maxperpage="+maxperpage);
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
              <td width="97%"><span class="sort-title">非诉催收业务&gt;&gt;我的催收任务</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="mynonlawtaskList.action" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
<s:hidden name="maxperpage"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=15 >
			           委托银行：<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					   支行:<s:textfield name="bankname" size="10"/>
					   客户姓名：<s:textfield name="username" size="10"/>
					  证件号码：<s:textfield name="idcard" size="10"/>	
					  委托日期:<jscalendar:jscalendar	name="consigndate" format="%Y-%m-%d"/>	
					    逾期期数:<s:select name="overnum" list="#{-1:'全部',0:'小于3',3:'等于3',4:'大于3'}"/>	
					  <br>
					   当天还款:<jscalendar:jscalendar	name="curdate" format="%Y-%m-%d"/>
					  约定还款:<jscalendar:jscalendar	name="paydate" format="%Y-%m-%d"/>
					  联系标记:<s:select name="canlink" list="#{'':'全部','y':'是','n':'否','yy':'联系亲友','nn':'无法接通'}"/>
					  预警记录:<s:select name="lawflag" list="#{-1:'全部',3:'预警'}"/>	
					  <input type="button" value="查询" class="botton" onclick="query()"/>					
			           </TD>
			          </TR>
			           <tr>
			          <td colSpan=15 class=listline><span id="sum_desc" style="color:red"></span></td>
			          </tr>
			          <TR>
			           <TD align="center" colSpan=15>
			           <table id="tasks"  width="100%">
                      <TR class="listheadline">        
                       <TD>选择</TD>    
                       <td>借据号</td>          
                       <TD >委托银行</TD>
                       <TD >支行名称</td>
                      <TD >客户姓名</TD>
                      
                      <TD >贷款金额</TD>
                      <TD >贷款余额</TD>  
                       <TD>逾期本金</TD>
                      <TD>逾期利息</TD>   
                       <TD>逾期罚息</TD>              
                      <TD >委托时间</TD>
                      <TD >贷款时间</TD>
                      <TD >还款状态</TD>
                      <TD >购房(抵押)地址</TD>
                       <TD >还款记录</TD>                      
                      <TD >催收日志</TD>
             		  <TD >约定还款</TD>	
                      </TR>
<s:iterator value="tasklist" status="status">
                      <TR class=listline>
                      <TD >
                        <input type="hidden" value="${tnlwNonlaw.lendfee}">
                      	<input type="hidden" value="${tnlwNonlaw.curbalancefee}">
                      	<input type="hidden" value="${tnlwNonlaw.overfee}">
                      	<input type="hidden" value="${tnlwNonlaw.accrualfee}">
                      	<input type="hidden" value="${tnlwNonlaw.refee}">
                        <INPUT type="checkbox" name="check" onclick="change(this)">
                        </TD>
                            <TD>${tnlwNonlaw.duebill}</TD>  
                        <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[tnlwNonlaw.bankid+\"\"]"/></TD>
                      <TD>${tnlwNonlaw.bankname}</TD> 
                      <TD >${tnlwNonlaw.username}</TD>
               
                      <TD >${tnlwNonlaw.lendfee}</TD>
                      <TD >${tnlwNonlaw.curbalancefee}</TD>
                      <TD >${tnlwNonlaw.curoverfee}</TD>
                      <TD >${tnlwNonlaw.curaccrualfee}</TD>
                       <TD >${tnlwNonlaw.castfee}</TD>
                      <TD >${tnlwNonlaw.consigndate}</TD>
                      <TD >${tnlwNonlaw.lenddate}</TD>
                      <TD >
                      ${tnlwNonlaw.refee}
                      <s:if test="tnlwNonlaw.repaystatus==3">备注清零</s:if>
                       <s:elseif test="tnlwNonlaw.repaystatus==2">全清</s:elseif>
                      </TD>
                      <TD >${tnlwNonlaw.buyaddr}</TD>
                      <TD><a href="repaylogList.action?nonlawid=${tnlwNonlaw.nonlawid}&pagenumber=${pagenumber}">查看</a></TD>
                      <TD><a href="nonlawlogList.action?nonlawtaskid=${nonlawtaskid}&pagenumber=${pagenumber}">查看</a></TD>
                      <td>
                      	<a href="setPaydate!input.action?nonlawtaskid=${nonlawtaskid}&keepThis=true&TB_iframe=true&height=250&width=400" title="约定还款日期设置" class="thickbox" >
                      		<s:if test="paydate==null">设置</s:if>                    
                      		<s:else>${paydate}</s:else>
                      	</a>
                      </td>
                  </TR>
</s:iterator>
</table>
</TD>
</TR>
<TR bgcolor="#F9F9F7" class="pt9-18">
    <TD colSpan=15 >
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
                      <TD colSpan=15 >
						<div align="center">
							<input class="botton" type=button onclick="nonlawCall()" value="电话催收">
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
<s:form name="pageForm" action="mynonlawtaskList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>

