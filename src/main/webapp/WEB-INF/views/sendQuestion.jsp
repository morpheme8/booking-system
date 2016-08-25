<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Zadaj pytanie dotyczące rezerwacji</title>
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
        <h2>Zadaj pytanie dotyczące rezerwacji o id: ${bookingId}</h2>
        <hr class="m-y-2">
    </div>

    <form:form method="POST" modelAttribute="mailMessage" class="form-horizontal">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <fieldset>

            <div class="form-group">
                <label class="col-md-4 control-label" for="text">Wprowadź treść pytania:</label>
                <div class="col-md-4">
                    <form:textarea path="text" id="text" rows="6"
                                   cols="50"/>
                </div>
                <div>
                    <form:errors style="color:red" path="text" />
                </div>
            </div>




            <div class="form-group">
                <div class="col-md-4 col-centered">
                    <button name="submit" type="submit" class="btn btn-primary pull-left">Wyślij
                    </button>
                </div>
            </div>

        </fieldset>
    </form:form>
    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>
</html>
