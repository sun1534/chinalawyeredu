<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>


<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_3.css" rel="stylesheet" type="text/css">
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
              <td width="97%"><span class="sort-title">用户管理&gt;&gt;家庭用户详细信息</span></td>
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
               <s:form name="form1" action="usersDetailCreateEdit" validate="true" method="post">
        			 <TR>
					  <TD width="15%" class="listheadline">姓名:</TD>
					  <TD width="35%" class="listline">
					  ${user.userName}
					  <s:hidden name="exist"/>
					  </TD>
					</TR>
        			 	<TR>
					  <TD width="15%" class="listheadline">QQ:</TD>
					  <TD width="35%" class="listline">
					  <s:hidden name="userid"/>
					  <s:textfield size="15" name="userdetail.qq"/></TD>
					</TR>
                 	<TR>
					  <TD width="15%" class="listheadline">MSN:</TD>
					  <TD width="35%" class="listline"><s:textfield size="20" name="userdetail.msn"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">所用邮箱:</TD>
					  <TD width="35%" class="listline"><s:textfield size="20" name="userdetail.email"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">家庭电话:</TD>
					  <TD width="35%" class="listline"><s:textfield size="15" name="userdetail.phone"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">邮编:</TD>
					  <TD width="35%" class="listline"><s:textfield size="10" name="userdetail.postcode"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">家庭地址:</TD>
					  <TD width="35%" class="listline"><s:textfield size="25" name="userdetail.userAddress"/></TD>
					</TR>
				<!-- 		<TR>
					  <TD width="15%" class="listheadline">兴趣爱好:</TD>
					  <TD width="35%" class="listline"><s:textarea rows="2" cols="30" name="userdetail.interest"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">喜欢的书籍:</TD>
					  <TD width="35%" class="listline"><s:textarea rows="2" cols="30" name="userdetail.books"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">喜欢的音乐:</TD>
					  <TD width="35%" class="listline"><s:textarea rows="2" cols="30" name="userdetail.musics"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">喜欢的电影:</TD>
					  <TD width="35%" class="listline"><s:textarea rows="2" cols="30" name="userdetail.movies"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">喜欢的游戏:</TD>
					  <TD width="35%" class="listline"><s:textarea rows="2" cols="30" name="userdetail.games"/></TD>
					</TR>
						<TR>
					  <TD width="15%" class="listheadline">喜欢的运动:</TD>
					  <TD width="35%" class="listline"><s:textarea rows="2" cols="30" name="userdetail.sports"/></TD>
					</TR>
					-->
					
					
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="4" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">
		              </TD>
                    </TR>
                   </s:form>
                  </TBODY>
                </TABLE>
                <br></TD>
          </TR>
        </TABLE>
    </div></TD>
  </TR>
</TABLE>
</BODY>
</HTML>
