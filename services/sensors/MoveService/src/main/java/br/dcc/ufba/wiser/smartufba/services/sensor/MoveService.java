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
    public Response getMove() throws Exception {
        Move m = new Move();

        DriverMQTT movement = new DriverMQTT("rele-pres", "device", "boteco@wiser");
        int move = movement.getValue("pres1");
        
        m.setMove(move);

        ResponseBuilder rb = Response.ok(m)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
        
    }
}
