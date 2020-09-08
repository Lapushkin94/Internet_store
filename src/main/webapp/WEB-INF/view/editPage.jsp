<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty product.name}">
        <title>Add product</title>
    </c:if>
    <c:if test="${!empty product.name}">
        <title>Edit product</title>
    </c:if>
</head>
<body>

<c:if test="${empty product.name}">
    <c:url value="/catalog/add" var="var"/>
</c:if>
<c:if test="${!empty product.name}">
    <c:url value="/catalog/edit" var="var"/>
</c:if>

<form action="${var}" method="POST">

    <c:if test="${!empty product.name}">
        <input type="hidden" name="id" value="${product.id}">
    </c:if>

    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="category">Category</label>
    <input type="text" name="category" id="category">
    <label for="brandName">Brand name</label>
    <input type="text" name="brandName" id="brandName">
    <label for="color">Color</label>
    <input type="text" name="color" id="color">
    <label for="weight">Weight</label>
    <input type="text" name="weight" id="weight">
    <label for="country">Country</label>
    <input type="text" name="country" id="country">
    <label for="description">Description</label>
    <input type="text" name="description" id="description">
    <label for="number">Number</label>
    <input type="number" name="number" id="number">
    <label for="price">Price</label>
    <input type="text" name="price" id="price">

    <c:if test="${!empty product.name}">
        <input type="submit" value="Edit product">
    </c:if>
    <c:if test="${empty product.name}">
        <input type="submit" value="Add product">
    </c:if>

    <c:url value="/catalog" var="url">
        <c:param name="page" value="${page}"/>
    </c:url>
    <a href="${url}">Back</a>

</form>

</body>
</html>
