/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.actuator;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Path("/devices/actuator/camera")
public class CameraService {

    private final String cameraIp = "192.168.0.199:7777";
    private final HashMap<String, Integer> mapaStringCodigos = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
        {
            put("left", 4);
            put("right", 3);
            put("down", 2);
            put("up", 1);
        }
    };

    // Realiza um sleep por s segundos
    private void sleepFor(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
        }
    }

    @GET
    @Produces("*/*")
    public Response getSnapshot(@DefaultValue("none") @QueryParam("move") String direction) {
        Client client = ClientBuilder.newClient();
        Response response;

        if (mapaStringCodigos.containsKey(direction)) {
            client.target("http://" + cameraIp + "/media/?action=cmd&code=2&value=" + mapaStringCodigos.get(direction)).request().header("Authorization", "Basic YWRtaW46YWRtaW4=").get();
            sleepFor(300);
            client.target("http://" + cameraIp + "/media/?action=cmd&code=3&value=" + mapaStringCodigos.get(direction)).request().header("Authorization", "Basic YWRtaW46YWRtaW4=").get();
            sleepFor(2000);
            response = client.target("http://" + cameraIp + "/media/?action=snapshot").request().header("Authorization", "Basic YWRtaW46YWRtaW4=").get();

            return response;
        }

        return client.target("http://" + cameraIp + "/media/?action=snapshot").request().header("Authorization", "Basic YWRtaW46YWRtaW4=").get();
    }
}