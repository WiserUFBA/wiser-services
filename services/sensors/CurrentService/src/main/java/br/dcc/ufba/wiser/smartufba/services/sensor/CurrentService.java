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

@Path("/")
public class CurrentService {

    public CurrentService() {
    }

    @GET
    @Produces("application/json")
    public Response getAcumulatedCurrent() {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Current c = new Current();

        try{
            DriverMQTT current = new DriverMQTT("ufbaino01", "wiser", "wiser2014");
            float cc = current.getValue("currentSensor01");
            c.setAcumulatedCurrent(cc);

            rb = Response.ok(c);
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
