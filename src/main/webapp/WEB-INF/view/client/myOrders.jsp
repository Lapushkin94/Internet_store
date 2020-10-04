<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Your orders</title>

</head>
<br>

<jsp:include page="../basic/header.jsp"/>

<div class="container-fluid" style="margin-left: 480px; margin-top: 50px; text-align: center">
    <div class="row">
        <div>
            <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Your orders</h2>
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <thead>
                <th>№</th>
                <th>Date</th>
                <th>Pay status</th>
                <th>Comment</th>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderListByClientId}" varStatus="i">
                    <tr>
                        <td>${i.index + 1 + (orderListPage - 1) * 10}</td>
                        <td>${order.date}</td>
                        <td>${order.payStatus}</td>
                        <td>${order.comment}</td>

                        <td>
                            <button type="button" class="btn btn-info">
                                <a href="/myProfile/myOrders/checkOrdersProducts/${order.id}" style="color: wheat">Show
                                    products and status</a>
                            </button>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>



                <c:forEach begin="1" end="${orderPagesCount}" step="1" varStatus="i">
            <div class="border border-danger"
                 style="background-color: khaki; margin-top: 10px; margin-left: 30px; padding: 10px; float: left; margin-bottom: 50px">
                    <c:url value="/myProfile/myOrders" var="url">
                        <c:param name="orderListPage" value="${i.index}"/>
                    </c:url>
                    <a href="${url}">${i.index}</a>
            </div>
                </c:forEach>

            <button type="button" class="btn btn-secondary">
                <a href="${pageContext.request.contextPath}/myProfile" style="color: wheat">Your profile</a>
            </button>


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
</html>