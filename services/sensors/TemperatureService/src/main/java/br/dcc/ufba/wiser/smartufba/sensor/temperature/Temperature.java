/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.temperature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Temperature")
public class Temperature {
	private String unit; 
    private int degree;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int d) {
        this.degree = d;
    }
}
