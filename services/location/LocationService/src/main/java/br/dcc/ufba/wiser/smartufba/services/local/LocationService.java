/*
 * Created by Wiser Research Group UFBA
 */
package br.dcc.ufba.wiser.smartufba.services.local;


import br.ufba.dcc.services.db.BdBean;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/devices")

public class LocationService {

    public LocationService() {
    }
    
    //protected final Logger log = LoggerFactory.getLogger(LocationService.class);
    public void init(){}
    
    @GET
    @Path("/getlocation/{id}")
    @Produces({"application/json"})
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response getLocation(@PathParam("id") String id) {
        Location location;

        BdBean bdBean = BdBean.getInstance();
        location = bdBean.selectOne(id);
        String s = "Error";
                
        if (location == null) {
            return Response.ok(s).build();
        } else {
            return Response.ok(location).build();
        }
    }

    
    @PUT
    @Path("/addlocation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response addLocation(@FormParam("longitude") String longitude,
            @FormParam("latitude") String latitude) {
    try{
        
        //System.out.println(longitude);
        //System.out.println(latitude);
        Location location;
        location = new Location(Float.parseFloat(longitude), Float.parseFloat(latitude));
        BdBean bdBean = BdBean.getInstance();
        
        
        if (bdBean.insert(location)) {
            return Response.ok(location).build();
        } else {
           return Response.status(Response.Status.BAD_REQUEST).build(); 
        }
    } catch(Exception e){
        System.out.println("POXA BUNDLE PQ NÃO ME NOTA :(");
        e.printStackTrace(System.out);
        return Response.status(Response.Status.BAD_REQUEST).build(); 
    }
    }
    
    
    
    @PUT
    @Path("/updatelocation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({"application/xml", "application/json", "application/x-www-form-urlencoded"})
    public Response updateLocation(@FormParam("longitude") String longitude,
            @FormParam("latitude") String latitude, @FormParam("id") String id) {

        Location location;
        location = new Location(Float.parseFloat(longitude), Float.parseFloat(latitude));
        location.setId(Long.parseLong(id));
     
        BdBean bdBean = BdBean.getInstance();
   
        if (bdBean.update(location)) {
            return Response.ok(location).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello World!!!";
     }
    
}
