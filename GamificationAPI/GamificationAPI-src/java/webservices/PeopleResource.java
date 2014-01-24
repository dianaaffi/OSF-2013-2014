/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAL.PlayerDAL;
import entities.Player;
import java.io.IOException;
import java.util.List;
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
@Path("/persons")
public class PeopleResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PeopleResource
     */
    public PeopleResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.PeopleResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@QueryParam("appId") int appId) throws IOException {
        //TODO return proper representation object
        List players = PlayerDAL.getAllPersonsByApplicationId(appId);
        JSONArray returnArray = util.Helper.listToJSONArray(players);
        return Response.status(200).entity(returnArray.toString()).build();

    }

    /**
     * POST method for creating an instance of PersonResource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) throws JSONException {

        JSONObject json = new JSONObject(content);
        if (json.has("name") && json.has("appId")) {
            try {
                json.put("userType", "person");
                Player player = new Player();
                player.fromJSON(json);
                player = PlayerDAL.addPlayer(player);
                return Response.status(201).entity(player.toJSON().toString()).build();
            } catch (JSONException ex) {
                Logger.getLogger(PeopleResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return Response.status(400).build();
        }
        return null;
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public PersonResource getPersonResource(@PathParam("id") String id) {
        return PersonResource.getInstance(id);
    }
}
