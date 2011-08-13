<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
}
function assignTask(){
	if(noChecked()){
          alert("请选择需要分配的催收记录！");
          return false;
     }
     if (confirm("您确定要对该用户分配所选催收任务?")) {
          document.form1.action="nonlawtaskAssign.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
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
}
function query(){
 document.form1.action="caseAssignList.action";
 document.form1.submit();
}
function assign(){
 document.form1.action="caseAssign.action";
 document.form1.submit();
}
</script>
</HEAD>
<body>
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		           <div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	          </td>
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;案件待分配列表</span></td>
            </tr>
          </table>
        </div>
     </TD>
  </TR>
  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    
     <s:form name="form1" method="post" action="caseAssignList">
    <div align="center">
    	<s:hidden name="pagenumber"/>
	
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
         <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
               <TABLE cellSpacing=1 cellPadding=3 width="100%" align=center  border=0>
                  <TBODY>
                 <TR>
                 <TD class="listline" align="left" colspan="3">
                 <s:hidden name="statusid"/>
                 借款人：<s:textfield name="jiekuanren" size="10"/>
                 受理时间：<jscalendar:jscalendar name="thedate" format="%Y-%m-%d" showstime="false"/>
                 受理案号：<s:textfield name="contractno" size="10"/>
                 银行：<s:select name="bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS" headerKey="0" headerValue="全部"/>
                 身份证号码：<s:textfield name="theidcard" size="10"/>
                <input name="submit11" type="button" class="mask"  value="查询" onClick="query()">
                      </TD>
                    </TR>
         
                  </TBODY>
              </TABLE> 
              </TD>
          </TR>
        
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>
     			<TABLE width="100%"  border="0" align="center" cellPadding="3" cellSpacing="1" bgcolor="#F9F9F7">
             
                      <TR class="listheadline">
                      
                    <TD align=center>委托银行</TD>
                      <TD align=center>支行</TD>
                      <TD align=center>委托时间</TD>
                      <TD align=center>借款人</TD>
                      <TD align=center>担保人</TD>
                      <TD align=center>合同编号</TD>
                      <TD align=center>贷款类型</TD>
                      <TD align=center>身份证号码</TD>
                      <TD align=center>贷款金额</TD>
                      <TD align=center>处理</TD>
                   
                      </TR>
                      <s:iterator value="lawcaselist" status="status">
                      <tr class=listline>
                          <TD>${bank }</TD>
                        <TD>${bankbranch }</TD>
                        <TD>${ thedate}</TD>
                         <TD>${ jiekuanren.jiekuanren}</TD>
                          <TD>${ jiekuanren.danbaoren}</TD>
                           <TD>${ contractno}</TD>
                            <TD>${ jiekuanren.jiekuantype}</TD>
                             <TD>${ jiekuanren.theidcard}</TD>
                             <TD>${ jiekuanren.howmuch}</TD>
                                <TD align=center><a href="caseDoView.action?caseid=${caseid }">【处理】</a></TD>
                       </tr>
                      </s:iterator>
               </TABLE>
             </TD>
           </TR>
           <TR bgcolor="#FEF7E9" class="pt9-18">
              <TD colSpan=15 align="right">
                ${lawcasePagestring }
               </TD>
           </TR>
         
		</TABLE>
   </div>
   </s:form>
    
    </TD>
  </TR>
   
  </TABLE>
</body>


</HTML>
