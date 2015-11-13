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
public class LampService {
    
    public LampService() {
    }

    @GET
    @Produces("application/json")
    @Path("/rele-pres")
    public Response getStatusLamp2(){
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
    	Lamp l = new Lamp();
        
        try{
            DriverMQTT lamp = new DriverMQTT("rele-pres", "device", "boteco@wiser");
            String status = lamp.getInfo("lamp");

            if(status.contentEquals("ON")) 
                l.setStatus(true);
            else
                l.setStatus(false);

            rb = Response.ok(l);
        } catch (Exception e) {
            x.setStatus(true);
            rb = Response.ok(x);
        }
            
        return  rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
 
    @GET
    @Produces("application/json")
    @Path("/temp-lamp")
    public Response getStatusLamp3(){
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
    	Lamp l = new Lamp();
        
        try{
            DriverMQTT lamp = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
            String status = lamp.getInfo("lamp");

            if(status.contentEquals("ON")) 
                l.setStatus(true);
            else
                l.setStatus(false);

            rb = Response.ok(l);
        } catch (Exception e) {
            x.setStatus(true);
            rb = Response.ok(x);
        }
            
        return  rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/rele-pres")
    public Response setStatusLamp2(@FormParam("status") boolean status) {
    	ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Lamp l = new Lamp();    	
    	l.setStatus(status);
    	
        try{
            DriverMQTT lamp = new DriverMQTT("rele-pres", "device", "boteco@wiser");
            lamp.setInfo("lamp", (l.getStatus() ? "T" : "F"));      

            rb = Response.ok(l);
        } catch (Exception e) {
            x.setStatus(true);
            rb = Response.ok(x);
        }
        
        return  rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/temp-lamp")
    public Response setStatusLamp3(@FormParam("status") boolean status) {
    	ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Lamp l = new Lamp();    	
    	l.setStatus(status);
    	
        try{
            DriverMQTT lamp = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
            lamp.setInfo("lamp", l.getStatus() ? "T" : "F");      

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
