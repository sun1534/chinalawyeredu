<%-- 
    nonFieldValidatorsExample.jsp
    
    @author tm_jee
    @version $Date: 2006-09-06 00:07:10 -0400 (Wed, 06 Sep 2006) $ $Id: nonFieldValidatorsExample.jsp 440609 2006-09-06 04:07:10Z wsmoak $
--%>


<%@taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Showcase - Validation - Non Field Validator Example</title>
        <s:url id="siteCss" value="/validation/validationExamplesStyles.css" includeContext="true" />
        <s:head />
        <!-- link rel="stylesheet" type="text/css" href='<s:property value="%{siteCss}" />'-->
    </head>
    <body>
    
       
       <!-- START SNIPPET: nonFieldValidatorsExample -->
        <s:actionerror />
    
        <s:form method="POST" action="submitNonFieldValidatorsExamples" namespace="/validation">
            <s:textfield name="someText" label="Some Text" />
            <s:textfield name="someTextRetype" label="Retype Some Text" />  
            <s:textfield name="someTextRetypeAgain" label="Retype Some Text Again" />
            <s:submit label="Submit" />
        </s:form>
        
        
        <!--  END SNIPPET: nonFieldValidatorsExample -->
        
        
        <s:include value="footer.jsp" />
    </body>
</html>

