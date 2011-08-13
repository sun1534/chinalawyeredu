<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%

%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">

<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown":""}${curuser.style==3?"green":""}.css" type="text/css"/>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style>
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
    border-bottom-width: 5px;
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
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60"><img src="../images/arr.gif" width="13" height="13"></td>
              <td width="97%"><span class="sort-title">诉讼案件&gt;&gt;案件信息查看</span></td>
            </tr>
          </table>
      </div>
      </TD>
  </TR>
  <TR>
  <TD bgColor=#F9F9F7>
  	<TABLE width="100%" border="0" align="left" cellPadding="3" cellSpacing=1 bgcolor="#F9F9F7">
    	<TR>
        	<TD width="5" >&nbsp;</TD>
        	<TD align="left" >
               <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
               <s:if test="issubmit"> 
                   <input name="submit" type=submit class="mask" onClick="document.submitForm.submit()" value="提交">
               </s:if>      
               <s:if test="isedit">
                   <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
               </s:if>
               <s:iterator value="otherdoes" status="stat">
                    <input name="does" type="button" class="mask" onClick="doesFormSubmit('${actionurl}')" value="${actionname}"/>
               </s:iterator>
            </TD>
            <TD>&nbsp;</TD>
            <TD align="right" > 
                 <s:if test="candelete">
                    <input name="edit2" type=submit class="mask" onClick="deleteit()" value="删除">
                 </s:if>
            </TD>
         	<TD width="5" >&nbsp;</TD>
        </TR>
    </TABLE>
  </TD>	
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    <div align="center">
    	<DIV class="tabs" style="text-align:left">
     <A class="selected" href="caseView.action?caseid=${caseid }">基本信息</A>
     <A class="plain" href="lianInfo.action?caseid=${caseid }&tab=usetab">立案信息</A>
     <A class="plain" href="susongInfo.action?caseid=${caseid }&tab=usetab">诉状材料</A>
     <A class="plain" href="caseLogs.action?caseid=${caseid }">代理日志</A>
     <A class="plain" href="kaitingInfo.action?caseid=${caseid }">开庭信息</A>
     <A class="plain" href="zhixingInfo.action?caseid=${caseid }">执行材料</A>
     <A class="plain" href="jixiaoInfo.action?caseid=${caseid }">案件绩效信息</A>
     
     </DIV>
    <DIV class="personalBar"></DIV>
    
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
			  <br>			  
             <table width="580"  border=0 align=center cellpadding=3 cellspacing=1 >
              <tbody>
				<s:hidden name="pagenumber" value="${pagenumber}"/>
					<TR class="listline">
	                    <TD colspan="12" class="sort-title">基本信息</TD>
	                </TR>
                 	<tr>
                      <td class="listheadline" width="15%">所在银行：</td>
                      <TD class="listline"  width="35%">${lawcase.bank }</TD>
                      <td class="listheadline"  width="15%">所在支行：</td>
                      <TD class="listline"  width="35%">${lawcase.bankbranch }</TD>
                      
				    </tr>
				    <tr>
                      <td class="listheadline">银行分管人：</td>
                      <TD class="listline">${lawcase.bankadmin}</TD>
                      <td class="listheadline">合同编号：</td>
                      <TD class="listline">${lawcase.contractno}</TD>
                    </tr>
                     <tr>
                      <td class="listheadline">案件承办人：</td>
                      <TD class="listline" colspan="3">
                      <s:if test="lawcase.nowworkerid==0">
                     <font color='red'>还未分配</font>
                      </s:if>
                      <s:else> ${lawcase.nowworkername}
                      </s:else>
                      </TD>
                   
                    </tr>
                    
                    <tr>
					  <td class="listheadline">委托日期：</td>
                      <td class="listline">
                   		${awcase.thedate}
                      </td>
                      <td class="listheadline">重复查询日期：</td>
                      <TD class="listline" >
                   		${lawcase.requerytime}
					  </TD>
                    </tr>
                    
                  	 <TR class="listline">
                    	<TD colSpan=12 class="sort-title">借款人信息</TD>
                  	</TR>
                    <tr>
                      <td class="listheadline">借款人姓名：</td>
                      <td class="listline">${lawcase.jiekuanren.jiekuanren}</td>
                      <td class="listheadline">担保人（抵押人/保证人）：</td>
                      <td  class="listline" >${lawcase.jiekuanren.danbaoren}</td>
                    </tr>
                    <tr>
                       <td class="listheadline">身份证号码：</td>
                      <td  class="listline" >${lawcase.jiekuanren.theidcard}</td>
                      <td class="listheadline">贷款帐号：</td>
                      <td class="listline" >${lawcase.jiekuanren.bankno}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">电话号码1：</td>
                      <td class="listline">${lawcase.jiekuanren.thephone1}</td>
                      <td class="listheadline">电话号码2：</td>
                      <td class="listline">${lawcase.jiekuanren.thephone2}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款类型：</td>
                      <td class="listline">${lawcase.jiekuanren.jiekuantype}</td>
                      <td class="listheadline">担保方式：</td>
                      <td  class="listline">${lawcase.jiekuanren.danbaotype}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款金额：</td>
                      <td class="listline">${lawcase.jiekuanren.howmuch}</td>
                      <td class="listheadline">贷款余额：</td>
                      <td  class="listline">${lawcase.jiekuanren.remain}</td>
                    </tr>
                    <tr>
                      <td class="listheadline">贷款起始日期：</td>
                      <td class="listline">
                      ${lawcase.jiekuanren.startdate}
                      </td>
                      <td class="listheadline">贷款终止日期：</td>
                      <td  class="listline">
                  ${lawcase.jiekuanren.enddate}  
                     </td>
                    </tr>
                    
                
                <tr bgcolor="#CCCCCC">
                  <td colspan="4" align="center">
                    <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                  </td>
                </tr>
              </tbody>
            </table>
		    <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
