/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import DAL.PlayerDAL;
import entities.Player;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author joseph
 */
public class TeamResource {

    private String id;

    /**
     * Creates a new instance of TeamResource
     */
    private TeamResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the TeamResource
     */
    public static TeamResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of TeamResource class.
        return new TeamResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.TeamResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@QueryParam("appId") int addId) {

        Player player = PlayerDAL.getTeamById(addId, this.id);
        return Response.status(200).entity(player.toJSON().toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of TeamResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public Response putJson(String content) {
        try {
            JSONObject json = new JSONObject(content);
            if (json.has("name") && json.has("id")) {
                Player player = new Player();
                player.fromJSON(json);
                PlayerDAL.editPlayer(player);
            } else {
                 return Response.status(400).build();
            }
        } catch (Exception ex) {
            System.out.println("Error PlayerRessource-putJson: " + ex.getMessage());
        }
        return Response.status(201).build();
    }

    /**
     * DELETE method for resource TeamResource
     */
    @DELETE
    public Response delete() {
        try {
            if (this.id != null && !this.id.isEmpty()) {
                Player player = PlayerDAL.getPlayerById(this.id);
                PlayerDAL.deletePlayer(player);
                
            } else {
                return Response.status(400).build();
            }
        } catch (Exception ex) {
            System.out.println("Error PlayerRessource-deleteJson: " + ex.getMessage());
        }
        return Response.status(201).build();
    }
}
