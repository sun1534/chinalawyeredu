function getEditDir(id) {
	$.ajax({
		type : "POST",
		url : "editdir!input.action",
		data: "id="+id,
		success : function(data) {
//			$(".ft").after(data);
//			$.Jxq.ShowDialog('#editalbums',400,-120,-100);
			$.blockUI({message:data});
			
		}
	});
}

function removeEditAlbum(){
	$('#editalbums').remove();
	$(".blockUI").remove();
}

function editalbum(id,dirname,description) {
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
			url : "editdir.action",
			data : "id=" + id + "&dirname=" + dirname + "&description=" + description,
			success : function(data) {
				window.location = window.location;//"../filemanage/dirlist.action";
			}
		});
	}
}