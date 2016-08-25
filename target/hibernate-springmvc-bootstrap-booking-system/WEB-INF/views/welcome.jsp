<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Moja pierwsza apka</title>

    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="/static/css/custom-styles.css" type="text/css" rel="stylesheet">

  <!--  <script src="/static/js/bootstrap.min.js" type="text/javascript"/>
  <script src="/static/js/jquery-3.0.0.min.js" type="text/javascript"/>

  <script src="/static/js/bootstrap.min.js" type="text/javascript"/>
  jQuery(document).ready(function(){
    $.getScript('/static/js/bootstrap.min.js');
    });

  -->


</head>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<body style="background-color: #F8FFF4">


<div class="container">
    <div class="wrapper">
<jsp:include page="/fragments/navbar.jsp" />

    <div class="panel-heading">
        <h2>Witaj ${loggedinuser}</h2>
        <p class="lead">Sprawdź dostępność i zarezerwuj pokój!</p>
        <hr class="m-y-2">
        <a class="btn btn-primary btn-lg" href="/booking" role="button">Zarezerwuj teraz!</a>

    </div>
    <br>
    <br>

    <div class="row">
        <div class="col col-md-2 col-sm-2">
        <ul class="nav nav-list">
            <li class="nav-header">Nawigacja:</li>
            <li><a href="#">O nas</a></li>
            <li><a href="/picture">Galeria</a></li>
            <li><a href="#">Promocje</a></li>
            <li><a href="/contact">Kontakt</a></li>
            <li><a href="#">Dojazd</a></li>
        </ul>


        </div>

        <div class="col col-md-10 col-sm-10">

        <h3>Uwaga super nowości i ciekawe rzeczy!</h3>
    <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lac
    in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam. Quisque semper justo
    at risus. Donec venenatis, turpis vel hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci.
    Nam congue, pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam sit
    amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare, ante vitae consectetuer
    consequat, purus sapien ultricies dolor, et mollis pede metus eget nisi. Praesent sodales velit quis augue.
    Cras suscipit, urna at aliquam rhoncus, urna quam viverra nisi, in interdum massa nibh nec erat.</p>
    </div>

    </div>
    <jsp:include page="/fragments/footer.jsp" />
        </div>
    <div class="push"/>
</div>


</body>
</html>
