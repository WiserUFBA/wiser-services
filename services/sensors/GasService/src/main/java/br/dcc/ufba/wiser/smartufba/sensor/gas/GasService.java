/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.sensor.gas;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.smartufba.tatu.drivers.DriverMQTT;

@Path("/devices/sensor/gas")
public class GasService {

    public GasService() {
    }


    @GET
    @Produces("application/json")
    @Path("window")
    public Response getGasWindow() throws Exception {
        Gas g = new Gas();
        
        DriverMQTT gas = new DriverMQTT("window", "device", "boteco@wiser");
        int rate = gas.getValue("gas");
        
        g.setRate(rate);        
        ResponseBuilder rb = Response.ok(g)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
    
    @GET
    @Produces("application/json")
    @Path("door")
    public Response getGasDoor() throws Exception {
        Gas g = new Gas();
        
        DriverMQTT gas = new DriverMQTT("temp-lamp", "device", "boteco@wiser");
    	int rate = gas.getValue("gas");
        
        g.setRate(rate);
        
        ResponseBuilder rb = Response.ok(g)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
    
    @GET
    @Produces("application/json")
    @Path("back")
    public Response getGasBack() throws Exception {
        Gas g = new Gas();
        
        DriverMQTT gas = new DriverMQTT("rele-pres", "device", "boteco@wiser");
        int rate = gas.getValue("gas");
        
        g.setRate(rate);
        
        ResponseBuilder rb = Response.ok(g)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
    }
}