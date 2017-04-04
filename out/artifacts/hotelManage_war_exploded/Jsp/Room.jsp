<%--
  Created by IntelliJ IDEA.
  User: linGo
  Date: 2017/4/1
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>房间信息</title>
    <link rel="icon" href="/hotelManage/img/1.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../style/main.css" >
    <link rel="stylesheet" href="../style/room.css">
    <link rel="stylesheet" href="../style/bootstrap.min.css" >
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="logo">
            <h1><a href="../index.jsp">Hotel</a></h1>
            <nav class="navbar navbar-reverse " role="navigation">
                <div class="container-fluid">
                    <div class="collapse navbar-collapse" id="example-navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="Room.jsp">房间信息</a></li>
                            <li><a href="Nationality.jsp">国籍统计</a></li>
                            <li><a href = "Rent.jsp">租金统计</a></li>
                            <li><a href="Application.jsp">学生申请<span id="badge" class="badge"></span></a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div id="roomView">
            <header>
                <div class="btn-group btn-group-lg " id="roomType">
                    <button type="button" class="btn btn-default btn-primary">全部房间</button>
                    <button type="button" class="btn btn-default">标单</button>
                    <button type="button" class="btn btn-default">标双</button>
                    <button type="button" class="btn btn-default">未满</button>
                </div>

                <div id="addBtn"><button  class="add btn-danger btn ">添加房间</button></div>

                <form class="bs-example bs-example-form" role="form">
                    <div class="input-group input-group-lg">
                        <input id="search" type="text" class="form-control" placeholder="查找房间号">
                        <span id="searchSpan" class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
                    </div>
                </form>

            </header>

            <section id="roomList">

            </section>

            <footer>
                <div id="page" class="btn-group ">
                    <button type="button" class="btn btn-default btn-primary">首页</button>
                    <button type="button" class="btn btn-default">上一页</button>
                    <button type="button" class="btn btn-default">下一页</button>
                    <button type="button" class="btn btn-default">尾页</button>
                </div>
            </footer>
        </div>

    </div>
</div>

<div class="modal " id="roomInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

</div><!--end of modal-->

<div class="modal fade" id="addRoom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">输入房间信息</h4>
            </div>
            <div class="modal-body">
                <form enctype="multipart/form-data" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="roomNum" class="col-sm-3 control-label">roomNo(房号)</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="roomNum" placeholder="请输入房间号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="size" class="col-sm-3 control-label">large(平方)</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="size" placeholder="请输入房间面积">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-3 control-label">type(单/双)</label>
                        <div class="col-sm-9">
                            <select id="type" class="form-control">
                                <option>1</option>
                                <option>2</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="front" class="col-sm-3 control-label">front(朝向)</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="front" placeholder="请输入房间朝向">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="monthRent" class="col-sm-3 control-label">monthRent(月租)</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="monthRent" placeholder="请输入月租">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="file" class="col-sm-3 control-label">file(附件)</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="file" placeholder="picures or other">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="comment" class="col-sm-3 control-label">remark(备注)</label>
                        <div class="col-sm-9">
                            <textarea style="resize: none;" class="form-control" id="comment" placeholder="备注"></textarea>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-10 col-sm-10">
                            <button type="button" id="submit" class="btn btn-primary">comfirm</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="waringWrap"></div>

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="../JS/bootstrap.min.js" ></script>
<script src="../JS/main.js"></script>
<script src="../JS/room.js"></script>
<script src="../JS/application.js"></script>
</body>
</html>