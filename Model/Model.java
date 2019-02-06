package Model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Model")
@XmlAccessorType(XmlAccessType.NONE)
public class Model implements Serializable, Cloneable {

    private static final long serialVersionUID = 6529685098267757690L;
    private Long id;
    private Flights currentFlight;
    private Passengers currentPassenger;
    private ArrayList<Flights> flightsArrayList;
    private ArrayList<Passengers> passengersArrayList;

    public void setFlightsArrayList(ArrayList<Flights> flightsArrayList) {
        this.flightsArrayList = flightsArrayList;
    }

    public void setPassengersArrayList(ArrayList<Passengers> passengersArrayList) {
        this.passengersArrayList = passengersArrayList;
    }

    @XmlElement(name = "flights")
    public ArrayList<Flights> getFlightsArrayList() {
        return flightsArrayList;
    }

    @XmlElementWrapper(name = "passengers")
    public ArrayList<Passengers> getPassengersArrayList() {
        return passengersArrayList;
    }

    public Flights CreateFlight(String id, String name, String date) {
        currentFlight = new Flights(id, name, date, passengersArrayList);
        return currentFlight;
    }

    public Passengers CreatePassenger(String id, String name, String surname, String age, String place, String key) {
        currentPassenger = new Passengers(id, name, surname, age, place, key);
        return currentPassenger;
    }

    public Model() {
        flightsArrayList = new ArrayList<Flights>();
        passengersArrayList = new ArrayList<Passengers>();

    }

    public void addDataFlights(Flights row) {
        flightsArrayList.add(row);
    }

    public void addDataPassengers(Passengers row) {
        passengersArrayList.add(row);
    }

    public void setFlights(Flights flights) {
        this.currentFlight = flights;
    }

    public void setPassangers(Passengers passangers) {
        this.currentPassenger = passangers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Model[ id=" + id + " ]";
    }

    public Flights getFlights() {
        return currentFlight;
    }

    public Passengers getPassengers() {
        return currentPassenger;
    }

    public Flights obtainFlight(int index) {
        return flightsArrayList.get(index);
    }

    public Passengers obtainPassenger(int index) {
        return passengersArrayList.get(index);
    }

    public Flights getFlight(int id) {
        for (int i = 0; i < flightsArrayList.size(); i++) {
            if (Integer.parseInt(flightsArrayList.get(i).getId()) == id) {
                return flightsArrayList.get(i);
            }
        }
        return null;
    }

    public Passengers getPassenger(int id) {
        for (int i = 0; i < passengersArrayList.size(); i++) {
            if (Integer.parseInt(passengersArrayList.get(i).getId()) == id) {
                return passengersArrayList.get(i);
            }
        }
        return null;
    }

    public int getSizeP() {
        return passengersArrayList.size();
    }

    public int getSizeF() {
        return flightsArrayList.size();
    }

}
