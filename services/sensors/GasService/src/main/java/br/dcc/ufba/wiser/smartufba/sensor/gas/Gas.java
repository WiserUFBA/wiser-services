/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.gas;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Gas")
public class Gas {
    private int rate;


    public int getRate() {
        return rate;
    }

    public void setRate(int r) {
        this.rate = r;
    }
}