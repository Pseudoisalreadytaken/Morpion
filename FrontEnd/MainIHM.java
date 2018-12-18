package Morpion.FrontEnd;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainIHM extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ClientPanel clientPanel = new ClientPanel();
		Group root = new Group();
		root.getChildren().add(clientPanel);
		Scene scene = new Scene(root, 500, 620);
		primaryStage.setTitle("Morpion");
		primaryStage.setScene(scene);
		primaryStage.show();
	
		
		
	}
	

}
