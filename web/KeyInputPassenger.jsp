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
        <title>Пассажиры</title>
        <link href="EditStyle.css" type="text/css" rel="stylesheet"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon.ico" type="image/x-icon">
    </head>
    <body>


    <center>
        <legend>Выбрать пассажиров по рейсу</legend>
        <form action="KeyPassenger" method="post">        

            <% if (error != null) {%>
            <div class="error"><%=error%></div>
            <% }%>
            <br>
            <label for="surnameID">Номер рейса:</label>
            <br>
            <input id="surnameID" type="text" name="key" maxlength="30"/>
            <br>
            <input class="button" type="submit" name="ok" id="ok" value="Показать" onclick=""/>
        </form>
        <input class="button" type="button" name="cancel" id="ok" value="Вернуться назад" onclick="redirect('Passengers.jsp')"/>
        <input class="button" type="button" name="goback" id="ok" value="Вернуться на главную страницу" onclick="redirect('index.jsp')"/>
    </center>

    <script>
        function redirect(page) {
            window.location = page;
        }
    </script>
