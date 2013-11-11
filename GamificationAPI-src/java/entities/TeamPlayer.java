package entities;
// Generated Nov 4, 2013 10:45:52 PM by Hibernate Tools 3.6.0



/**
 * TeamPlayer generated by hbm2java
 */
public class TeamPlayer  implements java.io.Serializable {


     private Integer id;
     private Player playerByPlayerId;
     private Player playerByTeamId;
     private TeamRole teamRole;

    public TeamPlayer() {
    }

    public TeamPlayer(Player playerByPlayerId, Player playerByTeamId, TeamRole teamRole) {
       this.playerByPlayerId = playerByPlayerId;
       this.playerByTeamId = playerByTeamId;
       this.teamRole = teamRole;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Player getPlayerByPlayerId() {
        return this.playerByPlayerId;
    }
    
    public void setPlayerByPlayerId(Player playerByPlayerId) {
        this.playerByPlayerId = playerByPlayerId;
    }
    public Player getPlayerByTeamId() {
        return this.playerByTeamId;
    }
    
    public void setPlayerByTeamId(Player playerByTeamId) {
        this.playerByTeamId = playerByTeamId;
    }
    public TeamRole getTeamRole() {
        return this.teamRole;
    }
    
    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }




}


