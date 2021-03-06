<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <title>Payment</title>

</head>
<body>

<jsp:include page="../basic/header.jsp"/>

<div class="container">
    <div class="row" style="margin-top: 50px">

        <div class="col-xs-12 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">

                    <h3 class="panel-title">
                        Payment Details
                    </h3>

                </div>

                <div class="panel-body">

                    <form action="${pageContext.request.contextPath}/catalog/order/successfulPayment" method="post">

                        <div class="form-group">

                            <label for="cardNumber">
                                CARD NUMBER</label>
                            <div class="input-group">

                                <input type="tel" class="form-control" id="cardNumber" placeholder="XXXX-XXXX-XXXX-XXXX"
                                       pattern="[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}" required autofocus/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>

                            </div>
                        </div>

                        <div class="row">

                            <div class="col-xs-7 col-md-7">
                                <div class="form-group">

                                    <label for="expityMonth">
                                        EXPIRY DATE
                                    </label>

                                    <div class="col-xs-6 col-lg-6 pl-ziro">
                                        <input type="text" class="form-control" id="expityMonth" placeholder="MM" pattern="[0-1]{1}[0-9]{1}" required>
                                    </div>

                                    <div class="col-xs-6 col-lg-6 pl-ziro">
                                        <input type="text" class="form-control" id="expityYear" placeholder="YY" pattern="[0-2]{1}[0-9]{1}"
                                               required>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-5 col-md-5 pull-right">
                                <div class="form-group">
                                    <label for="cvCode">
                                        CV CODE</label>
                                    <input type="password" class="form-control" id="cvCode" placeholder="CV: XXX" minlength="3" maxlength="3" pattern="[0-9]{3}" required/>
                                </div>
                            </div>

                        </div>

                        <div>
                            <ul class="nav nav-pills nav-stacked">
                                <li class="active">
                                    <button type="button" class="btn btn-primary" disabled>
                                        Thank you for purchase!
                                    </button>
                                </li>
                            </ul>
                            <br/>
                        </div>

                        <div>
                            <button type="submit" class="btn btn-success btn-lg btn-block">
                                Pay
                            </button>
                        </div>

                    </form>

                    <div>
                        <a href="${pageContext.request.contextPath}/" style="color: wheat">
                            <button type="button" class="btn btn-secondary">
                                Home
                            </button>
                        </a>
                    </div>

                </div>
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
</body>
</html>
