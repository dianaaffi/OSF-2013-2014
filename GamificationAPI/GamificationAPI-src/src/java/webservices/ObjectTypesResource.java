/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.ObjectTypeDAL;
import entities.ObjectType;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author joseph
 */
@Path("/objecttypes")
public class ObjectTypesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ObjectTypesResource
     */
    public ObjectTypesResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.ObjectTypesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@QueryParam("appId") int appId) throws IOException, JSONException {
        //TODO return proper representation object
        List objectType = ObjectTypeDAL.getAllObjectType(appId);
        JSONArray returnArray = util.Helper.listToJSONArray(objectType);
        return Response.status(200).entity(returnArray.toString()).build();
    }

    /**
     * POST method for creating an instance of ObjectTypeResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) throws JSONException {
        JSONObject json  = new JSONObject(content);
        try {
            if (json.has("name") && json.has("appId")) {
                ObjectType reward = new ObjectType();
                reward.fromJSON(json);
                reward = ObjectTypeDAL.addObjectType(reward);
                return Response.status(201).entity(reward.toJSON().toString()).build();
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error BadgesRessource-postJson: " + ex.getMessage());
            return Response.status(500).build();
        }
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ObjectTypeResource getObjectTypeResource(@PathParam("id") String id) {
        return ObjectTypeResource.getInstance(id);
    }
}
