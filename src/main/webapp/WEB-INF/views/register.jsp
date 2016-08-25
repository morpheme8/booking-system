<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<html>
<head>
    <title>Formularz Rejestracji</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">
    <div class="wrapper">
    <jsp:include page="/fragments/navbar.jsp" />



    <div class="row centered-form\">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default" style="border-color: black">
                <div class="panel-heading" style="background-color: mediumpurple">
                    <h3 class="panel-title" style="color: white">Zarejestruj się: </h3>
                </div>
                <div class="panel-body">


<form:form role="form" method="POST" modelAttribute="user" >
    <form:input path="id" type="hidden" id="id" />

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <form:input type="text" id="firstName" path="firstName" class="form-control input-sm" placeholder="Imię" />
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <form:input type="text" id="lastName" path="lastName" class="form-control input-sm" placeholder="Nazwisko" />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <form:input type="email" id="email" path="email" class="form-control input-sm" placeholder="Email" />
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <form:input type="password" id="password" path="password" class="form-control input-sm" placeholder="Hasło" />
                                </div>
                            </div>

    <!-- W przyszłości
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" id="passwordConfirmation" class="form-control input-sm" placeholder="Confirm Password">
                                </div>
                            </div>

    -->
                        </div>

                        <input type="submit" value="Zapisz" class="btn btn-primary btn-block" />

</form:form>

                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/fragments/footer.jsp" />
        </div>
    <div class="push"/>
</div>
</body>
</html>
