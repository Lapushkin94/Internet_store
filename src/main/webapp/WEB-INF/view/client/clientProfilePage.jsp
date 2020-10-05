<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Client's profile</title>

</head>
<br>

<jsp:include page="../basic/header.jsp"/>

<div class="container-fluid" style="text-align: center; margin-top: 50px">
    <div class="row">
        <div style="margin: auto">
            <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Client's profile</h2>
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <thead>
                <th>Name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Email</th>
                </thead>
                <tbody>
                <tr>
                    <td>${client.name}</td>
                    <td>${client.secondName}</td>
                    <td>${client.birthday}</td>
                    <td>${client.email}</td>
                </tr>

                </tbody>
            </table>

            <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px; margin-top: 50px">Clients Address</h2>
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <thead>
                <th>Country</th>
                <th>City</th>
                <th>Postal code</th>
                <th>Street</th>
                <th>House number</th>
                <th>Flat number</th>
                </thead>
                <tbody>
                <tr>
                    <td>${client.clientAddress.country}</td>
                    <td>${client.clientAddress.city}</td>
                    <td>${client.clientAddress.postalCode}</td>
                    <td>${client.clientAddress.street}</td>
                    <td>${client.clientAddress.houseNumber}</td>
                    <td>${client.clientAddress.flatNumber}</td>
                </tr>

                </tbody>
            </table>

            <div>
                <c:url value="/orderList" var="url">
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