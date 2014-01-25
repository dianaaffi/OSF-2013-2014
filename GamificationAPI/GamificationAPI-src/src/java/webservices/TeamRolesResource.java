/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.TeamPlayerDAL;
import entities.TeamRole;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;

/**
 * REST Web Service
 *
 * @author joseph
 */
@Path("/teamroles")
public class TeamRolesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TeamRolesResource
     */
    public TeamRolesResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.TeamRolesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() {
        List<TeamRole> teamRoles = TeamPlayerDAL.getAllTeamRoles();
        JSONArray returnArray = util.Helper.listToJSONArray(teamRoles);
            
        return Response.status(200).entity(returnArray.toString()).build();
    }

    /**
     * POST method for creating an instance of TeamRoleResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public TeamRoleResource getTeamRoleResource(@PathParam("id") String id) {
        return TeamRoleResource.getInstance(id);
    }
}
