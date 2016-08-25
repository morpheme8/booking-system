<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Rejestracja Zakończona Powodzeniem</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">
    <jsp:include page="/fragments/navbar.jsp" />



<c:choose>
    <c:when test="${not empty message}">
        <div class="alert alert-danger">
            <strong>${message}</strong>
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-success">
            <strong>${success}</strong>
        </div>

    </c:otherwise>
</c:choose>




   <a href="<c:url value='/' />">Wróć do strony startowej</a>


    <jsp:include page="/fragments/footer.jsp" />

</div>

</body>
</html>
