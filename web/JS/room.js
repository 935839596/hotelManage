/**
 * Created by linGo on 2017/4/1.
 */
$(window).ready(function () {
    //get roomInformation
    $('#roomView #roomList').on('click','.room',function () {
        $table = $(this);
        var roomID = $table.find('.panel-title').text();
        roomInfo(roomID);
        $('#roomInfo').modal();
    })
    //settle
    $('#roomInfo').on('click','#settle',function () {
        var form = $(this).prev().prev();
        var roomID = $('#roomInfo .modal-header td').eq(1).text();
        var stuID = form.find('div input').val();
        if (stuID==""){
            modal("请输入学生号");
        }else{
            settle(roomID,stuID);
        }
    })
    //add rooms
    $('#addBtn').on('click',function () {
        $('#addRoom').modal();
        $('#submit').click(function () {
            if($('#roomNum').val()==""){
                modal('请输入房间号');
                return;
            }
            else if($('#size').val()==""){
                modal('请输入房间面积');
                return;
            }
            else if($('#type').val()==""){
                modal('请输入房间类型');
                return;
            }
            else if($('#front').val()==""){
                modal('请输入房间朝向');
                return;
            }
            else if($('#monthRent').val()==""){
                modal('请输入月租金');
                return;
            }else {
                if($('#file').val()!=""){
                    upload();
                    return;
                }else {
                    addRooms();
                }

            }
        })
    })


    //css control
    $('#roomType ').on('click','.btn',function () {
        $b = $(this);
        $b.siblings().removeClass("btn-primary");
        $b.addClass('btn-primary');
    })
    $('#page').on('click','.btn',function () {
        $b = $(this);
        $b.siblings().removeClass("btn-primary");
        $b.addClass('btn-primary');
    })

    //choose type
    var currentPage = 1,type=0;
    init(currentPage,0);
    $('#roomType .btn').eq(0).on('click',function () {
        type=0;
        currentPage=1;
        btnClass();
        init(currentPage,type);
    })
    $('#roomType .btn').eq(1).on('click',function () {
        type=1;
        btnClass();
        currentPage=1;
        init(currentPage,1);
    })
    $('#roomType .btn').eq(2).on('click',function () {
        type=2;
        btnClass();
        currentPage=1;
        init(currentPage,2);
    })
    $('#roomType .btn').eq(3).on('click',function () {
        type=3;
        btnClass();
        init(currentPage,3);
    })

    //turn to page
    $('#page .btn').eq(0).on('click',function () {
        currentPage = 1;
        currentPage =init(currentPage,type);
    })
    $('#page .btn').eq(1).on('click',function () {
        currentPage -= 1;
        currentPage =init(currentPage,type);
    })
    $('#page .btn').eq(2).on('click',function () {
        currentPage += 1;
        currentPage = init(currentPage,type);
    })
    $('#page .btn').eq(3).on('click',function () {
        currentPage = -10000;
        currentPage =  init(currentPage,type);
    })

    //search room
    $('#searchSpan').on('click',function () {
       var roomNum= $('#search').val();
        if (roomNum == ""){
            modal('请输入所要查找的房间号');
            return ;
        }else{
            search(roomNum);
        }
    })

})

function init(cur,type){
    var currentPage = cur;
    $.ajax({
        url: "../Servlets/GetData",
        datatype: 'json',
        type: "Post",
        async:false,
        data: "currentPage=" + cur+"&type="+type,
        success:function (data) {
            if (data!=null){
                $('#roomList').html("");
                var list = data.list;
                currentPage = data.currentPage;
                if(list[0]==null) return;
                $.each(list,function (index,item) {
                    if (item.type==1) {
                        item.type = "标单";
                    }else if(item.type==2){
                        item.type = "标双";
                    }


                    var table = '<div class="room panel panel-default">';
                    if(item.remain==0){
                        table +='<div class="panel-heading full">';
                    }else{
                        table +='<div class="panel-heading empty">';
                    }
                       table += '<h3 class="panel-title">'+item.roomID+'</h3>'+
                        '</div>'+
                        '<table class="table">'+
                        '<tbody>'+
                        '<tr>'+
                        '<td>large(面积)</td>'+
                        '<td>'+item.size+'</td>'+
                        '</tr>'+
                        '<tr>'+
                        '<td>type(房型)</td>'+
                        '<td>'+item.type+'</td>'+
                        '</tr>'+
                        '<tr>'+
                        '<td>remain(空床)</td>'+
                        '<td>'+item.remain+'</td>'+
                        '</tr>'+
                        '</tbody>'+
                        '</table>'+
                        '</div>';
                    $('#roomList').append(table);
                })
            }
        },
        error:function () {
            modal("you are wrong");
        }
    })
    return currentPage;
};

function roomInfo(roomID) {
    $.ajax({
        url:"../Servlets/GetRoomInfo",
        datatype:'json',
        type:"Post",
        data:"roomID="+roomID,
        success:function (data) {
            $('#roomInfo').html("");
            if(data!=null){
                var feedback = data.feedback;
                var room = feedback[0].room;
                var head = '<div class="modal-dialog">' +
                    '<div class="modal-content">' +
                    '<div class="modal-header">' +
                    '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
                    '<table class="table table-bordered table-hover">' +
                    '<tbody>' +
                    '<tr>' +
                    '<td class="col-xs-3 col-sm-3 col-md-3">roomNum(房号)</td>' +
                    '<td class="col-xs-3 col-sm-3 col-md-3">' + room.roomID+'</td>'+
                    '<td class="col-xs-3 col-sm-3 col-md-3">front(朝向)</td>' +
                    '<td class="col-xs-3 col-sm-3 col-md-3">' + room.front+'</td>'+
                    '</tr>' +
                    '<tr>' +
                    '<td class="col-xs-3 col-sm-3 col-md-3">pay(月租/元)</td>' +
                    '<td class="col-xs-3 col-sm-3 col-md-3">'+room.pay+'</td>' +
                    '<td class="col-xs-3 col-sm-3 col-md-3">file(附件)</td>';
                    if(room.file!='undefined'){
                        head += '<td class="col-xs-3 col-sm-3 col-md-3"><a  href="'+room.file+'">附件</a></td>';
                    }else {
                        head += '<td class="col-xs-3 col-sm-3 col-md-3">无</td>';
                    }
                     head += '</tr>' +
                    '</tbody>' +
                    '</table>' +
                    '</div>';


                var body ="";
                body += '<div class="modal-body">';
                var flag=1;
                var stu = feedback[1].students;
                if(stu!=null){
                    $.each(stu,function (index,item) {
                         body +='<table class="table table-bordered table-hover">' +
                            '<caption>床位 '+flag+'</caption>' +
                            '<tbody>' +
                            '<tr>' +
                            '<td>FirstName(名字)</td>' +
                            '<td>'+item.FirstName+'</td>' +
                            '<td>LastName(姓)</td>' +
                            '<td>'+item.LastName+'</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<td>stuID(学号/护照)</td>' +
                            '<td>'+item.stuID+'</td>' +
                            '<td>ChineseName(中文名)</td>' +
                            '<td>'+item.ChineseName+'</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<td>arrivalDate(入住日)</td>' +
                            '<td>'+item.arrivalDate+'</td>' +
                            '<td>nationality(国家)</td>' +
                            '<td>'+item.nationality+'</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<td>galeDate(到期日)</td>' +
                            '<td>'+item.galeDate+'</td>' +
                             '<td>file(附件)</td>';
                        if (item.file!='undefined')
                            body += '<td><a href=" '+item.file+'">附件</a></td>';
                        else{
                            body +='<td>'+'无'+'</td>' ;
                        }
                        body += '</tr>' +
                            '</tbody>' +
                            '</table>';
                        flag++;
                    })
                }

                for(var i=0;i<room.remain;i++){
                    body += '<table class="table">' +
                        '<caption>床位 '+flag+'</caption>' +
                        '</table>' +
                        '<form name="settle" class="bs-example bs-example-form" role="form">' +
                        '<div class="input-group">' +
                        '<input type="text" class="form-control" placeholder="输入学生号">' +
                        '</div>' +
                        '</form>' +
                        '<br />' +
                        '<button id="settle" class="btn btn-primary">确定</button>';
                    flag++;
                }
                body += '</div>';
                var footer = "";
                footer += '<div class="modal-footer">' +
                    '<p>备注(remark):'+room.remark+'</p>' +
                    '<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
                    '</div>' +
                    '</div>' +
                    '</div>' ;
                var all = head + body +footer;
                $('#roomInfo').append(all);
            }

        }
    })
}

function settle(roomID,stuID) {
    $.ajax({
        url:'../Servlets/SettleDown',
        datatype:'html',
        type:'Post',
        data:"roomID="+roomID+"&stuID="+stuID,
        success:function (data) {
            if(data==0){
                modal('请输入正确的学生号码');
                return ;
            }else if(data==-1){
                modal('该学生已经入住，请安排别的学生');
                return;
            }
            else{
                roomInfo(roomID);
                $('#roomInfo').on('hidden.bs.modal', function () {
                    location.reload();
                })
            }
        }
    })
}

function addRooms(Path) {
    $.ajax({
        url:'../Servlets/AddRooms',
        datatype:'html',
        type:'Post',
        data:"roomNum=" +$('#roomNum').val()+"&size=" +$('#size').val()+"&type=" +$('#type').val()+"&front=" +$('#front').val()+"&monthRent=" +$('#monthRent').val()+ "&file=" +Path+ "&remark=" +$('#comment').val(),
        success:function (data) {
            if(data=='false'){
                modal("发生错误！可能因为房间号已经存在，或者其他原因。");
            }else{
                modal('add successfully');
                $('#waringWrap').on('hidden.bs.modal', function () {
                    location.reload();
                })
            }
        }
    })
}

function search(roomID) {
    $('#roomList').html("");
    $.ajax({
        url:'../Servlets/Search',
        datatype:'json',
        data:'roomNum='+roomID,
        type:'Post',
        success:function(data){
            var item = data.list;
            if (item.roomID==0) {
                modal('对不起，没有这个房间');
                $('#waringWrap').on('hidden.bs.modal', function () {
                    location.reload();
                })

            }
            var table = '<div class="room panel panel-default">';
            if(item.remain==0){
                table +='<div class="panel-heading full">';
            }else{
                table +='<div class="panel-heading empty">';
            }
            table += '<h3 class="panel-title">'+item.roomID+'</h3>'+
                '</div>'+
                '<table class="table">'+
                '<tbody>'+
                '<tr>'+
                '<td>size(面积)</td>'+
                '<td>'+item.size+'</td>'+
                '</tr>'+
                '<tr>'+
                '<td>pay(月租)</td>'+
                '<td>'+item.monthRent+'</td>'+
                '</tr>'+
                '<tr>'+
                '<td>remain(空床)</td>'+
                '<td>'+item.remain+'</td>'+
                '</tr>'+
                '</tbody>'+
                '</table>'+
                '</div>';
            $('#roomList').append(table);
        }
    })
}

function upload() {
    // var formData = new FormData($('#postForm'));
    // console.log(formData);
    var formData = new FormData();
    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url:'../Servlets/UploadServlet',
        datatype:'json',
        type:'Post',
        data:formData,
        cache:false,
        processData : false,
        contentType : false,
        beforeSend:function(){
            // console.log("正在进行，请稍候");
        },
        success:function (data) {
            // console.log(data.filePath);
            var array = data.filePath;
            var path = '../upload/'+array[0];

            addRooms(path);
        }
    })
}

function btnClass() {
    $('#page .btn').removeClass('btn-primary');
    $('#page .btn').eq(0).addClass('btn-primary');
}