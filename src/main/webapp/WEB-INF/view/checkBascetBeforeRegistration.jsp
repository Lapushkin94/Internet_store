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

<div class="container" style="margin-top: 20px">
    <table class="table table-striped table-hover" style="background-color: cornsilk">
        <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Your bascet</h2>
        <thead>
        <th>№</th>
        <th>Id</th>
        <th>Quantity</th>
        <th>Product</th>
        <th>Alt name</th>
        <th>Brand</th>
        <th>price</th>
        <th>Info</th>
        <th>Action</th>
        </thead>

        <tbody>
        <c:forEach var="productInBascet" items="${productInBascetList}" varStatus="i">
            <tr>
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
                    <a href="/catalog/delete/${productInBascet.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <td>
            <a href="${pageContext.request.contextPath}/order/registrationPage">Order it!</a>
        </td>
    </table>
</div>


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