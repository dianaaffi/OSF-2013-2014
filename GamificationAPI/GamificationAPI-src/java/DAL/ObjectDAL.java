/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Object;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class ObjectDAL {
    
  
    private static final String GET_OBJECTTYPE_BY_ID_QUERY = "from Object as object where object.id =";
    
    public static Object getObjectById(int userId){
        List<Object> allObjects;
        Object object = null;
        try{
            allObjects = HibernateUtil.executeHQLQuery(GET_OBJECTTYPE_BY_ID_QUERY+userId);
            if (allObjects!=null && !allObjects.isEmpty()) {
                object = allObjects.get(0);
            }
        }catch(Exception ex){
            
        }
        return object;
    }
    
}
