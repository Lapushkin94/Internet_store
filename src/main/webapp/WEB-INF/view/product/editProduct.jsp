<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <c:if test="${empty product.name}">
        <title>Add product</title>
    </c:if>
    <c:if test="${!empty product.name}">
        <title>Edit product</title>
    </c:if>
</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 15px">

    <c:if test="${empty product.name}">
        <c:url value="/catalog/add" var="var"/>
    </c:if>
    <c:if test="${!empty product.name}">
        <c:url value="/catalog/editProduct" var="var"/>
    </c:if>

    <div class="alert alert-warning">
        <h2>
            <c:if test="${empty product.name}">
                <p class="text-center">Add page</p>
            </c:if>
            <c:if test="${!empty product.name}">
                <p class="text-center">Edit page</p>
            </c:if>
        </h2>
    </div>

    <div>

        <form action="${var}" method="POST">

            <c:if test="${!empty product.name}">
                <div class="form-row">
                    <input type="hidden" name="id" value="${product.id}"/>
                    <div class="col-md-4 mb-3">
                        <label for="name">Name</label>
                        <input type="text" name="name" value="${product.name}" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="alternative_name">Alternative name</label>
                        <input type="text" name="alternative_name" value="${product.alternative_name}"
                               class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="brandName">Brand name</label>
                        <input type="text" name="brandName" value="${product.brandName}" class="form-control">
                    </div>
                    <div class="col-md-1 mb-3">
                        <label for="color">Color</label>
                        <input type="color" name="color" value="${product.color}" class="form-control">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="weight">Weight</label>
                        <input type="text" name="weight" value="${product.weight}" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="country">Country</label>
                        <input type="text" name="country" value="${product.country}" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="description">Description</label>
                        <input type="text" name="description" value="${product.description}" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="price">Price</label>
                        <input type="text" name="price" value="${product.price}" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="quantityInStore">Quantity</label>
                        <input type="text" name="quantityInStore" value="${product.quantityInStore}"
                               class="form-control">
                    </div>
                </div>
            </c:if>

            <c:if test="${empty product.name}">
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="alternative_name">Alternative name</label>
                        <input type="text" name="alternative_name" id="alternative_name" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="brandName">Brand name</label>
                        <input type="text" name="brandName" id="brandName" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="color">Color</label>
                        <input type="color" name="color" id="color" class="form-control">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-4 mb-3">
                        <label for="weight">Weight</label>
                        <input type="text" name="weight" id="weight" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="country">Country</label>
                        <input type="text" name="country" id="country" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="description">Description</label>
                        <input type="text" name="description" id="description" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="price">Price</label>
                        <input type="text" name="price" id="price" class="form-control">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="quantityInStore">Quantity</label>
                        <input type="text" name="quantityInStore" id="quantityInStore" class="form-control">
                    </div>
                </div>
            </c:if>

            <div class="col-md-4 mb-3">
                <label for="nameOfCategory">Category</label>
                <select name="nameOfCategory" id="nameOfCategory" class="custom-select">
                    <c:forEach var="categoryInputList" items="${categoryList}">
                        <option>${categoryInputList.nameOfCategory}</option>
                    </c:forEach>
                </select>
            </div>


            <c:if test="${!empty product.name}">
                <button type="submit" class="btn btn-success">
                    Edit product
                </button>
            </c:if>
            <c:if test="${empty product.name}">
                <button type="submit" class="btn btn-success">
                    Add product
                </button>
            </c:if>

        </form>

    </div>

    <div>

        <c:url value="/catalog" var="url">
            <c:param name="productListPage" value="${existingProductListPage}"/>
        </c:url>
        <button type="button" class="btn btn-secondary">
            <a href="${url}" style="color: wheat">Back</a>
        </button>

    </div>

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