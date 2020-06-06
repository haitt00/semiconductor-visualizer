package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import elements.crystal.Crystal;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import settings.Settings;

@SuppressWarnings({"rawtypes","unchecked"})

public class MainControl implements Initializable {

	@FXML
	Slider sliderVoltage, sliderTemperature;

	@FXML
	TextField txtfVoltage, txtfTemperature;

	@FXML
	Button btnStartSimulation, btnStopSimulation;

	@FXML
	Pane paneSemi, paneCrystalBackground, paneCrystalFrame, paneNotes;
	
	@FXML
	AnchorPane anchorPaneBG;

	@FXML
	RadioMenuItem radiomnitemPType, radiomnitemNType, radiomnitemIType;

	@FXML
	MenuItem mnitemExit, mnitemHowToUse, mnitemAbout;
	
	Crystal newCrystal;
	static Timeline timeline;

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		// initialize parameters
		
		Settings.voltage.set(1);
		Settings.temperature.set(25);
		// binding value of slider and textfield
		StringConverter<Number> strVoltageConverter = new NumberStringConverter();
		txtfVoltage.textProperty().bindBidirectional(sliderVoltage.valueProperty(), strVoltageConverter);
		Settings.voltage.bindBidirectional(sliderVoltage.valueProperty());
		Settings.transitionLength.bind((new SimpleIntegerProperty(1000)).divide(sliderVoltage.valueProperty()));
		Settings.seperateProb.bind(sliderVoltage.valueProperty().divide(Settings.maxvoltage).multiply(0.5));
//				System.out.println("binded");
		// test
		
		StringConverter<Number> strTemperatureConverter = new NumberStringConverter();
		txtfTemperature.textProperty().bindBidirectional(sliderTemperature.valueProperty(), strTemperatureConverter);
		Settings.temperature.bindBidirectional(sliderTemperature.valueProperty());
		Settings.diffuseProb.bind(sliderTemperature.valueProperty().divide(Settings.maxTemperature).multiply(0.75));
//		Settings.transitionLength.get() = Settings.voltage.divide(1000).get();
		
		// initialize timeline
		play(paneSemi);
		
		// set notes pane
		String strNote = "./src/images/Notes.png";
		FileInputStream ipNote = null;
		try {
			ipNote = new FileInputStream(strNote);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ImageView notes = new ImageView(new Image(ipNote));
		paneNotes.getChildren().add(notes);
				
		ToggleGroup toogleGroup = new ToggleGroup();
		radiomnitemNType.setToggleGroup(toogleGroup);
		radiomnitemPType.setToggleGroup(toogleGroup);
		radiomnitemIType.setToggleGroup(toogleGroup);
		btnStopSimulation.setDisable(true);
		
		
		// test
		
		// initialize function of elements
		

		
		Settings.voltage.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					// TODO Auto-generated method stub
//				double externalVoltage = sliderVoltage.getValue();
//				int timeDuration = (int)(1000/externalVoltage);
//				IntegerProperty timeDurationProperty = new SimpleIntegerProperty(timeDuration);
//				Settings.transitionLength.bindBidirectional(timeDurationProperty);
//				Settings.transitionLength = new SimpleIntegerProperty((int) (1000/sliderVoltage.getValue()));
//					System.out.println(Settings.voltage.get());
					//		Double externalVoltage = sliderVoltage.getValue();
							//fucntion to pass value to backend code of crystal
					//		Settings.transitionLength.get() = (int) (1000/externalVoltage);
							
				if(MainControl.timeline.getStatus().equals(Status.RUNNING)) {
					timeline.stop();
					timeline.getKeyFrames().clear();
					play(paneSemi);
					timeline.play();
				}
				else {
					play(paneSemi);
				}
			}
			
		}); 
		
/*		sliderVoltage.setOnMouseDragged(e -> {
			// test		
			// tao mot integer property o GUI de bind voi integer property o backcode
			// co can thiet ko?
			double externalVoltage = sliderVoltage.getValue();
			int timeDuration = (int)(1000/externalVoltage);
			IntegerProperty timeDurationProperty = new SimpleIntegerProperty(timeDuration);
			Settings.transitionLength.bindBidirectional(timeDurationProperty);	
			
			System.out.println(Settings.voltage.get());
	//		Double externalVoltage = sliderVoltage.getValue();
			//fucntion to pass value to backend code of crystal
<<<<<<< HEAD
			if(this.timeline.getStatus().equals(Status.RUNNING)) {
				timeline.stop();
				timeline.getKeyFrames().clear();
				play(paneSemi);
				timeline.play();

=======
	//		Settings.transitionLength.get() = (int) (1000/externalVoltage);
			try {
				if(this.timeline.getStatus().equals(Status.RUNNING)) {
					timeline.stop();
					timeline.getKeyFrames().clear();
					play(paneSemi);
					timeline.play();
				}
>>>>>>> 33eb4d12faf774baee1842ae3fcdc0a2e47a4a4b
			}
			else {
				play(paneSemi);
			}
		}); */
		
		
		Settings.temperature.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// test
//				System.out.println(Settings.temperature.get());
//				System.out.println(Settings.transitionLength.get());
				//fucntion to pass value to backend code of crystal
		//		Double temperature = sliderTemperature.getValue();
//				Settings.chaoticRate = Settings.temperature.get();
				//test
			//	System.out.println("chaotic rate: " + Settings.chaoticRate);
//				Settings.transitionLength = new SimpleIntegerProperty((int) (1000/sliderVoltage.getValue()));
				if(MainControl.timeline.getStatus().equals(Status.RUNNING)) {
					timeline.stop();
					timeline.getKeyFrames().clear();
					play(paneSemi);
					timeline.play();
				}
				else {
					play(paneSemi);
				}
			}
			
		});

		radiomnitemPType.setOnAction(e -> {
			timeline.stop();
			setButtonOnStop();
			setCrystalView("P");
		});

		radiomnitemNType.setOnAction(e -> {
			timeline.stop();
			setButtonOnStop();
			setCrystalView("N");
		});

		radiomnitemIType.setOnAction(e -> {
			timeline.stop();
			setButtonOnStop();
			setCrystalView("I");
		});

		mnitemAbout.setOnAction(e -> {
			Stage stage = new Stage();
			try {
				String strWelcome = "/application/AboutWindow.fxml";
				Parent root = FXMLLoader.load(getClass().getResource(strWelcome));
				stage.setTitle("About");
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
				stage.setTitle("How to use");
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
			timeline.play();
			setButtonOnPlay();
		});
		
		btnStopSimulation.setOnMouseClicked(e->{
			timeline.stop();
			setButtonOnStop();
		});
	
			
	}


	public void setStartMode(String choice) {
		if (choice.contains("P")) {
			radiomnitemPType.setSelected(true);
		} else if (choice.contains("N")) {
			radiomnitemNType.setSelected(true);
		} else if (choice.contains("I")) {
			radiomnitemIType.setSelected(true);
		}
		paneSemi.setVisible(true);
	}
	
	public void setCrystalView(String choice) {
		newCrystal = new Crystal();
		
		// set crystal frame and background
		String strCFrame = "./src/images/crystal-frame.png";
		String strCBackground = "./src/images/crystal-background.png";
		
		FileInputStream ipCFrame = null;
		try {
			ipCFrame = new FileInputStream(strCFrame);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView CFrame = new ImageView(new Image(ipCFrame));
		
		FileInputStream ipCBackground = null;
		try {
			ipCBackground = new FileInputStream(strCBackground);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		ImageView CBackground = new ImageView(new Image(ipCBackground));
		
		paneCrystalFrame.getChildren().add(CFrame);
		paneCrystalBackground.getChildren().add(CBackground);
		
		if(paneSemi.getChildren().isEmpty()==false) {
			paneSemi.getChildren().clear();
		}
		
		if(choice.contains("P")) {
			newCrystal.initCrystal("P");
		}

		else if (choice.contains("N")) {
			newCrystal.initCrystal("AL");
		}
		else if (choice.contains("I")) {
			newCrystal.initCrystal("SI");
		}
		for (int x = 0; x < Settings.crystalWidth; x++) {
			for (int y = 0; y < Settings.crystalHeight; y++) {
				paneSemi.getChildren().add(newCrystal.getAtomAt(x, y).getView());
				paneSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("up").getView());
				paneSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("down").getView());
				paneSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("right").getView());
				paneSemi.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("left").getView());
				if(newCrystal.getAtomAt(x, y).checkForConductingE()) {
					paneSemi.getChildren().add(newCrystal.getAtomAt(x, y).getConductingE().getView());
				}
			}
		}
	}

	private void play(Pane pane) {
		EventHandler onFinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				newCrystal.progress(pane);
			}
			
		};

		KeyFrame kf = new KeyFrame(Duration.millis(Settings.transitionLength.getValue()+40), onFinished);
		
		timeline = new Timeline(kf);
		
		timeline.setCycleCount(Timeline.INDEFINITE);
	}
	
	private void setButtonOnPlay() {
		btnStartSimulation.setDisable(true);
		btnStopSimulation.setDisable(false);
	}
	
	private void setButtonOnStop() {
		btnStartSimulation.setDisable(false);
		btnStopSimulation.setDisable(true);
	}
}
