<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
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
<jscalendar:head/>
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
              
              <span class="sort-title">诉讼案件&gt;&gt;审核监督岗审核执行材料(借款人/委托日期:${lawcase.jiekuanren.jiekuanren }/${lawcase.thedate })
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
                <s:form name="form1" action="zhixingConfirm" method="post">
                <s:fielderror/>
                <s:actionerror/>
               
				<s:hidden name="pagenumber" value="${pagenumber}"/>
			   <s:if test="susong.statusid==2">
	                  <TR>
	                    <TD align="right" class="listheadline">上次审核时间</TD>
	                     <TD class="listline">
	                     <s:date name="zhixing.confirmtime" format="yyyy-MM-dd HH:mm:ss"/>
	                     </TD>
	                   </TR>
	                   <TR>
	                    <TD align="right" class="listheadline">上次审核未通过说明</TD>
	                     <TD class="listline">${zhixing.cfmcontent}</TD>
	                   </TR>
	                </s:if>
                 	<tr>
                      <td class="listheadline" width="30%" align="right">
                    审核结果
                      </td>
                      <TD class="listline">
                 
                      <s:radio name="tonext" list="#{'pass':'通过','nopass':'不通过'}"/>
                      <s:hidden name="actionid"/>
                      <s:hidden name="nodeid"/>
                      <s:hidden name="caseid"/>
                    
                     </TD>
				    </tr>
				    <tr>
				    <td class="listheadline" width="20%" align="right">
                    审核说明：
                      </td>
                      <TD class="listline">
                      <s:textarea name="zhixing.cfmcontent" rows="8" cols="55"></s:textarea>
		                <font color="red">必须输入,长度不超过500汉字</font>
                     </TD>
				    </tr>
                  <tr>
				    <td class="listheadline" colspan="2" align="left">
                   执行材料
                      </td>
                      </tr>
                      <tr>
                      <TD class="listline" colspan="2"> 
                      <div>
                      <s:hidden name="canedit" id="canedit"/>
                  	<FCK:editor id="thecontent" height="450" width="100%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="custom"
		                >${thecontent}
		                </FCK:editor>
                      </div>
                     </TD>
				    </tr>
                
                <tr bgcolor="#CCCCCC">
                  <td colspan="4" align="center">
                    <input name="insert" type="submit" class="botton" value="修改材料" onclick="canchange()">
            &nbsp;
                    <input name="insert" type="submit" class="botton" value="审核确认">
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
