<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Galeria</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<body style="background-color: #F8FFF4">
<div class="container">
    <jsp:include page="/fragments/navbar.jsp" />


    <div class="panel-heading">
        <h3>Zdjęcia hotelu: </h3>
        <hr class="m-y-2">
    </div>


    <div class="gallery">
        <div class="container">
            <div class="row">



                <c:forEach items="${roomDescImgIds}" var="roomDescIds">
                    <div class="gallery-item">
                            <jsp:include page="/picture/${roomDescIds}"/>
                    </div>

                </c:forEach>
            </div>
        </div>


<img src="/static/receptionSm.jpg" class="img-rounded" >







    <jsp:include page="/fragments/footer.jsp" />
    </div>



</body>
</html>
