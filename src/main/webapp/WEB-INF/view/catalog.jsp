<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/catalog.css"/>" rel="stylesheet" type="text/css"/>
    <title>Catalog</title>
</head>
<body>
<h2>Product list</h2>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Category</th>
        <th>Brand name</th>
        <th>Number</th>
        <th>Price</th>
        <th>Info</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${allProducts}">
        <tr>
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

                <!-- Administrator only -->
                <a href="/catalog/edit/${product.id}">Edit product</a>
                <a href="/catalog/delete/${product.id}">Delete product</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <a href="/catalog/add">Add new product</a>
        </td>
    </tr>
</table>

</body>
</html>