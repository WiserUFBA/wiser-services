/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.actuator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
//import org.apache.commons.codec.binary.Base64;

@Path("/devices/actuator/camera")
public class CameraService {
    public static int START_MOVE_COMMAND = 2;
    public static int END_MOVE_COMMAND = 3;
    public static int PRESET_COMMAND = 13;
    private static final String cameraIp = "192.168.0.199:7777";
    // Movements
    private final HashMap<String, Integer> movementsMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
        {   
            // Basic movements
            put("up", 1);
            put("down", 2);
            put("right", 3);
            put("left", 4);
            // Diagonals
            put("left-up", 5);
            put("left-down", 6);
            put("right-up", 7);
            put("right-down", 8);
        }
    };
    // Sections
    private final HashMap<String, Integer> presetsMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 1L;
        {
            put("bottom-right", 1);
            put("bottom-left", 2);
            put("middle-right", 3);
            put("middle-left", 4);
            put("top-right", 5);
            put("top-left", 6);
        }
    };
  
    private String queryUrl(int code, int value){
        String urlQuery;
        // Tenta da forma mais adequada
        try {
            urlQuery =  "http://" + cameraIp + "/media/?" +
                        String.format("action=cmd&code=%s&value=%s",
                                    URLEncoder.encode(code + "", "UTF-8"),
                                    URLEncoder.encode(value + "", "UTF-8"));
        }
        // Adiciona de forma mais bruta caso não tenha ocorrido bem
        catch (UnsupportedEncodingException ex) {
            urlQuery = cameraIp + "/media/?action=cmd&code="+code+"&value="+value;
        }
        return urlQuery;
    }
    
    private void sendCommand(int command, int code) throws IOException{
        // Chave de autenticação da camera
        String keyCam = "admin:admin";
        //String encoding = new String(Base64.encodeBase64(keyCam.getBytes("UTF-8")), "UTF-8");
        HttpURLConnection connection = (HttpURLConnection) new URL(this.queryUrl(command, code)).openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");

        // Recebe a saida e verifica o status
        InputStream response = connection.getInputStream();
        BufferedReader in = new BufferedReader (new InputStreamReader (response));
        String text = "", line;
        while ((line = in.readLine()) != null)
            text += line;

        if(!text.contains("OK")) throw new IOException();
    }
    
    // Realiza um sleep por s segundos
    private void sleepFor(int s) throws InterruptedException {
        Thread.sleep(s);
    }
    
    @GET
    @Produces("image/x-jpeg") // Need to check this
    @Path("/snapshot")
    public Response getSnapshot(){
        Response res = ClientBuilder
                .newClient()
                .target("http://" + cameraIp + "/media/?action=snapshot")
                .request()
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .get();
        
        return  Response
                .ok(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/move")
    public Response doMovement(@FormParam("direction") String direction) {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Camera cam = new Camera();
        
        try{
            // Verifica se a propriedade existe
            if (!movementsMap.containsKey(direction)) throw new IllegalArgumentException(); 
            // Envia o comando
            sendCommand(START_MOVE_COMMAND, presetsMap.get(direction));
            // Para a execução por alguns milisegundos
            sleepFor(500);
            // Para o comando enviado
            sendCommand(END_MOVE_COMMAND, presetsMap.get(direction));
            // Informa sobre a execução do mesmo
            cam.setStatus(true);
            // Prepara o retorno
            rb = Response.ok(cam);
        } catch(IllegalArgumentException e){
            x.setStatus(true);
            rb = Response.ok(x);
        } catch (IOException e) {
            x.setStatus(true);
            rb = Response.ok(x);
        } catch (InterruptedException e) {
            x.setStatus(true);
            rb = Response.ok(x);
        }
        
        return  rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
    
    @POST
    @Produces("application/json")
    @Path("/goto-section") //bottom-right/bottom-left/middle-right/middle-left/top-right/top-left
    public Response goToSection(@FormParam("section") String section){
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        Camera cam = new Camera();
        
        try{
            // Verifica se a propriedade existe
            if (!presetsMap.containsKey(section)) throw new IllegalArgumentException(); 
            // Envia o comando
            sendCommand(PRESET_COMMAND, presetsMap.get(section));
            // Informa sobre a execução do mesmo
            cam.setStatus(true);
            // Prepara o retorno
            rb = Response.ok(cam);
        } catch(IllegalArgumentException e){
            x.setStatus(true);
            rb = Response.ok(x);
        } catch (IOException e) {
            x.setStatus(true);
            rb = Response.ok(x);
        }
        
        return  rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .allow("OPTIONS")
                .build();
    }
}