<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书详情 - ${book.title}</title>
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
                        <div class="row">
                            <div class="col-md-10">
                                <h4>图书详情</h4>
                            </div>
                            <div class="col-md-2 text-right">
                                <a href="${pageContext.request.contextPath}/book?action=list" class="btn btn-light btn-sm">返回列表</a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">ISBN编号</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.isbn}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">书名</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.title}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">作者</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.author}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">出版社</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.publisher != null ? book.publisher : '-'}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">出版日期</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.publishDate != null ? book.publishDate : '-'}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">分类</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.categoryName}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">价格</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="¥${book.price}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">库存数量</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.stock}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">描述</label>
                            <div class="col-md-9">
                                <textarea class="form-control" rows="3" readonly>${book.description != null ? book.description : '-'}</textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">创建时间</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.createdAt}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 col-form-label">更新时间</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control-plaintext" value="${book.updatedAt}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="${pageContext.request.contextPath}/book?action=edit&id=${book.id}" class="btn btn-primary">编辑</a>
                        <a href="${pageContext.request.contextPath}/book?action=list" class="btn btn-secondary">返回</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>