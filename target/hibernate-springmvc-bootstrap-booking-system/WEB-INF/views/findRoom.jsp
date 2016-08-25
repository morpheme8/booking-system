<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Znajdź pokój</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<body style="background-color: #F8FFF4">
<div class="container">

<jsp:include page="/fragments/navbar.jsp" />

<form:form method="POST" modelAttribute="booking" class="form-horizontal">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


        <fieldset>


            <legend>Uzupełnij formularz: </legend>


            <div class="form-group">
                <label class="col-md-4 control-label" for="arrivalDate">Wprowadź datę przyjazdu</label>
                <div class="col-md-4">
                    <form:input type="date" min ="${today}" path="arrivalDate" id="arrivalDate" value="${today}"
                                class="form-control input-md"/>
                <div>
                        <form:errors  style="color:red" path="arrivalDate" />
            </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="departureDate">Wprowadź datę wyjazdu</label>
                <div class="col-md-4">
                    <form:input type="date" min ="${tomorrow}" path="departureDate"
                                id="departureDate" value="${tomorrow}" class="form-control input-md" />
                        <div>
                            <form:errors style="color:red" path="departureDate" />
                        </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="people">Ile osób?</label>
                <div class="col-md-4">
                    <label class="radio-inline" for="people">
                        <form:radiobutton  id="people" path="people" value="1"/>1
                    </label>
                    <label class="radio-inline" for="people">
                        <form:radiobutton  id="people" path="people" value="2"/>2
                    </label>
                    <label class="radio-inline" for="people">
                        <form:radiobutton  id="people" path="people" value="3"/>3
                    </label>
                    <label class="radio-inline" for="people">
                        <form:radiobutton  id="people" path="people" value="4"/>4
                    </label>
                    <div>
                        <form:errors style="color:red" path="people"/>
                    </div>
                </div>
            </div>





            <div class="form-group">
                <label class="col-md-4 control-label" for="beds">Liczba łóżek w pokoju?</label>
                <div class="col-md-4">
                    <label class="radio-inline" for="beds">
                        <form:radiobutton  id="beds" path="beds" value="1"/>1
                    </label>
                    <label class="radio-inline" for="beds">
                        <form:radiobutton  id="beds" path="beds" value="2"/>2
                    </label>
                    <label class="radio-inline" for="beds">
                        <form:radiobutton  id="beds" path="beds" value="3"/>3
                    </label>
                    <div>
                        <form:errors style="color:red" path="beds"/>
                    </div>
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-4 col-centered">
                    <button name="submit" type="submit" class="btn btn-primary pull-left">Dalej
        <span class="glyphicon glyphicon-triangle-right">
        </span>
      </button>
                </div>
            </div>

        </fieldset>
    </form:form>
    <jsp:include page="/fragments/footer.jsp" />
</div>

</body>
</html>
