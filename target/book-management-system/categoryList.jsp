<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>分类管理 - 分类列表</title>
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
        <c:if test="${sessionScope.message != null}">
            <div class="alert alert-info alert-dismissible fade show" role="alert">
                ${sessionScope.message}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <div class="row mb-4">
            <div class="col-md-6">
                <h2>分类列表</h2>
            </div>
            <div class="col-md-6 text-right">
                <a href="${pageContext.request.contextPath}/category?action=add" class="btn btn-primary">添加分类</a>
            </div>
        </div>

        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>分类名称</th>
                    <th>描述</th>
                    <th>创建时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categoryList}" var="category">
                    <tr>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>${category.description != null ? category.description : '-'}</td>
                        <td>${category.createdAt}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/category?action=edit&id=${category.id}" class="btn btn-primary btn-sm">编辑</a>
                            <a href="${pageContext.request.contextPath}/category?action=delete&id=${category.id}" class="btn btn-danger btn-sm" onclick="return confirm('确定删除该分类吗？删除后关联的图书也会被删除。')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${categoryList.isEmpty()}">
            <div class="alert alert-info text-center">暂无分类数据</div>
        </c:if>
    </div>
</body>
</html>