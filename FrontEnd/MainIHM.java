package Morpion.FrontEnd;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.util.concurrent.TimeUnit;

import Morpion.Controller.Reseau.Common.Message;
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
		
		//Si on ferme l'IHM
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	              System.out.println("Stage is closing");
	              //Si il y a un client connecter
	              if (lePanel.getLeMainClient() != null)
	              {
		              lePanel.getLeMainClient().GetClient().GetLeClientSend().SetMessAEnvoyer("vient de se déconnecter");
		              if (lePanel.GetEstServeur() == true)
		              {
		            	  lePanel.getLeMainClient().GetClient().GetLeClientSend().SetSenderDuMessAEnvoyer("ServeurDeconnecter");
		              }
		              else
		              {
		            	  lePanel.getLeMainClient().GetClient().GetLeClientSend().SetSenderDuMessAEnvoyer("ClientDeconnecter");
		              }
		              lePanel.getLeMainClient().GetClient().GetLeClientSend().EnvoyerLeMessage(true);
		              lePanel.getLeMainClient().GetClient().disconnectedServer();
	              }
	              //on attend 1 seconde
	              try {
	            	  TimeUnit.SECONDS.sleep(1);
	              }
	              catch (Exception e)
	              {
	            	  System.out.println(e.getMessage());
	              }			
	              System.exit(0);
	          }
		});
		
	}
	
	public static void main(String[] args) {	
        Application.launch(MainIHM.class, args);
    }
	
	public ClientPanel GetLePanel() {
		return this.lePanel;
	}
	

}
