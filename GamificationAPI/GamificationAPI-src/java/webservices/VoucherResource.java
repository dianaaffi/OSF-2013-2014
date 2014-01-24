/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import DAL.VoucherDAL;
import DAL.RewardDAL;
import entities.Voucher;
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
public class VoucherResource {

    private String id;

    /**
     * Creates a new instance of VoucherResource
     */
    private VoucherResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the VoucherResource
     */
    public static VoucherResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of VoucherResource class.
        return new VoucherResource(id);
    }

    /**
     * Retrieves representation of an instance of webservices.VoucherResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response getJson() throws JSONException {
       JSONObject obj = new JSONObject();
        Voucher voucher = VoucherDAL.getVoucherById(Integer.parseInt(this.id));
        if(voucher != null){
            obj = voucher.toJSON();
        }
        return Response.status(200).entity(obj.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of VoucherResource
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
                Voucher voucher = new Voucher();
                voucher.fromJSON(json);
                VoucherDAL.editVoucher(voucher);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error VouchersRessource-putJson: " + ex.getMessage());
        }
        return Response.status(201).entity(json.toString()).build();
    }

    /**
     * DELETE method for resource VoucherResource
     */
    @DELETE
    public Response delete() {
        try {
            if (this.id != null && !this.id.isEmpty()) {
                Reward reward = RewardDAL.getRewardById(Integer.parseInt(this.id));
                Voucher voucher = VoucherDAL.getVoucherById(Integer.parseInt(this.id));
                VoucherDAL.deleteVoucher(voucher);
                RewardDAL.deleteReward(reward);
            } else {
                return Response.status(400).entity("Incomplete or wrong parameters").build();
            }
        } catch (Exception ex) {
            System.out.println("Error VouchersRessource-putJson: " + ex.getMessage());
        }
        return Response.status(204).build();
    }
}
