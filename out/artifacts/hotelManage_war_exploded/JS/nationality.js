/**
 * Created by linGo on 2017/4/1.
 */
$(window).ready(function () {
    var currentPage = 1;
    $('#page').on('click', '.btn', function () {
        $b = $(this);
        $b.siblings().removeClass("btn-primary");
        $b.addClass('btn-primary');
    })
    init(currentPage);

    $('#page .btn').eq(0).on('click', function () {
        currentPage = 1;
        currentPage=init(currentPage);
    });
    $('#page .btn').eq(1).on('click', function () {
        currentPage -= 1;
        currentPage=init(currentPage);
    });
    $('#page .btn').eq(2).on('click', function () {
        currentPage += 1;
        currentPage= init(currentPage);
    });
    $('#page .btn').eq(3).on('click', function () {
        currentPage = -10000;
        currentPage= init(currentPage);
    });
})

function init(currentPage) {
    var xixi;
    $.ajax({
        url:'../Servlets/Nationality',
        datatype:'json',
        type:'Post',
        async:false,
        data:'&currentPage='+currentPage,
        success:function (data) {
            var countries = data.array;
            var length = countries.length;
            $('#table').html("");
            var head = '<header>一共有 '+data.totalNum+' 个学生，来自 '+length+' 个国家</header>' +
                '<table class="table table-bordered table-hover">' +
                '<thead>' +
                '<tr>' +
                '<th>countryName（国家）</th>' +
                '<th>total（人数）</th>' +
                ' </tr>' +
                '</thead>' +
                '<tbody>';
            var body = "";

            $.each(countries,function (index,item) {
                for(x in item){
                    body += '<tr>' +
                        '<td class="col-xs-5 col-sm-5 col-md-5">'+x+'</td>' +
                        '<td class="col-xs-5 col-sm-5 col-md-5">'+item[x]+'</td>' +
                        '</tr>'
                }
            })
            body += '</tbody>' +
                '</table>';

            var str = head + body;
            $('#table').append(str);
            xixi = data.currentPage;
        }
    })
    return xixi;
}