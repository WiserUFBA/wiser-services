/* 
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.humidity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Humidity")
public class Humidity {
	private String percent; 

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

}
