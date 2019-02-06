<%@page import="Model.Model"%>
<%@page import="Model.Flights"%>
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
        <title>List of flights</title>
        <link href="EditStyle.css" type="text/css" rel="stylesheet"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon.ico" type="image/x-icon">
    </head>
    <body>
    <center>
        <form action="ListFlight" method="post">
            <fieldset>
                <legend>Рейсы</legend>
                <table>
                    <%try {
                            for (int i = 0; i < currentModel.getSizeF(); i++) {

                                Flights currentFlight = currentModel.obtainFlight(i);
                                out.println("</td><td><label for=\"" + currentFlight.getId() + "\">  <table><tr><td> <strong style=\"color:#000\";>№: " + currentFlight.getId() + "</strong>");
                                out.println("</td><td><strong style=\"color:#000\";>Name: </strong>" + currentFlight.getName()
                                        + "</td></tr> </table> </label></td></tr>");

                            }
                        } catch (Exception e) {
                        }
                    %>

                    <br>
                    <center>
                        <input class="button" type="button" name="goback" id="ok" value="Вернуться назад" onclick="redirect('DateInputFlight.jsp')"/>
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
