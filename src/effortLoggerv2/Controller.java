package effortLoggerv2;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime; // import the LocalDateTime class
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Controller implements Initializable {
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
	private Pane myPane;
	@FXML
	private Rectangle clockIndicatorBox;
	@FXML
	private Label clockIndicatorLabel;
	
	@FXML
	private Button myButton;
	
	private String[] choices = {"Business Project", "Development Project"};
	private String[] choices2 = {"Planning", "Information Gathering", "Information Understanding", "Verifying", 
			"Outlining", "Drafting", "Finalizing", "Team Meeting", "Coach Meeting", "Stakeholder Meeting"};
	private String[] choices3 = {"Plans", "Deliverables", "Interruptions", "Defects", "Others"};
	private String[] choices4 = {"Project Plan", "Risk Management Plan", "Conceptual Design Plan", 
			"Detailed Design Plan", "Implementation Plan"};
	private String[] choices5 = {"Team 1", "Team 2", "Team 3"};
	private String[] choices6 = {"Developer 1", "Developer 2", "Developer 3", "Developer 4",
			"Developer 5","Developer 6","Developer 7"};
	
	private boolean isLogging = false;
	
	private LocalDateTime startDateTime;
	private long startTime;
	private LocalDateTime stop;
	private long stopTime;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myChoiceBox.getItems().addAll(choices);
		myChoiceBox2.getItems().addAll(choices2);
		myChoiceBox3.getItems().addAll(choices3);
		myChoiceBox4.getItems().addAll(choices4);
		myChoiceBox5.getItems().addAll(choices5);
		myChoiceBox6.getItems().addAll(choices6);
		clockIndicatorLabel.getText();
	}
	
	@FXML
	public void timerStart(ActionEvent event) {
		if (!isLogging) {
			// Debug Statement
			System.out.println("Time Started");
			// take note of current date time for log and time in ms for elapsed time
			startDateTime = LocalDateTime.now();
			startTime = System.currentTimeMillis();
			
			clockIndicatorBox.setFill(Color.GREEN);
			clockIndicatorLabel.setText("Clock is Running");
			isLogging = !isLogging;
		}
	}
	
	@FXML
	public void timerStop(ActionEvent event) {
		if (isLogging) {
			// Debug Statement
			System.out.println("Time Finished");
			// take note of current date time for log and time in ms for elapsed time
			stop = LocalDateTime.now();
			stopTime = System.currentTimeMillis();
			
			System.out.println("Elapsed Time (ms): " + calcTimeElapsed(startTime, stopTime));
			
			clockIndicatorBox.setFill(Color.RED);
			clockIndicatorLabel.setText("Clock is Stopped");
			isLogging = !isLogging;
		}
	}
	
	public long calcTimeElapsed(long start, long stop) {
		// calculate time elapsed (in milliseconds), convert to hours
		// 1 hour = 3.6 million ms => time elapsed / 3.6million = hours
		return stop - start;
	}
}