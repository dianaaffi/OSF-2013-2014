/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.ActionDAL;
import DAL.EventDAL;
import DAL.PlayerRewardDAL;
import entities.Event;
import entities.Reward;
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
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("/events")
public class EventsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EventsResource
     */
    public EventsResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.EventsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of EventResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) throws JSONException {
        JSONObject json  = new JSONObject(content);
        try {
            if (json.has("eventType")) {
                Event event = new Event();
                event.fromJSON(json);
                event = EventDAL.addEvent(event);
                json.put("id", event.getId());
                
                JSONArray jsonArray = ActionDAL.getRewardFromEventTypeAndObjectType(event);
                json.put("earnedRewards", jsonArray);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error BadgesRessource-postJson: " + ex.getMessage());
        }
        return Response.status(201).entity(json.toString()).build();
    
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public EventResource getEventResource(@PathParam("id") String id) {
        return EventResource.getInstance(id);
    }
}
