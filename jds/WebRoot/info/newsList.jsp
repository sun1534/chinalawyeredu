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
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css"> 
<style type="text/css">
<!--
div.tabs {
	font-size: 9pt;
    background: White;
    color: Black;
    margin: 0;
    padding: 0;
	background: transparent;
	border-collapse: collapse;
	border-bottom-color: #8CACBB;
	border-bottom-style: solid;
	border-bottom-width: 1px;
	padding: 0.5em 0em 0em 2em;
	white-space: nowrap;

}
div.personalBar {
    /* Bar with personalized menu (user preferences, favorites etc) */
    background: #DEE7EC;
    border-bottom-color: #DEE7EC;
    border-bottom-style: solid;
    border-bottom-width: 8px;
    color: Black;
    padding-top: 0.1em;
    padding-right: 3em;
    /*text-transform: lowercase;*/
}
/* Rule 3 of ../css/plone.css */
A:link {	
	COLOR: #436976;	
	BACKGROUND-COLOR: transparent;
	TEXT-DECORATION: none
} 
div.tabs a {
    /* The normal, unselected tabs. They are all links */
    background: transparent;
    border-color: #8CACBB;
    border-width: 1px; 
    border-style: solid solid none solid;
    color: #436976;
    margin-right: 0.5em;
    padding: 0em 1.8em;
    padding-top: 0.1em;
    text-transform: lowercase;
}
div.tabs a.selected {
    /* The selected tab. There's only one of this */
    background: #DEE7EC;
    border: 1px solid #8CACBB;
    border-bottom: #DEE7EC 1px solid;
    color: #436976;
    font-weight: normal;
    padding-top: 0.1em;
}
DIV.tabs A:hover {
	BORDER-LEFT-COLOR: #8cacbb; BACKGROUND: #dee7ec; BORDER-BOTTOM-COLOR: #dee7ec; COLOR: #436976; BORDER-TOP-COLOR: #8cacbb; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #8cacbb
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
  	<DIV class="tabs">
     <A class="selected" href="infoViewList.action?type=1">新闻列表</A>
     <A class="plain" href="infoViewList.action?type=2">制度列表</A> 
     <A class="plain" href="infoViewList.action?type=3">公告列表</A> 
     <A class="plain" href="infoViewList.action?type=4">文件列表</A> 
    </DIV>
    <DIV class="personalBar"></DIV>
  	</td>
  </tr>
  
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF>
    <div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
  			<TR>
     		<TD align=middle valign="top" bgColor=#F9F9F7>
     			<TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                      <td>&nbsp;</td>
                      <TD align=middle width="50%" >新闻名称</TD>
                      <TD align=middle>发布部门</TD>
                      <TD align=middle>创建用户</TD>
                      <TD align=middle>创建时间</TD>
                      </TR>
                      <s:iterator value="inf" status="status">
                      <tr class=listline>
                        <TD align=middle><img src="../images/arr_2.gif"></TD>
                        <TD><a href="infoView.action?infoid=${infoid}&pagenumber=${pagenumber}">${infoname}</a></TD>
                        <TD>${department.departmentname}</TD>
                        <TD>${createuser.username}</TD>
                        <TD>${createtime}</TD>
                      </tr>
                      </s:iterator>
                   </TBODY>
               </table>
             </TD>
           </TR>
   
    		<TR bgcolor="#FEF7E9" class="pt9-18">
                 <TD colSpan=11 align=middle>
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
              
              </TABLE>
   </div></TD></TR>
   
  </TABLE>
</body>
<s:form name="pageForm" action="informationList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
</HTML>
