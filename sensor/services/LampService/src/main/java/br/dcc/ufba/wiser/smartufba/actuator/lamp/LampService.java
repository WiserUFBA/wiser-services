/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package br.dcc.ufba.wiser.smartufba.actuator.lamp;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.smartufba.tatu.drivers.DriverMQTT;

@Path("/devices/actuator/lamp")
public class LampService {

    public LampService() {
    }

    @GET
    @Produces("application/json")
    public Response getStatusLamp() throws Exception {
        Lamp l = new Lamp();
    
        
        //DriverMQTT temp = new DriverMQTT("192.168.0.100", 1883, "test1", "temp");
        //String degree = temp.getInfo("temp");
        
        l.setStatus(true);
        
        
        ResponseBuilder rb = Response.ok(l)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
        
    }
    
    @POST
    @Produces("application/json")
    public Response setStatusLamp(@FormParam("status") boolean status) throws Exception {
        Lamp l = new Lamp();
    
        
        //DriverMQTT temp = new DriverMQTT("192.168.0.100", 1883, "test1", "temp");
        //String degree = temp.getInfo("temp");
        
        l.setStatus(true);
        
        
        ResponseBuilder rb = Response.ok(l)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
            .header("Access-Control-Allow-Headers", "Content-Type")
            .allow("OPTIONS");
        return rb.build();
        
    }
}
