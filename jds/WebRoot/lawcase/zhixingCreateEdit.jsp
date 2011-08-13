<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>

<%
/**
 * <p>功能： 创建employee</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-03-04</p>
 * @版本： V1.0
 * @修改：
**/
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
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%">
              
              <span class="sort-title">诉讼案件&gt;&gt;
              <s:if test="!isedit">
              承办人填写执行材料
              </s:if>
              <s:else>
              承办人修改执行材料
              </s:else>
              </span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
			  <br>			  
             <table width="90%"  border=0 align=center cellpadding=3 cellspacing=1 >
              <tbody>
                <s:form name="form1" action="zhixingCreateEdit" enctype="multipart/form-data" method="post">
			        <TR class="listheadline">
	                    <TD>银行：</TD>
	                     <TD class="listline">${lawcase.bank}</TD>
	                      <TD>支行：</TD>
	                       <TD class="listline">${lawcase.bankbranch }</TD>
	                </TR>
					<TR class="listheadline">
	                    <TD>贷款人：</TD>
	                     <TD class="listline">${lawcase.jiekuanren.jiekuanren}</TD>
	                      <TD>委托日期：</TD>
	                       <TD class="listline">${lawcase.thedate }</TD>
	                </TR>
	                <TR class="listheadline">
	                    <TD>贷款金额：</TD>
	                     <TD class="listline">${lawcase.jiekuanren.howmuch}</TD>
	                      <TD>贷款余额：</TD>
	                       <TD class="listline">${lawcase.jiekuanren.remain }</TD>
	                </TR>
	                 <s:if test="susong.statusid==0">
	                  <TR class="listheadline">
	                   
	                     <TD class="listline" align="left"><font color="red">执行材料还未进行审核</font></TD>
	                     </TR>
	                </s:if>
	                <s:if test="susong.statusid==2">
	                  <TR>
	                    <TD class="listline" align="left"><font color="blue">执行材料审核未通过。原因：${susong.cfmcontent}</font>(<s:date name="susong.confirmtime" format="yyyy-MM-dd HH:mm:ss"/>)</TD>
	                     </TR>
	                </s:if>
	                  <s:if test="susong.statusid==1">
	                  <TR>
	                    <TD class="listline" align="left">执行材料审核通过(<s:date name="susong.confirmtime" format="yyyy-MM-dd HH:mm:ss"/>)</TD>
	                     </TR>
	                </s:if>
	                 <TR class="listheadline">
	                    <TD colspan="4" align="left"  class="listline">
	                 执行材料内容：
	                    </TD>
	                </TR>
                 	<tr>
                      <td class="listline" colspan="4">
                      	<FCK:editor id="thecontent" height="450" width="100%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
		                >${thecontent}
		                </FCK:editor>
		                	<s:hidden name="isedit"/>
				<s:hidden name="caseid"/>
				<s:hidden name="nodeid"/>
				<s:hidden name="actionid"/>
				<s:hidden name="tonext"/>
				<s:hidden name="pagenumber"/>
                      </td>
				    </tr>
				       <TR class="listheadline">
	                    <TD align="left"  class="listline">
	               执行材料其他电子档上传：
	                    </TD>
	                    <TD colspan="3" align="left"  class="listline">
	               <s:file name="upload" size="20"/>
	               <s:file name="upload" size="20"/>
	               <s:file name="upload" size="20"/>
	               <s:file name="upload" size="20"/>
	               <s:file name="upload" size="20"/>
	                    </TD>
	                </TR>
                <tr bgcolor="#CCCCCC">
                  <td align="center" colspan="4">
                    <input name="insert" type="submit" class="botton" value="保存">
            &nbsp;
                    <input name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
                  </td>
                </tr>
                </s:form>
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
