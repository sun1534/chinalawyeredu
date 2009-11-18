<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>${sysName}</title>
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">

<script language="javascript">
function gotoedit(){
window.location.href="sysParameterFckEdit!input.action?paramname=${paramname}";
}
function preview(theurl){
 document.form1.action="http://211.148.192.252/home/about.action?type="+theurl;
 document.form1.submit();
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

</style>
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=5 >
<!--   <TR>
    <TD height=30 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		 <div align="center">
		  <img src="../images/arr.gif" width="13" height="13">
		 </div>
	      </td>
              <td width="97%"><span class="sort-title">广告管理&gt;&gt;广告新增修改</span></td>
            </tr>
          </table>
      </div></TD>
  </TR>-->
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
              
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1>
                  <TBODY>

                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="left">
                        <INPUT type="button" class="botton" value=" 修改 " onclick="gotoedit()">
                        <s:if test="theurl">
                        <INPUT type="button" class="botton" value=" 预览 " onclick="preview('${paramname }')">
			           </s:if>
		              </TD>
                    </TR>
        		<TR>
					<td  colspan="4" >
					<div>
					  ${value }
				</div>
					</TD>
					</TR>
			
               
               
                 
                  
                  </TBODY>
                </TABLE>
                <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
<form name="form1" method="post" target="_blank"></form>
</BODY>
</HTML>
