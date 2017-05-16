
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${param.error != null}">
    <p>Invalid username / password</p>
</c:if>
<c:url var="loginUrl" value="/login"/>
<form name="f" action="/login" method="POST">
    <p><label for="username">User:</label></p>
    <input type="text" id="username" name="username"/>

    <p><label for="password">Password:</label></p>
    <input type="password" id="password" name="password">

    <div>
        <input name="submit" type="submit"/>
    </div>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
</body>
</html>
