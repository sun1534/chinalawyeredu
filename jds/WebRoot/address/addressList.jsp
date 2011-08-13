<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.2.6.pack.js"></script> 

<script language="javascript">
function _close(){

	//触发窗口关闭事件
	var comment = parent.document.getElementById("TB_closeWindowButton");
	

	if (document.all) {
		 // For IE 	 
		comment.click();
	} else if (document.createEvent) {
	   //FOR DOM2
		 var ev = document.createEvent('HTMLEvents');
		 ev.initEvent('click', false, true);
		 comment.dispatchEvent(ev);
	}	
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
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
     <br>
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="98%"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="addressList" validate="true" method="post">
       				<TR bgcolor="#CCCCCC">
                      <TD colspan="5" align="left" class=listheadline>
                      	电话号码：<s:textfield name="phone" size="12"/>
			            <INPUT type="submit" value="查询"/>
		              </TD>
                    </TR>
       				<TR class="listheadline">
                      <TD >姓名</TD>
                      <TD >住址</TD>
                      <TD >公司名称</TD>
                      <TD >备注</TD>
                       <TD>承办人</TD>
                      </TR>
                      <s:iterator value="addresslist" status="status">
                      <TR class=listline>
                      <TD>${username}</TD>
                      <TD>${homeaddr}</TD>
                      <TD>${company}</TD>
                      <TD>${comments}</TD>
                      <TD>${tsysUser.username} </TD>
                       </TR>
                      </s:iterator>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="5" align="center">
			            <INPUT  type=button class="botton" onclick="_close()" value="返回">
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
