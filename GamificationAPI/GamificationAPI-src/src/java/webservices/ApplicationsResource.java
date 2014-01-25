/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.ApplicationDAL;
import entities.Application;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author joseph
 */
@Path("/applicaitons")
public class ApplicationsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApplicationsResource
     */
    public ApplicationsResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.ApplicationsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() {
        List applications = ApplicationDAL.getAllApplication();
        JSONArray returnArray = util.Helper.listToJSONArray(applications);
            
        return Response.status(200).entity(returnArray.toString()).build();
    }
    @Path("{id}")
    public ApplicationResource getApplicationsResource(@PathParam("id") String id) {
        return ApplicationResource.getInstance(id);
    }
    
    /**
     * POST method for creating an instance of Application
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) throws JSONException {

        JSONObject json = new JSONObject(content);
        if (json.has("name")) {
            Application app = new Application(json.getString("name"),nextSessionId(),nextSessionId());
            //app.fromJSON(json);
            ApplicationDAL.addApplication(app);
            return Response.status(201).entity(app.toJSON().toString()).build();
        } else {
            return Response.status(400).build();
        }       
    }
    
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
    return new BigInteger(130, random).toString(32);
    }
    
}