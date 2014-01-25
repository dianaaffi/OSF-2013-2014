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
    
    public static List<Application> getAllApplication(){
        List<Application> allApplications = null;
        try{
            allApplications = HibernateUtil.executeHQLQuery("from Application as application");
        }catch(Exception ex){
            
        }
        return allApplications;
    }
 public static Application addApplication(Application application) {
        try {
            application.setId(HibernateUtil.addEntity(application));
        } catch (Exception ex) {
            System.out.println("Error in ApplicationDAL-addApplication: " + ex.getMessage());
        }
        return application;
    }

    public static void editApplication(Application application) {
        try {
            if (application.getId() != null && application.getId() > 0) {
                HibernateUtil.editEntity(application);
            }
        } catch (Exception ex) {
            System.out.println("Error in ApplicationDAL-editApplication: " + ex.getMessage());
        }
    }
    
    public static void deleteApplication(Application application) {
        try {
            if (application.getId() != null && application.getId() > 0) {
                HibernateUtil.deleteEntity(application);
            }
        } catch (Exception ex) {
            System.out.println("Error in ApplicationDAL-deleteApplication: " + ex.getMessage());
        }
    }

}
