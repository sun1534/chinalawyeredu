<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看creditcard列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-06-09</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="../ext2.0/resources/css/xtheme-gray.css" />
<!-- GC -->
<!-- LIBS -->
<script type="text/javascript" src="../ext2.0/adapter/ext/ext-base.js"></script>
<!-- ENDLIBS -->
<script type="text/javascript" src="../ext2.0/ext-all.js"></script>
<script type="text/javascript" src="userselect.js"></script>
<script language="javascript">
<!--
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
              <td width="97%"><span class="sort-title">信用卡业务&gt;&gt;催收任务分配</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
         
         
          <TR>
            <TD  valign="top" bgColor="#F9F9F7">
<s:form name="form1" action="assignBatch.action" method="POST">

                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
			          <TR class=listline >
			           <TD align="center">
			           <br>
			           <div align="left" style="font:bold;color:blue">选定以下催收员:<input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
			           <br>
			           <div align="left">
			           <s:iterator value="roleusers" status="stat">
			           			<input type="checkbox" name="check" value="${comp_id.tsysUser.userid}"/>${comp_id.tsysUser.username}&nbsp;&nbsp;		           
			           			<s:if test="(#stat.index+1)%12==0"><br></s:if>
			           </s:iterator>
			           </div>
			             <br>
			           <div align="left" style="font:bold;color:blue">对查询条件如下的任务进行批量分配:</div>
			           <br>
			           <div align="left">
			           委托银行：<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					  委托类型：<s:select name="consigntype" list="@com.changpeng.operation.util.OperationUtil@consigntypeMap" headerKey="" headerValue="全部"/>
					  委托类别：<s:select name="consignflag" list="@com.changpeng.operation.util.OperationUtil@consignflagMap" headerKey="" headerValue="全部"/>
					
			           </div>
			             <br>
			           <div align="center">
			           	<input type="submit" value="确认" class="botton" />
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
<s:form name="pageForm" action="credittaskList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
