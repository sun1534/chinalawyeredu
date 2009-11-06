$(document).ready(function() {
    $(function(){
        $("#fileUpload").MultiFile({
            accept:'gif|jpg|png|bmp'/*可接收的文件类型*/,
            max:5/*最大接收的上传个数*/, STRING: {
                remove:"移除",
                selected:"Selecionado: $file",
                denied:"不接受的文件类型\n$ext!",
                duplicate:"文件已经存在\n$file!"
            }
        });
    });
})


function upphoto(){
	if($("#fileUpload").attr("value")==""){
		alert("请添加照片");
	}else{
		document.uploadphoto.submit();
	}
}

function backlist(){
	window.location = "../photo/albumlist.action";
}