package DAO;

import java.io.*;
import Model.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class DAO {
    private static DAO instance = null;
    
    private DAO() {

    }
        public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }
        return instance;
    }


    public int getMaxIdFlights() throws SQLException, NamingException {
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        String maxIdSQL = "SELECT MAX(id) FROM FLIGHTS";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(maxIdSQL);
        ResultSet rsMax = preparedStatement.executeQuery();
        rsMax.next();
        int max = rsMax.getInt("MAX(id)");
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
        return max;
    }

    public int getMaxIdPassengers() throws SQLException, NamingException {
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        String maxIdSQL = "SELECT MAX(id) FROM PASSENGERS";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(maxIdSQL);
        ResultSet rsMax = preparedStatement.executeQuery();
        rsMax.next();
        int max = rsMax.getInt("MAX(id)");
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
        return max;
    }

    public void deleteFlights(String flightId) throws IOException, SQLException, NamingException {

        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        String deleteSQL = "DELETE FROM FLIGHTS WHERE ID = ?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, Integer.parseInt(flightId));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
    }

    public void updateFlights(String flightId, String name, String date) throws IOException, SQLException, NamingException {

        String updateTableSQL = "UPDATE FLIGHTS SET NAME= ?, DATE = ? WHERE id = ?";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, date);
        preparedStatement.setInt(3, Integer.parseInt(flightId));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();

    }

    public void addFlight(String id, String name, String date) throws IOException, SQLException, NamingException {
        String insertTableSQL = "INSERT INTO FLIGHTS"
                + "(NAME, DATE) VALUES" + "(?,?)";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, date);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
    }

    public Model getModelFlight() throws SQLException, NamingException {
        String selectSQL = "SELECT * FROM FLIGHTS";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
        Model currentModel = new Model();
        ResultSet rsModel = preparedStatement.executeQuery();
        while (rsModel.next()) {
            String id = String.valueOf(rsModel.getInt("id"));
            String name = rsModel.getString("NAME");
            String date = rsModel.getString("DATE");
            currentModel.addDataFlights(currentModel.CreateFlight(id, name, date));
        }
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
        return currentModel;
    }

    public Model getModelFlightDate(String dateF) throws SQLException, NamingException {
        String selectSQL = "SELECT * FROM FLIGHTS WHERE DATE = ?";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
        preparedStatement.setString(1, dateF);
        preparedStatement.execute();
        Model currentModel = new Model();
        ResultSet rsModel = preparedStatement.executeQuery();
        while (rsModel.next()) {
            String id = String.valueOf(rsModel.getInt("id"));
            String name = rsModel.getString("NAME");
            String date = rsModel.getString("DATE");
            currentModel.addDataFlights(currentModel.CreateFlight(id, name, date));
        }
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
        return currentModel;
    }

    public Model getModelPassenger() throws SQLException, NamingException {
        String selectSQL = "SELECT * FROM PASSENGERS";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
        Model currentModel = new Model();
        ResultSet rsModel = preparedStatement.executeQuery();
        while (rsModel.next()) {
            String id = String.valueOf(rsModel.getInt("id"));
            String name = rsModel.getString("NAME");
            String surname = rsModel.getString("SURNAME");
            String age = String.valueOf(rsModel.getInt("AGE"));
            String place = String.valueOf(rsModel.getInt("PLACE"));
            String key = String.valueOf(rsModel.getInt("FLIGHTS_ID"));
            currentModel.addDataPassengers(currentModel.CreatePassenger(id, name, surname, age, place, key));
        }
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
        return currentModel;
    }

    public Model getModelPassengerKey(String flights_id) throws SQLException, NamingException {
        String selectSQL = "SELECT * FROM PASSENGERS WHERE FLIGHTS_ID = ?";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL);
        preparedStatement.setInt(1, Integer.parseInt(flights_id));
        preparedStatement.execute();
        Model currentModel = new Model();
        ResultSet rsModel = preparedStatement.executeQuery();
        while (rsModel.next()) {
            String id = String.valueOf(rsModel.getInt("id"));
            String name = rsModel.getString("NAME");
            String surname = rsModel.getString("SURNAME");
            String age = String.valueOf(rsModel.getInt("AGE"));
            String place = String.valueOf(rsModel.getInt("PLACE"));
            String key = String.valueOf(rsModel.getInt("FLIGHTS_ID"));
            currentModel.addDataPassengers(currentModel.CreatePassenger(id, name, surname, age, place, key));
        }
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
        return currentModel;
    }

    public ArrayList<Integer> getIndexesFlights() throws SQLException, NamingException {
        String selectSQLF = "SELECT * FROM FLIGHTS";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatementF = dbConnection.prepareStatement(selectSQLF);
        ResultSet rsFlights = preparedStatementF.executeQuery();
        ArrayList<Integer> intIdFlights = new ArrayList<Integer>();
        while (rsFlights.next()) {
            int flightId = rsFlights.getInt("id");
            intIdFlights.add(flightId);
        }
        preparedStatementF.close();
        ConnectionPool.getInstance().disconnect();
        return intIdFlights;
    }

    public ArrayList<Integer> getIndexesPassengersKey() throws SQLException, NamingException {
        String selectSQLF = "SELECT * FROM PASSENGERS";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatementF = dbConnection.prepareStatement(selectSQLF);
        ResultSet rsKey = preparedStatementF.executeQuery();
        ArrayList<Integer> intKeyPassengers = new ArrayList<Integer>();
        while (rsKey.next()) {
            int key = rsKey.getInt("FLIGHTS_ID");
            intKeyPassengers.add(key);
        }
        preparedStatementF.close();
        ConnectionPool.getInstance().disconnect();
        return intKeyPassengers;
    }

    public ArrayList<Integer> getIndexesPassengers() throws SQLException, NamingException {
        String selectSQLF = "SELECT * FROM PASSENGERS";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatementF = dbConnection.prepareStatement(selectSQLF);
        ResultSet rsKey = preparedStatementF.executeQuery();
        ArrayList<Integer> intPassengers = new ArrayList<Integer>();
        while (rsKey.next()) {
            int id = rsKey.getInt("id");
            intPassengers.add(id);
        }
        preparedStatementF.close();
        ConnectionPool.getInstance().disconnect();
        return intPassengers;
    }

    public void deletePassengers(String Id) throws IOException, SQLException, NamingException {
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        String deleteSQL = "DELETE FROM PASSENGERS WHERE ID = ?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, Integer.parseInt(Id));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
    }

    public void updatePassengers(String Id, String name, String surname, String age, String place, String key) throws IOException, SQLException, NamingException {

        String updateTableSQL = "UPDATE PASSENGERS SET NAME = ?, SURNAME = ?, AGE = ?, PLACE = ?, FLIGHTS_ID = ? WHERE id = ?";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(updateTableSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, age);
        preparedStatement.setString(4, place);
        preparedStatement.setInt(5, Integer.parseInt(key));
        preparedStatement.setInt(6, Integer.parseInt(Id));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();

    }

    public void addPassengers(String id, String name, String surname, String age, String place, String key) throws IOException, SQLException, NamingException {
        String insertTableSQL = "INSERT INTO Passengers"
                + "(NAME, SURNAME, AGE, PLACE, FLIGHTS_ID) VALUES" + "(?,?,?,?,?)";
        Connection dbConnection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, age);
        preparedStatement.setString(4, place);
        preparedStatement.setInt(5, Integer.parseInt(key));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        ConnectionPool.getInstance().disconnect();
    }


        public String xmlToString(Model model) throws ParserConfigurationException, SAXException, IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(Model.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(model, sw);
            String sx = sw.toString();
            return sx;
        } catch (JAXBException e) {
        }
        return null;
    }

}
