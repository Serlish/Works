package SessionBeans;

import javax.ejb.Stateless;
import DAO.DAO;
import Model.Model;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.rmi.RemoteException;
import javax.ejb.EJBHome;

@Stateless
public class DBSessionBean implements DataBase {

  
    @Override
    public int getMaxIdFlights() throws SQLException, NamingException{
        return DAO.getInstance().getMaxIdFlights();
    }
    
    @Override
    public int getMaxIdPassengers() throws SQLException, NamingException{
        return DAO.getInstance().getMaxIdPassengers();
    }
    @Override
    public void deleteFlights(String flightId) throws IOException, SQLException, NamingException{
        DAO.getInstance().deleteFlights(flightId);
    }
    @Override
    public void updateFlights(String flightId, String name, String date) throws IOException, SQLException, NamingException {
        DAO.getInstance().updateFlights(flightId, name, date);
    }
    @Override
    public void addFlight(String id, String name, String date) throws IOException, SQLException, NamingException {
        DAO.getInstance().addFlight(id, name, date);
    }
    
    @Override
    public Model getModelFlight() throws SQLException, NamingException {
       return DAO.getInstance().getModelFlight();
    }
    @Override
    public Model getModelFlightDate(String dateF) throws SQLException, NamingException {
        return DAO.getInstance().getModelFlightDate(dateF);
    }
    @Override
    public Model getModelPassenger() throws SQLException, NamingException {
        return DAO.getInstance().getModelPassenger();
    }
    
    @Override
    public Model getModelPassengerKey(String flights_id) throws SQLException, NamingException {
        return DAO.getInstance().getModelPassengerKey(flights_id);
    }

    @Override
    public ArrayList<Integer> getIndexesFlights() throws SQLException, NamingException {
        return DAO.getInstance().getIndexesFlights();
    }
    @Override
    public ArrayList<Integer> getIndexesPassengersKey() throws SQLException, NamingException {
        return DAO.getInstance().getIndexesPassengersKey();
    }
    @Override
    public ArrayList<Integer> getIndexesPassengers() throws SQLException, NamingException {
        return DAO.getInstance().getIndexesPassengers();
    }
    
    public void deletePassengers(String Id) throws IOException, SQLException, NamingException {
        DAO.getInstance().deletePassengers(Id);
    }
    
    @Override
    public void updatePassengers(String Id, String name, String surname, String age, String place, String key) throws IOException, SQLException, NamingException {
        DAO.getInstance().updatePassengers(Id, name, surname, age, place, key);
    }
    
    @Override
    public void addPassengers(String id, String name, String surname, String age, String place, String key) throws IOException, SQLException, NamingException {
        DAO.getInstance().addPassengers(id, name, surname, age, place, key);
    }
    
    @Override
    public String xmlToString(Model model) throws ParserConfigurationException, SAXException, IOException {
        return DAO.getInstance().xmlToString(model);
    }

    
}
