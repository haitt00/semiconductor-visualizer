package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import elements.crystal.Crystal;
import elements.crystal.NDopedCrystal;
import elements.crystal.PDopedCrystal;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

@SuppressWarnings({"rawtypes","unchecked"})

public class MainControl implements Initializable {

	@FXML
	Slider sliderVoltage, sliderTemperature;

	@FXML
	TextField txtfVoltage, txtfTemperature;

	@FXML
	Button btnStartSimulation, btnStopSimulation;

	@FXML
	Pane paneElements, paneCrystalBackground, paneCrystalFrame, paneNotes;
	
	@FXML
	AnchorPane anchorPaneBG;

	@FXML
	RadioMenuItem radiomnitemPType, radiomnitemNType, radiomnitemIType, radiomnitemLightDoped, radiomnitemHeavyDoped;

	@FXML
	MenuItem mnitemExit, mnitemHowToUse, mnitemAbout;
	
	Crystal newCrystal;
	static Timeline timeline;

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		// initialize parameters
		
//		Environment.externalVoltage.set(1);
//		Environment.temperature.set(25);
		StringConverter<Number> strVoltageConverter = new NumberStringConverter();
		txtfVoltage.textProperty().bindBidirectional(sliderVoltage.valueProperty(), strVoltageConverter);
//		Environment.externalVoltage.bindBidirectional(sliderVoltage.valueProperty());
//		Environment.electronCycle.bind((new SimpleIntegerProperty(1000)).divide(sliderVoltage.valueProperty()));
//		Environment.seperateProb.bind(sliderVoltage.valueProperty().divide(Environment.maxvoltage).multiply(0.5));

		
		StringConverter<Number> strTemperatureConverter = new NumberStringConverter();
		txtfTemperature.textProperty().bindBidirectional(sliderTemperature.valueProperty(), strTemperatureConverter);
//		Environment.temperature.bindBidirectional(sliderTemperature.valueProperty());
//		Environment.diffuseProb.bind(sliderTemperature.valueProperty().divide(Environment.maxTemperature).multiply(0.75));
		Crystal.bindWithController(sliderVoltage.valueProperty(), sliderTemperature.valueProperty());
		setTimeline(paneElements);
		
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
				
		ToggleGroup toogleGroupType = new ToggleGroup();
		radiomnitemNType.setToggleGroup(toogleGroupType);
		radiomnitemPType.setToggleGroup(toogleGroupType);
		radiomnitemIType.setToggleGroup(toogleGroupType);
		
		ToggleGroup toogleGroupDope = new ToggleGroup();
		radiomnitemLightDoped.setToggleGroup(toogleGroupDope);
		radiomnitemHeavyDoped.setToggleGroup(toogleGroupDope);
		
		btnStopSimulation.setDisable(true);
			
		
		// test
		
		// initialize function of elements		

		ChangeListener<Number> listener = new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
							
				if(MainControl.timeline.getStatus().equals(Status.RUNNING)) {
					timeline.stop();
					timeline.getKeyFrames().clear();
					setTimeline(paneElements);
					timeline.play();
				}
				else {
					setTimeline(paneElements);
				}
			}
		};
		sliderVoltage.valueProperty().addListener(listener); 
		sliderTemperature.valueProperty().addListener(listener);
/*		sliderVoltage.setOnMouseDragged(e -> {
			// test		
			// tao mot integer property o GUI de bind voi integer property o backcode
			// co can thiet ko?
			double externalVoltage = sliderVoltage.getValue();
			int timeDuration = (int)(1000/externalVoltage);
			IntegerProperty timeDurationProperty = new SimpleIntegerProperty(timeDuration);
			Environment.transitionLength.bindBidirectional(timeDurationProperty);	
			
			System.out.println(Environment.voltage.get());
	//		Double externalVoltage = sliderVoltage.getValue();
			//fucntion to pass value to backend code of crystal
<<<<<<< HEAD
			if(this.timeline.getStatus().equals(Status.RUNNING)) {
				timeline.stop();
				timeline.getKeyFrames().clear();
				play(paneElements);
				timeline.play();

=======
	//		Environment.transitionLength.get() = (int) (1000/externalVoltage);
			try {
				if(this.timeline.getStatus().equals(Status.RUNNING)) {
					timeline.stop();
					timeline.getKeyFrames().clear();
					play(paneElements);
					timeline.play();
				}
>>>>>>> 33eb4d12faf774baee1842ae3fcdc0a2e47a4a4b
			}
			else {
				play(paneElements);
			}
		}); */
		
		
//		Environment.temperature.addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				// test
////				System.out.println(Environment.temperature.get());
////				System.out.println(Environment.transitionLength.get());
//				//fucntion to pass value to backend code of crystal
//		//		Double temperature = sliderTemperature.getValue();
////				Environment.chaoticRate = Environment.temperature.get();
//				//test
//			//	System.out.println("chaotic rate: " + Environment.chaoticRate);
////				Environment.transitionLength = new SimpleIntegerProperty((int) (1000/sliderVoltage.getValue()));
//				if(MainControl.timeline.getStatus().equals(Status.RUNNING)) {
//					timeline.stop();
//					timeline.getKeyFrames().clear();
//					setTimeline(paneElements);
//					timeline.play();
//				}
//				else {
//					setTimeline(paneElements);
//				}
//			}
//			
//		});
		

				
//		toogleGroupType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//			
//			String strType;
//			String strDope;
//			
//			@Override
//			public void changed(ObservableValue<? extends Toggle> value, Toggle oldValue, Toggle newValue) {
//				// TODO Auto-generated method stub
//				strType = getSemiconductorType(toogleGroupType.getSelectedToggle());
//				strDope = getDopedType(toogleGroupDope.getSelectedToggle());
//				timeline.stop();
//				setButtonOnStop();
//				setCrystalView(strType, strDope);
//				
//			}		
//		});

		radiomnitemPType.setOnAction(e -> {
			String strDope = getDopedType(toogleGroupDope.getSelectedToggle());
			timeline.stop();
			setButtonOnStop();
			setCrystalView("P", strDope);
			setDisableChooseDopeType(false);
		});

		radiomnitemNType.setOnAction(e -> {
			String strDope = getDopedType(toogleGroupDope.getSelectedToggle());
			timeline.stop();
			setButtonOnStop();
			setCrystalView("N", strDope);
			setDisableChooseDopeType(false);
		});

		radiomnitemIType.setOnAction(e -> {
			String strDope = getDopedType(toogleGroupDope.getSelectedToggle());
			timeline.stop();
			setButtonOnStop();
			setCrystalView("I", strDope);
			setDisableChooseDopeType(true);
		});
		
		radiomnitemLightDoped.setOnAction(e->{
			String strType = getSemiconductorType(toogleGroupType.getSelectedToggle());
			timeline.stop();
			setButtonOnStop();
			setCrystalView(strType, "light");			
		});
		
		radiomnitemHeavyDoped.setOnAction(e->{
			String strType = getSemiconductorType(toogleGroupType.getSelectedToggle());
			timeline.stop();
			setButtonOnStop();
			setCrystalView(strType, "heavy");
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
		radiomnitemLightDoped.setSelected(true);
		if (choice.contains("P")) {
			radiomnitemPType.setSelected(true);
		} else if (choice.contains("N")) {
			radiomnitemNType.setSelected(true);
		} else if (choice.contains("I")) {
			radiomnitemIType.setSelected(true);
			setDisableChooseDopeType(true);
		}
		paneElements.setVisible(true); 
	}
	
	public void setCrystalView(String choice, String dope) {
/*		if(choice.contains("P")) {
			newCrystal = new PDopedCrystal(dope);
		}

		else if (choice.contains("N")) {
			newCrystal = new NDopedCrystal(dope);
		}
		else if (choice.contains("I")) {
			newCrystal = new Crystal();
		}
*/		newCrystal = new Crystal(); 
		
		ArrayList<Crystal> crystalTypeList = new ArrayList<Crystal>();
		crystalTypeList.add(new PDopedCrystal(dope));
		crystalTypeList.add(new NDopedCrystal(dope));
		
		for(int i = 0; i<crystalTypeList.size(); i++) {
			Crystal obj = crystalTypeList.get(i);
			System.out.println(obj.getClass().toString());
			if(obj.getClass().toString().contains(choice))
				newCrystal = obj;
		}
		
		
		// set crystal frame and background
//		public final int PanelHeight = 100*Crystal.crystalHeight;
//		public final int PanelWidth = 100*Crystal.crystalHeight;
//		public final int atomViewRadius = 26;
//		public final int valenceViewPadding = 16;
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
		
		if(paneElements.getChildren().isEmpty()==false) {
			paneElements.getChildren().clear();
		}
		
		for (int x = 0; x < newCrystal.crystalWidth; x++) {
			for (int y = 0; y < newCrystal.crystalHeight; y++) {
				paneElements.getChildren().add(newCrystal.getAtomAt(x, y).getView());
				paneElements.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("up").getView());
				paneElements.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("down").getView());
				paneElements.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("right").getView());
				paneElements.getChildren().add(newCrystal.getAtomAt(x, y).getValenceCharge("left").getView());
				if(newCrystal.getAtomAt(x, y).checkForConductingE()) {
					paneElements.getChildren().add(newCrystal.getAtomAt(x, y).getConductingE().getView());
				}
			}
		}
	}

	private void setTimeline(Pane pane) {
		EventHandler onFinished = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				newCrystal.progress(pane);
			}
			
		};

		KeyFrame kf = new KeyFrame(Duration.millis(Crystal.getElectronCycle().getValue()+40), onFinished);
		
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
	
	private String getDopedType(Toggle selectedDopeType) {
		String stringType = "";
		if(selectedDopeType.toString().contains("Light")==true) 
			stringType = "light";
		
		if(selectedDopeType.toString().contains("Heavy")==true) 
			stringType = "heavy";
		
		return stringType;

	}
	
	private String getSemiconductorType(Toggle selectedSemiconductorType) {
		
		String stringType = selectedSemiconductorType.toString();
		if(stringType.contains("P")==true) 
			stringType = "P";
		
		if(stringType.contains("N")==true) 
			stringType = "N";
		
		if(stringType.contains("I")==true) 
			stringType = "I";
		
		return stringType;
	}
	
	private void setDisableChooseDopeType(boolean isDisable) {
		radiomnitemHeavyDoped.setDisable(isDisable);
		radiomnitemLightDoped.setDisable(isDisable);
	}
}
