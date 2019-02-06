package Servlets;

import Model.Model;
import SessionBeans.DataBase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class AddFlight extends HttpServlet {
    @EJB
    DataBase db;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String date = request.getParameter("date");
            int max = db.getMaxIdFlights();
            int error = checkTaskData(id, name, date, max);
            if (error == 0) {
                db.addFlight(id, name, date);
                Model currentModel = db.getModelFlight();
                String strModel = db.xmlToString(currentModel);
                request.getSession().setAttribute("model", strModel);
                response.sendRedirect("Flights.jsp");
            } else {
                switch (error) {
                    case 1: {
                        String strError = "Поле не может быть пустым";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 2: {
                        String strError = "Введите корректный id";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 3: {
                        String strError = "Id не икременирован (Введите " + max + ")";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 4: {
                        String strError = "Введите корректную дату";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 5: {
                        String strError = "Запись уже существует";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 6: {
                        String strError = "Введите корректное название";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                }
                response.sendRedirect("AddFlight.jsp");

            }
        } catch (SQLException | ParserConfigurationException | SAXException | NamingException ex) {
            Logger.getLogger(AddFlight.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int checkTaskData(String id, String name, String date, int max) {
        if ("".equals(id) || "".equals(name) || "".equals(date)) {
            return 1;
        }
        if (isNumeric(id) == false) {
            return 2;
        }
        int st = Integer.parseInt(id);
        if (st < 0) {
            return 2;
        }
        if (max + 1 == st) {
            char[] charName = name.toCharArray();
            if (charName.length > 25) {
                return 6;
            }
            for (int i = 0; i < charName.length; i++) {
                if (charName[i] == '+' || charName[i] == '/' || charName[i] == '|') {
                    return 6;
                }
                if (charName[i] == '*' || charName[i] == '(' || charName[i] == ')' || charName[i] == '=') {
                    return 6;
                }
                if (charName[i] == '_' || charName[i] == '"' || charName[i] == ';' || charName[i] == ':') {
                    return 6;
                }
                if (charName[i] == '<' || charName[i] == '?' || charName[i] == '>' || charName[i] == '.') {
                    return 6;
                }
                if (charName[i] == ',' || charName[i] == '{' || charName[i] == '}' || charName[i] == '[') {
                    return 6;
                }
                if (charName[i] == ']' || charName[i] == '&' || charName[i] == '!' || charName[i] == '@') {
                    return 6;
                }
                if (charName[i] == '#' || charName[i] == '№' || charName[i] == '$' || charName[i] == '%') {
                    return 6;
                }
                if (charName[i] == '^' || charName[i] == '~' || charName[i] == '`' || charName[i] == '\\') {
                    return 6;
                }
                if (charName[i] == '\'') {
                    return 6;
                }
                if (isNumeric(String.valueOf(charName[i])) == true) {
                    return 6;
                }
            }

            char[] charDate = date.toCharArray();
            String str = "";
            String str1 = "";
            if (charDate.length > 10) {
                return 4;
            }
            for (int i = 0; i < charDate.length; i++) {
                if (i == 2) {
                    if (charDate[i] != '.') {
                        return 4;
                    }
                }
                if (i == 5) {
                    if (charDate[i] != '.') {
                        return 4;
                    }
                    str1 = "";
                }
                if (i < 2) {
                    if (isNumeric(String.valueOf(charDate[i])) == false) {
                        return 4;
                    }
                    str += String.valueOf(charDate[i]);
                    if (Integer.parseInt(str) > 31 || Integer.parseInt(str) < 0) {
                        return 4;
                    }
                }
                if (i > 2 & i < 5) {
                    if (isNumeric(String.valueOf(charDate[i])) == false) {
                        return 4;
                    }
                    str1 += String.valueOf(charDate[i]);
                    if (Integer.parseInt(str1) > 12 || Integer.parseInt(str) < 0) {
                        return 4;
                    }
                    if (Integer.parseInt(str) > 28 & Integer.parseInt(str1) == 2) {
                        return 4;
                    }
                    if (Integer.parseInt(str) > 30 & (Integer.parseInt(str1) == 4 || Integer.parseInt(str1) == 6 || Integer.parseInt(str1) == 9 || Integer.parseInt(str1) == 11)) {
                        return 4;
                    }
                }
                if (i > 5) {
                    if (isNumeric(String.valueOf(charDate[i])) == false) {
                        return 4;
                    }
                    str1 += String.valueOf(charDate[i]);
                    if (Integer.parseInt(str1) > 2019 || Integer.parseInt(str1) < 0) {
                        return 4;
                    }
                    if (i == 9 & Integer.parseInt(str1) < 2018) {
                        return 4;
                    }
                }
            }
            return 0;
        } else {
            return 3;
        }
    }

    private boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
