<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<h1>Booking System</h1>


<nav id="menu-bar" class="navbar navbar-default navbar-inverse" style="background-color: mediumpurple">

        <div class="navbar-header">
            <a class="navbar-brand" href="/"><p style="color:white">Strona główna</p></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">

               <!--
                        <li><a href="/user" style="color:white">Menu użytkownika</a></li>

                -->


                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false" style="color:white">Opcje<span class="caret"></span></a>
                    <ul class="dropdown-menu">

                            <c:choose>
                                <c:when test="${loggedUserProfile == 'user'}">
                                    <li><a href="/user" >Menu użytkownika</a></li>
                                    <li><a href="/booking" >Zarezerwuj Pokój</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="/admin" >Menu administratora</a></li>
                                    <li><a href="/user" >Menu użytkownika</a></li>
                                    <li><a href="/admin/upload_description" >Dodaj opis pokoju</a></li>
                                    <li><a href="/admin/delete_description" >Usuń opis pokoju</a></li>
                                    <li><a href="/admin/room_menu" >Zarządzaj pokojami</a></li>
                                    <li><a href="/admin/add_room" >Dodaj pokój</a></li>
                                </c:otherwise>
                            </c:choose>

                        <li role="separator" class="divider"></li>
                        <li><a href="/logout">Wyloguj<span class="glyphicon glyphicon-off pull-right"></span></a></li>
                    </ul>

                </li>
            </ul>


        </div>

</nav>