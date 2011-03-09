$(function(){
	$(".tableMod tr:even").addClass("tdEven");
	hoverBg(".tableMod","tbody tr","tdOver")
});

function hoverBg(em,o,c){
	 $(em+" "+o).hover(
	  function () {
		$(this).addClass(c);
	  },
	  function () {
		$(this).removeClass(c);
	  }
	);
}


function modTableBg(){
	$(".tableMod tr:even").addClass("tdEven");
	hoverBg(".tableMod","tbody tr","tdOver");
}
