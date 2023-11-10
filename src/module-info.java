/**
 * 
 */
/**
 * 
 */
module EffortLoggerv2 {
	/*requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.swt;
	requires javafx.web;*/
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	requires org.json;
	
	opens effortLoggerv2 to javafx.graphics, javafx.fxml, javafx.base;

}