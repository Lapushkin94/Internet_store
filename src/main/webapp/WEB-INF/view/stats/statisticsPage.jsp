<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Statistics</title>
</head>
<body>
<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 50px">
    <section class="mb-4">

        <h2 class="h1-responsive font-weight-bold text-center my-4">Statistics page</h2>

        <div class="row">

            <div class="col-4">

                <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">

                    <h4 style="background-color: wheat">Top 10 products</h4>

                    <thead>
                    <th>Name</th>
                    <th>Quantity</th>
                    </thead>

                    <tbody>
                    <c:forEach var="product" items="${topTenProducts}" varStatus="i">

                        <tr>
                            <td>${product.key}</td>
                            <td>${product.value}</td>
                        </tr>

                    </c:forEach>

                    </tbody>

                </table>

                <div style="margin-top: 50px">
                    <a href="${pageContext.request.contextPath}/" style="color: wheat">
                        <button type="button" class="btn btn-warning">
                            Home
                        </button>
                    </a>
                </div>

            </div>

            <div class="col-4">

                <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">

                    <h4 style="background-color: wheat">Top 10 clients</h4>

                    <thead>
                    <th>Name</th>
                    <th>Profit</th>
                    </thead>

                    <tbody>
                    <c:forEach var="client" items="${top10clientsByProfit}" varStatus="i">

                        <tr>
                            <td>${client.key}</td>
                            <td>${client.value}</td>
                        </tr>

                    </c:forEach>

                    </tbody>

                </table>

            </div>

            <div class="col-4">

                <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">

                    <h4 style="background-color: wheat">Profit of the day</h4>
                    <tbody>
                    <tr>
                        <td>${totalProfitOfTheDay}</td>
                    </tr>
                    </tbody>

                </table>


                <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">

                    <h4 style="background-color: wheat">Profit of three days</h4>
                    <tbody>
                    <tr>
                        <td>${totalProfitOfTheThreeDays}</td>
                    </tr>
                    </tbody>

                </table>

                <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">

                    <h4 style="background-color: wheat">Profit of the week</h4>
                    <tbody>
                    <tr>
                        <td>${totalProfitOfTheWeek}</td>
                    </tr>
                    </tbody>

                </table>

            </div>

        </div>

    </section>
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