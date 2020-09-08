<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
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

</body>
</html>