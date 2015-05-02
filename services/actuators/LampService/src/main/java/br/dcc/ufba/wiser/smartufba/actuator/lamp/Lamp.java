/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.actuator.lamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Lamp")
public class Lamp {
	private boolean status; 

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
