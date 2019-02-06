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

public class KeyPassenger extends HttpServlet {

    @EJB
    DataBase db;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("key");

            ArrayList<Integer> indx = db.getIndexesPassengersKey();
            int error = checkTaskData(id, indx);
            if (error == 0) {
                Model currentModel = db.getModelPassengerKey(id);
                String strModel = db.xmlToString(currentModel);
                request.getSession().setAttribute("model", strModel);
                response.sendRedirect("KeyPassenger.jsp");
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
                }              
                response.sendRedirect("KeyInputPassenger.jsp");
            }
        } catch (SQLException | ParserConfigurationException | SAXException | NamingException ex) {
            Logger.getLogger(KeyPassenger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int checkTaskData(String id, ArrayList<Integer> indx) {

        if (isNumeric(id) == false) {
            return 2;
        }
        if ("".equals(id)) {
            return 1;
        }
        int st = Integer.parseInt(id);
        if (st < 0) {
            return 2;
        }
        int k = -1;
        for (int i = 0; i < indx.size(); i++) {
            if (indx.get(i) == st) {
                k = i;
                break;
            }
        }
        if (k != -1) {
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
