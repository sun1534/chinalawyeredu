<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 编辑rskm</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-10-21</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
<!--
var _opener=window.dialogArguments; 
function test(){
  _opener.userid.value=6;
}

// -->
</script>


</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=15 bgColor=#FFFFFF><div align="left">
      <table width="100%" border="0" cellspacing="0" cellpadding="4">
        <tr>
          <td width="60"><div align="center"> <img src="../images/arr.gif" width="13" height="13"> </div></td>
          <td width="97%"><span class="sort-title">选择用户(单选)</span></td>
        </tr>
      </table>
    </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
      <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
        <TR>
          <TD  valign="top" bgColor=#F9F9F7><br>
                <TABLE width="90%"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
                      <TR>
                        <TD  class="listline">
                        <s:form name="form1" action="userSelect.jsp" validate="true" method="post">
                        过滤:<input name="username" type="text" maxlength="10" id="username" value="${username}" size="10"/>
                        <input type="submit" value="查询" name="submit1" class="botton"/>
                        </s:form>
                        
                        </TD>
                      </TR>
                      <TR>
                        <TD  class="listline">
<s:select 
       name="username"
       list="userlist"
       listKey="userid"
       listValue="username"
       multiple="true"
       size="3"
/>

                        </TD>
                      </TR>
                      <TR bgcolor="#CCCCCC">
                        <TD class="listline" align="center">
                        <INPUT name="insert" type="button" class="botton" test() value="确定">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:window.close()" value="关闭">                        </TD>
                      </TR>
                  </TBODY>
                </TABLE>
                <br>
          </TD>
        </TR>
      </TABLE>
    </div></TD>
  </TR>
</TABLE>





</BODY>
</HTML>