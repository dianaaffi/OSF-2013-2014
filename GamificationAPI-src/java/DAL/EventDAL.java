/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Event;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class EventDAL {

    
    private static final String GET_EVENT_BY_ID_QUERY = "from Event as event where event.id =";

    

    public static Event getEventById(int eventId) {
        List<Event> allEvents;
        Event event = null;
        try {
            allEvents = HibernateUtil.executeHQLQuery(GET_EVENT_BY_ID_QUERY + eventId);
            if (allEvents != null && !allEvents.isEmpty()) {
                event = allEvents.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in EventDAL-getEventById: " + ex.getMessage());
        }
        return event;
    }

    public static Event addEvent(Event event) {
        try {
            event.setId(HibernateUtil.addEntity(event));
        } catch (Exception ex) {
            System.out.println("Error in EventDAL-addEvent: " + ex.getMessage());
        }
        return event;
    }

    public static void editEvent(Event event) {
        try {
            if (event.getId() != null && event.getId() > 0) {
                HibernateUtil.editEntity(event);
            }
        } catch (Exception ex) {
            System.out.println("Error in EventDAL-editEvent: " + ex.getMessage());
        }
    }
    
    public static void deleteEvent(Event event) {
        try {
            if (event.getId() != null && event.getId() > 0) {
                HibernateUtil.deleteEntity(event);
            }
        } catch (Exception ex) {
            System.out.println("Error in EventDAL-deleteEvent: " + ex.getMessage());
        }
    }

}
