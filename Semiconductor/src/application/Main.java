package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {

	public void start(Stage primaryStage) {
		try {
			String strWelcome = "/application/WelcomeWindow.fxml";
			Parent root = FXMLLoader.load(getClass().getResource(strWelcome));
			primaryStage.setResizable(false);
			primaryStage.setTitle("Semiconductor Visualizer 1.1");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		primaryStage.setOnCloseRequest(e->{
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
	}
	
	public static void main(String args[]) {
		launch(args);
	}

}
