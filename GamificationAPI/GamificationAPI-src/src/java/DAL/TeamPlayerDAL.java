/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import entities.Player;
import entities.TeamPlayer;
import entities.TeamRole;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author joseph
 */
public class TeamPlayerDAL {
    
    public static TeamPlayer getTeamPlayerByPlayerAndTeam (Player player, Player team) {
        List<TeamPlayer> allTeamPlayers = null;
        try{
            allTeamPlayers = HibernateUtil.executeHQLQuery("from TeamPlayer as tp where tp.playerId ="+player.getId()+" and tp.teamId ="+team.getId());
        }catch(Exception ex){
            System.out.println("Error in TeamPlayerDAL-addPlayerToTeam: " + ex.getMessage());
        }
        
        if(allTeamPlayers != null && !allTeamPlayers.isEmpty()) {
            return allTeamPlayers.get(0);
        }
        return null;
    }
    
    public static Exception addPlayerToTeamWithRole (Player player, Player team, TeamRole role) {
        try {
            if(team.getUserType().equals("team")) {
                TeamPlayer teamPlayer = new TeamPlayer(player, team, role);
                HibernateUtil.addEntity(teamPlayer);
            } else {
                return new Exception("Erro adding Player to a non Team Player");
            }
        } catch (Exception ex) {
            System.out.println("Error in TeamPlayerDAL-addPlayerToTeam: " + ex.getMessage());
        }
        return null;
    }
    
    public static Exception removePlayerFromTeam (Player player, Player team) {
        try {
            TeamPlayer tp =TeamPlayerDAL.getTeamPlayerByPlayerAndTeam(player, team);
            if (tp != null) {
                HibernateUtil.deleteEntity(tp);
            } else {
                return new Exception("Error: Player not in Team");
            }
        } catch (Exception ex) {
            System.out.println("Error in TeamPlayerDAL-removePlayerFromTeam: " + ex.getMessage());
        }
        return null;
    }
    
    public static Exception editPlayerRoleInTeam (Player player, Player team, TeamRole role) {
        try {
            TeamPlayer tp = TeamPlayerDAL.getTeamPlayerByPlayerAndTeam(player, team);
            if (tp != null) {
                tp.setTeamRole(role);
                HibernateUtil.editEntity(tp);
            } else {
                return new Exception("Error: Player not in Team");
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static TeamRole getTeamRoleById (int teamRole) {
        
        List<TeamRole> allTeamRoles = null;
        try{
            allTeamRoles = HibernateUtil.executeHQLQuery("from TeamRole as tr where tr.id ="+teamRole);
        }catch(Exception ex){
            System.out.println("Error in TeamPlayerDAL-addTeamRole: " + ex.getMessage());
        }
        
        if(allTeamRoles != null && !allTeamRoles.isEmpty()) {
            return allTeamRoles.get(0);
        }
        return null;
        
    }
    
    public static List<TeamRole> getAllTeamRoles () {
        
        List<TeamRole> allTeamRoles = null;
        try{
            allTeamRoles = HibernateUtil.executeHQLQuery("from TeamRole");
        }catch(Exception ex){
            System.out.println("Error in TeamPlayerDAL-addTeamRole: " + ex.getMessage());
        }
        
        return allTeamRoles;
        
    }
    
}
