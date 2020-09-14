<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="<c:url value="/style/fire.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
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
    <c:url value="/catalog/editProduct" var="var"/>
</c:if>

<form action="${var}" method="POST">

    <c:if test="${!empty product.name}">
        <input type="hidden" name="id" value="${product.id}">
    </c:if>

    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="alternative_name">Alternative name</label>
    <input type="text" name="alternative_name" id="alternative_name">
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
    <label for="price">Price</label>
    <input type="text" name="price" id="price">

    <c:if test="${!empty product.name}">
        <input type="submit" value="Edit product">
    </c:if>
    <c:if test="${empty product.name}">
        <input type="submit" value="Add product">
    </c:if>

    <c:url value="/catalog" var="url">
        <c:param name="productListPage" value="${existingProductListPage}"/>
    </c:url>
    <a href="${url}">Back</a>

</form>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
