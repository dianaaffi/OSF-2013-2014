package entities;
// Generated Nov 24, 2013 1:07:37 PM by Hibernate Tools 3.6.0


import DAL.PlayerDAL;
import DAL.RewardDAL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jettison.json.JSONObject;

/**
 * PlayerReward generated by hbm2java
 */
public class PlayerReward  implements java.io.Serializable, JSONable {


     private Integer id;
     private Player player;
     private Reward reward;
     private Date attributionDate;
     private Float currentLevel;
     private Date completionDate;

    public PlayerReward() {
    }

	
    public PlayerReward(Player player, Reward reward, Date attributionDate) {
        this.player = player;
        this.reward = reward;
        this.attributionDate = attributionDate;
    }
    public PlayerReward(Player player, Reward reward, Date attributionDate, Float currentLevel, Date completionDate) {
       this.player = player;
       this.reward = reward;
       this.attributionDate = attributionDate;
       this.currentLevel = currentLevel;
       this.completionDate = completionDate;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Reward getReward() {
        return this.reward;
    }
    
    public void setReward(Reward reward) {
        this.reward = reward;
    }
    public Date getAttributionDate() {
        return this.attributionDate;
    }
    
    public void setAttributionDate(Date attributionDate) {
        this.attributionDate = attributionDate;
    }
    public Float getCurrentLevel() {
        return this.currentLevel;
    }
    
    public void setCurrentLevel(Float currentLevel) {
        this.currentLevel = currentLevel;
    }
    public Date getCompletionDate() {
        return this.completionDate;
    }
    
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    @Override
    public JSONObject toJSON() {
        try {
             JSONObject obj = new JSONObject();
             obj.put("id", this.id);
             obj.put("attributionDate", this.attributionDate);
             obj.put("currentLevel", this.currentLevel);
             obj.put("completionDate", this.completionDate);
             obj.put("player", this.player.toJSON());
             obj.put("reward", this.reward.toJSON());
             return obj;
         } catch (Exception ex) {
             
         }
         return null;
    }

    @Override
    public void fromJSON(JSONObject obj) {
        try {
            if (obj.has("attributionDate")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                this.setAttributionDate(df.parse(obj.getString("attributionDate")));
            }
            if (obj.has("currentLevel")) {
                this.setCurrentLevel((float)obj.getDouble("currentLevel"));
            }
            if (obj.has("completionDate")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                this.setCompletionDate(df.parse(obj.getString("completionDate")));
            }
            if (obj.has("player")) {
                this.setPlayer(PlayerDAL.getPlayerById(obj.getString("player")));
            }
            if (obj.has("reward")) {
                this.setReward(RewardDAL.getRewardById(obj.getInt("reward")));
            }
        } catch (Exception ex) {
            System.out.println("Error in Player-fromJson: " + ex.getMessage());
        }
    }




}


