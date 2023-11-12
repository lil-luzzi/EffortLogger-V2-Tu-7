package effortLoggerv2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DefectLog {
	private SimpleIntegerProperty defectNum;
	private SimpleStringProperty defectName;
	private SimpleStringProperty defectDetail;
	private SimpleStringProperty stepInjected;
	private SimpleStringProperty stepRemoved;
	private SimpleStringProperty defectCategory;
	private SimpleStringProperty defectStatus;
	private SimpleStringProperty defectFix;
	private SimpleStringProperty project;
	
	//constructor
	DefectLog(String project, int defectNum, String defectName, String defectDetail, String stepInjected, 
			String stepRemoved, String defectCategory, String defectStatus, String defectFix){
		this.project = new SimpleStringProperty(project);
		this.defectNum = new SimpleIntegerProperty(defectNum);
		this.defectName= new SimpleStringProperty(defectName);
		this.defectDetail= new SimpleStringProperty(defectDetail);
		this.stepInjected = new SimpleStringProperty(stepInjected);
		this.stepRemoved = new SimpleStringProperty(stepRemoved);
		this.defectCategory = new SimpleStringProperty(defectCategory);
		this.defectStatus = new SimpleStringProperty(defectStatus);
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
	public StringProperty projectProperty() {
	        return project;
	}
	//for defect num
	public int getDefectNum() {
		return defectNum.get();
	}
	public void setDefectNum(int defectNum) {
		this.defectNum.set(defectNum);
	}
	public IntegerProperty defectNumProperty() {
        return defectNum;
}
	//for defect name
	public String getDefectName() {
		return defectName.get();
	}
	public void setDefectName(String defectName) {
		this.defectName.set(defectName);
	}
	public StringProperty defectNameProperty() {
        return defectName;
	}
	//for defect detail
	public String getDefectDetail() {
		return defectDetail.get();
	}
	public void setDefectDetail(String defectDetail) {
		this.defectDetail.set(defectDetail);
	}
	public StringProperty defectDetailProperty() {
        return defectDetail;
	}
	//for step Injected
	public String getStepInjected() {
		return stepInjected.get();
	}
	public void setStepInjected(String stepInjected) {
		this.stepInjected.set(stepInjected);
	}
	public StringProperty stepInjectedProperty() {
        return stepInjected;
	}
	//for step removed
	public String getStepRemoved() {
		return stepRemoved.get();
	}
	public void setStepRemoved(String stepRemoved) {
		this.stepRemoved.set(stepRemoved);
	}
	public StringProperty stepRemovedProperty() {
        return stepRemoved;
	}
	//for defect category
	public String getDefectCategory() {
		return defectCategory.get();
	}
	public void setDefectCategory(String defectCategory) {
		this.defectCategory.set(defectCategory);
	}
	public StringProperty defectCategoryProperty() {
        return defectCategory;
	}
	//for defect status
	public String getDefectStatus() {
		return defectCategory.get();
	}
	public void setDefectStatus(String defectStatus) {
		this.defectStatus.set(defectStatus);
	}
	public StringProperty defectStatusProperty() {
        return defectStatus;
	}
	//for fix???? (idk what that is carter did NOT showcase it :|
	public String getDefectFix() {
		return defectFix.get();
	}
	public void setDefectFix(String defectFix) {
		this.defectFix.set(defectFix);
	}
	public StringProperty defectFixProperty() {
        return defectFix;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}