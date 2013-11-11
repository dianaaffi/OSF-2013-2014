/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Reward;
import java.io.Serializable;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class RewardDAL {

    private static final String GET_ALL_REWARDS_QUERY = "from Reward as reward where reward.application =";
    private static final String GET_REWARD_BY_ID_QUERY = "from Reward as reward where reward.id =";

    public static List<Reward> getAllRewardsByApplicationId(int applicationId) {
        List<Reward> allRewards = null;
        try {
            allRewards = HibernateUtil.executeHQLQuery(GET_ALL_REWARDS_QUERY + applicationId);
        } catch (Exception ex) {
            System.out.println("Error in RewardDAL-getAllRewardsByApplicationId: " + ex.getMessage());
        }
        return allRewards;
    }

    public static Reward getRewardById(int rewardId) {
        List<Reward> allRewards;
        Reward reward = null;
        try {
            allRewards = HibernateUtil.executeHQLQuery(GET_REWARD_BY_ID_QUERY + rewardId);
            if (allRewards != null && !allRewards.isEmpty()) {
                reward = allRewards.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in RewardDAL-getRewardById: " + ex.getMessage());
        }
        return reward;
    }

    public static Reward addReward(Reward reward) {
        try {
            reward.setId(HibernateUtil.addEntity(reward));
        } catch (Exception ex) {
            System.out.println("Error in RewardDAL-addReward: " + ex.getMessage());
        }
        return reward;
    }
    
    public static void editReward(Reward reward) {
        try {
            if (reward.getId() != null && reward.getId() > 0) {
                HibernateUtil.editEntity(reward);
            }
        } catch (Exception ex) {
            System.out.println("Error in RewardDAL-editReward: " + ex.getMessage());
        }
    }
    
    public static void deleteReward(Reward reward) {
        try {
            if (reward.getId() != null && reward.getId() > 0) {
                HibernateUtil.deleteEntity(reward);
            }
        } catch (Exception ex) {
            System.out.println("Error in RewardDAL-deleteReward: " + ex.getMessage());
        }
    }

}
