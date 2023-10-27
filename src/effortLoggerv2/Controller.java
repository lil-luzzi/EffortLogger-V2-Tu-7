package effortLoggerv2;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDateTime; // import the LocalDateTime class
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;



public class Controller implements Initializable {
	@FXML
	private Label myLabel;
	
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
	private Rectangle myRectangle;
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
	private LocalDateTime start;
	private LocalDateTime stop;
	private int timeElapsed;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myChoiceBox.getItems().addAll(choices);
		myChoiceBox2.getItems().addAll(choices2);
		myChoiceBox3.getItems().addAll(choices3);
		myChoiceBox4.getItems().addAll(choices4);
		myChoiceBox5.getItems().addAll(choices5);
		myChoiceBox6.getItems().addAll(choices6);
	}
	
	public void startTime(ActionEvent e) {
		start = LocalDateTime.now();
	}
	
	public void stopTime(ActionEvent e) {
		stop = LocalDateTime.now();
	}
	
}