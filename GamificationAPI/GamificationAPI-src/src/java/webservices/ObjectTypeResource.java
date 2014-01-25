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
 * @author joseph
 */
public class ObjectTypeResource {

    private String id;

    /**
     * Creates a new instance of ObjectTypeResource
     */
    private ObjectTypeResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the ObjectTypeResource
     */
    public static ObjectTypeResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of ObjectTypeResource class.
        return new ObjectTypeResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.ObjectTypeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ObjectTypeResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource ObjectTypeResource
     */
    @DELETE
    public void delete() {
    }
}
