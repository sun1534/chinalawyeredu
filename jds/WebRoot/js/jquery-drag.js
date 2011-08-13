jQuery.fn.drag = function(){   
    return this.each(function(){   
        var draging = false;   
        var startLeft,startTop;   
        var startX,startY;   
           
        $(this).css('cursor','move');   
        $(this).mousedown(function(event){   
            var offset = $(this).offset();   
            startLeft = offset.left;   
            startTop = offset.top;   
            startX = event.clientX;   
            startY = event.clientY;   
            draging = true;   
        }).mousemove(function(event){   
            if (draging == false)return;   
            var deltaX = event.clientX - startX;   
            var deltaY = event.clientY - startY;   
            var left = startLeft + deltaX;   
            var top = startTop + deltaY;   
            $(this).css('left',left+'px').css('top',top+'px');   
        }).mouseup(function(event){   
            draging = false;   
        });   
    });   
} 