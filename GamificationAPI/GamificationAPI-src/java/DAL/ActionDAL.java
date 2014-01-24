/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Action;
import entities.Reward;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class ActionDAL {


    

    public static Reward getRewardFromEventTypeAndObjectType(int eventTypeId, int objectTypeId) {
        List<Reward> allRewards;
        Reward reward = null;
        try {
            allRewards = HibernateUtil.executeHQLQuery("from Action as action where action.eventType = "+eventTypeId+" AND action.ObjectType = "+objectTypeId);
            if (allRewards != null && !allRewards.isEmpty()) {
                reward = allRewards.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in ActionDAL-getRewardFromEventTypeAndObjectType: " + ex.getMessage());
        }
        return reward;
    }

    

}
