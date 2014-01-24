/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Badge;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class BadgeDAL {

    private static final String GET_ALL_BADGES_QUERY = "from Badge as badge, Reward as reward where badge.id= reward.id and reward.application =";
    private static final String GET_BADGE_BY_ID_QUERY = "from Badge as badge where badge.id =";

    public static List<Badge> getAllBadgesByApplicationId(int applicationId) {
        List<Badge> allBadges = null;
        try {
            allBadges = HibernateUtil.executeHQLQuery(GET_ALL_BADGES_QUERY + applicationId);
        } catch (Exception ex) {
            System.out.println("Error in BadgeDAL-getAllBadgesByApplicationId: " + ex.getMessage());
        }
        return allBadges;
    }

    public static Badge getBadgeById(int badgeId) {
        List<Badge> allBadges;
        Badge badge = null;
        try {
            allBadges = HibernateUtil.executeHQLQuery(GET_BADGE_BY_ID_QUERY + badgeId);
            if (allBadges != null && !allBadges.isEmpty()) {
                badge = allBadges.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in BadgeDAL-getBadgeById: " + ex.getMessage());
        }
        return badge;
    }

    public static Badge addBadge(Badge badge) {
        try {
            badge.setId(HibernateUtil.addEntity(badge));
        } catch (Exception ex) {
            System.out.println("Error in BadgeDAL-addBadge: " + ex.getMessage());
        }
        return badge;
    }

    public static void editBadge(Badge badge) {
        try {
            if (badge.getId() != null && badge.getId() > 0) {
                HibernateUtil.editEntity(badge);
            }
        } catch (Exception ex) {
            System.out.println("Error in BadgeDAL-editBadge: " + ex.getMessage());
        }
    }
    
    public static void deleteBadge(Badge badge) {
        try {
            if (badge.getId() != null && badge.getId() > 0) {
                HibernateUtil.deleteEntity(badge);
            }
        } catch (Exception ex) {
            System.out.println("Error in BadgeDAL-deleteBadge: " + ex.getMessage());
        }
    }

}
