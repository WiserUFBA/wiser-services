/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Current")
public class Current {
    private float current;

    public float getAcumulatedCurrent() {
        return this.current;
    }

    public void setAcumulatedCurrent(float m) {
        this.current = m;
    }
}
