<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Moja pierwsza apka</title>

    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">

<jsp:include page="/fragments/navbar.jsp" />



    <div class="panel-heading">
        <h2>${loggedinuser}, witaj w centrum kontroli nad światem!</h2>
        <p>(Albo przynajmniej kontroli nad użytkownikami i ich rezerwacjami.)</p>
        <hr class="m-y-2">
        </div>
<h3>Lista użytkowników</h3>

<table class="table-condensed">


    <thead>
        <tr>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>

    <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>

        <td><a class="btn btn-large btn-success" href="<c:url value='/admin/change-user-${user.id}-profile'/>">Zmień uprawnienia</a></td>
        <td><a class="btn btn-large btn-success" href="<c:url value='/admin/change-user-${user.id}-status'/>">Zmień status</a></td>
        <td><a class="btn btn-large btn-danger" href="<c:url value='/admin/delete-user-${user.id}'/>">Usuń użytkownika</a></td>

    </tr>
    </c:forEach>
    </tbody>
</table>

<h3>Lista Rezerwacji</h3>

    <table class="table-condensed">
        <thead>
            <th>Id rezerwacji</th>
            <th>Użytkownik</th>
            <th>Data przyjazdu</th>
            <th>Data Wyjazdu</th>
            <th>Typ pokoju</th>
        </thead>
        <tbody>

    <c:forEach items="${bookings}" var="booking">
        <tr>
            <td>${booking.getId()}</td>
            <td>${booking.getUser().getFirstName()}</td>
            <td>${booking.getArrivalDate()}</td>
            <td>${booking.getDepartureDate()}</td>
            <td><c:forEach items="${booking.getRooms()}" var="bookedRoom"> ${bookedRoom.getType()};
            </c:forEach>
            </td>
            <td><a class="btn btn-large btn-danger" href="<c:url value='/admin/delete-booking-${booking.getId()}'/>">Anuluj rezerwację</a></td>

        </tr>
    </c:forEach>
        </tbody>
</table>


    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>
</html>
