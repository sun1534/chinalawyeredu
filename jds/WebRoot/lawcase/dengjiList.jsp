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
document.form1.action="caselogList.action";
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
/*
function deleteit(){
  if(noChecked())
  {
    alert("请选择需要删除的日志");
    return;
  }
  if(confirm("您确定需要删除这个日志嘛?")){
     document.form1.action="caselogDelete.action";
     document.form1.submit();
     return true;
  }
  else{
     return false;
  }
}
*/
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
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;案件登记表</span></td>
            </tr>
          </table>
        </div>
     </TD>
  </TR>
  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    
     <s:form name="form1" method="post" action="dengjiList">
    <div align="center">
    	<s:hidden name="pagenumber"/>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
       
        
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>
     			<TABLE width="100%"  border="0" align="center" cellPadding="3" cellSpacing="1" bgcolor="#F9F9F7">
             
                      <TR class="listheadline">
                    
                      <TD align=center>案件序号</TD>
                      <TD align=center>委托银行</TD>
                      <TD align=center>借款人</TD>
                      <TD align=center>开庭日期</TD>
                      <TD align=center>开庭时间</TD>
                       <TD align=center>受理机构名称</TD>
                        <TD align=center>审理人</TD>
                         <TD align=center>承办人</TD>
                         <TD align=center>开庭律师</TD>
                         <TD align=center>开庭结果</TD>
                         <TD align=center>开庭绩效</TD>
                         <TD align=center>查看</TD>
                      </TR>
                      <s:iterator value="dengjilist" status="status">
                      <tr class=listline>
                        
                        <TD>${caseid }</TD>
                        <TD>${bankname }</TD>
                        <TD>${ jiekuanren}</TD>
                        <TD>${ kaitingdate}</TD>
                        <TD>${ kaitingtime}</TD>
                         <TD>${ jigou}</TD>
                          <TD>${ faguan}</TD>
                           <TD>${ susongworkname}</TD>
                            <TD>${ kaitinglawyer}</TD>
                             <TD>${ kaitingresult}</TD>
                              <TD>${ kaitingjixiao}</TD>
                                  <TD align=center><a href="caseDoView.action?caseid=${caseid }">【详情】</a></TD>
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
           <!-- 
           <tr bgcolor="#F9F9F7" class="pt9-18">
              <td colspan=15 align="center">
                 <input name="submitaddbatch" type="button" class="mask"  value="删除" onClick="deleteit()">&nbsp;
               </td>
            </tr>
            -->
		</TABLE>
   </div>
   </s:form>
    
    </TD>
  </TR>
   
  </TABLE>
</body>


</HTML>
