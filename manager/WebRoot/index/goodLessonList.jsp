<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="9" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;系统帮助</td>
        </tr>

<!--      <tr>
  
        <td width="5%" height="23" background="../imagesa/top-bg1.gif"  align="left">费用</td>
     
      </tr>-->
           <s:iterator value="helpList" status="stat">
      <tr>
      
        <td class="tab_content1" align="left"><a href="../articles/articlesView.pl?articleid=${articleid}">&nbsp;&nbsp;${title}</a></td>
       
      </tr>
    </s:iterator>

    </table></td>
  </tr>
</table>
<br/>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="9" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">
        &nbsp;&nbsp;好评课程
          </td>
        </tr>
      <tr>
      
        <td width="80%" height="23" background="../imagesa/top-bg1.gif"  align="center">内容标题</td>
        <td width="20%" background="../imagesa/top-bg1.gif"  align="center">好评数</td>
        
      </tr>
        <s:iterator value="goodlessonList" status="goodstat">
      <tr>
       
        <td class="tab_content1" align="left">
      
       
        
        <s:set name="lessonid" value="goodlessonList[#goodstat.index][1]"/>
      
        
      <a href="../lessons/lessonsView.pl?lessonid=${lessonid}">  &nbsp;&nbsp;<s:property value="@com.changpeng.lessons.util.CommonDatas@AllLessonMap[#lessonid]"/></a>
        </td>
        <td  align="center" class="tab_content1"><s:property value="goodlessonList[#goodstat.index][0]"/></td>
 
      </tr>
         </s:iterator>

    </table></td>
  </tr>
</table>
<br/> 	
 