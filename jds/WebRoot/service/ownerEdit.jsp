<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%//@ taglib prefix="jscalendar" uri="/jscalendar" %>

<%
/**
 * <p>功能： 编辑owner</p>
 * <p>作者： 刘兴华</p>
 * <p>公司： 长鹏软件</p>
 * <p>日期： 2009-08-28</p>
 * @版本： V1.0
 * @修改：
**/
%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
<!--
<jscalendar:head/>
<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown
":""}${curuser.style==3?"green":""}.css" type="text/css"/>
-->
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
              <td width="97%"><span class="sort-title">物业管理&gt;&gt;编辑物业管理</span></td>
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
               <s:form name="form1" action="ownerEdit" validate="true" method="post">
	 			 	<!--
	 			 	<TR>
						<TD width="15%" class="listheadline">主键:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.ownerid"/></TD>
					</TR>
	 			 	-->
	 			 	<TR>
						<TD width="15%" class="listheadline">房产名称:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.hourse"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">承租方:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.rentuser"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">房产面积:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.area"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">保证金:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.guafee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">租赁起止日:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.renttime"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">交租日/月:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.rentdate"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">月租金:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.rentfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">已经租金/月:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.rentmonth"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">涨租日期:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.addfeedate"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">交税金额:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.taxfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">管理费:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.adminfee"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">纳税情况:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.taccomment"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">土地使用税:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.taxsoil"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系电话:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.mobile"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">座机:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.phone"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">收租账号:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.recuser"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">备注:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.comments"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">入库时间:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.createtime"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">管理费说明:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.admincomment"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">纳税总金额:</TD>
					<TD width="35%" class="listline"><s:textfield name="owner.taxall"/></TD>
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
               <br>
            </TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
