package effortLoggerv2;

import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserStory {
    private final SimpleStringProperty title;
    private final SimpleStringProperty feature;
    private final SimpleStringProperty reason;		//variables
    private final SimpleStringProperty typeOfUser;
    private final SimpleStringProperty priority;
    private final SimpleStringProperty estimateStoryPoints;
    private final SimpleBooleanProperty actioned;
    private final SimpleStringProperty actualPointScore;
    
    //constructor

    UserStory(String title, String feature, String reason,
              String typeOfUser, String priority) {
        this.title = new SimpleStringProperty(title);
        this.feature = new SimpleStringProperty(feature);
        this.reason = new SimpleStringProperty(reason);
        this.typeOfUser = new SimpleStringProperty(typeOfUser);
        this.priority = new SimpleStringProperty(priority);
        this.estimateStoryPoints = new SimpleStringProperty("0");
        this.actioned = new SimpleBooleanProperty(false);
        this.actualPointScore = new SimpleStringProperty("Not Assigned");
    }

    /*
     getters/setters
     */
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getFeature() {
        return feature.get();
    }

    public void setFeature(String feature) {
        this.feature.set(feature);
    }

    public StringProperty featureProperty() {
        return feature;
    }

    public String getReason() {
        return reason.get();
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }

    public StringProperty reasonProperty() {
        return reason;
    }

    public String getTypeOfUser() {
        return typeOfUser.get();
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser.set(typeOfUser);
    }

    public StringProperty typeOfUserProperty() {
        return typeOfUser;
    }

    public String getPriority() {
        return priority.get();
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public String getEstimateStoryPoints() {
        return estimateStoryPoints.get();
    }

    public void setEstimateStoryPoints(String estimateStoryPoints) {
        this.estimateStoryPoints.set(estimateStoryPoints);
    }

    public StringProperty estimateStoryPointsProperty() {
        return estimateStoryPoints;
    }
    public boolean isActioned() {
        return actioned.get();
    }

    public void setActioned(boolean actioned) {
        this.actioned.set(actioned);
    }

    public BooleanProperty actionedProperty() {
        return actioned;
    }
    
    public String getActualPointScore() {
        return actualPointScore.get();
    }

    public void setActualPointScore(String actualPointScore) {
        this.actualPointScore.set(actualPointScore);
    }

    public StringProperty actualPointScoreProperty() {
        return actualPointScore;
    }
}
