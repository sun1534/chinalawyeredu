/*
 * Copyright (c) 2006, www.never-online.net! All rights reserved.
 * web    : http://www.never-online
 * author : never-online, BlueDestiny
 * version: 0.12 beta
 * this is a autocomplete extras version, complete version in neverModules framework.
 * debug in IE6.0, Opera9.0, Mozilla Firefox1.5.0
 */ 

/*
neverModules.runtime.useCache = false;
_registerNS(neverModules.modules.autocomplete);
_registerNS(neverModules.configuration.autocomplete)
_extends(neverModules.modules.autocomplete, 
         neverModules.base.baseclass);
 */
  
/*
 * Copyright (c) 2006, www.never-online.net! All rights reserved.
 * Batch default autocomplete configuration
 * autocomplete 0.12
 */

 var neverModules = window.neverModules || {};

var _registerNS = _registerNS || function (ns) {
  var levels = ns.split("."); var _NS = neverModules;
  for (var i=(levels[0]=="neverModules")?1:0; i<levels.length; i++) {
    _NS[levels[i]] = _NS[levels[i]] || {};
    _NS = _NS[levels[i]];
  }; return _NS;
};

var _createjsClass = _createjsClass || function () {
  return function () { 
    this._defaultInitializer.apply(this,arguments); 
  };
};  

_registerNS("neverModules.modules");
_registerNS("neverModules.browser");
_registerNS("neverModules.configuration");

neverModules.browser =  {
  isMozilla : (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument!='undefined'),
  isIE      : window.ActiveXObject?true:false,
  isOpera   : (navigator.userAgent.toLowerCase().indexOf("opera")!=-1)
};

if (!neverModules.browser.isIE) {
  HTMLElement.prototype.click = function() {
    var evt = this.ownerDocument.createEvent('MouseEvents');
    evt.initMouseEvent('click', true, true, this.ownerDocument.defaultView, 1, 0, 0, 0, 0, false, false, false, false, 0, null);
    this.dispatchEvent(evt);
  }
};

String.prototype.rgEncode = function () {
  var val = this; if (val=="undefined") { return ""; }
  val = val.replace(/\\/g,"\\\\");
  val = val.replace(/\//g,"\\\/");
  val = val.replace(/\^/g,"\\\^");
  val = val.replace(/\*/g,"\\\*");
  val = val.replace(/\?/g,"\\\?");
  val = val.replace(/\+/g,"\\\+");
  val = val.replace(/\./g,"\\\.");
  val = val.replace(/\|/g,"\\\|");
  val = val.replace(/\[/g,"\\\[");
  val = val.replace(/\]/g,"\\\]");
  val = val.replace(/\(/g,"\\\(");
  val = val.replace(/\)/g,"\\\)");
  val = val.replace(/\{/g,"\\\{");
  val = val.replace(/\}/g,"\\\}");
  return val;
}

function getAbsoluteCoords (e) {
  var t = e.offsetTop; var l = e.offsetLeft; var w = e.offsetWidth; var h = e.offsetHeight;
  while  (e=e.offsetParent) { t += e.offsetTop; l += e.offsetLeft; }; 
  return { top: t, left: l, width: w, height: h, bottom: t+h, right: l+w }
}

if (typeof addEvent != 'function')
{
 var addEvent = function(o, t, f, l)
 {
  var d = 'addEventListener', n = 'on' + t, rO = o, rT = t, rF = f, rL = l;
  if (o[d] && !l) return o[d](t, f, false);
  if (!o._evts) o._evts = {};
  if (!o._evts[t])
  {
   o._evts[t] = o[n] ? { b: o[n] } : {};
   o[n] = new Function('e',
    'var r = true, o = this, a = o._evts["' + t + '"], i; for (i in a) {' +
     'o._f = a[i]; r = o._f(e||window.event) != false && r; o._f = null;' +
     '} return r');
   if (t != 'unload') addEvent(window, 'unload', function() {
    removeEvent(rO, rT, rF, rL);
   });
  }
  if (!f._i) f._i = addEvent._i++;
  o._evts[t][f._i] = f;
 };
 addEvent._i = 1;
 var removeEvent = function(o, t, f, l)
 {
  var d = 'removeEventListener';
  if (o[d] && !l) return o[d](t, f, false);
  if (o._evts && o._evts[t] && f._i) delete o._evts[t][f._i];
 };
};

neverModules.configuration.autocomplete = {

  defaultAnimateImage : {src:"images/animated_loading.gif"},
  defaultSliceRange   : {low: 0, high: 200},
  defaultIsUseContent : false,
  defaultAutoSlice    : true,
  defaultIgnoreSpeed  : false,
  defaultIgnoreCase   : true,
  defaultIgnoreWhere  : true,
  defaultUseSpaceMatch: true,
  defaultIsUseArrow   : true,
  defaultCallbackHdle : function(){},
  defaultDataSource   : [],
  defaultHeight       : 200,
  defaultMaxSlice     : 200,
  defaultSpaceMatchRg : "\.\{1,}",

  _defaultInvalidCode : [13,38,40,27,9,17,16],
  _defaultSelectedIdx : -1,
  _defaultStatus      : 0,
  _defaultInitObject  : null,
  _defaultStyleCss    : "neverModules-auto",
  _defaultHighlightTag: {start: "<strong>", end: "</strong>"}
  
}

neverModules.modules.autocomplete = _createjsClass();

neverModules.modules.autocomplete.prototype = {

  _defaultInitializer: function (configuration) {

    this.name          = configuration.instanceName; 
    this.textbox       = configuration.textbox;  
    this.returnBox     = configuration.returnBox||this.textbox; 
    this.maxSlice      = neverModules.configuration.autocomplete.defaultMaxSlice; 
    this.sliceRange    = neverModules.configuration.autocomplete.defaultSliceRange; 
    this.ignoreSpeed   = !!neverModules.configuration.autocomplete.defaultIgnoreSpeed;
    this.ignoreCase    = !!neverModules.configuration.autocomplete.defaultIgnoreCase;
    this.ignoreWhere   = !!neverModules.configuration.autocomplete.defaultIgnoreWhere;
    this.autoSlice     = !!neverModules.configuration.autocomplete.defaultAutoSlice;
    this.useContent    = !!neverModules.configuration.autocomplete.defaultIsUseContent;
    this.useArrow      = !!neverModules.configuration.autocomplete.defaultIsUseArrow;
    this.useSpaceMatch = !!neverModules.configuration.autocomplete.defaultUseSpaceMatch;
    this.spaceMatchRg  = neverModules.configuration.autocomplete.defaultSpaceMatchRg;
    this.animateImage  = neverModules.configuration.autocomplete.defaultAnimateImage;
    this.callback      = neverModules.configuration.autocomplete.defaultCallbackHdle;
    this.scrollHeight  = isNaN(parseInt(configuration.height))?
                         neverModules.configuration.autocomplete.defaultHeight:
                         parseInt(configuration.height);

    /* Initialize private variables */
    this._currentVer        = 0.12;
    this._formatData        = [];
    this._inTheCacheData    = false;
    this._cacheValue        = "$NEVERMODULES_NULL_STRING$";
    this._selectedIdx       = neverModules.configuration.autocomplete._defaultSelectedIdx;
    this._currentStatus     = neverModules.configuration.autocomplete._defaultStatus;
    this._cacheData         = neverModules.configuration.autocomplete._defaultInitObject;  
    this._completeContainer = neverModules.configuration.autocomplete._defaultInitObject;
    this._completeIframe    = neverModules.configuration.autocomplete._defaultInitObject;
    this._completeStyle     = configuration.style||neverModules.configuration.autocomplete._defaultStyleCss;
    this._dataSource        = configuration.dataSource||neverModules.configuration.autocomplete._defaultDataSource;
    this._highlightTag      = neverModules.configuration.autocomplete._defaultHighlightTag;
    this._invalidKeyCode    = neverModules.configuration.autocomplete._defaultInvalidCode;
    this._inputValue        = "$NEVERMODULES_NULL_STRING$";
  },

  _createAutocomplete: function () { with(this) {
      if (!document.body) throw new Error
      (["neverModules Error","Document <BODY> not loaded，Can not create autoComplete"]);

      textbox.setAttribute("autocomplete","off");
      _completeContainer = document.createElement("DIV");
      document.body.appendChild(_completeContainer);

      _completeContainer.className = this._completeStyle;
      with (_completeContainer.style) {
        height = scrollHeight+"px"; overflow = "auto";
        zIndex = "2"; position = "absolute"; display = "none";
      };
      
      var self = this;
      _completeContainer.onscroll = function () {
        self.textboxFocus();
      };

      if (neverModules.browser.isIE) {
        _completeIframe = document.createElement("iframe");
        document.body.appendChild(_completeIframe);
        with (_completeIframe.style) {
          position = "absolute"; display  = "none";
          zIndex   = "1";        overflow = "hidden";
          frameBorder  = "0"; scrolling    = "no";
          marginHeight = "0"; marginWidth  = "0";
        };
        _completeContainer.onselectstart = function () {
          window.returnValue=false;
        };
      };

      addEvent(document,'mousedown', function (evt) {
        e = window.event?
        window.event.srcElement:
        evt.target;
        try {
          if (!self._queryTheContainer(e)) {
            self.close();
          } else { self.textboxFocus(); }
        } catch(ex) {};
      });
      // add a event listener to close autocomplete, 
      // when the focus is not in the autocomplete(input control).
      addEvent(document,'keyup', function (evt) {
        e = window.event?
        window.event.srcElement:
        evt.target;
        try {
          if (!self._queryTheContainer(e)) {
            self.close();
          } else { self.textboxFocus(); }
        } catch(ex) {};
      });

      if (this._currentStatus) this.show();
  }},

  _encode: function (str) {
    str = str+"";
    str = str.replace(/\&nbsp;/g, " ");
    str = str.replace(/\&nbsp/g, " ");
    //str = str.replace(/</g,"&lt;");
    //str = str.replace(/>/g,"&gt;");
    return str;
  },
    
  _decode: function (str) {
    str = str+"";
    str = str.replace(/ /g, "&nbsp;");
    //str = str.replace(/&lt;/g,"<");
    //str = str.replace(/&gt;/g,">");
    return str;
  },

  _queryTheContainer: function (e) {
    while(e && e.tagName!="BODY") {
      if (!e) return false;
      if (e==this.textbox || e==this._completeContainer) {
        return true;
      }; e=e.parentNode;
    }; return false;
  },

  _fireCallbackHdle: function () {
    if (typeof (this.callback)=="function") {
      this.callback.apply(this, arguments);
    }
  },

  _hdleFactory: function (hdle, aArgs) {

    aArgs = aArgs || [];
    if ("formatDatasource"==hdle) {
      return this.useContent==true?
      this._formatMutiDataSource.apply(this, aArgs):
      this._formatSingleDataSource.apply(this, aArgs);

    } else if ("query"==hdle) {
      return this.useContent==true?
      this._queryMutiDataSource.apply(this, aArgs):
      this._querySingleDataSource.apply(this, aArgs);

    } else if ("toggle"==hdle) {
      return this.useContent==true?
      this._hdleMutiToggle.apply(this, aArgs):
      this._hdleSingleToggle.apply(this, aArgs);

    } else if ("arrow"==hdle) {
      return this.useContent==true?
      this._hdleMutiArrow.apply(this, aArgs):
      this._hdleSingleArrow.apply(this, aArgs); 
    }

  },

  _hdleQuery: function (strQuery) {
    if (!strQuery) return false;
    return this._hdleFactory("query",arguments);
  },

  _hdleToggle: function (oElement, sClassName1, sClassName2, sClassName3) {
    this._hdleFactory("toggle",arguments);
  },

  _hdleClick: function (oElement) {
    var text = this.getSelectedValue(oElement).text;
    var content = this.getSelectedValue(oElement).content;
    this._inputValue = text;
    this.returnBox.value = text;
    this._fireCallbackHdle(text, content);
    this.close(); this.textboxFocus();
  },

  _hdleFormatDataSource: function () {
    return this._hdleFactory("formatDatasource", arguments);
  },

  /*
   * Copyright (c) 2006, www.never-online.net! All rights reserved.
   * the following that is the event handler
   * autocomplete 0.12
  */


  _hdleSingleArrow: function (nKeyCode) { with(this) {
    var r = _completeContainer.getElementsByTagName("div");

    switch (nKeyCode) {
      case 13: // Enter
        r[this._selectedIdx]?r[this._selectedIdx].click():null;
        break;
      case 38: // Up arrow
        this._selectedIdx--;
        break;
      case 40: // Down arrow
        this._selectedIdx++;
        break;
      case 27: // ESC
        this.close();
        break;
      default:
        return;
    }
    
    this._selectedIdx<=-1?this._selectedIdx=0:this._selectedIdx>r.length-1?this._selectedIdx=r.length-1:"";
    if (!r[this._selectedIdx]) return;
    this._completeContainer.scrollTop=this._getRelativeScrollTop(r[this._selectedIdx]);

    this._hdleToggle(r[this._selectedIdx],'over');
    this._selectedIdx>0?this._hdleToggle(r[this._selectedIdx-1],'out'):"";
    this._hdleToggle(r[this._selectedIdx+1],'out');
    this._createRange(r[this._selectedIdx]);
  }},

  _hdleMutiArrow: function (nKeyCode) {
    var a = this._completeContainer.childNodes[0];
    var r = a.getElementsByTagName("TR");

    switch (nKeyCode) {
      case 13: // Enter
        r[this._selectedIdx]?r[this._selectedIdx].click():"";
        break;
      case 38: // Up arrow
        this._selectedIdx--;
        break;
      case 40: // Down arrow
        this._selectedIdx++;
        break;
      case 27: // ESC
        this.close();
        break;
      default:
        return;
    }
    this._selectedIdx<=-1?this._selectedIdx=0:this._selectedIdx>r.length-1?this._selectedIdx=r.length-1:"";
    if (!r[this._selectedIdx]) return;
    this._completeContainer.scrollTop=this._getRelativeScrollTop(r[this._selectedIdx]);

    this._hdleToggle(r[this._selectedIdx],'over','autot-over','autoc-over');
    this._selectedIdx>0?this._hdleToggle(r[this._selectedIdx-1],'out','autot','autoc'):"";
    r[this._selectedIdx+1]?this._hdleToggle(r[this._selectedIdx+1],'out','autot','autoc'):"";
    this._createRange(r[this._selectedIdx]);
  },
  
  _createRange: function (oElement) {
    if (!oElement) return; var rng;
    var text = this.getSelectedValue(oElement).text;
    this.returnBox.value = text;
    if (neverModules.browser.isIE) {
      try {
        rng = this.textbox.createTextRange();
        rng.moveStart("character", this._inputValue.length);
        rng.select(); rng=null;
      } catch (ex) {};
    } else {
      //TODO
    }
  },

  _tg: function (oElement) {
    this._hdleToggle.apply(this, arguments);
  },
  _hdleMutiToggle: function (oElement, sClassName1, sClassName2, sClassName3) {
    if (!oElement) return;
    var a = this._completeContainer;
    oElement.tagName=="TR"?oElement.className=sClassName1:"";
    var a = oElement.childNodes;
    for(var i=0; i<a.length; i++) { 
      if (a[i].nodeType==1) {
        if (a[i].className.toLowerCase().indexOf("autot")>-1) {
          a[i].className=sClassName2;
        } else if (a[i].className.toLowerCase().indexOf("autoc")>-1) {
          a[i].className=sClassName3;
        }
      }
    }
  },
  _hdleSingleToggle: function (oElement, sClassName) {
    if (!oElement) return; oElement.className = sClassName;
  },

  _hc: function (oElement) {
    this._hdleClick.apply(this, arguments);
  },
  
  _ckSpaceMatchQuery: function (strQuery) {
    strQuery=strQuery+""; if (!this.useSpaceMatch) return strQuery;
    strQuery = this.useSpaceMatch?strQuery.replace(/ /g,"\|"):strQuery;
    return strQuery;
  },

  /*
   * Copyright (c) 2006, www.never-online.net! All rights reserved.
   * the following that is the private method
   * autocomplete 0.12
  */

  _getRelativeScrollTop: function (e) {
    return e.offsetTop;
  },

  _querySingleDataSource: function (strQuery) {
    var rQuery = this._ckSpaceMatchQuery(strQuery);
    strQuery = this.useSpaceMatch?strQuery.replace(/ /g,this.spaceMatchRg):strQuery;  
    var rg = new RegExp().compile('<div[^<>]+?>' +(this.ignoreWhere?'[^<>]*?':'')+strQuery+'[^<>]*?<\/div>',
                  this.ignoreCase?"ig":"g");
    var sDataSource = (this._inTheCacheData?this._cacheData:this._hdleFormatDataSource())+"";
    
    var aMatch = sDataSource.match(rg); rg = null;
    if (!aMatch) { this.close(); return false; }
    if (this.ignoreSpeed==true) {
      rg = new RegExp().compile("(?:>)([^<>]*?)(" +rQuery+ ")", this.ignoreCase?"ig":"g");
      return this._cacheData=(this.autoSlice && aMatch.length>this.maxSlice?
      aMatch.slice(this.sliceRange.low, this.sliceRange.high).join(""):
      aMatch.join("")).replace(rg, ">$1" +this._highlightTag.start+ "$2" +this._highlightTag.end +"");
    } else {
       return this._cacheData=this.autoSlice && aMatch.length>this.maxSlice?
       aMatch.slice(this.sliceRange.low, this.sliceRange.high).join(""):
       aMatch.join("");
    }
  },

  _queryMutiDataSource: function (strQuery) {
    var rQuery = this._ckSpaceMatchQuery(strQuery);
    strQuery = this.useSpaceMatch?strQuery.replace(/ /g,this.spaceMatchRg):strQuery;    
    var a=(this._inTheCacheData?(this._cacheData||[]).length>0?this._cacheData:this._dataSource:this._dataSource)||[];
    var text, content, hints, rText, rg; this._formatData=[];
    rg = new RegExp().compile(
         this.ignoreWhere?"(.*?)(" +strQuery+ ")(.*?)":"^(" +strQuery+ ")(.*?)", 
         this.ignoreCase?"ig":"g");
    for (var i=0; i<a.length; i++) {
      text=a[i].text?a[i].text:"";
      content=a[i].content?a[i].content:"";
      hints=a[i].hints?a[i].hints:"";
      rText="";text=this._encode(text);
      if (text.match(rg)) {
        var rg = new RegExp(this.ignoreWhere?"(.*?)(" +rQuery+ ")(.*?)":"^(" +rQuery+ ")(.*?)", 
                 this.ignoreCase?"ig":"g");
        rText = text.replace(rg,this.ignoreWhere?
               "$1" +this._highlightTag.start+ "$2" +this._highlightTag.end+ "$3":
               "" +this._highlightTag.start+ "$1" +this._highlightTag.end+ "$2");
        this._formatData.push({text:text, content:content, hints:hints, rText: rText});
      }
    }
    /* reset cache data */
    this._cacheData = [];
    this._cacheData  = this._cacheData.concat(this._formatData);
    this._formatData = this.autoSlice && this._formatData.length>this.maxSlice?
                       this._formatData.slice(this.sliceRange.low, this.sliceRange.high):
                       this._formatData;
    return this._hdleFormatDataSource();
  },

  _formatSingleDataSource: function () {
    var strHTML=""; var a=this._dataSource||[];
    if (a.length==1) {
      strHTML = ''
      + '<div class="out" onclick="' +this.name+'._hc(this)" onmouseover="' +this.name+'._tg(this,\'over\')" onmouseout="' +this.name+'._tg(this,\'out\')">' +a[0]+ '</div>';
    } else {
      strHTML = ''
      + '<div class="out" onclick="' +this.name+'._hc(this)" onmouseover="'
      + this.name+'._tg(this,\'over\')" onmouseout="'
      + this.name+'._tg(this,\'out\')">\n'+a.join('</div>\n<div class="out" onclick="'
      + this.name+'._hc(this)" onmouseover="' +this.name+'._tg(this,\'over\')" onmouseout="'
      + this.name+'._tg(this,\'out\')">\n')+"\n</div>";
    }
    return strHTML;
  },

  _formatMutiDataSource: function () {
    var a = this._formatData||[]; var strBuilder = []; 
    var text,rText,content,hints,strHTML;

    for (var i=0; i<a.length; i++) {
      rText=a[i].rText?a[i].rText:a[i].text;
      text=this._encode(a[i].text?a[i].text:"");
      content=this._encode(a[i].content?a[i].content:"");
      hints=this._encode(a[i].hints?a[i].hints:"");
      rText=this._decode(rText);
      strBuilder.push(''
      + '\n<tr title="' +hints+ '" uid="' +i+ '" class="out" txt="' +text+ '" content="' +content+ '" onclick="' +this.name+'._hc(this)" onmouseover="' +this.name+'._tg(this,\'over\',\'autot-over\',\'autoc-over\')" onmouseout="' +this.name+'._tg(this,\'out\',\'autot\',\'autoc\')">\n<td class="autot">' +rText+ '</td>\n<td class="autoc">' +content+ '</td>\n</tr>\n');
    }

    strHTML = '<table border="0" id="' +this.name+ '_complete_table" cellspacing="0" cellpadding="0" width="100%">';
    strHTML += strBuilder.join("")
    strHTML += "</table>";
    return strHTML;
  },
  
  _showAnimateImage: function (src, width, height) {
    if (!!src) this.setAnimateImage.apply(this,arguments);
    var o = this.animateImage; 
    var p = new Image(); var ig; var self = this;
    p.src=o.src; p.width=o.width?o.width:p.width; 
    p.height=o.height?o.height:p.height;

    if (document.getElementById("autocompleteAnimateImageId") &&
        document.getElementById("autocompleteAnimatecontainerId")) {
      ig = document.getElementById("autocompleteAnimateImageId");
      c=document.getElementById("autocompleteAnimatecontainerId");
      ig.src = p.src; ig.width = p.width; ig.height = p.height;
      animateImageShow(); return;
    }

    var c = document.createElement("DIV"); c.id  = "autocompleteAnimatecontainerId";
    ig    = document.createElement("IMG"); ig.id = "autocompleteAnimateImageId";
    ig.src = p.src; ig.width = p.width; ig.height = p.height; c.style.display = "none";
    document.body.appendChild(c); c.appendChild(ig); animateImageShow();

    function animateImageShow() {
      var os = getAbsoluteCoords(self.textbox);
      with (c.style) {
        left = os.left+os.width-p.width-2+"px";
        top  = os.top+Math.round((os.height-p.height)/2)-(neverModules.browser.isIE?-1:2)+"px";
        zIndex = "10"; position = "absolute"; display = "block";
      }
    }    
  },

  _setCacheValue: function (strValue) {
    /*
     * using cache array to improve the query speed performace
     * must be set the ignoreSpeed with false value
     * 缓存存于cacheData中，使用cache来提高查询速度。大数据量时有效
     * 此功能还有一个好处,可以减少与服务器的交互（在AJAX时）
    */
    this._inTheCacheData = false;

    if (this._cacheValue=="" || 
        (!this._cacheData)) {
      this._inTheCacheData=false;
      this._cacheValue = strValue;
      return;
    };

    if (this._cacheValue==strValue.substring(0,this._cacheValue.length)) {
      this._inTheCacheData = true;
    }; this._cacheValue = strValue;

  },


  /*
   * Copyright (c) 2006, www.never-online.net! All rights reserved.
   * the following that is the public method
   * autocomplete 0.12
   * interface list
  */

  show: function () {
    this._currentStatus = 1; 
    var p = getAbsoluteCoords(this.textbox);
    var x = this._completeContainer.style; 

    x.left = p.left+"px"; x.top = p.top+p.height+"px";
    x.width = p.width+"px"; x.display = "block";

    if (neverModules.browser.isIE) {
      var w = this._completeIframe.style;
      w.display =  "block"; w.left = p.left+"px"; w.top = p.top+p.height+"px";
      w.width = p.width+"px"; w.height = this._completeContainer.offsetHeight+"px";
    }
    
    try {
      var a = document.getElementById(this.name+ "_complete_table");
      if (a.offsetHeight>=this._completeContainer.offsetHeight) {
        a.setAttribute("width",this.textbox.offsetWidth-18);
        a.setAttribute("height",this.scrollHeight);
      }
    } catch(e) {};

    this._completeContainer.scrollTop = '0px';
  },

  close: function (e) {
    try {
    if (neverModules.browser.isIE) this._completeIframe.style.display = "none";
    this._completeContainer.style.display = "none";
    this._currentStatus = 0;
    } catch (ex) {};
  },

  clearDataSource: function () {
    this._dataSource = [];
    this._cacheData = [];
  },

  setDataSource: function (aDataSource) {
    if (aDataSource.constructor!=Array) return;
    this.close(); this.clearDataSource();
    this._dataSource = aDataSource; // reference
    //this._dataSource = aDataSource.join("$NEVER_MODULES_SPLIT$").split("$NEVER_MODULES_SPLIT$");
  },

  getSelectedValue: function (oElement) {
    if (!oElement) return {text: "null", content: "null"};
    var text = this.useContent?
    oElement.getAttribute("txt"):
    oElement.innerHTML.replace(/<[^<>]*>/g,"");
    var content = this.useContent?
    oElement.getAttribute("content"):"";
    return {text: text, content: content};
  },

  expandAllItem: function () {
    this._formatData = this._dataSource;
    this._completeContainer.innerHTML = this._hdleFormatDataSource();
    this.show();
  },

  hdleEvent: function (evt) {
    var strQuery = this.textbox.value.rgEncode();  var strHTML;
    var nKeyCode = window.event?event.keyCode:evt.which;

    if (strQuery == "" && this._currentStatus==0) { this.close(); return; }

    if (this.isValidKey(evt)==false) {
      if ((!this._currentStatus) || (this.useArrow!=true && nKeyCode!=27)) return;
      this._hdleFactory("arrow",[nKeyCode]); return;
    }; 

    this._inputValue = strQuery;
    this._setCacheValue(strQuery); this._completeContainer.innerHTML = ""; 
    this._selectedIdx = -1; strHTML = this._hdleQuery(strQuery); 

    if (strHTML!=false) {
      this._completeContainer.innerHTML = strHTML;
      this.show();
    } else {
      this.close();
      this._inTheCacheData = false;
    }
  },

  isValidKey: function (evt) {
    var nKeyCode = window.event?event.keyCode:evt.which;
    for (var key in this._invalidKeyCode) {
      if (nKeyCode==this._invalidKeyCode[key])
        return false;
    }; return true;
  },

  showAnimateImage: function (src, width, height) {
    this._showAnimateImage.apply(this, arguments);
  },

  closeAnimateImage: function () {
    try { document.getElementById("autocompleteAnimatecontainerId").style.display="none"; } 
    catch (ex) {};
  },

  setAnimateImage: function (src, width, height) {
    if (!src) return;
    this.animateImage = {src:src, width:width, height:height};
  },

  isRequireAjax: function () {
    return (!this._inTheCacheData);
  },

  isinCache: function () {
    return this.isRequireAjax();
  },

  dispose: function () {
    try {
      this._completeContainer.parentNode.removeChild(this._completeContainer);
      if (neverModules.browser.isIE)
      this._completeIframe.parentNode.removeChild(this._completeIframe);
    } catch (ex) {};
  },

  create: function () {
    this._createAutocomplete();
  },

  textboxFocus: function () {
    //禁止focus
    return;
    if (this.textbox)
    this.textbox.focus();
  }
}