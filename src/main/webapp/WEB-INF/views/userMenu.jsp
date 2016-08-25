<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>Witaj ${userName}</h2>
        <hr class="m-y-2">
    </div>




<h3>Lista twoich rezerwacji: </h3>

<table class="table">
    <thead>
        <th>Id rezerwacji</th>
        <th>Data przyjazdu</th>
        <th>Data Wyjazdu</th>
        <th>Typ pokoju</th>
        <th>Liczba gości</th>
        <th>Liczba łóżek</th>
    </thead>
    <tbody>


    <c:forEach items="${bookings}" var="booking">
        <tr>

            <td>${booking.getId()}</td>
            <td>${booking.getArrivalDate()}</td>
            <td>${booking.getDepartureDate()}</td>
            <td>
                <c:forEach items="${booking.getRooms()}" var="bookedRoom"> ${bookedRoom.getType()}
                </c:forEach>
            </td>
            <td>${booking.getPeople()}</td>
            <td>${booking.getBeds()}</td>
            <td><a class="btn btn-large btn-success" href="<c:url value='/user/ask_question/${booking.getId()}'/>">Zadaj pytanie</a></td></td>
            <td><a class="btn btn-large btn-danger" href="<c:url value='/user/delete_booking/${booking.getId()}'/>">Anuluj rezerwację</a></td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>
</html>
