<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Client control</title>

</head>
<br>

<jsp:include page="header.jsp"/>

<div class="container-fluid" style="margin-left: 340px; margin-top: 50px; text-align: center">
    <div class="row">
        <div>
            <h2 style="background-color: darksalmon; padding: 10px; margin-bottom: 10px">Clients</h2>
            <table class="table table-striped table-hover" style="background-color: cornsilk">
                <thead>
                <th>№</th>
                <th>Name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Email</th>
                <th>Password</th>
                <th>Address</th>
                </thead>
                <tbody>
                <c:forEach var="client" items="${clientList}" varStatus="i">
                    <tr>
                        <td>${i.index + 1 + (clientListPage - 1) * 10}</td>
                        <td>${client.name}</td>
                        <td>${client.secondName}</td>
                        <td>${client.birthday}</td>
                        <td>${client.email}</td>
                        <td>${client.password}</td>

                        <td>
                            <button type="button" class="btn btn-info">
                                <a href="/clientControl/clientAddress/${client.id}" style="color: wheat">Show
                                    address</a>
                            </button>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>


            <div class="border border-danger"
                 style="background-color: khaki; margin-top: 10px; margin-left: 30px; padding: 10px; float: left">
                <c:forEach begin="1" end="${clientPagesCount}" step="1" varStatus="i">
                    <c:url value="/clientControl" var="url">
                        <c:param name="clientListPage" value="${i.index}"/>
                    </c:url>
                    <a href="${url}">${i.index}</a>
                </c:forEach>
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
