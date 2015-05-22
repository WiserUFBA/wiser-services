/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.actuator.air;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.smartufba.tatu.drivers.DriverMQTT;

@Path("/devices/actuator/air")
public class AirConditionerService {
    
    public AirConditionerService() {
    }

    @GET
    @Produces("application/json")
    public Response getStatusAirConditioner() throws Exception {
    	
    	AirConditioner a = new AirConditioner();
        
        DriverMQTT air = new DriverMQTT("nome", "device", "boteco@wiser");
        String status = air.getInfo("state");
        
        
        if(status.contentEquals("on")){ 
        	a.setStatus(true);
        }else{
        	a.setStatus(false);
        }
    	
        ResponseBuilder rb = Response.ok(a)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
 
    @POST
    @Produces("application/json")
    public Response setStatusAirConditioner(@FormParam("status") boolean status) throws Exception {
    	AirConditioner a = new AirConditioner();    	
    	a.setStatus(status);
    	
        DriverMQTT air = new DriverMQTT("temp-air", "device", "boteco@wiser"); 
        
        air.setInfo("air", a.getStatus() ? 1 : 0);      
        
        ResponseBuilder rb = Response.ok(a)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}
