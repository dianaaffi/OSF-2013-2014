/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservices;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author joseph
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(webservices.BadgeResource.class);
        resources.add(webservices.BadgesResource.class);
        resources.add(webservices.EventResource.class);
        resources.add(webservices.EventsResource.class);
        resources.add(webservices.PeopleResource.class);
        resources.add(webservices.PersonResource.class);
        resources.add(webservices.PlayerResource.class);
        resources.add(webservices.PlayerRewardResource.class);
        resources.add(webservices.PlayerRewardsResource.class);
        resources.add(webservices.PlayersResource.class);
        resources.add(webservices.PointResource.class);
        resources.add(webservices.PointsResource.class);
        resources.add(webservices.PrivilegeResource.class);
        resources.add(webservices.PrivilegesResource.class);
        resources.add(webservices.QuestResource.class);
        resources.add(webservices.QuestsResource.class);
        resources.add(webservices.TeamResource.class);
        resources.add(webservices.TeamsResource.class);
        resources.add(webservices.VoucherResource.class);
        resources.add(webservices.VouchersResource.class);
    }
    
}
