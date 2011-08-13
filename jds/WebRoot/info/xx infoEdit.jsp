<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 编辑info</p>
 * <p>作者： 张如兵</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-08-27</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
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
              <td width="97%"><span class="sort-title">信息管理&gt;&gt;编辑信息</span></td>
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
               <s:form name="form1" action="infoEdit" validate="true" method="post">
	 			 	<TR>
						<TD width="15%" class="listheadline">公告id:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.infoid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公告类别:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.typeid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">公告名称:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.infoname"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">文件编号:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.filenumber"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">详细内容:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.content"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">状态 001:新增 010:审批 100:发布:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.statusid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">发布部门:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.departmentid"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">创建用户:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.createuser"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">创建时间:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.createtime"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">修改用户:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.modifyuser"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">修改时间:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.modifytime"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">代办id:</TD>
						<TD width="35%" class="listline"><s:textfield name="info.waitid"/></TD>
					</TR>
                    <TR>
                      <TD width="15%" class="listheadline">状态:</TD>
                      <TD width="35%" class="listline">
                      <SELECT name="info.statusid" class="input1">
<s:if test="info.statusid==1">
                         <OPTION VALUE="1" selected>启用</OPTION>
                         <OPTION VALUE="0">冻结</OPTION>
</s:if>
<s:if test="info.statusid==0">
                         <OPTION VALUE="1">启用</OPTION>
                         <OPTION VALUE="0" selected>冻结</OPTION>
</s:if>
                      </SELECT>
                      </TD>
                    </TR>
                    <TR>
                      <TD width="15%" class="listheadline">描 述:</TD>
                      <TD width="35%" class="listline">
                        <s:textarea  name="info.description" cols="30" rows="5"/>
                      </TD>
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
