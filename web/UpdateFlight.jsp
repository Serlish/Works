<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%
    String error = null;
    try {
        error = (String) request.getSession().getAttribute("error");
        if (error != null) {
            request.getSession().removeAttribute("error");
        }
    } catch (Exception e) {

    }
%>
<html>
    <head>
        <title>Рейс</title>
        <link href="EditStyle.css" type="text/css" rel="stylesheet"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon.ico" type="image/x-icon">
    </head>
    <body>


    <center>
        <legend>Изменить рейс</legend>
        <form action="UpdateFlight" method="post">        

            <% if (error != null) {%>
            <div class="error"><%=error%></div>
            <% }%>
            <br>
            <label for="ID">Номер рейса:</label>
            <br>
            <input id="placeID" type="text" name="id" maxlength="30"/>
            <br>
            <label for="nameID">Название рейса:</label>
            <br>
            <input id="nameID" type="text" name="name" maxlength="30"  autofocus/>
            <br>
            <label for="surnameID">Дата:</label>
            <br>
            <input id="surnameID" type="text" name="date" maxlength="30"/>
            <br>
            <input class="button" type="submit" name="ok" id="ok" value="Изменить" onclick=""/>
        </form>
        <input class="button" type="button" name="cancel" id="ok" value="Вернуться назад" onclick="redirect('Flights.jsp')"/>
    </center>

    <script>
        function redirect(page) {
            window.location = page;
        }
    </script>
