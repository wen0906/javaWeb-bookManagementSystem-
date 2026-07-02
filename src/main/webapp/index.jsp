<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书管理系统</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">图书管理系统</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/">首页</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/book?action=list">图书管理</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/category?action=list">分类管理</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="jumbotron">
            <h1 class="display-4">欢迎使用图书管理系统</h1>
            <p class="lead">基于纯JavaWeb技术栈(Servlet+JSP+JDBC)构建的图书管理系统</p>
            <hr class="my-4">
            <p>支持图书CRUD、分类管理、分页查询、模糊检索等功能</p>
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/book?action=list" role="button">进入图书管理</a>
        </div>

        <div class="row">
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">图书管理</h5>
                        <p class="card-text">图书的增删改查、分页浏览</p>
                        <a href="${pageContext.request.contextPath}/book?action=list" class="btn btn-primary">进入管理</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">分类管理</h5>
                        <p class="card-text">图书分类的创建和管理</p>
                        <a href="${pageContext.request.contextPath}/category?action=list" class="btn btn-primary">进入管理</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">统计分析</h5>
                        <p class="card-text">图书库存统计与数据展示</p>
                        <a href="${pageContext.request.contextPath}/book?action=list" class="btn btn-primary">查看统计</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="mt-4">
            <h2>技术栈</h2>
            <div class="row">
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-header bg-success text-white">后端</div>
                        <div class="card-body">
                            <ul class="list-group">
                                <li class="list-group-item">Servlet 4.0</li>
                                <li class="list-group-item">JSP 2.3</li>
                                <li class="list-group-item">JDBC</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-header bg-info text-white">数据库</div>
                        <div class="card-body">
                            <ul class="list-group">
                                <li class="list-group-item">MySQL 8.0</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-header bg-warning text-white">前端</div>
                        <div class="card-body">
                            <ul class="list-group">
                                <li class="list-group-item">Bootstrap 4</li>
                                <li class="list-group-item">jQuery</li>
                                <li class="list-group-item">AJAX</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-header bg-danger text-white">构建工具</div>
                        <div class="card-body">
                            <ul class="list-group">
                                <li class="list-group-item">Maven</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>