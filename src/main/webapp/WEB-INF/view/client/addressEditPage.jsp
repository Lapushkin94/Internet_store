<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">


    <title>Edit address</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 15px">


    <div class="alert alert-warning">
        <h2>
            <p class="text-center">Edit address</p>
        </h2>
    </div>

    <div>
        <form action="${pageContext.request.contextPath}/myProfile/editAddress" method="POST">

            <div class="form-row">
                <input type="hidden" name="id" value="${client.clientAddress.id}"/>
                <div class="col-md-4 mb-3">
                    <label for="country">Country</label>
                    <input type="text" name="country" value="${client.clientAddress.country}" class="form-control"
                           id="country">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="city">City</label>
                    <input type="text" name="city" value="${client.clientAddress.city}"
                           class="form-control" id="city">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="postalCode">Postal code</label>
                    <input type="number" name="postalCode" value="${client.clientAddress.postalCode}" class="form-control"
                           id="postalCode" required min="0" minlength="3">
                </div>
                <div class="col-md-5 mb-3">
                    <label for="street">Street</label>
                    <input type="text" name="street" value="${client.clientAddress.street}" class="form-control"
                           id="street" required>
                </div>
                <div class="col-md-5 mb-3">
                    <label for="houseNumber">House number</label>
                    <input type="number" name="houseNumber" value="${client.clientAddress.houseNumber}"
                           class="form-control" id="houseNumber" min="1" required>
                </div>
                <div class="col-md-5 mb-3">
                    <label for="flatNumber">Flat number</label>
                    <input type="number" name="flatNumber" value="${client.clientAddress.flatNumber}" class="form-control"
                           id="flatNumber" min="1" required>
                </div>

            </div>
            <button type="submit" class="btn btn-success" style="margin-top: 15px">
                Edit address
            </button>
        </form>

        <div>
            <a href="${pageContext.request.contextPath}/myProfile" style="color: wheat">
                <button type="button" class="btn btn-secondary">
                    Back
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