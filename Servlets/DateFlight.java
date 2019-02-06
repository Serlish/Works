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

public class DateFlight extends HttpServlet {
    
    @EJB
    DataBase db;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dateF = request.getParameter("date");

            int error = checkTaskData(dateF);
            if (error == 0) {
                Model currentModel = db.getModelFlightDate(dateF);
                String strModel = db.xmlToString(currentModel);
                request.getSession().setAttribute("model", strModel);
                response.sendRedirect("DateFlight.jsp");
            } else {
                if (error == 4) {
                    String strError = "Введите корректную дату";
                    request.getSession().setAttribute("error", strError);
                } else {
                    String strError = "Поле не может быть пустым";
                    request.getSession().setAttribute("error", strError);
                }
                response.sendRedirect("DateInputFlight.jsp");
            }

        } catch (SQLException | ParserConfigurationException | SAXException | NamingException ex) {
            Logger.getLogger(DateFlight.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int checkTaskData(String date) {
        if ("".equals(date)) {
            return 1;
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
