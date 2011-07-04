<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"  />
 <meta name="author" content="KevinXiao Email:kevin_218@163.com" />
 <title>${sysName}-参数列表</title>
 <link rel="stylesheet" type="text/css" href="../css/reset.css" />
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/pager.css" />
 <script type="text/javascript" src="../js/jquery.js"></script>
 <script language="javascript">
function fanye(str){
  document.form1.pageNo.value=str;
  document.form1.submit();
}
function getAdd(){
window.location.href="ggsnipset!input.action?type=new";
}
function deleteInfo(paramname){
   if(confirm('您确定要删除这个参数吗?'))
     window.location.href="ggsnipset.action?type=delete&paramname="+paramname;
   return false;
}
</script>
</head>

<body >
		<div class="navigation" id="quickTools">
			<div class="innavigation">
				<div  class="navlist">
					<span>您所在是位置:</span><a>MEM参数设置</a>＞<em>PDP激活IP接入GGSN配对列表</em>
				</div>
			</div>
		</div>
			<s:form name="form1" action="ggsnipList" method="post">	
		<div class="main">
			<div class="inmain">
				<div class="wrap">
			
					<!-- 操作模块-->
					<div class="operate">
						<input type="button" class="btnSubmit" title="保 存" value="新　增" onclick="getAdd()"/>
					<!--<input type="button" class="btnCancel" title="返 回" value="删　除"/>-->
					</div>
				  <div class="tablist">
			        <table class="tableBox" id="a">
                      <thead>
                        <tr>
                          <th>激活IP前2位</th>
                          <th>所在GGSN</th>
                          <th>接入方式</th>
                            <th>说明</th>
                          <th>修改</th>
                          <th>删除</th>
                        </tr>
                      </thead>
                      <tbody id="checkForm">
                        <s:iterator value="page.items" status="status">
                        <tr>
                          <td>${paramname}</td>
                          <td>${ggsn }</td>  
                          <td>${apn }</td>  
                          <td>${comments }</td>  
                          <td><a href="ggsnipset!input.action?type=modify&paramname=${paramname}">修改</a></td>
                          <td><a href="#" onclick="deleteInfo('${paramname}')">【删除】</a></td>
                        </tr>
                        </s:iterator>
                      
                      </tbody>
                 <!--    <tfoot>
							<tr>
							   <td colspan="6" class="fright">
							     <input type="button" value="导　出" title="导　出" class="btnSubmit "/>
							   </td>
							</tr>
						 </tfoot>
			     -->
                    </table>
			  </div>

				<div  class="tabpagelist">
						<div class="pager">
							${page.pageView}
						</div>
					</div>
				</div>
			</div>
		</div>
</s:form>
</body>

</html>

