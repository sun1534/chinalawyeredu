<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/css.css" rel="stylesheet" type="text/css">
<link href="../css/system.css" rel="stylesheet" type="text/css">
<link href="../css/system_${curuser.style}.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" height="95%"  border="0" cellpadding="3" cellspacing="1">

  <tr>
    <td width="59%" valign="top"><br>
      <table width="95%" border="0" align="center" cellpadding="3" cellspacing="1">
      <tr>
        <td class="t_black">待办事宜:</td>
      </tr>
      <tr>
        <td height="1" bgcolor="#E4E4E4"></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="1" cellpadding="3">
          <tr>
            <td width="25%"><img src="../images/t3.gif" width="78" height="54"></td>
            <td width="75%" valign="top"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
				<!--tr-->
            </table>
              <table width="100%"  border="0" cellspacing="1" cellpadding="0">
                <s:iterator value="waitlist" status="status">
                <tr>
                  <td width="3%" align="center"><img src="../images/arr_2.gif" width="6" height="7"></td>
                  <td width="97%" height="23"><a href="<s:property value='url'/>&backurl=../info/webViewList.action&pagenumber=<s:property value='pagenumber'/>" target="<s:property value='target'/>">
				  ${subject}</a></td>
                </tr>
                </s:iterator>
                <tr>
                  <td ></td>
                  <td ><div align="right"><a href="../wait/listWait1.action">更多...</a></div></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td class="t_black">最新公告:&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td height="1" bgcolor="#E4E4E4"></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="1" cellpadding="3">
          <tr>
            <td width="25%"><img src="../images/t1.gif" width="78" height="54"></td>
            <td width="75%" valign="top"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
			  <s:iterator value="bulletinlist" status="status">
              <tr>
                <td width="3%" align="center"><img src="../images/arr_2.gif" width="6" height="7"></td>
                <td width="97%" height="23"><a href="infoWebView.action?infoid=${infoid}&type=${type}&pagenumber=${pagenumber}">${infoname}</a></td>
              </tr>
			  </s:iterator>
              <tr>
                <td ></td>
                <td ><div align="right"><a href="../info/infoViewList.action?type=2">更多...</a></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><span class="t_black">公司新闻:&nbsp;&nbsp;</span></td>
      </tr>
      <tr>
        <td height="1" bgcolor="#E4E4E4"></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="1" cellpadding="3">
          <tr>
            <td width="25%"><img src="../images/t1.gif" width="78" height="54"></td>
            <td width="75%" valign="top"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
			  <s:iterator value="newslist" status="status">
              <tr>
                <td width="3%" align="center"><img src="../images/arr_2.gif" width="6" height="7"></td>
                <td width="97%" height="23"><a href="infoWebView.action?infoid=${infoid}&pagenumber=${pagenumber}">${infoname}</a></td>
              </tr>
			  </s:iterator>
              <tr>
                <td ></td>
                <td ><div align="right"><a href="../info/infoViewList.action?type=1">更多...</a></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
	  

	  
	  

      <tr>
        <td class="t_black">规章制度:&nbsp;&nbsp;</td>
      </tr>
      <tr>
        <td height="1" bgcolor="#E4E4E4"></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="1" cellpadding="3">
          <tr>
            <td width="25%"><img src="../images/t2.gif" width="78" height="54"></td>
            <td width="75%" valign="top"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
			  <s:iterator value="bylawlist" status="status">
              <tr>
                <td width="3%" align="center"><img src="../images/arr_2.gif" width="6" height="7"></td>
                <td width="97%" height="23"><a href="infoWebView.action?infoid=${infoid}&pagenumber=${pagenumber}">${infoname}</a></td>
              </tr>
			  </s:iterator>
              <tr>
                <td ></td>
                <td ><div align="right"><a href="../info/infoViewList.action?type=3">更多...</a></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="1%" valign="middle"><img src="../images/right_line.gif" width="3" height="440"></td>
    <td width="40%" valign="bottom"><table width="100" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td align="center"><img src="../images/right1.gif" width="285" height="134"></td>
      </tr>
      <tr>
        <td height="62" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td><img src="../images/right2.jpg" width="308" height="194"></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
