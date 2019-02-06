<%@page import="Model.Model"%>
<%@page import="Model.Passengers"%>
<%@page import="TX.TransformXml"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
%>
<%  TransformXml trfxml = new TransformXml();
    String strModel = (String) request.getSession().getAttribute("model");
    Model currentModel = trfxml.fromXmlToObject(strModel);
%>
<html>
    <head>
        <title>List of passengers</title>
        <link href="EditStyle.css" type="text/css" rel="stylesheet"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon.ico" type="image/x-icon">
    </head>
    <body>
    <center>
        <form action="ListPassenger" method="post">
            <fieldset>
                <legend>Список пассажиров</legend>
                <table>
                    <%try {
                            for (int i = 0; i < currentModel.getSizeP(); i++) {
                                Passengers currentPassenger = currentModel.obtainPassenger(i);
                                out.println("</td><td><label for=\"" + currentPassenger.getId() + "\">  <table><tr><td> <strong style=\"color:#000\";>№: " + currentPassenger.getId() + "</strong>");
                                out.println("</td><td><strong style=\"color:#000\";>Name: </strong>" + currentPassenger.getName()
                                        + ",  <strong style=\"color:#000\";>Surname: </strong>" + currentPassenger.getSurname()
                                        + ",  <strong style=\"color:#000\";>Age: </strong>" + currentPassenger.getAge()
                                        + ",  <strong style=\"color:#000\";>Place: </strong>" + currentPassenger.getPlace()
                                        + ",  <strong style=\"color:#000\";>Key: </strong>" + currentPassenger.getKey()
                                        + "</td></tr> </table> </label></td></tr>");
                            }
                        } catch (Exception e) {
                        }
                    %>

                    <br>
                    <center>                       
                        <input class="button" type="button" name="edit" id="ok" value="Добавить пассажира" onclick="redirect('AddPassenger.jsp')"/>
                        <input class="button" type="button" name="edit" id="ok" value="Изменить пассажира" onclick="redirect('UpdatePassenger.jsp')"/>
                        <input class="button" type="button" name="edit" id="ok" value="Удалить пассажира" onclick="redirect('DeletePassenger.jsp')"/>
                        <input class="button" type="button" name="find" id="ok" value="Поиск по рейсам" onclick="redirect('KeyInputPassenger.jsp')"/>
                        <input class="button" type="button" name="goback" id="ok" value="Вернуться на главную страницу" onclick="redirect('index.jsp')"/>
                    </center>
                </table>

            </fieldset>
        </form>
    </center>

    <script>
        function redirect(page) {
            window.location = page;
        }

    </script>
</body>
</html>