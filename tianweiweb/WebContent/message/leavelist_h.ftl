<#macro head>
<link href="../css/default/message.css" rel="stylesheet" type="text/css" />
<script src="${staticpath}/style/js/tokeninput.js" ></script>
<script src="../js/leavemessage.js" ></script>
<link rel="stylesheet" type="text/css" href="${staticpath}/style/images/tokeninput/style.css" />
<script>
function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}
</script>
</#macro>