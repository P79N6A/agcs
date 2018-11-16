$(document).ready(function () {
    $("li[key='attr_shape'],li[key='attr_color'],li[key='attr_cut'],li[key='attr_polish'],li[key='attr_symmetry'],li[key='attr_cert'],li[key='attr_fluorescence'],li[key='attr_clarity'],li[key='attr_location']").each(function (i, el) {
        $(el).click(function () {
            if ($(el).hasClass('current')) {
                $(el).removeClass("current").attr('state', 'none');
            }
            else {
                $(el).addClass("current").attr('state', 'checked');
            }
            searchDiamond("", "", 1);
        });
    });

    $(".Search_list #SearchBtn").each(function () {
        if ($(this).attr("key") != "") {
            $(this).bind('click', function () {
                searchDiamond("", "", 1);
            });
        }
    });
});
