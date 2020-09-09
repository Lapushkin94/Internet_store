<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/style/fire.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Details</title>
</head>
<body>
<h2>Product details</h2>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Category</th>
        <th>Brand name</th>
        <th>Color</th>
        <th>Weight</th>
        <th>Country</th>
        <th>Description</th>
        <th>Number</th>
        <th>Price</th>
        <th>Action</th>
    </tr>

    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.category}</td>
        <td>${product.brandName}</td>
        <td>${productDetails.color}</td>
        <td>${productDetails.weight}</td>
        <td>${productDetails.country}</td>
        <td>${productDetails.description}</td>
        <td>${product.number}</td>
        <td>${product.price}</td>
        <td>
            <a href="/catalog/get/${product.id}">Get it</a>
            <a href="/catalog/edit/${product.id}">Edit product</a>
            <a href="/catalog/delete/${product.id}">Delete product</a>
        </td>
    </tr>

    <tr>
        <c:url value="/catalog" var="url">
            <c:param name="page" value="${page}"/>
        </c:url>
        <a href="${url}">Back</a>
    </tr>

</table>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>