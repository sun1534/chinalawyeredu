<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib prefix="ww" uri="webwork" %>

<%
/**
 * <p>功能： 查询Wait列表</p>
 * <p>作者： zrb</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2005-04-29</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<link href="../css/system.css" rel="stylesheet" type="text/css">
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
          document.form1.action="deleteWaits.action";
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
<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 1px;
	margin-right: 2px;
	margin-bottom: 1px;
}
-->
</style></HEAD>
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
              <td width="97%">
		<span class="title-blank-b">待办文件&gt;&gt;查询急件</span>
	      </td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
<s:form name="'form1'" action="'listCorp.action'" enctype="'whith=100%'"  method="'post'">
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR bgcolor="#FFD275" class="pt9-18">
                        <TD align="center"><div align="center" class="pt9-18">选择</div></TD>
                        <TD align="center"><div align="center" class="pt9-18">处理连接</div></TD>
                        <TD align="center"><div align="center" class="pt9-18">标题</div></TD>
                        <TD align="center"><div align="center" class="pt9-18"> </div></TD>
                        <TD align="center"><div align="center" class="pt9-18"> </div></TD>
                        <TD align="center"><div align="center" class="pt9-18">状态 1 已办 0 未办</div></TD>
                        <TD align="center"><div align="center" class="pt9-18">流程环节</div></TD>
                        <TD align="center"><div align="center" class="pt9-18">缓急状态 3 阅件 2 办件 1 急件</div></TD>
                        <TD align="center"><div align="center" class="pt9-18">提交时间</div></TD>
                        <TD align="center" class="pt9-18">查看</TD>
                        <TD align="center" class="pt9-18">编辑</TD>
                        <TD align="center" class="pt9-18">删除</TD>
                      </TR>
<s:iterator value="Waitlist" status="status">
                      <TR bgcolor="#ECECFF" >
                        <TD align="center"><INPUT class="inputwhiteboder" type="checkbox" value='<s:property value="waitid"/>' name="check">
                        </TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="url"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="subject"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="fromto"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="userid"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="status"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="flowstep"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="docstatus"/></TD>
                        <TD align="center" bgcolor="#ECECFF" class="pt9-18"><s:property value="createtime"/></TD>
                        <TD align="center" class="pt9-18">
                            <a href="viewWait.action?waitid=<s:property value="waitid"/>">查看</a>
                        </TD>
                        <TD align="center" class="pt9-18">
                            <a href="editWait.action?waitid=<s:property value="waitid"/>">编辑</a>
                        </TD>
                        <TD align="center" class="pt9-18">
                            <a href="deleteWait.action?waitid=<s:property value="waitid"/>">删除</a>
                        </TD>
                  </TR>
</s:iterator>
<s:if test="waitlist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 align="center"><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan=12 align="center">
<s:if test="waitlist.size>0">
  <div align="right" bgcolor="#FEF7E9">
  <span class="pt9-18">
   共<font color=red><b><s:property value="Waitlist.size"/></b></font>记录</span>
</div>
</s:if>
                      </TD>
                    </TR>
                    <TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=12 align="center">
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
<s:form name="'createForm'" action="'createWait.action'" method="'POST'">
</s:form>
<s:form name="'searchForm'" action="'searchWait.action'" method="'POST'">
<s:hidden name="'flag'" value="'in'"/>
</s:form>

