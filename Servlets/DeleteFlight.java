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


public class DeleteFlight extends HttpServlet {

    @EJB
    DataBase db;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");

            ArrayList<Integer> indx = db.getIndexesFlights();
            ArrayList<Integer> indxKey = db.getIndexesPassengersKey();
            int error = checkTaskData(id, indx, indxKey);
            if(error == 0){
                db.deleteFlights(id);
                Model currentModel = db.getModelFlight();
                String strModel = db.xmlToString(currentModel);
                request.getSession().setAttribute("model", strModel);
                response.sendRedirect("Flights.jsp");
            }
            else{
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
                        String strError = "К рейсу прикреплены пассажиры";
                        request.getSession().setAttribute("error", strError);
                        break;
                    }
                }              
                response.sendRedirect("DeleteFlight.jsp");
            }
        } catch (SQLException | ParserConfigurationException | SAXException | NamingException ex) {
            Logger.getLogger(DeleteFlight.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    private int checkTaskData(String id,  ArrayList<Integer> indx, ArrayList<Integer> indxKey){

        if ("".equals(id)) {
            return 1;
        }
        if (isNumeric(id) == false) {
            return 2;
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
        for (int i = 0; i < indxKey.size(); i++) {
            if (indxKey.get(i) == st) {
                return 4;
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
