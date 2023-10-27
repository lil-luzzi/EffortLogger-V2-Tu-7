package effortLoggerv2;

import java.time.LocalDateTime;


public class EffortLog {
	// EffortLog Time Info
	private LocalDateTime startTime;
	private LocalDateTime stopTime;
	private long timeElapsed;
	
	// EffortLog Project Info
	private String project;
	private String plan;
	private String lifecycleStep;
	
	// EffortLog User Info
	private String userGroup;
	private String employeeRank;
	private String effortCategory;
	
	
	EffortLog(LocalDateTime startTime, LocalDateTime stopTime, 
			long timeElapsed, String project, String plan, String lifecycleStep,
			String userGroup, String employeeRank, String effortCategory){
		this.setStartTime(startTime);
		this.setStopTime(stopTime);
		this.setTimeElapsed(timeElapsed);
		this.setProject(project);
		this.setPlan(plan);
		this.setLifecycleStep(lifecycleStep);
		this.setUserGroup(userGroup);
		this.setEmployeeRank(employeeRank);
		this.setEffortCategory(effortCategory);
	}

	
	// getters and setters for modifying data (TODO)
	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getStopTime() {
		return stopTime;
	}


	public void setStopTime(LocalDateTime stopTime) {
		this.stopTime = stopTime;
	}


	public long getTimeElapsed() {
		return timeElapsed;
	}


	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}


	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}
	
	
	public String getPlan() {
		return plan;
	}


	public void setPlan(String plan) {
		this.plan = plan;
	}


	public String getLifecycleStep() {
		return lifecycleStep;
	}


	public void setLifecycleStep(String lifecycleStep) {
		this.lifecycleStep = lifecycleStep;
	}


	public String getUserGroup() {
		return userGroup;
	}


	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}


	public String getEmployeeRank() {
		return employeeRank;
	}


	public void setEmployeeRank(String employeeRank) {
		this.employeeRank = employeeRank;
	}


	public String getEffortCategory() {
		return effortCategory;
	}


	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}
}
