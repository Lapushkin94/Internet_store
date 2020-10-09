<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">


    <title>Edit password</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container-fluid" style="margin-top: 15px">


    <div class="alert alert-warning">
        <h2>
            <p class="text-center">Edit password</p>
        </h2>
    </div>

    <div>
        <div class="row">
            <div style="margin: auto">
                <c:if test="${error == 1}">
                    <div class="alert alert-danger" style="float: left">
                        New passwords not equals!
                    </div>
                </c:if>

                <c:if test="${error == 2}">
                    <div class="alert alert-danger" style="float:left">
                        Wrong password!
                    </div>
                </c:if>
            </div>
        </div>

        <div class="row" style="margin-top: 25px">
            <div style="margin: auto">
            <form action="${pageContext.request.contextPath}/myProfile/editPassword" method="POST">

                <div class="form-row">

                    <div class="col-md-8 mb-3">
                        <label for="usersPassword">Password</label>
                        <input type="password" name="usersPassword" class="form-control" id="usersPassword" minlength="3" required>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label for="firstNewPassword">New password №1</label>
                        <input type="password" name="firstNewPassword"
                               class="form-control" id="firstNewPassword" minlength="3" required>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label for="secondNewPassword">New password №2</label>
                        <input type="password" name="secondNewPassword" class="form-control" id="secondNewPassword" minlength="3" required>
                    </div>

                </div>
                <button type="submit" class="btn btn-success" style="margin-top: 15px">
                    Edit password
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