/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.smartufba.tatu.drivers.*;

@Path("/devices/sensor/gas")
public class GasService {

    public GasService() {
    }

    @GET
    @Produces("application/json")
    public Response getGas() throws Exception {
        Gas g = new Gas();
        
        DriverMQTT gas = new DriverMQTT("rele-pres", "device", "boteco@wiser");
        String rate = "" + gas.getValue("gas");
        
        g.setRate(new Integer(rate));
        
        ResponseBuilder rb = Response.ok(g)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}