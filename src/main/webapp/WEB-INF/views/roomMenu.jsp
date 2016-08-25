<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Dodaj pokój</title>

    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">

    <jsp:include page="/fragments/navbar.jsp" />


    <div class="panel-heading">
        <h2>Zarządzaj pokojami</h2>
        <hr class="m-y-2">
    </div>

<h3>Twoje pokoje: </h3>


    <table class="table-condensed">
        <thead>
        <tr>
        <th>Id</th>
        <th>Typ</th>
        <th>Liczba łóżek</th>
        <th>Pojemność</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.getId()}</td>
                <td>${room.getType()}</td>
                <td>${room.getBeds()}</td>
                <td>${room.getCapacity()}</td>
                <td><a class="btn btn-large btn-danger" href="<c:url value='/admin/delete_room/${room.getId()}'/>">Usuń pokój</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>



    <jsp:include page="/fragments/footer.jsp" />
    </div>
</body>
</html>
