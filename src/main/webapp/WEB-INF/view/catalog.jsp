<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
        <th>Alternative name</th>
        <th>Brand name</th>
        <th>Price</th>
        <th>Info</th>
        <th colspan="3">Action</th>
    </tr>
    <c:forEach var="product" items="${productList}" varStatus="i">
        <tr>
            <td>${i.index + 1 + (productListPage - 1) * 10}</td>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.alternative_name}</td>
            <td>${product.brandName}</td>
            <td>${product.price}</td>
            <td>
                <a href="/catalog/productDetails/${product.id}">Details</a>
            </td>
            <td>
                <a href="/get/${product.id}">Get it</a>
                <a href="/catalog/editProduct/${product.id}">Edit product</a>
                <a href="/catalog/delete/${product.id}">Delete product</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4">
            <a href="/catalog/addProduct">Add new product</a>
        </td>
        <td>
            <c:forEach begin="1" end="${productPagesCount}" step="1" varStatus="i">
                <c:url value="/catalog" var="url">
                    <c:param name="productListPage" value="${i.index}"/>
                </c:url>
                <a href="${url}">${i.index}</a>
            </c:forEach>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Quantity</th>
        <th>Product</th>
        <th>Alt product name</th>
        <th>Brand</th>
        <th>price</th>
        <th>Info</th>
        <th>Action</th>
    </tr>
    <c:forEach var="productInBascet" items="${productInBascetList}" varStatus="i">
        <td>${i.index + 1 + (productInBascetListPage - 1) * 10}</td>
        <td>${productInBascet.id}</td>
        <td>${productInBascet.quantity}</td>
        <td>${productInBascet.product.name}</td>
        <td>${productInBascet.product.alternative_name}</td>
        <td>${productInBascet.product.brandName}</td>
        <td>${productInBascet.product.price}</td>
        <td>
            <a href="/catalog/productDetails/${productInBascet.product.id}">Details</a>
        </td>
        <td>
            <a href="/catalog/editProduct/${productInBascet.id}">Edit product</a>
            <a href="/catalog/delete/${productInBascet.id}">Delete product</a>
        </td>
        </tr>
    </c:forEach>
</table>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>