/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Motion")
public class Motion {
    private int time;

    public int getTimeofLastMotion() {
        return time;
    }

    public void setTimeofLastMotion(int m) {
        this.time = m;
    }
}
