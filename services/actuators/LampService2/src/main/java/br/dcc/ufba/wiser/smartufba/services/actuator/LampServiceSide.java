/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.actuator;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.smartufba.tatu.drivers.DriverMQTT;

@Path("/devices/actuator/lamp")
public class LampServiceSide {
    
    public LampServiceSide() {
    }

    @GET
    @Produces("application/json")
    public Response getStatusLamp() throws Exception {
    	
    	LampSide l = new LampSide();
        
        DriverMQTT lamp = new DriverMQTT("rele-pres", "device", "boteco@wiser");
        String status = lamp.getInfo("lamp");
        
        
        if(status.contentEquals("ON")){ 
        	l.setStatus(true);
        }else{
        	l.setStatus(false);
        }
    	
        ResponseBuilder rb = Response.ok(l)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
 
    @POST
    @Produces("application/json")
    public Response setStatusLamp(@FormParam("status") boolean status) throws Exception {
    	LampSide l = new LampSide();    	
    	l.setStatus(status);
    	
        DriverMQTT lamp = new DriverMQTT("rele-pres", "device", "boteco@wiser"); 
        
        lamp.setInfo("lamp", ""+(l.getStatus() ? 1 : 0));      
        
        ResponseBuilder rb = Response.ok(l)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}
