<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
/**
 * <p>功能： 催收任务重新分配</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-11-07</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">


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
              <td width="97%"><span class="sort-title">非诉业务&gt;&gt;催收任务分配</span></td>
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
               <s:form name="form1" action="nonlawReassignBatch" method="post" enctype="multipart/form-data">
               <s:hidden name="pagenumber" value="${pagenumber}"/>
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">任务分配文件:</TD>
						<TD width="35%" class="listline"><s:file name="file"/></TD>
					</TR>
					<TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="left" style="font-size:9pt;color:red">
                       说明:任务重新分配时文件中，必须符合第一列为原始导出文件中的任务ID，第二列为催收员姓名。
                        <br>催收任务导出文件类型，为html格式的excel体现，需要将其文件另存为excel格式。
                        <br>即在保存类型中，选择"Excel 97-2003 工作薄(*.xls)"。
                        <br>任务已分配，且无需重新分配的记录，切忌需要从导入文件中剔除。
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
