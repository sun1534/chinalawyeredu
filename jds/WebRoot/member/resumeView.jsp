<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<%
/**
 * <p>功能： 查看resume</p>
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
              <td width="97%"><span class="sort-title">简历录入&gt;&gt;查看简历管理</span></td>
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
                  <TBODY >
	 			 	
	 			 	<TR bgcolor="#CCCCCC">
	 			 	  <TD colspan="4"><span class="sort-title">简历详细信息：</span>
                      <input name="edit" type=submit class="mask" onClick="document.editForm.submit()" value="编辑">
                      <input name="delete" type=button class="mask"
                      onClick="if(confirm('您确定要删除?')) document.deleteForm.submit()" value="删除">
                      <input name="back" type=button class="mask" onClick="document.backForm.submit()" value="返回">												  					  </TD>
 			 	    </TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">姓名:</TD>
						<TD width="35%" class="listline">${resume.username}</TD>
					    <TD width="17%" class="listheadline">性别:</TD>
					    <TD width="31%" class="listline">${resume.sex==0?"女":"男"}</TD>
	 			 	</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">生日:</TD>
	 			 	  <TD class="listline">${resume.birthday}</TD>
	 			 	  <TD class="listheadline">婚否:</TD>
	 			 	  <TD class="listline">${resume.marragestat==0?"未婚":"已婚"}</TD>
 			 	    </TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">手机号码:</TD>
						<TD width="35%" class="listline">${resume.mobile}</TD>
					    <TD width="17%" class="listheadline">邮件:</TD>
					    <TD width="31%" class="listline">${resume.email}</TD>
	 			 	</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">籍贯:</TD>
	 			 	  <TD class="listline">${resume.birthplace}</TD>
	 			 	  <TD class="listheadline">户口所在地:</TD>
	 			 	  <TD class="listline">${resume.registplace}</TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">民族:</TD>
	 			 	  <TD class="listline">${resume.nation}</TD>
	 			 	  <TD class="listheadline">身份证号码:</TD>
	 			 	  <TD class="listline">${resume.idcard}</TD>
 			 	    </TR>
	 			 	
	 			 	<TR>
						<TD width="17%" class="listheadline">年龄:</TD>
						<TD width="35%" class="listline">${resume.age}</TD>
					    <TD width="17%" class="listheadline">健康状况:</TD>
					    <TD width="31%" class="listline">${resume.health}</TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="17%" class="listheadline">毕业学校:</TD>
						<TD width="35%" class="listline">${resume.school}</TD>
					    <TD width="17%" class="listheadline">毕业时间:</TD>
					    <TD width="31%" class="listline">${resume.graduateddate}</TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">专业:</TD>
						<TD width="35%" class="listline">${resume.major}</TD>
					    <TD width="17%" class="listheadline">爱好:</TD>
					    <TD width="31%" class="listline">${resume.hobbies}</TD>
	 			 	</TR>
	 			 	<TR>
	 			 	  <TD class="listheadline">学位:</TD>
	 			 	  <TD class="listline">${resume.degree}</TD>
	 			 	  <TD class="listheadline">学历:</TD>
	 			 	  <TD class="listline">${resume.educationallevel}</TD>
 			 	    </TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">联系地址:</TD>
						<TD width="35%" class="listline">${resume.linkaddr}</TD>
					    <TD width="17%" class="listheadline">电话:</TD>
					    <TD width="31%" class="listline">${resume.phone}</TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="17%" class="listheadline">英文名:</TD>
						<TD width="35%" class="listline">${resume.enname}</TD>
					    <TD width="17%" class="listheadline">英语水平:</TD>
					    <TD width="31%" class="listline">${resume.englishlevel}</TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="17%" class="listheadline">填写日期:</TD>
						<TD width="35%" class="listline">${resume.createdate}</TD>
					    <TD width="17%" class="listheadline">状态:</TD>
					    <TD width="31%" class="listline">${resume.statusid}</TD>
	 			 	</TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">简历水平:</TD>
						<TD width="35%" class="listline">${resume.resumelevel}</TD>
					    <TD width="17%" class="listheadline">技能水平:</TD>
					    <TD width="31%" class="listline">${resume.skilldesc}</TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="17%" class="listheadline">工作年限:</TD>
						<TD width="35%" class="listline">${resume.workyears}</TD>
					    <TD width="17%" class="listheadline">薪水:</TD>
					    <TD width="31%" class="listline">${resume.salary}</TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
						<TD width="17%" class="listheadline">入职时间:</TD>
						<TD width="35%" class="listline">${resume.rzdate}</TD>
					    <TD width="17%" class="listheadline">转正时间:</TD>
					    <TD width="31%" class="listline">${resume.zzdate}</TD>
	 			 	</TR>
	 			 		<TR>
						<TD width="17%" class="listheadline">离职时间:</TD>
						<TD width="35%" class="listline">${resume.lzdate}</TD>
					    <TD width="17%" class="listheadline">交纳社保时间:</TD>
					    <TD width="31%" class="listline">${resume.sbdate}</TD>
	 			 	</TR>
	 			 	
	 			 	<TR>
	 			 	  <TD class="listheadline">状态:</TD>
	 			 	  <TD class="listline">${resume.statusid==1?"启用":"冻结"}</TD>
	 			 	  <TD class="listheadline">&nbsp;</TD>
	 			 	  <TD class="listline">&nbsp;</TD>
 			 	    </TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">自我评价:</TD>
						<TD colspan="3" class="listline">${resume.selfeval}</TD>
				    </TR>
	 			 	<TR>
						<TD width="17%" class="listheadline">备注:</TD>
						<TD colspan="3" class="listline">${resume.remark}</TD>
				    </TR>
	 			 	<TR>
						<TD colspan="4" class="listline">&nbsp;</TD>
					</TR>
                    
	 			 	<TR bgcolor="#CCCCCC">
	 			 	  <TD colspan="4" ><span class="sort-title">技能信息列表：</span>
	 			 	    <input class="botton" type=button onClick="document.createSkillForm.submit()" value="新增">
						<s:form name="createSkillForm" action="skillCreate!input.action" method="POST">
						<s:hidden name="resumeid" value="${resume.resumeid}"/>
						</s:form>					  
					  </TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">
					  
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD width="2%"></TD>
                      <TD width="23%" >技能名称</TD>
                      <TD width="17%" >熟练程度</TD>
                      <TD width="28%" >描述</TD>
                      <TD width="15%" >使用时间</TD>
                        <TD width="8%" >编辑</TD>
                        <TD width="7%" >删除</TD>
                      </TR>
<s:iterator value="skilllist" status="status">
                      <TR class=listline>
                        <TD ><img src="../images/arr_2.gif"></TD>
                        <TD>${skillname}</TD>
                        <TD>${degree}</TD>
                        <TD>${description}</TD>
                        <TD>${years}&nbsp;年</TD>
                        <TD><a href="skillEdit!input.action?resumeid=${resume.resumeid}&skillid=${skillid}">编辑</a></TD>
                        <TD><a href="skillDelete.action?resumeid=${resume.resumeid}&skillid=${skillid}" onClick="javascript:return confirm('您确定要删除?')">删除</a></TD>
                  </TR>
</s:iterator>
<s:if test="skilllist!=null"></s:if>                    
                  </TBODY>
              </TABLE>					  </TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">&nbsp;</TD>
 			 	    </TR>
	 			 	<TR bgcolor="#CCCCCC">
	 			 	  <TD colspan="4" ><span class="sort-title">教育信息列表：</span>
	 			 	      <input name="button" type=button class="botton" onClick="document.createStudyexpForm.submit()" value="新增">
						  <s:form name="createStudyexpForm" action="studyexpCreate!input.action" method="POST">
							<s:hidden name="resumeid" value="${resume.resumeid}"/>
						  </s:form>			  
					  </TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">

                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD>&nbsp;</TD>
                      <TD >学历</TD>
                      <TD >教育机构</TD>
                      <TD >开始时间</TD>
                      <TD >结束时间</TD>
                      <TD >专业</TD>
                      <TD >资历证书</TD>
                      <TD >技能专长</TD>
                      <TD >编辑</TD>
                      <TD >删除</TD>
                      </TR>
<s:iterator value="studyexplist" status="status">
                      <TR class=listline>
                        <TD ><img src="../images/arr_2.gif"></TD>
                        <TD>${education}</TD>
                        <TD>${school}</TD>
                        <TD>${begindate}</TD>
                        <TD>${enddate}</TD>
                        <TD>${major}</TD>
                        <TD>${certification}</TD>
                        <TD>${skills}</TD>
                        <TD><a href="studyexpEdit!input.action?resumeid=${resume.resumeid}&studyexpid=${studyexpid}">编辑</a></TD>
                        <TD><a href="studyexpDelete.action?resumeid=${resume.resumeid}&studyexpid=${studyexpid}" onClick="javascript:return confirm('您确定要删除?')">删除</a></TD>
                  </TR>
</s:iterator>
<s:if test="studyexplist!=null"></s:if>                    
                  </TBODY>
              </TABLE>					  
					  
					  </TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">&nbsp;</TD>
 			 	    </TR>
	 			 	<TR bgcolor="#CCCCCC">
	 			 	  <TD colspan="4" ><span class="sort-title">工作经验列表：</span>
	 			 	      <input name="button" type=button class="botton" onClick="document.createWorkexpForm.submit()" value="新增">
						  <s:form name="createWorkexpForm" action="workexpCreate!input.action" method="POST">
							<s:hidden name="resumeid" value="${resume.resumeid}"/>
						  </s:form>			  
					  </TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">
					  
                <TABLE width="100%"  border=0 align=center cellPadding=3 cellSpacing=1 bgcolor="#F9F9F7">
                  <TBODY>
                      <TR class="listheadline">
                        <TD>&nbsp;</TD>
                      <TD >公司名称</TD>
                      <TD >企业性质</TD>
                      <TD >职务</TD>
                      <TD >在职时间</TD>
                      <TD >薪水</TD>
                      <TD >离职原因</TD>
                      <TD >工作职责和突出业绩</TD>
                      <TD >证明人资料</TD>
                      <TD >编辑</TD>
                      <TD >删除</TD>
                      </TR>
<s:iterator value="workexplist" status="status">
                      <TR class=listline>
                        <TD ><img src="../images/arr_2.gif"></TD>
                        <TD>${companyname}</TD>
                        <TD>${character}</TD>
                        <TD>${duty}</TD>
                        <TD>从:${begindate} <br>到:${enddate}</TD>
                        <TD>${salary}</TD>
                        <TD>${leavereason}</TD>
                        <TD title="${achievement}">${achievement}</TD>
                        <TD>姓名：${attesterman}<br> 职位：${attestermanduty}<br> 电话：${attestermanphone}</TD>
                        <TD><a href="workexpEdit!input.action?resumeid=${resume.resumeid}&workexpid=${workexpid}">编辑</a></TD>
                        <TD><a href="workexpDelete.action?resumeid=${resume.resumeid}&workexpid=${workexpid}" onClick="javascript:return confirm('您确定要删除?')">删除</a></TD>
                  </TR>
</s:iterator>
<s:if test="workexplist!=null"></s:if>                    
                  </TBODY>
              </TABLE>					  
					  
					  </TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">&nbsp;</TD>
 			 	    </TR>
	 			 	<TR>
	 			 	  <TD colspan="4" class="listline">&nbsp;</TD>
 			 	    </TR>
	 			 	<TR>
						<TD colspan="4" class="listline"></TD>
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
   <s:form name="editForm" action="resumeEdit!input.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="deleteForm" action="resumeDelete.action" onsubmit="javascript:return confirm('您确定要删除?')" 
   method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
   <s:form name="backForm" action="memberList.action" method="POST">
   <s:hidden name="pagenumber" value="${pagenumber}"/>
   </s:form>
</BODY>
</HTML>
