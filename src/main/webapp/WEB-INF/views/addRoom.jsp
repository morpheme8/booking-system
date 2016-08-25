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
        <h2>Dodaj pokój:</h2>
        <hr class="m-y-2">
    </div>


    <form:form method="POST" modelAttribute="room" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>
            <div class="form-group">
                <label class="control-label" for="type">Podaj typ pokoju: </label>
                <div>
                    <c:forEach items="${roomTypes}" var="room">
                        <form:radiobutton  id="type" path="type" value="${room.getType()}"/> ${room.getType()}
                    </c:forEach>
                    <div>
                        <form:errors style="color:red" path="type" />
                    </div>

                </div>
            </div>
            <div class="form-group">
                <label class="control-label" for="beds">Ile łóżek?</label>
                <div>
                    <form:radiobutton  id="beds" path="beds" value="1"/>1
                    <form:radiobutton  id="beds" path="beds" value="2"/>2
                    <form:radiobutton  id="beds" path="beds" value="3"/>3
                    <div>
                        <form:errors style="color:red" path="beds" />
                    </div>

                </div>
            </div>
            <div class="form-group">
                <label class="control-label" for="beds">Max osób w pokoju?</label>
                <div>
                    <form:radiobutton  id="capacity" path="capacity" value="1"/>1
                    <form:radiobutton  id="capacity" path="capacity" value="2"/>2
                    <form:radiobutton  id="capacity" path="capacity" value="3"/>3
                    <form:radiobutton  id="capacity" path="capacity" value="4"/>4
                    <div>
                        <form:errors style="color:red" path="capacity" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-centered">
                    <button name="submit" type="submit" class="btn btn-primary pull-left">Zapisz</button>
                </div>
            </div>
        </fieldset>
    </form:form>
    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>
</html>
