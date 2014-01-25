/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Privilege;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class PrivilegeDAL {

    private static final String GET_ALL_PRIVILEGES_QUERY = "from Privilege as privilege where privilege.application =";
    private static final String GET_PRIVILEGE_BY_ID_QUERY = "from Privilege as privilege where privilege.id =";

    public static List<Privilege> getAllPrivilegesByApplicationId(int applicationId) {
        List<Privilege> allPrivileges = null;
        try {
            allPrivileges = HibernateUtil.executeHQLQuery(GET_ALL_PRIVILEGES_QUERY + applicationId);
        } catch (Exception ex) {
            System.out.println("Error in PrivilegeDAL-getAllPrivilegesByApplicationId: " + ex.getMessage());
        }
        return allPrivileges;
    }

    public static Privilege getPrivilegeById(int privilegeId) {
        List<Privilege> allPrivileges;
        Privilege privilege = null;
        try {
            allPrivileges = HibernateUtil.executeHQLQuery(GET_PRIVILEGE_BY_ID_QUERY + privilegeId);
            if (allPrivileges != null && !allPrivileges.isEmpty()) {
                privilege = allPrivileges.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in PrivilegeDAL-getPrivilegeById: " + ex.getMessage());
        }
        return privilege;
    }

    public static Privilege addPrivilege(Privilege privilege) {
        try {
            privilege.setId(HibernateUtil.addEntity(privilege));
        } catch (Exception ex) {
            System.out.println("Error in PrivilegeDAL-addPrivilege: " + ex.getMessage());
        }
        return privilege;
    }

    public static void editPrivilege(Privilege privilege) {
        try {
            if (privilege.getId() != null && privilege.getId() > 0) {
                HibernateUtil.editEntity(privilege);
            }
        } catch (Exception ex) {
            System.out.println("Error in PrivilegeDAL-editPrivilege: " + ex.getMessage());
        }
    }
    
    public static void deletePrivilege(Privilege privilege) {
        try {
            if (privilege.getId() != null && privilege.getId() > 0) {
                HibernateUtil.deleteEntity(privilege);
            }
        } catch (Exception ex) {
            System.out.println("Error in PrivilegeDAL-deletePrivilege: " + ex.getMessage());
        }
    }

}
