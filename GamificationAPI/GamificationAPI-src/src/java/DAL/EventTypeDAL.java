/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.EventType;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class EventTypeDAL {
    
  
    private static final String GET_EVENTTYPE_BY_ID_QUERY = "from EventType as eventType where eventType.id =";
    
    public static EventType getEventTypeById(int userId){
        List<EventType> allEventTypes;
        EventType eventType = null;
        try{
            allEventTypes = HibernateUtil.executeHQLQuery(GET_EVENTTYPE_BY_ID_QUERY+userId);
            if (allEventTypes!=null && !allEventTypes.isEmpty()) {
                eventType = allEventTypes.get(0);
            }
        }catch(Exception ex){
            
        }
        return eventType;
    }
    
    public static List<EventType> getAllEventType(int appId){
        List<EventType> allApplications = null;
        try{
            allApplications = HibernateUtil.executeHQLQuery("from EventType as objectType WHERE objectType.application = " + appId);
        }catch(Exception ex){
            
        }
        return allApplications;
    }
    
    public static EventType addEventType(EventType application) {
        try {
            application.setId(HibernateUtil.addEntity(application));
        } catch (Exception ex) {
            System.out.println("Error in ApplicationDAL-addApplication: " + ex.getMessage());
        }
        return application;
    }
    
}
