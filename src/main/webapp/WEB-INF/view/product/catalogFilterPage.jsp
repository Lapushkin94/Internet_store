<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>Filter</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 15px">

    <div class="alert alert-warning">
        <h2>
            <p class="text-center">Catalog filter</p>
        </h2>
    </div>

    <div>

        <form action="${pageContext.request.contextPath}/catalog/filterCatalog" method="POST">

            <div class="form-row">

                <c:if test="${empty temporaryProductName}">
                    <div class="col-md-4 mb-3" style="margin-top: 30px">
                        <label for="productName1"><h3>Name</h3></label>
                        <input type="text" name="productName" class="form-control" id="productName1"
                               placeholder="Enter product name">
                    </div>
                </c:if>

                <c:if test="${!empty temporaryProductName}">
                    <div class="col-md-4 mb-3" style="margin-top: 30px">
                        <label for="productName2"><h3>Name</h3></label>
                        <input type="text" name="productName" class="form-control" id="productName2"
                               value="${temporaryProductName}">
                    </div>
                </c:if>


                <c:if test="${temporaryOnlyInStore != false}">
                    <div class="col-md-2 mb-3" style="margin-left: 40px; margin-top: 30px">
                        <label for="onlyInStore1"><h3>Only in store</h3></label>
                        <input type="checkbox" name="onlyInStore" class="form-control" id="onlyInStore1" checked>
                    </div>
                </c:if>

                <c:if test="${temporaryOnlyInStore == false}">
                    <div class="col-md-2 mb-3" style="margin-left: 40px; margin-top: 30px">
                        <label for="onlyInStore2"><h3>Only in store</h3></label>
                        <input type="checkbox" name="onlyInStore" class="form-control" id="onlyInStore2">
                    </div>
                </c:if>


                <c:if test="${!empty temporaryNameOfCategory}">
                    <div class="col-md-4 mb-3" style="margin-left: 40px; margin-top: 30px">
                        <label for="nameOfCategory1"><h3>Category</h3></label>
                        <select name="nameOfCategory" id="nameOfCategory1" class="custom-select">
                            <option value="${temporaryNameOfCategory}" selected>${temporaryNameOfCategory}</option>
                            <option>All</option>
                            <c:forEach var="categoryInputList" items="${categoryList}">
                                <option>${categoryInputList.nameOfCategory}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>

                <c:if test="${empty temporaryNameOfCategory}">
                    <div class="col-md-4 mb-3" style="margin-left: 40px; margin-top: 30px">
                        <label for="nameOfCategory2"><h3>Category</h3></label>
                        <select name="nameOfCategory" id="nameOfCategory2" class="custom-select">
                            <option>All</option>
                            <c:forEach var="categoryInputList" items="${categoryList}">
                                <option>${categoryInputList.nameOfCategory}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>


                <c:if test="${!empty temporaryMinPrice}">
                    <div class="col-md-5 mb-3" style="margin-top: 30px">
                        <label for="minPrice1"><h3>Min price</h3></label>
                        <input type="text" name="minPrice" class="form-control" id="minPrice1"
                               value="${temporaryMinPrice + 1}">
                    </div>
                </c:if>

                <c:if test="${empty temporaryMinPrice}">
                    <div class="col-md-5 mb-3" style="margin-top: 30px">
                        <label for="minPrice2"><h3>Min price</h3></label>
                        <input type="text" name="minPrice" class="form-control" id="minPrice2"
                               placeholder="${minPrice}">
                    </div>
                </c:if>


                <c:if test="${!empty temporaryMaxPrice}">
                    <div class="col-md-5 mb-3" style="margin-top: 30px">
                        <label for="maxPrice1"><h3>Max price</h3></label>
                        <input type="text" name="maxPrice" class="form-control" id="maxPrice1"
                               value="${temporaryMaxPrice - 1}">
                    </div>
                </c:if>

                <c:if test="${empty temporaryMaxPrice}">
                    <div class="col-md-5 mb-3" style="margin-top: 30px">
                        <label for="maxPrice2"><h3>Max price</h3></label>
                        <input type="text" name="maxPrice" class="form-control" id="maxPrice2"
                               placeholder="${maxPrice}">
                    </div>
                </c:if>

            </div>

            <button type="submit" class="btn btn-success" style="margin-top: 25px">
                Filter
            </button>

        </form>

        <div style="margin-top: 25px">
            <c:url value="/catalog" var="url">
                <c:param name="existingProductListPage" value="${existingProductListPage}"/>
                <c:param name="productInBascetListPage" value="${productInBascetListPage}"/>
            </c:url>
            <a class="btn btn-secondary" href="${url}">Back</a>
        </div>

        <div style="margin-top: 25px">
            <a href="${pageContext.request.contextPath}/catalog/resetFilter" style="color: wheat">
                <button type="button" class="btn btn-warning">
                    Rest filter
                </button>
            </a>
        </div>

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