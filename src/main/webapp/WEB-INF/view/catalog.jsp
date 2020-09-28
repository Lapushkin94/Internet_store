<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Catalog</title>

</head>
<br>

<jsp:include page="header.jsp"/>

<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-8">
            <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Our product</h2>
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <thead>
                <th>№</th>
                <th>Name</th>
                <th>Alt name</th>
                <th>Brand name</th>
                <th>Price</th>
                <th>Total in store</th>
                <th>Info</th>
                <th>#</th>
                <th colspan="3">Action</th>
                </thead>
                <tbody>
                <c:forEach var="product" items="${productList}" varStatus="i">
                    <tr>
                        <td>${i.index + 1 + (existingProductListPage - 1) * 10}</td>
                        <td>${product.name}</td>
                        <td>${product.alternative_name}</td>
                        <td>${product.brandName}</td>
                        <td>${product.price}</td>
                        <td>${product.quantityInStore}</td>

                        <td>
                            <a href="/catalog/productDetails/${product.id}">Details</a>
                        </td>

                        <td>
                            <form action="/catalog/get/${product.id}" method="POST">

                                <label for="quantity">#</label>
                                <input type="number" name="quantity" id="quantity" min="1"
                                       max="${product.quantityInStore}">

                                <input type="submit" value="Get it!">
                            </form>
                        </td>
                        <td>
                            <a href="/catalog/editProduct/${product.id}">Edit product</a>
                            <a href="/catalog/delete/${product.id}">Delete product</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="row">
            <div class="row-1; border border-danger" style="background-color: khaki; margin-top: 10px; padding: 10px; margin-left: 15px">
                <a href="${pageContext.request.contextPath}/catalog/addProduct">Add new product</a>
            </div>
            <div class="row-3; border border-danger" style="background-color: khaki; margin-top: 10px; margin-left: 30px; padding: 10px">
                <c:forEach begin="1" end="${productPagesCount}" step="1" varStatus="i">
                    <c:url value="/catalog" var="url">
                        <c:param name="existingProductListPage" value="${i.index}"/>
                    </c:url>
                    <a href="${url}">${i.index}</a>
                </c:forEach>
            </div>
            </div>

        </div>

        <div class="col-4">
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Your bascet</h2>
                <thead>
                <th>№</th>
                <th>Quantity</th>
                <th>Product</th>
                <th>price</th>
                <th>Info</th>
                <th>Action</th>
                </thead>

                <tbody>
                <c:forEach var="productInBascet" items="${productInBascetList}" varStatus="i">
                    <tr>
                        <td>${i.index + 1 + (productInBascetListPage - 1) * 10}</td>
                        <td>${productInBascet.quantity}</td>
                        <td>${productInBascet.product.name}</td>
                        <td>${productInBascet.product.price}</td>
                        <td>
                            <a href="/catalog/productDetails/${productInBascet.product.id}">Details</a>
                        </td>
                        <td>
                            <a href="/catalog/delete/${productInBascet.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="border border-danger"
                 style="float: left; background-color: khaki; margin-top: 10px; padding: 10px">
                <a href="${pageContext.request.contextPath}/order">Go to order registration!</a>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${catalogParam == 'catalogFalse'}">
            <div class="alert alert-danger" role="alert" style="margin-top: 20px">
                You want too many. You can <a href="${pageContext.request.contextPath}/contacts" class="alert-link">contact</a>
                us. We will do everything possible!
            </div>
        </c:when>
        <c:when test="${catalogParam == 'catalogSuccess'}">
            <div class="alert alert-success" role="alert" style="margin-top: 20px">
                Product successful added to your <a href="${pageContext.request.contextPath}/order" class="alert-link">bascet</a>.
                Nice choice!
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info" role="alert" style="margin-top: 20px">
                It is our choices that show what we truly are, far more than our abilities (c).
            </div>
        </c:otherwise>
    </c:choose>
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
</html>
