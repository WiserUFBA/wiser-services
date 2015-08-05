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

@Path("/devices/actuator/air")
public class AirConditionerService {
    
    public AirConditionerService() {
    }

    @GET
    @Produces("application/json")
    public Response getStateAirConditioner() throws Exception {
    	AirConditioner a = new AirConditioner();
        
        DriverMQTT air = new DriverMQTT("air-conditioner", "device", "boteco@wiser");
        String status = air.getInfo("status");
        
        if(status.contentEquals("on")){ 
        	a.setStatus(true);
        }else{
        	a.setStatus(false);
        }
        String value = air.getInfo("value");        
        a.setValue(value);
        
        ResponseBuilder rb = Response.ok(a)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
 
    @POST
    @Produces("application/json")
    public Response setValueAirConditioner(@FormParam("value") String value) throws Exception {
    	AirConditioner a = new AirConditioner();    	
    	a.setValue(value);
    	
        DriverMQTT air = new DriverMQTT("air-conditioner", "device", "boteco@wiser"); 
        
        air.setInfo("value", value);
        
        a.setStatus((Integer.parseInt(value) > 0));
        
        ResponseBuilder rb = Response.ok(a)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
    
    public void turnOffAirConditioner() throws Exception{
    	setValueAirConditioner("0");
    }
}
