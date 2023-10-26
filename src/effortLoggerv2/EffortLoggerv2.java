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
    	/*System.out.println("Luz Rodriguez Hello World!");
    	System.out.println("It started!");
        primaryStage.setTitle("Luz Rodriguez Hello World");
        Button btn = new Button();
        btn.setText("Display: 'hehe says: Hello World!'");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                System.out.println("Luz Rodriguez: Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();*/
    	//luz wrote this, we might need to switch to javafx 21 depending on if 
    	//we start getting real issues but ignore the warning for now :)
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
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