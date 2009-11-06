function getCreateDir(dirtype) {
	$.ajax({
		type : "POST",
		url : "createdir!input.action?dirtype="+dirtype,
		success : function(data) {
			$.blockUI({message:data});
		}
	});
}

function removeCreateAlbum() {
	$('#createalbums').remove();
	$(".blockUI").remove();
}

function adddir() {
	var dirname = $('#dirname').attr('value');
	var description = $('#description').attr('value');
	var dirtype = $('#dirtype').attr('value');
	var pattern = /[~!@#$%^&*()_+]/;
	if ($.trim(dirname) == "") {
		alert("请输入目录名称");
		return false;
	}
	if (pattern.exec(dirname)) {
		alert("您输入的目录名称中包含了非法字符,请重新输入");
	} else {
		$.ajax({
			type : "POST",
			url : "../filemanage/createdir.action",
			data : "dirname=" + dirname + "&description=" + description+ "&dirtype=" + dirtype,
			success : function(data) {
//				alert(data);
				window.location = "../filemanage/dirlist.action?dirtype="+dirtype;
			}
		});
	}
}