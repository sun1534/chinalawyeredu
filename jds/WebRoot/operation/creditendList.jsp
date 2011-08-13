<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 信用卡业务全清记录</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-31</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../js/thickbox.css" type="text/css" media="screen" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script> 
<script type="text/javascript" src="../js/thickbox-compressed.js"></script> 
<script type="text/javascript" src="../js/common.js"></script> 
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
          document.form1.action="creditcardDeletes.action";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function transfer(){
	if(noChecked()){
          alert("请选择需要转诉讼的记录！");
          return false;
     }
     if (confirm("您确定要转诉讼吗?")) {
          document.form1.action="creditcardUpdate.action?cflag=1";
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
     if (confirm("您确定要变更还款状态吗?")) {
          document.form1.action="creditcardUpdate.action?cflag=2";
          document.form1.submit();
          return true;
     }
     else {
          return false;
     }
}
function page(str){
  //document.pageForm.pagenumber.value=str;
  //document.pageForm.submit();
  document.form1.pagenumber.value=str;
  document.form1.action="creditendList.action";
 document.form1.submit();
  return true;
}
function query(){
 document.form1.action="creditendList.action";
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
              <td width="60">
		<div align="center"><img src="../images/arr.gif" width="13" height="13"></div>
	      </td>
              <td width="97%"><span class="sort-title">信用卡业务&gt;&gt;催收记录结案列表</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
         
         
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="creditcardDeletes.action" method="POST">
<s:hidden name="pagenumber" value="${pagenumber}"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                  <TR class=listline >
			           <TD colSpan=13 >
			           委托银行：<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" headerKey="0" headerValue="全部" listKey="bankid" listValue="bankname"/>
					  委托类型：<s:select name="consigntype" list="@com.changpeng.operation.util.OperationUtil@consigntypeMap" headerKey="" headerValue="全部"/>
					  委托类别：<s:select name="consignflag" list="@com.changpeng.operation.util.OperationUtil@consignflagMap" headerKey="" headerValue="全部"/>
					
					   <br>
					   客户姓名：<s:textfield name="username" size="10"/>
					  客户账号：<s:textfield name="creditcard" size="10"/>	
					  <input type="button" value="查询" class="botton" onclick="query()"/>		
					  
					  </TD>
			          </TR>
                      <TR class="listheadline">
                        <TD>选择</TD>
                        <TD >委托银行</TD>
                        <TD >客户姓名</TD>
                        <TD >委托日期</TD>
                        <TD >透支人民币</TD>
						<TD >透支美元</TD>
						<TD >透支港元</TD>
						<TD >透支欧元</TD>
                      	<TD >还款状态</TD>
              			<TD >承办人</TD>
                     
                     	 <TD >还款记录</TD>
                    	 <TD >详细信息</TD>
                    	 <TD >催收日志</TD>
                      </TR>
<s:iterator value="creditcardlist" status="status">
                      <TR class=listline>
                        <TD >
                        <INPUT type="checkbox" value='${creditcardid}' name="check">
                        </TD>
                        <TD ><s:property value="@com.changpeng.operation.util.OperationUtil@bankMap()[bankid+\"\"]"/></TD>
                      <TD >${username}</TD>                      
                      <TD >${consigndate}</TD>
                                 
                  	 	<TD ><s:if test="curcnfee==null||curcnfee==\"\"">0</s:if>${curcnfee}<s:else></s:else></TD>
                     	<TD ><s:if test="curhkfee==null||curhkfee==\"\"">0</s:if>${curhkfee}<s:else></s:else></TD>
                     	<TD ><s:if test="curusafee==null||curusafee==\"\"">0</s:if>${curusafee}<s:else></s:else></TD>
                     	<TD ><s:if test="cureurfee==null||cureurfee==\"\"">0</s:if>${cureurfee}<s:else></s:else></TD>
                    
                      <TD >
                      <s:if test="repaystatus==1">部分</s:if>
                       <s:elseif test="repaystatus==2">全清</s:elseif>
                       <s:else>备注清零</s:else>
                       
                      </TD>
                 	<td>
                 		<a href="reassignTask!input.action?creditcardid=${creditcardid}&keepThis=true&TB_iframe=true&height=250&width=400" title="任务重新分配" class="thickbox" >			
                       		<font color="red"><!-- 已分配 --><s:property value="@com.changpeng.operation.util.OperationUtil@taskuser(creditcardid)"/></font>
                       	</a>
                      </td>
                  
                      <TD><a href="repaylogList.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
					    <TD><a href="creditcardView.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                  	 <TD><a href="creditlogList.action?creditcardid=${creditcardid}&pagenumber=${pagenumber}">查看</a></TD>
                  	
                  </TR>
</s:iterator>
<s:if test="creditcardlist!=null">                    
<TR bgcolor="#ECECFF" class="pt9-18">
                      <TD colSpan=13 align="left">
                         <input type="checkbox" name="selectAll" onClick="getCheckAll()" value="checkbox">全选
                      	 
                      </TD>
                      
                    </TR>
</s:if>                    <TR bgcolor="#FEF7E9" class="pt9-18">
                      <TD colSpan="13">
						${pagestring }
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
<s:form name="pageForm" action="creditcardList.action" method="POST">
	<s:hidden name="pagenumber"/>
</s:form>
<s:form name="createForm" action="creditcardCreate!input.action" method="POST">
	<s:hidden name="pagenumber" value="${pagenumber}"/>
</s:form>
<s:form name="searchForm" action="creditcardSearch!input.action" method="POST">
<s:hidden name="flag" value="in"/>
</s:form>
