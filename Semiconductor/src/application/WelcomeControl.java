package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class WelcomeControl implements Initializable {
	
    @FXML
    private Label appNameLabel;

    @FXML
    private Label promptLabel;
    
	@FXML
	MenuButton mnbtnChooseType;
	
	@FXML
	MenuItem mnitemPType, mnitemNType, mnitemIType;
	
	@FXML
	Button btnStart;
	
	public void goMainControl() {
		
		if(mnbtnChooseType.getText().contains("<")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Please choose a type");
			alert.setContentText("Please choose a type before getting started!");
			alert.showAndWait();
			return;
		}
		
		try {
			//close the old window
			Stage closeStage = (Stage)btnStart.getScene().getWindow();
			closeStage.close();
			
			String strMain = "/application/MainWindow_1.fxml";

			FXMLLoader loader = new FXMLLoader(getClass().getResource(strMain));
			Parent rootMainMenu = (Parent)loader.load();
			MainControl mainControl = loader.getController();
			//pass the start mode into the controller
			// to call set start mode
			mainControl.setStartMode(mnbtnChooseType.getText());
			//and to call set crystal view
			if(mnbtnChooseType.getText().contains("P"))
				mainControl.setCrystalView("P", "light");
			else if(mnbtnChooseType.getText().contains("N"))
				mainControl.setCrystalView("N", "light");
			else if(mnbtnChooseType.getText().contains("I"))
				mainControl.setCrystalView("I", "light");
			
			//create new stage with the new pane as root
			Stage stage = new Stage();
			
			stage.setTitle("Semiconductor Visualizer 1.1");
			stage.setScene(new Scene(rootMainMenu)); 
			stage.setResizable(false);
			
			stage.setOnCloseRequest(e->{
				Alert confirmation = new Alert(AlertType.CONFIRMATION);
				confirmation.setHeaderText("Exit confirmation");
				confirmation.setContentText("Are you sure you want to exit program?");
				Optional<ButtonType> option = confirmation.showAndWait();
				
				if(option.get()==ButtonType.OK) {
					confirmation.close();
					Alert thank = new Alert(AlertType.INFORMATION);
					thank.setHeaderText("Thank you very much!");
					thank.setContentText("Thank you for using our software!");
					thank.showAndWait();
				} else {
					e.consume();
				}

			});
			
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mnitemPType.setOnAction(e->{
			mnbtnChooseType.setText(mnitemPType.getText());
		});
		
		mnitemNType.setOnAction(e->{
			mnbtnChooseType.setText(mnitemNType.getText());
		});
		
		mnitemIType.setOnAction(e->{
			mnbtnChooseType.setText(mnitemIType.getText());
		});
		
		btnStart.setOnMouseClicked(e->{
			goMainControl();
		});
	}

}
