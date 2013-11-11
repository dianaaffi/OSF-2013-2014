package entities;
// Generated Nov 4, 2013 10:45:52 PM by Hibernate Tools 3.6.0


import DAL.RewardDAL;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jettison.json.JSONObject;

/**
 * Application generated by hbm2java
 */
public class Application  implements java.io.Serializable, JSONable {


     private Integer id;
     private String name;
     private String apiKey;
     private String apiSecret;
     private Set<Player> players = new HashSet<Player>(0);
     private Set<Action> actions = new HashSet<Action>(0);
     private Set<Reward> rewards = new HashSet<Reward>(0);
     private Set<EventType> eventTypes = new HashSet<EventType>(0);
     private Set<ObjectType> objectTypes = new HashSet<ObjectType>(0);

    public Application() {
    }

	
    public Application(String name, String apiKey, String apiSecret) {
        this.name = name;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }
    public Application(String name, String apiKey, String apiSecret, Set<Player> players, Set<Action> actions, Set<Reward> rewards, Set<EventType> eventTypes, Set<ObjectType> objectTypes) {
       this.name = name;
       this.apiKey = apiKey;
       this.apiSecret = apiSecret;
       this.players = players;
       this.actions = actions;
       this.rewards = rewards;
       this.eventTypes = eventTypes;
       this.objectTypes = objectTypes;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getApiKey() {
        return this.apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getApiSecret() {
        return this.apiSecret;
    }
    
    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }
    public Set<Player> getPlayers() {
        return this.players;
    }
    
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    public Set<Action> getActions() {
        return this.actions;
    }
    
    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }
    public Set<Reward> getRewards() {
        return this.rewards;
    }
    
    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
    }
    public Set<EventType> getEventTypes() {
        return this.eventTypes;
    }
    
    public void setEventTypes(Set<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }
    public Set<ObjectType> getObjectTypes() {
        return this.objectTypes;
    }
    
    public void setObjectTypes(Set<ObjectType> objectTypes) {
        this.objectTypes = objectTypes;
    }

@Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", this.getId());
            obj.put("name", this.getName());
            obj.put("apiSecret", this.getApiSecret());
            obj.put("apiKey", this.getApiKey());
        } catch (Exception ex) {
            System.out.println("Error Application-toJSON: " + ex.getMessage());
        }
        return obj;
    }

    @Override
    public void fromJSON(JSONObject obj) {
        try {
            if (obj.has("name")) {
                this.setName(obj.getString("name"));
            }
            if (obj.has("id")) {
                this.setId(obj.getInt("id"));
            }
            if (obj.has("apiSecret")) {
                this.setApiSecret(obj.getString("apiSecret"));
            }
            if (obj.has("apiKey")) {
                this.setApiSecret(obj.getString("apiKey"));
            }
        } catch (Exception ex) {
            System.out.println("Error in Application-fromJson: " + ex.getMessage());
        }
    }


}


