/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Sound")
public class Sound {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int m) {
        this.value = m;
    }
}
