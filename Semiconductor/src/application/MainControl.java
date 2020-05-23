package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainControl implements Initializable {

	@FXML
	Slider sliderVoltage, sliderTemperature;

	@FXML
	TextField txtfVoltage, txtfTemperature;

	@FXML
	Button btnStart;

	@FXML
	Pane panePSemi, paneNSemi, paneISemi;

	@FXML
	RadioMenuItem radiomnitemPType, radiomnitemNType, radiomnitemIType;

	@FXML
	MenuItem mnitemExit, mnitemHowToUse, mnitemAbout;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		ToggleGroup toogleGroup = new ToggleGroup();
		radiomnitemNType.setToggleGroup(toogleGroup);
		radiomnitemPType.setToggleGroup(toogleGroup);
		radiomnitemIType.setToggleGroup(toogleGroup);

		sliderVoltage.setOnMouseDragged(e -> {
			txtfVoltage.setText(Double.toString(Math.round(sliderVoltage.getValue())));
			
			//fucntion to pass value to backend code of crystal
		});

		sliderTemperature.setOnMouseDragged(e -> {
			txtfTemperature.setText(Double.toString(Math.round(sliderTemperature.getValue())));
			//fucntion to pass value to backend code of crystal
		});

		radiomnitemPType.setOnAction(e -> {
			showPPane();
		});

		radiomnitemNType.setOnAction(e -> {
			showNPane();
		});

		radiomnitemIType.setOnAction(e -> {
			showIPane();
		});

		mnitemAbout.setOnAction(e -> {
			Stage stage = new Stage();
			try {
				String strWelcome = "/application/AboutWindow.fxml";
				Parent root = FXMLLoader.load(getClass().getResource(strWelcome));
				stage.setTitle("WelcomeWindow");
				stage.setScene(new Scene(root));
				stage.show();
			} catch (Exception er) {
				er.printStackTrace();
			}
		});

		mnitemHowToUse.setOnAction(e -> {
			Stage stage = new Stage();
			try {
				String strWelcome = "/application/HowToUseWindow.fxml";
				Parent root = FXMLLoader.load(getClass().getResource(strWelcome));
				stage.setTitle("WelcomeWindow");
				stage.setScene(new Scene(root));
				stage.show();
			} catch (Exception er) {
				er.printStackTrace();
			}
		});

		mnitemExit.setOnAction(e -> {
			Alert confirmation = new Alert(AlertType.CONFIRMATION);
			confirmation.setHeaderText("Exit confirmation");
			confirmation.setContentText("Are you sure you want to exit program?");
			
			Optional<ButtonType> option = confirmation.showAndWait();
			
			if(option.get()==ButtonType.OK) { 
				confirmation.close(); 
				Alert thank = new Alert(AlertType.INFORMATION); 
				thank.setHeaderText("Thank you very much!");
				thank.setContentText("Thank you for using our software!");
				thank.showAndWait(); Platform.exit(); } 
			else { 
				e.consume(); 
			}
			 
		});
	}

	private void showPPane() {
		panePSemi.setVisible(true);
		paneNSemi.setVisible(false);
		paneISemi.setVisible(false);
	}

	private void showNPane() {
		paneNSemi.setVisible(true);
		panePSemi.setVisible(false);
		paneISemi.setVisible(false);
	}

	private void showIPane() {
		paneISemi.setVisible(true);
		panePSemi.setVisible(false);
		paneNSemi.setVisible(false);
	}

	public void setStartMode(String choice) {
		if (choice.contains("P")) {
			radiomnitemPType.setSelected(true);
			showPPane();
		} else if (choice.contains("N")) {
			radiomnitemNType.setSelected(true);
			showNPane();
		} else if (choice.contains("I")) {
			showIPane();
			radiomnitemIType.setSelected(true);
		}
	}
}
