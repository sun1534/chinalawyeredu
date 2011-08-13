<%-- 
    visitorValidatorsExample.jsp
    
    @author tm_jee
    @version $Date: 2006-09-06 00:07:10 -0400 (Wed, 06 Sep 2006) $ $Id: visitorValidatorsExample.jsp 440609 2006-09-06 04:07:10Z wsmoak $
--%>


<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head><title>Showcase - Validation - VisitorValidatorsExample </title>
        <s:url id="siteCss" value="/validation/validationExamplesStyles.css" includeContext="true" />
        <s:head />
        <!-- link rel="stylesheet" type="text/css" href='<s:property value="%{siteCss}" />' -->
</head>
<body>

    <!-- START SNIPPET: visitorValidatorsExample -->
    
    <s:fielderror />

    <s:form method="POST" action="submitVisitorValidatorsExamples" namespace="/validation">
        <s:textfield name="user.name" label="User Name" />
        <s:textfield name="user.age" label="User Age" />
        <s:textfield name="user.birthday" label="Birthday" />
        <s:submit label="Submit" />
    </s:form>
    
    <!--  END SNIPPET: visitorValidatorsExample -->
    
    
    <s:include value="footer.jsp" />
</body>
</html>

