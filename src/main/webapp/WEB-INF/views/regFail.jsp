<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rejestracja zakończona niepowodzeniem</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<body style="background-color: #F8FFF4">
<div class="container">
    <jsp:include page="/fragments/navbar.jsp" />



    <div class="alert alert-danger">
        <strong>${message}</strong>
    </div>

    <br>

<a href="/">Wróć do menu logowania</a>
    <jsp:include page="/fragments/footer.jsp" />
</div>
</body>
</html>
