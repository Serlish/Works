<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Current page</title>
        <link href="IndexStyle.css" type="text/css" rel="stylesheet"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon.ico" type="image/x-icon">
    </head>
    <body>

        <table>
            <tr>
                <td>
                    <br>
                    <br>
                    <br>
            <center>
                <h3 id="header">Добро пожаловать!</h3>
            </center>              
            <center>
                <form action = "ListFlight" method = "post"> 
                    <input class="button" type="submit" name="Add" value="Список рейсов" onclick=""/>

                </form>
                <form action = "ListPassenger" method = "post">
                    <input class="button" type="submit" name="Add" value="Список пассажиров" onclick=""/>
                </form>
            </center>          
        </td>
    </tr>
</table>

</body>
</html>
