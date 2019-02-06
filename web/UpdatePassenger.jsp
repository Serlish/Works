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
        <title>Пассажир</title>
        <link href="EditStyle.css" type="text/css" rel="stylesheet"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon.ico" type="image/x-icon">
    </head>
    <body>


    <center>
        <legend>Изменить пассажира</legend>
        <form action="UpdatePassenger" method="post">        

            <% if (error != null) {%>
            <div class="error"><%=error%></div>
            <% }%>
            <br>
            <label for="ID">Номер пассажира:</label>
            <br>
            <input id="placeID" type="text" name="id" maxlength="30"/>
            <br>
            <label for="nameID">Имя пассажира</label>
            <br>
            <input id="nameID" type="text" name="name" maxlength="30"  autofocus/>
            <br>
            <label for="surnameID">Фамилия пассажира:</label>
            <br>
            <input id="surnameID" type="text" name="surname" maxlength="30"/>
            <br>
            <label for="surnameID">Возраст пассажира:</label>
            <br>
            <input id="surnameID" type="text" name="age" maxlength="30"/>
            <br>
            <label for="surnameID">№ места:</label>
            <br>
            <input id="surnameID" type="text" name="place" maxlength="30"/>
            <br>
            <label for="surnameID">№ рейса:</label>
            <br>
            <input id="surnameID" type="text" name="key" maxlength="30"/>
            <br>
            <input class="button" type="submit" name="ok" id="ok" value="Изменить" onclick=""/>
        </form>
        <input class="button" type="button" name="cancel" id="ok" value="Вернуться назад" onclick="redirect('Passengers.jsp')"/>
    </center>

    <script>
        function redirect(page) {
            window.location = page;
        }
    </script>
