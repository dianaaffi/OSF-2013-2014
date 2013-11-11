/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Player;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class PlayerDAL {
        
    public static List<Player> getAllPlayersByApplicationId(int applicationId, String filter){
        List<Player> allPlayers = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.application ="+applicationId+" and player.name LIKE '%"+filter+"%'");
        }catch(Exception ex){
            
        }
        return allPlayers;
    }
    
    public static Player getPlayerById(String userId){
        List<Player> allPlayers;
        Player player = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.id ="+userId);
            if (allPlayers!=null && !allPlayers.isEmpty()) {
                player = allPlayers.get(0);
            }
        }catch(Exception ex){
            
        }
        return player;
    }
    
    public static List<Player> getAllPersonsByApplicationId(int applicationId){
        List<Player> allPlayers = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.userType='person' and player.application ="+applicationId);
        }catch(Exception ex){
            
        }
        return allPlayers;
    }
    
    public static Player getPersonById(int applicationId, String userId){
        List<Player> allPlayers;
        Player player = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.userType='person' and player.application ="+ applicationId +" player.id ="+userId);
            if (allPlayers!=null && !allPlayers.isEmpty()) {
                player = allPlayers.get(0);
            }
        }catch(Exception ex){
            
        }
        return player;
    }
    
    public static List<Player> searchPersons(int applicationId, String q) {
        List<Player> allPlayers = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.userType='person' and player.application ="+ applicationId +" player.name LIKE '%"+q+"%'");
        }catch(Exception ex){
            
        }
        return allPlayers;
    }
    
    public static List<Player> getAllTeamsByApplicationId(int applicationId){
        List<Player> allPlayers = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.userType='team' and player.application ="+applicationId);
        }catch(Exception ex){
            
        }
        return allPlayers;
    }
    
    public static Player getTeamById(int applicationId, String userId){
        List<Player> allPlayers;
        Player player = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.userType='team' and player.application ="+ applicationId +" and player.id ="+userId);
            if (allPlayers!=null && !allPlayers.isEmpty()) {
                player = allPlayers.get(0);
            }
        }catch(Exception ex){
            
        }
        return player;
    }
    
    public static List<Player> searchTeams(int applicationId, String q) {
        List<Player> allPlayers = null;
        try{
            allPlayers = HibernateUtil.executeHQLQuery("from Player as player where player.userType='person' and player.application ="+ applicationId +" and player.name LIKE '%"+q+"%'");
        }catch(Exception ex){
            
        }
        return allPlayers;
    }
    
    public static Player addPlayer(Player player) {
        try {
            player.setId(HibernateUtil.addEntity(player));
        } catch (Exception ex) {
            System.out.println("Error in PlayerDAL-addPlayer: " + ex.getMessage());
        }
        return player;
    }
    
    public static void editPlayer(Player player) {
        try {
            if (player.getId() != null && player.getId() > 0) {
                HibernateUtil.editEntity(player);
            }
        } catch (Exception ex) {
            System.out.println("Error in PlayerDAL-editPlayer: " + ex.getMessage());
        }
    }
    
    public static void deletePlayer(Player player) {
        try {
            if (player.getId() != null && player.getId() > 0) {
                HibernateUtil.deleteEntity(player);
            }
        } catch (Exception ex) {
            System.out.println("Error in PlayerDAL-deletePlayer: " + ex.getMessage());
        }
    }
}