/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.actuator.lamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Camera")
public class Camera {
    private boolean status; 

    public Camera() {
        status = false;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
