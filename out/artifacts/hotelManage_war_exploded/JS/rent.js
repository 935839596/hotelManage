/**
 * Created by linGo on 2017/4/1.
 */
$(window).ready(function () {
    $("#month").datetimepicker({
        language:  'zh-CN',
        format: 'yyyy-mm',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 3, //这里就设置了默认视图为年视图
        minView: 3, //设置最小视图为年视图
        forceParse: 0,
    });
    $("#year").datetimepicker({
        language:  'zh-CN',
        format: 'yyyy',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 4, //这里就设置了默认视图为年视图
        minView: 5, //设置最小视图为年视图
        forceParse: 0,
    });

    $('.accordion-inner').on('click','.btn', function () {
        var date = $(this).prev().val();

        if (date == ""){
            $('#myModal .modal-body').html('plz select a date');
            $('#myModal').modal();
        }else if($(this).prev().attr('id')=="month"){
            count(1,date);
        }else {
            count(2,date);
        }

    })


})


function count(type,date) {
    $.ajax({
        url:'../Servlets/Rent',
        datatype:'html',
        type:'Post',
        data:'type='+type+"&date="+date,
        success:function (data) {
            $('#myModal .modal-body').html(''+date +'的总租金是'+data+"元。");
            $('#myModal').modal();
        }
    })
}