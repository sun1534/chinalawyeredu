/**
 * @example $('table').tablegrid();
 *
 * @params {oddColor, evenColor, overColor, selColor, useClick}
 * oddColor  : 奇数行背景色
 * evenColor : 偶数行背景色
 * overColor : 鼠标悬停时背景色
 * selColor  : 行选中时背景色
 * useClick  : 是否启用点击选中
 */

$.fn.tablegrid = function(params){
    var options = {
        oddColor   : '#D6F2FF',
        evenColor  : '#F2F9FD',
        overColor  : '#ADE4FF',
        selColor   : '#FDD57A',
        useClick   : false
    };
    $.extend(options, params);
    $(this).each(function(){
        $(this).find('tr:odd > td').css('backgroundColor', options.oddColor);
        $(this).find('tr:even > td').css('backgroundColor', options.evenColor);
        $(this).find('tr').each(function(){
            this.origColor = $(this).find('td').css('backgroundColor');
            this.clicked = false;
            if (options.useClick) {
                $(this).click(function(){
                    if (this.clicked) {
                        $(this).find('td').css('backgroundColor', this.origColor);
                        this.clicked = false;
                    } else {
                        $(this).find('td').css('backgroundColor', options.selColor);
                        this.clicked = true;
                    }
                   // $(this).find('td > input[type=checkbox]').attr('checked', this.clicked);
                    $(this).find('td > input[name=checkboxItem]').attr('checked', this.clicked);
                });
            }
            $(this).mouseover(function(){
                $(this).find('td').css('backgroundColor', options.overColor);
            });
            $(this).mouseout(function(){
                if (this.clicked) {
                    $(this).find('td').css('backgroundColor', options.selColor);
                } else {
                    $(this).find('td').css('backgroundColor', this.origColor);
                }
            });
        });
    });
};