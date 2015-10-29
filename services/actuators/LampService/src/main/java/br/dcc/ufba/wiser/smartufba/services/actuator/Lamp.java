/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.actuator;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Lamp")
public class Lamp {
    private boolean status; 

    public Lamp(){
        status = false;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
