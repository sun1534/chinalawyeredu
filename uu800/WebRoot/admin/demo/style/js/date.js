/*********************************************************
 * 使用方法
 * 控件调用onclick="fPopCalendar(event,this,this)"
 * 如下，给控件设置为readonly
 * <input type="text" style="border:1px solid #cccccc;"
 * size="15" onclick="fPopCalendar(event,this,this)"
 * onfocus="this.select()" readonly="readonly" />
 *
 * 如果页面乱码，把下面包含汉字的定义项放到页面中即可
 *
 * 已使用的id串：
 * calendar_div，tbSelYear，tbSelMonth，cellText[1-6][0-6]
 *
********************************************************/
var gMonths=new Array("一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月");
var WeekDay=new Array("日","一","二","三","四","五","六");
var strToday="今天";
var strYear="年";
var strMonth="月";
var strDay="日";
var splitChar="-";
var startYear=2000;
var endYear=2050;
var dayTdHeight=12;
var dayTdTextSize=12;
var gcNotCurMonth="#E0E0E0";
var gcRestDay="#FF0000";
var gcWorkDay="#444444";
var gcWeekDay="#388DC6";
var gcMouseOver="#eeeeee";
var gcMouseOut="#fff";
var gcToday="#444444";
var thcolor="#E7F0F7";
var gcTodayMouseOver="#6699FF";
var gcTodayMouseOut="#CCCCFF";
var gdCtrl=new Object();
var gdCurDate=new Date();
var giYear=curYear=gdCurDate.getFullYear();
var giMonth=curMonth=gdCurDate.getMonth()+1;
var giDay=curDay=gdCurDate.getDate();
function $id(){return document.getElementById(arguments[0]);}
function $idV(){return $id(arguments[0]).value;}
function checkColor(color,model){var color_tmp=(color+"").replace(/\s/g,"").toUpperCase();var model_tmp1=model.toUpperCase();var model_tmp2="rgb("+parseInt(model.substring(1,2),16)+","+parseInt(model.substring(3,2),16)+","+parseInt(model.substring(5,2),16)+")";model_tmp2=model_tmp2.toUpperCase();if(color_tmp==model_tmp1 || color_tmp==model_tmp2){return true;}return false;}
function fPopCalendar(evt,popCtrl,dateCtrl){evt.cancelBubble=true;gdCtrl=dateCtrl;var evtDate="";if(document.all){evtDate=evt.srcElement.value;}else{evtDate=evt.currentTarget.value;}var reg=/(^\d{4})\-([0-1]\d)\-([0-2]\d$)/;if(reg.test(evtDate)){reg.exec();giYear=RegExp.$1;giMonth=RegExp.$2;giDay=RegExp.$3;}else{giYear=curYear;giMonth=curMonth;giDay=curDay;}fSetYearMon(giYear,giMonth);var point=fGetXY(popCtrl);with($id("calendar_div")){style.left=point.x+"px";style.top=(point.y+popCtrl.offsetHeight+1)+"px";style.display='block';style.zindex='99';style.position='absolute';nextSibling.style.width=offsetWidth;nextSibling.style.height=offsetHeight;nextSibling.style.top=style.top;nextSibling.style.left=style.left;nextSibling.style.zIndex=style.zIndex - 1;nextSibling.style.display="block";nextSibling.style.position="absolute";focus();}}
function fHideCalendar(){with($id("calendar_div")){style.display="none";nextSibling.style.display="none";}}function fSetDate(iYear,iMonth,iDay){var iMonthNew=new String(iMonth);var iDayNew=new String(iDay);if(iMonthNew.length<2){iMonthNew="0"+iMonthNew;}if(iDayNew.length<2){iDayNew="0"+iDayNew;}gdCtrl.value=iYear+splitChar+iMonthNew+splitChar+iDayNew;fHideCalendar();}
function fSetSelected(){var iOffset=0;var iYear=parseInt($id("tbSelYear").value);var iMonth=parseInt($id("tbSelMonth").value);var aCell=$id("cellText"+arguments[0]);with(aCell){var iDay=parseInt(innerHTML);if(checkColor(style.color,gcNotCurMonth)){iOffset=(innerHTML>10)?-1:1;}iMonth+=iOffset;if(iMonth<1){iYear--;iMonth=12;}else if(iMonth>12){iYear++;iMonth=1;}}fSetDate(iYear,iMonth,iDay);}
function Point(iX,iY){this.x=iX;this.y=iY;}
function fBuildCal(iYear,iMonth){var aMonth=new Array();for(var i=1;i<7;i++){aMonth[i]=new Array(i);}var dCalDate=new Date(iYear,iMonth-1,1);var iDayOfFirst=dCalDate.getDay();var iDaysInMonth=new Date(iYear,iMonth,0).getDate();var iOffsetLast=new Date(iYear,iMonth-1,0).getDate()-iDayOfFirst+1;var iDate=1;var iNext=1;for(var d=0;d<7;d++){aMonth[1][d]=(d<iDayOfFirst)?(iOffsetLast+d)*(-1):iDate++;}for(var w=2;w<7;w++){for(var d=0;d<7;d++){aMonth[w][d]=(iDate<=iDaysInMonth)?iDate++:(iNext++)*(-1);}}return aMonth;}
function fDrawCal(iYear,iMonth,iCellHeight,iDateTextSize){var colorTD="background:"+thcolor+";";var styleTD="height:"+iCellHeight+"px;font-weight:bolder;font-size:"+iDateTextSize+"px;vertical-align:middle; text-align:center;";var dateCal="";dateCal+="<tr>";for(var i=0;i<7;i++){dateCal+="<th style='"+colorTD+styleTD+"color:"+gcWeekDay+";'>"+WeekDay[i]+"</th>";}dateCal+="</tr>";for(var w=1;w<7;w++){dateCal+="<tr>";for(var d=0;d<7;d++){var tmpid=w+""+d;dateCal+="<td style='"+styleTD+"cursor:pointer;' onclick='fSetSelected("+tmpid+")'>";dateCal+="<span id='cellText"+tmpid+"'></span>";dateCal+="</td>";}dateCal+="</tr>";}return dateCal;}
function fUpdateCal(iYear,iMonth){var myMonth=fBuildCal(iYear,iMonth);var i=0;for(var w=1;w<7;w++){for(var d=0;d<7;d++){with($id("cellText"+w+""+d)){parentNode.style.backgroundColor=gcMouseOut;parentNode.style.borderColor=thcolor;parentNode.onmouseover=function(){this.style.backgroundColor=gcMouseOver;};parentNode.onmouseout=function(){this.style.backgroundColor=gcMouseOut;};if(myMonth[w][d]<0){style.color=gcNotCurMonth;innerHTML=Math.abs(myMonth[w][d]);}else{style.color=((d==0)||(d==6))?gcRestDay:gcWorkDay;innerHTML=myMonth[w][d];if(iYear==giYear && iMonth==giMonth && myMonth[w][d]==giDay){parentNode.style.backgroundColor=gcMouseOver;parentNode.onmouseover=function(){this.style.backgroundColor=gcMouseOver;};parentNode.onmouseout=function(){this.style.backgroundColor=gcMouseOver;};}if(iYear==curYear && iMonth==curMonth && myMonth[w][d]==curDay){style.color=gcToday;parentNode.style.backgroundColor=gcTodayMouseOut;parentNode.onmouseover=function(){this.style.backgroundColor=gcTodayMouseOver;};parentNode.onmouseout=function(){this.style.backgroundColor=gcTodayMouseOut;};}}}}}}
function fCurrentDate(){return curYear+strYear+curMonth+strMonth+curDay+strDay;}
function fSetYearMon(iYear,iMon){$id("tbSelMonth").options[iMon-1].selected=true;for(var i=0;i<$id("tbSelYear").length;i++){if($id("tbSelYear").options[i].value==iYear){$id("tbSelYear").options[i].selected=true;}}fUpdateCal(iYear,iMon);}
function fPrevMonth(){var iMon=$id("tbSelMonth").value;var iYear=$id("tbSelYear").value;if(--iMon<1){iMon=12;iYear--;}fSetYearMon(iYear,iMon);}
function fNextMonth(){var iMon=$id("tbSelMonth").value;var iYear=$id("tbSelYear").value;if(++iMon>12){iMon=1;iYear++;}fSetYearMon(iYear,iMon);}
function fGetXY(aTag){var oTmp=aTag;var pt=new Point(0,0);do{pt.x+=oTmp.offsetLeft;pt.y+=oTmp.offsetTop;oTmp=oTmp.offsetParent;}while(oTmp.tagName.toUpperCase()!="BODY");return pt;}
function getDateDiv(){var noSelectForIE="";var noSelectForFireFox="";if(document.all){noSelectForIE="onselectstart='return false;'";}else{noSelectForFireFox="-moz-user-select:none;";}var dateDiv="<div id='calendar_div' onclick='event.cancelBubble=true' "+noSelectForIE+" style='"+noSelectForFireFox+"position:absolute;z-index:99;display:none;border:1px solid #999999;'><table ><tr><td><input type='button' id='PrevMonth' value='&lt;' style='height:20px;width:20px;font-weight:bolder;' onclick='fPrevMonth()'></td><td><select id='tbSelYear' style='border:1px solid;' calendar='calendar' onchange='fUpdateCal($idV(\"tbSelYear\"),$idV(\"tbSelMonth\"))'>";for(var i=startYear;i<endYear;i++){dateDiv+="<option value='"+i+"'>"+i+strYear+"</option>";}dateDiv+="</select></td><td><select id='tbSelMonth' style='border:1px solid;' calendar='calendar' onchange='fUpdateCal($idV(\"tbSelYear\"),$idV(\"tbSelMonth\"))'>";for(var i=0;i<12;i++){dateDiv+="<option value='"+(i+1)+"'>"+gMonths[i]+"</option>";}dateDiv+="</select></td><td><input type='button' id='NextMonth' value='&gt;' style='height:20px;width:20px;font-weight:bolder;' onclick='fNextMonth()'></td></tr><tr><td align='center' colspan='4'><div style='background-color:#fff'><table width='100%' border='0' cellpadding='3' cellspacing='1'>"+fDrawCal(giYear,giMonth,dayTdHeight,dayTdTextSize)+"</table></div></td></tr><tr><td align='center' colspan='4' nowrap><span style='cursor:pointer;font-weight:bolder;' onclick='fSetDate(curYear,curMonth,curDay)' onmouseover='this.style.color=\"#000\"' onmouseout='this.style.color=\"#000\"'>"+strToday+":"+fCurrentDate()+"</span></td></tr></table></div><iframe src='javascript:false;' scrolling='no' frameborder='0' style='position:absolute; top:0px; left:0px; display:none;'></iframe>";return dateDiv;}
with(document){onclick=fHideCalendar;write(getDateDiv());}