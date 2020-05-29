package application;

import java.awt.Color;
import java.awt.Paint;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import elements.Crystal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import settings.Settings;

public class MainControl implements Initializable {

	@FXML
	Slider sliderVoltage, sliderTemperature;

	@FXML
	TextField txtfVoltage, txtfTemperature;

	@FXML
	Button btnStartSimulation, btnStopSimulation;

	@FXML
	Pane panePSemi, paneNSemi, paneISemi;

	@FXML
	RadioMenuItem radiomnitemPType, radiomnitemNType, radiomnitemIType;

	@FXML
	MenuItem mnitemExit, mnitemHowToUse, mnitemAbout;
	
	Crystal newCrystal;
	Timeline timeline;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Border border = new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
		
//		panePSemi.setBorder(border);

		ToggleGroup toogleGroup = new ToggleGroup();
		radiomnitemNType.setToggleGroup(toogleGroup);
		radiomnitemPType.setToggleGroup(toogleGroup);
		radiomnitemIType.setToggleGroup(toogleGroup);
		btnStopSimulation.setDisable(true);

		sliderVoltage.setOnMouseDragged(e -> {
			txtfVoltage.setText(Double.toString(Math.round(sliderVoltage.getValue())));
			
			//fucntion to pass value to backend code of crystal
		});

		sliderTemperature.setOnMouseDragged(e -> {
			txtfTemperature.setText(Double.toString(Math.round(sliderTemperature.getValue())));
			//fucntion to pass value to backend code of crystal
		});

		radiomnitemPType.setOnAction(e -> {
			setCrystalView("P");
			showPPane();
		});

		radiomnitemNType.setOnAction(e -> {
			setCrystalView("N");
			showNPane();
		});

		radiomnitemIType.setOnAction(e -> {
			setCrystalView("I");
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
		
		btnStartSimulation.setOnMouseClicked(e->{
			if(panePSemi.isVisible()==true) {
				play(panePSemi);
			} else if(paneNSemi.isVisible()==true) {
				play(paneNSemi);
			} else if(paneISemi.isVisible()==true) {
				play(paneNSemi);
			}
			btnStartSimulation.setDisable(true);
			btnStopSimulation.setDisable(false);
		});
		
		btnStopSimulation.setOnMouseClicked(e->{
			if(panePSemi.isVisible()==true) {
				timeline.stop();
			} else if(paneNSemi.isVisible()==true) {
				timeline.stop();
			} else if(paneISemi.isVisible()==true) {
				timeline.stop();
			}
			btnStopSimulation.setDisable(true);
			btnStartSimulation.setDisable(false);
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
	
	public void setCrystalView(String choice) {
		newCrystal = new Crystal();
		if(choice.contains("P")) {
			newCrystal.initCrystal("P");
			for (int x = 0; x < Settings.crystalWidth; x++) {
				for (int y = 0; y < Settings.crystalHeight; y++) {
					panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getView());
					panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("up").getView());
					panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("down").getView());
					panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("right").getView());
					panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("left").getView());
					if(newCrystal.getAtomAt(x, y).checkForConductingE()) {
						panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getConductingE().getView());
					}
				}
			}
		}

		else if (choice.contains("N")) {
			newCrystal.initCrystal("AL");
			for (int x = 0; x < Settings.crystalWidth; x++) {
				for (int y = 0; y < Settings.crystalHeight; y++) {
					paneNSemi.getChildren().add(newCrystal.getAtomAt(x, y).getView());
					paneNSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("up").getView());
					paneNSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("down").getView());
					paneNSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("right").getView());
					paneNSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("left").getView());
					if(newCrystal.getAtomAt(x, y).checkForConductingE()) {
						panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getConductingE().getView());
					}
				}
			}
		}
		else if (choice.contains("I")) {
			newCrystal.initCrystal("SI");
			for (int x = 0; x < Settings.crystalWidth; x++) {
				for (int y = 0; y < Settings.crystalHeight; y++) {
					paneISemi.getChildren().add(newCrystal.getAtomAt(x, y).getView());
					paneISemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("up").getView());
					paneISemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("down").getView());
					paneISemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("right").getView());
					paneISemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("left").getView());
					if(newCrystal.getAtomAt(x, y).checkForConductingE()) {
						panePSemi.getChildren().add(newCrystal.getAtomAt(x, y).getConductingE().getView());
					}
				}
			}
		}
	}

	public void play(Pane pane) {
		EventHandler onFinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				newCrystal.progress(pane);
			}
			
		};

		KeyFrame kf = new KeyFrame(Duration.millis(2000), onFinished);
		
		timeline = new Timeline(kf);
		
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
}
