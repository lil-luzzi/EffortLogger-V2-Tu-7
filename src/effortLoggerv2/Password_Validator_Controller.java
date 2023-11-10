//this controller file is developed by Sai Arjun Shroff
package effortLoggerv2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Password_Validator_Controller {

    @FXML
    private TextField lastNameField; // Fields linked with the User Interface
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label passwordStrength;
    @FXML
    private Label passwordStrengthLabel;
    @FXML
    private TextField passwordStrengthTextField;

    @FXML
    public void handleSignUpButton(ActionEvent event) throws IOException {
        String name = firstNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        
        if (password.equals(confirmPassword)) {
            String strength = calculatePasswordStrength(password);
            passwordStrengthTextField.setText(strength);
            
            // Specify the text color based on strength
            String textColor = "-fx-text-fill: ";
            if (strength.equals("Strong(Accepted)")) {
                textColor += "green;";
                passwordStrengthTextField.setStyle(textColor);
                loadNewPageWithDelay(event);
            } else if (strength.equals("Moderate(Accepted)")) {
                textColor += "blue;";
                passwordStrengthTextField.setStyle(textColor);
                loadNewPageWithDelay(event);
            } else {
                textColor += "red;";
                passwordStrengthTextField.setStyle(textColor);
            }
            
        } else {
            passwordStrengthTextField.setText("Passwords do not match");
        }
    }

    private void loadNewPageWithDelay(ActionEvent event) {
        Duration delay = Duration.millis(2000); // Delay for 2 seconds
        KeyFrame keyFrame = new KeyFrame(delay, e -> loadNewPage(event));
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    private void loadNewPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            Controller mainController = loader.getController();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String calculatePasswordStrength(String password) {
        int strength = 0;

        // Check the length of the password
        if (password.length() >= 8) {
            strength += 2; // A longer password is stronger
        }

        int uppercaseCount = (int) password.chars().filter(Character::isUpperCase).count();
        int lowercaseCount = (int) password.chars().filter(Character::isLowerCase).count();
        int digitCount = (int) password.chars().filter(Character::isDigit).count();
        int specialCharCount = (int) password.chars()
                .filter(c -> "!@#$%^&*()_+-=[]{};':\"\\|,.<>/?".contains(String.valueOf((char) c)))
                .count();

        // Add points based on the count of uppercase characters
        if (uppercaseCount >= 2) {
            strength += 4;
        } else if (uppercaseCount == 1) {
            strength += 2;
        }

        // Add points based on the count of lowercase characters
        if (lowercaseCount >= 2) {
            strength += 4;
        } else if (lowercaseCount == 1) {
            strength += 2;
        }

        // Add points based on the count of digits
        if (digitCount >= 2) {
            strength += 4;
        } else if (digitCount == 1) {
            strength += 2;
        }

        // Add points based on the count of special characters
        if (specialCharCount >= 2) {
            strength += 4;
        } else if (specialCharCount == 1) {
            strength += 2;
        }

        // Check if the password contains the first name and last name (case-insensitively)
        if (password.toLowerCase().contains(firstNameField.getText().toLowerCase().replaceAll("\\s", ""))) {
            strength = 0;
        }
        if (password.toLowerCase().contains(lastNameField.getText().toLowerCase().replaceAll("\\s", ""))) {
            strength = 0;
        }

        // Determine password strength level based on the total strength points
        if (strength > 16) {
            return "Strong(Accepted)";
        } else if (strength >= 13 && strength < 16) {
            return "Moderate(Accepted)";
        } else if (strength >= 1 && strength < 13) {
            return "Weak(Not Accepted)";
        } else if (strength == 0) {
            return "Password contains the name (Not Accepted)";
        }
        return "Invalid Strength";
    }
}
