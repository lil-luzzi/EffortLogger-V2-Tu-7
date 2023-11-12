//this file was made by Luz and Jonathan
package effortLoggerv2;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


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
	Vector<String> descVect;
	
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
	private TextField description;
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
	
	@FXML
	private ComboBox<String> groupSelect;
	@FXML
	private RadioButton groupSelectButton;
	@FXML
	private ComboBox<String> roleSelect;
	@FXML
	private RadioButton roleSelectButton;
	
	@FXML
	private ChoiceBox<String> monthSelect;
	@FXML
	private ChoiceBox<String> daySelect;
	@FXML
	private ChoiceBox<String> yearSelect;
	
	private boolean groupSelected = false;
	private boolean roleSelected = false;
	
	private String prodReportSelection;
	@FXML
	private Label prodGraphLabel;
	@FXML
	private BarChart <String, Integer> laborHoursChart;
	@FXML
	private BarChart <String, Integer> ticketsCompletedChart;
	
	final ToggleGroup prodReportGroup = new ToggleGroup();
	
	// labor series for prototype
	XYChart.Series<String, Integer> laborSeries1;
	XYChart.Series<String, Integer> laborSeries2;
	XYChart.Series<String, Integer> laborSeries3;
	XYChart.Series<String, Integer> laborSeries4;
	XYChart.Series<String, Integer> laborSeries5;
	XYChart.Series<String, Integer> laborSeries6;
	// ticket series for prototype
	XYChart.Series<String, Integer> ticketSeries1;
	XYChart.Series<String, Integer> ticketSeries2;
	XYChart.Series<String, Integer> ticketSeries3;
	XYChart.Series<String, Integer> ticketSeries4;
	XYChart.Series<String, Integer> ticketSeries5;
	XYChart.Series<String, Integer> ticketSeries6;

	private String[] choices = {"Business Project", "Development Project"};
	//life-cycle step
	private String[] choices2 = {"Planning", "Information Gathering", "Information Understanding", "Verifying", 
			"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};

	//effort category
	private String[] choices3 = {"Plans", "Deliverables", "Interruptions", "Defects", "Others"};
	//plans
	private String[] choices4 = {"Project Plan", "Risk Management Plan", "Conceptual Design Plan", 
			"Detailed Design Plan", "Implementation Plan"};
	//team numbers
	private String[] choices5 = {"Team 1", "Team 2", "Team 3"};
	//employee rank
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
	
	//Private Feedback Tool variables - Anton Nguyen
	//TextFields from UI
	@FXML
	private TextField pft_text1;
	@FXML
	private TextField pft_text2;
	@FXML
	private TextField pft_text3;
	@FXML
	private TextField pft_text4;
	
	//bar chart variables
	@FXML
	private BarChart<String, Number> pft_chart;               
	private CategoryAxis xAxis = new CategoryAxis();   
	private NumberAxis yAxis = new NumberAxis();
	
	//integer variables which use TextField data
	private int projectsCmp;
	private int overtime;
	private int peerRating;
	private int solutionCnt;
	
	//iterator for the term a graph is representing
	private int termCount = 0;

	//end of Private Feedback Tool variables
	
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

		/*
		 * OPTIONAL PARAMS SET AUTOMATICALLY
		myChoiceBox.setValue("Business Project");
		myChoiceBox2.setValue("Planning");
		myChoiceBox3.setValue("Plans");
		myChoiceBox4.setValue("Project Plan");
		myChoiceBox5.setValue("Team 1");
		myChoiceBox6.setValue("Developer 1");
		*/
		
		// add options to group and role selections
		groupSelect.getItems().addAll(choices5);
		roleSelect.getItems().addAll(choices6);
		
		monthSelect.getItems().addAll("11");
		daySelect.getItems().addAll("24");
		yearSelect.getItems().addAll("23");
		
		// add radio buttons to group
		groupSelectButton.setToggleGroup(prodReportGroup);
		roleSelectButton.setToggleGroup(prodReportGroup);
		
		// dummy data for bar charts
		laborSeries1 = new XYChart.Series<String, Integer>();
		laborSeries1.setName("Team 1 Labor Hours");
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.24.23", 50));
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.25.23", 101));
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.26.23", 80));
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.27.23", 75));
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.28.23", 42));
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.29.23", 35));
		laborSeries1.getData().add(new XYChart.Data<String, Integer>("11.30.23", 15));
		
		ticketSeries1 = new XYChart.Series<String, Integer>();
		ticketSeries1.setName("Team 1 Tickets");
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.24.23", 3));
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.25.23", 17));
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.26.23", 5));
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.27.23", 8));
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.28.23", 2));
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.29.23", 5));
		ticketSeries1.getData().add(new XYChart.Data<String, Integer>("11.30.23", 1));
		
		laborSeries2 = new XYChart.Series<String, Integer>();
		laborSeries2.setName("Team 2 Labor Hours");
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.24.23", 20));
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.25.23", 22));
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.26.23", 42));
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.27.23", 32));
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.28.23", 54));
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.29.23", 75));
		laborSeries2.getData().add(new XYChart.Data<String, Integer>("11.30.23", 102));
		
		ticketSeries2 = new XYChart.Series<String, Integer>();
		ticketSeries2.setName("Team 2 Tickets");
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.24.23", 3));
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.25.23", 17));
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.26.23", 5));
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.27.23", 7));
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.28.23", 3));
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.29.23", 6));
		ticketSeries2.getData().add(new XYChart.Data<String, Integer>("11.30.23", 20));
		
		laborSeries3 = new XYChart.Series<String, Integer>();
		laborSeries3.setName("Team 3 Labor Hours");
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.24.23", 44));
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.25.23", 52));
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.26.23", 21));
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.27.23", 5));
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.28.23", 26));
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.29.23", 84));
		laborSeries3.getData().add(new XYChart.Data<String, Integer>("11.30.23", 10));
		
		ticketSeries3 = new XYChart.Series<String, Integer>();
		ticketSeries3.setName("Team 3 Tickets");
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.24.23", 0));
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.25.23", 2));
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.26.23", 1));
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.27.23", 1));
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.28.23", 5));
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.29.23", 15));
		ticketSeries3.getData().add(new XYChart.Data<String, Integer>("11.30.23", 1));
		
		laborSeries4 = new XYChart.Series<String, Integer>();
		laborSeries4.setName("Developer 1 Labor Hours");
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.24.23", 44));
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.25.23", 52));
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.26.23", 21));
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.27.23", 5));
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.28.23", 26));
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.29.23", 84));
		laborSeries4.getData().add(new XYChart.Data<String, Integer>("11.30.23", 10));
		
		ticketSeries4 = new XYChart.Series<String, Integer>();
		ticketSeries4.setName("Developer 1 Tickets");
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.24.23", 7));
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.25.23", 1));
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.26.23", 4));
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.27.23", 2));
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.28.23", 3));
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.29.23", 11));
		ticketSeries4.getData().add(new XYChart.Data<String, Integer>("11.30.23", 1));
		
		laborSeries5 = new XYChart.Series<String, Integer>();
		laborSeries5.setName("Developer 2 Labor Hours");
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.24.23", 55));
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.25.23", 52));
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.26.23", 21));
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.27.23", 5));
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.28.23", 26));
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.29.23", 85));
		laborSeries5.getData().add(new XYChart.Data<String, Integer>("11.30.23", 10));
		
		ticketSeries5 = new XYChart.Series<String, Integer>();
		ticketSeries5.setName("Developer 2 Tickets");
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.24.23", 8));
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.25.23", 7));
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.26.23", 2));
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.27.23", 0));
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.28.23", 2));
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.29.23", 14));
		ticketSeries5.getData().add(new XYChart.Data<String, Integer>("11.30.23", 5));
		
		laborSeries6 = new XYChart.Series<String, Integer>();
		laborSeries6.setName("Developer 3 Labor Hours");
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.24.23", 66));
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.25.23", 52));
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.26.23", 21));
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.27.23", 5));
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.28.23", 26));
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.29.23", 86));
		laborSeries6.getData().add(new XYChart.Data<String, Integer>("11.30.23", 10));
		
		ticketSeries6 = new XYChart.Series<String, Integer>();
		ticketSeries6.setName("Developer 3 Tickets");
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.24.23", 0));
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.25.23", 4));
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.26.23", 6));
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.27.23", 2));
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.28.23", 1));
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.29.23", 5));
		ticketSeries6.getData().add(new XYChart.Data<String, Integer>("11.30.23", 3));
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
	public void groupDecision(ActionEvent event) {
		if (groupSelected) {
			prodReportChange(groupSelect.getValue());
		}
		/*
		 * for future use
		else if (groupSelect.getValue() == ""){
			laborHoursChart.getData().clear();
			ticketsCompletedChart.getData().clear();
		}
		*/
	}
	
	@FXML
	public void roleDecision(ActionEvent event) {
		if (roleSelected) {
			prodReportChange(roleSelect.getValue());
		}
	}

	@FXML
	public void groupChoice(ActionEvent event) {
		String selection = "";
		groupSelected = true;
		roleSelected = false;
		if (groupSelect.getValue() != null) {
			selection = groupSelect.getValue();
		}
		prodReportChange(selection);
	}
	
	@FXML public void roleChoice(ActionEvent event) {
		String selection = "";
		roleSelected = true;
		groupSelected = false;
		if (roleSelect.getValue() != null) {
			selection = roleSelect.getValue();
		}
		prodReportChange(selection);
	}
	
	public void prodReportChange(String newSelection) {
		if (newSelection == prodReportSelection | monthSelect.getValue() == null | 
				daySelect.getValue() == null |yearSelect.getValue() == null) {
			return;
		}
		else if (newSelection != "") {
			// TODO insert data filter and sort as well as chart display
			prodGraphLabel.setText(prodGraphLabel.getText().substring(0,22) + newSelection);
			if (newSelection == "Team 1") {
				// clear labor hours chart, add data
				laborHoursChart.getData().clear();
				laborHoursChart.getData().add(laborSeries1);
				// clear tickets completed chart, add data
				ticketsCompletedChart.getData().clear();
				ticketsCompletedChart.getData().add(ticketSeries1);
			}
			else if (newSelection == "Team 2") {
				// clear labor hours chart, add data
				laborHoursChart.getData().clear();
				laborHoursChart.getData().add(laborSeries2);
				// clear tickets completed chart, add data
				ticketsCompletedChart.getData().clear();
				ticketsCompletedChart.getData().add(ticketSeries2);
			}
			else if (newSelection == "Team 3") {
				// clear labor hours chart, add data
				laborHoursChart.getData().clear();
				laborHoursChart.getData().add(laborSeries3);
				// clear tickets completed chart, add data
				ticketsCompletedChart.getData().clear();
				ticketsCompletedChart.getData().add(ticketSeries3);
			}
			else if (newSelection == "Developer 1") {
				// clear labor hours chart, add data
				laborHoursChart.getData().clear();
				laborHoursChart.getData().add(laborSeries4);
				// clear tickets completed chart, add data
				ticketsCompletedChart.getData().clear();
				ticketsCompletedChart.getData().add(ticketSeries4);
			}
			else if (newSelection == "Developer 2") {
				// clear labor hours chart, add data
				laborHoursChart.getData().clear();
				laborHoursChart.getData().add(laborSeries5);
				// clear tickets completed chart, add data
				ticketsCompletedChart.getData().clear();
				ticketsCompletedChart.getData().add(ticketSeries5);
			}
			else if (newSelection == "Developer 3") {
				// clear labor hours chart, add data
				laborHoursChart.getData().clear();
				laborHoursChart.getData().add(laborSeries6);
				// clear tickets completed chart, add data
				ticketsCompletedChart.getData().clear();
				ticketsCompletedChart.getData().add(ticketSeries6);
			}
		}
		else {
			// TODO insert cleared data
			return;
		}
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
	
	//Private Feedback Tool method - Anton Nguyen
	public void generatePFTChart(ActionEvent Event) {
		//write TextBox info to integers
		projectsCmp = Integer.valueOf(pft_text1.getText());
		overtime = Integer.valueOf(pft_text2.getText());
		peerRating = Integer.valueOf(pft_text3.getText());
		solutionCnt = Integer.valueOf(pft_text4.getText());
		
		//create chart
		xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
				   "Projects completed", "Overtime hours", "Peer satisfaction", "Solution contributions"))); 
		xAxis.setLabel("Categories");
        yAxis.setLabel("Ratings");
        
        //add chart data
        XYChart.Series series = new XYChart.Series();
        series.setName("Term " + termCount);
        series.getData().add(new XYChart.Data<>("Projects completed", projectsCmp));
        series.getData().add(new XYChart.Data<>("Overtime hours", overtime)); 
        series.getData().add(new XYChart.Data<>("Peer satisfaction", peerRating)); 
        series.getData().add(new XYChart.Data<>("Solution contributions", solutionCnt)); 
        pft_chart.getData().addAll(series);
        
        termCount++;	//iterate termCount
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