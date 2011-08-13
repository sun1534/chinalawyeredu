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
function changePagesize(maxperpage){ 
	var url = location.href; 
	if(url.indexOf("?")>0)
		url=url.substring(0,url.indexOf("?"));
	location.replace(url+"?maxperpage="+maxperpage);
}
function query(){
document.form1.action="newcaseList.action";
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
}
function deleteit(){
  if(noChecked())
  {
    alert("请选择需要删除的案件");
    return;
  }
  if(confirm("您确定需要删除这个案件嘛?")){
    document.form1.action="caseDelete.action";
     document.form1.submit();
     return true;
  }
  else{
     return false;
  }
}
function commit2manager(){
  if(noChecked())
  {
    alert("请选择需要进行分配的案件");
    return;
  }
  if(confirm("您确定提交到总经理进行案件分配吗?")){
    document.form1.action="caseAssignPre.action";
     document.form1.submit();
     return true;
  }
  else{
     return false;
  }
}
function toexception(){
  if(noChecked())
  {
    alert("请选择需要不符合条件的案件");
    return;
  }
  if(confirm("您确定需要将这些案件转为异常案件吗?")){
    document.form1.action="case2Exception!input.action";
     document.form1.submit();
     return true;
  }
  else{
     return false;
  }
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
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;异常案件列表</span></td>
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
    
      <s:form name="form1" method="post" action="newcaseList">
    <div align="center">
    	<s:hidden name="pagenumber"/>
	<s:hidden name="type" value="${type}" />
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
               <TABLE cellSpacing=1 cellPadding=3 width="100%" align=center  border=0>
                  <TBODY>
                 <TR>
                 <TD class="listline" align="left">
                 <s:hidden name="statusid"/>
                 借款人：<s:textfield name="jiekuanren" size="8"/>
                 受理时间：<jscalendar:jscalendar name="thedate" format="%Y-%m-%d" showstime="false"/>
                 受理案号：<s:textfield name="contractno" size="10"/>
                 身份证号码：<s:textfield name="theidcard" size="10"/>
                 银行：<s:select name="bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS" headerKey="0" headerValue="全部"/>
                <input name="submitquery" type="button" class="mask"  value="查询" onClick="query()">
                      </TD>
                    </TR>
                  </TBODY>
              </TABLE>          
               </TD>
          </TR>
        
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>
  	
     			<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <td align=center>
                      <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox" title="全选">
                      </td>
                      <TD align=center>委托银行</TD>
                      <TD align=center>所在支行</TD>
                      <TD align=center>委托时间</TD>
                      <TD align=center>借款人</TD>
                      <TD align=center>合同编号</TD>
                      <TD align=center>贷款金额</TD>
                      <TD align=center>异常说明</TD>
                      <TD align=center>处理</TD>
                      </TR>
                      <s:iterator value="lawcaselist" status="status">
                      <tr class=listline>
                    <!--     <TD align=center><img src="../images/arr_2.gif"></TD>-->
                      <td align="center"><INPUT type="checkbox" value='${caseid}' name="check">
                      </td>
                        <TD><s:property value="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS[bankid]"/></TD>
                        <TD>${bankbranch }</TD>
                        <TD>${ thedate}</TD>
                        <TD>${ jiekuanren.jiekuanren}</TD>
                        <TD>${ contractno}</TD>
                        <TD>${ jiekuanren.howmuch}</TD>
                        <TD width="30%">${ whynot}</TD>
                        <TD align=center><a href="caseDoView.action?caseid=${caseid }&pagenumber=${pagenumber }&backurl=lawcaseList.action?statusid=3">【处理】</a></TD>
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
    
			  <tr bgcolor="#F9F9F7" class="pt9-18">
              <td colspan=15 align="center">
                 <input name="submitaddbatch" type="button" class="mask"  value="删除" onClick="deleteit()">&nbsp;
                 <input name="submitaddbatch" type="button" class="mask"  value="提交到是事业部总经理分配" onClick="commit2manager()">
               </td>
                </tr>
				</TABLE>
				
				
				 
   </div>
   </s:form>
    
  </TD></TR>
   
  </TABLE>
</body>


</HTML>
