/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Door")
public class Door {
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean s) {
        this.status = s;
    }
}
