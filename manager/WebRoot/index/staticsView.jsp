<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="sysgroup==null||sysgroup.grouptype>=3">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="10" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;统计信息</td>
        </tr>
         <tr>
        <td height="23" colspan="3"  align="right" background="../imagesa/top-bg1.gif"><b>【${jifentime.startstr}】
            到 【${jifentime.endstr}】</b></td>
      </tr>
      <tr  bgcolor="#F2F8FF" >
        <td align="right" width="45%" height="18" valign="middle">
        <b>现场培训课程数：</b></td>
        <td align="left" >${lessonstatics.local}</td>      
        </tr>
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>网上培训课件数： </b></td>                   
        <td align="left" >${lessonstatics.online}</td>  
        </tr>
        <!--
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>新增文本课件：</b> </td>                   
        <td align="left" >0</td>
        </tr>-->
      
     <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>参加现场培数训： </b> </td>                  
        <td align="left" >${learnmodestatics.local}</td>
        </tr>
  
     <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>参加网上培训数： </b> </td>                  
        <td align="left" >${learnmodestatics.video}</td>
        </tr>
     
 
<tr  bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>总律师数： </b>   </td>                
        <td align="left" >${lawyerscnt}</td>
        </tr>
     
    </table></td>
  </tr>
</table>
</s:if>
 <s:elseif test="sysgroup.grouptype==2">
  <!--是管理员的话显示这个
  -->
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="10" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;市律协统计信息(达标分:${sysgroup.sysUnionparams.dabiaofen})</td>
        </tr>
      <tr>
        <td height="23" colspan="3"  align="right" background="../imagesa/top-bg1.gif"><b>【${jifentime.startstr}】
            到 【${jifentime.endstr}】</b></td>
      </tr>
        
      <tr  bgcolor="#F2F8FF" >
        <td align="right" width="45%" height="18" valign="middle">
        <b>现场培训课程数：</b></td>
        <td align="left" >${lessonstatics.local}</td>      
        </tr>
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>网上培训课件数： </b></td>                   
        <td align="left" >${lessonstatics.online}</td>  
        </tr>
        <!--
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>新增文本课件：</b> </td>                   
        <td align="left" >0</td>
        </tr>-->
      
     <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>参加现场培数训： </b> </td>                  
        <td align="left" >${learnmodestatics.local}</td>
        </tr>
  
     <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>参加网上培训数： </b> </td>                  
        <td align="left" >${learnmodestatics.video}</td>
        </tr>
     
 
<tr  bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>已达标人数： </b>   </td>                
        <td align="left" >${jifenstatics.dabiaoshu}</td>
        </tr>
      <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>未达标人数：</b> </td>                   
        <td align="left" >${jifenstatics.weidabiao}</td>
        </tr>
         <tr bgcolor="#F2F8FF" >
        <td align="right" height="18"><b>未培训人数：</b></td>                   
        <td align="left" >${jifenstatics.weipeixun}</td>
        </tr>     
    </table></td>
  </tr>
</table> <br/>
  </s:elseif>
 
   <!--是事务所管理员的话显示这个
   -->
<s:elseif test="sysgroup.grouptype==1">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#C2D6F0" >
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">
      <tr>
        <td height="24" colspan="11" background="../imagesa/top-bg2.gif" class="baseFontBold"  align="left">&nbsp;&nbsp;事务所信息</td>
        </tr>

      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="30" valign="middle">
        <b>证：</b></td>
        <td align="left" bgcolor="#F2F8FF"> ${sysgroup.groupenname} </td>      
        <td width="120" rowspan="7" align="center" valign="middle" bgcolor="#F2F8FF" >
        <s:if test="officelogo==null||officelogo.equals(\"\")">
          <img src="../imagesa/none.jpg" width="106"/>        
          </s:if>
          <s:else>
          <img src="${logopath}${officelogo}" width="106"/>
          </s:else>
          </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>所： </b></td>                   
        <td align="left" bgcolor="#F2F8FF">${sysgroup.groupname}  </td>  
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>人：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${jifenstatics.allusers}</td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>管： </b> </td>                  
        <td align="left" bgcolor="#F2F8FF">${sysgroup.contacter}</td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>电：</b> </td>                   
        <td align="left" bgcolor="#F2F8FF">${sysgroup.phone}
        </td>
      </tr>
      <tr>
        <td align="center" bgcolor="#F2F8FF" width="35" height="23"><b>址： </b>   </td>                
        <td align="left" bgcolor="#F2F8FF">${group.address}</td>
      </tr>
      <tr>
        <td width="35" align="center" bgcolor="#F2F8FF">  </td>                   
        <td align="left" bgcolor="#F2F8FF">        </td></tr>
    </table></td>
  </tr>
</table>
<br/>
</s:elseif>