<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * 建行的催收记录导出
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

function page(str){
  document.form1.pagenumber.value=str;
  document.form1.action="ccbExportLogs.action";
  document.form1.submit();
  return true;
}
function query(){
 document.form1.action="ccbExportLogs.action";
 document.form1.submit();
 return true;
}
function exportLogs(){
 document.form1.action="ccbExportLogs!export.action";
 document.form1.submit();
 return true;
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
              <td width="97%"><span class="sort-title">信用卡业务&gt;&gt;催收记录列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
         
         
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="ccbExportLogs.action" method="POST">
<s:hidden name="pagenumber"/>
<s:hidden name="maxperpage"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=13 >
			           委托银行:<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					 客户账号:<s:textfield name="creditcard" size="10"/>	 :<s:textfield name="username" size="10"/>
					   <br/>
					    
					  客户账号:<s:textfield name="creditcard" size="10"/>	
					 委托日期:<jscalendar:jscalendar	name="consigndate" format="%Y-%m-%d"/>
					 承办人:<s:textfield name="chengbanren" size="10"/>	
					  预警记录:<s:select name="lawflag" list="#{-1:'全部',3:'预警'}"/>	
					  <input type="button" value="查询" class="botton" onclick="query()"/>		
					  
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      	<s:select name="repaystatus" list="#{1:'部分还款',2:'全清',3:'备注清零'}"></s:select><input type="button"  value="变更还款状态" onclick="updateStat(3)"/>		
			           	
			           </TD>
			          </TR>
			          <tr>
			          <td colSpan=13 class=listline><span id="sum_desc" style="color:red"></span></td>
			          </tr>
			           <TR>
			           <TD align="center" colSpan=13>
			           <table id="tasks"  width="100%">
                      <TR class="listheadline">
                        <TD>选择</TD>
                         <TD >案件编号</TD>
                        <TD >委托银行</TD>
                        <TD >客户姓名</TD>
                        <TD >委托日期</TD>
                        <TD >透支人民币</TD>
                        <TD >透支港币</TD>
                        <TD >透支美元</TD>
                        <TD >透支欧元</TD>
						
                      	<TD >还款金额</TD>
              
                      	<!--  
                    	<TD >导入时间</TD>
                    	-->
                      	<TD >承办人</TD>
                      	<TD >还款记录</TD>
                        <TD >详细信息</TD>
                      </TR>
                     
<s:iterator value="creditcardlist" status="status">
                      <TR class=listline <s:if test="lawflag==3">style="color:red"</s:if>>
                        <TD >
                        <INPUT type="checkbox" value='${creditcardid}' name="check" onclick="change(this)">
                        </TD>
                           <TD >${bianhao}</TD>      
                        <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[bankid+\"\"]"/></TD>
                      <TD >${username}</TD>                      
                      <TD >${consigndate}</TD>
                      <TD >
                      	<input type="hidden" value="${curcnfee}">
                      	<input type="hidden" value="${curusafee}">
                      	<input type="hidden" value="${curhkfee}">
                      	<input type="hidden" value="${cureurfee}">
                      	<input type="hidden" value="${refee}">
                     	
                     	<a href="#" title="点击查看其他币种欠费情况" onclick="showFee('${curusafee}','${curhkfee}','${cureurfee}')"><s:if test="curcnfee==null||curcnfee==\"\"">0</s:if>${curcnfee}<s:else></s:else></a>
                     	
                      </TD>
                  	<TD ><s:if test="curhkfee==null||curhkfee==\"\"">0</s:if>${curhkfee}<s:else></s:else></TD>
                     	<TD ><s:if test="curusafee==null||curusafee==\"\"">0</s:if>${curusafee}<s:else></s:else></TD>
                     	<TD ><s:if test="cureurfee==null||cureurfee==\"\"">0</s:if>${cureurfee}<s:else></s:else></TD>
                      <TD>
                      <!--  
                      还款金额不允许在此处修改
                      <a href="refeeUpdate!input.action?creditcardid=${creditcardid}&keepThis=true&TB_iframe=true&height=200&width=400" title="更新还款金额" class="thickbox" >			
                       	<font color="blue">${refee}</font>
                      </a>
                      -->
                       ${refee}	
                     	
                      <s:if test="repaystatus==3">备注清零</s:if>
                       <s:elseif test="repaystatus==2">全清</s:elseif>
                      
                       <s:if test="lawflag==1">&nbsp;<font color="blue">转诉讼&nbsp;</font></s:if>
                       <s:elseif test="lawflag==2">&nbsp;<font color="red">已结案</font></s:elseif>
                      </TD>
                 
                       <!--TD ><s:property value="@com.changpeng.operation.util.OperationUtil@consigntypeMap[consigntype]"/></TD>
                      <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@consignflagMap[consignflag]"/></TD>
                       
                        <TD ><s:date name="createtime" format="yyyy-MM-dd"/></TD>
                         -->
                      <TD >
                      	<s:if test="state==0">未分配</s:if>
                       	<s:elseif test="state==1">
                       	<a href="reassignTask!input.action?creditcardid=${creditcardid}&keepThis=true&TB_iframe=true&height=250&width=400" title="任务重新分配" class="thickbox" >			
                       	<font color="red"><!-- 已分配 --><s:property value="@com.changpeng.operation.util.OperationUtil@taskuser(creditcardid)"/></font>
                       	</a>
                       	</s:elseif>
                      </TD>
                      <TD><a href="repaylogList.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                        <TD><a href="creditcardView.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
                  
</s:iterator>
</table>
               </TD>
               </TR>
<s:if test="creditcardlist!=null">                    
<TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=13>
                        
                      	<div align="left" style="float:left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选
                     	 <font color="red">(说明：点击承办人姓名可对任务重新分配，点击还款金额可更新任务还款金额)</font>
                      </div>
                      <div align="right">
                      	 每页显示<s:select name='maxperpage' list="#{10:'10',50:'50',100:'100',200:'200',500:'500'}" onchange="changePagesize(this.value)"/>条
                      </div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan="13">
						${pagestring }
                      </TD>
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 >
<div align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;
<input class="botton" type=button onclick="location.href='creditcardCreateBatch!input.action'" value="批量导入">&nbsp;
<input class="botton" type=button onclick="location.href='creditcardUpdateBatch!input.action'" value="资料更新">&nbsp;
<input  class="botton" type=button onclick="return getDelete()" value="删除">&nbsp;

<input  class="botton" type=button onclick="return transfer(2)" value="结案">&nbsp;
<input  class="botton" type=button onclick="return transfer(1)" value="转诉讼">&nbsp;

<input  class="botton" type=button onclick="location.href='repaylogCreateBatch!input.action'" value="还款记录导入">&nbsp;
<input  class="botton" type=button onclick="return exportLogs()" value="催收记录导出">
<input  class="botton" type=button onclick="return exportSelectedLogs()" value="催收记录选择导出">
<input  class="botton" type=button onclick="return exportCCbLogs()" value="建行格式催收记录导出">
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
<s:form name="pageForm" action="creditcardList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="creditcardCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
<s:form name="searchForm" action="creditcardSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
