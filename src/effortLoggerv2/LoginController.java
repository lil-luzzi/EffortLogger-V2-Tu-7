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
	private Boolean verification = false;
	private Boolean userExists = false;
	private String salt = LoginHasher.generateSalt();
	private int userIndex;
	LoginHasher pass = new LoginHasher();
    private String[] hashedPass = {LoginHasher.hashPassword(pass.passwords[0], salt),LoginHasher.hashPassword(pass.passwords[1], salt),
    		LoginHasher.hashPassword(pass.passwords[2], salt)};
	
	@FXML
	public void login(ActionEvent event) throws IOException {
		
		for (int i=0;i<3;i++) {
			if(nameTextField.getText().equals(pass.usernames[i])) {
				userIndex = i;
				userExists = true;
			}
		}
		
		
		if(userExists == true) {
			String username = nameTextField.getText();
			String password = passTextField.getText();
			
			verification = LoginHasher.verifyPassword(password, salt, hashedPass[userIndex]);

			if(verification == true) {
				System.out.println("Logging in...");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
				root = loader.load();
				
				Controller mainController = loader.getController();
				
				
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
			
			}
			else {
				System.out.println("Wrong Password!");
			}
		}
		else {
			System.out.println("User does not exist.");
		}
		
		
	}
		
		
		
	
}
	