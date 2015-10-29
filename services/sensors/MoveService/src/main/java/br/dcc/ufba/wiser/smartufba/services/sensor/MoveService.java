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

@Path("/devices/sensor/move")
public class MoveService {

    public MoveService() {
    }

    @GET
    @Produces("application/json")
    public Response getMove() {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Move m = new Move();

        try{
            DriverMQTT movement = new DriverMQTT("rele-pres", "device", "boteco@wiser");
            int move = movement.getValue("pres1");
            m.setMove(move);

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
}
