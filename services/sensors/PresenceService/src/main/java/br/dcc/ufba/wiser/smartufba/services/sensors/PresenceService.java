/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.sensors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/devices/sensor/presence")
public class PresenceService {

    public PresenceService() {
    }

    @GET
    @Produces("application/json")
    public Response getPresence() {
        ResponseBuilder rb;
        long idNumber = Long.parseLong("1");
        Presence c = new Presence();
        c.setId(idNumber);
        c.setStatus("true");
        
        rb = Response.ok(c);
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
        
    }
}
