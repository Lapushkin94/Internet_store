<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/style/fire.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Catalog</title>
</head>
<body>
    
    <jsp:include page="header.jsp"/>

<table>
    <caption>Product list</caption>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Name</th>
        <th>Category</th>
        <th>Brand name</th>
        <th>Number</th>
        <th>Price</th>
        <th>Info</th>
        <th colspan="3">Action</th>
    </tr>
    <c:forEach var="product" items="${allProducts}" varStatus="i">
        <tr>
            <td>${i.index + 1 + (page - 1) * 10}</td>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.brandName}</td>
            <td>${product.number}</td>
            <td>${product.price}</td>
            <td>
                <a href="/catalog/details/${product.id}">Details</a>
            </td>
            <td>
                <a href="/get/${product.id}">Get it</a>
                <a href="/catalog/edit/${product.id}">Edit product</a>
                <a href="/catalog/delete/${product.id}">Delete product</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4">
            <a href="/catalog/add">Add new product</a>
        </td>
        <td>
            <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
                <c:url value="/catalog" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>