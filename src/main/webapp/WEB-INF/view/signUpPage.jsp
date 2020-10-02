<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">

<head>

    <title>Sign up page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


</head>
<body>

<div>

    <form action="${pageContext.request.contextPath}/signUp" method="POST">

        <label for="name">Name</label>
        <input type="text" name="name" id="name">
        <label for="secondName">Second name</label>
        <input type="text" name="secondName" id="secondName">
        <label for="birthday">Birthday</label>
        <input type="date" name="birthday" id="birthday">
        <label for="email">Email</label>
        <input type="email" name="email" id="email">
        <label for="password">Password</label>
        <input type="password" name="password" id="password">

        <label for="country">Country</label>
        <input type="text" name="country" id="country">
        <label for="city">City</label>
        <input type="text" name="city" id="city">
        <label for="postalCode">Postal Code</label>
        <input type="text" name="postalCode" id="postalCode">
        <label for="street">Street</label>
        <input type="text" name="street" id="street">
        <label for="houseNumber">House Number</label>
        <input type="text" name="houseNumber" id="houseNumber">
        <label for="flatNumber">Flat Number</label>
        <input type="text" name="flatNumber" id="flatNumber">

        <input type="submit" value="Create profile">

    </form>

</div>

<div style="margin-top: 20px">
    <a href="${pageContext.request.contextPath}/">
        <button type="button" class="btn btn-info">Home</button>
    </a>
</div>

</body>
</html>