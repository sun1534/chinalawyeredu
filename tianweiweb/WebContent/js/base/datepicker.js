            $(document).ready(function() {
                $("#dateinput").datepicker({
                    // 选择年份的上下限
                    // yearRange:"-99:+0",
                    yearRange: '1900:2008',//年份范围
                    // 最大日期
                    maxDate:new Date(),
                    showStatus: true,
                    showOn: "both",
                    buttonImage: "http://58.83.134.61/ln/css/datepicker/calendar.gif",
                    buttonImageOnly: true

                }).attr("readonly", "readonly");
            });