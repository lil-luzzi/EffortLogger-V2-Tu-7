module EffortLoggerv2 {
	exports effortLoggerv2;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens effortLoggerv2 to javafx.fxml, javafx.graphics, javafx.base;
}