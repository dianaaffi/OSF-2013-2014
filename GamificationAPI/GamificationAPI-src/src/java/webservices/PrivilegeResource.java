/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.PrivilegeDAL;
import DAL.RewardDAL;
import entities.Privilege;
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
 * @author root
 */
public class PrivilegeResource {

    private String id;

    /**
     * Creates a new instance of PrivilegeResource
     */
    private PrivilegeResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the PrivilegeResource
     */
    public static PrivilegeResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of PrivilegeResource class.
        return new PrivilegeResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.PrivilegeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() throws JSONException {
        JSONObject obj = new JSONObject();
        Privilege privilege = PrivilegeDAL.getPrivilegeById(Integer.parseInt(this.id));
        if(privilege != null){
            obj = privilege.toJSON();
        }
        return Response.status(200).entity(obj.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of PrivilegeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public Response putJson(String content) {
        try {
            JSONObject json = new JSONObject(content);
            if (json.has("name") && json.has("id")) {
                Reward reward = new Reward();
                reward.fromJSON(json);
                RewardDAL.editReward(reward);
                json.put("reward", reward.getId());
                Privilege privilege = new Privilege();
                privilege.fromJSON(json);
                PrivilegeDAL.editPrivilege(privilege);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error PrivilegesRessource-putJson: " + ex.getMessage());
        }
        return Response.status(201).build();
    }

    /**
     * DELETE method for resource PrivilegeResource
     */
    @DELETE
    public Response delete() {
        try {
            if (this.id != null && !this.id.isEmpty()) {
                Reward reward = RewardDAL.getRewardById(Integer.parseInt(this.id));
                Privilege privilege = PrivilegeDAL.getPrivilegeById(Integer.parseInt(this.id));
                PrivilegeDAL.deletePrivilege(privilege);
                RewardDAL.deleteReward(reward);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error PrivilegesRessource-putJson: " + ex.getMessage());
        }
        return Response.status(204).build();
    }
}
