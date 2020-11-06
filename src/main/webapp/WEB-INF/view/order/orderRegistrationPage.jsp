<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>Order registration</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 15px">


    <div class="alert alert-warning">
        <h2>
            <p class="text-center">Order registration</p>
        </h2>
        <p class="text-center">
            Press "Confirm!" to create order! Also you can pay online by credit card to speed up delivery process!
        </p>
    </div>

    <div>
        <form action="${pageContext.request.contextPath}/catalog/order/confirmation" method="POST">

            <div class="form-row">

                <input type="hidden" name="id" value="${client.clientAddress.id}">

                <div class="col-md-6 mb-3">
                    <label for="country">Country</label>
                    <input type="text" name="country" value="${client.clientAddress.country}" class="form-control"
                           id="country" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="city">City</label>
                    <input type="text" name="city" value="${client.clientAddress.city}" class="form-control" id="city"
                           required>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="postalCode">Postal Code</label>
                    <input type="text" name="postalCode" value="${client.clientAddress.postalCode}" class="form-control"
                           id="postalCode" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="street">Street</label>
                    <input type="text" name="street" value="${client.clientAddress.street}" class="form-control"
                           id="street" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="houseNumber">House number</label>
                    <input type="text" name="houseNumber" value="${client.clientAddress.houseNumber}"
                           class="form-control" id="houseNumber" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="flatNumber">Flat number</label>
                    <input type="text" name="flatNumber" value="${client.clientAddress.flatNumber}" class="form-control"
                           id="flatNumber" required>
                </div>

                <div class="col-md-10 mb-6">
                    <label for="comment">Comment</label>
                    <input type="text" name="comment" class="form-control" id="comment">
                </div>

                <div class="col-md-3 mb-3" style="margin-top: 20px">
                    <label for="payStatus">Pay status</label>
                    <select name="payStatus" class="custom-select" id="payStatus">
                        <option>Card online</option>
                        <option>Card (upon receipt)</option>
                        <option>Cash (upon receipt)</option>
                    </select>
                </div>

            </div>
            <button type="submit" class="btn btn-success" style="margin-top: 15px">
                Confirm!
            </button>

        </form>

        <a href="${pageContext.request.contextPath}/catalog/order">
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



