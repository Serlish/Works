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
        <legend>Удалить пассажира</legend>
        <form action="DeletePassenger" method="post">        

            <% if (error != null) {%>
            <div class="error"><%=error%></div>
            <% }%>
            <br>
            <label for="ID">Номер пассажира:</label>
            <br>
            <input id="placeID" type="text" name="id" maxlength="30"/>
            <br>
            <input class="button" type="submit" name="ok" id="ok" value="Удалить" onclick=""/>
        </form>
        <input class="button" type="button" name="cancel" id="ok" value="Вернуться назад" onclick="redirect('Passengers.jsp')"/>
    </center>

    <script>
        function redirect(page) {
            window.location = page;
        }
    </script>
