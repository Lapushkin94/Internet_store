<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/style/catalog.css"/>" rel="stylesheet" type="text/css"/>
    <title>Catalog</title>
</head>
<body bgcolor="#ffebcd">

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

</body>
</html>