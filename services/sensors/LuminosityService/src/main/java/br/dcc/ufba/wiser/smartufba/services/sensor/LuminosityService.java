/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.smartufba.tatu.drivers.DriverMQTT;

@Path("/")

public class LuminosityService {
	

    public LuminosityService() {
    }

    @GET
    @Produces("application/json")
    public Response getTemperature() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Luminosity l = new Luminosity();      
        try{
            DriverMQTT driver = new DriverMQTT("ufbaino01", "wiser", "wiser2014");
            String percent = driver.getInfo("luminositySensor");
        	
            l.setPercent(new Integer(percent));

            rb = Response.ok(l);
        } catch (Exception e) {
            x.setStatus(true);
            rb = Response.ok(x);
        }
        
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
    
}
