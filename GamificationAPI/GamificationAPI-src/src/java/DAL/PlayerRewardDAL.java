/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import entities.Player;
import entities.PlayerReward;
import entities.Reward;
import java.util.Date;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author root
 */
public class PlayerRewardDAL {
    
    public static List<PlayerReward> getAllRewardsForPlayerId(int playerId){
        List<PlayerReward> allRewards = null;
        try{
            allRewards = HibernateUtil.executeHQLQuery("from PlayerReward as pr where pr.player ="+playerId);
        }catch(Exception ex){
            
        }
        return allRewards;
    }
    
    public static PlayerReward getPlayerReward(String playerRewardId){
        List<PlayerReward> allPlayerRewards;
        PlayerReward pr = null;
        try{
            allPlayerRewards = HibernateUtil.executeHQLQuery("from PlayerReward as pr where pr.id ="+playerRewardId);
            if (allPlayerRewards!=null && !allPlayerRewards.isEmpty()) {
                pr = allPlayerRewards.get(0);
            }
        }catch(Exception ex){
            
        }
        return pr;
    }
    
    public static PlayerReward getPlayerReward(int playerId, int rewardId){
        List<PlayerReward> allPlayerRewards;
        PlayerReward pr = null;
        try{
            allPlayerRewards = HibernateUtil.executeHQLQuery("from PlayerReward as pr where pr.player ="+playerId+" and pr.reward ="+rewardId);
            if (allPlayerRewards!=null && !allPlayerRewards.isEmpty()) {
                pr = allPlayerRewards.get(0);
            }
        }catch(Exception ex){
            
        }
        return pr;
    }
    
    public static PlayerReward addRewardToPlayer(int rewardId, int playerId, float level) {
        PlayerReward pr = new PlayerReward();
        pr.setReward(RewardDAL.getRewardById(rewardId));
        pr.setPlayer(PlayerDAL.getPlayerById("" + playerId));
        if (level != -1) pr.setCurrentLevel(level);
        pr.setAttributionDate(new Date());
        pr.setId(HibernateUtil.addEntity(pr));
        return pr;
    }
    
    public static PlayerReward addRewardToPlayer(int rewardId, int playerId) {
        return addRewardToPlayer(rewardId, playerId, -1);
    }
    
    public static void editPlayerReward(PlayerReward pr) {
        try {
            if (pr.getId() != null && pr.getId() > 0) {
                HibernateUtil.editEntity(pr);
            }
        } catch (Exception ex) {
            System.out.println("Error in PlayerRewardDAL-editPlayerReward: " + ex.getMessage());
        }
    }
    
    public static void deletePlayerReward(PlayerReward pr) {
        try {
            if (pr.getId() != null && pr.getId() > 0) {
                HibernateUtil.deleteEntity(pr);
            }
        } catch (Exception ex) {
            System.out.println("Error in PlayerRewardDAL-delete: " + ex.getMessage());
        }
    }
    
}
