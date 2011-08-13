<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 创建creditcard</p>
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

<jscalendar:head/>
<script type="text/javascript">
     var errorMessages = "";
     function addError(field,error){
         if(errorMessages)
         errorMessages+="\n"+error;
         else
         errorMessages+=error;
         }
    function validateForm_creditcardCreateBatch() {
        form = document.getElementById("creditcardCreateBatch");

        errorsMessage="";

        var errors = false;
        // field name: bankid
        // validator name: required
        if (form.elements['bankid']) {
            field = form.elements['bankid'];
            var error = "请选择委托银行!";
            if (field.value == "") {
                addError(field, error);
                errors = true;
            }
        if(errors){
         alert(errorMessages);
         field.focus();
         errorMessages="";
         return false;
         }
        }
        // field name: consigndate
        // validator name: required
        if (form.elements['consigndate']) {
            field = form.elements['consigndate'];
            var error = "请选择委托日期!";
            if (field.value == "") {
                addError(field, error);
                errors = true;
            }
        if(errors){
         alert(errorMessages);
         field.focus();
         errorMessages="";
         return false;
         }
        }
        // field name: file
        // validator name: required
        if (form.elements['file']) {
            field = form.elements['file'];
            var error = "请选择委托文件!";
            if (field.value == "") {
                addError(field, error);
                errors = true;
            }
        if(errors){
         alert(errorMessages);
         field.focus();
         errorMessages="";
         return false;
         }
        }
return !errors;
    }
</script>

<style type="text/css">
<!--
body {
	margin-left: 2px;
	margin-top: 0px;
	margin-right: 2px;
	margin-bottom: 2px;
}
-->
</style></HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
  <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">业务管理&gt;&gt;新增催收记录</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="creditcardCreateBatch" onsubmit="return validateForm_creditcardCreateBatch();" method="post" enctype="multipart/form-data">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托银行</TD>
						<TD width="35%" class="listline">
							<s:select name="bankid" list="@com.changpeng.operation.util.OperationUtil@listBank()" listKey="bankid" listValue="bankname" headerKey="" headerValue=""/>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托日期:</TD>
						<TD width="35%" class="listline"><jscalendar:jscalendar	name="consigndate" format="%Y-%m-%d"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">委托文件:</TD>
						<TD width="35%" class="listline"><s:file name="file"/></TD>
					</TR>
					<TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="left" style="font-size:9pt;color:red">
                       说明:导入文件中委托类型分别由1，2，3代替 其中1:3000-5000 2：10000以上 3：5000-10000 4：3000以下<br>
                     
		              </TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
                   </s:form>
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
