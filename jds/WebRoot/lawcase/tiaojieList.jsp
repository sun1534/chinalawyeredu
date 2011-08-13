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
document.form1.action="tiaojieList.action";
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
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;案件调解列表</span></td>
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
    
      <s:form name="form1" method="post" action="tiaojieList">
    <div align="center">
    	<s:hidden name="pagenumber"/>
	
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
               <TABLE cellSpacing=1 cellPadding=3 width="100%" align=center  border=0>
                  <TBODY>
                 <TR>
                 <TD class="listline" align="left">
                     转入调解时间：<jscalendar:jscalendar name="date" format="%Y-%m-%d" showstime="false"/>
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
                      <td align="center">
                      <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox" title="全选">
                      </td>
                      <TD align=center>委托银行</TD>
                      <TD align=center>所在支行</TD>
                      <TD align=center>委托时间</TD>
                      <TD align=center>借款人</TD>
                      <TD align=center>合同编号</TD>
                      <TD align=center>贷款金额</TD>
                      <TD align=center>调解日志</TD>
                      <TD align=center>处理</TD>
                      </TR>
                      <s:iterator value="tiaojielist" status="status">
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
                        <TD align=center><a href="caselogList.action?jilubeizhu=1&caseid=${caseid }&logtype=1&pagenumber=${pagenumber }">【查看】</a></TD>
                        <TD align=center><a href="caseDoView.action?caseid=${caseid }&pagenumber=${pagenumber }">【处理】</a></TD>
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
