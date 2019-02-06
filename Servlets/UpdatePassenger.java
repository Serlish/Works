package Servlets;


import Model.Model;
import SessionBeans.DataBase;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

public class UpdatePassenger extends HttpServlet {

    @EJB
    DataBase db;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String age = request.getParameter("age");
            String place = request.getParameter("place");
            String key = request.getParameter("key");

            ArrayList<Integer> indx = db.getIndexesFlights();
            ArrayList<Integer> indxPs = db.getIndexesPassengers();
            int error = checkTaskData(id, name, surname, age, place, key, indx, indxPs);
            if (error == 0) {
                db.updatePassengers(id, name, surname, age, place, key);
                Model currentModel = db.getModelPassenger();
                String strModel = db.xmlToString(currentModel);
                request.getSession().setAttribute("model", strModel);
                response.sendRedirect("Passengers.jsp");
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
                        String strError = "Id не найден";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 4: {
                        String strError = "Место занято";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 5: {
                        String strError = "Запись существует";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 6: {
                        String strError = "Номер рейса должнен быть целым";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 7: {
                        String strError = "Введите корректный возраст";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 8: {
                        String strError = "Введите корректное место";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                    case 9: {
                        String strError = "Рейса не существует";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                }

                response.sendRedirect("UpdatePassenger.jsp");
            }
        } catch (SQLException | ParserConfigurationException | SAXException | NamingException ex) {
            Logger.getLogger(UpdatePassenger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int checkTaskData(String id, String name, String surname, String age, String place, String key, ArrayList<Integer> indx, ArrayList<Integer> indxPs) {
        if ("".equals(id) || "".equals(name) || "".equals(surname) || "".equals(age) || "".equals(place) || "".equals(key)) {
            return 1;
        }
        if (isNumeric(id) == false) {
            return 2;
        }
        if (isNumeric(age) == false) {
            return 7;
        }
        if (isNumeric(place) == false) {
            return 8;
        }
        if (isNumeric(key) == false) {
            return 6;
        }
        int st = Integer.parseInt(id);
        int ag = Integer.parseInt(age);
        int pl = Integer.parseInt(place);
        if (st < 0) {
            return 2;
        }
        int k = -1;
        for (int i = 0; i < indxPs.size(); i++) {
            if (indxPs.get(i) == st) {
                k = i;
                break;
            }
        }
        if (k != -1) {
            if (ag < 0 || ag > 120) {
                return 7;
            }
            if (pl < 0 || pl > 350) {
                return 8;
            }
            char[] charName = name.toCharArray();
            if (charName.length > 15) {
                return 6;
            }
            for (int i = 0; i < charName.length; i++) {
                if (charName[i] == '+' || charName[i] == '/' || charName[i] == '|' || charName[i] == '-') {
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
            char[] charSurname = surname.toCharArray();
            if (charName.length > 15) {
                return 6;
            }
            for (int i = 0; i < charSurname.length; i++) {
                if (charSurname[i] == '+' || charSurname[i] == '/' || charSurname[i] == '|' || charSurname[i] == '-') {
                    return 6;
                }
                if (charSurname[i] == '*' || charSurname[i] == '(' || charSurname[i] == ')' || charSurname[i] == '=') {
                    return 6;
                }
                if (charSurname[i] == '_' || charSurname[i] == '"' || charSurname[i] == ';' || charSurname[i] == ':') {
                    return 6;
                }
                if (charSurname[i] == '<' || charSurname[i] == '?' || charSurname[i] == '>' || charSurname[i] == '.') {
                    return 6;
                }
                if (charSurname[i] == ',' || charSurname[i] == '{' || charSurname[i] == '}' || charSurname[i] == '[') {
                    return 6;
                }
                if (charSurname[i] == ']' || charSurname[i] == '&' || charSurname[i] == '!' || charSurname[i] == '@') {
                    return 6;
                }
                if (charSurname[i] == '#' || charSurname[i] == '№' || charSurname[i] == '$' || charSurname[i] == '%') {
                    return 6;
                }
                if (charSurname[i] == '^' || charSurname[i] == '~' || charSurname[i] == '`' || charSurname[i] == '\\') {
                    return 6;
                }
                if (charSurname[i] == '\'') {
                    return 6;
                }
                if (isNumeric(String.valueOf(charSurname[i])) == true) {
                    return 6;
                }
            }

            int keyFlights = 0;
            for (int i = 0; i < indx.size(); i++) {
                if (indx.get(i) == Integer.parseInt(key)) {
                    keyFlights = 1;
                    break;
                }
            }
            if (keyFlights == 0) {
                return 9;
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
