<%--
  Created by IntelliJ IDEA.
  User: linGo
  Date: 2017/4/2
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生申请</title>
    <link rel="icon" href="/hotelManage/img/1.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../style/main.css" >
    <link rel="stylesheet" href="../style/application.css" >
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
                            <li ><a href="Room.jsp">房间信息</a></li>
                            <li ><a href="Nationality.jsp">国籍统计</a></li>
                            <li ><a href = "Rent.jsp">租金统计</a></li>
                            <li class="active"><a href="Application.jsp">学生申请<span id="badge" class="badge"></span></a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div id="application" class="container-fluid">
            <div class="panel-group" id="accordion">

            </div>
        </div>


    </div>
</div>

<div id="waringWrap"></div>
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="../JS/bootstrap.min.js" ></script>
<script src="../JS/main.js"></script>
<script src="../JS/application.js"></script>
</body>
</html>