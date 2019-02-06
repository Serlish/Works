
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Flights")
@XmlAccessorType(XmlAccessType.NONE)

public class Flights   implements Serializable {
    
    private static final long serialVersionUID = 7529685098267757690L;
    private String id;
    private String name;
    private String date;
    private ArrayList<Passengers> passengersArrayList;
    
    public Flights() {
        this.id = null;
        this.name = null;
        this.date = null;
        this.passengersArrayList = new ArrayList();
    }

    public Flights(String id, String name, String date, ArrayList<Passengers> passengerArrayList) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.passengersArrayList = passengerArrayList;
    }
    @XmlElement(name = "idflight")
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    @XmlElement(name = "nameflight")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "date")
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Flights)) {
            return false;
        }
        Flights other = (Flights) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
