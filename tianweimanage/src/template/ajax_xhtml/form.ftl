<#if parameters.validate?exists>
<script type="text/javascript" src="${base}/struts/validationClient.js"></script>
<script type="text/javascript" src="${base}/dwr/interface/validator.js"></script>
<script type="text/javascript" src="${base}/dwr/engine.js"></script>
<script type="text/javascript" src="${base}/struts/ajax/validation.js"></script>
</#if>
<#include "/${parameters.templateDir}/xhtml/form-validate.ftl" />
<#include "/${parameters.templateDir}/simple/form.ftl" />
<#include "/${parameters.templateDir}/xhtml/control.ftl" />