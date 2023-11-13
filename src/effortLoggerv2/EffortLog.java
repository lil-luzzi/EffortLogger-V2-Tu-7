//this file was made by Jonathan
package effortLoggerv2;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EffortLog {
	// EffortLog Time Info
	private final SimpleObjectProperty<LocalDateTime> startDateTime;
	private final SimpleObjectProperty<LocalDateTime> stopDateTime;
	private final SimpleLongProperty timeElapsed;
	
	// EffortLog Project Info
	private SimpleStringProperty project;
	private SimpleStringProperty plan;
	private SimpleStringProperty lifecycleStep;
	
	// EffortLog User Info
	private SimpleStringProperty userGroup;
	private SimpleStringProperty employeeRank;
	private SimpleStringProperty effortCategory;
	
	
	EffortLog(LocalDateTime startDateTime, LocalDateTime stopDateTime, 
			long timeElapsed, String project, String plan, String lifecycleStep,
			String userGroup, String employeeRank, String effortCategory){
		this.startDateTime = new SimpleObjectProperty<LocalDateTime>(startDateTime);
		this.stopDateTime = new SimpleObjectProperty<LocalDateTime>(stopDateTime);
		this.timeElapsed = new SimpleLongProperty(timeElapsed);
		this.project = new SimpleStringProperty(project);
		this.plan = new SimpleStringProperty(plan);
		this.lifecycleStep = new SimpleStringProperty(lifecycleStep);
		this.userGroup = new SimpleStringProperty(userGroup);
		this.employeeRank = new SimpleStringProperty(employeeRank);
		this.effortCategory = new SimpleStringProperty(effortCategory);
	}

	
	// getters and setters for modifying data (TODO)
	public LocalDateTime getStartDateTime() {
		return startDateTime.get();
	}


	public void setStartDateTime(LocalDateTime startTime) {
		this.startDateTime.set(startTime);
	}
	
	
	public ObjectProperty<LocalDateTime> startDateTimeProperty() {
		return this.startDateTime;
	}


	public LocalDateTime getStopDateTime() {
		return stopDateTime.get();
	}


	public void setStopDateTime(LocalDateTime stopTime) {
		this.stopDateTime.set(stopTime);
	}
	
	
	public ObjectProperty<LocalDateTime> stopDateTimeProperty() {
		return this.stopDateTime;
	}


	public long getTimeElapsed() {
		return timeElapsed.get();
	}


	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed.set(timeElapsed);
	}
	
	
	public LongProperty timeElapsedProperty() {
		return this.timeElapsed;
	}


	public String getProject() {
		return project.get();
	}


	public void setProject(String project) {
		this.project.set(project);
	}
	
	public StringProperty projectProperty(){
		return this.project;
	}
	
	
	public String getPlan() {
		return plan.get();
	}


	public void setPlan(String plan) {
		this.plan.set(plan);
	}
	
	
	public StringProperty planProperty(){
		return this.plan;
	}


	public String getLifecycleStep() {
		return lifecycleStep.get();
	}


	public void setLifecycleStep(String lifecycleStep) {
		this.lifecycleStep.set(lifecycleStep);
	}
	
	
	public StringProperty lifecycleStepProperty(){
		return this.lifecycleStep;
	}


	public String getUserGroup() {
		return userGroup.get();
	}


	public void setUserGroup(String userGroup) {
		this.userGroup.set(userGroup);
	}
	
	
	public StringProperty userGroupProperty() {
		return this.userGroup;
	}


	public String getEmployeeRank() {
		return employeeRank.get();
	}


	public void setEmployeeRank(String employeeRank) {
		this.employeeRank.set(employeeRank);
	}
	
	
	public StringProperty employeeRankProperty() {
		return this.employeeRank;
	}


	public String getEffortCategory() {
		return effortCategory.get();
	}


	public void setEffortCategory(String effortCategory) {
		this.effortCategory.set(effortCategory);
	}
	
	
	public StringProperty effortCategoryProperty() {
		return this.effortCategory;
	}
}