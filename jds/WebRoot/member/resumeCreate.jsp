<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="jscalendar" uri="/jscalendar"%>
<%
/**
 * <p>功能： 创建resume</p>
 * <p>作者： 雷华</p>
 * <p>公司： 深训信科</p>
 * <p>日期： 2008-05-13</p>
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
<jscalendar:head/>	
	<link rel="stylesheet" href="../struts/jscalendar/calendar-${curuser.style==1?"bule":""}${curuser.style==2?"brown":""}${curuser.style==3?"green":""}.css" type="text/css"/>
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
              <td width="97%"><span class="sort-title">简历录入&gt;&gt;新增简历管理</span></td>
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
                <TABLE width="600"  border=0 align=center cellPadding=3 cellSpacing=1 >
                  <TBODY>
               <s:form name="form1" action="resumeCreate" validate="true" method="post">
               <s:fielderror/>
				<s:actionerror/>
				<s:actionmessage/>
               <s:hidden name="pagenumber" value="${pagenumber}"/>	 			 	
	 			 	<TR>
					  <TD width="15%" class="listheadline">姓名:</TD>
					  <TD width="31%" class="listline">
					  <s:hidden name="resume.resumeid" value="${memberid}"/>
					  <s:textfield name="resume.username" value="${member.username}"/>
					  <s:hidden name="resume.tmemMember.memberid" value="${memberid}"/> 
					  <s:hidden name="memberid" value="${memberid}"/>
					  </TD>
					    <TD width="16%" class="listheadline">性别:</TD>
					    <TD width="38%" class="listline">
						<s:select 
						name="resume.sex" 
						list="#{'1':'男','0':'女'}" 
						value="1"
						/></TD>
	 			 	</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">生日:</TD>
	 			 	  <TD class="listline"><jscalendar:jscalendar name="resume.birthday" format="%Y-%m-%d" showstime="false"  value=									"${enddate}"/>					  </TD>
	 			 	  <TD class="listheadline">婚否:</TD>
	 			 	  <TD class="listline"><s:select 
						name="resume.marragestat" 
						list="#{'0':'未婚','1':'已婚'}" 
						/></TD>
 			 	    </TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">手机号码:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.mobile"/></TD>
					    <TD width="16%" class="listheadline">邮件:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.email"/></TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">籍贯:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.birthplace"/></TD>
					    <TD width="16%" class="listheadline">户口所在地:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.registplace"/></TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">民族:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.nation"/></TD>
					    <TD width="16%" class="listheadline">身份证号码:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.idcard"/></TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">毕业学校:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.school"/></TD>
					    <TD width="16%" class="listheadline">毕业时间:</TD>
					    <TD width="38%" class="listline"><jscalendar:jscalendar name="resume.graduateddate" format="%Y-%m-%d" showstime="false"  value=									"${enddate}"/>
						</TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">专业:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.major"/></TD>
					    <TD width="16%" class="listheadline">爱好:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.hobbies"/></TD>
	 			 	</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">学位:</TD>
	 			 	  <TD class="listline"><s:textfield name="resume.degree"/></TD>
	 			 	  <TD class="listheadline">学历:</TD>
	 			 	  <TD class="listline"><s:textfield name="resume.educationallevel"/></TD>
 			 	    </TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">联系地址:</TD>
						<TD colspan="3" class="listline"><s:textfield name="resume.linkaddr" size='55'/></TD>
				    </TR>
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">英文名:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.enname"/></TD>
					    <TD width="16%" class="listheadline">英语水平:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.englishlevel"/></TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">工作年限:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.workyears"/></TD>
					    <TD width="16%" class="listheadline">薪水:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.salary"/></TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="15%" class="listheadline">技能水平:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.skilldesc"/></TD>
					    <TD width="16%" class="listheadline">健康状况:</TD>
					    <TD width="38%" class="listline"><s:textfield name="resume.health"/></TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
	 			 	  <TD class="listheadline">入职时间:</TD>
	 			 	  <TD class="listline"><jscalendar:jscalendar name="resume.rzdate"  format="%Y-%m-%d" showstime="false"/></TD>
	 			 	  <TD class="listheadline">转正时间:</TD>
	 			 	  <TD class="listline"><jscalendar:jscalendar name="resume.zzdate"  format="%Y-%m-%d" showstime="false"/></TD>
 			 	    </TR>
 			 	    <TR>
	 			 	  <TD class="listheadline">离职时间:</TD>
	 			 	  <TD class="listline"><jscalendar:jscalendar name="resume.lzdate"  format="%Y-%m-%d" showstime="false"/></TD>
	 			 	  <TD class="listheadline">交纳社保时间:</TD>
	 			 	  <TD class="listline"><jscalendar:jscalendar name="resume.sbdate"  format="%Y-%m-%d" showstime="false"/></TD>
 			 	    </TR>
 			 	    
	 			 	<TR>
						<TD width="15%" class="listheadline">电话:</TD>
						<TD width="31%" class="listline"><s:textfield name="resume.phone"/></TD>
					    <TD width="16%" class="listheadline">&nbsp;</TD>
					    <TD width="38%" class="listline">&nbsp;</TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="15%" class="listheadline">自我评价: </TD>
						<TD colspan="3" class="listline"><s:textarea  name="resume.selfeval" cols="55" rows="5"/></TD>
				    </TR>
                    
                    <TR>
                      <TD width="15%" class="listheadline">备注:</TD>
                      <TD colspan="3" class="listline"><s:textarea  name="resume.remark" cols="55" rows="5"/></TD>
                    </TR>
                    <TR bgcolor="#CCCCCC">
                      <TD colspan="6" align="center">
                        <INPUT name="insert" type="submit" class="botton" value="保存">&nbsp;
			            <INPUT name="back" type=button class="botton" onClick="javascript:history.back(-1)" value="返回">		              </TD>
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
