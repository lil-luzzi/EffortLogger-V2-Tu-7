module EffortLoggerV2 {
	exports effortLoggerv2;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens effortLoggerV2 to javafx.fxml;
}