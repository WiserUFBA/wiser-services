/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.move;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/devices/sensor/move")
public class MoveService {

    public MoveService() {
    }

    @GET
    @Produces("application/json")
    public Response getMove() {

        Move m = new Move();

        DriverMQTT movement = new DriverMQTT("rele-pres", "device", "boteco@wiser");
        String move = movement.getValue("pres1");
        
        m.setMove(new Integer(move));

        ResponseBuilder rb = Response.ok(m)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
        
    }
}
