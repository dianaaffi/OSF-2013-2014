/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.PlayerRewardDAL;
import entities.PlayerReward;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author root
 */
public class PlayerRewardResource {

    private String id;

    /**
     * Creates a new instance of PlayerRewardResource
     */
    private PlayerRewardResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the PlayerRewardResource
     */
    public static PlayerRewardResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of PlayerRewardResource class.
        return new PlayerRewardResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.PlayerRewardResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@QueryParam("playerId") int playerId, @QueryParam("rewardId") int rewardId) {
        PlayerReward pr = PlayerRewardDAL.getPlayerReward(playerId, rewardId);
        return Response.status(200).entity(pr.toJSON().toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of PlayerRewardResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public Response putJson(String content) {
        try {
            JSONObject json = new JSONObject(content);
            if (json.has("attributionDate") && json.has("player") && json.has("reward")) {
                PlayerReward pr = new PlayerReward();
                pr.fromJSON(json);
                PlayerRewardDAL.editPlayerReward(pr);
            } else {
                 return Response.status(400).build();
            }
        } catch (Exception ex) {
            System.out.println("Error PlayerRessource-putJson: " + ex.getMessage());
        }
        return Response.status(201).build();
    }

    /**
     * DELETE method for resource PlayerRewardResource
     * @return 
     */
    @DELETE
    public Response delete() {
        try {
            if (this.id != null && !this.id.isEmpty()) {
                PlayerReward pr = PlayerRewardDAL.getPlayerReward(this.id);
                PlayerRewardDAL.deletePlayerReward(pr);
                
            } else {
                return Response.status(400).build();
            }
        } catch (Exception ex) {
            System.out.println("Error PlayerRessource-deleteJson: " + ex.getMessage());
        }
        return Response.status(201).build();
    }
}
