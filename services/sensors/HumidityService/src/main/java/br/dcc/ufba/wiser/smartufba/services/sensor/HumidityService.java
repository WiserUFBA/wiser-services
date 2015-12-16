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

@Path("/devices/sensor/humidity")
public class HumidityService {

    public HumidityService() {
    }

    @GET
    @Produces("application/json")
    public Response getHumidity(){
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Humidity h = new Humidity();
        
        try{
            DriverMQTT humidity = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
            String percent = humidity.getInfo("humid");

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
}
