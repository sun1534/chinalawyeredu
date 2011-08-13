<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看repaylog列表</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-07-10</p>
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
</HEAD>
<BODY >
<TABLE width="100%" border=0 align=center cellPadding=0 cellSpacing=4 >

  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD  valign="top" bgColor=#F9F9F7>
<s:form name="form1" action="repaylogDeletes.action" method="POST">
<s:hidden name="nonlawid"/>
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                         <TD>人民币</TD>
	                       <TD>美元</TD>
	                       <TD>港元</TD>
	                       <TD>欧元</TD>
	                      <TD >还款日期</TD>
	                       <td>还款状态</td>
	                      <TD >备注</TD>
	                      <TD >记录时间</TD>
                      </TR>
<s:iterator value="repayloglist" status="status">
                      <TR class=listline>

                      <TD >${fee}</TD>
                       <TD >${usafee}</TD>
                        <TD >${hkfee}</TD>
                         <TD >${eurfee}</TD>
                      <TD >${repaytime}</TD>
                      <TD > <s:if test="repaystatus==2">全清</s:if><s:else>部分</s:else></TD>
                      
                      <TD >${comments}</TD>
                      <TD ><s:date name="createtime" format="yyyy-MM-dd"/></TD>
                     </TR>
</s:iterator>

${pagestring}
					<TR bgcolor="#F9F9F7" class="pt9-18">
                      <TD colSpan=7 align="center">
						<input class="botton" type=button onclick="_close()" value="返回">
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
