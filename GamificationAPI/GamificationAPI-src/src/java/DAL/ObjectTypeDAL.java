/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.ObjectType;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class ObjectTypeDAL {
    
  
    private static final String GET_OBJECTTYPE_BY_ID_QUERY = "from ObjectType as objectType where objectType.id =";
    
    public static ObjectType getObjectTypeById(int userId){
        List<ObjectType> allObjectTypes;
        ObjectType objectType = null;
        try{
            allObjectTypes = HibernateUtil.executeHQLQuery(GET_OBJECTTYPE_BY_ID_QUERY+userId);
            if (allObjectTypes!=null && !allObjectTypes.isEmpty()) {
                objectType = allObjectTypes.get(0);
            }
        }catch(Exception ex){
            
        }
        return objectType;
    }
    
    public static List<ObjectType> getAllObjectType(int appId){
        List<ObjectType> allApplications = null;
        try{
            allApplications = HibernateUtil.executeHQLQuery("from ObjectType as objectType WHERE objectType.application = " + appId);
        }catch(Exception ex){
            
        }
        return allApplications;
    }
    
    public static ObjectType addObjectType(ObjectType application) {
        try {
            application.setId(HibernateUtil.addEntity(application));
        } catch (Exception ex) {
            System.out.println("Error in ApplicationDAL-addApplication: " + ex.getMessage());
        }
        return application;
    }
    
}
