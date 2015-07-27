/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.move;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Move")
public class Move {
    private int movements;

    public int getMove() {
        return movements;
    }

    public void setMove(String m) {
        this.movements = m;
    }
}
