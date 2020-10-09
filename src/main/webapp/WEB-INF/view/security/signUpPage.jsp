<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">


    <title>Sign up</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 25px">


    <div class="alert alert-warning">
        <h2>
            <p class="text-center">Sign up</p>

        </h2>
        <c:if test="${passwordStatus == 1}">
            <div class="alert alert-danger">
                Passwords not equals!
            </div>
        </c:if>

        <c:if test="${emailStatus == 1}">
            <div class="alert alert-danger">
                Email already exists!
            </div>
        </c:if>
    </div>



    <div style="margin-top: 25px">

        <form action="${pageContext.request.contextPath}/signUp" method="POST">

            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="name">Name</label>
                    <input type="text" name="name" class="form-control" id="name" placeholder="1-20 symbols"
                           minlength="1" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="secondName">Last name</label>
                    <input type="text" name="secondName" class="form-control" id="secondName"
                           placeholder="1-20 symbols">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="birthday">Birthday</label>
                    <input type="date" name="birthday" class="form-control" id="birthday">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="email">Email</label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Email address" minlength="3"
                           required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="password1">Password</label>
                    <input type="password" name="firstPassword" class="form-control" id="password1"
                           placeholder="1-20 symbols" minlength="3" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="password2">Confirm password</label>
                    <input type="password" name="secondPassword" class="form-control" id="password2"
                           placeholder="1-20 symbols" minlength="3" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="country" style="margin-top: 50px">Country</label>
                    <input type="text" name="country" class="form-control" id="country" placeholder="Your country">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="city" style="margin-top: 50px">City</label>
                    <input type="text" name="city" class="form-control" id="city" placeholder="Your city">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="postalCode" style="margin-top: 50px">Postal code</label>
                    <input type="text" name="postalCode" class="form-control" id="postalCode" placeholder="6 numbers"
                           required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="street">Street</label>
                    <input type="text" name="street" class="form-control" id="street" placeholder="Your street">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="houseNumber">House number</label>
                    <input type="text" name="houseNumber" class="form-control" id="houseNumber"
                           placeholder="1-8 numbers" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="flatNumber">Flat number</label>
                    <input type="text" name="flatNumber" class="form-control" id="flatNumber" placeholder="1-8 numbers"
                           required>
                </div>

            </div>
            <button type="submit" class="btn btn-success" style="margin-top: 15px">
                Confirm
            </button>
        </form>

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