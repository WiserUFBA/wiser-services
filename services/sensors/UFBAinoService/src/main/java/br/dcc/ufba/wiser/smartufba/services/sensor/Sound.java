package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Sound")
public class Sound {
    private int number;


    public int getNumber() {
        return number;
    }

    public void setNumber(int n) {
        this.number = n;
    }
}
