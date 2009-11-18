/*
 * ArekorePopup.js (v1.2.5) 2003-10-04
 * by ALIMIKA SATOMI < http://www.remus.dti.ne.jp/~a-satomi/bunsyorou/ArekorePopup.html >
 * 
 * 'ArekorePopup.css' and '*.png' images are required to run script.
 * It is effective in Gecko-browser, Safari, and WinIE(6). not in Opera or MacIE.
 * This script is under BSD license.
 */
 
function ArekorePopup() {

/* ------------------------------------------------- *
 *                script preferences                 *
 * ------------------------------------------------- */

	// Attributes to popup
	// Attribute values are displayed in this order.
	// use 'prefix:name' format for attrs that has XML Namespace prefix.
	// Names of HTML/XHTML must be in lowercase, no prefixes are needed.
	this.attrs = ['ap:banner', 'title', 'href', 'cite', 'datetime'];

		// 'ap:banner' attribute is for specifying URL of the banner to display on popup.
		// To use 'ap:banner' attribute, you should write your HTML in XHTML syntax,
		// and declare XML Namespace for 'ap' prefix in <HTML> element.
		// Example: <html xmlns="http://www.w3.org/1999/xhtml" xmlns:ap="http://www.remus.dti.ne.jp/~a-satomi/ap">

	// Required attribute to popup. (pairs of 'element-name' & 'attribute-name')
	// Names of HTML/XHTML must be in lowercase, no prefixes are needed.
	// Example : { 'a' : 'title', 'blockquote' : 'cite', 'prefix:elemname' : 'prefix:attrname' ... }
	this.requiredAttr = { 'a' : 'title' };

		// The elements and its descendants that given 'AP-noPopup' classname,
		// ArekorePopup never appear even if it's in the case of appearing.

	// XML Namespaces (pairs of 'prefix' & 'Its NamespaceURI')
	this.ns = {
		xhtml1 : 'http://www.w3.org/1999/xhtml',
		xhtml2 : 'http://www.w3.org/2002/06/xhtml2',
		ap     : 'http://www.remus.dti.ne.jp/~a-satomi/ap'
	};

	// Apply to Safari only? (boolean)
	this.safariOnly = false;

	// Do node initialization beforehand? (boolean)
	// 'true'  : It takes long time until displaying of a huge page is completed.
	// 'false' : recommended, but the reaction of 'a:hover' style becomes slow.
	this.initBeforehand = false;

	// Time limit of node initializing process (ms)
	this.initTimeout = 3000;

	// Popup delay (ms)
	this.popupDelay = 1000;

	// Popup sustain (ms)
	this.popupSustain = 5000;

	// Distance from mouse pointer (px)
	this.offset = { X: 0, Y: 20 };

	// Use rich (image-enhanced) style popup? (boolean)
	// 'true' may causes slow responces
	this.useRichStyle = true;

	// Use text-shadow in rich-style popup? (boolean)
	// 'true' may causes more slow responces
	this.richStyleUseTextShadow = true;

	// Use DirectX Alpha Image Loader in WinIE? (boolean)
	// 'true' required if images are PNG-24 and has alpha transparency channel.
	// Notice : When 'true', images are no longer repeat-fill, only scale to element box size in WinIE.
	//          Gaps of background occurs irregularly in WinIE. limitation of using DX Alpha Image Loader?
	this.richStyleUseAILoader = true;

	// Edge width of rich-style popup (px)
	// 'padding' property value of 'ins#AP-popup-parent.AP-richStyle' selector in 'ArekorePopup.css'
	// and pixel size of images must be related with this value.
	// ('0' given, popup will be filled with 'simple_background' image only.)
	this.richStyleEdgeWidth = 16;

	// Images for rich-style.
	// (filename, or relative url from 'ArekorePopup.css')
	this.richStyleImages = {
		body               : 'body.png',
		rect_top           : 'top.png',
		rect_left          : 'left.png',
		rect_right         : 'right.png',
		rect_bottom        : 'bottom.png',
		angle_top_left     : 'top-left.png',
		angle_top_right    : 'top-right.png',
		angle_bottom_left  : 'bottom-left.png',
		angle_bottom_right : 'bottom-right.png',
		simple_background  : 'body.png'
	};

	// Manual setup path/URL of 'ArekorePopup.css' directory (must be end with '/' charactor)
	// Example : 'http://example.com/ArekorePopup/', '/root/subdir/', '../sibling/', etc.
	//           To use relative path/URL, specify relative location from HTML files.
	// If empty, script finds path/URL from <link> elements of HTML automatically. (recommended)
	this.cssDir = ''; 





// -------------------------------- Do not edit below --------------------------------------

	this.isSafari = navigator.userAgent.match('AppleWebKit');
	this.label = 'ArekorePopup';
	this.popupBoxId = 'AP-popup-parent';
	this.noPopupClassName = 'AP-noPopup';
	this.richStyleClassName = 'AP-richStyle';
	this.evacuate = {'title' : 'ap:ec-title', 'alt' : 'ap:ec-alt'};
	this.uriAttrsPtn = new RegExp('^(background|cite|classid|code|codebase|data|for|href|longdesc|src|usemap)$', 'i');
	this.version = 'v1.2.5';
	return this;
}

ArekorePopup.prototype = {
	launch : function() {
		AP.oBODY = AP.getElements('body')[0];
		if (!AP.oBODY || AP.safariOnly && !AP.isSafari || !AP.findCSS()) return;
		if (AP.isSafari)
			setTimeout('AP.init()', 500); // AP.cssEnabledCheck() does not work well in Safari without this delay
		else
			AP.init();
	},

	init : function() {
		if (!AP.cssEnabledCheck()) return;
		AP.initTimer = (new Date()).getTime();
		AP.image.prepare(); 
		if (AP.initBeforehand)
			AP.scanNode.recursive(AP.oBODY);
		else
			AP.addEvent(AP.oBODY, 'mouseover', AP.append);
		AP.addEvent(AP.oBODY, 'mousemove', AP.getMousePos);
		AP.addEvent(AP.oBODY, 'click', AP.remove);
		AP.statusMsg.show(AP.label + ' (' + AP.version + ') launched.'
			+ ((!AP.initBeforehand) ?
				' wasted for init : ' + (((new Date()).getTime() - AP.initTimer) / 1000) + ' sec.' : '')
		, 100, 5000);
	},

	scanNode : {
		recursive : function(node) {
			var nodes = node.childNodes;
			for (var i = 0; i < nodes.length; i++) {
				if (nodes[i].nodeType != 1 /* Node.ELEMENT_NODE */) continue;
				if (AP.scanNode.eachNode(nodes[i])) AP.scanNode.recursive(nodes[i]);
			}
		},

		eachNode : function(node) {
			if (AP.initBeforehand) {
				if ((new Date()).getTime() - AP.initTimer > AP.initTimeout) return false;
				AP.evacuateAttr(node);
				AP.image.loadBanner(node);
			}

			var nodeName = (!node.namespaceURI) ? node.nodeName.toLowerCase() : node.nodeName;
//			AP.setAttr(node, 'ap:nodeName', nodeName); // for debug

			var flag = false;
			if (!AP.findAttr.ancestorOrSelf(node, 'class', AP.noPopupClassName)) {
				if (AP.requiredAttr[nodeName])
					flag = (AP.getAttr(node, AP.revealAttr(AP.requiredAttr[nodeName])));
				else 
					for (var j in AP.attrs) if (AP.getAttr(node, AP.revealAttr(AP.attrs[j]))) { flag = true; break; }
		
				if (flag && AP.initBeforehand) {
					AP.addEvent(node, 'mouseover', AP.append);
					AP.addEvent(node, 'mouseout' , AP.remove);
				}
			}
			return (AP.initBeforehand) ? true : flag;
		}
	},

	addEvent : function(obj, type, listener) {
		if (obj.addEventListener) // Std DOM Events
			obj.addEventListener(type, listener, false);
		else if (obj.attachEvent) // IE
			obj.attachEvent(
				'on' + type,
				function() { listener( {
					type            : window.event.type,
					target          : window.event.srcElement,
					currentTarget   : obj,
					clientX         : window.event.clientX,
					clientY         : window.event.clientY,
					pageY           : document.body.scrollTop + window.event.clientY,
					shiftKey        : window.event.shiftKey,
					stopPropagation : function() { window.event.cancelBubble = true }
				} ) }
			);
	},

	findCSS : function() {
		if (!AP.cssDir) {
			var oLINK = AP.getElements('link');
			var ptn   = new RegExp('/?' + AP.label + '.css$');
			for (var i = 0; i < oLINK.length; i++) {
				var rel  = AP.getAttr(oLINK[i], 'rel');
				var href = AP.getAttr(oLINK[i], 'href');
				if (rel && href && rel.match(/stylesheet/i) && href.match(ptn)) {
					AP.cssDir = href.replace(/[^\/]+$/, '');
					AP.cssDir = (!AP.cssDir) ? './' : AP.cssDir;
					AP.APcss  = oLINK[i];
					break;
				}
			}
			if (!AP.cssDir) {
				AP.statusMsg.show(
					'Can\'t locate CSS : Not found <link> for "' + AP.label + '.css"'
					, 500, 15000
				);
				return false;
			}
		} else if (!AP.APcss) {
			AP.APcss = AP.createElement('link');
			AP.setAttr(AP.APcss, 'rel' , 'stylesheet');
			AP.setAttr(AP.APcss, 'type', 'text/css');
			AP.setAttr(AP.APcss, 'href', AP.cssDir + AP.label + '.css');
			AP.getElements('head')[0].appendChild(AP.APcss);
		}
		return true;
	},

	cssEnabledCheck : function() {
		if (!document.styleSheets) return false;
		if (AP.APcss) AP.APcss.disabled = false;
		if (!AP.curPopup) {
			AP.curPopup = AP.createElement('ins');
			AP.setAttr(AP.curPopup, 'id', AP.popupBoxId);
			AP.curPopup.style.position = 'absolute';
			AP.curPopup.style.top = '-10000px';
			AP.oBODY.appendChild(AP.curPopup);
			AP.addEvent(AP.curPopup, 'mouseover', AP.sticky );
			AP.addEvent(AP.curPopup, 'click'    , AP.command);
			AP.addEvent(AP.curPopup, 'mouseout' , AP.remove );
		}
		return (AP.curPopup.offsetTop == -10000);
	},

	image : {
		prepare : function() {
			AP.images = [];
			AP.useAPBanner = false;
			for (var i in AP.attrs)
				if (AP.attrs[i] == 'ap:banner')
					{ AP.useAPBanner = true; break; }
			if (AP.useRichStyle)
				for (var part in AP.richStyleImages)
					AP.image.preload(AP.cssDir + AP.richStyleImages[part]);
		},
	
		preload : function(url) {
			if (!url || !url.match(/(gif|png|jpe?g)$/i)) return;
			AP.images[url] = new Image();
			AP.images[url].src = url;
		},
	
		enabledCheck : function() {
			var part = (AP.richStyleEdgeWidth) ? 'body' : 'simple_background';
			return AP.images[AP.cssDir + AP.richStyleImages[part]].complete;
		},

		loadBanner : function(node, recursive) {
			if (!AP.useAPBanner || !node || AP.getAttr(node, 'ap:preloaded')) return;
			var url = (recursive) ? AP.findAttr.ancestorOrSelf(node, 'ap:banner') : AP.getAttr(node, 'ap:banner');
			AP.image.preload(url);
			AP.setAttr(node, 'ap:preloaded', 'true');
		},

		createImgNode : function(url) {
			var img = AP.images[url];
			if (!img || !img.complete) return null;
			if (AP.isSafari) { // Prevent DOM Exception
				var img = AP.createElement('img');
				AP.setAttr(img, 'src', url);
			}
			return img;
		}
	},

	append : function(e) {
		if (typeof AP != 'object' || AP.curItem || !AP.attrs.length) return;
		if (AP.initBeforehand) {
			e.stopPropagation();
			AP.curItem    = e.currentTarget;
			AP.popupTimer = setTimeout('AP.popup()' , AP.popupDelay);
		} else {
			AP.curItem = (e.target.nodeType == 3 /* Node.TEXT_NODE */) ? e.target.parentNode : e.target;
			AP.evacuateAttr(AP.curItem ,true);
			AP.image.loadBanner(AP.curItem, true);
			AP.addEvent(AP.curItem, 'mouseout' , AP.remove);
			AP.popupTimer = setTimeout('AP.checkNode()' , AP.popupDelay);
		}
	},

	checkNode : function() {
		if (typeof AP != 'object' || !AP.curItem) return;
		if (AP.scanNode.eachNode(AP.curItem))
			AP.popup();
		else if (AP.curItem != AP.oBODY) {
			AP.curItem = AP.curItem.parentNode;
			AP.checkNode();
		}
	},

	popup : function() {
		if (!AP.cssEnabledCheck() || !AP.curItem) return;

		var cUL      = AP.createElement('ul');
		var target   = AP.getAttr(AP.curItem, 'target');
		var maxWidth = parseInt(AP.windowW * 0.9) - AP.richStyleEdgeWidth * 2;

		AP.curPopup.appendChild(cUL);

		for (var i in AP.attrs) {
			var attr  = AP.revealAttr(AP.attrs[i]);
			var value = AP.getAttr(AP.curItem, attr);
			if (!value) continue;
			var cLI  = AP.createElement('li');
			var chld = (attr.match(AP.uriAttrsPtn)) ? AP.createAnchor(value, target) :
			                                          (attr == 'ap:banner') ? AP.image.createImgNode(value) :
				                                                              document.createTextNode(value);
			if (chld) {
				cLI.appendChild(chld);
				cUL.appendChild(cLI);
				AP.setAttr(cLI, 'class', AP.attrs[i].replace(/:/, '-'));
				if (cLI.offsetWidth > maxWidth) cLI.style.width = maxWidth + 'px';
			}
		}

		if (AP.curPopup.offsetWidth > maxWidth) cUL.style.width = maxWidth + 'px';

		AP.applyRichStyle();
		AP.revisePosition();
		AP.sustainTimer = setTimeout('AP.remove()', AP.popupSustain);
//		AP.statusMsg.show('Click on the popup to disable "' + AP.label + '".');
	},

	applyRichStyle : function() {
		if (!AP.useRichStyle || !AP.image.enabledCheck()) {
			AP.setAttr(AP.curPopup, 'class', '');
			return;
		}

		AP.setAttr(AP.curPopup, 'class', AP.richStyleClassName);
		var oUL  = AP.curPopup.firstChild;
		var edge = AP.richStyleEdgeWidth;

		// make text-shadow
		if (AP.richStyleUseTextShadow) {
			var offset = 1, cLI = [];
			for (var i = 0; i < oUL.childNodes.length; i++) {
				var oLI = oUL.childNodes[i];
				if (oLI.runtimeStyle) { // IE (use DX Drop Shadow Filter)
					oLI.runtimeStyle.filter = 'progid:DXImageTransform.Microsoft.DropShadow'
					                        + '(offX=' + offset + ', offY=' + offset + ', color="black")';
					oLI.style.width  = oLI.offsetWidth  + 'px';
					oLI.style.height = oLI.offsetHeight + 'px';
				} else {
					cLI[i] = oLI.cloneNode(true);
					AP.setAttr(cLI[i], 'class', AP.getAttr(cLI[i], 'class') + ' AP-shadow');
					var offset_ = (AP.getAttr(oLI, 'class') == 'ap-banner') ? 0 : offset;
					cLI[i].style.top   = (oLI.offsetTop   + offset_) + 'px';
					cLI[i].style.left  = (oLI.offsetLeft  + offset_) + 'px';
					cLI[i].style.width = (oLI.offsetWidth          ) + 'px';
				}
			}
			for (var i = 0; i < cLI.length; i++) oUL.appendChild(cLI[i]);
		}

		// apply background images
		for (var part in AP.richStyleImages) {
			var kind = part.split('_')[0];

			if (kind == 'body' && edge) {
				var obj  = oUL;
				var geom = { };
			} else if (kind == 'simple' && !edge) {
				var obj  = AP.curPopup;
				var geom = { };
			} else if (kind == 'angle' && edge) {
				var obj  = AP.createElement('div');
				var geom = {
					top    : (part.split('_')[1] == 'top'   ) ? 0 : 'auto',
					bottom : (part.split('_')[1] == 'bottom') ? 0 : 'auto',
					left   : (part.split('_')[2] == 'left'  ) ? 0 : 'auto',
					right  : (part.split('_')[2] == 'right' ) ? 0 : 'auto',
					width  : edge,
					height : edge
				};
				AP.setAttr(obj, 'id', 'AP-' + part.replace(/_/g, '-'));
			} else if (kind == 'rect' && edge) {
				var obj  = AP.createElement('div');
				var rect = part.split('_')[1];
				var geom = {
					top    : (rect.match(/left|right/)) ? edge : (rect == 'top'   ) ? 0 : 'auto',
					bottom : (rect.match(/left|right/)) ? edge : (rect == 'bottom') ? 0 : 'auto',
					left   : (rect.match(/top|bottom/)) ? edge : (rect == 'left'  ) ? 0 : 'auto',
					right  : (rect.match(/top|bottom/)) ? edge : (rect == 'right' ) ? 0 : 'auto',
					width  : (rect.match(/left|right/)) ? edge : (document.all) ? oUL.offsetWidth  : 'auto',
					height : (rect.match(/top|bottom/)) ? edge : (document.all) ? oUL.offsetHeight : 'auto'
				};
				AP.setAttr(obj, 'id', 'AP-' + part.replace(/_/g, '-'));
			} else
				continue;

			for (var prop in geom)
				obj.style[prop] = geom[prop] + (!isNaN(geom[prop]) ? 'px' : '');

			if (obj.runtimeStyle && AP.richStyleUseAILoader) // IE (use DX Alpha Image Loader)
				obj.runtimeStyle.filter = 'progid:DXImageTransform.Microsoft.AlphaImageLoader(src="'
				                        + AP.cssDir + AP.richStyleImages[part] + '", sizingMethod="scale")';
			else
				obj.style.backgroundImage = 'url(' + AP.cssDir + AP.richStyleImages[part] + ')';

			if (obj != AP.curPopup && obj != oUL)
				AP.curPopup.appendChild(obj);
		}
		// revise for IE using DX Alpha Image Loader (incomplete but marginal)
		if (AP.curPopup.runtimeStyle && AP.richStyleUseAILoader && edge) {
			var nodes = AP.curPopup.childNodes;
			nodes[0].style.width  = (nodes[0].offsetWidth         + 1) + 'px';
			nodes[0].style.height = (nodes[0].offsetHeight        + 1) + 'px';
			nodes[1].style.width  = (nodes[0].offsetWidth            ) + 'px';
			nodes[2].style.height = (nodes[0].offsetHeight           ) + 'px';
			nodes[3].style.height = (nodes[0].offsetHeight           ) + 'px';
			nodes[3].style.left   = (nodes[0].offsetWidth  + edge - 1) + 'px';
			nodes[4].style.width  = (nodes[0].offsetWidth            ) + 'px';
			nodes[4].style.top    = (nodes[0].offsetHeight + edge - 1) + 'px';
			nodes[6].style.left   = (nodes[0].offsetWidth  + edge - 1) + 'px';
			nodes[7].style.top    = (nodes[0].offsetHeight + edge - 1) + 'px';
			nodes[8].style.top    = (nodes[0].offsetHeight + edge - 1) + 'px';
			nodes[8].style.left   = (nodes[0].offsetWidth  + edge - 1) + 'px';
		}
	},

	revisePosition : function() {
		var reviseX = AP.windowW - (AP.windowX + AP.offset.X + AP.curPopup.offsetWidth  + 20);
		var reviseY = AP.windowH - (AP.windowY + AP.offset.Y + AP.curPopup.offsetHeight + 20);
		AP.curPopup.style.left = (AP.mouseX + AP.offset.X + ((reviseX < 0) ? reviseX : 0)) + 'px';
		AP.curPopup.style.top  = (AP.mouseY + AP.offset.Y + ((reviseY < 0) ? reviseY : 0)) + 'px';
	},

	sticky : function(e) {
		clearTimeout(AP.removeTimer);
	},
	
	command : function(e) {
		e.stopPropagation();

		// WinIE using DX Alpha Image Loader will make link-anchor-click disable.
		if (e.target.nodeName.toLowerCase() == 'ul' && e.target.runtimeStyle && AP.richStyleUseAILoader) {
			for (var i = 0; i < e.target.childNodes.length; i++) {
				var oLI = e.target.childNodes[i];
				if (oLI.firstChild.nodeType != 1 && oLI.firstChild.nodeName.toLowerCase() != 'a') continue;
				var oLItop = e.target.parentNode.offsetTop + AP.richStyleEdgeWidth + oLI.offsetTop;
				var oLIbtm = oLItop + oLI.offsetHeight;
				var href   = AP.getAttr(oLI.firstChild, 'href');
				if (e.pageY > oLItop && e.pageY < oLIbtm && href) {
					var target = (e.shiftKey) ? '_blank' : AP.getAttr(oLI.firstChild, 'target');
					if (!target) target = AP.getAttr(AP.getElements('base')[0], 'target');
					if (!target) target = '_self';
					window.open(href, target);
					return;
				}
			}
		}
		var name = (e.target.nodeType == 1 /* Node.ELEMENT_NODE */) ? e.target.nodeName : e.target.parentNode.nodeName;
		if (name.toLowerCase() != 'a' && confirm('Disable "' + AP.label + '" ?', '')) {
			AP.attrs = [];
			AP.remove_();
		}
	},

	remove : function(e) {
		if (typeof AP != 'object' || !AP.curPopup || !AP.curItem) return;
		AP.curItem = null;
		clearTimeout(AP.popupTimer);
		clearTimeout(AP.sustainTimer);
		clearTimeout(AP.removeTimer);
		var t = Math.ceil(Math.log(Math.sqrt(AP.offset.X * AP.offset.X + AP.offset.Y * AP.offset.Y) + 1.5) * 18);
		AP.removeTimer = setTimeout('AP.remove_()', t);
	},

	remove_ : function() {
		AP.curPopup.style.top   = '-10000px';
		while (AP.curPopup.firstChild) AP.curPopup.removeChild(AP.curPopup.firstChild);
//		AP.statusMsg.show('');
	},

	getElements : function(name) {
		if (name.match(/:/)) {
			var prfx = attr.split(':')[0];
			var name = attr.split(':')[1];
			if (document.getElementsByTagNameNS && AP.oBODY.namespaceURI)
				return document.getElementsByTagNameNS(AP.ns[prfx], name);
			else
				return document.getElementsByTagName(prfx + ':' + name);
		} else
			return document.getElementsByTagName(name);
	},

	createElement : function(name) {
		return (document.createElementNS) ? document.createElementNS(AP.ns.xhtml1, name) : document.createElement(name);
	},

	getAttr : function(node, attr) {
		if (!node || !attr) return null;
		if (document.all) { // IE
			if (attr == 'href' && node.nodeName.toLowerCase() == 'img') return null;
			if (attr == 'class') attr += 'Name';
		}
		try { var ret = node.getAttribute(attr) } catch(err) { } // IE causes js error when node is 'table' element.
		if (!ret && node.getAttributeNS && attr.match(/:/)) {
			var prfx = attr.split(':')[0];
			var attr = attr.split(':')[1];
			return node.getAttributeNS(AP.ns[prfx], attr)
		} else
			return ret;
	},

	setAttr : function(node, attr, value) {
		if (!node || !attr) return;
		if (attr.match(/:/)) {
			var prfx = attr.split(':')[0];
			var attr = attr.split(':')[1];
			if (node.setAttributeNS && node.namespaceURI || AP.isSafari)
				node.setAttributeNS(AP.ns[prfx], attr, value);
			else {
				node.setAttribute('xmlns:' + prfx, AP.ns[prfx]);
				node.setAttribute(prfx + ':' + attr, value);
			}
		} else {
			if (attr == 'class' && document.all) attr += 'Name'; // IE
			node.setAttribute(attr, value);
		}
	},

	evacuateAttr : function(node, recursive) {
		if (!node || AP.getAttr(node, 'ap:evacuated')) return;
		for (var i in AP.evacuate) {
			var value = AP.getAttr(node, i);
			if (value) {
				AP.setAttr(node, i, '');
				AP.setAttr(node, AP.evacuate[i], value);
				AP.setAttr(node, 'ap:evacuated', 'true');
			}
		}
		if (recursive) AP.evacuateAttr(node.parentNode, true);
	},

	revealAttr : function(attr) {
		return (!attr) ? null : (AP.evacuate[attr]) ? AP.evacuate[attr] : attr;
	},

	findAttr : {
		eachNode : function(node, attr, valueToFind) {
			if (!node || !attr) return null;
			var ret = value = AP.getAttr(node, attr);
			if (valueToFind) {
				ret = (value == valueToFind);
				if (attr == 'class' && value) {
					var values = value.split(' ');
					for (var j in values) if (values[j] == valueToFind) { ret = true; break; }
				}
			}
			return ret;
		},
		
		ancestorOrSelf : function(node, attr, valueToFind) {
			var ret = AP.findAttr.eachNode(node, attr, valueToFind);
			return (ret) ? ret :
				(node.parentNode) ? AP.findAttr.ancestorOrSelf(node.parentNode, attr, valueToFind) : null;
		}
	},

	createAnchor : function(url, target) {
		if (!url) return null;
		var cA = AP.createElement('a');
		AP.setAttr(cA, 'href', url);
		if (target) AP.setAttr(cA, 'target', target);
		cA.appendChild(document.createTextNode(cA.href));
		return cA;
	},

	getMousePos : function(e) {
		if (typeof AP != 'object') return;
		var d = document.documentElement;
		var scrollX = (window.scrollX) ? window.scrollX : (d.scrollLeft) ? d.scrollLeft : AP.oBODY.scrollLeft;
		var scrollY = (window.scrollY) ? window.scrollY : (d.scrollTop)  ? d.scrollTop  : AP.oBODY.scrollTop;
		AP.windowW  = (window.innerWidth)  ? window.innerWidth  : d.offsetWidth;
		AP.windowH  = (window.innerHeight) ? window.innerHeight : d.offsetHeight;
		AP.windowX  = e.clientX - (( AP.isSafari) ? scrollX : 0);
		AP.windowY  = e.clientY - (( AP.isSafari) ? scrollY : 0);
		AP.mouseX   = e.clientX + ((!AP.isSafari) ? scrollX : 0);
		AP.mouseY   = e.clientY + ((!AP.isSafari) ? scrollY : 0);

//		window.status = 'windowX:'    + AP.windowX + ' / windowY:' + AP.windowY
//		              + ' / scrollX:' + scrollX    + ' / scrollY:' + scrollY
//		              + ' / top:'     + AP.mouseX  + ' / left:'    + AP.mouseY;
	},

	statusMsg : {
		set   : function() { try { window.status = AP.statusMsg.msg }      catch(err) { } },
		clear : function() { try { window.status = AP.statusMsg.msg = '' } catch(err) { } },
		show  : function(msg, delay, sustain) {
			if (AP.msgTimer) clearTimeout(AP.msgTimer);
			AP.statusMsg.msg = msg;
			if (!delay) 
				AP.statusMsg.set();
			else
				AP.msgTimer = setTimeout('AP.statusMsg.set()', delay);
			if (AP.statusMsg.msg && sustain)
				AP.msgTimer = setTimeout('AP.statusMsg.clear()', delay + sustain);
		}
	}
};

var AP = new ArekorePopup();
AP.addEvent(window, 'load', AP.launch);
