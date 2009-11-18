<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看node</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2007-10-16</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=30 bgColor=#FFFFFF><div align="left"></div>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">流程管理&gt;&gt;查看节点 </span></td>
            </tr>
          </table>
      </div></TD>
  </TR>
  <TR>
    <TD height="171" valign="top" bgColor=#FFFFFF><div align="center">
        <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#999999 border=0>
          <TR>
            <TD align="center" valign="top" bgColor=#F9F9F7>
                <br>
                <TABLE width="480"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY >
	 			 	<TR>
						<TD width="15%" class="listheadline">流程:</TD>
						<TD width="35%" class="listline">${node.twflProcess.processname}</TD>
                        <TD width="15%" class="listheadline">后续节点:</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点名:</TD>
						<TD width="35%" class="listline">${node.nodename}</TD>
						<TD width="15%" rowspan="7" align="center" valign="top" class="listline">
<s:iterator value="node.toNode" status="status">
<span class="fieldvalue">${toNode.nodename}</span>
 (<a href="nodeDeleteTo.action?directionid=${id}&nodeid=${nodeid}">移去</a>)<br/>
</s:iterator>						</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点类型:</TD>
						<TD width="35%" class="listline">

<s:property value="@com.sxit.workflow.util.CommonDatas@NODETYPES[node.nodetype]"/>

   					</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点处理方式 :</TD>
						<TD width="35%" class="listline">
						<s:property value="@com.sxit.workflow.util.CommonDatas@NODEDOTYPES[node.nodedotype]"/>

                        </TD>
					</TR>

	 			 	<TR>
						<TD width="15%" class="listheadline">处理人职务:</TD>
						<TD width="35%" class="listline">${node.twflPosition.positionname}</TD>
					</TR>
					<%/*
	 			 	<TR>
						<TD width="15%" class="listheadline">正文处理能力:</TD>
						<TD width="35%" class="listline"></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">流程处理能力:</TD>
						<TD width="35%" class="listline"></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">附件处理能力 :</TD>
						<TD width="35%" class="listline"></TD>
					</TR>*/
				%>
	 			 	<TR>
						<TD width="15%" class="listheadline">邮件通知:</TD>
						<TD width="35%" class="listline">${node.sendemail}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">短信通知:</TD>
						<TD width="35%" class="listline">${node.sendsms}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">是否跳过自己:</TD>
						<TD width="35%" class="listline">${node.jumpself}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点图片:</TD>
						<TD width="35%" class="listline">${node.image}</TD>
                        <TD width="15%" align="center" class="listline">
                        <a href="nodeAddList.action?nodeid=${node.nodeid}&processid=${processid}">添加后续节点</a>						</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="13" align="center">
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                      <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">
                      </TD>
                    </TR>
                  </TBODY>
              </TABLE>
                <p>&nbsp;</p>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
   <s:form name="editForm" action="nodeEdit!input.action" method="POST">
   <s:hidden name="nodeid"/>
   <s:hidden name="processid"/>
   </s:form>
   <s:form name="deleteForm" action="nodeDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
    <s:hidden name="nodeid"/>
   <s:hidden name="processid"/>
   </s:form>
   <s:form name="backForm" action="nodeList.action" method="POST">
   <s:hidden name="processid"/>
   </s:form>
</BODY>
</HTML>
