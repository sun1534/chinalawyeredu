/**
 * 添加相片评论
 */
function addcomment(photoid){
	var content = $("#commcontent").attr("value");
	if ($.trim(content) == "") {
			alert("请输入评论内容");
			return false;
		}
//	alert(content);
//	alert(photoid);
	$.ajax({
				type : "POST",
				url : "addcomment.action",
				data: "photoid=" + photoid +"&comment=" + content,
				beforeSend : function() {
				},
				success : function(data) {
					$("#commentlist").replaceWith(data);
					$("#commcontent").attr("value","");
				}
			});
}

/**
 * 获取评论分页列表
 * @param {} pageno
 */
function getcommentpage(pageno){
		//alert(pageno);
	var photoid = $("#photoid").attr("value");
	$.ajax({
			type : "POST",
			url : "getcomment.action",
			data: "photoid=" + photoid +"&pageNo=" + pageno,
			beforeSend : function() {
			},
			success : function(data) {
				$("#commentlist").replaceWith(data);
			}
		});
}
var commentid;
function showdelcomment(i,commentid){
	$.Jxq.delTips('#mag_a_id'+i,'#mag_del',-60,10,180);
	this.commentid = commentid;
}

/**
 * 删除评论
 * @param {} 
 */
function delcomment(){
	var photoid = $("#photoid").attr("value");
	var pageno = $("#pageNo").attr("value");
	//alert(commentid);
	$.Jxq.hideTips('#mag_del');
	$.ajax({
		type : "POST",
		url : "delcomment.action",
		data: "commentid=" + commentid + "&photoid=" + photoid + "&pageNo=" + pageno,
		beforeSend : function() {
		},
		success : function(data) {
			
			$("#commentlist").replaceWith(data);
			
		}
	});
	
}