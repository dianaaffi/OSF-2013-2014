/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author root
 */
public class EventResource {

    private String id;

    /**
     * Creates a new instance of EventResource
     */
    private EventResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the EventResource
     */
    public static EventResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of EventResource class.
        return new EventResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.EventResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EventResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource EventResource
     */
    @DELETE
    public void delete() {
    }
}
