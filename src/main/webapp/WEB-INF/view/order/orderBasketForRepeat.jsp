<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Repeating order</title>
</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container-fluid" style="margin-top: 20px">
    <div class="row" style="margin-left: 60px">

        <div class="col-4">
            <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">
                <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Repeated basket</h2>

                <thead>
                <th>№</th>
                <th>Quantity</th>
                <th>Product</th>
                <th>Price</th>
                </thead>

                <tbody>
                <c:forEach var="productInBascet" items="${productInBascetList}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${productInBascet.quantity}</td>
                        <td>${productInBascet.product.name}</td>
                        <td>${productInBascet.product.price}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="row">
                <div class="row-3; border border-danger"
                     style="background-color: khaki; margin-left: 15px; padding: 10px; margin-top: 10px">
                    Total price: ${summPrice}
                </div>
            </div>

            <div style="margin-top: 25px">

                <c:if test="${!empty productInBascetList}">
                    <a href="${pageContext.request.contextPath}/order/orderRegistrationPage" style="color: wheat">
                        <button type="button" class="btn btn-info">
                            Order it!
                        </button>
                    </a>
                </c:if>

                <c:if test="${empty productInBascetList}">
                    <a href="${pageContext.request.contextPath}/order/orderRegistrationPage" style="color: wheat">
                        <button type="button" class="btn btn-info" disabled>
                            Order it!
                        </button>
                    </a>
                </c:if>

            </div>

            <div style="margin-top: 10px">
                <a href="${pageContext.request.contextPath}/catalog" style="color: wheat">
                    <button type="button" class="btn btn-secondary">
                        Back to catalog
                    </button>
                </a>
            </div>

        </div>


        <div class="col-3">
            <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">
                <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Not enough</h2>

                <thead>
                <th>№</th>
                <th>Product</th>
                <th>Added / required</th>
                </thead>

                <tbody>
                <c:forEach var="notAddedProduct" items="${quantityDifferenceOfNotAddedProducts}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${notAddedProduct.key}</td>
                        <td>${notAddedProduct.value}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <div class="row">

                <c:if test="${!empty quantityDifferenceOfNotAddedProducts}">
                    <div class="row-3; border border-danger"
                         style="background-color: khaki; margin-left: 15px; padding: 10px; margin-top: 10px">
                        We dont have enough, take all we have...
                    </div>
                </c:if>

                <c:if test="${empty quantityDifferenceOfNotAddedProducts}">
                    <div class="row-3; border border-dark"
                         style="background-color: yellowgreen; margin-left: 15px; padding: 10px; margin-top: 10px">
                        All existing products have been added to the basket
                    </div>
                </c:if>

            </div>
        </div>

        <div class="col-2">
            <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">
                <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Edited</h2>

                <thead>
                <th>№</th>
                <th>Product</th>
                </thead>

                <tbody>
                <c:forEach var="editedProduct" items="${editedProducts}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${editedProduct.name}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <div class="row">

                <c:if test="${!empty editedProducts}">
                    <div class="row-3; border border-danger"
                         style="background-color: khaki; margin-left: 15px; padding: 10px; margin-top: 10px">
                        These products was changed
                    </div>
                </c:if>

                <c:if test="${empty editedProducts}">
                    <div class="row-3; border border-dark"
                         style="background-color: yellowgreen; margin-left: 15px; padding: 10px; margin-top: 10px">
                        No edited products
                    </div>
                </c:if>

            </div>
        </div>

        <div class="col-2">
            <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">
                <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Missing</h2>

                <thead>
                <th>№</th>
                <th>Product</th>
                </thead>

                <tbody>
                <c:forEach var="missingProduct" items="${missingProducts}" varStatus="i">
                    <tr>
                        <td>${i.index + 1}</td>
                        <td>${missingProduct.name}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <div class="row">

                <c:if test="${!empty missingProducts}">
                    <div class="row-3; border border-danger"
                         style="background-color: khaki; margin-left: 15px; padding: 10px; margin-top: 10px">
                        These products are missing
                    </div>
                </c:if>

                <c:if test="${empty missingProducts}">
                    <div class="row-3; border border-dark"
                         style="background-color: yellowgreen; margin-left: 15px; padding: 10px; margin-top: 10px">
                        No missing products
                    </div>
                </c:if>

            </div>
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