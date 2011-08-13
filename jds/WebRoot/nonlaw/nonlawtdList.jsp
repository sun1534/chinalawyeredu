<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 退单记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-11-08</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script language="javascript">
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
          document.form1.action="nonlawDeletes.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  //document.pageForm.pagenumber.value=str;
  //document.pageForm.submit()
    document.form1.pagenumber.value=str;
  document.form1.action="nonlawtdList.action";
 document.form1.submit();
  return true;
}
function query(){
 document.form1.action="nonlawtdList.action";
 document.form1.submit();
 return true;
}
function transfer(){
	if(noChecked()){
          alert("请选择需要转诉讼的记录！");
          return false;
     }
     if (confirm("您确定要转诉讼吗?")) {
          document.form1.action="nonlawUpdate.action?cflag=1";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function updateStat(){
	if(noChecked()){
          alert("请选择需要变更状态的记录！");
          return false;
     }
     if (confirm("您确定要变更还款状态为全清吗?")) {
          document.form1.action="nonlawUpdate.action?cflag=2";
          document.form1.submit();
          return true;
     }
     else {
          return false;
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
              <td width="97%"><span class="sort-title">非诉催收业务&gt;&gt;催收记录退单列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="nonlawDeletes.action" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=12 >
			           委托银行：<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					
					   客户姓名：<s:textfield name="username" size="10"/>
					  证件号码：<s:textfield name="idcard" size="10"/>	
					  <input type="button" value="查询" class="botton" onclick="query()"/>					
			           </TD>
			          </TR>
                      <TR class="listheadline">
                        <TD>选择</TD>
                         <td>借据号</td>
                         <TD >委托银行</TD>
                         <TD >支行名称</td>
                      <TD>客户姓名</TD>                                      
                      <TD>贷款金额</TD>
    				  <TD>还款状态</TD>
              
                      <TD>委托时间</TD>
                      <TD>还款记录</TD>
                     
                      </TR>
<s:iterator value="nonlawlist" status="status">
                      <TR class=listline>
                        <TD>
                        <INPUT type="checkbox" value='${nonlawid}' name="check">
                        </TD>
                            <TD>${duebill}</TD>  
                        <TD><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[bankid+\"\"]"/></TD>
                     <TD>${bankname}</TD>  
                      <TD>${username}</TD>      
                      <TD>${lendfee}</TD>
                      <TD>
                      <s:if test="repaystatus==1">部分</s:if>
                       <s:elseif test="repaystatus==2">全清</s:elseif>
                       <s:else>备注清零</s:else>

                      </TD>
                     
                      <TD >${consigndate}</TD>
                      <TD><a href="repaylogList.action?nonlawid=${nonlawid}&pagenumber=${pagenumber}">查看</a></TD>
                    
                  </TR>
</s:iterator>
<s:if test="nonlawlist!=null">                    <TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=12 ><div align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选</div>
                      </TD>
                    </TR>
</s:if>
		${pagestring}
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
<s:form name="pageForm" action="nonlawList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="nonlawCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
                     