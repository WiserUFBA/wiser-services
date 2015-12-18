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

@Path("/devices/sensor/door")
public class DoorService {

    public DoorService() {
    }

    @GET
    @Produces("application/json")
    public Response getDoor() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Door d = new Door();

        DriverMQTT door = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
        
        try {
            DriverMQTT lamp = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
            String status = lamp.getInfo("door");

            if (status.contentEquals("ON")) {
                d.setStatus(true);
            } else {
                d.setStatus(false);
            }
            rb = Response.ok(d);
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
