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

public class ListFlight extends HttpServlet {

    @EJB
    DataBase db;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Model currentModel = db.getModelFlight();
            String strModel = db.xmlToString(currentModel);
            request.getSession().setAttribute("model", strModel);
            response.sendRedirect("Flights.jsp");
        } catch (SQLException | ParserConfigurationException | SAXException | NamingException ex) {
            Logger.getLogger(ListFlight.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
