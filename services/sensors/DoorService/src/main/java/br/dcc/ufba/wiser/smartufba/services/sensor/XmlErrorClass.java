/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is used to return an error to the user
 * You should include this on every service you create.
 * 
 * @author jeferson
 */
@XmlRootElement(name = "Error")
public class XmlErrorClass {
    private boolean status; 

    public XmlErrorClass(boolean status){
        this.status = status;
    }
    
    public XmlErrorClass(){
        this(false);
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
