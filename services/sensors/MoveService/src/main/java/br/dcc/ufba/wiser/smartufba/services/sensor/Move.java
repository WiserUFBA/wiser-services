/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Move")
public class Move {
    private int movements;

    public int getMove() {
        return movements;
    }

    public void setMove(int m) {
        this.movements = m;
    }
}
