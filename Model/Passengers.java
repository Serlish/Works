
package Model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Passengers")
@XmlAccessorType(XmlAccessType.NONE)

public class Passengers implements Serializable {
    
    private static final long serialVersionUID = 8529685098267757690L;
    private String id;
    private String name;
    private String surname;
    private String age;
    private String place;
    private String key;

    public Passengers() {
        this.id = null;
        this.name = null;
        this.surname = null;
        this.age = null;
        this.place = null;
        this.key = null;
    }
    
    public Passengers(String id, String name, String surname, String age, String place, String key) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.place = place;
        this.key = key;

    }    
    @XmlElement(name = "idpassengers")
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    @XmlElement(name = "key")
    public String getKey() {
        return key;
    }
    @XmlElement(name = "namepassenger")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "surname")
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @XmlElement(name = "age")
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    @XmlElement(name = "place")
    public String getPlace() {
        return place;
    }
    
    public void setPlace(String place) {
        this.place = place;
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
        Passengers other = (Passengers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    


}
