/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.PlayerDAL;
import DAL.PlayerRewardDAL;
import entities.Player;
import entities.Reward;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;

/**
 * REST Web Service
 *
 * @author joseph
 */
public class PlayerResource {

    private String id;

    /**
     * Creates a new instance of PlayerResource
     */
    private PlayerResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the PlayerResource
     */
    public static PlayerResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of PlayerResource class.
        return new PlayerResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.PlayerResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() {
            //TODO return proper representation object
            Player player = PlayerDAL.getPlayerById(this.id);
            
            return Response.status(200).entity(player.toJSON().toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of PlayerResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource PlayerResource
     */
    @DELETE
    public void delete() {
    }
}
