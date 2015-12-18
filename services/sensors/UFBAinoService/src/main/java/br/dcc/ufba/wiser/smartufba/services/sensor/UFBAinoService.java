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

@Path("/sensor")
public class UFBAinoService {

    public UFBAinoService() {
    }
    
    @GET
    @Produces("application/json")
    @Path("/temperature")
    public Response getTemperature() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Temperature t = new Temperature();
        t.setUnit("Celsius");
        
        try{
            DriverMQTT temp = new DriverMQTT("ufbaino", "device", "boteco@wiser");
            int degree = temp.getValue("temp");

            t.setDegree(degree);

            rb = Response.ok(t);
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
    
    @GET
    @Produces("application/json")
    @Path("/humidity")
    public Response getHumidity() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Humidity h = new Humidity();
        
        try{
            DriverMQTT humid = new DriverMQTT("ufbaino", "device", "boteco@wiser");
            int percent = humid.getValue("humid");

            h.setPercent(percent);

            rb = Response.ok(h);
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
    
    @GET
    @Produces("application/json")
    @Path("/gas")
    public Response getGas() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Gas g = new Gas();
        
        try{
            DriverMQTT gas = new DriverMQTT("ufbaino", "device", "boteco@wiser");
            int rate = gas.getValue("gas");

            g.setRate(rate);

            rb = Response.ok(g);
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
    
    @GET
    @Produces("application/json")
    @Path("/luminosity")
    public Response getLuminosity() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Luminosity l = new Luminosity();
        
        try{
            DriverMQTT luminosity = new DriverMQTT("ufbaino", "device", "boteco@wiser");
            int percent = luminosity.getValue("luminosity");

            l.setPercent(percent);

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
    
    @GET
    @Produces("application/json")
    @Path("/movement")
    public Response getMovement() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Movement m = new Movement();
        
        try{
            DriverMQTT move = new DriverMQTT("ufbaino", "device", "boteco@wiser");
            int number = move.getValue("move");

            m.setNumber(number);

            rb = Response.ok(m);
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
    
    @GET
    @Produces("application/json")
    @Path("/sound")
    public Response getSound() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Sound s = new Sound();
        
        try{
            DriverMQTT sound = new DriverMQTT("ufbaino", "device", "boteco@wiser");
            int number = sound.getValue("move");

            s.setNumber(number);

            rb = Response.ok(s);
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
