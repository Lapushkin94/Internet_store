<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Ctegories</title>

</head>
<br>

<jsp:include page="../basic/header.jsp"/>

<div class="container-fluid" style="margin-top: 10px">
    <div class="row">
        <div class="col-8">
            <h3>
                <div style="float: left; background-color: darksalmon; padding: 5px">Categories</div>
                <c:if test="${isEdited == 1}">
                    <div style="background-color: yellowgreen; float: left; padding: 5px; margin-left: 25px">
                        Edited!
                    </div>
                </c:if>
                <c:if test="${isEdited == 2}">
                    <div style="background-color: lightcyan; float: left; padding: 5px; margin-left: 25px">
                        Deleted!
                    </div>
                </c:if>
                <c:if test="${isEdited == 3}">
                    <div style="background-color: pink; float: left; padding: 5px; margin-left: 25px">
                        Added!
                    </div>
                </c:if>
            </h3>
            <table class="table table-striped table-hover table-sm" style="background-color: cornsilk">

                <thead>
                <th>Name</th>
                <th colspan="3">Action</th>
                </thead>

                <tbody>
                <c:forEach var="category" items="${categoryList}">
                    <tr>
                        <form action="/categories/editCategory/${category.id}" method="post">
                            <td>
                                <c:if test="${category.id == 1}">
                                    <input type="text" name="nameOfCategory" value="${category.nameOfCategory}"
                                           disabled>
                                </c:if>
                                <c:if test="${category.id != 1}">
                                    <input type="text" name="nameOfCategory" value="${category.nameOfCategory}">
                                </c:if>
                            </td>

                            <td>
                                <c:if test="${category.id != 1}">
                                    <button type="submit" class="btn btn-light">
                                        Edit
                                    </button>
                                </c:if>
                            </td>
                        </form>

                        <td>
                            <c:if test="${category.id != 1}">
                                <a href="/categories/deleteCategory/${category.id}" style="color: #0d0d0d">
                                    <button type="button" class="btn btn-light">
                                        Delete
                                    </button>
                                </a>
                            </c:if>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div style="margin-top: 20px">
                <form action="${pageContext.request.contextPath}/categories/addCategory" method="post">

                    <input type="text" name="nameOfCategory" placeholder="New category name">
                    <button type="submit" class="btn btn-warning" style="margin-left: 10px">Add category</button>
                </form>

            </div>

            <div style="margin-top: 20px">
                <a href="${pageContext.request.contextPath}/catalog">
                    <button type="button" class="btn btn-info">Back</button>
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