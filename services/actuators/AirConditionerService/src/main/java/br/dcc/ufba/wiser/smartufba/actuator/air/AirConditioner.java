/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.actuator.air;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AirConditioner")
public class AirConditioner{
    private boolean status;
    private String value;

    public AirConditioner(){}
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
