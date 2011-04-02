var switchbar =function (){
	if (parent.document.getElementById('wapFrame').cols=="175,9,*"){
		document.getElementById('switchbar').className="switchbarimg";
		document.getElementById('switchbar').title="展开左侧菜单";
		parent.document.getElementById('wapFrame').cols="0,9,*";
	}
	else{
		parent.document.getElementById('wapFrame').cols="175,9,*";
		document.getElementById('switchbar').className="switchbarimg_";
		document.getElementById('switchbar').title="关闭左侧菜单";
	}
};
var loadswitchbar= function (){
	if (parent.document.getElementById('wapFrame').cols=="0,9,*"){
		document.getElementById('switchbar').className="switchbarimg";
		document.getElementById('switchbar').title="展开左侧菜单";
	}

	else{
		document.getElementById('switchbar').className="switchbarimg_";
		document.getElementById('switchbar').title="关闭左侧菜单";
	}
};
