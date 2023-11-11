//this file was made by Luz and Jonathan
package effortLoggerv2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import java.util.*;
import javafx.fxml.FXMLLoader;


public class EffortLoggerv2 extends Application {
	
	@Override
    public void start(Stage primaryStage) {
    	
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
    		Scene scene = new Scene(root,900,600);
    		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    public static void main(String[] args) {
        launch(args);
    }
}