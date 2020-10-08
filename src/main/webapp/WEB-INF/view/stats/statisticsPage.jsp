<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Details</title>
</head>
<body>
<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 200px">
    <section class="mb-4">

        <h2 class="h1-responsive font-weight-bold text-center my-4">Statistics page</h2>
        <p class="text-center w-responsive mx-auto mb-5">Chose statistic type and push the button</p>

        <div class="row">

            <div class="col-md-9 mb-md-0 mb-5">

                <div class="text-center text-md-left">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/catalog">Top 10 products</a>
                </div>
                <div class="status"></div>
                <div class="text-center text-md-left" style="margin-top: 25px">
                <a class="btn btn-info" href="${pageContext.request.contextPath}/">Top 10 clients</a>
            </div>
                <div class="text-center text-md-left" style="margin-top: 25px">
                    <a class="btn btn-light border-dark" href="${pageContext.request.contextPath}/">Cash for a week</a>
                </div>
                <div class="status"></div>
            </div>

        </div>

    </section>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>