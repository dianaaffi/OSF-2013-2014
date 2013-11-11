/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.PlayerDAL;
import DAL.PlayerRewardDAL;
import entities.Reward;
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
import util.Helper;

/**
 * REST Web Service
 *
 * @author joseph
 */
@Path("/players")
public class PlayersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PlayersResource
     */
    public PlayersResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.PlayersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@QueryParam("appId") int appId,
            @QueryParam("q") String filter
    ) throws JSONException {
        List players = PlayerDAL.getAllPlayersByApplicationId(appId, filter);
        JSONArray returnArray = util.Helper.listToJSONArray(players);
            
        return Response.status(200).entity(returnArray.toString()).build();
    }

    /**
     * POST method for creating an instance of PlayerResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) {
        //TODO
        return null;
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public PlayerResource getPlayerResource(@PathParam("id") String id) {
        return PlayerResource.getInstance(id);
    }
    
    
    @Path("{id}/rewards")
    @GET
    @Produces("application/json")
    public Response getResourceJson (@PathParam("id") String id) {
            //TODO return proper representation object
            List<Reward> rewards = PlayerRewardDAL.getAllRewardsForPlayerId(Integer.parseInt(id));
            JSONArray returnArray = Helper.listToJSONArray(rewards);
            
            return Response.status(200).entity(returnArray.toString()).build();
    }
}
