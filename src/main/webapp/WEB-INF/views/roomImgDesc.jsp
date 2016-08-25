<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Zapisz obrazy i opisy pokojów</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">
    <jsp:include page="/fragments/navbar.jsp" />


    <div class="panel-heading">
        <h2>Dodaj nowe zdjęcie i opis pokoju</h2>
        <hr class="m-y-2">
    </div>

    <form:form method="POST" modelAttribute="roomDescImg" class="form-horizontal" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>

            <div class="form-group">
                <label class="col-md-4 control-label" for="description">Wprowadź opis:</label>
                <div class="col-md-4">
                    <form:textarea path="description" id="description" rows="3"
                                   cols="30"/>

                    <div>
                        <form:errors style="color:red" path="description" />
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="roomType">Którego pokoju ma dotyczyć zdjęcie i opis?</label>
                <div>
                    <c:forEach items="${roomTypes}" var="room">
                        <form:radiobutton  id="roomType" path="roomType" value="${room.getType()}"/> ${room.getType()}
                    </c:forEach>
                    <form:radiobutton  id="roomType" path="roomType" value="all"/>cały hotel
                </div>

                <div>
                    <form:errors style="color:red" path="roomType" />
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="image">Wprowadź plik</label>
                <div class="col-md-4">
                    <form:input type="file" path="image" id="image" class="input-file"/>

                    <div>
                        <form:errors style="color:red" path="image"/>
                    </div>
                </div>


            </div>


            <div class="form-group">
                <div class="col-md-4 col-centered">
                    <button name="submit" type="submit" class="btn btn-primary pull-left">Zapisz
                    </button>
                </div>
            </div>

        </fieldset>
    </form:form>
    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>
</html>
