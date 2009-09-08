<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>投票结果</title>
<style type="text/css">
td,p,li,select,input {font-size:12px}
.l13 {line-height:100%}
.l15 {line-height:100%}
.f14 {font-size:14.9px;}
.fl {font-size:14.9px;line-height:100%}
A:hover {color: ff0000;}
td{word-break:break-all;}
</style>
</head>
<body>
<table width=402 border=0 cellpadding=0 cellspacing=0 align="center" bgcolor="#FFF2BB">
<tr><td>
	<table width=360 border=0 cellpadding=5 cellspacing=0 align="center" bgcolor="#FFF2BB">
		<tr><td height='8'></td></tr>
		<tr><td class=fl>${lesson.title}</td></tr>
		<s:set name="sumcount" value="common+preferably+good"/>
		<tr><td class=f14>共有<font color=#fc0a0b>${sumcount}</font>人参加</td></tr>
	</table>
<tr><td>
	<table width=360 border=0 cellpadding=0 cellspacing=0 bgcolor=#b18a02 align='center'>
<tr><td>
	<table width=360 border=0 cellpadding=3 cellspacing=1 align=center>
		<tr bgcolor=#f9dc34 align=center>
			<td class=f14 colspan='3' align=left style='padding-left:5px'>投票结果</td>
		</tr>
        <tr bgcolor=#ffffff align=center>
		
            <td class=f14 width=112 height=20>选项</td>
          <td class=f14 width=117>比例</td>
            <td class=f14 width=109>票数</td>
  </tr>
		<tr bgcolor=#ffffff align=center>
        	
            <td class=f14 width=112 height=20 align=left><font color=#0262cd>一般</font></td>
		    <td class=f14 width=117 align=left> 
       	  <table border=0>
                	<tr>
             
                    
                        <td align=left><img src='bg.gif' width=${commonl} height=10></td>
                </tr>
			  </table>
		   <td class=f14 width=109 align=left><font color=#0262cd>${common}</font></td>
  </tr>
		<tr bgcolor=#ffffff align=center>
        	
            <td class=f14 width=112 height=20 align=left><font color=#0262cd>较好</font></td>
			<td class=f14 width=117 align=left>
              <table border=0>
              	<tr>
					
                    <td align=left><img src='bg.gif' width=${preferablyl} height=10></td>
                </tr>
			</table>
			<td class=f14 width=109 align=left><font color=#0262cd>${preferably}</font></td>
  </tr>
		<tr bgcolor=#ffffff align=center>
        	
            <td class=f14 width=112 height=20 align=left><font color=#0262cd>很好</font></td>
			<td class=f14 width=117 align=left> 
       	  <table border=0>
                	<tr>
					  
                        <td align=left><img src='bg.gif' width=${goodl} height=10></td>
                     </tr>
			  </table>
			<td class=f14 width=109 align=left><font color=#0262cd>${good}</font></td>
 		 </tr>
	</table>
   </td></tr>
</table>
</td></tr>
<tr><td height=19>&nbsp;</td></tr>
<tr height="30"><td class=fl align="center">
<input type="button" value=" 关  闭 " onclick="window.close()" >
</td></tr>
</table>
<body>
</html>
