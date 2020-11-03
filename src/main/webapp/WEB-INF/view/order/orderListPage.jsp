<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Orders</title>
</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container" style="margin-top: 20px">
    <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">
        <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Orders info</h2>
        <thead>

        <th>№</th>
        <th>Date</th>
        <th>Pay type</th>
        <th>Comment</th>
        <th>Order status</th>
        <th>Client</th>

        </thead>

        <tbody>
        <c:forEach var="order" items="${orderList}" varStatus="i">
            <tr>
                <td>${i.index + 1 + (orderListPage - 1) * 10}</td>
                <td>${order.date}</td>
                <td>${order.payStatus}</td>
                <td>${order.comment}</td>
                <td>${order.orderStatus.name}</td>
                <td>${order.client.email}</td>
                <td>
                    <a href="/orderList/clientDetails/${order.client.id}">
                        <button type="button" class="btn btn-info">
                            Client details
                        </button>
                    </a>
                </td>
                <td>
                    <a href="/orderList/orderDetails/${order.id}">
                        <button type="button" class="btn btn-info">
                            Order details
                        </button>
                    </a>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="row">
        <c:forEach begin="1" end="${orderPagesCount}" step="1" varStatus="i">
            <div class="row-3; border border-danger"
                 style="background-color: khaki; margin-left: 15px; padding: 10px; margin-top: 10px">
                <c:url value="/orderList" var="url">
                    <c:param name="orderListPage" value="${i.index}"/>
                </c:url>
                <a href="${url}">${i.index}</a>
            </div>
        </c:forEach>
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