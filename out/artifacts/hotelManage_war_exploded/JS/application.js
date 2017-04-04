/**
 * Created by linGo on 2017/4/1.
 */
$(window).ready(function () {
    getApplication();
})

function getApplication() {
    $.ajax({
        url:'../Servlets/Application',
        datatype:'json',
        type:'Post',
        success:function (data) {
            if(data.feedback!=null){
                var feedback = data.feedback;
                $('#badge').text(feedback.length);
                var str = "";
                $.each(feedback,function (index,item) {
                    str += '<div class="panel panel-default">' +
                        '<div class="panel-heading">' +
                        '<h4 class="panel-title">' +
                        '<a data-toggle="collapse" data-parent="#accordion" href="#collapse'+index+'">' +
                        '学生：'+item.stuID+
                        '</a>' +
                        '</h4>' +
                        '</div>' +
                        '<div id="collapse'+index+'" class="panel-collapse collapse">' +
                        '<div class="panel-body">' +
                        '<table class="table table-bordered table-hover">' +
                        '<tbody>' +
                        '<tr>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">stuID（学生号）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.stuID+'</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3" name="CN">ChineseName(中文名)</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.ChineseName+'</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">FirstName（名）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.FirstName+'</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">LastName（姓）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.LastName+'</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">Sex（性别）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.sex+'</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">Nationality（国籍）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.nationality+'</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">Project（项目）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.project+'</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">DOB（生日）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.DOB+'</td>' +
                        '</tr>' +
                        '<tr>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">regDate（注册日）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.regDate+'</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">DueDate（有效日）</td>' +
                        '<td class="col-xs-3 col-sm-3 col-md-3">'+item.dueDate+'</td>' +
                        '</tr>';
                    if(item.file!='undefined')
                        str += '<tr>' +
                            '<td class="col-xs-3 col-sm-3 col-md-3">files（附件）</td>'+
                            '<td class="col-xs-3 col-sm-3 col-md-3"><a href="'+item.file+'">files</a></td>'+
                            '</tr>';

                    str += '</tbody>' +
                        '</table>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                })
                str += '</div></div>';
                $('#accordion').html("");
                $('#accordion').append(str);
            }
            else{
                $('#accordion').html('<h3>没有学生申请。</h3>');
                $('#badge').text(0);
            }
        }
    })
}