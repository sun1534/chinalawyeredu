<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${sysName}</title>
<link rel="stylesheet" type="text/css" href="../css/style.css" media="all"/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script language="javascript">
$(document).ready(function(){
 $("#_tablebox tr:even").addClass("odd");
});
</script>

</head>
<body>
<div class="oper"> 当前位置：系统管理 &gt; 参数管理 &gt;参数修改</div>
<div class="maincontent">
 <s:form name="form1" action="sysParameterEdit" method="post" validate="true">
  <div class="tablebox">
    <p>红色<span class="cR">*</span>表示的选项为<span class="cR">必填</span></p>
    
    <table border="0" cellspacing="0" cellpadding="0" width="90%" id="_tablebox">
      <tr>
        <td width="25%" class="tar">
          <s:if test="typeid==2">
           网站名称：
          </s:if>
            <s:else>  参数名：
            </s:else>
        
      </td>
        <td>
       <s:textfield name="sysParameter.paramname" size="25" maxlength="150" cssClass="text1" required="true"/>
        <span class="cR">不为空且长度不超过7个汉字</span>
        </td>
      </tr>
      <tr>
        <td width="25%" class="tar">
        <s:if test="typeid==2">
       网站URL
        </s:if>
        <s:else>
        参数值：
        </s:else>
        </td>
        <td>
       <s:textfield name="sysParameter.paramvalue" size="25" maxlength="150" cssClass="text1" required="true"/>
        <span class="cR">不能为空</span>
        </td>
      </tr>
        <tr>
        <td class="tar">
        
           <s:if test="typeid==2">
        网站说明：
        </s:if>
        <s:else>
         参数说明：：
        </s:else>
        </td>
        <td>
          <s:textarea name="sysParameter.comments" rows="5" cols="40" cssClass="textarea1"/>
        </td>
      </tr>
     
      <tr>
        <td colspan="2">&nbsp;</td>
       
      </tr>
    </table>
    <p class="pbtn">
  <button class="btn" type="submit" id="save">保存</button>
  <button class="btn" type="button" onclick="javascript:history.go(-1)">返回</button>
  </p>
  </div>
  </s:form>
</div>
</body>
</html>
