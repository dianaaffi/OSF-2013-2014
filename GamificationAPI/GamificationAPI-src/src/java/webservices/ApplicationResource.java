/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.ApplicationDAL;
import entities.Application;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author joseph
 */
public class ApplicationResource {

    private String id;

    /**
     * Creates a new instance of ApplicationResource
     */
    private ApplicationResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the ApplicationResource
     */
    public static ApplicationResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of ApplicationResource class.
        return new ApplicationResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.ApplicationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@PathParam("id") String id) {
            //TODO return proper representation object
            Application player = ApplicationDAL.getApplicationById(Integer.parseInt(id));
            
            return Response.status(200).entity(player.toJSON().toString()).build();
    }
    /**
     * PUT method for updating or creating an instance of ApplicationResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public Response putJson(String content) throws JSONException{
         try {
            JSONObject json = new JSONObject(content);
            if (json.has("name") && json.has("apiSecret") && json.has("apiKey")) {
                           
                ApplicationDAL.editApplication(new Application(json.getString("name"),json.getString("apiSecret"),json.getString("apiKey")));
            } else {
                 return Response.status(400).build();
            }
        } catch (Exception ex) {
            System.out.println("Error Application-putJson: " + ex.getMessage());
        }
        return Response.status(201).build();
    }
    
     /**
     * DELETE method for resource ApplicationResource
     */
    @DELETE
    public void delete() {
    }
}
