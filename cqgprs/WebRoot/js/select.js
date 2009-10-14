/*全选*/
		function selectAll() {
			 var checkbox = $("#checkForm :checkbox");
			 if(!$(this).attr('checked')){
				checkbox.attr('checked','');
				checkbox.parent().parent().children().addClass("current");
				checkbox.parent().parent().children().removeClass("nomal");
			 }else{
				checkbox.attr('checked','checked');
				checkbox.parent().parent().children().addClass("nomal");
				checkbox.parent().parent().children().removeClass("current");
			}
			selectOne();
		}

		/*单选取值*/
		function selectOne(){
			var str="";
			var l=0;
			$("[name=checkboxItem]").each(function(){
				if($(this).attr('checked')){
					$(this).parent().parent().children().addClass("current");
					$(this).parent().parent().children().removeClass("nomal");
					str+=$(this).val()+"|";
					l=$("[name=checkboxItem]:checked").size();
				}else{
					$(this).parent().parent().children().addClass("nomal");
					$(this).parent().parent().children().removeClass("current");
					$("#checkAll").attr('checked','');
				}
			})
			$("#getValue").text(str)//
			$("#checknum").text(l)
		}
