/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.ActionDAL;
import DAL.PlayerDAL;
import entities.Action;
import entities.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author joseph
 */
@Path("/actions")
public class ActionsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ActionsResource
     */
    public ActionsResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.ActionsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of ActionResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) throws JSONException {
        //TODO
        JSONObject json = new JSONObject(content);
        if (json.has("appId") && json.has("eventType") && json.has("reward")) {
            Action action = new Action();
            action.fromJSON(json);
            action = ActionDAL.addAction(action);
            return Response.status(201).entity(action.toJSON().toString()).build();
        } else {
            return Response.status(400).build();
        }
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public ActionResource getActionResource(@PathParam("id") String id) {
        return ActionResource.getInstance(id);
    }
}
