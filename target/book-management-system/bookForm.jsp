<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:if test="${book != null}">编辑图书</c:if><c:if test="${book == null}">添加图书</c:if></title>
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
                    <li class="nav-item active">
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
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4><c:if test="${book != null}">编辑图书</c:if><c:if test="${book == null}">添加图书</c:if></h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${error != null}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/book?action=<c:if test="${book != null}">edit</c:if><c:if test="${book == null}">add</c:if>" method="post">
                            <c:if test="${book != null}">
                                <input type="hidden" name="id" value="${book.id}">
                            </c:if>

                            <div class="form-group">
                                <label for="isbn">ISBN编号</label>
                                <input type="text" class="form-control" id="isbn" name="isbn" 
                                       value="${book != null ? book.isbn : ''}" required>
                            </div>

                            <div class="form-group">
                                <label for="title">书名</label>
                                <input type="text" class="form-control" id="title" name="title" 
                                       value="${book != null ? book.title : ''}" required>
                            </div>

                            <div class="form-group">
                                <label for="author">作者</label>
                                <input type="text" class="form-control" id="author" name="author" 
                                       value="${book != null ? book.author : ''}" required>
                            </div>

                            <div class="form-group">
                                <label for="publisher">出版社</label>
                                <input type="text" class="form-control" id="publisher" name="publisher" 
                                       value="${book != null ? book.publisher : ''}">
                            </div>

                            <div class="form-group">
                                <label for="publishDate">出版日期</label>
                                <input type="date" class="form-control" id="publishDate" name="publishDate" 
                                       value="${book != null && book.publishDate != null ? book.publishDate : ''}">
                            </div>

                            <div class="form-group">
                                <label for="categoryId">分类</label>
                                <select class="form-control" id="categoryId" name="categoryId" required>
                                    <option value="">请选择分类</option>
                                    <c:forEach items="${categoryList}" var="category">
                                        <option value="${category.id}" 
                                                <c:if test="${book != null && book.categoryId == category.id}">selected</c:if>>${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="price">价格</label>
                                <input type="number" step="0.01" class="form-control" id="price" name="price" 
                                       value="${book != null ? book.price : ''}" required>
                            </div>

                            <div class="form-group">
                                <label for="stock">库存数量</label>
                                <input type="number" class="form-control" id="stock" name="stock" 
                                       value="${book != null ? book.stock : '0'}" required>
                            </div>

                            <div class="form-group">
                                <label for="description">描述</label>
                                <textarea class="form-control" id="description" name="description" rows="3">${book != null ? book.description : ''}</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">提交</button>
                            <a href="${pageContext.request.contextPath}/book?action=list" class="btn btn-secondary">取消</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>