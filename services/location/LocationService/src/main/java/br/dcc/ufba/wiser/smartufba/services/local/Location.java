/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.local;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "Location")
public class Location{
    private Long id;
    private String location;
    private Float  latitude;
    private Float  longitude;

    public Location(Long id, String location) {
        this.id = id;
        this.location = location;
    }

    public Location(Long id, String location, Float latitude, Float longitude) {
        this.id = id;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String location) {
        this.location = location;
    }

    public Location(){
    }
    
    public Location(Float latitude, Float longitude)
    {
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
