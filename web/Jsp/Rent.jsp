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
    <title>租金统计</title>
    <link rel="icon" href="/hotelManage/img/1.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="../style/main.css" >
    <link rel="stylesheet" href="../style/bootstrap.min.css" >
    <link rel="stylesheet" href="../style/rent.css">
    <link rel="stylesheet" href="../style/bootstrap-datetimepicker.min.css">
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
                            <li class="active"><a href = "Rent.jsp">租金统计</a></li>
                            <li><a href="Application.jsp">学生申请<span id="badge" class="badge"></span></a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div id="moneyStatics">
            <!--<input id="month" class="month form_datetime form-control" type="text"   size="16">-->
            <!--<input id="year" class="month form_datetime form-control" type="text"  ="16">-->


            <div class="accordion-group">
                <div class="accordion-heading">
                    <button class="btn btn-primary accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                        按月统计
                    </button>
                </div>
                <div id="collapseOne" class="accordion-body collapse" style="height: 0px; ">
                    <div class="accordion-inner">
                        <input id="month" class="month form_datetime form-control" type="text" placeholder="选择一个月份"   size="16">
                        <button class="btn btn-primary"> 确定 </button>
                    </div>
                </div>
            </div>
            <div class="accordion-group">
                <div class="accordion-heading">
                    <button class="btn btn-primary accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseYear">
                        按年统计
                    </button>
                </div>
                <div id="collapseYear" class="accordion-body collapse" style="height: 0px; ">
                    <div class="accordion-inner">
                        <input id="year" class="month form_datetime form-control" type="text" placeholder="选择一个年份"  size="16">
                        <button class="btn btn-primary"> 确定 </button>
                    </div>
                </div>
            </div>


        </div>


    </div>

    <div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-body">在这里添加一些文本</div>

            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>


<div id="waringWrap"></div>


<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="../JS/bootstrap.min.js" ></script>
<script src="../JS/bootstrap-datetimepicker.min.js"></script>
<script src="../JS/main.js"></script>
<script src="../JS/rent.js"></script>
<script src="../JS/application.js"></script>
</body>
</html>
