<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
/**
 * <p>功能： 查看User列表</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2004-10-10</p>
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
function getAdd(){
     if(noChecked()){
          alert("请选择需要的群组！");
          return false;
     }

      document.form1.submit();
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
              <td width="60"><div align="center">
                <img src="../images/arr.gif" width="13" height="13">              </div></td>
              <td width="97%"><span class="sort-title">群组管理&gt;&gt;用户</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="50%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="businessAddUsers.action"  method="POST">
<s:hidden name="flag" value="save"/>
<s:hidden name="businessid"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR bgcolor="#FFD275" class="listheadline">
                        <TD ><div align="center" class="listheadline">选择</div></TD>
                        <TD ><div align="center" class="listheadline">用户名</div></TD>
                      </TR>
<s:iterator value="userlist" status="status">
                      <TR class="listline" >
                        <TD ><INPUT class="inputwhiteboder" type="checkbox" value='${userid}' name="check">
                        </TD>
                        <TD align="center" >
                           <a href="viewUser.action?userid=${userid}">
                             ${username}
                           </a></TD>
                  </TR>
</s:iterator>
<s:if test="userlist.size!=0">
                    <TR class="listline">
                      <TD colSpan=2 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div></TD>
                    </TR>
</s:if>
<s:if test="userlist.size==0">
                    <TR bgcolor="#ECECFF" class="listline">
                      <TD colSpan=2 >
                        <div align="center" class="style1">以经没有用户供你选择 ！ </div></TD>
                    </TR>
</s:if>
                    <TR class="listline">
                      <TD colSpan=12 align="center">

<s:if test="userlist.size!=0">
<input  class="botton" type=button onclick="return getAdd()" value="保存">
</s:if>
&nbsp;&nbsp;
<input name="button" type=button  class="botton" onclick="history.back(-1)" value="取消">
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
