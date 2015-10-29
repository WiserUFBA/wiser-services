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
    public Response getStateAirConditioner() throws Exception {
    	
    	AirConditioner a = new AirConditioner();
        
        DriverMQTT air = new DriverMQTT("air", "device", "boteco@wiser");
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
    	
        DriverMQTT air = new DriverMQTT("air", "device", "boteco@wiser"); 
        
        air.setInfo("value", Integer.parseInt(value));
        
        a.setStatus(Integer.parseInt(value) > 0 ? true : false);
        
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
    
    @POST
    @Produces("application/json")
    public Response autoSetValueAirConditioner(@FormParam("city_temp") String city_temp,
    		                                   @FormParam("city_humd") String city_humd,
    		                                   @FormParam("local_temp") String local_temp,
    		                                   @FormParam("local_humd") String local_humd) throws Exception {
    	
    	int city_t = Integer.parseInt(city_temp);
    	int city_h = Integer.parseInt(city_humd);
    	int local_t = Integer.parseInt(local_temp);
    	int local_h = Integer.parseInt(local_humd);
    	int temp_value = new Float(31 - ((local_t*0.8 + local_h*0.2)*0.7+(city_t*0.8 + city_h*0.2)*0.3)*2.3).intValue();
    	
    	AirConditioner a = new AirConditioner();
    	a.setStatus(true);
    	a.setValue(String.valueOf(temp_value));
    	
        DriverMQTT air = new DriverMQTT("air", "device", "boteco@wiser");        
        air.setInfo("value", temp_value );        
        a.setStatus(temp_value > 0 ? true : false);
        
        ResponseBuilder rb = Response.ok(a)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
    

    
}
