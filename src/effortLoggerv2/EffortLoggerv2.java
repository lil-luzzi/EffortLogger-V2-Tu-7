package effortLoggerv2;


/*public class EffortLoggerv2 {
	public static void main(String[] args) {
		System.out.println("Hello Robert Lynn Carter!");
	}
}*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EffortLoggerv2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
    	System.out.println("Luz Rodriguez Hello World!");
    	System.out.println("It started!");
        primaryStage.setTitle("Luz Rodriguez Hello World");
        Button btn = new Button();
        btn.setText("Display: 'bruh says: Hello World!'");
        btn.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                System.out.println("Luz Rodriguez: Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}