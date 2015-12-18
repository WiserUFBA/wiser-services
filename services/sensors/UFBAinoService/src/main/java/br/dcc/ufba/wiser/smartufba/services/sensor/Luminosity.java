package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Luminosity")

public class Luminosity {
	private int percent;
	
	public int getPercent() {
        return this.percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

}
