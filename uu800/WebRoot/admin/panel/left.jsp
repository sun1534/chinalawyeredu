<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" id="lefthtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
<title>${sysName}</title>
<link rel="stylesheet" type="text/css" href="../style/css/reset.css" />
<link rel="stylesheet" type="text/css" href="../style/css/left.css" />
</head>
<body id="SiderBody">
<div id="sider">
	<div class="siderMenu" id="siderMenu">
      <s:iterator value="#session.menuList" status="status">
      <div class="toggleHd ${status.index==0?'current':''}"><h2>${menuname}</h2></div>
      <div class="toggleBd">
          <div class="siderNav">
              <ul>
              <s:iterator value="childMenus" status="status2">
                  <li><a href="${linkurl}" title="" target="mainFrame">${menuname}</a></li>
              </s:iterator>
              </ul>
          </div>
      </div>            
      </s:iterator>
      <div id="siderBT"></div>
    </div>
</div>
<script type="text/javascript" src="../style/js/left.js"></script>
</body>
</html>
