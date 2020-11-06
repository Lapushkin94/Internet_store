<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">

<head>

    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

<div style="margin-top: 50px">
    <div id="loginbox" class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

        <div class="panel panel-info">

            <div class="panel-heading">
                <div class="panel-title">Sign In</div>
            </div>

            <div style="padding-top: 30px" class="panel-body">

                <form action="${pageContext.request.contextPath}/signIn/process"
                      method="POST" class="form-horizontal">

                    <c:if test="${param.error != null}">
                        <div class="form-group">
                            <div class="col-xs-15">
                                <div>
                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                        Invalid username or password.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" name="email" placeholder="email" class="form-control" minlength="1" required>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" name="password" placeholder="password" class="form-control" minlength="1"
                               required>
                    </div>

                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-success">Login</button>
                        </div>
                    </div>
                </form>

                <div>
                    <a href="${pageContext.request.contextPath}/signUpPage">
                        <button type="button" class="btn btn-light">No acc? Sign Up!</button>
                    </a>
                </div>

                <div style="margin-top: 20px">
                    <a href="${pageContext.request.contextPath}/">
                        <button type="button" class="btn btn-info">Home</button>
                    </a>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>