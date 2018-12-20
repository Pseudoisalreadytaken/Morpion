package Morpion.FrontEnd;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import Morpion.FrontEnd.ClientPanel;
public class MainIHM extends Application{

	private ClientPanel lePanel;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.lePanel = new ClientPanel();
		Group root = new Group();
		root.getChildren().add(this.lePanel);
		Scene scene = new Scene(root, 500, 620);
		primaryStage.setTitle("Morpion");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {	
        Application.launch(MainIHM.class, args);
    }
	
	public ClientPanel GetLePanel() {
		return this.lePanel;
	}
	

}
