<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Details</title>
</head>
<body>
<jsp:include page="../basic/header.jsp"/>


<div class="container" style="margin-top: 20px">
    <table class="table table-striped table-hover" style="background-color: cornsilk">
        <h2>Product details</h2>

        <thead>
        <th>Id</th>
        <th>Name</th>
        <th>Alternative name</th>
        <th>Category</th>
        <th>Brand name</th>
        <th>Color</th>
        <th>Weight</th>
        <th>Country</th>
        <th>Description</th>
        <th>Price</th>
        <th>Total in store</th>
        <th>Quantity</th>

        <security:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
            <th colspan="3px">Action</th>
        </security:authorize>

        </thead>

        <tbody>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.alternative_name}</td>
        <td>${product.category.nameOfCategory}</td>
        <td>${product.brandName}</td>
        <td>${product.color}</td>
        <td>${product.weight}</td>
        <td>${product.country}</td>
        <td>${product.description}</td>
        <td>${product.price}</td>
        <td>${product.quantityInStore}</td>

        <td>
            <form action="/catalog/get/${product.id}" method="POST">

                <input type="number" name="quantity" id="quantity" min="1"
                       max="${product.quantityInStore}" value="1">

                <button type="submit" class="btn btn-success" style="margin-top: 10px">
                    Get it!
                </button>
            </form>
        </td>

        <security:authorize access="hasRole('ADMIN') or hasRole('MANAGER')">
            <td>
                <a class="btn btn-light" href="/catalog/editProduct/${product.id}">Edit</a>
                <a class="btn btn-light" href="/catalog/delete/${product.id}" style="margin-top: 10px">Delete</a>
            </td>
        </security:authorize>
        </tbody>

    </table>

    <div>
        <c:url value="/catalog" var="url">
            <c:param name="existingProductListPage" value="${existingProductListPage}"/>
            <c:param name="productInBascetListPage" value="${productInBascetListPage}"/>
        </c:url>
        <a class="btn btn-warning" href="${url}">Back</a>
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