<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
/**
 * <p>功能： 查看owner</p>
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
              <td width="97%"><span class="sort-title">物业管理&gt;&gt;查看物业管理</span></td>
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
                  <TBODY >
	 			 	<TR>
						<TD width="15%" class="listheadline">主键:</TD>
						<TD width="35%" class="listline">${owner.ownerid}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">房产名称:</TD>
						<TD width="35%" class="listline">${owner.hourse}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">承租方:</TD>
						<TD width="35%" class="listline">${owner.rentuser}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">房产面积:</TD>
						<TD width="35%" class="listline">${owner.area}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">保证金:</TD>
						<TD width="35%" class="listline">${owner.guafee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">租赁起止日:</TD>
						<TD width="35%" class="listline">${owner.renttime}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">交租日/月:</TD>
						<TD width="35%" class="listline">${owner.rentdate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">月租金:</TD>
						<TD width="35%" class="listline">${owner.rentfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">已经租金/月:</TD>
						<TD width="35%" class="listline">${owner.rentmonth}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">涨租日期:</TD>
						<TD width="35%" class="listline">${owner.addfeedate}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">交税金额:</TD>
						<TD width="35%" class="listline">${owner.taxfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">管理费:</TD>
						<TD width="35%" class="listline">${owner.adminfee}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">纳税情况:</TD>
						<TD width="35%" class="listline">${owner.taccomment}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">土地使用税:</TD>
						<TD width="35%" class="listline">${owner.taxsoil}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系电话:</TD>
						<TD width="35%" class="listline">${owner.mobile}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">座机:</TD>
						<TD width="35%" class="listline">${owner.phone}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">收租账号:</TD>
						<TD width="35%" class="listline">${owner.recuser}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">备注:</TD>
						<TD width="35%" class="listline">${owner.comments}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">入库时间:</TD>
						<TD width="35%" class="listline"><s:date name="owner.createtime" format="yyyy-MM-dd HH:mm:ss"/></TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">管理费说明:</TD>
						<TD width="35%" class="listline">${owner.admincomment}</TD>
					</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">纳税总金额:</TD>
						<TD width="35%" class="listline">${owner.taxall}</TD>
					</TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
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
   <s:form name="editForm" action="ownerEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="ownerDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="ownerList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
