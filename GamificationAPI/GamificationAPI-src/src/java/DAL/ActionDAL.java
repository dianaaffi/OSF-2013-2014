/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Action;
import entities.Event;
import entities.PlayerReward;
import entities.Reward;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class ActionDAL {


    

    public static JSONArray getRewardFromEventTypeAndObjectType(Event event) {
        List<Action> allActions = new ArrayList<>();
        Action action = null;
        JSONArray jsonArray = new JSONArray();
        try {
            
            //Get the player's all present rewards
            List<PlayerReward> playersRewards = PlayerRewardDAL.getAllRewardsForPlayerId(event.getPlayer().getId());
            
            //Get actions/rules that match the player's profile and the event
            if (playersRewards != null && !playersRewards.isEmpty()) {
                List<Action> actions = null;
                for (PlayerReward pr : playersRewards) {
                    actions = HibernateUtil.executeHQLQuery("from Action as action where"
                    + " action.eventType = "+event.getEventType().getId()+""
                    + " AND action.objectType = "+event.getObject().getObjectType().getId()+""
                    + "AND action.rewardByInputReward = "+ pr.getReward().getId()
                    + "AND (action.inputValue IS NULL OR action.inputValue = " + pr.getCurrentLevel());
                    if (actions != null && !actions.isEmpty()) {
                        allActions.addAll(actions);
                    }
                }
                
            }
            
            
            //Get the results of the found rules and add them to the player's rewards
            if (!allActions.isEmpty()) {
                for (Action ac : allActions) {
                    
                    //Check if the rule consumes a voucher
                    if(ac.getRewardByInputReward() != null && ac.getRewardByInputReward().getVoucher() != null){
                        //Consume the voucher
                        PlayerReward pr = PlayerRewardDAL.getPlayerReward(event.getPlayer().getId(), ac.getRewardByInputReward().getId());
                        PlayerRewardDAL.deletePlayerReward(pr);
                    }
                    
                    if (ac.getRewardByReward() != null) {
                        PlayerReward thePReward = isInRewards(ac.getRewardByReward(), playersRewards);
                        
                        //If the player do not have this reward, add it
                        if (thePReward == null) {
                            if (ac.getOutputValue() != null && !ac.getOutputValue().isEmpty()) {
                                PlayerRewardDAL.addRewardToPlayer(ac.getRewardByReward().getId(), event.getPlayer().getId(), Float.parseFloat(ac.getOutputValue()));
                            } else {
                                PlayerRewardDAL.addRewardToPlayer(ac.getRewardByReward().getId(), event.getPlayer().getId());
                            }
                            
                        //If the player already has this reward update his current level in it
                        } else if (ac.getOutputValue() != null && !ac.getOutputValue().isEmpty()) {
                            thePReward.setCurrentLevel(Float.parseFloat(ac.getOutputValue()));
                            //In case of a quest reward check if the quest is completed
                            if (ac.getRewardByReward().getQuest() != null && ac.getRewardByReward().getQuest().getMaxLevel() == Integer.parseInt(ac.getOutputValue())) {
                                thePReward.setCompletionDate(new Date());
                            }
                            PlayerRewardDAL.editPlayerReward(thePReward);
                        }
                        
                        JSONObject json = new JSONObject();
                        json.put("Reward", ac.getRewardByReward().getName());
                        json.put("Value", ac.getOutputValue());
                        jsonArray.put(json);
                    }
                }
                
            }
        } catch (Exception ex) {
            System.out.println("Error in ActionDAL-getRewardFromEventTypeAndObjectType: " + ex.getMessage());
        }
        return jsonArray;
    }
    
    private static PlayerReward isInRewards (Reward re, List<PlayerReward> list) {
        for (PlayerReward pr : list) {
            if (pr.getReward().getId() == re.getId())
                return pr;
        }
        return null;
    }

    public static Action addAction(Action action) {
        try {
            action.setId(HibernateUtil.addEntity(action));
        } catch (Exception ex) {
            System.out.println("Error in ApplicationDAL-addApplication: " + ex.getMessage());
        }
        return action;
    }

}
