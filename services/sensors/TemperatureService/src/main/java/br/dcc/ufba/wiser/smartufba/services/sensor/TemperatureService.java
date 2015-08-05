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

@Path("/devices/sensor/temperature")
public class TemperatureService {

    public TemperatureService() {
    }

    @GET
    @Produces("application/json")
    public Response getTemperature() throws Exception {
        Temperature t = new Temperature();
        t.setUnit("Celsius");
        
        DriverMQTT temp = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
        String degree = temp.getInfo("temp");
        
        t.setDegree(new Integer(degree));
        
        ResponseBuilder rb = Response.ok(t)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}
