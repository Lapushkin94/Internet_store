<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/home.css">
    <title>Internet shop</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<section>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-7">
                <h1 style="margin-left: 150px">You are welcome!</h1>
            </div>
            <div class="col-5">
                <div class="row" style="margin: 10px"></div>
                <a href="${pageContext.request.contextPath}/catalog">
                <img class="w-100"
                     src="https://image.freepik.com/free-vector/book-store_53876-16926.jpg"
                     alt="Shop">
                </a>
            </div>
        </div>
    </div>
</section>
<section>
    <div class="container" >
        <div class="row" style="background-color: paleturquoise">
        <h4 style="margin-left: 10px">Instant delivery:</h4>
    </div>
        <div class="row">
            <div class="col-9">
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://t3.ftcdn.net/jpg/02/77/50/26/240_F_277502654_P9Qwx7cukcmiWFtUi8SPXspBlwMbUr5k.jpg" height="200" width="200" alt="first">
                </div>

                <div class="carousel-item">
                    <img src="https://t3.ftcdn.net/jpg/02/77/50/26/240_F_277502654_P9Qwx7cukcmiWFtUi8SPXspBlwMbUr5k.jpg" height="200" width="200" alt="second">
                </div>
            </div>
        </div>
            </div>
            <div class="col-3">
                <a href="${pageContext.request.contextPath}/order">
                <img src="https://secure.diary.ru/userdir/3/4/2/8/3428343/86357077.jpg" height="200" width="200" alt="second" style="margin-left: 70px" class="border border-info">
                </a>
            </div>
        </div>
    </div>
</section>


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