/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Quest;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class QuestDAL {

    private static final String GET_ALL_QUESTS_QUERY = "from Quest as quest where quest.application =";
    private static final String GET_QUEST_BY_ID_QUERY = "from Quest as quest where quest.id =";

    public static List<Quest> getAllQuestsByApplicationId(int applicationId) {
        List<Quest> allQuests = null;
        try {
            allQuests = HibernateUtil.executeHQLQuery(GET_ALL_QUESTS_QUERY + applicationId);
        } catch (Exception ex) {
            System.out.println("Error in QuestDAL-getAllQuestsByApplicationId: " + ex.getMessage());
        }
        return allQuests;
    }

    public static Quest getQuestById(int questId) {
        List<Quest> allQuests;
        Quest quest = null;
        try {
            allQuests = HibernateUtil.executeHQLQuery(GET_QUEST_BY_ID_QUERY + questId);
            if (allQuests != null && !allQuests.isEmpty()) {
                quest = allQuests.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in QuestDAL-getQuestById: " + ex.getMessage());
        }
        return quest;
    }

    public static Quest addQuest(Quest quest) {
        try {
            quest.setId(HibernateUtil.addEntity(quest));
        } catch (Exception ex) {
            System.out.println("Error in QuestDAL-addQuest: " + ex.getMessage());
        }
        return quest;
    }

    public static void editQuest(Quest quest) {
        try {
            if (quest.getId() != null && quest.getId() > 0) {
                HibernateUtil.editEntity(quest);
            }
        } catch (Exception ex) {
            System.out.println("Error in QuestDAL-editQuest: " + ex.getMessage());
        }
    }
    
    public static void deleteQuest(Quest quest) {
        try {
            if (quest.getId() != null && quest.getId() > 0) {
                HibernateUtil.deleteEntity(quest);
            }
        } catch (Exception ex) {
            System.out.println("Error in QuestDAL-deleteQuest: " + ex.getMessage());
        }
    }

}
