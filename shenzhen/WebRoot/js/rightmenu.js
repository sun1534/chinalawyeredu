function contextMenu() {
	this.items = new Array();
	this.addItem = function(item) {
		this.items[this.items.length] = item;
	}
	this.show = function(oDoc) {
		var strShow = "";
		var i;
		strShow = "<div id=\"rightmenu\" style=\"BACKGROUND-COLOR: #ffffff; BORDER: #000000 1px solid; LEFT: 0px; POSITION: absolute; TOP: 0px; display: none; Z-INDEX: 10\">";
		strShow += "<table border=\"0\" height=\"";
		strShow += this.items.length * 20;
		strShow += "\" cellpadding=\"0\" cellspacing=\"0\">";
		strShow += "<tr height=\"3\"><td bgcolor=\"#d0d0ce\" width=\"2\"></td><td>";
		strShow += "<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=0 cellspacing=0 bgcolor=\"#ffffff\">";
		strShow += "<tr><td bgcolor=\"#d0d0ce\" width=\"23\"></td><td><img src=\" \" height=\"1\" border=\"0\"></td></tr></table>";
		strShow += "</td><td width=\"2\"></td></tr>";
		strShow += "<tr><td bgcolor=\"#d0d0ce\"></td><td>";
		strShow += "<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=3 cellspacing=0 bgcolor=\"#ffffff\">";
		oDoc.write(strShow);
		for (i = 0; i < this.items.length; i++) {
			this.items[i].show(oDoc);
		}
		strShow = "</table></td><td></td></tr>";
		strShow += "<tr height=\"3\"><td bgcolor=\"#d0d0ce\"></td><td>";
		strShow += "<table border=\"0\" width=\"100%\" height=\"100%\" cellpadding=0 cellspacing=0 bgcolor=\"#ffffff\">";
		strShow += "<tr><td bgcolor=\"#d0d0ce\" width=\"23\"></td><td><img src=\" \" height=\"1\" border=\"0\"></td></tr></table>";
		strShow += "</td><td></td></tr>";
		strShow += "</table></div>\n";
		oDoc.write(strShow);
	}
}

// menu Item object
function contextItem(text, icon, cmd, type) {
	this.text = text ? text : "";
	this.icon = icon ? icon : "";
	this.cmd = cmd ? cmd : "";
	this.type = type ? type : "menu";
	this.show = function(oDoc) {
		var strShow = "";
		if (this.type == "menu") {
			strShow += "<tr ";
			strShow += " 'on');\" ";
			strShow += " 'out');\" ";
			strShow += "onclick=\"";
			strShow += this.cmd;
			strShow += "\">";
			strShow += "<td class=\"ltdexit\" width=\"16\">";
			if (this.icon == "")
				strShow += "&nbsp;";
			else {
				strShow += "<img border=\"0\" src=\"";
				strShow += this.icon;
				strShow += "\" width=\"16\" height=\"16\" style=\"POSITION: relative\"></img>";
			}
			strShow += "</td><td class=\"mtdexit\">";
			strShow += this.text;
			strShow += "</td><td class=\"rtdexit\" width=\"5\">&nbsp;</td></tr>";
		} else if (this.type == "separator") {
			strShow += "<tr><td class=\"ltdexit\">&nbsp;</td>";
			strShow += "<td class=\"mtdexit\" colspan=\"2\"><hr color=\"#000000\" size=\"1\"></td></tr>";
		}

		oDoc.write(strShow);
	}
}

function changeStyle(obj, cmd) {
	if (obj)
		try {
			var imgObj = obj.children(0).children(0);
			if (cmd == 'on') {
				obj.children(0).className = "ltdfocus";
				obj.children(1).className = "mtdfocus";
				obj.children(2).className = "rtdfocus";
				if (imgObj) {
					if (imgObj.tagName.toUpperCase() == "IMG") {
						imgObj.style.left = "-1px";
						imgObj.style.top = "-1px";
					}
				}
			} else if (cmd == 'out') {
				obj.children(0).className = "ltdexit";
				obj.children(1).className = "mtdexit";
				obj.children(2).className = "rtdexit";
				if (imgObj) {
					if (imgObj.tagName.toUpperCase() == "IMG") {
						imgObj.style.left = "0px";
						imgObj.style.top = "0px";
					}
				}
			}
		} catch (e) {
			alert(e.errorMessage);
		}
}

function showMenu(e) {
	var x, y, w, h, ox, oy;
	e = window.event || e;
	x = e.x || e.layerX;
	y = e.y || e.layerY;

	var obj = document.getElementById("rightmenu");
	if (obj == null)
		return true;

	ox = document.body.clientWidth;
	oy = document.body.clientHeight;
	if (x > ox || y > oy)
		return false;
	w = obj.offsetWidth;
	h = obj.offsetHeight;
	if ((x + w) > ox)
		x = x - w;
	if ((y + h) > oy)
		y = y - h;

	obj.style.left = x + document.body.scrollLeft+"px";
	obj.style.top = y + document.body.scrollTop+"px";
	obj.style.display = "block";

	return false;
}
function hideMenu(e) {
	e = window.event || e;
	if (e.button == 0) {
		var obj = document.getElementById("rightmenu");
		if (obj == null)
			return true;
		obj.style.display = "none";
		obj.style.posLeft = 0;
		obj.style.posTop = 0;
	}
}

function writeStyle() {
	var strStyle = "";
	strStyle += "<STYLE type=text/css>";
	strStyle += "TABLE {Font-FAMILY: \"Tahoma\",\"Verdana\",\"宋体\"; FONT-SIZE: 9pt}";
	strStyle += ".mtdfocus {BACKGROUND-COLOR: #ccccff; BORDER-BOTTOM: #000000 1px solid; BORDER-TOP: #000000 1px solid; CURSOR: hand}";
	strStyle += ".mtdexit {BACKGROUND-COLOR: #ffffff; BORDER-BOTTOM: #ffffff 1px solid; BORDER-TOP: #ffffff 1px solid}";
	strStyle += ".ltdfocus {BACKGROUND-COLOR: #ccccff; BORDER-BOTTOM: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-LEFT: #000000 1px solid; CURSOR: hand}";
	strStyle += ".ltdexit {BACKGROUND-COLOR: #d0d0ce; BORDER-BOTTOM: #d0d0ce 1px solid; BORDER-TOP: #d0d0ce 1px solid; BORDER-LEFT: #d0d0ce 1px solid}";
	strStyle += ".rtdfocus {BACKGROUND-COLOR: #ccccff; BORDER-BOTTOM: #000000 1px solid; BORDER-TOP: #000000 1px solid; BORDER-RIGHT: #000000 1px solid; CURSOR: hand}";
	strStyle += ".rtdexit {BACKGROUND-COLOR: #ffffff; BORDER-BOTTOM: #ffffff 1px solid; BORDER-TOP: #ffffff 1px solid; BORDER-RIGHT: #ffffff 1px solid}";
	strStyle += "</STYLE>";
	document.write(strStyle);
}