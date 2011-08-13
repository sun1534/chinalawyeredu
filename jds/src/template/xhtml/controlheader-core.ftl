<#--
	Only show message if errors are available.
	This will be done if ActionSupport is used.
-->
<#assign hasFieldErrors = parameters.name?exists && fieldErrors?exists && fieldErrors[parameters.name]?exists/>
<#if hasFieldErrors>
<#list fieldErrors[parameters.name] as error>
<#if parameters.labelposition?default("") == 'top'>
    <#rt/>
<#else>
    <#rt/>
</#if>
        <span class="errorMessage">${error?html}</span><#t/>
    <#lt/>

</#list>
</#if>
<#--
	if the label position is top,
	then give the label it's own row in the table
-->
<#if parameters.labelposition?default("") == 'top'>
    <#rt/>
<#else>
    <#rt/>
</#if>
<#if parameters.label?exists>
    <label <#t/>
<#if parameters.id?exists>
        for="${parameters.id?html}" <#t/>
</#if>
<#if hasFieldErrors>
        class="errorLabel"<#t/>
<#else>
        class="label"<#t/>
</#if>
    ><#t/>
<#if parameters.required?default(false) && parameters.requiredposition?default("right") != 'right'>
        <span class="required">*</span><#t/>
</#if>
${parameters.label?html}<#t/>
<#if parameters.required?default(false) && parameters.requiredposition?default("right") == 'right'>
 <span class="required">*</span><#t/>
</#if>
:<#t/>
<#include "/${parameters.templateDir}/xhtml/tooltip.ftl" /> 
</label><#t/>
</#if>
    <#lt/>
<#-- add the extra row -->
<#if parameters.labelposition?default("") == 'top'>
</#if>
