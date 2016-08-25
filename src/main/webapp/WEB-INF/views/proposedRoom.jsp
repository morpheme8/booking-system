<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
    <title>Proponowane pokoje</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<body style="background-color: #F8FFF4">

<div class="container">
    <jsp:include page="/fragments/navbar.jsp" />


    <c:choose>
        <c:when test="${empty message}">



            <legend>W terminie od ${booking.getArrivalDate()} do ${booking.getDepartureDate()}
                mamy do zaoferowania następujące pokoje: </legend>

        <form:form method="POST" id="bookingForm" modelAttribute="booking"
                   onsubmit="return validateForm()" class="form-horizontal middle-form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="arrivalDate" value="${booking.getArrivalDate()}"/>
            <input type="hidden" name="departureDate" value="${booking.getDepartureDate()}"/>
            <input type="hidden" name="beds" value="${booking.getBeds()}"/>
            <input type="hidden" name="people" value="${booking.getPeople()}"/>



            <fieldset>


                <c:forEach items="${rooms}" var="room">

                    <div class="form-group">
                        <div>
                            <div>
                                <label for="${room.value}">Pokój typu ${room.key} </label>
                                <form:checkbox path="rooms" id="${room.value}" value="${room.value}" />

                            </div>
                        </div>
                    </div>
                </c:forEach>
                <form:errors path="rooms"/>

                <div class="form-group">
                    <div class="col-md-4">
                        <button name="submit" type="submit"  class="btn btn-primary pull-left">Zarezerwuj
        <span class="glyphicon glyphicon-triangle-right">
        </span>
                        </button>
                    </div>
                </div>

            </fieldset>
        </form:form>





    </c:when>
    <c:otherwise>
        <div class="alert alert-danger">
            ${message}
        </div>
    </c:otherwise>
</c:choose>
    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>


<script>
    function validateForm() {
        var valid = false;

        <c:forEach items="${rooms}" var="room">
        var checkboxElement = document.getElementById("${room.value}");
        var toVerify = checkboxElement.checked;
        if ( !( (toVerify==null )|| (toVerify=="") || (toVerify==undefined)) ) {
            valid = true;
        }
        </c:forEach>

        if(valid == false){
            alert("Aby dokończyć rezerwację musisz wybrać typ pokoju.");
            return false;
        }
    }
</script>


</html>