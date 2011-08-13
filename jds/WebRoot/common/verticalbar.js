var clickedButton
var clickedImage
var oldButton
var oldImage
var nowButtonIndex
var nowBarIndex
var nowBar
var numButton
var bds
var bus
grayScale = true
indexOfBar = new Array
underBarHeight = 0
numBar = 0
features = "toolbar=yes, location=yes, status=yes, menubar=yes, scrollbars=yes, resizable=yes"
var mainhref
function addbar(text, href, target)
{
return new bar(text, href, target)
}
function bar(text, href, target)
{
indexOfBar[numBar] = this
this.text = text
this.href = href
this.target = target
this.index = numBar++
this.children = new Array
this.nChildren = 0
}
function addbutton(parent, text, href, target, image, status)
{
parent.children[parent.nChildren++] = new button(text, href, target, image, status)
}
function button(text, href, target, image, status)
{
this.text = text
this.href = href
this.target = target
this.image = image
this.status = status
}
function initializeBar(selectButton)
{
var i = 0
with (document)
     {
     for (i = 0; i < numBar; i++)
         {
         write("<table id='table" + i + "'onclick=clickOnBar(" + i +") class=tab onmouseover=style.color='#FF4500' onmouseout=style.color='white' border=1 cellspacing=0 bordercolorlight=#B8DC7A;#808080 bordercolordark=#FFFFFF cellpadding=1 >"
              + "<tr><td>" + indexOfBar[i].text + "</td></tr></table>")
         indexOfBar[i].tabObj = all["table" + i].style
         }
     write("<table height=7><tr><td></td></tr></table>")
     }
for (i = 0; i < numBar; i++)
    {
    nowBarIndex = i
    showbar(indexOfBar[i])
    }
nowBar = indexOfBar[numBar - 1]
nowBarIndex = numBar - 1
for (i = 0; i < numBar - 1; i++)
    displayButton(indexOfBar[i], "none")
numButton = nowBar.nChildren
lastButton = document.all["s" + nowBarIndex + (numButton - 1)]
maxHeight = lastButton.offsetTop + 55
nowButtonIndex = 0
bds = buttondown.style
bus = buttonup.style
fcs = frameClose.style
fcs.left = bodycontents.clientWidth - 16
fcs.display = ""
if (bodycontents.clientHeight < maxHeight)
   {
   bds.top = bodycontents.clientHeight - 20
   bds.left = bodycontents.clientWidth - 20
   bds.display = ""
   }
if (selectButton)
   {
   if (grayScale)
      {
      clickedImage = document.images["imgs" + selectButton]
      clickedImage.style.filter = 0
      }
   clickedButton = document.all["s" + selectButton]
   clickedButton.className = "clicked"
   }
pathName = document.location.href
pathName = pathName.substring(0, pathName.lastIndexOf("/") + 1) + "imgbar/display.gif"

clickOnBar(0)
if (click) document.write("<iframe width=0 height=0 src=''></iframe>")
}
function showbar(bar)
{
var i = 0
nChildren = bar.nChildren
for (i = 0; i < nChildren; i++)
    {
    button = bar.children[i]
    target = (button.target)? "' target='" + button.target : ""
    Status = (button.status)? "' status='" + button.status : ""
    filter = (grayScale)? "id='imgs" + nowBarIndex + i + "' style='filter:gray' " : ""
    with (document)
         {
         write("<table id='s" + nowBarIndex + i + "' url='" + button.href + target + Status + "' cellspacing=0 cellpadding=0 width=37 height=37 onmouseover=mouseon(this) onmouseout=mouseout(this) onmousedown=mousedown(this) onmouseup=mouseup(this)>"
              + "<tr><td><a href='" + button.href + target + "'><img " + filter + "src='imgbar/" + button.image + "' border=0></a></td></tr></table>"
              + "<table id='label" + nowBarIndex + i + "'><tr><td>" + button.text + "</td></tr><tr height=6><td></td></tr></table>")
         //alert(button.href);
         button.imgObj = all["s" + nowBarIndex + i].style
         button.labObj = all["label" + nowBarIndex + i].style
         }
    }
}
function displayButton(bar, displayStyle)
{
nChildren = bar.nChildren
for (i = 0; i < nChildren; i++)
    with (bar.children[i])
         {
         imgObj.display = displayStyle
         labObj.display = displayStyle
         }
}
function clickOnBar(index)
{
if (nowBar != indexOfBar[index])
   {
   for (i = index; i > nowBarIndex; i--)
       with (indexOfBar[i].tabObj)
            {
            position = "relative"
            left = ""
            top = ""
            }
   for (i = index + 1; i <= nowBarIndex; i++)
       with (indexOfBar[i].tabObj)
            {
            position = "absolute"
            left = 0
            top = bodycontents.clientHeight - 20 * (numBar - i)
            }
   displayButton(nowBar, "none")
   nowBar = indexOfBar[index]
   nowBarIndex = index
   displayButton(nowBar, "")
   underBarHeight = 20 * (numBar - nowBarIndex - 1)
   numButton = nowBar.nChildren
   lastButton = document.all["s" + nowBarIndex + (numButton - 1)]
   maxHeight = lastButton.offsetTop + 55 + underBarHeight
   window.onresize()
   nowButtonIndex = 0
   bus.display = "none"
   }
if (nowBar.href)
   {
   window.open(nowBar.href, nowBar.target)
   if (clickedButton)
      {
      clickedButton.className = ""
      clickedButton = ""
      if (grayScale)
         {
         clickedImage.style.filter = "gray"
         clickedImage = ""
         }
      }
   }
}
function mouseon(button)
{
if (clickedButton != button)
   {
   button.className = "mouseon"
   if (grayScale) document.images["img" + button.id].style.filter = 0
   }
if (button.status) window.status = button.status
}
function mouseout(button)
{
if (clickedButton != button)
   {
   button.className = ""
   if (grayScale) document.images["img" + button.id].style.filter = "gray"
   }
window.status = ""
}
function mousedown(button)
{
if (window.event.button == 1)
   {
   oldButton = clickedButton
   clickedButton = button
   if (grayScale)
      {
      oldImage = clickedImage
      clickedImage = document.images["img" + button.id]
      }
   button.className = "clicked"
   }
}
function mouseup(button)
{
if (window.event.button == 1)
   {
   if (oldButton && oldButton != clickedButton)
      {
      oldButton.className = ""
      if (grayScale) oldImage.style.filter = "gray()"
      }
   window.open(button.url, (window.event.shiftKey)? "_blank" : button.target, features)
   }
}
function upclick()
{
if (nowButtonIndex)
   {
   document.all["s" + nowBarIndex + --nowButtonIndex].style.display = ""
   document.all["label" + nowBarIndex + nowButtonIndex].style.display = ""
   maxHeight = lastButton.offsetTop + 55 + underBarHeight
   window.onresize()
   }
if (!nowButtonIndex) bus.display = "none"
}
function downclick()
{
if (nowButtonIndex < numButton - 1)
   {
   document.all["s" + nowBarIndex + nowButtonIndex].style.display = "none"
   document.all["label" + nowBarIndex + nowButtonIndex++].style.display = "none"
   if (bus.display = "none")
      {
      bus.top = 20 * (nowBarIndex + 1) + 5
      bus.left = bodycontents.clientWidth - 20
      bus.display = ""
      }
   maxHeight = lastButton.offsetTop + 55 + underBarHeight
   window.onresize()
   }
}
function window.onresize()
{
bodyHeight = bodycontents.clientHeight
bodyWidth = bodycontents.clientWidth
if (bodyHeight < maxHeight)
   {
   bds.top = bodyHeight - 20 - underBarHeight
   bds.left = bodyWidth - 20
   bds.display = ""
   }
else
   {
   if (bodyHeight > maxHeight + 66) upclick()
   bds.display = "none"
   }
bus.left = bodyWidth - 20
fcs.left = bodyWidth - 16
if (nowBarIndex < numBar - 1)
   for (i = numBar - 1; i > nowBarIndex; i--)
       indexOfBar[i].tabObj.top = bodyHeight - 20 * (numBar - i)
}
function document.onkeydown()
{
return false
}
function document.ondragstart()
{
return false
}
function document.onselectstart()
{
return false
}
function document.oncontextmenu()
{
return false
}
function document.onmousedown()
{
if (window.event.ctrlKey) location.reload()
}
function closeFrame()
{
frameClose.className = ""
window.parent.index.cols = "0,*"
window.parent.frames(1).document.body.innerHTML += "<button style='height: 25; width: 25; position: absolute; top: 10; left: 10' onclick=\"style.display = 'none'; window.parent.index.cols = '100,*'\"><img alt=ÏÔÊ¾ src=" + pathName + " width=19 height=18></button>"
mainhref = window.parent.frames(1).document.location.href
scout()
}
function scout()
{
with (window.parent.frames(1).document)
     if (location.href != mainhref && readyState == "complete")
        {
        window.parent.frames(1).document.body.innerHTML += "<button style='height: 25; width: 25; position: absolute; top: 10; left: 10' onclick=\"style.display = 'none'; window.parent.index.cols = '100,*'\"><img alt=ÏÔÊ¾ src=" + pathName + " width=19 height=18></button>"
        mainhref = location.href
        }
if (window.parent.index.cols == "0,*") setTimeout("scout()", 1000)
}
function window.onload()
{
window.onresize()
}
