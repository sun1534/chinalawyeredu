<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看info列表</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
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
<script language=javascript>
<!--
function getSearch(){
     document.form1.action = "searchUser.action";
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
function getDelete(){
     if(noChecked()){
          alert("请选择记录，删除需要选择记录！");
          return false;
     }
     if (confirm("您确定要进行删除?")) {
          document.form1.action="infoAdminDeletes.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
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
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;管理列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
    <tr>
  	<td>

  	</td>
  </tr>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align=center  valign="top" bgColor=#F9F9F7>
              	<DIV class="tabs">
  	<s:iterator value="typeList" status="status">
     <A class=
     <s:if test="type==typeid ||type==null||type.equals(\"\")">"selected"</s:if>
     <s:else>"plain"</s:else>
      href="infoList.action?type=${typeid }">${name }</A>
	</s:iterator>
    </DIV>
    <DIV class="personalBar"></DIV>
<s:form name="form1" action="infoDeletes.action" method="POST">
	<s:hidden name="type" />
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD>选择</TD>
                      <TD >公告名称</TD>
                      <TD >文件编号</TD>
                      <TD >状态</TD>
                      <TD >发布部门</TD>
                      <TD >时间</TD>
                      <TD >详细信息</TD>
                      </TR>
<s:iterator value="infolist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${infoid}' name="check">
                        </TD>
                        <TD><a href="infoAdminView.action?infoid=${infoid}&pagenumber=${pagenumber}">${infoname}</a></TD>
                        <TD>${filenumber}</TD>
                        <TD><s:property escape="false" value="@com.sxit.info.util.CommonDatas@StatusWeb[${statusid}]"/></TD>
                        <TD>${department.departmentname}</TD>
                        <TD> <s:date name="createtime" format="yyyy-MM-dd"/> </TD>
                        <TD><a href="infoAdminView.action?infoid=${infoid}&type=${tinfType.typeid }&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="infolist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan=12 >
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
                      <TD colSpan=12 >
<div align="center">
<input  class="botton" type=button onclick="document.searchForm.submit()" value="查询">&nbsp;
<input  class="botton" type=button onclick="return getDelete()" value="删除">
</div>
                       </TD>
                    </TR>
                  </TBODY>
              </TABLE>
</s:form>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
<s:form name="pageForm" action="infoList.action" method="POST">
	<s:hidden name="type" value="${type}"/>
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="infoCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
<s:form name="searchForm" action="infoManageSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
