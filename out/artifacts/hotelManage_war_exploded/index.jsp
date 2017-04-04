<%--
  Created by IntelliJ IDEA.
  User: linGo
  Date: 2017/4/2
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8" lang="English">
    <title>hotel</title>
    <link rel="icon" href="/hotelManage/img/1.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="style/main.css" >
    <link rel="stylesheet" href="style/bootstrap.min.css" >
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="logo">
            <h1>Hotel</h1>
        </div>
        <div id = "groupbtn">
            <a id="applyBtn" class="btn btn-danger btn-lg "  href="#" title="apply for living">apply now</a>
            <br />
            <a id="administrator" class="btn btn-danger btn-lg" href="#" title="For administrator to deal with the application ">administrator</a>
        </div>
    </div>
</div>


<div class="modal fade" id="apply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">please input your information</h4>
            </div>
            <div class="modal-body">
                <form enctype="multipart/form-data" id="postForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="FirstName" class="col-sm-3 control-label">FirstName</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="FirstName" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="LastName" class="col-sm-3 control-label">LastName</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="LastName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ChineseName" class="col-sm-3 control-label">ChineseName</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="ChineseName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ID" class="col-sm-3 control-label">ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="ID" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Nationality" class="col-sm-3 control-label">Nationality</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="Nationality" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Sex" class="col-sm-3 control-label">Sex</label>
                        <div class="col-sm-9">
                            <select id="Sex" class="form-control">
                                <option>male</option>
                                <option>female</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="DOB" class="col-sm-3 control-label">DOB</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="DOB" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="RegDate" class="col-sm-3 control-label">RegDate</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="RegDate" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="DueDate" class="col-sm-3 control-label">DueDate</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="DueDate" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Project" class="col-sm-3 control-label">Project</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="Project" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="comment" class="col-sm-3 control-label">remark</label>
                        <div class="col-sm-9">
                            <textarea style="resize: none;" class="form-control" id="comment" placeholder="remark"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="file" class="col-sm-3 control-label">file</label>
                        <div class="col-sm-9">
                            <input type="file" class="form-control" id="file" >
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-10 col-sm-10">
                            <button id="confirm" type="button" class="btn btn-primary">confirm</button>
                        </div>
                    </div>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="admin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form enctype="multipart/form-data"  class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="user" class="col-sm-3 control-label">用户</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="user" value="Administrator" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="password">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-5 col-sm-10">
                            <button id="login" type="button" class="btn btn-primary">登录</button>
                        </div>
                    </div>
                </form>
             </div>
        </div>
    </div>

</div>

<div id="waringWrap"></div>

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="JS/bootstrap.min.js" ></script>
<script src="JS/main.js"></script>
</body>
</html>
