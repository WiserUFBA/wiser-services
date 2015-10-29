package br.dcc.ufba.wiser.smartufba.services.sensor;

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