/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.humidity;

import java.io.IOException;

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
    public Response getHumidity() throws Exception {
        Humidity h = new Humidity();
        
        DriverMQTT humidity = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
        String percent = humidity.getInfo("ar");
        
        h.setPercent(percent);
        
        ResponseBuilder rb = Response.ok(h)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
        
    }
}
