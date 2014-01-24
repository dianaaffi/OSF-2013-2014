/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.BadgeDAL;
import DAL.RewardDAL;
import entities.Badge;
import entities.Reward;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author joseph
 */
public class BadgeResource {

    private String id;

    /**
     * Creates a new instance of BadgeResource
     */
    private BadgeResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the BadgeResource
     */
    public static BadgeResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of BadgeResource class.
        return new BadgeResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.BadgeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() throws JSONException {
        JSONObject obj = new JSONObject();
        Badge badge = BadgeDAL.getBadgeById(Integer.parseInt(this.id));
        if(badge != null){
            obj = badge.toJSON();
        }
        return Response.status(200).entity(obj.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of BadgeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public Response putJson(String content) throws JSONException {
        JSONObject json = new JSONObject(content);
        try {
            if (json.has("name") && json.has("id")) {
                Reward reward = new Reward();
                reward.fromJSON(json);
                RewardDAL.editReward(reward);
                json.put("reward", reward.getId());
                Badge badge = new Badge();
                badge.fromJSON(json);
                BadgeDAL.editBadge(badge);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error BadgesRessource-putJson: " + ex.getMessage());
        }
        return Response.status(204).build();
    }

    /**
     * DELETE method for resource BadgeResource
     */
    @DELETE
    public Response delete() {
        try {
            if (this.id != null && !this.id.isEmpty()) {
                Reward reward = RewardDAL.getRewardById(Integer.parseInt(this.id));
                Badge badge = BadgeDAL.getBadgeById(Integer.parseInt(this.id));
                BadgeDAL.deleteBadge(badge);
                RewardDAL.deleteReward(reward);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error BadgesRessource-putJson: " + ex.getMessage());
        }
        return Response.status(204).build();
    }
}
