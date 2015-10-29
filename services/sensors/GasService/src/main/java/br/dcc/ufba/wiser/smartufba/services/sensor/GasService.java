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
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Gas g = new Gas();
        
        try{
            DriverMQTT gas = new DriverMQTT("rele-pres", "device", "boteco@wiser");
            String rate = "" + gas.getValue("gas");

            g.setRate(new Integer(rate));

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
}