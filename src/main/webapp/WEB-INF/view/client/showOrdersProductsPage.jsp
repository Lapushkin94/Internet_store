<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Products</title>

</head>
<br>

<jsp:include page="../basic/header.jsp"/>

<div class="container-fluid" style="margin-left: 340px; margin-top: 50px; text-align: center">
    <div class="row">
        <div>
            <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Orders products
                <div class="row">
                    <div class="col">
                        <c:if test="${orderStatus.name == 'Opened'}">
                            <div>
                                <button type="submit" class="btn btn-info" style="margin-top: 20px" disabled>
                                    Status: Opened
                                </button>
                            </div>
                        </c:if>

                        <c:if test="${orderStatus.name == 'In process'}">
                            <div>
                                <button type="submit" class="btn btn-success" style="margin-top: 20px" disabled>
                                    Status: In process
                                </button>
                            </div>
                        </c:if>

                        <c:if test="${orderStatus.name == 'Closed'}">
                            <div>
                                <button type="submit" class="btn btn-secondary" style="margin-top: 20px" disabled>
                                    Status: Closed
                                </button>
                            </div>
                        </c:if>
                    </div>
                    <div class="col">

                        <div>
                            <%--                    <a href="${pageContext.request.contextPath}/myProfile/repeatOrder/${ordersId}">--%>
                            <a href="#">
                                <button type="button" class="btn btn-warning" style="margin-top: 20px">
                                    Repeat order
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </h2>
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <thead>
                <th>№</th>
                <th>Name</th>
                <th>Author</th>
                <th>Publisher</th>
                <th>Color</th>
                <th>Weight</th>
                <th>Country</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
                </thead>
                <tbody>
                <c:forEach var="product" items="${ordersProductList}" varStatus="i">
                    <tr>
                        <td>${i.index + 1 + (orderHistoryListPage - 1) * 10}</td>
                        <td>${product.name}</td>
                        <td>${product.alternative_name}</td>
                        <td>${product.brandName}</td>
                        <td>${product.color}</td>
                        <td>${product.weight}</td>
                        <td>${product.country}</td>
                        <td>${product.description}</td>
                        <td>${product.quantity}</td>
                        <td>${product.price}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="row">
                <c:forEach begin="1" end="${orderHistoryPagesCount}" step="1" varStatus="i">
                    <div class="border border-danger"
                         style="background-color: khaki; margin-top: 10px; margin-left: 15px; padding: 10px; float: left">
                        <c:url value="/myProfile/myOrders/checkOrdersProducts/${ordersId}" var="url">
                            <c:param name="orderHistoryListPage" value="${i.index}"/>
                        </c:url>
                        <a href="${url}">${i.index}</a>
                    </div>
                </c:forEach>
            </div>

            <div class="row" style="margin-top: 15px; margin-left: 2px">
                <c:url value="/myProfile/myOrders" var="url">
                    <c:param name="orderListPage" value="${orderListPage}"/>
                </c:url>
                <a href="${url}" style="color: wheat">
                    <button type="button" class="btn btn-secondary">
                        Back
                    </button>
                </a>
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
</html>