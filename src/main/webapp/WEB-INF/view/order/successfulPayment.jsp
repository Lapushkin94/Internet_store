<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <title>Success!</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container">
    <div class="row text-center" style="margin-left: 300px">
        <div class="col-sm-6 col-sm-offset-3">
            <br><br>
            <h2 style="color:#0fad00">Success</h2>
            <img src="https://cs.pikabu.ru/post_img/big/2013/11/13/0/1384288202_362714509.jpg" height="200px"
                 width="250px">
            <h3>Dear, ${clientName}</h3>
            <p style="font-size:20px;color:#5C5C5C;">Thank you for purchase! Hope, you'll come again!</p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-success">Home</a>
            <br><br>
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