function getCreateAlbum() {

	$.ajax({
				type : "POST",
				url : "createalbum!input.action",
				success : function(data) {
//					$(".ft").after(data);
//					$.Jxq.ShowDialog('#createalbums',400,-120,-100);
					$.blockUI({message:data});
				}
			});
}

function removeCreateAlbum() {
	//$.Jxq.HideDialog('#createalbums');
	$('#createalbums').remove();
	$(".blockUI").remove();
}

function addalbum() {
    
	var albumname = $('#albumName').attr('value');
	var privateflag = $('#privateFlag').attr('value');
	var remark = $('#remark').attr('value');
	//alert(albumname);
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
					url : "createalbum.action",
					data : "albumName=" + albumname + "&privateFlag=" + privateflag + "&remark=" + remark ,
					beforeSend : function() {

					},
					success : function(data) {

						window.location = "../photo/albumlist.action";
					}
				});

	}
}