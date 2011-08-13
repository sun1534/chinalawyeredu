<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>

<%
/**
* authoer:sg
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<jscalendar:head/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
div.tabs {
	font-size: 9pt;
    background: White;
    color: #799C38;
    margin: 0;
    padding: 0;
	background: transparent;
	border-collapse: collapse;
	border-bottom-color: #999999;
	border-bottom-style: solid;
	border-bottom-width: 1px;
	padding: 0.5em 0em 0em 2em;
	white-space: nowrap;

}
div.personalBar {
    /* Bar with personalized menu (user preferences, favorites etc) */
    background: #EFEFEF;
    border-bottom-color: #EFEFEF;
    border-bottom-style: solid;
    border-bottom-width: 10px;
    color: #799C38;
    padding-top: 0px;
    padding-right: 3px;
	padding-bottom: 0px;
    /*text-transform: lowercase;*/
}
/* Rule 3 of ../css/plone.css */
A:link {	
	COLOR: #000000;	
	BACKGROUND-COLOR: transparent;
	TEXT-DECORATION: none
} 
div.tabs a {
    /* The normal, unselected tabs. They are all links */
    background: transparent;
    border-color: #999999;
    border-width: 1px; 
    border-style: solid solid none solid;
    color: #999999; 
    margin-right: 0.5em;
    padding: 0em 1.8em;
    padding-top: 0.1em;
    text-transform: lowercase;
}

div.tabs a.selected {
    /* The selected tab. There's only one of this */
    background: #EFEFEF;
    border: 1px solid #999999;
    border-bottom: #EFEFEF 1px solid;
    color: #000000;
    font-weight: normal;
    padding-top: 0.1em;
}
div.tabs a.plain {
    /* The selected tab. There's only one of this */
	background: #ffffff;
    color: #999999;
    font-weight: normal;
    padding-top: 0.1em;
}
DIV.tabs A:hover {
	BORDER-LEFT-COLOR: #999999; BACKGROUND: #EFEFEF; BORDER-BOTTOM-COLOR: #EFEFEF; COLOR: #000000; BORDER-TOP-COLOR: #999999; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #999999
}
-->
</style>
<script type="text/javascript">
function page(str){
  document.form1.pagenumber.value=str;
  document.form1.submit()
  return true;
}
function query(){
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
              <td width="97%"><span class="sort-title">诉讼业务&gt;&gt;我承办的案件列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>

  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
   <s:form name="form1" action="mycaseList" method="post">
   <s:hidden name="thetype"/>
    <div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        
        <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
               <TABLE cellSpacing=1 cellPadding=3 width="100%" align=center  border=0>
                  <TBODY>
                 <TR>
                 <TD class="listline" align="left">
                 借款人：<s:textfield name="jiekuanren" size="10"/>
                 受理时间：<jscalendar:jscalendar name="thedate" format="%Y-%m-%d" showstime="false"/>
                 受理案号：<s:textfield name="contractno" size="10"/>
                 银行：<s:select name="bankid" list="@com.changpeng.lawcase.util.CommanDatas@CASE_BANKS" headerKey="0" headerValue="全部"/>
                身份证号码：<s:textfield name="theidcard" size="10"/>
                
                      </TD>
                    </TR>
                    <TR>
                 <TD class="listline" align="left">
             案件类型：
             <s:select name="status" list="@com.changpeng.lawcase.util.CommanDatas@CASE_STATUS_TAB" headerKey="" headerValue="全部">
                     </s:select>
                
                <input name="submit11" type="button" class="mask"  value="查询" onClick="query()">
                      </TD>
                    </TR>
                  </TBODY>
              </TABLE>           </TD>
          </TR>
        
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>
  	<DIV class="tabs" align="left">
     <A class=
     <s:if test="thetype==1">"selected"</s:if>
     <s:else>"plain"</s:else>
      href="mycaseList.action?thetype=1">诉讼承办人</A>
      <A class=
     <s:if test="thetype==2">"selected"</s:if>
     <s:else>"plain"</s:else>
      href="mycaseList.action?thetype=2 }">执行承办人</A>
    </DIV>
    <DIV class="personalBar"></DIV>
     			<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <TD align=center>委托银行</TD>
                      <TD align=center>所在支行</TD>
                      <TD align=center>银行分管人</TD>
                      <TD align=center>委托时间</TD>
                      <TD align=center title="查看或修改诉状信息">借款人</TD>
                      <TD align=center>合同编号</TD>
                      <TD align=center>电话</TD>
                      <TD align=center>身份证号码</TD>
                      <TD align=center>贷款金额</TD>
                      <TD align=center>查看</TD>
                      <TD align=center>填写日志</TD>
                      <TD align=center>申请结案</TD>
                      </TR>
                      <s:iterator value="mycaselist" status="status">
                      <tr class=listline>
                        <TD>${bank }</TD>
                        <TD>${bankbranch }</TD>
                        <TD>${bankadmin }</TD>
                        <TD>${ thedate}</TD>
                         <TD><a href="susongCreateEdit!input.action?caseid=${caseid }" title="诉状信息">${ jiekuanren.jiekuanren}</a></TD>
                            <TD>${ contractno}</TD>
                            <TD>${ jiekuanren.thephone1}</TD>
                            <TD>${ jiekuanren.theidcard}</TD>
                            <TD>${ jiekuanren.howmuch}</TD>
                            <TD><a href="caseDoView.action?caseid=${caseid }">查看</a></TD>
                            <TD><a href="caselogWrite!input.action?caseid=${caseid }">填写日志</a></TD>
                             <TD><a href="caselogWrite!input.action?caseid=${caseid }">申请结案</a></TD>
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