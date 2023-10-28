//this file was made by Luz
package effortLoggerv2;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField passTextField;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void login(ActionEvent event) throws IOException {
		
		
		String username = nameTextField.getText();
		System.out.println(username);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
		root = loader.load();
		
		Controller mainController = loader.getController();
		
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	
	}
	
}
	