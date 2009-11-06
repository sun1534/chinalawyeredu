function editphoto(photoid){
//	alert(photoname);
	var photoname = $('#input_editpic').attr('value')
	if(photoname==""){
		alert("请输入照片名");
		return false;
	}
	$.ajax({
        type: "POST",
        url:"editphoto.action",
        data:"photoid="+photoid+"&photoname="+photoname,
        beforeSend:function(){
        },
        success:function(data){
        	 if(data=="1"){
        	 	$("#edit_picname span").show();
				$("#edit_picname input").hide();
        	 	$("#photoname1").html(photoname);
        	 	}
        	 else{
        	 	alert("编辑失败");
        	 	$("#edit_picname span").show();
				$("#edit_picname input").hide();
        	 }
     }});

}

function EditPicName(){
	$("#edit_picname").mouseover(function(){$("#edit_picname a").show()}).mouseout(function(){$("#edit_picname a").hide()})
	// $("#edit_picname a").click(picedit_show);
	$("#edit_picname #remove_editpic").click(picedit_hide);
		
}

//function change_picedit(){
//		$("#edit_picname span").toggle();
//		$("#edit_picname input").toggle();	
//}

function picedit_show(){
	$("#edit_picname span").hide();
	$("#edit_picname input").show();
}

function picedit_hide(){
	$("#edit_picname span").show();
	$("#edit_picname input").hide();
}