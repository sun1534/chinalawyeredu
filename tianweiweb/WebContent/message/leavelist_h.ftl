<#macro head>
<link href="../css/default/message.css" rel="stylesheet" type="text/css" />
<script src="${staticpath}/style/js/tokeninput.js" ></script>
<script src="../js/leavemessage.js" ></script>
<script type="text/javascript" src="../js/blog.js"></script>
<script type="text/javascript" src="../js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="../js/swfupload/swfupload.swfobject.js"></script>
<script type="text/javascript" src="../js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="../js/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="../js/swfupload/handlers.js"></script>
<script src="../js/filemanage/publishFile.js"></script>
<link rel="stylesheet" type="text/css" href="${staticpath}/style/images/tokeninput/style.css" />
<script>
function getPage(pageno){
	document.pageForm.pageNo.value=pageno;
	document.pageForm.submit();
}
</script>
</#macro>