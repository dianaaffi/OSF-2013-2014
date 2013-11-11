/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Application;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class ApplicationDAL {
    
  
    private static final String GET_APPLICATION_BY_ID_QUERY = "from Application as application where application.id =";
    
    public static Application getApplicationById(int userId){
        List<Application> allApplications;
        Application application = null;
        try{
            allApplications = HibernateUtil.executeHQLQuery(GET_APPLICATION_BY_ID_QUERY+userId);
            if (allApplications!=null && !allApplications.isEmpty()) {
                application = allApplications.get(0);
            }
        }catch(Exception ex){
            
        }
        return application;
    }
    
}
