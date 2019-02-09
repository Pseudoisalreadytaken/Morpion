package Morpion.FrontEnd;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.embed.swing.JFXPanel;

import java.util.concurrent.TimeUnit;

import Morpion.Controller.Reseau.Client.MainClient;
import Morpion.Controller.Reseau.Server.MainServer;

public class ClientPanel extends Parent {
	
	private String pseudoDuJoueur = "";
	
	//Section connexion
	private Button btnSinscrire;
	private Label labelSeConnecterSection;
	private Label labelPseudoJoueur;
	private TextField textPseudoJoueur;
	private Label labelMotDePasseJoueur;
	private PasswordField textMotDePasseJoueur;
	private Button btnSeConnecter;
	
	//Section incription
	private Button btnRetourConnexion;
	private Label labelSinscrireSection;
	private Label labelInscriptionPseudoJoueur;
	private TextField textInscriptionPseudoJoueur;
	private Label labelInscriptionMotDePasseJoueur;
	private PasswordField textInscriptionMotDePasseJoueur;
	private Button btnValiderInscrire;
	
	//Section Hebergement
	private Label labelCreeServeurSection;
	private Label labelPortCreationServeur;
	private TextField textPortCreationServeur;
	private Button btnNouveauServeur;
	private Label labelRejoindreServeurSection;
	private Label labelPortRejoindreServeur;
	private TextField textPortRejoindreServeur;
	private Button btnRejoindreServeur;
	
	//Section CHAT
	private TextArea textToSend;
	private ScrollPane scrollReceivedText;
	static private TextFlow receivedText;
	private Button sendBtn;
	
	private MainClient unMainClient;
	
	private boolean estServeur = false;
	
	
	//static public boolean attenteMessClient = false;
	public ClientPanel() {	
		
		//Section connexion
		this.btnSinscrire = new Button();
		this.labelSeConnecterSection = new Label();
		this.labelPseudoJoueur = new Label();
		this.textPseudoJoueur = new TextField();
		this.labelMotDePasseJoueur = new Label();
		this.textMotDePasseJoueur = new PasswordField();
		this.btnSeConnecter = new Button();
		
		//Section inscription
		this.btnRetourConnexion = new Button();
		this.labelSinscrireSection = new Label();
		this.labelInscriptionPseudoJoueur = new Label();
		this.textInscriptionPseudoJoueur = new TextField();
		this.labelInscriptionMotDePasseJoueur = new Label();
		this.textInscriptionMotDePasseJoueur = new PasswordField();
		this.btnValiderInscrire = new Button();
		
		//Section h�bergement
		this.labelCreeServeurSection = new Label();
		this.labelPortCreationServeur = new Label();
		this.textPortCreationServeur = new TextField();
		this.btnNouveauServeur = new Button();
		this.labelRejoindreServeurSection = new Label();
		this.labelPortRejoindreServeur = new Label();
		this.textPortRejoindreServeur = new TextField();
		this.btnRejoindreServeur = new Button();
		
		//Section CHAT
		this.textToSend = new TextArea();
		this.scrollReceivedText = new ScrollPane();
		this.receivedText = new TextFlow();
		this.sendBtn = new Button();
		

		//Afficher les �l�ment dont on a besoin
		this.getChildren().add(this.btnSinscrire);
		this.getChildren().add(this.labelSeConnecterSection);
		this.getChildren().add(this.labelPseudoJoueur);
		this.getChildren().add(this.textPseudoJoueur);
		this.getChildren().add(this.labelMotDePasseJoueur);
		this.getChildren().add(this.textMotDePasseJoueur);
		this.getChildren().add(this.btnSeConnecter);
		
		
		//
		//Section CONNEXION
		//
		btnSinscrire.setVisible(true);
		btnSinscrire.setLayoutX(350);
		btnSinscrire.setLayoutY(30);
		btnSinscrire.setPrefWidth(100);
		btnSinscrire.setPrefHeight(30);
		btnSinscrire.setText("Inscription");
		btnSinscrire.setStyle(String.format("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#4FC3F7; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
		labelSeConnecterSection.setWrapText(true);
		labelSeConnecterSection.setLayoutX(30);
		labelSeConnecterSection.setLayoutY(120);
		labelSeConnecterSection.setPrefWidth(400);
		labelSeConnecterSection.setPrefHeight(30);
		labelSeConnecterSection.setText("Connexion :");
		labelSeConnecterSection.setStyle(String.format("-fx-font-size:18px; -fx-font-weight:bold;"));
		
		
		labelPseudoJoueur.setWrapText(true);
		labelPseudoJoueur.setLayoutX(50);
		labelPseudoJoueur.setLayoutY(160);
		labelPseudoJoueur.setPrefWidth(400);
		labelPseudoJoueur.setPrefHeight(30);
		labelPseudoJoueur.setText("Nom d'utilisateur");
		labelPseudoJoueur.setStyle(String.format("-fx-font-size:16px; -fx-font-weight:bold;"));
		
		
		textPseudoJoueur.setVisible(true);
		textPseudoJoueur.setLayoutX(50);
		textPseudoJoueur.setLayoutY(195);
		textPseudoJoueur.setPrefWidth(400);
		textPseudoJoueur.setPrefHeight(30);
		textPseudoJoueur.setPromptText("Pseudo");
		
		
		labelMotDePasseJoueur.setWrapText(true);
		labelMotDePasseJoueur.setLayoutX(50);
		labelMotDePasseJoueur.setLayoutY(235);
		labelMotDePasseJoueur.setPrefWidth(400);
		labelMotDePasseJoueur.setPrefHeight(30);
		labelMotDePasseJoueur.setText("Mot de passe");
		labelMotDePasseJoueur.setStyle(String.format("-fx-font-size:16px; -fx-font-weight:bold;"));
		
		
		textMotDePasseJoueur.setVisible(true);
		textMotDePasseJoueur.setLayoutX(50);
		textMotDePasseJoueur.setLayoutY(270);
		textMotDePasseJoueur.setPrefWidth(400);
		textMotDePasseJoueur.setPrefHeight(30);
		
		btnSeConnecter.setVisible(true);
		btnSeConnecter.setLayoutX(150);
		btnSeConnecter.setLayoutY(320);
		btnSeConnecter.setPrefWidth(200);
		btnSeConnecter.setPrefHeight(30);
		btnSeConnecter.setText("Connexion");
		btnSeConnecter.setStyle(String.format("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#4FC3F7; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
			
		
		//
		//Section INSCRIPTION
		//
		btnRetourConnexion.setVisible(true);
		btnRetourConnexion.setLayoutX(30);
		btnRetourConnexion.setLayoutY(30);
		btnRetourConnexion.setPrefWidth(100);
		btnRetourConnexion.setPrefHeight(30);
		btnRetourConnexion.setText("Retour");
		btnRetourConnexion.setStyle(String.format("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#e57373; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
		labelSinscrireSection.setWrapText(true);
		labelSinscrireSection.setLayoutX(30);
		labelSinscrireSection.setLayoutY(120);
		labelSinscrireSection.setPrefWidth(400);
		labelSinscrireSection.setPrefHeight(30);
		labelSinscrireSection.setText("Inscription :");
		labelSinscrireSection.setStyle(String.format("-fx-font-size:18px; -fx-font-weight:bold;"));
		
		
		labelInscriptionPseudoJoueur.setWrapText(true);
		labelInscriptionPseudoJoueur.setLayoutX(50);
		labelInscriptionPseudoJoueur.setLayoutY(160);
		labelInscriptionPseudoJoueur.setPrefWidth(400);
		labelInscriptionPseudoJoueur.setPrefHeight(30);
		labelInscriptionPseudoJoueur.setText("Nom d'utilisateur");
		labelInscriptionPseudoJoueur.setStyle(String.format("-fx-font-size:16px; -fx-font-weight:bold;"));
		
		
		textInscriptionPseudoJoueur.setVisible(true);
		textInscriptionPseudoJoueur.setLayoutX(50);
		textInscriptionPseudoJoueur.setLayoutY(195);
		textInscriptionPseudoJoueur.setPrefWidth(400);
		textInscriptionPseudoJoueur.setPrefHeight(30);
		textInscriptionPseudoJoueur.setPromptText("Pseudo");
		
		
		labelInscriptionMotDePasseJoueur.setWrapText(true);
		labelInscriptionMotDePasseJoueur.setLayoutX(50);
		labelInscriptionMotDePasseJoueur.setLayoutY(235);
		labelInscriptionMotDePasseJoueur.setPrefWidth(400);
		labelInscriptionMotDePasseJoueur.setPrefHeight(30);
		labelInscriptionMotDePasseJoueur.setText("Mot de passe");
		labelInscriptionMotDePasseJoueur.setStyle(String.format("-fx-font-size:16px; -fx-font-weight:bold;"));
		
		
		textInscriptionMotDePasseJoueur.setVisible(true);
		textInscriptionMotDePasseJoueur.setLayoutX(50);
		textInscriptionMotDePasseJoueur.setLayoutY(270);
		textInscriptionMotDePasseJoueur.setPrefWidth(400);
		textInscriptionMotDePasseJoueur.setPrefHeight(30);
		
		btnValiderInscrire.setVisible(true);
		btnValiderInscrire.setLayoutX(150);
		btnValiderInscrire.setLayoutY(320);
		btnValiderInscrire.setPrefWidth(200);
		btnValiderInscrire.setPrefHeight(30);
		btnValiderInscrire.setText("Valider");
		btnValiderInscrire.setStyle(String.format("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#4FC3F7; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
		
		
		//
		//Section HEBERGEMENT
		//		
		labelCreeServeurSection.setWrapText(true);
		labelCreeServeurSection.setLayoutX(30);
		labelCreeServeurSection.setLayoutY(120);
		labelCreeServeurSection.setPrefWidth(400);
		labelCreeServeurSection.setPrefHeight(30);
		labelCreeServeurSection.setText("Cr�e un serveur :");
		labelCreeServeurSection.setStyle(String.format("-fx-font-size:18px; -fx-font-weight:bold;"));
		
		
		labelPortCreationServeur.setWrapText(true);
		labelPortCreationServeur.setLayoutX(50);
		labelPortCreationServeur.setLayoutY(160);
		labelPortCreationServeur.setPrefWidth(400);
		labelPortCreationServeur.setPrefHeight(30);
		labelPortCreationServeur.setText("Port");
		labelPortCreationServeur.setStyle(String.format("-fx-font-size:16px; -fx-font-weight:bold;"));
		
		
		textPortCreationServeur.setVisible(true);
		textPortCreationServeur.setLayoutX(50);
		textPortCreationServeur.setLayoutY(195);
		textPortCreationServeur.setPrefWidth(400);
		textPortCreationServeur.setPrefHeight(30);
		textPortCreationServeur.setPromptText("Entrez le num�ro de port de votre serveur");
		
		btnNouveauServeur.setVisible(true);
		btnNouveauServeur.setLayoutX(50);
		btnNouveauServeur.setLayoutY(235);
		btnNouveauServeur.setPrefWidth(400);
		btnNouveauServeur.setPrefHeight(50);
		btnNouveauServeur.setText("Nouveau serveur");
		btnNouveauServeur.setStyle(String.format("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#FFD54F; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
		labelRejoindreServeurSection.setWrapText(true);
		labelRejoindreServeurSection.setLayoutX(30);
		labelRejoindreServeurSection.setLayoutY(310);
		labelRejoindreServeurSection.setPrefWidth(400);
		labelRejoindreServeurSection.setPrefHeight(30);
		labelRejoindreServeurSection.setText("Rejoindre un serveur :");
		labelRejoindreServeurSection.setStyle(String.format("-fx-font-size:18px; -fx-font-weight:bold;"));
		
		
		labelPortRejoindreServeur.setWrapText(true);
		labelPortRejoindreServeur.setLayoutX(50);
		labelPortRejoindreServeur.setLayoutY(350);
		labelPortRejoindreServeur.setPrefWidth(400);
		labelPortRejoindreServeur.setPrefHeight(30);
		labelPortRejoindreServeur.setText("Port");
		labelPortRejoindreServeur.setStyle(String.format("-fx-font-size:16px; -fx-font-weight:bold;"));
		
		
		textPortRejoindreServeur.setVisible(true);
		textPortRejoindreServeur.setLayoutX(50);
		textPortRejoindreServeur.setLayoutY(385);
		textPortRejoindreServeur.setPrefWidth(400);
		textPortRejoindreServeur.setPrefHeight(30);
		textPortRejoindreServeur.setPromptText("Entrez le num�ro de port du serveur");
		
		btnRejoindreServeur.setVisible(true);
		btnRejoindreServeur.setLayoutX(50);
		btnRejoindreServeur.setLayoutY(425);
		btnRejoindreServeur.setPrefWidth(400);
		btnRejoindreServeur.setPrefHeight(50);
		btnRejoindreServeur.setText("Rejoindre le serveur");
		btnRejoindreServeur.setStyle(String.format("-fx-font-size:14px; -fx-font-weight:bold; -fx-background-color:#FFD54F; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
		
		
		
		//
		//Section CHAT
		//	
		scrollReceivedText.setLayoutX(50);
		scrollReceivedText.setLayoutY(50);
		scrollReceivedText.setPrefWidth(400);
		scrollReceivedText.setPrefHeight(350);
		scrollReceivedText.setFitToWidth(true);
		scrollReceivedText.setContent(receivedText);
		scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
		
		
		textToSend.setEditable(true);
		textToSend.setLayoutX(50);
		textToSend.setLayoutY(420);
		textToSend.setPrefWidth(400);
		textToSend.setPrefHeight(100);
		textToSend.setWrapText(true);
		
		
		receivedText.setVisible(true);
		
		
		sendBtn.setVisible(true);
		sendBtn.setLayoutX(50);
		sendBtn.setLayoutY(540);
		sendBtn.setPrefWidth(400);
		sendBtn.setPrefHeight(50);
		sendBtn.setText("Envoyer");
		sendBtn.setStyle(String.format("-fx-font-size:18px; -fx-font-weight:bold; -fx-background-color:#E0E0E0; -fx-background-radius:7px;"
				+"-fx-border-color:black; -fx-border-radius: 7px; -fx-border-width:2px;"));
		
		
		
		
		//
		//Les ECOUTEUR
		//
		btnSinscrire.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//Suppresion des �l�ment plus utile		
				getChildren().remove(btnSinscrire);
				getChildren().remove(labelSeConnecterSection);
				getChildren().remove(labelPseudoJoueur);
				getChildren().remove(textPseudoJoueur);
				getChildren().remove(labelMotDePasseJoueur);
				getChildren().remove(textMotDePasseJoueur);
				getChildren().remove(btnSeConnecter);
				
				//Ajout des �l�ment utile
				getChildren().add(btnRetourConnexion);
				getChildren().add(labelSinscrireSection);
				getChildren().add(labelInscriptionPseudoJoueur);
				getChildren().add(textInscriptionPseudoJoueur);
				getChildren().add(labelInscriptionMotDePasseJoueur);
				getChildren().add(textInscriptionMotDePasseJoueur);
				getChildren().add(btnValiderInscrire);
				
			}
		});
		
		btnRetourConnexion.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//Suppresion des �l�ment plus utile		
				getChildren().remove(btnRetourConnexion);
				getChildren().remove(labelSinscrireSection);
				getChildren().remove(labelInscriptionPseudoJoueur);
				getChildren().remove(textInscriptionPseudoJoueur);
				getChildren().remove(labelInscriptionMotDePasseJoueur);
				getChildren().remove(textInscriptionMotDePasseJoueur);
				getChildren().remove(btnValiderInscrire);
				
				//Ajout des �l�ment utile
				getChildren().add(btnSinscrire);
				getChildren().add(labelSeConnecterSection);
				getChildren().add(labelPseudoJoueur);
				getChildren().add(textPseudoJoueur);
				getChildren().add(labelMotDePasseJoueur);
				getChildren().add(textMotDePasseJoueur);
				getChildren().add(btnSeConnecter);
			}
		});
		
		btnSeConnecter.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				pseudoDuJoueur = textPseudoJoueur.getText();
				
				//Suppresion des �l�ment plus utile		
				getChildren().remove(btnSinscrire);
				getChildren().remove(labelSeConnecterSection);
				getChildren().remove(labelPseudoJoueur);
				getChildren().remove(textPseudoJoueur);
				getChildren().remove(labelMotDePasseJoueur);
				getChildren().remove(textMotDePasseJoueur);
				getChildren().remove(btnSeConnecter);
				
				//Ajout des �l�ment utile
				getChildren().add(labelCreeServeurSection);
				getChildren().add(labelPortCreationServeur);
				getChildren().add(textPortCreationServeur);
				getChildren().add(btnNouveauServeur);
				getChildren().add(labelRejoindreServeurSection);
				getChildren().add(labelPortRejoindreServeur);
				getChildren().add(textPortRejoindreServeur);
				getChildren().add(btnRejoindreServeur);			
			}
		});
			
		btnNouveauServeur.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				//V�rification de la donn�e entr� en num�ro de port				
				if (textPortCreationServeur.getText().matches("[0-9]+"))
				{
					// Parse en nombre le port
					int numeroDuPortNouveauServ = Integer.parseInt(textPortCreationServeur.getText());
					if (numeroDuPortNouveauServ >= 2000 && numeroDuPortNouveauServ <= 2100)
					{
						estServeur = true;
						//Cr�ation du serveur	
						MainServer unServ =	new MainServer(textPortCreationServeur.getText());
						
						try {
							TimeUnit.SECONDS.sleep(2);
						}
						catch (Exception e){
							System.out.println(e.getMessage());
						}
						
						//Cr�ation du client
						unMainClient = new MainClient("127.0.0.1", textPortCreationServeur.getText(), "[HOST] " + pseudoDuJoueur);
						
						//Suppresion des �l�ment plus utile	
						getChildren().remove(labelCreeServeurSection);
						getChildren().remove(labelPortCreationServeur);
						getChildren().remove(textPortCreationServeur);
						getChildren().remove(btnNouveauServeur);
						getChildren().remove(labelRejoindreServeurSection);
						getChildren().remove(labelPortRejoindreServeur);
						getChildren().remove(textPortRejoindreServeur);
						getChildren().remove(btnRejoindreServeur);
						
						//Ajout des �l�ment utile
						getChildren().add(scrollReceivedText);
						getChildren().add(textToSend);
						getChildren().add(sendBtn);
					}
					else
					{
						//Alerte l'utilisateur qu'il n'a pas entrer un chiffre disponible en port
						Alert alertPortMauvaisNombre = new Alert(AlertType.INFORMATION);
						alertPortMauvaisNombre.setTitle("Mauvais port");
						alertPortMauvaisNombre.setHeaderText("Le port doit �tre un nombre entier entre 2000 et 2100");
						alertPortMauvaisNombre.setContentText("Merci d'entrer un nombre entre 2000 et 2100 comme port du serveur");
						alertPortMauvaisNombre.showAndWait();
					}
				}
				else
				{
					//Alerte l'utilisateur qu'il n'a pas entr� un chiffre dans le champ port
					Alert alertPortPasNombre = new Alert(AlertType.INFORMATION);
					alertPortPasNombre.setTitle("Mauvais port");
					alertPortPasNombre.setHeaderText("Le port saisi n'est pas un nombre entier");
					alertPortPasNombre.setContentText("Merci d'entrer un nombre entier comme port du serveur");
					alertPortPasNombre.showAndWait();
				}			
			}
		});
		
		btnRejoindreServeur.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{		
				//V�rification de la donn�e entr� en num�ro de port				
				if (textPortRejoindreServeur.getText().matches("[0-9]+"))
				{
					// Parse en nombre le port
					int numeroDuPortRejoindreServ = Integer.parseInt(textPortRejoindreServeur.getText());
					if (numeroDuPortRejoindreServ >= 2000 && numeroDuPortRejoindreServ <= 2100)
					{
						//Cr�ation du client
						unMainClient = new MainClient("127.0.0.1", textPortRejoindreServeur.getText(), pseudoDuJoueur);
						
						//Suppresion des �l�ment plus utile	
						getChildren().remove(labelCreeServeurSection);
						getChildren().remove(labelPortCreationServeur);
						getChildren().remove(textPortCreationServeur);
						getChildren().remove(btnNouveauServeur);
						getChildren().remove(labelRejoindreServeurSection);
						getChildren().remove(labelPortRejoindreServeur);
						getChildren().remove(textPortRejoindreServeur);
						getChildren().remove(btnRejoindreServeur);
						
						//Ajout des �l�ment utile
						getChildren().add(scrollReceivedText);
						getChildren().add(textToSend);
						getChildren().add(sendBtn);		
					}
					else
					{
						//Alerte l'utilisateur qu'il n'a pas entrer un chiffre disponible en port
						Alert alertPortMauvaisNombre = new Alert(AlertType.INFORMATION);
						alertPortMauvaisNombre.setTitle("Mauvais port");
						alertPortMauvaisNombre.setHeaderText("Le port doit �tre un nombre entier entre 2000 et 2100");
						alertPortMauvaisNombre.setContentText("Merci d'entrer un nombre entre 2000 et 2100 comme port du serveur");
						alertPortMauvaisNombre.showAndWait();
					}
				}
				else
				{
					//Alerte l'utilisateur qu'il n'a pas entr� un chiffre dans le champ port
					Alert alertPortPasNombre = new Alert(AlertType.INFORMATION);
					alertPortPasNombre.setTitle("Mauvais port");
					alertPortPasNombre.setHeaderText("Le port saisi n'est pas un nombre entier");
					alertPortPasNombre.setContentText("Merci d'entrer un nombre entier comme port du serveur");
					alertPortPasNombre.showAndWait();
				}			
			}
		});
			
		
		sendBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				unMainClient.GetClient().GetLeClientSend().SetMessAEnvoyer(": " + textToSend.getText());
				unMainClient.GetClient().GetLeClientSend().EnvoyerLeMessage(true);
				
				textToSend.setText("");
			}
		});
		

	}
	
	
	
	
	//
	//Les METHODE
	//
	public static void AjouterMess(String leMess, String leSender){	
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	Label messageAEnvoye = new Label();			
				//Retour � la ligne lorsqu'il n'y a plus de place
				messageAEnvoye.setWrapText(true);
				//Le label prend toute la largeur du chat
				messageAEnvoye.setPrefWidth(398);		
				//Le label prend la valeur �rite
				messageAEnvoye.setText(leMess);
				
				if (leSender.equals("ClientDeconnecter"))
				{
					messageAEnvoye.setTextFill(Color.web("#e74c3c"));
				}
				
				if (leSender.equals("nouveauClient"))
				{
					messageAEnvoye.setTextFill(Color.web("#0abde3"));
				}
				
				if (leSender.equals("ServeurDeconnecter"))
				{
					messageAEnvoye.setTextFill(Color.web("#e74c3c"));
					messageAEnvoye.setText(leMess + "\n\nL'HOST est parti\nDeconnexion...");
					receivedText.getChildren().add(messageAEnvoye);
					
					//Alerte l'utilisateur qu'il va �tre d�connecter
					Alert alertDeconnexionHote = new Alert(AlertType.INFORMATION);
					alertDeconnexionHote.setTitle("L'h�te est parti");
					alertDeconnexionHote.setHeaderText("L'h�te du serveur s'est d�connect�");
					alertDeconnexionHote.setContentText("Vous allez �tre d�connect�");
					alertDeconnexionHote.showAndWait();		
					System.exit(0);
				}
							
				
			receivedText.getChildren().add(messageAEnvoye);		
				
				
		    }
		});			
		
	}
	
	
		
	public String AvoirLeMessClient()
	{
		return this.GetTextToSend().getText();
	}
	
	
	public TextArea GetTextToSend(){
		return textToSend;
	}
	
	
	public Button GetSendBtn()
	{
		return this.sendBtn;
	}
	
	public MainClient getLeMainClient() {
		return this.unMainClient;
	}
	
	public boolean GetEstServeur() {
		return this.estServeur;
	}

}





