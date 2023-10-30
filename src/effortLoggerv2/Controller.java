//this file was made by Luz and Jonathan
package effortLoggerv2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;


public class Controller implements Initializable {
	//create vector for the user story table
	Vector<Vector<?>> userTable = new Vector<Vector<?>>(6);	//User table with 6 columns
	
	//6 categories for the calculation view
	Vector<String> titleVect;
	Vector<String> priotityVect;
	Vector<String> estimateVect;		//The different columns
	Vector<String> typeOfUserVect;
	Vector<String> funcVect;
	Vector<String> benefitVect;
	
	@FXML
	private TextField title;
	@FXML
	private ChoiceBox<String> priorityBox;
	@FXML
	private ChoiceBox<String> userBox;
	@FXML
	private TextField feature;
	@FXML
	private TextField reason;
	@FXML
	private Button createUserStoryBtn;
	
	@FXML
	private TableView<UserStory> userStoryTable;		//FXML assignments, pairing the main FXML file to the controller.
	@FXML
	private TableColumn<UserStory, String> titleCol;
	@FXML
	private TableColumn<UserStory, String> priority;
	@FXML
	private TableColumn<UserStory, String> estimateCol;
	@FXML
	private TableColumn<UserStory, String> typeOfUserCol;
	@FXML
	private TableColumn<UserStory, String> funcCol;
	@FXML
	private TableColumn<UserStory, String> benefitCol;
	
	ObservableList<UserStory> userStories = FXCollections.observableArrayList();
	StatisticalInsightTool insightTool = new StatisticalInsightTool(userStories);	//Creates the table and sets up the insight tool
	
	// Create a Vector of dynamic Vectors to make a table (Matrix)
	Vector<Vector<?>> effortLogTable = new Vector<Vector<?>>(9);
	
	// 9 categories for the effort log
	Vector <LocalDateTime> startDateTimeVect;
	Vector <LocalDateTime> stopDateTimeVect;
	Vector <Long> timeElapsedVect;
	
	Vector <String> projectVect;
	Vector <String> planVect;
	Vector <String> lifecycleStepVect;
	
	Vector <String> userGroupVect;
	Vector <String> employeeRankVect;
	Vector <String> effortCategoryVect;
	
	@FXML
	private Rectangle clockIndicatorBox; // turns red when stopped, green when running
	@FXML
	private Label clockIndicatorLabel; // indicates when stopped and started
	
	@FXML
	private ChoiceBox<String> myChoiceBox;
	@FXML
	private ChoiceBox<String> myChoiceBox2;
	@FXML
	private ChoiceBox<String> myChoiceBox3;
	@FXML
	private ChoiceBox<String> myChoiceBox4;
	@FXML
	private ChoiceBox<String> myChoiceBox5;
	@FXML
	private ChoiceBox<String> myChoiceBox6;
	
	private String[] choices = {"Business Project", "Development Project"};
	private String[] choices2 = {"Planning", "Information Gathering", "Information Understanding", "Verifying", 
			"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
	private String[] choices3 = {"Plans                 ", "Deliverables", "Interruptions", "Defects", "Others"};
	private String[] choices4 = {"Project Plan", "Risk Management Plan", "Conceptual Design Plan", 
			"Detailed Design Plan", "Implementation Plan"};
	private String[] choices5 = {"Team 1", "Team 2", "Team 3"};
	private String[] choices6 = {"Developer 1", "Developer 2", "Developer 3", "Developer 4",
			"Developer 5","Developer 6","Developer 7"};
	private String[] choices7 = {"High", "Medium", "Low"};
	private String[] choices8 = {"Type 1", "Type 2", "Type 3", "Type 4"};
	
	// user starts timer
	private boolean isLogging = false;
	private LocalDateTime startDateTime;
	private long startTime;
	
	// user stops timer, generates EffortLog
	private LocalDateTime stopDateTime;
	private long stopTime;
	
	@FXML
	private TableView<EffortLog> effortLogs;
	// TODO there should be some way to iterate through this and the vectors
	@FXML
	private TableColumn<EffortLog, LocalDateTime> startDateTimeCol;
	@FXML
	private TableColumn<EffortLog, LocalDateTime> stopDateTimeCol;
	@FXML
	private TableColumn<EffortLog, Long> timeElapsedCol;
	@FXML
	private TableColumn<EffortLog, String> projectCol;
	@FXML
	private TableColumn<EffortLog, String> planCol;
	@FXML
	private TableColumn<EffortLog, String> lifecycleStepCol;
	@FXML
	private TableColumn<EffortLog, String> userGroupCol;
	@FXML
	private TableColumn<EffortLog, String> employeeRankCol;
	@FXML
	private TableColumn<EffortLog, String> effortCategoryCol;
	
	Vector<EffortLog> data = new Vector<EffortLog>(1);
	

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Sets the data in the user story table
		userStoryTable.setItems(userStories);
		
		//populates choice boxes with values
		priorityBox.getItems().addAll(choices7);
		userBox.getItems().addAll(choices8);
		
		//Linking particular variables to specific cells in the table
		titleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
		priority.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority()));
		estimateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstimateStoryPoints()));
		typeOfUserCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeOfUser()));
		funcCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFeature()));
		benefitCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReason()));
		
		
		// add choices to dropdown choice boxes
		myChoiceBox.getItems().addAll(choices);
		myChoiceBox2.getItems().addAll(choices2);
		myChoiceBox3.getItems().addAll(choices3);
		myChoiceBox4.getItems().addAll(choices4);
		myChoiceBox5.getItems().addAll(choices5);
		myChoiceBox6.getItems().addAll(choices6);
		
		// add columns to the effortLogTable
		effortLogTable.add(0, new Vector<LocalDateTime>());
		effortLogTable.add(1, new Vector<LocalDateTime>());
		effortLogTable.add(2, new Vector<Long>());
		
		effortLogTable.add(3, new Vector<String>());
		effortLogTable.add(4, new Vector<String>());
		effortLogTable.add(5, new Vector<String>());
		
		effortLogTable.add(6, new Vector<String>());
		effortLogTable.add(7, new Vector<String>());
		effortLogTable.add(8, new Vector<String>());
		
		// intialize each column in the table
		startDateTimeVect = (Vector<LocalDateTime>) effortLogTable.get(0);
		stopDateTimeVect = (Vector<LocalDateTime>) effortLogTable.get(1);
		timeElapsedVect = (Vector<Long>) effortLogTable.get(2);
		
		projectVect = (Vector<String>) effortLogTable.get(3);
		planVect = (Vector<String>) effortLogTable.get(4);
		lifecycleStepVect = (Vector<String>) effortLogTable.get(5);

		userGroupVect = (Vector<String>) effortLogTable.get(6);
		employeeRankVect = (Vector<String>) effortLogTable.get(7);
		effortCategoryVect = (Vector<String>) effortLogTable.get(8);
		
		// set column names to port over to the table
		startDateTimeCol.setText("Start Date & Time");
		stopDateTimeCol.setText("Stop Date & Time");
		timeElapsedCol.setText("Time Elapsed");
		projectCol.setText("Project");
		planCol.setText("Plan");
		lifecycleStepCol.setText("Lifecycle Step");
		userGroupCol.setText("User Group");
		employeeRankCol.setText("Employee Rank");
		effortCategoryCol.setText("Effort Category");
		
		// set cell factories using property names (associate data names and values)
		startDateTimeCol.setCellValueFactory(new PropertyValueFactory<EffortLog, LocalDateTime>("startDateTime"));
		stopDateTimeCol.setCellValueFactory(new PropertyValueFactory<EffortLog, LocalDateTime>("stopDateTime"));
		timeElapsedCol.setCellValueFactory(new PropertyValueFactory<EffortLog, Long>("timeElapsed"));
		projectCol.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("project"));
		planCol.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("plan"));
		lifecycleStepCol.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("lifecycleStep"));
		userGroupCol.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("userGroup"));
		employeeRankCol.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("employeeRank"));
		effortCategoryCol.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("effortCategory"));
	}
	

	public boolean areFieldsFull() {
		// Check if the user did their civic duty and filled out
		// EffortLogger essential information, denies access if not
		if (myChoiceBox.getValue() == null | 
				myChoiceBox2.getValue() == null |
				myChoiceBox3.getValue() == null |
				myChoiceBox4.getValue() == null |
				myChoiceBox5.getValue() == null |
				myChoiceBox6.getValue() == null) {
			return false;
		}
		else { return true; }
	}
	
	
	@FXML
	public void timerStart(ActionEvent event) {
		if (!isLogging) {
			// take note of current date time for log and time in ms for elapsed time
			setStartDateTime(LocalDateTime.now());
			startTime = System.currentTimeMillis();
			
			clockIndicatorBox.setFill(Color.GREEN);
			clockIndicatorLabel.setText("Clock is Running");
			isLogging = true;
		}
	}
	
	
	@FXML
	public void timerStop(ActionEvent event) {
		if (isLogging & areFieldsFull()) {
			// take note of current date time for log and time in ms for elapsed time
			setStopDateTime(LocalDateTime.now());
			stopTime = System.currentTimeMillis();
			
			// Debug statement, TODO turn into data entries.
			long elapsedTime = stopTime - startTime;
			System.out.println("Time Elapsed : " + String.format("%d min, %d sec", 
				    TimeUnit.MILLISECONDS.toMinutes(elapsedTime),
				    TimeUnit.MILLISECONDS.toSeconds(elapsedTime) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedTime))));
			
			// adds each value to the applicable table column/row
			EffortLog newEffortLog = new EffortLog(startDateTime, stopDateTime, elapsedTime,
					myChoiceBox.getValue(), myChoiceBox4.getValue(), myChoiceBox2.getValue(), 
					myChoiceBox5.getValue(), myChoiceBox6.getValue(), myChoiceBox3.getValue());
			
			parseEffortLog(newEffortLog);
			
			// reset the logging timer/indicators
			clockIndicatorBox.setFill(Color.RED);
			clockIndicatorLabel.setText("Clock is Stopped");
			isLogging = false;
			
			// reset the start and stop datetime
			setStartDateTime(null);
			setStopDateTime(null);
			
			// clear the choice box choices
			myChoiceBox.setValue(null);
			myChoiceBox2.setValue(null);
			myChoiceBox3.setValue(null);
			myChoiceBox4.setValue(null);
			myChoiceBox5.setValue(null);
			myChoiceBox6.setValue(null);
		}
	}
	
	
	public void parseEffortLog(EffortLog e) {
		// add data to vector objects
		startDateTimeVect.add(e.getStartDateTime());
		stopDateTimeVect.add(e.getStopDateTime());
		timeElapsedVect.add(e.getTimeElapsed());
		projectVect.add(e.getProject());
		planVect.add(e.getPlan());
		lifecycleStepVect.add(e.getLifecycleStep());
		userGroupVect.add(e.getUserGroup());
		employeeRankVect.add(e.getEmployeeRank());
		effortCategoryVect.add(e.getEffortCategory());
		
		// add effort log to the running observable list
		data.add(e);
		
		final ObservableList<EffortLog> writeData = FXCollections.observableArrayList(data);
		
		// update data on table columns
		effortLogs.setItems(writeData);
	}


	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}


	public LocalDateTime getStopDateTime() {
		return stopDateTime;
	}


	public void setStopDateTime(LocalDateTime stopDateTime) {
		this.stopDateTime = stopDateTime;
	}
	
	public void createUserStory() {
		String storyTitle = title.getText();
		String storyPriority = priorityBox.getValue();
		String userType = userBox.getValue();
		String storyFeature = feature.getText();
		String storyReason = reason.getText();
		
		int priorityVal = 0;
		
		switch(storyPriority) {
		case "High":
			priorityVal = 3;
			break;
		case "Medium":
			priorityVal = 2;
			break;
		case "Low" :
			priorityVal = 1;
			break;
		}
		
		String priVal = Integer.toString(priorityVal);
		
		if(!storyPriority.equals(""))
				{
		
			UserStory newUserStory = new UserStory(storyTitle, storyFeature, 
					storyReason, userType, priVal);
			newUserStory.setEstimateStoryPoints(Integer.toString(insightTool.calcEstimate(newUserStory)));
			userStories.add(newUserStory);	
		
			title.clear();
			priorityBox.setValue(null);
			userBox.setValue(null);
			feature.clear();
			reason.clear();
				}
		}
}