/*--------------------------------------------------|
| dTree 2.05 | www.destroydrop.com/javascript/tree/ |
|---------------------------------------------------|
| Copyright (c) 2002-2003 Geir Landr\uFFFD               |
|                                                   |
| This script can be used freely as long as all     |
| copyright messages are intact.                    |
|                                                   |
| Updated: 17.04.2003                               |
|--------------------------------------------------*/
/*updated by linheng for faster initialize @ 2006-08-02*/

// Node object
function Node(id, pid, name, url, title, target, icon, iconOpen, open) {
	this.id = id;
	this.nodeId=0;
	this.pid = pid;
	this.name = name;
	this.url = url;
	this.title = title;
	this.target = target;
	this.icon = icon;
	this.iconOpen = iconOpen;
	// inner attributes
	this._io = open || false;
	this._is = false;
	this._ls = false;
	this._hc = false;
	this._ai = 0;
	this._p;

	// add by linheng for faster initialize
	this._fcp = -1;
	this._lcp = -1;
	// add by linheng for dynamic load
	this._layer = -1;
	this._completed = false;
};

// Tree object
function dTree(objName) {
	this.showchkboxflag	= 0;			// if checkbox is show
	this.checksubflag	= 1;			// check sub node
	this.config = {
		target			: null,			// default target
		folderLinks		: true,			// folder has link?
		useSelection	: true,			// can select node?
		useCookies		: true,			// seems must be true
		stepDepth		: 0,
		useLines		: true,
		useIcons		: false,
		useStatusText	: false,
		closeSameLevel	: false
	}

	this.icon = {
		root		: '../style/images/dtree/base.gif',
		folder		: '../style/images/dtree/folder.gif',
		folderOpen	: '../style/images/dtree/folderopen.gif',
		node		: '../style/images/dtree/page.gif',
		empty		: '../style/images/dtree/empty.gif',
		line		: '../style/images/dtree/line.gif',
		join		: '../style/images/dtree/join.gif',
		joinBottom	: '../style/images/dtree/joinbottom.gif',
		plus		: '../style/images/dtree/plus.gif',
		plusBottom	: '../style/images/dtree/plusbottom.gif',
		minus		: '../style/images/dtree/minus.gif',
		minusBottom	: '../style/images/dtree/minusbottom.gif',
		nlPlus		: '../style/images/dtree/nolines_plus.gif',
		nlMinus		: '../style/images/dtree/nolines_minus.gif'
	};

	this.obj = objName;
	this.aNodes = [];
	this.aIndent = [];
	this.root = new Node(-1);
	this.selectedNode = null;
	this.selectedFound = false;
};

// Adds a new node to the node array
dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title, target, icon, iconOpen, open);
};

// Open/close all nodes
dTree.prototype.openAll = function() {
	this.oAll(true);
};

dTree.prototype.closeAll = function() {
	this.oAll(false);
};

// Outputs the tree to the page
dTree.prototype.toString = function() {
	if (this.config.stepDepth < 0 || (this.showchkboxflag==1 && this.checksubflag==1)) {
	    this.config.stepDepth = 0;
	}
	var str = '<div class="dtree">\n';
	if (document.getElementById) {
		if (this.config.useCookies) {
		    this.selectedNode = this.getSelected();
		}
		this.initialize();
		str += this.addNode(this.root, 0);
	} else str += 'Browser not supported.';
	str += '</div>';

	if (!this.selectedFound) this.selectedNode = null;
	if (this.config.useCookies) this.updateCookie();

	return str;
};

dTree.prototype._dynamicLoad = function(index) {
    var pNode = this.aNodes[index];
	if(!pNode._p._completed) {
	    this._dynamicLoad(pNode._p._ai);
	}
	if(pNode._completed) return;
    // build indent list
	while(pNode.pid != this.root.id) {
	    this.aIndent[pNode._layer - 1] = (pNode._ls) ? 0 : 1;
		pNode = pNode._p;
	}
	var str = this.addNode(this.aNodes[index], 0);
	eDiv = document.getElementById('d' + this.obj + index);
	eDiv.innerHTML = str;
	// clear aIndent
	this.aIndent.length = 0;
	if (this.config.useCookies) this.updateCookie();
}

// initialize all nodes before output
dTree.prototype.initialize = function() {
	// get all open node id
	var aOpen = null;
	if(this.config.useCookies) {
	    aOpen = this.getCookie('co' + this.obj).split('.');
	}

	var node, pNode;
    for(var n=0; n<this.aNodes.length; n++) {
	    pNode = null;
	    node = this.aNodes[n];
		node._ai = n;
		// is selected?
		if(this.config.useSelection && !this.selectedFound && node.id == this.selectedNode) {
			node._is = true;
			this.selectedNode = n;
			this.selectedFound = true;
		}
		// target
		if (!node.target && this.config.target) node.target = this.config.target;
		// find parent
		if(node.pid == this.root.id) {
		    pNode = this.root;
		} else {
		    // find in front
		    for(var m=n-1; m>=0; m--) {
			    if(this.aNodes[m].id == node.pid) {
				    pNode = this.aNodes[m];
					break;
				} else if(this.aNodes[m].pid == node.pid) {
				    pNode = this.aNodes[m]._p;
					break;
				}
			}
			if(!pNode) {
			    // find in behind
				for(var k=n+1; k<this.aNodes.length; k++) {
				    if(this.aNodes[k].id == node.pid) {
					    pNode = this.aNodes[k];
						break;
					}
				}
			}
		}
		// update parent status
		if(pNode) {
			if(pNode._hc) {
			    this.aNodes[pNode._lcp]._ls = false;
			} else {
			    pNode._hc = true;
				pNode._fcp = n;
				// folder link
			    if (!this.config.folderLinks) pNode.url = null;
	            // check if parent is open
		        if(pNode.id != this.root.id && this.config.useCookies && !pNode._io) {
		            for(var i=0; i<aOpen.length; i++) {
			            if(aOpen[i] == pNode.id) pNode._io = true;
			        }
		        }
			}
		    node._p = pNode;
			node._ls = true;
			pNode._lcp = n;
		}
	}
};

// Creates the tree structure
dTree.prototype.addNode = function(pNode, currentDepth) {
	var canAddChild = this.config.stepDepth == 0 || currentDepth < this.config.stepDepth;
	var str = '';
	var n = (pNode._fcp > 0) ? pNode._fcp : 0;
	for (; n<=pNode._lcp; n++) {
		// find pNode's children
		if (this.aNodes[n].pid == pNode.id) {
			var cn = this.aNodes[n];
			cn._layer = pNode._layer + 1;
			cn._io = cn._io && canAddChild;
			// get string
			str += this.node(cn, n);
			// add children
			if(cn._hc) {
				str += '<div class="clip" id="d' + this.obj + n + '" style="display:' + ((this.root.id == cn.pid || cn._io) ? 'block' : 'none') + ';">';
				if(canAddChild) {
					str += this.addNode(cn, currentDepth + 1);
				}
				str += '</div>';
			}
			this.aIndent.pop();
		}
	}
	pNode._completed = true;

	return str;
};

// Creates the node icon, url and text
dTree.prototype.node = function(node, nodeId) {
	var str = '<div class="dTreeNode">' + this.indent(node, nodeId);
	// icon
	if (this.config.useIcons) {
		if (this.root.id == node.pid) {
			node.icon = this.icon.root;
			node.iconOpen = this.icon.root;
		}
		if (!node.icon) node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);
		if (!node.iconOpen) node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
        var ss = (this.showchkboxflag == 1) ? 'style="display:none"' : '';
		str += '<img '+ ss +' id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />';
        if(node.pid!=-1 && this.showchkboxflag==1) {
			str += '<input type="checkbox" name="ckx' + nodeId + '"';
			str += ' onclick="javascript: ' + this.obj + '.ck(' + nodeId + ');" >';
			node.nodeId = nodeId;
		}
	}
	// url
	if (node.url) {
		str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';
		if (node.title) str += ' title="' + node.title + '"';
		if (node.target) str += ' target="' + node.target + '"';
		if (this.config.useStatusText) str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';
		if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))
			str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';
		str += '>';
	} else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id) {
		str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">';
	}

	str += node.name;

	if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)) str += '</a>';

	str += '</div>';
	return str;
};

// Adds the empty and line icons
dTree.prototype.indent = function(node, nodeId) {
	var str = '';
	if (this.root.id != node.pid) {
		for (var n=0; n<this.aIndent.length; n++)
			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />';
		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);
		if (node._hc) {
			str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img border="0" id="j' + this.obj + nodeId + '" src="';
			if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;
			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );
			str += '" alt="" /></a>';
		} else str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />';
	}

	return str;
};

//get node by id
dTree.prototype.getNodeById = function(id) {
    for(var n=0; n<this.aNodes.length; n++) {
	    var node = this.aNodes[n];
	    if(node.pid == id) {
		    return node._p;
		} else if(node.id == id) {
		    return node;
		}
	}
	return null;
}

// Returns the selected node
dTree.prototype.getSelected = function() {
	var sn = this.getCookie('cs' + this.obj);
	return (sn) ? sn : null;
};

// Highlights the selected node
dTree.prototype.s = function(id) {
	if (!this.config.useSelection) return;
	var cn = this.aNodes[id];
	if (cn._hc && !this.config.folderLinks) return;
	if (this.selectedNode != id) {
		if (this.selectedNode || this.selectedNode==0) {
			if(this.aNodes[this.selectedNode]._p._completed) {
				eOld = document.getElementById("s" + this.obj + this.selectedNode);
				if(eOld) {
					eOld.className = "node";
				}
			}
			this.aNodes[this.selectedNode]._is = false;
		}
		if(cn._p._completed && cn.url) {
			eNew = document.getElementById("s" + this.obj + id);
			eNew.className = "nodeSel";
		}
		cn._is = true;
		this.selectedNode = id;
		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);
	}
};

// Toggle Open or close
dTree.prototype.o = function(id) {
	var cn = this.aNodes[id];
	if(!cn._p._completed) return;
	this.nodeStatus(!cn._io, id, cn._ls);
	if (this.config.closeSameLevel) this.closeLevel(cn);
	if (this.config.useCookies) this.updateCookie();
};

//check all children
dTree.prototype.ck = function(id) {
	var node = this.aNodes[id];
	var checkflag = document.all('ckx' + id).checked;
    if(this.checksubflag == 1) {
		for (var n=node._fcp; n<node._lcp; n++) {
			if (this.aNodes[n].pid == node.id ) {
				var obj = document.all('ckx' + this.aNodes[n].nodeId);
				obj.checked = checkflag;
				this.ck(this.aNodes[n].nodeId);
			}
		}
	}
};

//return selected text
dTree.prototype.getSelectText = function(str) {
	var ss = "";
	for(var i=0; i<this.aNodes.length; i++) {
		var objnode = this.aNodes[i];
		if(objnode.nodeId != 0) {
			var objchx = document.all('ckx' + objnode.nodeId);
			if(objchx.checked) ss += objnode.name + str;
		}
	}
	if(ss!="") ss = ss.substr(0, ss.length-1);
	return ss;
};

//return selected id
dTree.prototype.getSelectId = function(str) {
	var ss = "";
	for(var i=0; i<this.aNodes.length; i++) {
		var objnode = this.aNodes[i];
		if(objnode.nodeId!=0) {
			var objchx = document.all('ckx' + objnode.nodeId);
			if(objchx.checked) ss += objnode.id + str;
		}
	}
	if(ss!="") ss = ss.substr(0, ss.length-1);
	return ss;
};

// Open or close all nodes
dTree.prototype.oAll = function(status) {
	for (var n=0; n<this.aNodes.length; n++) {
		var node = this.aNodes[n];
		if (node._hc && node.pid != this.root.id && node._completed) {
			this.nodeStatus(status, n, node._ls)
		}
	}

	if (this.config.useCookies) this.updateCookie();
};

// Opens the tree to a specific node
dTree.prototype.openTo = function(id, bSelect) {
    if(this.root.id == id) return;
    var cn = this.getNodeById(id);
	if(!cn) return;
	// if parent not complete, complete parent
	if(!cn._p._completed) {
	    this._dynamicLoad(cn._p._ai);
	}
	if(bSelect) {
	    this.s(cn._ai);
	}
	var pNode = cn._p;
	while(pNode && pNode._p && pNode.pid != this.root.id) {
		this.nodeStatus(true, pNode._ai, pNode._ls);
		if (this.config.closeSameLevel) this.closeLevel(pNode);
		pNode = pNode._p;
	}

	if (this.config.useCookies) this.updateCookie();
};

// Closes all nodes on the same level as certain node
dTree.prototype.closeLevel = function(node) {
	var pNode = node._p;
	for (var n=pNode._fcp; n<=pNode._lcp; n++) {
		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._io) {
			this.nodeStatus(false, n, this.aNodes[n]._ls);
			//this.closeAllChildren(this.aNodes[n]);
		}
	}
}

// Closes all children of a node
dTree.prototype.closeAllChildren = function(node) {
	for (var n=node._fcp; n<=node._lcp; n++) {
		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc && this.aNodes[n]._completed) {
			if (this.aNodes[n]._io) {
				this.nodeStatus(false, n, this.aNodes[n]._ls);
			}
			this.closeAllChildren(this.aNodes[n]);
		}
	}
}

// Change the status of a node(open or closed)
dTree.prototype.nodeStatus = function(status, id, bottom) {
    // if children is not shown before, show this time
    if(!this.aNodes[id]._completed) {
	    this._dynamicLoad(id);
	}
	eDiv	= document.getElementById('d' + this.obj + id);
	eJoin	= document.getElementById('j' + this.obj + id);
	if (this.config.useIcons) {
		eIcon	= document.getElementById('i' + this.obj + id);
		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;
	}

	eJoin.src = (this.config.useLines)?
	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):
	((status)?this.icon.nlMinus:this.icon.nlPlus);

	eDiv.style.display = (status) ? 'block': 'none';

	this.aNodes[id]._io = status;
};

// [Cookie] Clears a cookie
dTree.prototype.clearCookie = function() {
	var now = new Date();
	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
	this.setCookie('co'+this.obj, 'cookieValue', yesterday);
	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);
};

// [Cookie] Sets value in a cookie
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {
	document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};

// [Cookie] Gets a value from a cookie
dTree.prototype.getCookie = function(cookieName) {
	var cookieValue = '';
	var posName = document.cookie.indexOf(escape(cookieName) + '=');
	if (posName != -1) {
		var posValue = posName + (escape(cookieName) + '=').length;
		var endPos = document.cookie.indexOf(';', posValue);
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
		else cookieValue = unescape(document.cookie.substring(posValue));
	}

	return (cookieValue);
};

// [Cookie] Returns ids of open nodes as a string
dTree.prototype.updateCookie = function() {
	var str = '';
	for (var n=0; n<this.aNodes.length; n++) {
		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {
			if (str) str += '.';
			str += this.aNodes[n].id;
		}
	}

	this.setCookie('co' + this.obj, str);
};

// [Cookie] Checks if a node id is in a cookie
dTree.prototype.isOpen = function(id) {
	var aOpen = this.getCookie('co' + this.obj).split('.');
	for (var n=0; n<aOpen.length; n++)
		if (aOpen[n] == id) return true;

	return false;
};

// If Push and pop is not implemented by the browser
if (!Array.prototype.push) {
	Array.prototype.push = function array_push() {
		for(var i=0;i<arguments.length;i++)
			this[this.length]=arguments[i];
		return this.length;
	}
};

if (!Array.prototype.pop) {
	Array.prototype.pop = function array_pop() {
		lastElement = this[this.length-1];
		this.length = Math.max(this.length-1,0);
		return lastElement;
	}
};

