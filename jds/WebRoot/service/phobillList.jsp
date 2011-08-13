<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar" %>
<%
/**
 * <p>功能： 查看phobill列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-07</p>
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
          document.form1.action="phobillDeletes.action";
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
function query(){
	document.form1.action="phobillList.action";
    document.form1.submit();
}
-->
</script>
<jscalendar:head/>
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
              <td width="97%"><span class="sort-title">电话费管理&gt;&gt;电话费管理列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="phobillDeletes.action" method="POST">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                    <TR class="listheadline">
                        <TD colspan="7">
                        用户：<s:textfield name="username" size="10"/>
                      号码:<s:textfield name="phone" size="10"/>
                       费用月份:<jscalendar:jscalendar name="feetime" format="%Y-%m"/>
                       <input type="button" value="查询" onclick="query()"/>
                        	</TD>
                        </TR>
                      <TR class="listheadline">
                        <TD>选择</TD>
                      <TD >电话用户</TD>
                      <TD >电话号码</TD>
                      <TD >电话费用</TD>
                      <TD >费用时间</TD>
                     
                      <TD >记录时间</TD>
                        <TD >详细信息</TD>
                      </TR>
<s:iterator value="phobilllist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${phobillid}' name="check">
                        </TD>
                      <TD >${username}</TD>
                      <TD >${phone}</TD>
                      <TD >${fee}</TD>
                      <TD >${feetime}</TD>
                    
                      <TD ><s:date name="createtime" format="yyyy-MM-dd"/></TD>
                      <TD><a href="phobillView.action?phobillid=${phobillid}&pagenumber=${pagenumber}">查看</a></TD>
                  </TR>
</s:iterator>
<s:if test="phobilllist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>
${pagestring}
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 >
<div align="center">
<input class="botton" type=button onclick="document.createForm.submit()" value="新增">&nbsp;
<input class="botton" type=button onclick="location.href='phobillCreateBatch!input.action'" value="批量导入">&nbsp;
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
<s:form name="pageForm" action="phobillList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="phobillCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
