<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK"%>
<%
/**
 * <p>功能： 查看suggest列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-18</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language=javascript>
<!--

function page(str){
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
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
              <td width="97%"><span class="sort-title">问题与建议&gt;&gt;问题与建议列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="suggestDeletes.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <TD width="45%">标题</TD>
                      <TD width="15%">提交用户</TD>
                      <TD width="20%">提交时间</TD>
                      <TD width="10%">查看</TD>
                      <TD width="10%">删除</TD>
                      </TR>
<s:iterator value="suggestlist" status="status">
                      <TR class=listline>
                      <TD >${title}</TD>
                      <TD >${createuser}</TD>
                      <TD ><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></TD>
                      <TD><a href="suggestView.action?suggestid=${suggestid}&pagenumber=${pagenumber}">查看</a></TD>
                      <TD><a href="suggestDelete.action?suggestid=${suggestid}&pagenumber=${pagenumber}">删除</a></TD>
                  </TR>
</s:iterator>
${pagestring}

                  </TBODY>
              </TABLE>
</s:form>

            </TD>
          </TR>
        </TABLE>
    </div>
    
    <div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" border=0>
               <s:form name="form1" action="suggestCreate"  method="post">
               		<s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 	<TR>
						<TD width="35%" class="listline">标  题 : <s:textfield name="suggest.title" size="50"/></TD>
					</TR>
	 			
	 			 	<TR>
						<TD class="listline">
						<FCK:editor id="suggest.contents" height="250" width="80%"
									skinPath="../editor/skins/default/"
									basePath="../" toolbarSet="Basic"	> 
		  		        </FCK:editor>
						</TD>
					</TR>
                    <TR>
                      <TD align="center" class="listline">
                        <INPUT name="insert" type="submit" class="botton" value="提交问题建议">&nbsp;			          
		              </TD>
                    </TR>
                   </s:form>
            
        </TABLE>
    </div>
    
    </TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="suggestList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="suggestCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
