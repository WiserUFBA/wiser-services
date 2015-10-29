package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Humidity")
public class Humidity {
    private int percent; 

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

}
