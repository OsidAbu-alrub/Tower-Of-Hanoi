package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Driver extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane anchor = new AnchorPane();
		MainWindow.mainWindow(anchor);
		Scene scene = new Scene(anchor);
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
}
