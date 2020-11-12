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
                <p class="text-center">Add-page</p>
            </c:if>
            <c:if test="${!empty product.name}">
                <p class="text-center">Edit-page</p>
            </c:if>
        </h2>
    </div>

    <c:if test="${uniqName == 0}">
        <div class="alert alert-danger" role="alert" style="margin-top: 5px">
            Not uniq name!
        </div>
    </c:if>

    <div>

        <form action="${var}" method="POST">

            <c:if test="${!empty product.name}">
                <div class="form-row">

                    <input type="hidden" name="id" value="${product.id}"/>
                    <div class="col-md-4 mb-3">
                        <label for="name2">Name</label>
                        <input type="text" name="name" id="name2" value="${product.name}" class="form-control"
                               minlength="1" required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="alternative_name2">Author</label>
                        <input type="text" name="alternative_name" value="${product.alternative_name}"
                               id="alternative_name2"
                               class="form-control" minlength="1" required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="brandName2">Publisher</label>
                        <input type="text" name="brandName" id="brandName2" value="${product.brandName}"
                               class="form-control" minlength="1" required>
                    </div>

                    <div class="col-md-1 mb-3">
                        <label for="color2">Color</label>
                        <input type="color" name="color" id="color2" value="${product.color}" class="form-control" required>
                    </div>

                </div>

                <div class="form-row">

                    <div class="col-md-4 mb-3">
                        <label for="weight2">Weight</label>
                        <input type="number" name="weight" value="${product.weight}" id="weight2" class="form-control"
                               min="0" minlength="1" required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="country2">Country</label>
                        <input type="text" name="country" id="country2" value="${product.country}" class="form-control" required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="description2">Description</label>
                        <input type="text" name="description" value="${product.description}" id="description2"
                               class="form-control" minlength="5">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="price2">Price</label>
                        <input type="number" name="price" value="${product.price}" id="price2" class="form-control"
                               min="0" minlength="1" required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="quantityInStore1">Quantity</label>
                        <input type="number" name="quantityInStore" value="${product.quantityInStore}"
                               id="quantityInStore1" class="form-control" min="0" minlength="1" required>
                    </div>

                </div>

                <div class="col-md-4 mb-3">

                    <label for="nameOfCategory1">Category</label>
                    <select name="nameOfCategory" id="nameOfCategory1" class="custom-select">
                        <option value="${product.category.nameOfCategory}"
                                selected>${product.category.nameOfCategory}</option>
                        <c:forEach var="categoryInputList" items="${categoryList}">
                            <option>${categoryInputList.nameOfCategory}</option>
                        </c:forEach>
                    </select>

                </div>
            </c:if>

            <c:if test="${empty product.name}">
                <div class="form-row">

                    <div class="col-md-4 mb-3">
                        <label for="name">Name</label>
                        <input type="text" name="name" id="name" class="form-control" minlength="1" required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="alternative_name">Author</label>
                        <input type="text" name="alternative_name" id="alternative_name" class="form-control"
                               minlength="1">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="brandName">Publisher</label>
                        <input type="text" name="brandName" id="brandName" class="form-control" minlength="1">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="color">Color</label>
                        <input type="color" name="color" id="color" class="form-control">
                    </div>

                </div>

                <div class="form-row">

                    <div class="col-md-4 mb-3">
                        <label for="weight">Weight</label>
                        <input type="number" name="weight" id="weight" class="form-control" min="0" minlength="1"
                               required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="country">Country</label>
                        <input type="text" name="country" id="country" class="form-control" minlength="1">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="description">Description</label>
                        <input type="text" name="description" id="description" class="form-control" minlength="5">
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="price">Price</label>
                        <input type="number" name="price" id="price" class="form-control" min="0" minlength="1"
                               required>
                    </div>

                    <div class="col-md-4 mb-3">
                        <label for="quantityInStore">Quantity</label>
                        <input type="number" name="quantityInStore" id="quantityInStore" class="form-control" min="0"
                               minlength="1" required>
                    </div>

                </div>

                <div class="col-md-4 mb-3">

                    <label for="nameOfCategory2">Category</label>
                    <select name="nameOfCategory" id="nameOfCategory2" class="custom-select">
                        <c:forEach var="categoryInputList" items="${categoryList}">
                            <option>${categoryInputList.nameOfCategory}</option>
                        </c:forEach>
                    </select>

                </div>
            </c:if>

            <c:if test="${!empty product.name}">
                <button type="submit" class="btn btn-success" style="margin-top: 25px">
                    Edit product
                </button>
            </c:if>

            <c:if test="${empty product.name}">
                <button type="submit" class="btn btn-success" style="margin-top: 25px">
                    Add product
                </button>
            </c:if>

        </form>
    </div>

    <div>

        <c:url value="/catalog" var="url">
            <c:param name="existingProductListPage" value="${existingProductListPage}"/>
            <c:param name="productInBascetListPage" value="${productInBascetListPage}"/>
        </c:url>

        <a href="${url}" style="color: wheat">
            <button type="button" class="btn btn-secondary">
                Back
            </button>
        </a>

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