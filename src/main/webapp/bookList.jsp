<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书管理 - 图书列表</title>
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
        <c:if test="${sessionScope.message != null}">
            <div class="alert alert-info alert-dismissible fade show" role="alert">
                ${sessionScope.message}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="message" scope="session"/>
        </c:if>

        <div class="row mb-4">
            <div class="col-md-3">
                <div class="card bg-success text-white">
                    <div class="card-body text-center">
                        <h5 class="card-title">图书总数</h5>
                        <p class="card-text display-4">${stats.totalBooks}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-info text-white">
                    <div class="card-body text-center">
                        <h5 class="card-title">库存总量</h5>
                        <p class="card-text display-4">${stats.totalStock}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-warning text-white">
                    <div class="card-body text-center">
                        <h5 class="card-title">当前页码</h5>
                        <p class="card-text display-4">${currentPage}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-danger text-white">
                    <div class="card-body text-center">
                        <h5 class="card-title">总页数</h5>
                        <p class="card-text display-4">${totalPages}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <h2>图书列表</h2>
            </div>
            <div class="col-md-6 text-right">
                <a href="${pageContext.request.contextPath}/book?action=add" class="btn btn-primary">添加图书</a>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/book?action=search" method="post" class="form-inline mb-4">
            <div class="form-group mr-2">
                <select name="categoryId" class="form-control">
                    <option value="0">全部分类</option>
                    <c:forEach items="${categoryList}" var="category">
                        <option value="${category.id}" <c:if test="${selectedCategoryId != null && selectedCategoryId == category.id}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group mr-2">
                <input type="text" name="keyword" class="form-control" placeholder="搜索书名、作者、ISBN..." value="${keyword != null ? keyword : ''}">
            </div>
            <button type="submit" class="btn btn-success">搜索</button>
        </form>

        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ISBN</th>
                    <th>书名</th>
                    <th>作者</th>
                    <th>分类</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bookList}" var="book">
                    <tr>
                        <td>${book.isbn}</td>
                        <td><a href="${pageContext.request.contextPath}/book?action=detail&id=${book.id}">${book.title}</a></td>
                        <td>${book.author}</td>
                        <td>${book.categoryName}</td>
                        <td>¥${book.price}</td>
                        <td>${book.stock}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/book?action=detail&id=${book.id}" class="btn btn-info btn-sm">详情</a>
                            <a href="${pageContext.request.contextPath}/book?action=edit&id=${book.id}" class="btn btn-primary btn-sm">编辑</a>
                            <a href="${pageContext.request.contextPath}/book?action=delete&id=${book.id}" class="btn btn-danger btn-sm" onclick="return confirm('确定删除该图书吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${bookList.isEmpty()}">
            <div class="alert alert-info text-center">暂无图书数据</div>
        </c:if>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item <c:if test="${currentPage <= 1}">disabled</c:if>">
                    <a class="page-link" href="${pageContext.request.contextPath}/book?action=list&page=${currentPage - 1}&keyword=${keyword != null ? keyword : ''}&categoryId=${selectedCategoryId != null ? selectedCategoryId : '0'}">上一页</a>
                </li>
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <li class="page-item <c:if test="${currentPage == page}">active</c:if>">
                        <a class="page-link" href="${pageContext.request.contextPath}/book?action=list&page=${page}&keyword=${keyword != null ? keyword : ''}&categoryId=${selectedCategoryId != null ? selectedCategoryId : '0'}">${page}</a>
                    </li>
                </c:forEach>
                <li class="page-item <c:if test="${currentPage >= totalPages}">disabled</c:if>">
                    <a class="page-link" href="${pageContext.request.contextPath}/book?action=list&page=${currentPage + 1}&keyword=${keyword != null ? keyword : ''}&categoryId=${selectedCategoryId != null ? selectedCategoryId : '0'}">下一页</a>
                </li>
            </ul>
        </nav>
    </div>
</body>
</html>