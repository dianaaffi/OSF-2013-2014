package entities;
// Generated Nov 24, 2013 1:07:37 PM by Hibernate Tools 3.6.0

import DAL.ApplicationDAL;
import DAL.EventTypeDAL;
import DAL.ObjectTypeDAL;
import DAL.RewardDAL;
import org.codehaus.jettison.json.JSONObject;




/**
 * Action generated by hbm2java
 */
public class Action  implements java.io.Serializable, JSONable {


     private Integer id;
     private EventType eventType;
     private Application application;
     private ObjectType objectType;
     private Reward rewardByInputReward;
     private Reward rewardByReward;
     private String inputValue;
     private String outputValue;

    public Action() {
    }

	
    public Action(EventType eventType, Application application, Reward rewardByReward) {
        this.eventType = eventType;
        this.application = application;
        this.rewardByReward = rewardByReward;
    }
    public Action(EventType eventType, Application application, ObjectType objectType, Reward rewardByInputReward, Reward rewardByReward, String inputValue, String outputValue) {
       this.eventType = eventType;
       this.application = application;
       this.objectType = objectType;
       this.rewardByInputReward = rewardByInputReward;
       this.rewardByReward = rewardByReward;
       this.inputValue = inputValue;
       this.outputValue = outputValue;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public EventType getEventType() {
        return this.eventType;
    }
    
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    public Application getApplication() {
        return this.application;
    }
    
    public void setApplication(Application application) {
        this.application = application;
    }
    public ObjectType getObjectType() {
        return this.objectType;
    }
    
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }
    public Reward getRewardByInputReward() {
        return this.rewardByInputReward;
    }
    
    public void setRewardByInputReward(Reward rewardByInputReward) {
        this.rewardByInputReward = rewardByInputReward;
    }
    public Reward getRewardByReward() {
        return this.rewardByReward;
    }
    
    public void setRewardByReward(Reward rewardByReward) {
        this.rewardByReward = rewardByReward;
    }
    public String getInputValue() {
        return this.inputValue;
    }
    
    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }
    public String getOutputValue() {
        return this.outputValue;
    }
    
    public void setOutputValue(String outputValue) {
        this.outputValue = outputValue;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", this.getId());
            obj.put("application", this.getApplication());
            obj.put("eventType", this.getEventType());
            obj.put("objectType", this.getObjectType());
            obj.put("reward", this.getRewardByReward());
            obj.put("inputReward", this.getRewardByInputReward());
            obj.put("inputValue", this.getInputValue());
            obj.put("outputValue", this.getOutputValue());
        } catch (Exception ex) {
            System.out.println("Error Application-toJSON: " + ex.getMessage());
        }
        return obj;
   }

    @Override
    public void fromJSON(JSONObject obj) {
         try {
            if (obj.has("application")) {
                Application ap = ApplicationDAL.getApplicationById(Integer.parseInt(obj.getString("application")));
                this.setApplication(ap);
            }
            if (obj.has("id")) {
                this.setId(obj.getInt("id"));
            }
            if (obj.has("objectType")) {
                ObjectType ap = ObjectTypeDAL.getObjectTypeById(Integer.parseInt(obj.getString("objectType")));
                this.setObjectType(ap);
            }
            if (obj.has("eventType")) {
                EventType ap = EventTypeDAL.getEventTypeById(Integer.parseInt(obj.getString("eventType")));
                this.setEventType(ap);
            }
            if (obj.has("reward")) {
                Reward re = RewardDAL.getRewardById(Integer.parseInt(obj.getString("reward")));
                this.setRewardByReward(re);
            }
            if (obj.has("inputReward")) {
                Reward re = RewardDAL.getRewardById(Integer.parseInt(obj.getString("inputReward")));
                this.setRewardByInputReward(re);
            }
            if (obj.has("inputValue")) {        
                this.setInputValue(obj.getString("inputValue"));
            }
            if (obj.has("outputValue")) {
                this.setOutputValue(obj.getString("outputValue"));
            }
        } catch (Exception ex) {
            System.out.println("Error in Application-fromJson: " + ex.getMessage());
        }
    }
}


