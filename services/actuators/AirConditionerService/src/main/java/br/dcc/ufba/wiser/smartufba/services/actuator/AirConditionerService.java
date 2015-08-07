/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.actuator;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import br.ufba.dcc.wiser.smartufba.tatu.drivers.DriverMQTT;

@Path("/devices/actuator/air")
public class AirConditionerService {

    public AirConditionerService() {
    }

    @GET
    @Produces("application/json")
    public Response getStateAirConditioner() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        AirConditioner a = new AirConditioner();

        try {
            DriverMQTT air = new DriverMQTT("air-conditioner", "device", "boteco@wiser");
            String status = air.getInfo("status");

            if (status.contentEquals("on")) {
                a.setStatus(true);
            } else {
                a.setStatus(false);
            }
            String value = air.getInfo("value");
            a.setValue(value);

            rb = Response.ok(a);
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

    @POST
    @Produces("application/json")
    public Response setValueAirConditioner(@FormParam("value") String value) throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        AirConditioner a = new AirConditioner();
        a.setValue(value);

        try {
            DriverMQTT air = new DriverMQTT("air-conditioner", "device", "boteco@wiser");

            air.setInfo("value", value);
            a.setStatus((Integer.parseInt(value) > 0));

            rb = Response.ok(a);
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

    public void turnOffAirConditioner() throws Exception {
        setValueAirConditioner("0");
    }
}
