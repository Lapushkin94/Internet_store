<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>Edit profile</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 15px">

    <div class="alert alert-warning">
        <h2>
            <p class="text-center">Edit profile</p>
        </h2>

        <c:if test="${emailStatus == 1}">
            <div class="alert alert-danger">
                Email already exists!
            </div>
        </c:if>
    </div>

    <div>

        <form action="${pageContext.request.contextPath}/myProfile/editProfile" method="POST">

            <div class="form-row">
                <input type="hidden" name="id" value="${client.id}"/>
                <input type="hidden" name="password" value="${client.password}"/>
                <div class="col-md-4 mb-3">
                    <label for="name">Name</label>
                    <input type="text" name="name" value="${client.name}" class="form-control" id="name" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="secondName">Last name</label>
                    <input type="text" name="secondName" value="${client.secondName}"
                           class="form-control" id="secondName">
                </div>
                <div class="col-md-4 mb-3">
                    <label for="birthday">Birthday</label>
                    <input type="date" name="birthday" value="${client.birthday}" class="form-control" id="birthday" min="1930-01-01" max="2020-01-01">
                </div>
                <div class="col-md-5 mb-3">
                    <label for="email">Email</label>
                    <input type="email" name="email" value="${client.email}" class="form-control" id="email"
                           minlength="3" required>
                </div>

            </div>
            <button type="submit" class="btn btn-success" style="margin-top: 15px">
                Edit profile
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