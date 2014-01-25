/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.PlayerDAL;
import DAL.TeamPlayerDAL;
import entities.TeamRole;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author joseph
 */
public class TeamRoleResource {

    private String id;

    /**
     * Creates a new instance of TeamRoleResource
     */
    private TeamRoleResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the TeamRoleResource
     */
    public static TeamRoleResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of TeamRoleResource class.
        return new TeamRoleResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.TeamRoleResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() {
            //TODO return proper representation object
            TeamRole player = TeamPlayerDAL.getTeamRoleById(Integer.parseInt(this.id));
            
            return Response.status(200).entity(player.toJSON().toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of TeamRoleResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource TeamRoleResource
     */
    @DELETE
    public void delete() {
    }
}
