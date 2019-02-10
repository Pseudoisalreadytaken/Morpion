package Morpion.Controller.Reseau.Client;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


import Morpion.Controller.Reseau.Common.Message;
import Morpion.FrontEnd.ClientPanel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ClientReceive implements Runnable {


	public Client client;
	public Socket socket;
	private String pseudo;
	private ObjectInputStream in;

	
	public ClientReceive(Client unClient, Socket unSocket, String unPseudo)
	{
		this.client = unClient;
		this.socket = unSocket;
		this.pseudo = unPseudo;
	}
	
	
	
	public synchronized void run()
	{
		//cr�e l'object in � partir du socket
		try
		{
			//cr�er l'objet in � partir du socket :
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Attend un nouveau message � l'aide de in tant que la connexion est active. Si le
		//message re�u ne vien pas d'un client d�connecter, on l�affiche sur la sortie standard. Si le message re�u vien d'un client d�connecter, la
		//connexion devient inactive, et on appelle la m�thode disconnectedServer() de l�attribut client
		//
		Message mess;
		while(!Thread.interrupted())
		{
			try
			{
				//On attend un message
				mess = (Message) in.readObject();
				//On ajoute le messsage sur le FrontEnd
				Morpion.FrontEnd.ClientPanel.AjouterMess(mess.GetContent(), mess.GetSender());
				System.out.println(pseudo);		
				if(mess.GetCroixOuRond().equals("croix")) 
				{
					Morpion.FrontEnd.ClientPanel.AjouterCroix(mess.GetX1(), mess.GetX2(), mess.GetY1(), mess.GetY2());
				}	
				else if(mess.GetCroixOuRond().equals("rond"))
				{
					double xDuRond = (mess.GetX1() + mess.GetX2())/2;
					double yDuRond = (mess.GetY1() + mess.GetY2())/2;
					Morpion.FrontEnd.ClientPanel.AjouterRond(xDuRond,yDuRond);
				}
				if(mess.GetWin()) 
				{
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
							Alert alert = new Alert(AlertType.CONFIRMATION);
					        alert.setContentText( " gagng� !");
					        alert.show();
					    }
					});		
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		} 
	}
	
}