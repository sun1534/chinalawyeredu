
function getUploadFile(id){

		$.ajax({
				type : "POST",
				url : "uploadfile!input.action",
				data:"id="+id,
				success : function(data) {
					removeSelectalbum();
					$.blockUI({message:data});
				}
			});
			
		setTimeout("swfload()",1000);
	
}

/**
 * 加入自定义传递的参数
 */
function addMyPostPrams(){
	var filenames = $(".swf_upload_filename");

	for(var i=0;i<filenames.length;i++){

		swfu.addPostParam("swfUploadFileName_"+i,filenames.get(i).value);
	}
}

function removeUploadPhoto(){
	$("#uploadpic").remove();
	$(".blockUI").remove();
}

function removeSelectalbum(){
	$("#selectalbum").remove();
	$(".blockUI").remove();
}

function getselectAlbum(){

	$.ajax({
				type : "POST",
				url : "selectalbum.action",
				beforeSend : function() {
				},
				success : function(data) {
//					$(".ft").after(data);
//					$.Jxq.ShowDialog('#selectalbum',600,-300,-100);
					$.blockUI({message:data});
				}
			});
}


var swfu;

function swfload() {

	var settings = {
		flash_url : "../js/swfupload/swfupload.swf",
		upload_url: "../filemanage/uploadfile.action",
		post_params: {
			jxq_ : ""
		},
		file_size_limit : "5120",
		//file_types : "*.jpg;*.jpeg;*.gif",
		//file_types_description : "*.jpg;*.jpeg;*.gif",
		file_upload_limit : 10,
		file_queue_limit : 0,
		custom_settings : {
			progressTarget : "fsUploadProgress",
			cancelButtonId : "btnCancel",
			uploadButtonId : "btnUpload",
			myFileListTarget : "idFileList"
		},
		debug: false,
		auto_upload:false,

		/* Button Settings */
		button_image_url : "../css/images/XPButtonUploadText_61x22.png",	// Relative to the SWF file
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 61,
		button_height: 22,

		/* The event handler functions are defined in handlers.js */
		swfupload_loaded_handler : swfUploadLoaded,
		file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete,	// Queue plugin event
		
		/* SWFObject settings */
		minimum_flash_version : "9.0.28",
		swfupload_pre_load_handler : swfUploadPreLoad,
		swfupload_load_failed_handler : swfUploadLoadFailed
	};
	
	settings.post_params.jxq_ = $("#jxq_").attr("value");
	
	swfu = new SWFUpload(settings);

}