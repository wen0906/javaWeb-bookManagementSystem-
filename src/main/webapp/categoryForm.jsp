<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:if test="${category != null}">编辑分类</c:if><c:if test="${category == null}">添加分类</c:if></title>
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
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/">首页</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/book?action=list">图书管理</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/category?action=list">分类管理</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4><c:if test="${category != null}">编辑分类</c:if><c:if test="${category == null}">添加分类</c:if></h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${error != null}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/category?action=<c:if test="${category != null}">edit</c:if><c:if test="${category == null}">add</c:if>" method="post">
                            <c:if test="${category != null}">
                                <input type="hidden" name="id" value="${category.id}">
                            </c:if>

                            <div class="form-group">
                                <label for="name">分类名称</label>
                                <input type="text" class="form-control" id="name" name="name" 
                                       value="${category != null ? category.name : ''}" required>
                            </div>

                            <div class="form-group">
                                <label for="description">描述</label>
                                <textarea class="form-control" id="description" name="description" rows="3">${category != null ? category.description : ''}</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">提交</button>
                            <a href="${pageContext.request.contextPath}/category?action=list" class="btn btn-secondary">取消</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>