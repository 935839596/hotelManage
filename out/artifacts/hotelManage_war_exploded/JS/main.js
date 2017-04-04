/**
 * Created by linGo on 2017/3/30.
 */
var render = function () {
    var zwin = $(window);
    var height = zwin.height();
    $('#wrap').height(height+'px');
}

window.onload = function () {
    //render();
    $('#applyBtn').on('click',function () {
        $('#apply').modal();
    })
    $('#administrator').on('click',function () {
        $('#admin').modal();
    })

    $('#login').on('click',function () {
        login($('#password').val());
    })

    $('#confirm').on('click',function () {

        if ($('#FirstName').val()==""){
            modal('plz input your FirstName.')
            return;

        }
        else if ($('#LastName').val()==""){
            modal('plz input your LastName.');
            return;
        }
        else if ($('#ChineseName').val()==""){
            modal('plz input your ChineseName.');
            return;
        }
        else if ($('#ID').val()==""){
            modal('plz input your ID');
            return;
        }
        else if ($('#Nationality').val()==""){
            modal('plz input your Nationality.');
            return;
        }
        else if ($('#Sex').val()==""){
            modal('plz input your Sex.');
            return;
        }
        else if ($('#DOB').val()==""){
            modal('plz input your DOB.');
            return;
        }
        else if ($('#RegDate').val()==""){
            modal('plz input your RegDate.');
            return;
        }
        else if ($('#DueDate').val()==""){
            modal('plz input your DueDate.');
            return;
        }else if ($('#Project').val()==""){
            modal('plz input your Project.');
            return;
        }
        else{
            if($('#file').val()!=""){
                upload();
                return;
            }else {
                add();
            }

        }
    })
}

function add(fileName) {
    $.ajax({
        url:'/hotelManage/Servlets/Apply',
        datatype:'html',
        type:'Post',
        data:'FirstName=' +$('#FirstName').val()+
            '&LastName=' +$('#LastName').val()+
            '&ChineseName=' +$('#ChineseName').val()+
            '&stuID=' +$('#ID').val()+
            '&Nationality=' +$('#Nationality').val()+
            '&Sex=' +$('#Sex').val()+
            '&DOB=' +$('#DOB').val()+
            '&RegDate=' +$('#RegDate').val()+
            '&DueDate=' +$('#DueDate').val()+
            '&Project=' +$('#Project').val()+
            '&remark=' +$('#comment').val()+
            '&file=' +fileName,
        success:function (data) {
            if(data == -1){
                modal('you are living here.plz apply later.');
                //location.reload();
            }else if (data==-2){
                modal('you have applied before.plz wait for  Administrators.');
            }else if(data==1){
                modal('successfully.plz wait for Administrators.');
                $('#waringWrap').on('hidden.bs.modal', function () {
                    location.reload();
                })
            }else{
                modal('sth. wrong.');
            }
        },
        error:function () {
            modal("you are wrong!");
        }
    })


}

function upload() {
    // var formData = new FormData($('#postForm'));
    // console.log(formData);
    var formData = new FormData();
    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url:'/hotelManage/Servlets/UploadServlet',
         datatype:'json',
        type:'Post',
        data:formData,
        cache:false,
        processData : false,
        contentType : false,
        beforeSend:function(){
            console.log("正在进行，请稍候");
        },
        success:function (data) {
            // console.log(data.filePath);
            var array = data.filePath;
            var path = '../upload/'+array[0];

            add(path);
        }
    })
}

function modal(sth) {
    var m = '<div class="modal" id="warning"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
        '<div class="modal-dialog" >' +
        '<div class="modal-content" >' +
        '<div class="modal-body">'+sth+'</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#waringWrap').html("");
    $('#waringWrap').append(m);
    $('#warning').modal();
}

function login(password) {
    $.ajax({
        url:'/hotelManage/Servlets/Login',
        datatype:'html',
        data:'password='+password,
        type:'Post',
        success:function (data) {
            if(data=='wrong'){
                modal('sry,password is wrong.plz try again.<br/> 密码错误，请重试');
            }else{
                window.location.href='/hotelManage/Jsp/Room.jsp';
            }
        }
    })
}
