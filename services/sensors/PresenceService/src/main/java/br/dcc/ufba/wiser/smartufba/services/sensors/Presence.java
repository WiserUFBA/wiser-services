/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensors;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Presence")
public class Presence {
    private long id;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String d) {
        this.status = d;
    }
}
