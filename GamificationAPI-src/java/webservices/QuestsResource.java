/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.QuestDAL;
import DAL.QuestDAL;
import DAL.PointDAL;
import DAL.RewardDAL;
import entities.Quest;
import entities.Reward;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("/quests")
public class QuestsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QuestsResource
     */
    public QuestsResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.QuestsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson(@QueryParam("appId") int appId) throws IOException, JSONException {
        List quests = QuestDAL.getAllQuestsByApplicationId(appId);
        JSONArray returnArray = util.Helper.listToJSONArray(quests);
        return Response.status(200).entity(returnArray.toString()).build();
    }

    /**
     * POST method for creating an instance of QuestResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) throws JSONException {
        
        JSONObject json = new JSONObject(content);
        try {
            if (json.has("name")) {
                Reward reward = new Reward();
                reward.fromJSON(json);
                reward = RewardDAL.addReward(reward);
                json.put("reward", reward.getId());
                json.put("id", reward.getId());
                Quest quest = new Quest();
                quest.fromJSON(json);
                QuestDAL.addQuest(quest);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error QuestsRessource-postJson: " + ex.getMessage());
        }
        return Response.status(201).entity(json.toString()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public QuestResource getQuestResource(@PathParam("id") String id) {
        return QuestResource.getInstance(id);
    }
}
