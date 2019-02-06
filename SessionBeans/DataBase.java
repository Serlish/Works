
package SessionBeans;

import Model.Model;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJBObject;
import javax.ejb.Remote;
import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

@Remote
public interface DataBase {
    
    public int getMaxIdFlights() throws SQLException, NamingException;
    public int getMaxIdPassengers() throws SQLException, NamingException;
    public void deleteFlights(String flightId) throws IOException, SQLException, NamingException;
    public void updateFlights(String flightId, String name, String date) throws IOException, SQLException, NamingException;
    public void addFlight(String id, String name, String date) throws IOException, SQLException, NamingException;
    public Model getModelFlight() throws SQLException, NamingException, RemoteException;
    public Model getModelFlightDate(String dateF) throws SQLException, NamingException, RemoteException;
    public Model getModelPassenger() throws SQLException, NamingException, RemoteException;
    public Model getModelPassengerKey(String flights_id) throws SQLException, NamingException, RemoteException;
    public ArrayList<Integer> getIndexesFlights() throws SQLException, NamingException;
    public ArrayList<Integer> getIndexesPassengersKey() throws SQLException, NamingException;
    public ArrayList<Integer> getIndexesPassengers() throws SQLException, NamingException;
    public void deletePassengers(String Id) throws IOException, SQLException, NamingException;
    public void updatePassengers(String Id, String name, String surname, String age, String place, String key) throws IOException, SQLException, NamingException;
    public void addPassengers(String id, String name, String surname, String age, String place, String key) throws IOException, SQLException, NamingException;
    public String xmlToString(Model model) throws ParserConfigurationException, SAXException, IOException;
    
}
