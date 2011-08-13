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
  document.form1.pagenumber.value=str;
  document.form1.submit()
  return true;
}
function query(){
document.form1.action="lawcaseList.action";
 document.form1.submit();
}
function add(){
document.form1.action="caselawCreateEditPre.action";
 document.form1.submit();
}
function batchadd(){
document.form1.action="caselawCreateBatch!input.action";
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
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;<s:property value="@com.changpeng.lawcase.util.CommonDatas@CASE_STATUS_TAB[statusid]"/></span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <tr>
  	<td>

  	</td>
  </tr>
  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    
      <s:form name="form1" method="post" action="lawcaseList">
    <div align="center">
    	<s:hidden name="pagenumber"/>
	<s:hidden name="type" value="${type}" />
<s:hidden name="caseid"/>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        
        <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
               <TABLE cellSpacing=1 cellPadding=3 width="100%" align=center  border=0>
                  <TBODY>
                 <TR>
                 <TD class="listline" align="left">
                 <s:hidden name="statusid"/>
                 借款人：<s:textfield name="jiekuanren" size="10"/>
                 受理时间：<jscalendar:jscalendar name="thedate" format="%Y-%m-%d" showstime="false"/>
                 受理案号：<s:textfield name="contractno" size="10"/>
                 银行：<s:select name="bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS" headerKey="0" headerValue="全部"/>
                 
                身份证号码：<s:textfield name="theidcard" size="10"/>
                <input name="submitquery" type="button" class="mask"  value="查询" onClick="query()">
                <input name="submitadd" type="button" class="mask"  value="新增案件" onClick="add()">
                <input name="submitaddbatch" type="button" class="mask"  value="批量新增案件" onClick="batchadd()">
                
                
                      </TD>
                    </TR>
                  </TBODY>
              </TABLE>           </TD>
          </TR>
        
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>
  	
     			<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <TD align=center>委托银行</TD>
                      <TD align=center>所在支行</TD>
                      <TD align=center>银行分管人</TD>
                      <TD align=center>委托时间</TD>
                      <TD align=center>借款人</TD>
                      <TD align=center>担保人</TD>
                      <TD align=center>合同编号</TD>
                      <TD align=center>贷款类型</TD>
                      <TD align=center>担保方式</TD>
                      <TD align=center>电话</TD>
                      <TD align=center>身份证号码</TD>
                      <TD align=center>贷款金额</TD>
                      <TD align=center>贷款起始日</TD>
                      <TD align=center>贷款终止日</TD>
                      </TR>
                      <s:iterator value="lawcaselist" status="status">
                      <tr class=listline>
                    <!--     <TD align=center><img src="../images/arr_2.gif"></TD>-->
                        <TD><s:property value="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS[bankid]"/></TD>
                        <TD>${bankbranch }</TD>
                        <TD>${bankadmin }</TD>
                        <TD>${ thedate}</TD>
                         <TD>${ jiekuanren.jiekuanren}</TD>
                          <TD>${ jiekuanren.danbaoren}</TD>
                           <TD>${ contractno}</TD>
                            <TD>${ jiekuanren.jiekuantype}</TD>
                             <TD>${ jiekuanren.danbaotype}</TD>
                              <TD>${ jiekuanren.thephone1}</TD>
                             <TD>${ jiekuanren.theidcard}</TD>
                             <TD>${ jiekuanren.howmuch}</TD>
                            <TD>${ jiekuanren.startdate}</TD>
                            <TD>${ jiekuanren.enddate}</TD>
                       </tr>
                      </s:iterator>
                   </TBODY>
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
    
  </TD></TR>
   
  </TABLE>
</body>


</HTML>
