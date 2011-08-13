<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
* authoer:sg
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
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
  document.pageForm.pagenumber.value=str;
  document.pageForm.submit()
  return true;
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
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;信息列表</span></td>
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
    <div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
  			<TR>
     		<TD align=center valign="top" bgColor=#F9F9F7>
  	<DIV class="tabs">
  	<s:iterator value="typeList" status="status">
     <A class=
     <s:if test="type==typeid ||type==null||type.equals(\"\")">"selected"</s:if>
     <s:else>"plain"</s:else>
      href="infoViewList.action?type=${typeid }">${name }</A>
	</s:iterator>
    </DIV>
    <DIV class="personalBar"></DIV>
     			<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <td>&nbsp;</td>
                      <TD align=center >
                         标题
                      </TD>
                      <TD align=center>时间</TD>
                      <TD align=center>发布部门</TD>
                      <TD align=center>查看</TD>
                      </TR>
                      <s:iterator value="inf" status="status">
                      <tr class=listline>
                        <TD align=center><img src="../images/arr_2.gif"></TD>
                        <TD><a href="infoWebView.action?infoid=${infoid}&pagenumber=${pagenumber}">${infoname}</a></TD>
                        <TD>
                          <div align="center"><s:date name="createtime" format="yyyy-MM-dd"/> </div></TD>
                        <TD><div align="center">${department.departmentname}</div></TD>
                        <TD><a href="infoWebView.action?infoid=${infoid}&pagenumber=${pagenumber}">查看</TD>
                       </tr>
                      </s:iterator>
                   </TBODY>
               </table>
             </TD>
           </TR>
    		<TR bgcolor="#FEF7E9" class="pt9-18">
                 <TD colSpan=5 align=center>
                    <s:if test="recordsize>0">
                      <div align="right" bgcolor="#FEF7E9">
                      <span class="pt9-18">
                       共<font color=red><b><s:property value="recordsize"/></b></font>记录
                    第<font color=red><b><s:property value="pagenumber+1"/></b></font>页/
                    共<font color=red><b><s:property value="pagesize"/></b></font>页
                      </span>
                      <s:if test="pagesize==1">
                       <font color="#bbbbbb">
                         首页 前页 后页 末页
                       </font>
                      </s:if>
                      <s:else>
                      <s:if test="pagenumber+1==1">
                       <font color="#bbbbbb">
                        首页 前页
                       </font>
                      </s:if>
                      <s:else>
                        <a href="javascript:page(0)">首页</a>
                        <a href="javascript:page(<s:property value="pagenumber-1"/>)">前页</a>
                      </s:else>
                      <s:if test="pagenumber+1==pagesize">
                       <font color="#bbbbbb">
                        后页 末页
                       </font>
                      </s:if>
                      <s:else>
                        <a href="javascript:page(<s:property value="pagenumber+1"/>)">后页</a>
                        <a href="javascript:page(<s:property value="pagesize-1"/>)">末页</a>
                      </s:else>
                    </s:else>
                    </div>
                    </s:if>
                 </TD>
            </TR>
			<TR bgcolor="#F9F9F7" class="pt9-18">
				<TD colSpan=5>
					<div align="center">
						<input class="botton" type=button
							onClick="document.searchForm.submit()" value="查询">
						&nbsp;
					</div>
				</TD>
			</TR>
				</TABLE>
   </div></TD></TR>
   
  </TABLE>
</body>
<s:form name="pageForm" action="infoViewList.action" method="POST">
	<s:hidden name="pagenumber"/>
	<s:hidden name="type" value="${type}"/>
</s:form>
<s:form name="searchForm" action="infoSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
</HTML>
