package effortLoggerv2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DefectLog {
	private SimpleIntegerProperty defectNum;
	private SimpleStringProperty defectName;
	private SimpleStringProperty defectDetail;
	private SimpleIntegerProperty stepInjected;
	private SimpleIntegerProperty stepRemoved;
	private SimpleIntegerProperty defectCategory;
	private SimpleIntegerProperty defectStatus;
	private SimpleStringProperty defectFix;
	private SimpleStringProperty project;
	
	//constructor
	DefectLog(String project, int defectNum, String defectName, String defectDetail, int stepInjected, 
			int stepRemoved, int defectCategory, int defectStatus, String defectFix){
		this.project = new SimpleStringProperty(project);
		this.defectNum = new SimpleIntegerProperty(defectNum);
		this.defectName= new SimpleStringProperty(defectName);
		this.defectDetail= new SimpleStringProperty(defectDetail);
		this.stepInjected = new SimpleIntegerProperty(stepInjected);
		this.stepRemoved = new SimpleIntegerProperty(stepRemoved);
		this.defectCategory = new SimpleIntegerProperty(defectCategory);
		this.defectStatus = new SimpleIntegerProperty(defectStatus);
		this.defectFix = new SimpleStringProperty(defectFix);
			
	}
	
	//gets and sets B)
	//for project
	public String getProject() {
		return project.get();
	}
	public void setProject(String project) {
		this.project.set(project);
	}
	//for defect num
	public int getDefectNum() {
		return defectNum.get();
	}
	public void setProject(int defectNum) {
		this.defectNum.set(defectNum);
	}
	//for defect name
	public String getDefectName() {
		return defectName.get();
	}
	public void setDefectName(String defectName) {
		this.defectName.set(defectName);
	}
	//for defect detail
	public String getDefectDetail() {
		return defectDetail.get();
	}
	public void setDefectDetail(String defectDetail) {
		this.defectDetail.set(defectDetail);
	}
	//for step Injected
	public int getStepInjected() {
		return stepInjected.get();
	}
	public void setStepInjected(int stepInjected) {
		this.stepInjected.set(stepInjected);
	}
	//for step removed
	public int getStepRemoved() {
		return stepRemoved.get();
	}
	public void setStepRemoved(int stepRemoved) {
		this.stepRemoved.set(stepRemoved);
	}
	//for defect category
	public int getDefectCategory() {
		return defectCategory.get();
	}
	public void setDefectCategory(int defectCategory) {
		this.defectCategory.set(defectCategory);
	}
	//for defect status
	public int getDefectStatus() {
		return defectCategory.get();
	}
	public void setDefectStatus(int defectStatus) {
		this.defectStatus.set(defectStatus);
	}
	//for fix???? (idk what that is carter did NOT showcase it :|
	public String getDefectFix() {
		return defectFix.get();
	}
	public void setDefectFix(String defectFix) {
		this.defectFix.set(defectFix);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}