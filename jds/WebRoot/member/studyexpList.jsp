<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看studyexp列表</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-05-16</p>
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
          document.form1.action="studyexpDeletes.action";
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
              <td width="97%"><span class="sort-title">简历录入&gt;&gt;简历管理列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="studyexpDeletes.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD>选择</TD>
                      <TD >学历</TD>
                      <TD >教育机构</TD>
                      <TD >开始时间</TD>
                      <TD >结束时间</TD>
                      <TD >专业</TD>
                      <TD >资历证书</TD>
                      <TD >技能专长</TD>
                      <TD >id</TD>
                        <TD >详细信息</TD>
                      </TR>
<s:iterator value="studyexplist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${}' name="check">
                        </TD>
                        <TD>${education}</TD>
                        <TD>${school}</TD>
                        <TD>${begindate}</TD>
                        <TD>${enddate}</TD>
                        <TD>${major}</TD>
                        <TD>${certification}</TD>
                        <TD>${skills}</TD>
                        <TD>${resumeid}</TD>
                        <TD><a href="studyexpView.action?=${}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="studyexplist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
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
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;
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
<s:form name="pageForm" action="studyexpList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="studyexpCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
<s:form name="searchForm" action="studyexpSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
