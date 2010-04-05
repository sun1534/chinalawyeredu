<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="3"  align="left" background="../imagesa/top-bg2.gif" class="baseFontBold">&nbsp;&nbsp;培训论坛          </td>
        <td height="24" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="center">
        <a href="../forum/forumList.pl">更多...</a>
        </td>
      </tr>
      <tr>
        <td width="45%" height="23" background="../imagesa/top-bg1.gif" align="center">标题</td>
        <td width="13%" height="23" background="../imagesa/top-bg1.gif"  align="center">创建者</td>
       <!--  <td width="12%" height="23" background="../imagesa/top-bg1.gif"  align="center">板块</td>-->
        <td width="10%" background="../imagesa/top-bg1.gif"  align="center">回复数</td>
        <td width="20%" height="23" background="../imagesa/top-bg1.gif"  align="center">上次更新时间</td>
      </tr>
      <s:iterator value="forumList" status="stat">
      <tr>
       
        <td class="tab_content1" align="left"><a href="../forum/forumView.pl?mainforumid=${forumid}">&nbsp;&nbsp;${title}</a></td>
        <td class="tab_content1" align="center">${createuser}</td>
        <!-- <td class="tab_content1" align="center">
         <s:property value="@com.changpeng.system.util.CommonDatas@groups[thegroup]"/>
        </td>-->
         <td class="tab_content1" align="center">${replycount}</td>
       
        <td class="tab_content1" align="center"><s:date name="lastupdate" format="yyyy-MM-dd HH:mm"/></td>
      </tr>
      </s:iterator>
      <tr>
        <td colspan="5" align="left" class="tab_content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value=" 我要发帖 " name="addForum" class="button" onClick="javascript:window.location.href='../forum/forumCreate!input.pl'"/></td>
        </tr>
    </table>
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="5"  align="left" background="../imagesa/top-bg2.gif" class="baseFontBold">&nbsp;&nbsp;转所申请列表</td>
        <td height="24" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="center">
        <a href="../lawyers/officeChangeList.pl">更多...</a>
        </td>
      </tr>
      <tr>
        <td height="23" background="../imagesa/top-bg1.gif" align="center">律师</td>
        <td background="../imagesa/top-bg1.gif"  align="center">申请人</td>
        <td background="../imagesa/top-bg1.gif"  align="center">原事务所</td>
        <td height="23" background="../imagesa/top-bg1.gif"  align="center">申请事务所</td>
        <td background="../imagesa/top-bg1.gif"  align="center">申请时间</td>
        <td background="../imagesa/top-bg1.gif"  align="center">当前状态</td>
      </tr>
      <s:iterator value="officeChangeList" status="stat">
      <tr>
        <TD class="tab_content" align="center"><a href="../lawyers/lawyerView.pl?lawyerid=${lawyerid }">${lawyername }</a></TD>
         <TD class="tab_content" align="center">${applyname }</TD>
       <TD class="tab_content" align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[oldoffice]"/></TD>
         <TD class="tab_content" align="center"><s:property value="@com.changpeng.system.util.CommonDatas@groups[newoffice]"/></TD>
       <TD class="tab_content" align="center"><s:date name="applyTime" format="yyyy-MM-dd HH:mm:ss"/></TD>
       <TD class="tab_content" align="center">${statusStr}
       <s:if test="status==0">
      &nbsp;<s:if test="canhandle"><a href="officeChangeHandle!input.pl?id=${id }">【处理】</a></s:if>
       </s:if>
       </TD>    
        </s:iterator>
        </tr>
    </table>