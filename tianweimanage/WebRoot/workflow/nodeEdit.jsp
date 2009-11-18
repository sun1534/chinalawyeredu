<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>



<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY >
<TABLE width="100%" border=0  align=center cellPadding=0 cellSpacing=4 >
  <TR>
    <TD height=15 bgColor=#FFFFFF>
        <div align="left">
          <table width="80%" border="0" cellspacing="0" cellpadding="4">
            <tr>
              <td width="60">
		<div align="center">
		<img src="../images/arr.gif" width="13" height="13">
		</div>
	      </td>
              <td width="97%"><span class="sort-title">流程管理&gt;&gt;编辑节点</span></td>
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
                <TABLE width="460"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#7F9DB9">
                  <TBODY>
               <s:form name="form1" action="nodeEdit" validate="true" method="post">
               <s:hidden name="processid"/>
	 			 	<TR>
						<TD width="15%" class="listheadline">流程:</TD>
						<TD width="35%" class="listline">${process.processname}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点名:</TD>
						<TD width="35%" class="listline"><s:textfield name="node.nodename"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点类型:</TD>
						<TD width="35%" class="listline">
<s:select 
	name="node.nodetype" 
	list="@com.sxit.workflow.util.CommonDatas@NODETYPES"
/>
                     </TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点处理方式 :</TD>
						<TD width="35%" class="listline">
<s:select 
	name="node.nodedotype" 
	list="@com.sxit.workflow.util.CommonDatas@NODEDOTYPES"
/>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">处理人职务:</TD>
						<TD width="35%" class="listline">

<s:select 
	name="node.twflPosition.positionid" 
	listKey="positionid" 
	listValue="positionname" 
	list="positionlist" 
/>
<span class="errorMessage">当职务无人担任时，则跳过此节点</span>						</TD>
					</TR>
	 			 
	 			 	<TR>
						<TD width="15%" class="listheadline">邮件通知:</TD>
						<TD width="35%" class="listline"><s:checkbox name="node.sendemail"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">短信通知:</TD>
						<TD width="35%" class="listline"><s:checkbox name="node.sendsms"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">是否跳过自己:</TD>
						<TD width="35%" class="listline"><s:checkbox name="node.jumpself"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">节点图片:</TD>
						<TD width="35%" class="listline"><s:textfield name="node.image"/></TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
                        <INPUT name="back2"  type=button   class="botton" onClick="javascript:history.back(-1)" value="返回">
		      </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
