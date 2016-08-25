<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Zmień status użytkownika</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">
    <jsp:include page="/fragments/navbar.jsp" />


    <div class="panel-heading">
        <h2>Zmień status użytkownika <bold>${user.getFirstName()}</bold></h2>
    <hr class="m-y-2">
    </div>

    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <input type="hidden" name="id" value='${user.getId()}'/>
        <input type="hidden" name="firstName" value='${user.getFirstName()}'/>
        <input type="hidden" name="lastName" value='${user.getLastName()}'/>
        <input type="hidden" name="email" value='${user.getEmail()}'/>
        <input type="hidden" name="userProfiles" value='${user.getUserProfiles()}'/>
        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

    <fieldset>
        <div class="form-group">
            <c:choose>
                <c:when test="${userState eq 'Active'}">
                    <form:radiobutton  path="state" value="Inactive"/>Dezaktywuj</td>
                </c:when>
                <c:otherwise>
                    <form:radiobutton  path="state" value="Active"/>Aktywuj</td>

                </c:otherwise>
            </c:choose>
            <div>
                <form:errors style="color:red" path="state" />
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Zapisz</button>
        </div>

    </fieldset>
    </form:form>
    <jsp:include page="/fragments/footer.jsp" />
    </div>
</body>
</html>
