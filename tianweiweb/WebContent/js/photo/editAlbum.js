function getEditAlbum(albumid) {

	$.ajax({
				type : "POST",
				url : "editalbum!input.action",
				data: "albumid="+albumid,
				beforeSend : function() {
				},
				success : function(data) {
//					$(".ft").after(data);
//					$.Jxq.ShowDialog('#editalbums',400,-120,-100);
					$.blockUI({message:data});
				}
			});
}

function removeEditAlbum(){
	$('#editalbums').remove();
	$(".blockUI").remove();
}


function editalbum(albumid,albumname,privateFlag,remark) {
//	 alert("editalbum("+albumid+","+albumname+","+privateFlag+","+remark+")"); 
	var pattern = /[~!@#$%^&*()_+]/;
	if ($.trim(albumname) == "") {
		alert("请输入相册名");
		return false;
	}
	if (pattern.exec(albumname)) {
		alert("您输入的相册名称中包含了非法字符,请重新输入");
	} else {
		$.ajax({
					type : "POST",
					url : "editalbum.action",
					data : "albumid=" + albumid + "&albumName=" + albumname + "&privateFlag=" + privateFlag + "&remark=" + remark,
					beforeSend : function() {
					},
					success : function(data) {

						window.location = "../photo/albumlist.action";
					}
				});

	}
}